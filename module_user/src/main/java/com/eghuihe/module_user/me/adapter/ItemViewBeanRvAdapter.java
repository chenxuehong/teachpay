package com.eghuihe.module_user.me.adapter;

import android.content.Context;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.ItemBeanView;
import com.huihe.base_lib.model.personal.ItemViewBean;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class ItemViewBeanRvAdapter extends CommonRVAdapter<ItemViewBean> {
    public ItemViewBeanRvAdapter(int layoutId, Context context, List<ItemViewBean> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, ItemViewBean itemViewBean, int position) {

        ItemBeanView itemBeanView = viewHolder.getView(R.id.item_teaching_pay_me_itembeanview);
        itemBeanView.setTitle(itemViewBean.title);
        itemBeanView.setLeftIcon(itemViewBean.iconResId);
        if (position == 0) {
            itemBeanView.setBackgroundResource(R.drawable.shape_bg_radius_top_12_color_fff);
        } else if (position == getItemCount() - 1) {
            itemBeanView.setBackgroundResource(R.drawable.shape_bg_radius_bottom_12_color_fff);
        } else {
            itemBeanView.setBackgroundResource(R.drawable.shape_bg_fff);
        }
    }
}
