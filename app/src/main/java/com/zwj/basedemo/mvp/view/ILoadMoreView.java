package com.zwj.basedemo.mvp.view;


import com.zwj.basedemo.view.adapter.recyclerview.wrapper.LoadMoreWrapper;

/**
 * Created by zwj on 2016/7/20.
 */
public interface ILoadMoreView extends BaseView{
    /**
     * 设置数据全部加载完成(后台没有数据了)
     */
    void setLoadAll();

    /**
     * 重置加载更多的状态
     */
    void resetLoadMoreStatus();

    void onLoadMoreFinish();

    /**
     * 获取当前加载更多的状态
     */
    LoadMoreWrapper.LoadStatus getLoadMoreStatus();

    /**
     * 加载的数据为空时
     */
    void onEmpty();
}
