package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class CourseSelectRvAdapter extends CommonRVAdapter<MasterSetPriceEntity> {

    private int lastSelectedIndex;
    private OnListener onListener;

    public CourseSelectRvAdapter(int layoutId, Context context, List<MasterSetPriceEntity> data, OnListener onListener) {
        super(layoutId, context, data);
        lastSelectedIndex = 0;
        this.onListener = onListener;
    }

    @Override
    protected void covert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity,final int position) {
        String title = masterSetPriceEntity.getTitle();
        viewHolder.setText(R.id.item_course_select_tv_title, title);
        viewHolder.setVisible(R.id.item_course_select_view_line, getItemCount() - 1 != position);
        if (lastSelectedIndex == position) {
            viewHolder.setTextColor(R.id.item_course_select_tv_title,
                    context.getResources().getColor(R.color.mainColor));
        } else {
            viewHolder.setTextColor(R.id.item_course_select_tv_title,
                    context.getResources().getColor(R.color.color_333333));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    onListener.onItemCourseClicked(masterSetPriceEntity);
                }
                lastSelectedIndex = position;
            }
        });
    }

    public void resetStatus() {
        lastSelectedIndex = 0;
    }

    public interface OnListener {
        void onItemCourseClicked(MasterSetPriceEntity masterSetPriceEntity);
    }
}
