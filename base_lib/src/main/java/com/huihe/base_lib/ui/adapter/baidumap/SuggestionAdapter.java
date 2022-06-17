package com.huihe.base_lib.ui.adapter.baidumap;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;

import java.util.List;

public class SuggestionAdapter extends EmptyRVAdapter<PoiInfo> {

    private LatLng curLatlng;

    public SuggestionAdapter(int layoutId, Context context, List<PoiInfo> data,OnItemClickListener<PoiInfo> onItemClickListener) {
        super(layoutId, context, data,onItemClickListener);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final PoiInfo poiInfo, int position) {

        LatLng pt = poiInfo.location;
        double distance = DistanceUtil.getDistance(curLatlng, pt);
        viewHolder.setText(R.id.item_suggestion_tv_title, poiInfo.name);
        viewHolder.setText(R.id.item_suggestion_tv_desc, poiInfo.address);
        float distanceF = NumberUtils.tranToADecimal(distance/1000);
        viewHolder.setText(R.id.item_suggestion_tv_distance, distanceF + "km");
    }

    public void setLatLng(LatLng curLatlng) {
        this.curLatlng = curLatlng;
    }
}
