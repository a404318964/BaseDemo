package com.zwj.basedemo.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.zwj.basedemo.bean.SearchCarParamBean;
import com.zwj.basedemo.bean.buycar.importcar.ImportCarBean;
import com.zwj.basedemo.constant.Constant;
import com.zwj.basedemo.mvp.biz.CarBiz;
import com.zwj.basedemo.mvp.view.IImportCarListView;
import com.zwj.basedemo.util.JsonUtil;
import com.zwj.basedemo.util.net.bean.ResponseBean;
import com.zwj.basedemo.util.net.callback.SimpleCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwj on 2016/8/11.
 */
public class ImportCarListPresenter extends BaseCarListPresenter<IImportCarListView> {
    private List<ImportCarBean> importCarBeanList;
    private SearchCarParamBean searchCarParamBean;

    public ImportCarListPresenter(IImportCarListView importCarListView) {
        super(importCarListView);
        importCarBeanList = new ArrayList<>();
        searchCarParamBean = new SearchCarParamBean();
    }

    public List<ImportCarBean> getImportCarBeanList() {
        return importCarBeanList;
    }

    @Override
    protected void searchCarData() {
        searchCarParamBean.getPageNoParamBean().setValue(String.valueOf(pageNo));
        CarBiz.searchImportCar(searchCarParamBean, new SimpleCallBack() {
            @Override
            public void onSuccess(ResponseBean responseBean) {
                try {
                    JSONObject jsonObject = new JSONObject(responseBean.getResult());
                    int status = jsonObject.getInt("status");
                    if (status == 1) {
                        count = jsonObject.getInt("count");
                        if (count > 0) {
                            String data = jsonObject.optString("data");
                            // 下拉刷新还有重置，必须把list清空(就是pageno=1的时候)
                            if (pageNo == 1) {
                                mView.resetLoadMoreStatus();
                                importCarBeanList.clear();
                            }
                            List<ImportCarBean> tempList = JsonUtil.getObjects(data, ImportCarBean.class);

                            if (tempList != null && tempList.size() > 0) {
                                importCarBeanList.addAll(tempList);
                                mView.refreshUI();
                                if (importCarBeanList != null && importCarBeanList.size() == count) {
                                    mView.setLoadAll();
                                }
                            }
                        } else {
                            // 没有数据
                            onDataEmpty();
                        }
                    } else { // 失败
                        mView.showErrorLayout(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished(ResponseBean responseBean) {
                super.onFinished(responseBean);

                mView.showLoadingLayout(false);
                mView.onLoadMoreFinish();
                if (isPullDownRefreshing) {
                    isPullDownRefreshing = false;
                    mView.finishSwipeRefreshLayout();
                }
            }

            @Override
            public void onError(ResponseBean responseBean) {
                super.onError(responseBean);
                mView.showErrorLayout(true);
            }
        });
    }

    @Override
    public void setSelectParams() {
        // 把所有选中的条件加入list中
        addToSelectParamList(searchCarParamBean.getBrandParamBean());
        addToSelectParamList(searchCarParamBean.getStyleParamBean());
        addToSelectParamList(searchCarParamBean.getCountryParamBean());
        addToSelectParamList(searchCarParamBean.getCarFlagParamBean());
        addToSelectParamList(searchCarParamBean.getAgioCarParamBean());
        addToSelectParamList(searchCarParamBean.getHotCarParamBean());
        addToSelectParamList(searchCarParamBean.getPriceStartParamBean());
        addToSelectParamList(searchCarParamBean.getSearchTextParamBean());
    }

    @Override
    protected void clearCarList() {
        importCarBeanList.clear();
    }

    @Override
    protected void searchCarParamReset() {
        searchCarParamBean.clear();
    }

    /**
     * 处理从其他界面回来的数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_SELECT_BRAND:
                    searchCarParamBean.getBrandParamBean().setValue(data.getStringExtra(Constant.PARAM_BRAND));
                    searchCarParamBean.getBrandParamBean().setTag(data
                            .getStringExtra(Constant.ALL));
                    break;

                case Constant.REQUEST_CODE_MORE_FILTER_CONDITION:

                    searchCarParamBean.getStyleParamBean().set(data.getStringExtra(Constant.STYLE_NAME),
                            data.getStringExtra(Constant.PARAM_STYLE));

                    searchCarParamBean.getCarFlagParamBean().set(data.getStringExtra(Constant.CAR_FLAG_TEXT),
                                data.getStringExtra(Constant.PARAM_CAR_FLAG));

                    searchCarParamBean.getPriceStartParamBean().setValue(data.getStringExtra(Constant.PARAM_PRICE_START));

                    String priceEnd = data.getStringExtra(Constant.PARAM_PRICE_END);
                    searchCarParamBean.getPriceEndParamBean().setValue(priceEnd);

                    StringBuilder sbPriceTag = new StringBuilder();
                    sbPriceTag.append(data.getStringExtra(Constant.PARAM_PRICE_START));
                    if(TextUtils.isEmpty(priceEnd)) {
                        sbPriceTag.append("万以上");
                    }else {
                        sbPriceTag.append("-").append(data.getStringExtra(Constant.PARAM_PRICE_END)).append("万");
                    }
                    searchCarParamBean.getPriceStartParamBean().setTag(sbPriceTag.toString());


                    searchCarParamBean.getAgioCarParamBean().setValue(data.getStringExtra(Constant.PARAM_AGIOCAR));
                    if ("true".equals(searchCarParamBean.getAgioCarParamBean().getValue())) {
                        searchCarParamBean.getAgioCarParamBean().setTag("特惠车");
                    } else {
                        searchCarParamBean.getAgioCarParamBean().setTag(null);
                    }

                    searchCarParamBean.getHotCarParamBean().setValue(data.getStringExtra(Constant.PARAM_HOTCAR));
                    if ("true".equals(searchCarParamBean.getHotCarParamBean().getValue())) {
                        searchCarParamBean.getHotCarParamBean().setTag("热门车");
                    } else {
                        searchCarParamBean.getHotCarParamBean().setTag(null);
                    }
                    break;
            }

            reloadData();
        }
    }

    /**
     * 跳转到更多筛选的界面
     */
    public void startMoreFilterConditionActivity(Activity activity) {
//        Intent intent = new Intent(activity,
//                ImportCarMoreFilterConditionActivity.class);
//
//        intent.putExtra(Constant.CAR_COUNT, count);
//        CommonUtil.putExtra(intent, Constant.PARAM_STYLE, searchCarParamBean.getStyleParamBean().getValue()); // 车型id
//        CommonUtil.putExtra(intent, Constant.PARAM_CAR_FLAG, searchCarParamBean.getCarFlagParamBean().getValue());
////        CommonUtil.putExtra(intent, Constant.PARAM_PRICE, searchCarParamBean.getPriceSectionParamBean().getValue());
//        CommonUtil.putExtra(intent, Constant.PARAM_PRICE_START, searchCarParamBean.getPriceStartParamBean().getValue());
//        CommonUtil.putExtra(intent, Constant.PARAM_PRICE_END, searchCarParamBean.getPriceEndParamBean().getValue());
//        CommonUtil.putExtra(intent, Constant.PARAM_AGIOCAR, searchCarParamBean.getAgioCarParamBean().getValue());
//        CommonUtil.putExtra(intent, Constant.PARAM_HOTCAR, searchCarParamBean.getHotCarParamBean().getValue());
//
//        CommonUtil.putExtra(intent, Constant.PARAM_PRICE_START, searchCarParamBean.getPriceStartParamBean().getValue());
//        CommonUtil.putExtra(intent, Constant.PARAM_PRICE_END, searchCarParamBean.getPriceEndParamBean().getValue());

//        activity.startActivityForResult(intent, Constant.REQUEST_CODE_MORE_FILTER_CONDITION);
    }

    public SearchCarParamBean getSearchCarParamBean() {
        return searchCarParamBean;
    }
}
