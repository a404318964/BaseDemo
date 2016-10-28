package com.zwj.basedemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/***
 * 用于传递对象到相册的基类
 * 
 * @author chenlz
 *
 */
public class BasePicBean implements Parcelable {
	/**
	 * 图片路径(本地路径)
	 */
	protected String path;

	/**
	 * 网络图片路径
	 */
	protected String url;


	
	public BasePicBean() {
		
	}
	
	public BasePicBean(String path) {
		this.path = path;
	}



	public String getPath() {
		return path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.path);
		dest.writeString(this.url);
	}

	protected BasePicBean(Parcel in) {
		this.path = in.readString();
		this.url = in.readString();
	}

	public static final Creator<BasePicBean> CREATOR = new Creator<BasePicBean>() {
		@Override
		public BasePicBean createFromParcel(Parcel source) {return new BasePicBean(source);}

		@Override
		public BasePicBean[] newArray(int size) {return new BasePicBean[size];}
	};
}
