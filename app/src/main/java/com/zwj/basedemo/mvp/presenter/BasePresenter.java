package com.zwj.basedemo.mvp.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import com.zwj.basedemo.MyApplication;
import com.zwj.basedemo.mvp.view.BaseView;


public abstract class BasePresenter<T extends BaseView> {
    protected String TAG = getClass().getSimpleName();
    protected T mView;
    protected Context mContext;

    public BasePresenter() {}

    public BasePresenter(T view, Context context) {
        mView = view;
        mContext = context;
    }

    public BasePresenter(T view) {
        mView = view;
        if(view instanceof Activity) {
            mContext = (Context) view;
        }else if(view instanceof Fragment) {
            Fragment fragment = (Fragment) view;
            mContext = fragment.getActivity();
        }else {
            mContext = MyApplication.getGlobalContext();
        }
    }

    public void onBind(T view, Context pContext) {
        mView = view;
        mContext = pContext;
    }

    public void onUnbind() {
//        NetManager.cacelRquests(TAG);
        mView = null;
        mContext = null;
    }
}
