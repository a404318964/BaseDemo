package com.zwj.basedemo.view.activity.buycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gc.flashview.FlashView;
import com.zhy.autolayout.base.BaseAutoLayoutBindingActivity;
import com.zwj.basedemo.R;
import com.zwj.basedemo.bean.buycar.importcar.CarOffer;
import com.zwj.basedemo.bean.buycar.importcar.ImportCarBean;
import com.zwj.basedemo.constant.Constant;
import com.zwj.basedemo.databinding.ActivityImportCarDetailBinding;
import com.zwj.basedemo.mvp.presenter.ImportCarDetailPresenter;
import com.zwj.basedemo.mvp.view.IImportCarDetailView;
import com.zwj.basedemo.util.CommonUtil;
import com.zwj.basedemo.util.DictionaryUtil;
import com.zwj.basedemo.util.ImageBuilder;
import com.zwj.basedemo.view.adapter.CarOfferAdapter;
import com.zwj.basedemo.view.adapter.recyclerview.MultiItemTypeAdapter;
import com.zwj.basedemo.view.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 车辆详情页
 *
 * @author zwj
 */
public class ImportCarDetailActivity extends BaseAutoLayoutBindingActivity implements
        OnClickListener, IImportCarDetailView {
    private ActivityImportCarDetailBinding binding;
    private ImportCarDetailPresenter mPresenter = new ImportCarDetailPresenter(this);
//    private TitleView mTitleView;
    private FlashView mFlashView;

    private TextView tvIndicator;
    private TextView tvSparePrice;
    private ImageView ivBrandIcon; // 品牌icon

    private ImageView ivHot, ivAgio;
    private RecyclerView rvCarAddress;  // 提车地
    private CarOfferAdapter<CarOffer> adapter;

    // 底部控件
    private TextView tvDial, tvBuy;

    // 车辆详情
    private ImageView ivFacade;      // 外观颜色
    private ImageView ivInterior;    // 内饰颜色

    private ScrollView svContent;
//    private SharePopupWindow mShareWindow;  //分享

    @Override
    protected int getContentViewId() {
        return R.layout.activity_import_car_detail;
    }

    @Override
    protected void findViews() {
//        mTitleView = getView(R.id.id_title);
        mFlashView = getView(R.id.flash_view);
        tvIndicator = getView(R.id.tv_indicator);
        tvSparePrice = getView(R.id.tv_spare_price);
        ivHot = getView(R.id.iv_hot);
        ivAgio = getView(R.id.iv_agio);
        rvCarAddress = getView(R.id.rv_get_car_address);
        tvDial = getView(R.id.tv_dial);
        tvBuy = getView(R.id.tv_buy);
        ivBrandIcon = getView(R.id.iv_brand);

        // 车辆详情
        ivFacade = getView(R.id.iv_facade);
        ivInterior = getView(R.id.iv_interior);

        svContent = getView(R.id.sv_content);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if(mBinding != null) {
            binding = (ActivityImportCarDetailBinding) mBinding;
        }

        mFlashView.setDotLayoutVisibility(false);

        Intent intent = getIntent();
        if (intent != null) {
            mPresenter.getCarDetailInfo(intent.getStringExtra(Constant.PARAM_CAR_NO));
        }
    }

    @Override
    protected void setListener() {
//        mTitleView
//                .setOnTitleMenuClickListener(new SimpleTitleMenuClickListener() {
//                    @Override
//                    public void onClickBack() {
//                        finish();
//                    }
//
//                    @Override
//                    public void onClickImRightListener() {
//                        showShareWindow();
//                    }
//                });
        mFlashView.setOnPageSelectedListener((int position) -> mPresenter.setPageText(position));
        mFlashView.setOnPageClickListener((int position) -> {
            if (mPresenter.getCarPicList() != null && mPresenter.getCarPicList().size() > 0) {
//                startImageViewActivity(mPresenter.getCarPicList(), position);
            }
        });

        tvDial.setOnClickListener(this);
        tvBuy.setOnClickListener(this);
    }

    /**
     * 弹出分享弹框
     */
    private void showShareWindow() {
        // TODO
//        if (mPresenter.getImportCarBean() == null) {//避免数据没有加载完成就点击弹框导致崩溃
//            return;
//        }
//        if (mShareWindow == null) {
//            mShareWindow = new SharePopupWindow(this, "title", "http://www.baidu.com", mPresenter.getImportCarBean().getCoverImgURL(), mPresenter.getImportCarBean().getFullName(), mPresenter.getCarNo());
//            mShareWindow.showPopupWindow();
//        } else if (mShareWindow != null && !mShareWindow.isShowing()) {
//            mShareWindow.showPopupWindow();
//        }
    }

    public void setPageText(String indicator) {
        tvIndicator.setText(indicator);
    }

    public void fillCarData() {
        ImportCarBean importCarBean = mPresenter.getImportCarBean();

        // TODO
        binding.setImportCarBean(importCarBean);

        StringBuilder sbBrandPath = new StringBuilder();
        sbBrandPath.append("/img/brands/ic_brand_").append(importCarBean.getBrandID()).append(".png");
        new ImageBuilder(this, ivBrandIcon, sbBrandPath.toString(), ImageBuilder.LoadMode.ASSETS).setScaleType(ImageView.ScaleType.FIT_XY).build();

        CommonUtil.showView(ivHot, importCarBean.isHotCar());
        CommonUtil.showView(ivAgio, importCarBean.isAgioCar());

        // 提车地
        if (importCarBean.getCarOffers() != null && importCarBean.getCarOffers().size() > 0) {
            rvCarAddress.setLayoutManager(new GridLayoutManager(mContext, 4));
            rvCarAddress.setHasFixedSize(true);
            rvCarAddress.setItemAnimator(new DefaultItemAnimator());
            adapter = new CarOfferAdapter<CarOffer>(mContext, importCarBean.getCarOffers(), R.layout.item_car_offer, 0) {

                @Override
                public void convert(ViewHolder holder, CarOffer item, int position) {
                    super.convert(holder, item, position);
                    holder.setText(R.id.tv, item.getCity());
                }
            };
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<CarOffer>() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, CarOffer item, int position) {
                    setPrice(item);
                    adapter.setCurrentCheckedPosition(position);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, CarOffer o, int position) {
                    return false;
                }
            });
            rvCarAddress.setAdapter(adapter);

            // 默认选中第一个提车地
            setPrice(importCarBean.getCarOffers().get(0));
        }

        // 车辆详情
//        ivFacade.setImageResource(DictionaryUtil.getSmallRectangleBgId(importCarBean.getColorOutID()));
//        ivInterior.setImageResource(DictionaryUtil.getSmallRectangleBgId(importCarBean.getColorInID()));
        binding.tvStyle.setText(DictionaryUtil.getStyleById(Integer.valueOf(importCarBean.getStyleID())));

        // 车辆配置情况
        binding.tvCarParam.setText(Html.fromHtml(importCarBean.getReadme()));

        // 解决scrollview嵌套listview导致进入界面后自动定位到了页面最底部的问题
        svContent.smoothScrollTo(0, 0);
    }

    @Override
    public void onClick(View v) {
        if (mPresenter.getImportCarBean() != null) {
            switch (v.getId()) {
                case R.id.tv_dial: // 拨打电话
                    CommonUtil.call(this, getString(R.string.service_hotline));
                    break;

                case R.id.tv_buy: // 确认购买
                    startOrderConfirmActivity();
                    break;
            }
        }
    }

    private void startOrderConfirmActivity() {
//        if (mPresenter.getImportCarBean() != null) {
//            Intent intent = new Intent(mContext, ImportOrNewCarOrderConfirmActivity.class);
//            intent.putExtra(Constant.PARAM_CAR_NO, mPresenter.getCarNo());
//            intent.putExtra("fullName", mPresenter.getImportCarBean().getFullName());
//            intent.putExtra("coverImgURL", mPresenter.getImportCarBean().getCoverImgURL());
//            intent.putExtra(Constant.PARAM_HOTCAR, mPresenter.getImportCarBean().isHotCar());
//            intent.putExtra(Constant.PARAM_AGIOCAR, mPresenter.getImportCarBean().isAgioCar());
//            intent.putExtra("price", String.valueOf(mPresenter.getImportCarBean().getPrice()));
//
//            startActivity(intent);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(mContext).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setImageUris(List<String> imageList) {
        mFlashView.setImageUris(imageList);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onUnbind();
        super.onDestroy();
    }

    private void setPrice(CarOffer carOffer) {
        StringBuilder sbPrice = new StringBuilder();
        sbPrice.append("￥").append(carOffer.getPrice()).append("万");
        binding.tvPrice.setText(sbPrice.toString());

        if (carOffer.getPriceAgioMax() == 0) {
            CommonUtil.showView(tvSparePrice, false);
        } else {
            CommonUtil.showView(tvSparePrice, true);
            StringBuilder sbPriceAgioMax = new StringBuilder();
            sbPriceAgioMax.append("(省").append(carOffer.getPriceAgioMax()).append("万)");
            tvSparePrice.setText(sbPriceAgioMax.toString());
        }
    }
}
