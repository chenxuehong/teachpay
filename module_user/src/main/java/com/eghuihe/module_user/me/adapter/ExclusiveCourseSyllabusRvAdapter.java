package com.eghuihe.module_user.me.adapter;

import android.content.Context;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.RecordingCourseSyllabusEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class ExclusiveCourseSyllabusRvAdapter extends CommonRVAdapter<RecordingCourseSyllabusEntity> {

    public ExclusiveCourseSyllabusRvAdapter(int layoutId, Context context, List<RecordingCourseSyllabusEntity> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, RecordingCourseSyllabusEntity exclusiveCourseSyllabusEntity, int position) {
        viewHolder.setText(R.id.item_exclusive_course_detail_syllabus_tv_title, exclusiveCourseSyllabusEntity.title);
        viewHolder.setText(R.id.item_exclusive_course_detail_syllabus_tv_no,
                String.format(context.getResources().getString(R.string.section_param), String.valueOf(position + 1)));
    }
}
