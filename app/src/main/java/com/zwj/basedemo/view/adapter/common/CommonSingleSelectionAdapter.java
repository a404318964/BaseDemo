package com.zwj.basedemo.view.adapter.common;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zwj.basedemo.R;
import com.zwj.basedemo.view.adapter.abslistview.CommonAdapter;
import com.zwj.basedemo.view.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * 单选通用Adapter，显示选中的图标id为iv
 * 其他操作就重写convert，记得调用super.convert（）
 * <p>
 * 若需要改变文本的颜色，textview的id设为tv
 *
 * @author zwj
 */
public class CommonSingleSelectionAdapter<T> extends CommonAdapter<T> {
    protected int currentCheckedPosition = 0; // 当前被选中的item位置,-1为全未选中
    private int normalDrawableId;        // 正常时显示的图片id
    private int selectedDrawableId;        // 选中时显示的图片id
    private int textNormalColor;
    private int textSelectedColor;
    private boolean textNeedChangeStatus;    // true,文字也要跟着变色

    public interface OnItemClickListener<T> {
        /**
         * 点击item时回调
         */
        void onItemClick(T item, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public CommonSingleSelectionAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    public CommonSingleSelectionAdapter(Context context, int layoutId, List<T> datas, int normalDrawableId, int selectedDrawableId) {
        super(context, layoutId, datas);
        this.normalDrawableId = normalDrawableId;
        this.selectedDrawableId = selectedDrawableId;
    }

    public CommonSingleSelectionAdapter(Context context, int layoutId, List<T> datas, int currentCheckedPosition) {
        super(context, layoutId, datas);
        this.currentCheckedPosition = currentCheckedPosition;
    }

    @Override
    protected void convert(ViewHolder viewHolder, T item, int position) {
        View itemView = viewHolder.getConvertView();
        ImageView iv = viewHolder.getView(R.id.iv);
        if (position == currentCheckedPosition) {
            if (iv != null) {
                if (normalDrawableId == -1) {
                    iv.setVisibility(View.VISIBLE);
                }
                iv.setImageResource(selectedDrawableId);

                if (textNeedChangeStatus) {
                    viewHolder.setTextColor(R.id.tv, textSelectedColor);
                }
            }
        } else {
            if (iv != null) {
                if (normalDrawableId == -1) {
                    iv.setVisibility(View.GONE);
                } else {
                    iv.setImageResource(normalDrawableId);
                }

                if (textNeedChangeStatus) {
                    viewHolder.setTextColor(R.id.tv, textNormalColor);
                }
            }
        }

        itemView.setOnClickListener((View v) -> {
            currentCheckedPosition = viewHolder.getItemPosition();
            notifyDataSetChanged();

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(item, viewHolder.getItemPosition());
            }
        });
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

    public CommonSingleSelectionAdapter<T> setCurrentCheckedPosition(int currentCheckedPosition) {
        if (currentCheckedPosition >= mDatas.size()
                || currentCheckedPosition < 0) {
            this.currentCheckedPosition = 0;
        } else {
            if (this.currentCheckedPosition != currentCheckedPosition) {
                this.currentCheckedPosition = currentCheckedPosition;
            }
        }

        notifyDataSetChanged();
        return this;
    }

    public int getNormalDrawableId() {
        return normalDrawableId;
    }

    public CommonSingleSelectionAdapter<T> setNormalDrawableId(int normalDrawableId) {
        this.normalDrawableId = normalDrawableId;
        return this;
    }

    public int getSelectedDrawableId() {
        return selectedDrawableId;
    }

    public CommonSingleSelectionAdapter<T> setSelectedDrawableId(int selectedDrawableId) {
        this.selectedDrawableId = selectedDrawableId;
        return this;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public CommonSingleSelectionAdapter<T> setmOnItemClickListener(OnItemClickListener<T> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        return this;
    }

    public int getTextNormalColor() {
        return textNormalColor;
    }

    public CommonSingleSelectionAdapter<T> setTextNormalColor(int textNormalColor) {
        this.textNormalColor = textNormalColor;
        return this;
    }

    public int getTextSelectedColor() {
        return textSelectedColor;
    }

    public CommonSingleSelectionAdapter<T> setTextSelectedColor(int textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
        return this;
    }

    public boolean isTextNeedChangeStatus() {
        return textNeedChangeStatus;
    }

    public CommonSingleSelectionAdapter<T> setTextNeedChangeStatus(boolean textNeedChangeStatus) {
        this.textNeedChangeStatus = textNeedChangeStatus;
        return this;
    }
}
