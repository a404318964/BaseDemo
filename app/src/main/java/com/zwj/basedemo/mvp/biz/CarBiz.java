package com.zwj.basedemo.mvp.biz;

import android.content.Context;
import android.text.TextUtils;

import com.zwj.basedemo.MyApplication;
import com.zwj.basedemo.bean.SearchCarParamBean;
import com.zwj.basedemo.constant.Constant;
import com.zwj.basedemo.constant.UrlConstant;
import com.zwj.basedemo.util.net.NetManager;
import com.zwj.basedemo.util.net.bean.RequestBean;

import org.xutils.common.Callback;

/**
 * Created by zwj on 2016/6/28.
 */
public class CarBiz {

//    private static CarBiz instance;
//
//    private CarBiz() {
//    }
//
//    public static CarBiz getInstance() {
//        if (instance == null) {
//            synchronized (CarBiz.class) {
//                if (instance == null) {
//                    instance = new CarBiz();
//                }
//            }
//        }
//        return instance;
//    }

    /**
     * 获取车辆
     *
     * @param buyCarParamBean
     * @param callBack
     * @return
     */
//    public static Callback.Cancelable searchCar(BuyCarParamBean buyCarParamBean, NetManager.RequestCallBack callBack) {
//        return new RequestBean(UrlConstant.POST_SEARCH_CAR, RequestBean.METHOD_GET)
//                .addParam(Constant.PARAM_PAGE_NO, buyCarParamBean.getPageNo().getValue())        // 第几页
//                .addParam(Constant.PARAM_PAGE_NUMBER, Constant.PAGE_NUMBER + "")    // 每页显示的车辆数目
//                .addParam(Constant.PARAM_PRICE, buyCarParamBean.getPriceParamBean().getValue())
//                .addParam(Constant.PARAM_BRAND, buyCarParamBean.getBrandParamBean().getValue())
//                .addParam(Constant.PARAM_SERIES, buyCarParamBean.getSeriesParamBean().getValue())
//                .addParam(Constant.PARAM_STYLE, buyCarParamBean.getStyleParamBean().getValue())        // 车型id
//                .addParam(Constant.PARAM_SORT, buyCarParamBean.getSortParamBean().getValue())
//                .addParam(Constant.PARAM_CAR_YEAR, buyCarParamBean.getCarYearParamBean().getValue())
//                .addParam(Constant.PARAM_MILEAGE, buyCarParamBean.getMileageParamBean().getValue())
//                .addParam(Constant.PARAM_GEAR_BOX, buyCarParamBean.getGearboxParamBean().getValue())
//                .addParam(Constant.PARAM_OUTPUT_VOLUME, buyCarParamBean.getOutputVolumeParamBean().getValue())
//                .addParam(Constant.PARAM_DISCHARGE_STANDARD, buyCarParamBean.getDischargeStandardParamBean().getValue())
//                .addParam(Constant.PARAM_COUNTRY, buyCarParamBean.getCountryParamBean().getValue())
//                .addParam(Constant.PARAM_COLOR, buyCarParamBean.getColorParamBean().getValue())
//                .addParam(Constant.PARAM_CAR_NO, buyCarParamBean.getCarNoParamBean().getValue())
//                .addParam(Constant.PARAM_IS_URGENT, buyCarParamBean.getIsUrgentParamBean().getValue())
//                .addParam(Constant.PARAM_QUASI_NEW_CAR, buyCarParamBean.getQuasiNewCarParamBean().getValue())
//                .addParam(Constant.PARAM_SEVEN_SEAT, buyCarParamBean.getSevenSeatParamBean().getValue())
//                .addParam(Constant.PARAM_CITY_ID, buyCarParamBean.getCityParamBean().getValue())
//                .addParam("TestReportCode", buyCarParamBean.getTestReportCodeParamBean().getValue())
//                .addParam("IsMobile", "1")
//                .setTag(buyCarParamBean.getTag())
//                .setNeedParse(false)
//                .setCallback(callBack).request(MyApplication.getGlobalContext());
//    }


    /**
     * 获取进口车辆
     *
     * @param callBack
     * @return
     */
    public static Callback.Cancelable searchImportCar(SearchCarParamBean searchCarParamBean, NetManager.RequestCallBack callBack) {
        RequestBean requestBean = new RequestBean(UrlConstant.POST_IMPORTCAR_SEARCH_CAR, RequestBean.METHOD_POST)
                .addParam(Constant.PARAM_PAGE_NO, searchCarParamBean.getPageNoParamBean().getValue())        // 第几页
                .addParam("BrandID", searchCarParamBean.getBrandParamBean().getValue())
                .addParam("CountryID", searchCarParamBean.getCountryParamBean().getValue())
                .addParam("StyleID", searchCarParamBean.getStyleParamBean().getValue())        // 车型id
                .addParam(Constant.PARAM_CAR_FLAG, searchCarParamBean.getCarFlagParamBean().getValue())       // 状态
//                .addParam("OnlineFlag", searchCarParamBean.getOnlineFlagParamBean().getValue())
                .addParam("SearchText", searchCarParamBean.getSearchTextParamBean().getValue())
                .addParam("UnitCode", searchCarParamBean.getUnitCodeParamBean().getValue())
                .addParam("CarCount_Start", searchCarParamBean.getCarCountStartParamBean().getValue())
                .addParam("CarCount_End", searchCarParamBean.getCarCountEndParamBean().getValue())
                .addParam(Constant.PARAM_AGIOCAR, searchCarParamBean.getAgioCarParamBean().getValue())
                .addParam(Constant.PARAM_HOTCAR, searchCarParamBean.getHotCarParamBean().getValue())
                .addParam("SortType", searchCarParamBean.getSortParamBean().getValue())
                .setNeedParse(false)
                .setNeedCookies(false)
                .setCallback(callBack);

        if (!TextUtils.isEmpty(searchCarParamBean.getPriceStartParamBean().getValue())) {
            requestBean.addParam(Constant.PARAM_PRICE_START, searchCarParamBean.getPriceStartParamBean().getValue());

            if (!TextUtils.isEmpty(searchCarParamBean.getPriceEndParamBean().getValue())) {
                requestBean.addParam(Constant.PARAM_PRICE_END, searchCarParamBean.getPriceEndParamBean().getValue());
            }
        }


        return requestBean.request(MyApplication.getGlobalContext());
    }

    /**
     * 获取进口车详情信息
     *
     * @param requestTag 请求标签
     */
    public static Callback.Cancelable getImportCarDetailBean(Context context, String carNo, String requestTag, NetManager.RequestCallBack callBack) {
        if (TextUtils.isEmpty(carNo)) {
            return null;
        }

        return new RequestBean(UrlConstant.GET_IMPORT_CAR_DETAIL, RequestBean.METHOD_GET)
                .addParam(Constant.PARAM_CAR_NO, carNo)
                .addParam("IsAPP", "true")
                .setShowLoading(true)
                .setTag(requestTag)
                .setCallback(callBack).request(context);
    }
}
