package com.zwj.basedemo.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.zwj.basedemo.R;
import com.zwj.basedemo.view.adapter.recyclerview.CommonAdapter;
import com.zwj.basedemo.view.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 提车地adapter
 * @author zwj
 */
public class CarOfferAdapter<T> extends CommonAdapter<T> {
	protected int currentCheckedPosition = -1; // 当前被选中的item位置,-1为全未选中

	public CarOfferAdapter(Context context, List<T> mDatas,
						   int itemLayoutId) {
		super(context, itemLayoutId, mDatas);
	}

	public CarOfferAdapter(Context context, List<T> mDatas,
						   int itemLayoutId, int currentCheckedPosition) {
		super(context, itemLayoutId, mDatas);
		this.currentCheckedPosition = currentCheckedPosition;
	}

	@Override
	public void convert(ViewHolder holder, T t, int position) {
		View view = holder.getConvertView();
		TextView tv = holder.getView(R.id.tv);
		if (position == currentCheckedPosition) {
			view.setBackgroundResource(R.drawable.shape_bg_car_offer_selected);
			if(tv != null) {
				tv.setTextColor(Color.WHITE);
			}
		} else {
			view.setBackgroundResource(R.drawable.shape_bg_normal);
			if(tv != null) {
				tv.setTextColor(mContext.getResources().getColor(R.color.gray_dark_999999));
			}
		}
	}

	public T getCurrentCheckedItem() {
		if (currentCheckedPosition != -1) {
			return mDatas.get(currentCheckedPosition);
		}

		return null;
	}

	public int getCurrentCheckedPosition() {
		return currentCheckedPosition;
	}

	public void setCurrentCheckedPosition(int currentCheckedPosition) {
		if (currentCheckedPosition >= mDatas.size()
				|| currentCheckedPosition < 0) {
			this.currentCheckedPosition = -1;
		} else {
			if (this.currentCheckedPosition != currentCheckedPosition) {
				this.currentCheckedPosition = currentCheckedPosition;
			}
		}

		notifyDataSetChanged();
	}

}
