package com.zwj.basedemo.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.zwj.basedemo.R;
import com.zwj.basedemo.bean.buycar.importcar.ImportCarBean;
import com.zwj.basedemo.util.CommonUtil;
import com.zwj.basedemo.view.adapter.recyclerview.CommonAdapter;
import com.zwj.basedemo.view.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by zwj on 2016/8/11.
 */
public class ImportCarAdapter extends CommonAdapter<ImportCarBean> {
    public ImportCarAdapter(Context context, List<ImportCarBean> datas) {
        super(context, R.layout.item_import_car_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ImportCarBean item, int position) {
        TextView tvFullName = holder.getView(R.id.tv_full_name);
        CommonUtil.addImage(mContext, tvFullName, item.getFullName(), item.isHotCar(), item.isAgioCar());

//        holder.setImageResource(R.id.iv_color_out, DictionaryUtil.getSmallRectangleBgId(item.getColorOutID()));
//        holder.setImageResource(R.id.iv_color_in, DictionaryUtil.getSmallRectangleBgId(item.getColorInID()));
        holder.setText(R.id.tv_country, item.getCountry());
        holder.setText(R.id.tv_price, String.valueOf(item.getPrice()));
        holder.setText(R.id.tv_market_price, item.getPriceMarket() + "万");
        holder.setImageByUrl(R.id.iv_car_pic, item.getCoverImgURL());
        holder.setText(R.id.tv_status, item.getStatus());

        //1现车, 2报关中, 3已到港, 4期货
        switch (item.getCarFlag()) {
            case "1":
                holder.setBackgroundColor(R.id.tv_status, Color.parseColor("#44c256"));
                break;

            case "2":
                holder.setBackgroundColor(R.id.tv_status, Color.parseColor("#7550e0"));
                break;

            case "3":
                holder.setBackgroundColor(R.id.tv_status, Color.parseColor("#5097df"));
                break;

            case "4":
                holder.setBackgroundColor(R.id.tv_status, Color.BLACK);
                break;
        }
    }
}
