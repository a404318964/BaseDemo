/**
 * 
 */
package com.zwj.basedemo.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.zwj.basedemo.MyApplication;


public class ToastUtil {

	private static Toast mToast;

	/**
	 * 取消mToast引用对象
	 */
	public static void destroy() {
		mToast = null;
	}

	/**
	 * 默认toast的显示
	 * 
	 * @param content
	 *            提示内容
	 */
	public static void toast(String content) {
		if(!TextUtils.isEmpty(content)) {
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = Toast.makeText(MyApplication.getGlobalContext(), content,
					Toast.LENGTH_SHORT);
			mToast.show();
		}
	}

	/***
	 * 
	 * @param content
	 *            内容
	 * @param duration
	 *            时间
	 */
	public static void toast(String content, int duration) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(MyApplication.getGlobalContext(), content,
				duration);
		mToast.show();
	}

	/**
	 * 默认toast的显示
	 * 
	 * @param resId
	 *            提示内容字符串
	 */
	public static void toast(int resId) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(MyApplication.getGlobalContext(), resId,
				Toast.LENGTH_SHORT);
		mToast.show();
	}

}