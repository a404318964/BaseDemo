package com.zwj.basedemo.mvp.presenter;


import com.zwj.basedemo.bean.buycar.importcar.CarPicBean;
import com.zwj.basedemo.bean.buycar.importcar.ImportCarBean;
import com.zwj.basedemo.mvp.biz.CarBiz;
import com.zwj.basedemo.mvp.biz.CarDetailBiz;
import com.zwj.basedemo.mvp.presenter.inter.ICarDetailPresenter;
import com.zwj.basedemo.mvp.view.IImportCarDetailView;
import com.zwj.basedemo.util.JsonUtil;
import com.zwj.basedemo.util.net.bean.ResponseBean;
import com.zwj.basedemo.util.net.callback.SimpleCallBack;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwj on 2016/8/12.
 */
public class ImportCarDetailPresenter extends BasePresenter<IImportCarDetailView> implements ICarDetailPresenter {
    private String carNo;
    private List<String> imageList;     // 用来展示滚动的图片
    private ImportCarBean importCarBean;


    public ImportCarDetailPresenter(IImportCarDetailView importCarDetailView) {
        super(importCarDetailView);
        imageList = new ArrayList<>();
    }

    @Override
    public void getCarDetailInfo(String carNo) {

        Callback.Cancelable cancelable = CarBiz.getImportCarDetailBean(mContext, carNo, TAG, new SimpleCallBack() {

            @Override
            public void onSuccess(ResponseBean responseBean) {
                if (mView != null) {
                    importCarBean = JsonUtil.getObject(responseBean.getResult(), ImportCarBean.class);
                    fillData();
                }
            }
        });

        if(cancelable != null) {
            this.carNo = carNo;
        }
    }

    /**
     * 将数据填充到界面上
     */
    public void fillData() {
        if(importCarBean != null) {
            setImageUris();
            mView.fillCarData();
        }
    }

    private void setImageUris() {
        for(int i=0; i<importCarBean.getCarPics().size(); i++) {
            imageList.add(importCarBean.getCarPics().get(i).getBigPicUrl());
        }
        mView.setImageUris(imageList);
    }

    public String getCarNo() {
        return importCarBean.getCarNo();
    }

    public List<String> getImageList() {
        return imageList;
    }

    public List<CarPicBean> getCarPicList() {
        return importCarBean.getCarPics();
    }

    @Override
    public List<String> getCarPicListSizeLimit() {
        return CarDetailBiz.getCarPicListSizeLimit(imageList, 5);
    }

    @Override
    public void setPageText(int position) {
        CarDetailBiz.setPageText(mView, imageList, position);
    }

    public ImportCarBean getImportCarBean() {
        return importCarBean;
    }
}
