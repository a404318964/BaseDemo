package com.zwj.basedemo.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zwj.basedemo.R;
import com.zwj.basedemo.view.adapter.abslistview.ViewHolder;
import com.zwj.basedemo.view.adapter.common.CommonSingleSelectionAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterView extends FrameLayout implements OnClickListener {
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private LinearLayout llSort, llBrand, llMore;
    private List<TextView> filterMenuTvList; // 用来存放顶部过滤菜单选中的textview
    private List<ImageView> filterMenuIvList; // 用来存放顶部过滤菜单选中的小箭头
    private List<View> underLineList;
    private View mViewCovering;
    private TextView tvSortLabel;

    private ListView lvSortCondition;
    private CommonSingleSelectionAdapter<String> sortConditionAdapter;

    private String sort;
    private boolean isDefalutSort = true;    // 是否使用默认的排序

    public FilterView(Context context) {
        this(context, null);
    }

    public FilterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_filter_view, this, true);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        llSort = (LinearLayout) findViewById(R.id.ll_sort);
        llBrand = (LinearLayout) findViewById(R.id.rl_brand);
        llMore = (LinearLayout) findViewById(R.id.ll_more);
        mViewCovering = findViewById(R.id.view_covering);

        if (filterMenuTvList == null) {
            filterMenuTvList = new ArrayList<TextView>();
        } else {
            filterMenuTvList.clear();
        }

        tvSortLabel = (TextView) findViewById(R.id.tv_sort);
        filterMenuTvList.add(tvSortLabel);
        filterMenuTvList.add((TextView) findViewById(R.id.tv_brand));
        filterMenuTvList.add((TextView) findViewById(R.id.tv_more));

        if (filterMenuIvList == null) {
            filterMenuIvList = new ArrayList<ImageView>();
        } else {
            filterMenuIvList.clear();
        }
        filterMenuIvList.add((ImageView) findViewById(R.id.iv_sort));
        filterMenuIvList.add((ImageView) findViewById(R.id.iv_brand));
        filterMenuIvList.add((ImageView) findViewById(R.id.iv_more));

        underLineList = new ArrayList<View>();
        underLineList.add(findViewById(R.id.view_under_line_sort));
        underLineList.add(findViewById(R.id.view_under_line_brand));
        underLineList.add(findViewById(R.id.view_under_line_more));

        lvSortCondition = (ListView) findViewById(R.id.lv_sort_condition);
    }

    private void initData() {
        List<String> sortDataList = Arrays.asList(getResources().getStringArray(R.array.sort_items));
        sortConditionAdapter = new CommonSingleSelectionAdapter<String>(mContext, R.layout.item_lv_sort, sortDataList, -1, R.drawable.ic_tick) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                super.convert(viewHolder, item, position);
                viewHolder.setText(R.id.tv, item);
            }
        };
        sortConditionAdapter.setTextNeedChangeStatus(true)
                .setTextNormalColor(getResources().getColor(R.color.text_import_color_333333))
                .setTextSelectedColor(getResources().getColor(R.color.theme_color));
        lvSortCondition.setAdapter(sortConditionAdapter);
    }

    public void setSortListData(List<String> sortList) {
        isDefalutSort = false;
        sortConditionAdapter.setDatas(sortList);
        sortConditionAdapter.notifyDataSetChanged();
    }

    private void setListener() {
        llSort.setOnClickListener(this);
        llBrand.setOnClickListener(this);
        llMore.setOnClickListener(this);
        mViewCovering.setOnClickListener(this);

        sortConditionAdapter.setmOnItemClickListener(new CommonSingleSelectionAdapter.OnItemClickListener<String>() {

            @Override
            public void onItemClick(String item, int position) {
                if (isDefalutSort) {
                    switch (position) {
                        case 0:
                            sort = null;
                            break;

                        case 1:
                            sort = "1";
                            break;

                        case 2:
                            sort = "-1";
                            break;

                        case 3:
                            sort = "2";
                            break;

                        case 4:
                            sort = "-2";
                            break;

                        case 5:
                            sort = "3";
                            break;

                        case 6:
                            sort = "-3";
                            break;
                    }
                }else {
                    sort = String.valueOf(position);
                }

                tvSortLabel.setText(item);
                changeFilterMenuStatus(-1);
                if (mOnSelectFilterItemListener != null) {
                    mOnSelectFilterItemListener.onSelectSort(sort);
                }
            }
        });
    }

    /**
     * 标题栏菜单选项点击回调接口
     *
     * @author zhuangwj
     */
    public interface OnSelectFilterItemListener {

        /**
         * 选择的排序条件
         *
         * @param sort
         */
        void onSelectSort(String sort);

        /**
         * 点击品牌
         */
        void onBrandClick();

        /**
         * 点击更多
         */
        void onMoreClick();
    }

    private OnSelectFilterItemListener mOnSelectFilterItemListener;

    public void setOnSelectFilterItemListener(OnSelectFilterItemListener listener) {
        mOnSelectFilterItemListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_sort: // 选择排序的依据
                changeFilterMenuStatus(0);
                break;

            case R.id.rl_brand: // 选择品牌
                changeFilterMenuStatus(1);
                if (mOnSelectFilterItemListener != null) {
                    mOnSelectFilterItemListener.onBrandClick();
                }
                break;

            case R.id.ll_more:
                changeFilterMenuStatus(2);
                if (mOnSelectFilterItemListener != null) {
                    mOnSelectFilterItemListener.onMoreClick();
                }
                break;

            case R.id.view_covering:
                changeFilterMenuStatus(-1);
                break;
        }
    }

    /**
     * 改变顶部过滤车辆菜单选项的状态
     *
     * @param index 0，选中排序； 1，选中品牌；2，选中更多；-1则全部置为未选中
     */
    public void changeFilterMenuStatus(int index) {
        if (index != 0) {
            mViewCovering.setVisibility(View.GONE);
        }
        for (int i = 0; i < filterMenuTvList.size(); i++) {
            TextView tv = filterMenuTvList.get(i);
            ImageView iv = filterMenuIvList.get(i);
            View underLine = underLineList.get(i);

            if (i == index) {
                // 切换选中与为选中状态
                if (tv.isSelected()) {
                    tv.setSelected(false);
                    iv.setSelected(false);
                    underLine.setVisibility(View.GONE);

                    if (i == 0) {
                        lvSortCondition.setVisibility(View.GONE);
                        mViewCovering.setVisibility(View.GONE);
                    }
                } else {
                    if (i == 0) {
                        lvSortCondition.setVisibility(View.VISIBLE);
                        // 设置半透明的view可视
                        mViewCovering.setVisibility(View.VISIBLE);
                    }

                    tv.setSelected(true);
                    iv.setSelected(true);
                    underLine.setVisibility(View.VISIBLE);
                }
            } else {
                if (tv.isSelected()) {
                    tv.setSelected(false);
                    iv.setSelected(false);
                    underLine.setVisibility(View.GONE);

                    if (i == 0) {
                        lvSortCondition.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

}
