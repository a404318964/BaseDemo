package com.zwj.basedemo.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.flowlayoutlibrary.TagFlowLayout;
import com.zwj.basedemo.R;
import com.zwj.basedemo.bean.ParamBean;
import com.zwj.basedemo.bean.buycar.importcar.ImportCarBean;
import com.zwj.basedemo.constant.Constant;
import com.zwj.basedemo.mvp.presenter.ImportCarListPresenter;
import com.zwj.basedemo.mvp.view.IImportCarListView;
import com.zwj.basedemo.util.CommonUtil;
import com.zwj.basedemo.util.ImageBuilder;
import com.zwj.basedemo.util.ToastUtil;
import com.zwj.basedemo.view.activity.buycar.ImportCarDetailActivity;
import com.zwj.basedemo.view.adapter.ImportCarAdapter;
import com.zwj.basedemo.view.adapter.recyclerview.MultiItemTypeAdapter;
import com.zwj.basedemo.view.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zwj.basedemo.view.custom.FilterView;
import com.zwj.basedemo.view.fragment.base.BaseFragment;

import java.util.List;

/**
 * Created by zwj on 2016/9/12.
 */
public class BuyCarFragment extends BaseFragment implements IImportCarListView {
    private ImportCarListPresenter mPresenter = new ImportCarListPresenter(this);
//    private TitleView mTitleView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView rvImportCar;
    private ImportCarAdapter mAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;


    private FilterView mFilterView;
    private LinearLayout llNoList;

    // 当前选择的筛选条件
    private RelativeLayout rlCurSelectCondition;
    private TagFlowLayout flowLayoutCurSelectCondition;
    private TextView tvReset;
//    private SelectConditionTagAdapter tagAdapter;

    private RelativeLayout rlCarLoading;
//    private ImageView ivCarLoading;
//    private AnimationDrawable aniDrawable;
    private ImageView ivScrollTop;

    // 加载错误的相关view
    private LinearLayout llLoadingError;
    private TextView tvRefresh;

    private int lastVisibleItem;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_car_list;
    }

    @Override
    protected void initView() {
//        mTitleView = getView(R.id.id_title);
        mFilterView = getView(R.id.id_filter_view);
        mSwipeRefreshLayout = getView(R.id.swipe);
        rvImportCar = getView(R.id.rv_car_list);
        llNoList = getView(R.id.ll_no_list);
        rlCurSelectCondition = getView(R.id.rl_current_select_condition);
        flowLayoutCurSelectCondition = getView(R.id.flowlayout_current_select_condition);
        tvReset = getView(R.id.tv_reset);
        rlCarLoading = getView(R.id.rl_car_loading);
//        ivCarLoading = getView(R.id.iv_car_loading);
        ivScrollTop = getView(R.id.iv_scroll_top);
        llLoadingError = getView(R.id.ll_loading_error);
        tvRefresh = getView(R.id.tv_refresh);

        mSwipeRefreshLayout.setColorSchemeColors(mContext.getResources().getColor(R.color.theme_color));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.searchCar();
    }

    @Override
    protected void setListener() {
//        mTitleView.setOnTitleMenuClickListener(new SimpleTitleMenuClickListener() {
//            @Override
//            public void onClickBack() {
//                finish();
//            }
//        });

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.refresh();
        });

        mFilterView
                .setOnSelectFilterItemListener(new FilterView.OnSelectFilterItemListener() {

                    @Override
                    public void onSelectSort(String sort) {
                        // 价格从高到低的标志是-2
                        if("3".equals(sort)) {
                            sort = "-2";
                        }
                        mPresenter.getSearchCarParamBean().getSortParamBean().setValue(sort);
                        mPresenter.reloadData();
                    }

                    @Override
                    public void onBrandClick() {
//                        Intent intent = new Intent(mContext,
//                                SelectBrandActivity.class);
//                        intent.putExtra("isNeedCarSeries", false);
//                        startActivityForResult(intent,
//                                Constant.REQUEST_CODE_SELECT_BRAND);
                        ToastUtil.toast("点击 brand");
                    }

                    @Override
                    public void onMoreClick() {
//                        mPresenter.startMoreFilterConditionActivity(ImportCarListActivity.this);
                        ToastUtil.toast("点击 more");
                    }
                });

        ivScrollTop.setOnClickListener(this);
        tvReset.setOnClickListener(this);
        tvRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scroll_top:
                rvImportCar.scrollToPosition(0);
                ivScrollTop.setVisibility(View.GONE);
                break;

            case R.id.tv_reset:
            case R.id.tv_refresh:
                mPresenter.reset();
                break;
        }
    }

    @Override
    public void refreshUI() {
        if (mAdapter == null || mLoadMoreWrapper == null) {
            rvImportCar.setLayoutManager(new LinearLayoutManager(mContext));
            rvImportCar.setHasFixedSize(true);
            mAdapter = new ImportCarAdapter(mContext, mPresenter.getImportCarBeanList());

            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<ImportCarBean>() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, ImportCarBean item, int position) {
                    Intent intent = new Intent(mContext, ImportCarDetailActivity.class);
                    intent.putExtra(Constant.PARAM_CAR_NO, item.getCarNo());
                    startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, ImportCarBean item, int position) {
                    return false;
                }
            });

            mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
            setLoadStatusOnRefreshUI();
            mLoadMoreWrapper.setOnLoadMoreListener(() -> mPresenter.loadMore());

            rvImportCar.setAdapter(mLoadMoreWrapper);
            rvImportCar.setItemAnimator(new DefaultItemAnimator());

            rvImportCar.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    ImageBuilder.setOnSlidePauseLoadImage(mContext, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    RecyclerView.LayoutManager layoutManager = rvImportCar.getLayoutManager();

                    if (layoutManager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    } else if (layoutManager instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                        lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    }

                    if (lastVisibleItem > 6) {
                        CommonUtil.showView(ivScrollTop, true);
                    } else {
                        CommonUtil.showView(ivScrollTop, false);
                    }
                }
            });
        } else {
            setLoadStatusOnRefreshUI();
            mAdapter.setDatas(mPresenter.getImportCarBeanList());
            mLoadMoreWrapper.notifyDataSetChanged();
        }
    }

    /**
     * 在刷新界面的时候，根据获取的数据条数去设置底部加载更多的状态
     */
    private void setLoadStatusOnRefreshUI() {
        if (mPresenter.getImportCarBeanList().size() == 0) {
            mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LoadStatus.LOAD_EMPTY);
        } else if (mPresenter.getImportCarBeanList().size() > 0 && mPresenter.getImportCarBeanList().size() == mPresenter.getCount()) {
            mLoadMoreWrapper.setLoadStatus(LoadMoreWrapper.LoadStatus.LOAD_ALL);
        }
    }


    @Override
    public void finishSwipeRefreshLayout() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoListLayout(boolean isShow) {
        CommonUtil.showView(llNoList, isShow);
    }

    @Override
    public void showErrorLayout(boolean isShow) {
        CommonUtil.showView(llLoadingError, isShow);
    }

    @Override
    public void showLoadingLayout(boolean isShow) {
        if (isShow) {
            // 如果正在加载，隐藏掉空界面和错误界面
            CommonUtil.showView(llNoList, false);
            CommonUtil.showView(llLoadingError, false);

//            ivCarLoading.setBackgroundResource(R.drawable.car_loading_list);
//            aniDrawable = (AnimationDrawable) ivCarLoading.getBackground();
//            aniDrawable.start();
        } else {
//            if (aniDrawable != null && aniDrawable.isRunning()) {
//                aniDrawable.stop();
//            }
        }
        CommonUtil.showView(rlCarLoading, isShow);
    }

    @Override
    public void hideFilterView() {
//        mFilterView.changeFilterMenuStatus(-1);
    }

    @Override
    public void setCurrentSelectCondition(List<ParamBean> selectParamList) {

    }

    @Override
    public void setLoadAll() {
        mLoadMoreWrapper.setLoadAll();
    }

    @Override
    public void resetLoadMoreStatus() {
        if (mLoadMoreWrapper != null) {
            mLoadMoreWrapper.reset();
        }
    }

    @Override
    public void onLoadMoreFinish() {
        if (mLoadMoreWrapper != null) {
            mLoadMoreWrapper.onFinish();
        }
    }

    @Override
    public LoadMoreWrapper.LoadStatus getLoadMoreStatus() {
        if (mLoadMoreWrapper != null) {
            return mLoadMoreWrapper.getLoadStatus();
        }
        return null;
    }

    @Override
    public void onEmpty() {
        mLoadMoreWrapper.setEmpty();
    }

    @Override
    public void onDestroy() {
        mPresenter.onUnbind();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        mPresenter.onActivityResult(requestCode, resultCode, data);
//        hideFilterView();
    }
}
