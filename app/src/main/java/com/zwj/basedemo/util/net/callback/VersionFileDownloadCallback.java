package com.zwj.basedemo.util.net.callback;


import com.zwj.basedemo.bean.VersionBean;

import java.io.File;

public class VersionFileDownloadCallback extends DownloadCallback {
    private static final String TAG = "DownloadCallback";
    private VersionBean versionBean;   // 从服务端下载需要更新的版本文件时需用到

    /**
     * 当下载成功后调用
     */
    public interface OnVersionFileDownloadSuccessListener {
        void onSuccess(File result, VersionBean versionBean);
    }

    private OnVersionFileDownloadSuccessListener mOnVersionFileDownloadSuccessListener;

    public VersionFileDownloadCallback() {
    }

    public VersionFileDownloadCallback(VersionBean versionBean) {
        this.versionBean = versionBean;
    }


    @Override
    public void onSuccess(File result) {
        if(mOnVersionFileDownloadSuccessListener != null) {
            mOnVersionFileDownloadSuccessListener.onSuccess(result, versionBean);
        }
    }

    public OnVersionFileDownloadSuccessListener getmOnVersionFileDownloadSuccessListener() {
        return mOnVersionFileDownloadSuccessListener;
    }

    public VersionFileDownloadCallback setmOnVersionFileDownloadSuccessListener(OnVersionFileDownloadSuccessListener mOnVersionFileDownloadSuccessListener) {
        this.mOnVersionFileDownloadSuccessListener = mOnVersionFileDownloadSuccessListener;
        return this;
    }
}
