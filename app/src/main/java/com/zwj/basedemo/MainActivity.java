package com.zwj.basedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.base.BaseAutoLayoutCommonActivity;
import com.zwj.basedemo.util.AppManager;
import com.zwj.basedemo.util.ToastUtil;
import com.zwj.basedemo.view.adapter.MyFragmentPagerAdapter;
import com.zwj.basedemo.view.fragment.BuyCarFragment;
import com.zwj.basedemo.view.fragment.CFragment;
import com.zwj.basedemo.view.fragment.HomeFragment;
import com.zwj.basedemo.view.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseAutoLayoutCommonActivity implements
        View.OnClickListener {
    private static final int PAGE_HOME_INDEX = 0;
    private static final int PAGE_BUY_CAR_INDEX = 1;
    private static final int PAGE_SELL_CAR_INDEX = 2;
    private static final int PAGE_ME_INDEX = 3;

    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private HomeFragment homeFragment;
    private BuyCarFragment buyCarFragment;
    private CFragment cFragment;
    private MeFragment meFragment;

    private List<LinearLayout> bottomMenuItems;
    private List<ImageView> bottomMenuImageViews;
    private List<TextView> bottomMenuTextViews;
    private int curPageIndex;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        mViewPager = getView(R.id.viewpager);

        bottomMenuItems = new ArrayList<>();
        bottomMenuItems.add(getView(R.id.ll_home));
        bottomMenuItems.add(getView(R.id.ll_buycar));
        bottomMenuItems.add(getView(R.id.ll_sell_car));
        bottomMenuItems.add(getView(R.id.ll_me));

        bottomMenuImageViews = new ArrayList<>();
        bottomMenuImageViews.add(getView(R.id.iv_home));
        bottomMenuImageViews.add(getView(R.id.iv_buycar));
        bottomMenuImageViews.add(getView(R.id.iv_sell_car));
        bottomMenuImageViews.add(getView(R.id.iv_me));

        bottomMenuTextViews = new ArrayList<>();
        bottomMenuTextViews.add(getView(R.id.tv_home));
        bottomMenuTextViews.add(getView(R.id.tv_buycar));
        bottomMenuTextViews.add(getView(R.id.tv_sell_car));
        bottomMenuTextViews.add(getView(R.id.tv_me));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        homeFragment = new HomeFragment();
        buyCarFragment = new BuyCarFragment();
        cFragment = new CFragment();
        meFragment = new MeFragment();
        fragments = new ArrayList<Fragment>();
        fragments.add(homeFragment);
        fragments.add(buyCarFragment);
        fragments.add(cFragment);
        fragments.add(meFragment);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager
                .setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));

        changePage(PAGE_HOME_INDEX);
    }

    @Override
    protected void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                changePage(position, false);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        for (int i = 0; i < bottomMenuItems.size(); i++) {
            bottomMenuItems.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                changePage(PAGE_HOME_INDEX);
                break;

            case R.id.ll_buycar:
                changePage(PAGE_BUY_CAR_INDEX);
                break;

            case R.id.ll_sell_car:
                changePage(PAGE_SELL_CAR_INDEX);
                break;

            case R.id.ll_me:
                changePage(PAGE_ME_INDEX);
                break;
        }
    }

    /**
     * @param index
     * @param isChangePage true,页面也跟着改变；false，只改变底部导航栏图标
     */
    public void changePage(int index, boolean isChangePage) {
        for (int i = 0; i < bottomMenuImageViews.size(); i++) {
            ImageView iv = bottomMenuImageViews.get(i);
            TextView tv = bottomMenuTextViews.get(i);
            if (index == i) {
                if (!iv.isSelected()) {
                    curPageIndex = index;
                    if (isChangePage) {
                        mViewPager.setCurrentItem(index, false);
                    }
                    iv.setSelected(true);
                    tv.setSelected(true);

                    MyApplication.setCurPageIndex(curPageIndex);
                }
            } else {
                if (iv.isSelected()) {
                    iv.setSelected(false);
                    tv.setSelected(false);
                }
            }
        }
    }

    public void changePage(int index) {
        changePage(index, true);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis();
                ToastUtil.toast("再按一次退出！");
            } else {
                MyApplication.resetFlag();
                AppManager.getAppManager().AppExit(mContext);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
