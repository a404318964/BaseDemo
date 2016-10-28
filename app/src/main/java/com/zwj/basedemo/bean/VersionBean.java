package com.zwj.basedemo.bean;

public class VersionBean {
	protected int objectlID;
	protected String objectName;
	protected String objectVersion;
	protected String objectURL;
	protected String versionMemo;
	protected int versionCode;	// 用于app版本的控制
	protected boolean forceUpdate;	// true,强制更新; false,让用户选择是否更新		(用于app更新)

	public VersionBean() {
		super();
	}

	public VersionBean(int objectlID, String objectName, String objectVersion, String objectURL, String versionMemo, boolean forceUpdate) {
		this.objectlID = objectlID;
		this.objectName = objectName;
		this.objectVersion = objectVersion;
		this.objectURL = objectURL;
		this.versionMemo = versionMemo;
		this.forceUpdate = forceUpdate;

		if (2 == objectlID) {	// android apk
			try {
				versionCode = Integer.valueOf(objectVersion);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(String objectVersion) {
		this.objectVersion = objectVersion;
	}

	public String getObjectURL() {
		return objectURL;
	}

	public void setObjectURL(String objectURL) {
		this.objectURL = objectURL;
	}

	public String getVersionMemo() {
		return versionMemo;
	}

	public void setVersionMemo(String versionMemo) {
		this.versionMemo = versionMemo;
	}

	public int getObjectlID() {
		return objectlID;
	}

	public void setObjectlID(int objectlID) {
		this.objectlID = objectlID;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	
}
