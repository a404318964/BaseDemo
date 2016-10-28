package com.zwj.basedemo;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;

import org.xutils.x;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static int curPageIndex;
    private static MyApplication gApp;
    private String curCity;
    private String curCityId;
    private static boolean existCityData;    // true，已经存在城市数据了
    private static boolean isPopChangeCityDialog;    // true,已经弹出过城市切换的对话框；false，还未弹出过

    /** true,已经弹出过检测更新的弹窗 */
    public static boolean isCheckUpdate;

    @Override
    public void onCreate() {
        super.onCreate();

        // 禁止默认的页面统计方式，这样将不会再自动统计Activity
//        MobclickAgent.openActivityDurationTrack(false);
        /** 设置是否对日志信息进行加密, 默认false(不加密). */
//		AnalyticsConfig.enableEncrypt(true);
//        MobclickAgent.setDebugMode(true);

        gApp = (MyApplication) getApplicationContext();
        // 初始化Xutils
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

//        enabledStrictMode();
        LeakCanary.install(this);

        curPageIndex = 0;
        existCityData = false;
//        initShare();
        // 每次启动就从后台获取城市数据
//        NetManager.getCiytInfos(MyApplication.this);
        //  创建版本数据库，检测版本，进行下载
//        NetManager.getVersion(this);
    }

    /**
     * 初始化分享配置
     */
    private void initShare() {
        //QQ和Qzone appid appkey
//        PlatformConfig.setQQZone("1105331059", "ThNnmbV2m70PIifO");
        //微信 appid appsecret
//        PlatformConfig.setWeixin("wxb45b0d2c84b74c97", "6455fbec44ceaaf1bc9a02d0c8bf371d");
        //新浪微博 appkey appsecret
//        PlatformConfig.setSinaWeibo("3777808488", "effebf0fd5f36e89bb5442e896275acc");
    }


    public static MyApplication getGlobalContext() {
        return gApp;
    }

    /**
     * 通过判断cookie是否有值来判断是否有登录（注销需重置）
     *
     * @return
     */
//    public static boolean isLogin() {
//        User user = UserUtil.getInstance().getUser();
//        if (user == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }

//    public String getCurCity() {
//        if (TextUtils.isEmpty(curCity)) {
//            curCity = SPUtil.getString(this, Constant.CURRENT_CITY);
//        }
//        return curCity;
//    }
//
//    public void setCurCity(String curCity) {
//        this.curCity = curCity;
//        SPUtil.putString(getApplicationContext(), Constant.CURRENT_CITY,
//                curCity);
//    }

//    public String getCurCityId() {
//        if (TextUtils.isEmpty(curCityId)) {
//            curCityId = SPUtil.getString(this, Constant.CURRENT_CITY_ID);
//        }
//        return curCityId;
//    }

//    public void setCurCityId(String curCityId) {
//        this.curCityId = curCityId;
//        SPUtil.putString(getApplicationContext(), Constant.CURRENT_CITY_ID,
//                curCityId == null ? "" : curCityId);
//    }

//    public void setCurCity(String curCity, String curCityId) {
//        setCurCity(curCity);
//        setCurCityId(curCityId);
//    }

    public static int getCurPageIndex() {
        return curPageIndex;
    }

    public static void setCurPageIndex(int curPageIndex) {
        MyApplication.curPageIndex = curPageIndex;
    }

    public static boolean isExistCityData() {
        return existCityData;
    }

    public static void setExistCityData(boolean existCityData) {
        MyApplication.existCityData = existCityData;
    }

    public static boolean isPopChangeCityDialog() {
        return isPopChangeCityDialog;
    }

    public static void setPopChangeCityDialog(boolean isPopChangeCityDialog) {
        MyApplication.isPopChangeCityDialog = isPopChangeCityDialog;
    }

    /**
     * 用来重置一些程序退出时需要重置的标志（因为有可能出现程序退出，但Application并没有结束的情况）
     */
    public static void resetFlag() {
        setCurPageIndex(0);
        setExistCityData(false);
        isPopChangeCityDialog = false;
        isCheckUpdate = false;
    }

    /**
     * 打印出MyApplication里的一些标志位
     */
    public static void printFlags() {
//        LogUtils.sysout(TAG + ": existCityData ---> " + existCityData);
//        LogUtils.sysout(TAG + ": isPopChangeCityDialog ---> " + isPopChangeCityDialog);
//        LogUtils.sysout(TAG + ": isCheckUpdate ---> " + isCheckUpdate);
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
}
