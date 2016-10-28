package com.gc.flashview;

import android.os.Bundle;
import android.widget.ImageView;

import com.zwj.basedemo.R;
import com.zwj.basedemo.util.ImageBuilder;
import com.zwj.basedemo.view.fragment.base.BaseFragment;


/**
 * Created by zwj on 2016/9/6.
 */
public class ImageFragment extends BaseFragment {
    private ImageView iv;
    private String url;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_flash_image;
    }

    @Override
    protected void receiveArguments(Bundle savedInstanceState) {
        super.receiveArguments(savedInstanceState);
        this.url = getArguments().getString("url");
    }

    @Override
    protected void initView() {
        iv = getView(R.id.iv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        new ImageBuilder(getActivity() , iv, url, ImageBuilder.LoadMode.URL).setScaleType(ImageView.ScaleType.FIT_XY).build();
    }

    @Override
    protected void setListener() {

    }
}
