package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.ClassStatusBean;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class ClassStatusRvAdapter extends CommonRVAdapter<ClassStatusBean> {

    private int lastSelectedIndex;
    private OnListener onListener;

    public ClassStatusRvAdapter(int layoutId, Context context, List<ClassStatusBean> data, OnListener onListener) {
        super(layoutId, context, data);
        lastSelectedIndex = 0;
        this.onListener = onListener;
    }

    @Override
    protected void covert(ViewHolder viewHolder, final ClassStatusBean classStatusBean,final int position) {
        viewHolder.setText(R.id.item_class_status_select_tv_title, classStatusBean.title);
        viewHolder.setVisible(R.id.item_class_status_select_view_line, getItemCount() - 1 != position);
        if (lastSelectedIndex == position) {
            viewHolder.setTextColor(R.id.item_class_status_select_tv_title,
                    context.getResources().getColor(R.color.mainColor));
        } else {
            viewHolder.setTextColor(R.id.item_class_status_select_tv_title,
                    context.getResources().getColor(R.color.color_333333));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    onListener.onItemClassStatusClicked(classStatusBean);
                }
                lastSelectedIndex = position;
            }
        });
    }

    public void resetStatus() {
        lastSelectedIndex = 0;
    }

    public interface OnListener {
        void onItemClassStatusClicked(ClassStatusBean classStatusBean);
    }
}
