package com.zwj.basedemo.util.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.zwj.basedemo.MyApplication;
import com.zwj.basedemo.constant.Constant;
import com.zwj.basedemo.constant.Status;
import com.zwj.basedemo.constant.UrlConstant;
import com.zwj.basedemo.util.LogUtils;
import com.zwj.basedemo.util.SPUtil;
import com.zwj.basedemo.util.ToastUtil;
import com.zwj.basedemo.util.net.bean.RequestBean;
import com.zwj.basedemo.util.net.bean.ResponseBean;
import com.zwj.basedemo.util.net.callback.DownloadCallback;
import com.zwj.basedemo.util.net.callback.SimpleCallBack;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetManager {

    public static final String TAG = "NetManager";
    /**
     * 是否正在重连
     */
    private static boolean isReLoading = false;


    /**
     * 网络请求回调接口
     */
    public interface RequestCallBack {
        /**
         * 访问成功并返回数据时调用该方法
         */
        void onSuccess(ResponseBean responseBean);

        /**
         * 取消网络请求时调用
         */
        void onCancelled(ResponseBean responseBean);

        /**
         * 发生网络错误时调用
         */
        void onError(ResponseBean responseBean);

        /**
         * 不管如何结束后都会调用该方法
         */
        void onFinished(ResponseBean responseBean);
    }

    private static Map<String, RequestBean> requestMap = new HashMap<>();

    /**
     * 需要重连的时候返回null
     */
    public static Cancelable request(@NonNull final Context context, @NonNull final RequestBean requestBean) {
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setUrl(requestBean.getUrl())
                .setShowToast(requestBean.isShowErrorToast());

        // 重置为false
        requestBean.setNeedReconnection(false);
        // 添加到网络访问列表里
        requestMap.put(requestBean.toString(), requestBean);

        if (!isNetWorkReachable()) {
            if (requestBean.getCallback() != null) {
                responseBean.setStatus(ResponseStatus.DISABLE_NETWORK)
                        .setMessage("当前网络不可用，请检查网络后再试")
                        .setThrowable(new Throwable("network disable"));
                requestBean.getCallback().onError(responseBean);
                requestBean.getCallback().onFinished(responseBean);
            }
            return null;
        }

        if (requestBean.isShowLoading()) {
            //TODO
//            ProgressUtil.startProgress(context, requestBean.getTipContent());
        }

        RequestParams params = new RequestParams(requestBean.getUrl());
        params.setConnectTimeout(1000 * 10);  // 网络超时时间设置为10s
        if (!TextUtils.isEmpty(requestBean.getBodyContent())) {
            // 以json数据格式提交
            params.setAsJsonContent(true);
            params.setBodyContent(requestBean.getBodyContent());
        } else {
            Map<String, String> paramMap = requestBean.getParamMap();
            if (paramMap != null) {
                Set<String> keySet = paramMap.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = paramMap.get(key);

                    // 打印参数名称和值
                    StringBuilder sbParam = new StringBuilder();
                    sbParam.append("param: ").append(key).append(" ---> ").append(value);
                    LogUtils.i(TAG, sbParam.toString());

                    params.addBodyParameter(key, value);
                }
            }

            // 添加数组参数
            Map<String, List<String>> paramArrayList = requestBean.getParamArrayMap();
            if (paramArrayList != null) {
                Set<String> keySet = paramArrayList.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<String> valueList = paramArrayList.get(key);

                    // 打印参数名称和值
                    for (int i = 0; i < valueList.size(); i++) {
                        StringBuilder sbParam = new StringBuilder();
                        sbParam.append("param: ").append(key).append(" ---> ").append(valueList.get(i));
                        LogUtils.i(TAG, sbParam.toString());

                        params.addBodyParameter(key, valueList.get(i));
                    }
                }
            }
        }


        // 过期了就重新登陆
//        String lastTimeStr = FileUtils.loadContentFromFiles(MyApplication.getGlobalContext(),
//                Constant.FILE_TIME);
//        long curTime = System.currentTimeMillis();
//        FileUtils.writeContent2File(null, Constant.FILE_TIME, String.valueOf(curTime), false);
//
//        if (!TextUtils.isEmpty(lastTimeStr)) {
//            LogUtils.i(TAG, "lastTimeStr --> " + lastTimeStr);
//            long lastTimeL = Long.parseLong(lastTimeStr);
//            User user = UserUtil.getInstance().getUser();
//            //比后台的时间小于一小时就重连
//            if ((curTime - lastTimeL) > (Constant.LOSE_EFFICICY_TIME - 10 * 60 * 1000)
//                    && user != null) {
//                reLoginWithPwd(context, user, requestBean);
//                return null;
//            }
//        }

        // 添加Cookie
        if (requestBean.isNeedCookies()) {
            if (!TextUtils.isEmpty(SPUtil.getString(
                    MyApplication.getGlobalContext(), Constant.COOKIE_NAME))) {
                StringBuilder sbCookie = new StringBuilder();
                sbCookie.append(Constant.COOKIE_NAME)
                        .append("=")
                        .append(SPUtil.getString(MyApplication.getGlobalContext(),
                                Constant.COOKIE_NAME)).append("; path=/; domain=")
                        .append(UrlConstant.DOMAIN);
                LogUtils.e(TAG, "cookie------>" + sbCookie);
                params.addHeader("Cookie", sbCookie.toString());
            }

            if (!TextUtils.isEmpty(SPUtil.getString(
                    context.getApplicationContext(), Constant.COOKIE_SESSION))) {
                StringBuilder sbSession = new StringBuilder();
                sbSession
                        .append(Constant.COOKIE_SESSION)
                        .append("=")
                        .append(SPUtil.getString(context.getApplicationContext(),
                                Constant.COOKIE_SESSION))
                        .append("; path=/; domain=").append(UrlConstant.DOMAIN);
                LogUtils.e(TAG, "session------>" + sbSession);
                params.addHeader("Cookie", sbSession.toString());
            }
        }

        CommonCallback<String> commonCallback = new CommonCallback<String>() {

            @Override
            public void onCancelled(CancelledException cancelledException) {
                if (requestBean.getCallback() != null) {
                    responseBean.setStatus(ResponseStatus.CANCELLED_EXCEPTION)
                            .setMessage("取消网络请求")
                            .setCancelledException(cancelledException);
                    requestBean.getCallback().onCancelled(responseBean);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();

//                    StringBuilder sbResult = new StringBuilder();
//                    sbResult.append("网络错误: code-->").append(responseCode)
//                            .append("; message --> ").append(responseMsg);
                    responseBean.setStatus(ResponseStatus.ERROR)
                            .setMessage("网络错误")
                            .setThrowable(ex)
                            .setShowToast(true);

                    // TODO
//                    MobclickAgent.reportError(context,
//                            DateUtil.getDay(System.currentTimeMillis())
//                                    + "-------->" + requestBean.getUrl()
//                                    + "--------->" + ex.toString()); // 把错误上传到友盟
                } else { // 其他错误
                    responseBean.setStatus(ResponseStatus.ERROR_OTHER)
                            .setMessage("出错啦~")
                            .setThrowable(ex)
                            .setShowToast(true);

                    // TODO
//                    MobclickAgent.reportError(context, ex);
                    LogUtils.sysout(TAG + ": error --> " + ex.toString());
                }

                ex.printStackTrace();

                // 连接超时，重连(最多重连2次)
                if ((ex instanceof SocketTimeoutException || ex instanceof UnknownHostException) && requestBean.getCount() < RequestBean.MAX_RECONNECTION_COUNT) {
                    // 连接超时，设置重连标志
                    requestBean.setNeedReconnection(true).setCount(requestBean.getCount() + 1);

                    LogUtils.i(TAG, "重连 ---> ");
                    LogUtils.i(TAG, "url ---> " + requestBean.getUrl());
                    LogUtils.i(TAG, "count ---> " + requestBean.getCount());
                } else {
                    if (requestBean.getCallback() != null) {
                        requestBean.getCallback().onError(responseBean);
                    }
                }
            }

            @Override
            public void onFinished() {
                if (requestBean.isShowLoading() && (!isReLoading || !requestBean.isNeedReconnection())) {
                    // TODO
//                    ProgressUtil.hideProgress();
                }

                if (requestBean.isNeedReconnection() && requestBean.getCount() < 3) {
                    // 进行重连
                    requestBean.setShowLoading(false);
                    requestBean.setNeedReconnection(false);
                    request(context, requestBean);
                } else {
                    requestMap.remove(requestBean.toString());
                    if (requestBean.getCallback() != null) {
                        requestBean.getCallback().onFinished(responseBean);
                    }
                }
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.e(TAG, result);
                if (!requestBean.isNeedParse()) {
                    if (requestBean.getCallback() != null) {
                        responseBean.setStatus(ResponseStatus.SUCCESS)
                                .setMessage("获取数据成功")
                                .setResult(result)
                                .setShowToast(false);
                        requestBean.getCallback().onSuccess(responseBean);
                    }
                    return;
                }
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(result);
                    int status = jsonObject.optInt(Status.STATUS, 1000);
                    switch (status) {
                        case ResponseStatus.SUCCESS:// 成功获取数据
                            String datas = jsonObject.optString(Status.DATA);
                            if (requestBean.getCallback() != null) {
                                responseBean.setStatus(ResponseStatus.SUCCESS)
                                        .setMessage("获取数据成功")
                                        .setResult(datas)
                                        .setShowToast(false);
                                requestBean.getCallback().onSuccess(responseBean);
                            }
                            break;
                        case ResponseStatus.SUCCESS_ONLY_DATA:// 成功获取数据，没有返回状态字直接返回数据的情况
                            if (requestBean.getCallback() != null) {
                                responseBean.setStatus(ResponseStatus.SUCCESS_ONLY_DATA)
                                        .setMessage("获取数据成功")
                                        .setResult(result)
                                        .setShowToast(false);
                                requestBean.getCallback().onSuccess(responseBean);
                            }
                            break;
                        case ResponseStatus.UNLOGIN:// 当前未登录
                            // TODO
//                            User user = UserUtil.getInstance().getUser();
//                            if (user != null) {
//                                isReLoading = true;
//                                responseBean.setStatus(ResponseStatus.UNLOGIN)
//                                        .setMessage("获取数据成功")
//                                        .setResult(result);
//                                reLoginWithPwd(context, user, requestBean);
//                            }
                            break;
                        case ResponseStatus.FAIL:// 获取数据异常(+已拉去玩全部数据的情况)
                            String msg = jsonObject.optString(Status.MESSAGE);
                            if (requestBean.getCallback() != null) {
                                responseBean.setStatus(ResponseStatus.FAIL)
                                        .setMessage(msg)
                                        .setThrowable(new Throwable("fail"))
                                        .setResult(result);

                                requestBean.getCallback().onError(responseBean);
                            }
                            break;
                    }

                } catch (JSONException e) {
                    LogUtils.e(TAG, e.getMessage());
                    e.printStackTrace();

                    if (requestBean.getCancelable() != null) {
                        responseBean.setStatus(ResponseStatus.ERROR_OTHER)
                                .setMessage("解析出错")
                                .setThrowable(e);

                        requestBean.getCallback().onError(responseBean);
                    }
                }
            }
        };

        if (requestBean.getRequestMethod() == RequestBean.METHOD_POST) {
            return x.http().post(params, commonCallback);
        } else {
            return x.http().get(params, commonCallback);
        }
    }

    /**
     * 检查网络是否连通
     */
    public static boolean isNetWorkReachable() {
        final ConnectivityManager mManager = (ConnectivityManager) MyApplication
                .getGlobalContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        // 如果没有可用的数据网络会返回null
        NetworkInfo current = mManager.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        // 有可用的数据网络还需要检查他的稳定性
        return (current.getState() == NetworkInfo.State.CONNECTED);
    }

    /**
     * 重新登录
     */
//    public static void reLoginWithPwd(@NonNull final Context context, @NonNull final User user, @NonNull final RequestBean requestBean) {
//
//        LogUtils.e(TAG, "进行了重新登陆");
//        FileUtils.writeContent2File(null, "login.txt",
//                DateUtil.getMillon(System.currentTimeMillis())
//                        + "------->进行了重新登陆", true);
//
//        String userName = user.getAccount();
//        String passWd = user.getPwd();
//        Map<String, String> loginParamMap = new HashMap<>();
//        loginParamMap.put("Account", userName);
//        loginParamMap.put("Pwd", passWd);
//
//        RequestBean loginRequestBean = new RequestBean(UrlConstant.LOGIN, RequestBean.METHOD_POST);
//        loginRequestBean.setParamMap(loginParamMap).setNeedCookies(false).setCallback(new SimpleCallBack() {
//            @Override
//            public void onSuccess(ResponseBean responseBean) {
//
//                if (responseBean.getStatus() != ResponseStatus.UNLOGIN) {
//                    // 保存cookie的值
//                    DbCookieStore instance = DbCookieStore.INSTANCE;
//                    List<HttpCookie> cookies = instance.getCookies();
//                    for (int i = 0; i < cookies.size(); i++) {
//                        HttpCookie cookie = cookies.get(i);
//                        if (cookie.getName() != null
//                                && cookie.getName().equals(Constant.COOKIE_NAME)) {
//                            SPUtil.putString(context.getApplicationContext(),
//                                    Constant.COOKIE_NAME, cookie.getValue());
//                        }
//
//                        if (cookie.getName() != null
//                                && cookie.getName().equals(Constant.COOKIE_SESSION)) {
//                            SPUtil.putString(context.getApplicationContext(),
//                                    Constant.COOKIE_SESSION, cookie.getValue());
//                        }
//                        LogUtils.sysout(TAG + ": cookie name --> "
//                                + cookie.getName());
//                        LogUtils.sysout(TAG + ": cookie value --> "
//                                + cookie.getValue());
//                    }
//                    FileUtils.writeContent2File(null, "login.txt",
//                            DateUtil.getMillon(System.currentTimeMillis())
//                                    + "------->重新登陆成功", true);
//
//                    // 重新发起未登录前的请求
//                    request(context, requestBean);
//                }
//            }
//
//            @Override
//            public void onFinished(ResponseBean responseBean) {
//                super.onFinished(responseBean);
//                isReLoading = false;
//            }
//        }).request(context);
//    }

    public static void deleteImgFromServer(@NonNull Context context, @NonNull String url,
                                           final boolean isNeedToast) {
        RequestBean deleteImgRequestBean = new RequestBean(UrlConstant.POST_DELETE_IMG, RequestBean.METHOD_POST);
        deleteImgRequestBean.addParam("fileName", url).setCallback(new SimpleCallBack() {
            @Override
            public void onSuccess(ResponseBean responseBean) {
                if (isNeedToast) {
                    ToastUtil.toast("删除成功");
                }
            }
        }).setShowLoading(false)
                .request(context);
    }

    /**
     * 上传车辆照片到服务器
     *
     * @param path 图片路径
     */
//    public static void uploadImage(String URL, Context context, String path,
//                                   UploadImageCallBack uploadImageCallBack, boolean isShowLoading) {
//
//        ResponseBean responseBean = null;
//        if (path == null) {
//            responseBean = new ResponseBean(ResponseStatus.PARSE_IMAGE_FILE_TO_BASE64, "本地图片路径为空!");
//            responseBean.setThrowable(new Throwable("本地图片路径为空!"));
//            if (uploadImageCallBack != null) {
//                uploadImageCallBack.onError(responseBean);
//                uploadImageCallBack.onFinished(responseBean);
//            }
//            return;
//        }
//
//        JSONObject json = new JSONObject();
//        try {
//            json.put("BaseCode", BitmapCompressUtil.BitmapToStream(path));
//        } catch (JSONException e) {
//            if (uploadImageCallBack != null) {
//                responseBean = new ResponseBean(ResponseStatus.PARSE_IMAGE_FILE_TO_BASE64, "解析错误");
//                uploadImageCallBack.onError(responseBean);
//                uploadImageCallBack.onFinished(responseBean);
//            }
//            LogUtils.e(TAG, e.getMessage());
//        }
//        RequestBean uploadImgRequestBean = new RequestBean(URL, RequestBean.METHOD_POST);
//        uploadImgRequestBean.setBodyContent(json.toString())
//                .setShowLoading(isShowLoading)
//                .setCallback(uploadImageCallBack).
//                request(context);
//    }

    /**
     * 从后台获取城市数据
     *
     * @param context
     */
//    public static void getCiytInfos(final Context context) {
//        RequestBean requestBean = new RequestBean(UrlConstant.GET_OPEN_CITY_INFOS, RequestBean.METHOD_GET);
//        requestBean.setNeedParse(false)
//                .setShowErrorToast(false)
//                .setCallback(new SimpleCallBack() {
//                    @Override
//                    public void onSuccess(ResponseBean responseBean) {
//                        LogUtils.sysout(TAG + ": city info ---> " + responseBean.getResult());
//
//                        List<ProvinceBeanBaseOnServer> provinceList = CityDAO.setAresData(JsonUtil.getObjects(responseBean.getResult(), ProvinceBeanBaseOnServer.class));
//                        if (provinceList != null) {
////                            MyApplication.setExistCityData(true);
//
//                            String jsonString = JSON.toJSONString(provinceList);
//                            LogUtils.sysout("jsonString ----> " + jsonString);
//                            FileUtils.saveFile(context, Constant.FILE_CITY_NAME,
//                                    jsonString.getBytes());
//
//                            // 把城市数据库中的数据取出
//                            new Thread(() -> {
//                                MyApplication.setProvinceList(CityDAO.getAllCities());
//                            }).start();
//                        }
//                    }
//                }).request(context);
//    }

    /**
     * 取消tag所标记的所有网络请求
     *
     * @param tag
     */
    public static void cacelRquests(String tag) {
        Set<Map.Entry<String, RequestBean>> entrySet = requestMap.entrySet();
        Iterator<Map.Entry<String, RequestBean>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, RequestBean> entry = iterator.next();
            RequestBean requestBean = entry.getValue();

            if (tag != null) {
                if (tag.equals(requestBean.getTag()) && requestBean.getCancelable() != null && !requestBean.getCancelable().isCancelled()) {
                    requestBean.getCancelable().cancel();
                    iterator.remove();
                }
            } else {
                if (requestBean.getCancelable() != null && !requestBean.getCancelable().isCancelled()) {
                    requestBean.getCancelable().cancel();
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 取消全部请求
     */
    public static void cacelAllRquests() {
        cacelRquests(null);
    }

    /**
     * 下载
     */
    public static void download(String url, File destFolder, DownloadCallback downloadCallback) {
        if (destFolder != null && destFolder.exists()) {
            StringBuilder sbDownPath = new StringBuilder();
            sbDownPath.append(destFolder.getAbsolutePath()).append(File.separator).append(url.substring(url.lastIndexOf("/") + 1));
            LogUtils.sysout("downPath ----> " + sbDownPath.toString());

            RequestParams params = new RequestParams(url);
            params.setAutoResume(true);
            params.setAutoRename(false);
            params.setSaveFilePath(sbDownPath.toString());
//	        params.setExecutor(executor);
            // 为请求创建新的线程, 取消时请求线程被立即中断; false: 请求建立过程可能不被立即终止
            params.setCancelFast(true);
            x.http().get(params, downloadCallback);
        } else {
            Toast.makeText(MyApplication.getGlobalContext(), "找不到下载路径,请确认是否已安装sdcard", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 获取服务端一些文件的版本的信息
     */
//    public static void getVersion(Context context) {
//        new RequestBean(UrlConstant.GET_VERSION, RequestBean.METHOD_GET)
//                .setNeedParse(false)
//                .setShowErrorToast(false)
//                .setCallback(new SimpleCallBack() {
//                    @Override
//                    public void onSuccess(ResponseBean responseBean) {
//                        LogUtils.sysout(TAG + " version ---> " + responseBean.getResult());
//                        List<VersionBean> versionBeanList = JsonUtil.getObjects(responseBean.getResult(), VersionBean.class);
//
//                        VersionDAO versionDAO = VersionDAO.getInstance(MyApplication.getGlobalContext());
//                        List<VersionBean> oldVersionBeanList = versionDAO.getVersionBeanList();
//
//                        List<VersionBean> newVersionBeanList = new ArrayList<VersionBean>();
//                        for (int i = 0; i < versionBeanList.size(); i++) {
//                            VersionBean versionBean = versionBeanList.get(i);
//
//                            // 还未有该条记录或者有更新则为true
//                            boolean isNew = true;
//                            for (int j = 0; j < oldVersionBeanList.size(); j++) {
//                                if (versionBean.getObjectlID() == oldVersionBeanList.get(j).getObjectlID()) {
//                                    if (versionBean.getObjectVersion().equals(oldVersionBeanList.get(j).getObjectVersion())) {
//                                        isNew = false;
//                                    }
//                                }
//                            }
//
//                            if (isNew) {
//                                newVersionBeanList.add(versionBean);
//                            }
//                        }
//
//                        boolean isNeedUpdateCityDB = false; // 是否需要更新城市数据库
//                        if (newVersionBeanList != null) {
//                            for (int i = 0; i < newVersionBeanList.size(); i++) {
//                                VersionBean newVersionBean = newVersionBeanList.get(i);
//                                if (newVersionBean.getObjectlID() == 5) {
//                                    isNeedUpdateCityDB = true;
//                                }
//
//                                // 不下载city.json和apk
//                                // 1为city.json；2为apk
//                                if (newVersionBean.getObjectlID() > 2) {
//                                    download(newVersionBean.getObjectURL(), FileUtils.getFolder(), new VersionFileDownloadCallback(newVersionBean) {
//
//                                        @Override
//                                        public void onError(Throwable ex, boolean isOnCallback) {
//                                            super.onError(ex, isOnCallback);
//                                        }
//                                    }.setmOnVersionFileDownloadSuccessListener(new VersionFileDownloadCallback.OnVersionFileDownloadSuccessListener() {
//                                        @Override
//                                        public void onSuccess(File result, VersionBean versionBean) {
//                                            VersionDAO versionDAO = VersionDAO.getInstance(MyApplication.getGlobalContext());
//                                            versionDAO.add(versionBean);
//
//                                            if (newVersionBean.getObjectlID() == 5) {    // 城市数据库
//                                                // 每次启动就从后台获取城市数据
//                                                NetManager.getCiytInfos(MyApplication.getGlobalContext());
//                                            }
//                                        }
//                                    }));
//                                }
//                            }
//                        }
//
//                        if (!isNeedUpdateCityDB) {
//                            NetManager.getCiytInfos(MyApplication.getGlobalContext());
//                        }
//                    }
//                }).request(context);
//    }
}
