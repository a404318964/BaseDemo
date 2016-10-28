package com.zwj.basedemo.view.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zwj.basedemo.util.LogUtils;

public abstract class BaseFragment extends Fragment implements OnClickListener {
    public String TAG = getClass().getSimpleName();
    protected Context mContext;
    public View mRootView;
    public boolean isInited;    // 是否为第一次创建fragment界面,true已经初始化过界面，false还未初始化过界面

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        receiveArguments(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            if (getContentViewId() != 0) {
                mRootView = inflater.inflate(getContentViewId(), container,
                        false);
                initView();

                LogUtils.d(TAG, TAG + " mRootView is null");
            }
        }

        LogUtils.d(TAG, TAG + " onCreateView");
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        setListener();
        LogUtils.d(TAG, TAG + " onActivityCreated");
        isInited = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, TAG + " onResume");
//        MobclickAgent.onPageStart(TAG); //统计页面
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, TAG + " onPause");
//        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void onDestroyView() {
        LogUtils.d(TAG, TAG + " onDestroyView");
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            parent.removeView(mRootView);
        }
        super.onDestroyView();
    }

    public View getRootView() {
        return mRootView;
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 如果没有布局，那么就返回0
     *
     * @return fragment的布局文件
     */
    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void setListener();

    /**
     * 利用setArguments传递参数在这里进行获取
     */
    protected void receiveArguments(Bundle savedInstanceState) {
    }

    /**
     * 通过泛型来简化findViewById
     */
    protected final <E extends View> E getView(int id) {
        try {
            return (E) mRootView.findViewById(id);
        } catch (ClassCastException ex) {
            LogUtils.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

    /**
     * 设置背景透明度
     *
     * @param aplha
     */
    protected void setBgAlpha(float aplha) {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = aplha;
        getActivity().getWindow().setAttributes(lp);
    }

}
