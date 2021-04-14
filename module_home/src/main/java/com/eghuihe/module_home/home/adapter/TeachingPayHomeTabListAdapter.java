package com.eghuihe.module_home.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.model.personal.ItemViewBean;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

public class TeachingPayHomeTabListAdapter extends CommonRVAdapter<ItemViewBean> {

    public TeachingPayHomeTabListAdapter(int layoutId, Context context, List<ItemViewBean> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, ItemViewBean itemViewBean, int position) {

        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        layoutParams.width = DensityUtils.getScreenWidth(context) / 3 - DensityUtils.dp2px(context, 60);
        viewHolder.itemView.setLayoutParams(layoutParams);
        viewHolder.setImageResource(R.id.item_teaching_pay_home_tab_iv_icon, itemViewBean.iconResId);
        viewHolder.setText(R.id.item_teaching_pay_home_tab_tv_title, itemViewBean.title);
    }
}
