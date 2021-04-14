package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.LiveEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class WaitingLiveListRvAdapter extends EmptyRVAdapter<LiveEntity> {

    private OnListener onListener;
    public WaitingLiveListRvAdapter(int layoutId, Context context,OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder,final LiveEntity liveEntity, int position) {

        viewHolder.setText(R.id.item_waiting_live_mastersetprice_tv_title, liveEntity.getTitle());
        String start_time = liveEntity.getStart_time();
        viewHolder.setText(R.id.item_waiting_live_mastersetprice_tv_time, start_time);
        viewHolder.setOnClickListener(R.id.item_waiting_live_mastersetprice_tv_live,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener!=null){
                            onListener.startLive(liveEntity);
                        }
                    }
                });
    }

    public interface OnListener {
        void startLive(LiveEntity liveEntity);
    }
}
