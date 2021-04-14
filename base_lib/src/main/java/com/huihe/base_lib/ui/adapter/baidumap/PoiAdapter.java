package com.huihe.base_lib.ui.adapter.baidumap;

import android.content.Context;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class PoiAdapter extends CommonRVAdapter<PoiInfo> {
    public PoiAdapter(int layoutId, Context context, List<PoiInfo> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, PoiInfo poiInfo, int position) {
        String name = poiInfo.name;
        String address = poiInfo.getAddress();
        viewHolder.setText(R.id.item_poi_tv_name, name);
        viewHolder.setText(R.id.item_poi_tv_address, address);
        ImageView ivCheckState = viewHolder.getView(R.id.item_poi_iv_check_state);
        if (position == 0) {
            ivCheckState.setImageResource(R.drawable.check_state);
        } else {
            ivCheckState.setImageResource(R.drawable.uncheck_state);
        }
    }
}
