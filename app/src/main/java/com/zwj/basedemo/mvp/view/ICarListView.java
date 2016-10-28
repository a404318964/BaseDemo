package com.zwj.basedemo.mvp.view;


import com.zwj.basedemo.bean.ParamBean;

import java.util.List;

/**
 * Created by zwj on 2016/8/11.
 */
public interface ICarListView extends ILoadMoreView {
    void refreshUI();
    void finishSwipeRefreshLayout();
    void showNoListLayout(boolean isShow);
    void showErrorLayout(boolean isShow);
    void showLoadingLayout(boolean isShow);
    //    void reloadCarData();
    void hideFilterView();
    void setCurrentSelectCondition(List<ParamBean> selectParamList);
}
