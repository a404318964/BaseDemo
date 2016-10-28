package com.zwj.basedemo.bean;

/**
 * Created by zwj on 2016/8/24.
 * 进口车车源接口参数实体类
 */
public class SearchCarParamBean {
    private ParamBean pageNoParamBean = new ParamBean();
    private ParamBean brandParamBean = new ParamBean(); // 品牌
    private ParamBean styleParamBean = new ParamBean(); // 车型
    private ParamBean countryParamBean = new ParamBean(); // 国别
    private ParamBean carFlagParamBean = new ParamBean(); // 状态
    private ParamBean onlineFlagParamBean = new ParamBean();    // 上架
    private ParamBean searchTextParamBean = new ParamBean();     // 搜索框内容
    private ParamBean unitCodeParamBean = new ParamBean();    // 提车地编号
    private ParamBean carCountStartParamBean = new ParamBean();    // 数量范围起始
    private ParamBean carCountEndParamBean = new ParamBean();    // 数量范围截止
    private ParamBean priceSectionParamBean = new ParamBean();     // 热门
    private ParamBean priceStartParamBean = new ParamBean();    // 价格范围起始
    private ParamBean priceEndParamBean = new ParamBean();     // 价格范围截止
    private ParamBean agioCarParamBean = new ParamBean();    // 优惠
    private ParamBean hotCarParamBean = new ParamBean();     // 热门
    private ParamBean sortParamBean = new ParamBean();     // 排序

    public ParamBean getPageNoParamBean() {
        return pageNoParamBean;
    }

    public ParamBean getStyleParamBean() {
        return styleParamBean;
    }

    public ParamBean getCountryParamBean() {
        return countryParamBean;
    }

    public ParamBean getCarFlagParamBean() {
        return carFlagParamBean;
    }

    public ParamBean getOnlineFlagParamBean() {
        return onlineFlagParamBean;
    }

    public ParamBean getSearchTextParamBean() {
        return searchTextParamBean;
    }

    public ParamBean getUnitCodeParamBean() {
        return unitCodeParamBean;
    }

    public ParamBean getCarCountStartParamBean() {
        return carCountStartParamBean;
    }

    public ParamBean getCarCountEndParamBean() {
        return carCountEndParamBean;
    }

    public ParamBean getPriceStartParamBean() {
        return priceStartParamBean;
    }

    public ParamBean getPriceEndParamBean() {
        return priceEndParamBean;
    }

    public ParamBean getBrandParamBean() {
        return brandParamBean;
    }

    public ParamBean getHotCarParamBean() {
        return hotCarParamBean;
    }

    public ParamBean getAgioCarParamBean() {
        return agioCarParamBean;
    }

    public ParamBean getPriceSectionParamBean() {
        return priceSectionParamBean;
    }

    public ParamBean getSortParamBean() {
        return sortParamBean;
    }

    /**
     * 重置所有的参数内容
     * @return
     */
    public SearchCarParamBean clear() {
        pageNoParamBean.setValue("1");
        brandParamBean.reset();
        styleParamBean.reset();
        countryParamBean.reset();
        carFlagParamBean.reset();
        onlineFlagParamBean.reset();
        searchTextParamBean.reset();
        unitCodeParamBean.reset();
        carCountStartParamBean.reset();
        carCountEndParamBean.reset();
        priceStartParamBean .reset();
        priceEndParamBean.reset();
        hotCarParamBean.reset();
        agioCarParamBean.reset();
        priceSectionParamBean.reset();
        sortParamBean.reset();
        return this;
    }
}
