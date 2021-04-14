package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.TeachPaypalDetailEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DateUtils;

import java.util.List;

public class SignDayRvAdapter extends CommonRVAdapter<TeachPaypalDetailEntity.GoldEntity> {
    private String singInDay;

    public SignDayRvAdapter(int layoutId, Context context, List<TeachPaypalDetailEntity.GoldEntity> data, String singInDay) {
        super(layoutId, context, data);
        this.singInDay = singInDay;
    }

    @Override
    protected void covert(ViewHolder viewHolder, TeachPaypalDetailEntity.GoldEntity goldEntity, int position) {
        viewHolder.setText(R.id.item_reward_sign_day_tv_title,
                "第".concat(String.valueOf(position + 1)).concat("天"));
        TextView tvScore = viewHolder.getView(R.id.item_reward_sign_day_tv_score);
        int signDay = ConvertUtils.toInt(singInDay);
        if (signDay != -1) {
            if (position < signDay) {
                tvScore.setText("");
                tvScore.setBackgroundResource(R.mipmap.reward_sign_bg);
            } else {
                String gold_num = ConvertUtils.trimZero(goldEntity.gold_num);
                tvScore.setText(String.valueOf(gold_num).concat("分"));
                tvScore.setBackgroundResource(R.mipmap.reward_unsign_bg);
            }
        }
        viewHolder.setVisible(R.id.item_reward_sign_day_view_line,
                position != getItemCount() - 1);
    }
}
