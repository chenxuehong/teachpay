package com.eghuihe.module_user.me.adapter;

import android.content.Context;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.UserGoldTypeEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class TaskRvAdapter extends EmptyRVAdapter<UserGoldTypeEntity> {
    public TaskRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, UserGoldTypeEntity userGoldTypeEntity, int position) {
        viewHolder.setText(R.id.item_reward_task_tv_title, userGoldTypeEntity.introduce);
        viewHolder.setText(R.id.item_reward_task_tv_content, userGoldTypeEntity.pic_path);
        viewHolder.setVisible(R.id.item_reward_task_view, position != getItemCount() - 1);
    }
}
