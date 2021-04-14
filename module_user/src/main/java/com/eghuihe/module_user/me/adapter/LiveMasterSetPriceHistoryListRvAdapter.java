package com.eghuihe.module_user.me.adapter;

import android.content.Context;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.LiveEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.NumberUtils;

public class LiveMasterSetPriceHistoryListRvAdapter extends EmptyRVAdapter<LiveEntity> {
    public LiveMasterSetPriceHistoryListRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, LiveEntity liveEntity, int position) {

        viewHolder.setText(R.id.item_history_live_mastersetprice_tv_title,liveEntity.getTitle());
        String start_time = liveEntity.getStart_time();
        String end_time = liveEntity.getEnd_time();
        String distanceTime = DateUtils.getDistanceHMSTime2(start_time, end_time);

        viewHolder.setText(R.id.item_history_live_mastersetprice_tv_start_time,"开播时间: ".concat(start_time));
        Long click_num = liveEntity.getClick_num();
        String clickNumStr = NumberUtils.getNumber(context, click_num);
        Long pay_num = liveEntity.getPay_num();
        String payNumStr = NumberUtils.getNumber(context, pay_num);
        viewHolder.setText(R.id.item_history_live_mastersetprice_tv_duration
                ,"时长: ".concat(distanceTime)
                    .concat(" 观看人数: ").concat(clickNumStr)
                    .concat(" 成交量: ").concat(payNumStr));
    }
}
