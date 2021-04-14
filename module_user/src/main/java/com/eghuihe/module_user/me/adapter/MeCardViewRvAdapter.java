package com.eghuihe.module_user.me.adapter;

import android.content.Context;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.personal.ItemTvBean;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class MeCardViewRvAdapter extends CommonRVAdapter<ItemTvBean> {
    public MeCardViewRvAdapter(int layoutId, Context context, List<ItemTvBean> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, ItemTvBean itemTvBean, int position) {
        viewHolder.setText(R.id.item_student_me_cardview_tv_content,itemTvBean.content);
        viewHolder.setText(R.id.item_student_me_cardview_tv_title,itemTvBean.title);
    }
}
