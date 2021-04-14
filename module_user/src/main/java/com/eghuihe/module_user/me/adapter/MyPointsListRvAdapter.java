package com.eghuihe.module_user.me.adapter;

import android.content.Context;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.MyPointsEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class MyPointsListRvAdapter extends EmptyRVAdapter<MyPointsEntity> {
    public MyPointsListRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MyPointsEntity myPointsEntity, int position) {

        if ("sign".equals(myPointsEntity.type)) {
            viewHolder.setText(R.id.item_my_points_tv_title, "签到");
        } else {
            viewHolder.setText(R.id.item_my_points_tv_title, "购买课程");
        }
        viewHolder.setText(R.id.item_my_points_tv_amount, myPointsEntity.point);
        viewHolder.setText(R.id.item_my_points_tv_date, myPointsEntity.create_time);
    }
}
