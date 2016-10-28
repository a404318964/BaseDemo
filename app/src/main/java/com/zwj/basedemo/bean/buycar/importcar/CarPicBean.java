package com.zwj.basedemo.bean.buycar.importcar;

import android.os.Parcel;
import android.os.Parcelable;

import com.zwj.basedemo.bean.BasePicBean;
import com.zwj.basedemo.constant.UrlConstant;
import com.zwj.basedemo.util.CommonUtil;


/**
 * Created by zwj on 2016/8/26.
 */
public class CarPicBean extends BasePicBean implements Parcelable {
    private String carPicID;
    private String carNo;
    private String serialNO;
    private String picAddr;

    private String smallPicUrl;
    private String bigPicUrl;

    public String getCarPicID() {
        return carPicID;
    }

    public void setCarPicID(String carPicID) {
        this.carPicID = carPicID;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(String serialNO) {
        this.serialNO = serialNO;
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = UrlConstant.IMPORTCAR_BASE_URL + picAddr;

        String[] picUrls = CommonUtil.formatPicUrl(this.picAddr);
        this.smallPicUrl = picUrls[0];
        this.bigPicUrl = picUrls[1];

        url = picUrls[1];
    }

    public String getSmallPicUrl() {
        return smallPicUrl;
    }

    public void setSmallPicUrl(String smallPicUrl) {
        this.smallPicUrl = smallPicUrl;
    }

    public String getBigPicUrl() {
        return bigPicUrl;
    }

    public void setBigPicUrl(String bigPicUrl) {
        this.bigPicUrl = bigPicUrl;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.carPicID);
        dest.writeString(this.carNo);
        dest.writeString(this.serialNO);
        dest.writeString(this.picAddr);
        dest.writeString(this.smallPicUrl);
        dest.writeString(this.bigPicUrl);
    }

    public CarPicBean() {}

    protected CarPicBean(Parcel in) {
        super(in);
        this.carPicID = in.readString();
        this.carNo = in.readString();
        this.serialNO = in.readString();
        this.picAddr = in.readString();
        this.smallPicUrl = in.readString();
        this.bigPicUrl = in.readString();
    }

    public static final Creator<CarPicBean> CREATOR = new Creator<CarPicBean>() {
        @Override
        public CarPicBean createFromParcel(Parcel source) {return new CarPicBean(source);}

        @Override
        public CarPicBean[] newArray(int size) {return new CarPicBean[size];}
    };
}
