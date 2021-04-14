package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class SelectClassListRvAdapter extends EmptyRVAdapter<MechanismClassEntity> {
    private OnListener onListener;

    public SelectClassListRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, MechanismClassEntity mechanismClassEntity, int position) {
        String name = mechanismClassEntity.getName();
        TextView tvClassName = viewHolder.getView(R.id.item_class_select_tv_className);
        tvClassName.setText(name);
        tvClassName.setBackgroundResource(R.drawable.shape_class_name);
        String student_count = mechanismClassEntity.getStudent_count();
        student_count = TextUtils.isEmpty(student_count) ? "0" : student_count;
        viewHolder.setText(R.id.item_class_select_tv_studentNum, student_count);
        viewHolder.setText(R.id.item_class_select_tv_status, "未排课");
        TextView tvArrange = viewHolder.getView(R.id.item_class_select_tv_arrange);
        tvArrange.setText("排课");
        tvArrange.setTextColor(context.getResources().getColor(R.color.white));
        tvArrange.setBackgroundResource(R.drawable.shape_bg_radius_15_color_maincolor);
        viewHolder.setVisible(R.id.item_class_select_tv_time, false);
        MechanismClassEntity.Map map = mechanismClassEntity.getMap();
        final boolean is_scheduling_over = mechanismClassEntity.isIs_scheduling_over();
        if (map != null) {
            String lessonCount = map.getLessonCount();
            String endLessonCount = map.getEndLessonCount();
            String needLessonCount = map.getNeedLessonCount();
            lessonCount = TextUtils.isEmpty(lessonCount) ? "0" : lessonCount;
            endLessonCount = TextUtils.isEmpty(endLessonCount) ? "0" : endLessonCount;
            needLessonCount = TextUtils.isEmpty(needLessonCount) ? "0" : needLessonCount;
            String beginTime = map.getBeginTime();
            viewHolder.setText(R.id.item_class_select_tv_time, beginTime);

            if (TextUtils.isEmpty(beginTime)) {
                viewHolder.setVisible(R.id.item_class_select_tv_time, false);
            } else {
                viewHolder.setVisible(R.id.item_class_select_tv_time, true);
            }

            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (is_scheduling_over) {
                tvArrange.setText("已排课");
                tvClassName.setBackgroundResource(R.drawable.shape_class_name2);
                tvArrange.setBackgroundResource(R.drawable.shape_bg_radius_15_color_fff2f2f2);
                viewHolder.setText(R.id.item_class_select_tv_status,
                        "已上".concat(endLessonCount)
                                .concat("节|")
                                .concat("剩")
                                .concat(needLessonCount)
                                .concat("节"));
            }
            if (masterSetPriceEntity != null) {
                String title = masterSetPriceEntity.getTitle();
                viewHolder.setText(R.id.item_class_select_tv_title, title);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!is_scheduling_over) {
                    if (onListener != null) {
                        onListener.onArranged(mechanismClassEntity);
                    }
                }
            }
        });
    }

    public interface OnListener {
        void onArranged(MechanismClassEntity mechanismClassEntity);
    }
}
