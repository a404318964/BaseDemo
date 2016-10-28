package com.zwj.basedemo.mvp.presenter.inter;

import java.util.List;

/**
 * Created by zwj on 2016/8/12.
 */
public interface ICarDetailPresenter {
    /**
     * 设置当前页数的文本
     *
     * @param position  当前图片下标
     */
    void setPageText(int position);

    /**
     * 获取车辆详情信息(进口车、新车、二手车)
     * @param carNo
     */
    void getCarDetailInfo(String carNo);

    /**
     * 获取车辆图片，限制图片的显示张数
     * @return
     */
    List<String> getCarPicListSizeLimit();
}
