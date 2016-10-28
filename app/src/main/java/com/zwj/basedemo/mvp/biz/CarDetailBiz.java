package com.zwj.basedemo.mvp.biz;


import com.zwj.basedemo.mvp.view.ICarDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwj on 2016/8/12.
 */
public class CarDetailBiz {

    /**
     * 获取车辆图片，图片最多不超过5张
     * @param count 限制的图片张数
     */
    public static List<String> getCarPicListSizeLimit(List<String> imageList, int count) {
        List<String> carPicList = new ArrayList<String>();
        for (int i = 0; i < imageList.size(); i++) {
            if (i < count) {
                carPicList.add(imageList.get(i));
            } else {
                break;
            }
        }
        return carPicList;
    }

    /**
     * 设置当前页数的文本
     *
     * @param position  当前图片下标
     */
    public static void setPageText(ICarDetailView carDetailView, List<String> imageList, int position) {
        if(carDetailView != null) {
            carDetailView.setPageText(getPageText(imageList, position));
        }
    }

    public static String getPageText(List<String> imageList, int position) {
        StringBuilder indicator = new StringBuilder();
        if (position == 0) {
            indicator.append("1");
        } else {
            indicator.append(position % imageList.size() + 1);
        }
        indicator.append("/").append(imageList.size());
        return indicator.toString();
    }
}
