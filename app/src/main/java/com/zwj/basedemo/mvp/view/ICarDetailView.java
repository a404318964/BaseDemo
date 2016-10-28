package com.zwj.basedemo.mvp.view;

import java.util.List;

/**
 * Created by zwj on 2016/8/12.
 */
public interface ICarDetailView extends BaseView {
    /**
     * 设置当前页数的文本
     */
    void setPageText(String indicator);

    /**
     * 设置头部滚动的图片数据
     */
    void setImageUris(List<String> imageList);

    /**
     * 把车辆信息填充到界面上
     */
    void fillCarData();
}
