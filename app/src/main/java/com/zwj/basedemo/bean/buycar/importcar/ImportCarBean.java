package com.zwj.basedemo.bean.buycar.importcar;


import com.zwj.basedemo.constant.UrlConstant;
import com.zwj.basedemo.util.CommonUtil;

import java.util.List;

/**
 * Created by zwj on 2016/8/11.
 * 平行进口车实体类
 */
public class ImportCarBean {
    private String carNo;
    private double price;           // 售价
    private String priceID;     // 价位ID
    private double priceMarket;    // 市场价
    private double priceAgioMax;   // 最大优惠价
    private int carCount;       // 数量
    private String fullName;    // 完整车名
    private String brandID;     // 品牌ID
    private String styleID;     // 车型
    private String seriesID;    // 车系ID
    private int colorOutID;  // 外观颜色ID
    private String colorOutMemo;    // 外观颜色说明
    private int colorInID;       // 内饰颜色ID
    private String colorInMemo;     // 内饰颜色说明
    private String countryID;   // 产地/国家ID
    private String country;     // 产地国家
    private String readme;      // 配置说明
    private boolean hotCar;     // 热门车型
    private boolean agioCar;    // 特惠车型
    private String coverImgURL; // 封面图片
    private String carFlag;     // 状态   1,2
    private String status;      // 状态   现车等
    private String futuresTime; // 期货时间
    private int onlineFlag;     // 1上架/0下架
    private String publishTime;     // 发布时间
    private String updateTime;      // 最后编辑时间
    private boolean isDeleted;      // 信息是否删除
    private List<CarOffer> carOffers;   // 提车地报价信息
    private List<CarPicBean> carPics;

    private String carNoLabel;  // 格式化后的车源号


    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
        StringBuilder sbCarNo = new StringBuilder();
        carNoLabel = sbCarNo.append("车源号: ").append(CommonUtil.formatCarNo(carNo)).toString();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(double priceMarket) {
        this.priceMarket = priceMarket;
    }

    public double getPriceAgioMax() {
        return priceAgioMax;
    }

    public void setPriceAgioMax(double priceAgioMax) {
        this.priceAgioMax = priceAgioMax;
    }

    public String getPriceID() {
        return priceID;
    }

    public void setPriceID(String priceID) {
        this.priceID = priceID;
    }

    public void setPriceAgioMax(int priceAgioMax) {
        this.priceAgioMax = priceAgioMax;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public int getColorOutID() {
        return colorOutID;
    }

    public void setColorOutID(int colorOutID) {
        this.colorOutID = colorOutID;
    }

    public String getColorOutMemo() {
        return colorOutMemo;
    }

    public void setColorOutMemo(String colorOutMemo) {
        this.colorOutMemo = colorOutMemo;
    }

    public int getColorInID() {
        return colorInID;
    }

    public void setColorInID(int colorInID) {
        this.colorInID = colorInID;
    }

    public String getColorInMemo() {
        return colorInMemo;
    }

    public void setColorInMemo(String colorInMemo) {
        this.colorInMemo = colorInMemo;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public boolean isHotCar() {
        return hotCar;
    }

    public void setHotCar(boolean hotCar) {
        this.hotCar = hotCar;
    }

    public boolean isAgioCar() {
        return agioCar;
    }

    public void setAgioCar(boolean agioCar) {
        this.agioCar = agioCar;
    }

    public String getCoverImgURL() {
        return coverImgURL;
    }

    public void setCoverImgURL(String coverImgURL) {
        this.coverImgURL = UrlConstant.IMPORTCAR_BASE_URL + coverImgURL;
    }

    public String getCarFlag() {
        return carFlag;
    }

    public void setCarFlag(String carFlag) {
        this.carFlag = carFlag;
    }

    public String getFuturesTime() {
        return futuresTime;
    }

    public void setFuturesTime(String futuresTime) {
        this.futuresTime = futuresTime;
    }

    public int getOnlineFlag() {
        return onlineFlag;
    }

    public void setOnlineFlag(int onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CarOffer> getCarOffers() {
        return carOffers;
    }

    public void setCarOffers(List<CarOffer> carOffers) {
        this.carOffers = carOffers;
    }

    public String getStyleID() {
        return styleID;
    }

    public void setStyleID(String styleID) {
        this.styleID = styleID;
    }

    public List<CarPicBean> getCarPics() {
        return carPics;
    }

    public void setCarPics(List<CarPicBean> carPics) {
        this.carPics = carPics;
    }

    public String getCarNoLabel() {
        return carNoLabel;
    }
}
