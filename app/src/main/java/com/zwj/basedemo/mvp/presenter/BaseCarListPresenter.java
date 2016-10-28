package com.zwj.basedemo.mvp.presenter;

import android.content.Intent;

import com.zwj.basedemo.bean.ParamBean;
import com.zwj.basedemo.mvp.view.ICarListView;
import com.zwj.basedemo.view.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwj on 2016/8/11.
 */
public abstract class BaseCarListPresenter<T extends ICarListView> extends BasePresenter<T> {
    protected List<ParamBean> selectParamList;    // 筛选条件集合
    protected int pageNo = 1;
    protected int count;
    protected boolean isPullDownRefreshing;   // true,正在下拉刷新

    public BaseCarListPresenter(T view) {
        super(view);
        selectParamList = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void searchCar() {
        if (isPullDownRefreshing || mView.getLoadMoreStatus() == LoadMoreWrapper.LoadStatus.LOADING) {
            mView.showLoadingLayout(false);
        } else {
            mView.showLoadingLayout(true);
        }
        searchCarData();
        setCurrentSelectCondition();
    }

    /**
     * 搜索车辆，不用去判断是否显示加载界面以及之后的筛选条件设置等
     */
    protected abstract void searchCarData();

    private void setCurrentSelectCondition() {
        selectParamList.clear();
        setSelectParams();
        mView.setCurrentSelectCondition(selectParamList);
    }

    /**
     * 设置选中的筛选条件(集合已清空，无需子类再次清空)
     */
    public abstract void setSelectParams();

    /**
     * 添加参数实体类到选中的参数列表里
     *
     * @param paramBean
     */
    public void addToSelectParamList(ParamBean paramBean) {
        if (paramBean.isSelected()) {
            selectParamList.add(paramBean);
        }
    }

    public void loadMore() {
        pageNo++;
        searchCar();
    }

    protected void onDataEmpty() {
        mView.showNoListLayout(true);
        clearCarList();
        mView.refreshUI();
        mView.onEmpty();
    }

    /**
     * 清空车源数据集合
     */
    protected abstract void clearCarList();

    public void refresh() {
        isPullDownRefreshing = true;
        reloadData();
    }

    public void reloadData() {
        pageNo = 1;
        searchCar();
    }

    public void reset() {
        searchCarParamReset();
        reloadData();
    }

    protected abstract void searchCarParamReset();

    /**
     * 处理从其他界面回来的数据
     */
    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public List<ParamBean> getSelectParamList() {
        return selectParamList;
    }

}
