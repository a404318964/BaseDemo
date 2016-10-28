package com.zwj.basedemo.util.net.callback;


import com.zwj.basedemo.constant.UrlConstant;
import com.zwj.basedemo.util.LogUtils;
import com.zwj.basedemo.util.ToastUtil;
import com.zwj.basedemo.util.net.NetManager;
import com.zwj.basedemo.util.net.bean.ResponseBean;

import java.net.SocketTimeoutException;

/**
 * 简单的网络请求回调类
 */
public abstract class SimpleCallBack implements NetManager.RequestCallBack {

    public static final String TAG = "SimpleCallBack";

    @Override
    public void onCancelled(ResponseBean responseBean) {
        LogUtils.e(TAG, "执行onCancelled");
    }

    @Override
    public void onError(ResponseBean responseBean) {
        LogUtils.e(TAG, "执行onError--------->");

        if (responseBean.isShowToast() && responseBean.getThrowable() != null) {
            responseBean.getThrowable().printStackTrace();

            // 连接超时，重连
            if(responseBean.isShowToast() && !UrlConstant.GET_VERSION.equals(responseBean.getUrl()) && !UrlConstant.GET_OPEN_CITY_INFOS.equals(responseBean.getUrl())) {
                if (responseBean.getThrowable() instanceof SocketTimeoutException) {
                    String errorMsg = "网络连接超时 url --> "+responseBean.getUrl();
                    ToastUtil.toast(errorMsg);
                    // TODO
//                    FileUtils.saveFile(MyApplication.getGlobalContext(), "timeout_error.txt", errorMsg.getBytes());
                } else {
                    ToastUtil.toast(responseBean.getMessage());
                }
            }
        }

    }

    @Override
    public void onFinished(ResponseBean responseBean) {
        LogUtils.e(TAG, "执行onFinished");
    }

}
