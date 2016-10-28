package com.zwj.basedemo.bean.buycar.importcar;

/**
 * Created by zwj on 2016/8/24.
 * 提车地报价信息
 */
public class CarOffer {
    private String offerID;     // ID
    private String carNo;       // 车源编号
    private String unitCode;    // 提车地编号
    private String cityName;    // 所在城市
    private String city;        // 同cityname
    private String province;    // 省份
    private String cityNameEN;  // 城市拼音缩写
    private double price;       // 售价
    private double priceAgioMax;    // 优惠了多少

    public String getOfferID() {
        return offerID;
    }

    public void setOfferID(String offerID) {
        this.offerID = offerID;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEN() {
        return cityNameEN;
    }

    public void setCityNameEN(String cityNameEN) {
        this.cityNameEN = cityNameEN;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceAgioMax() {
        return priceAgioMax;
    }

    public void setPriceAgioMax(double priceAgioMax) {
        this.priceAgioMax = priceAgioMax;
    }
}
