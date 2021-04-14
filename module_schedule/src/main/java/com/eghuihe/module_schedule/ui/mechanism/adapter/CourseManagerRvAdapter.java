package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseManagerRvAdapter extends EmptyRVAdapter<MechanismClassEntity> {

    private OnListener onListener;

    private List<MechanismClassEntity> mechanismClassEntities;
    private boolean isShow;

    public CourseManagerRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
        isShow = false;
        mechanismClassEntities = new ArrayList<>();
    }

    @Override
    protected void convert(ViewHolder viewHolder, MechanismClassEntity mechanismClassEntity, int position) {
        String name = mechanismClassEntity.getName();
        viewHolder.setText(R.id.item_class_tv_className, name);
        String student_count = mechanismClassEntity.getStudent_count();
        student_count = TextUtils.isEmpty(student_count) ? "0" : student_count;
        viewHolder.setText(R.id.item_class_tv_studentNum, student_count);
        viewHolder.setText(R.id.item_class_tv_status, "未排课");
        TextView tvArrange = viewHolder.getView(R.id.item_class_tv_arrange);
        tvArrange.setText("排课");
        tvArrange.setTextColor(context.getResources().getColor(R.color.white));
        tvArrange.setBackgroundResource(R.drawable.shape_bg_radius_15_color_maincolor);
        viewHolder.setVisible(R.id.item_class_tv_time, false);
        final boolean is_scheduling_over = mechanismClassEntity.isIs_scheduling_over();
        CheckBox checkBox = viewHolder.getView(R.id.item_class_cbox);
        viewHolder.setVisible(R.id.item_class_cbox, isShow);
        checkBox.setChecked(containerClass(mechanismClassEntity.getId()));
        MechanismClassEntity.Map map = mechanismClassEntity.getMap();
        if (map != null) {
            String lessonCount = map.getLessonCount();
            lessonCount = TextUtils.isEmpty(lessonCount) ? "0" : lessonCount;
            String endLessonCount = map.getEndLessonCount();
            String needLessonCount = map.getNeedLessonCount();
            endLessonCount = TextUtils.isEmpty(endLessonCount) ? "0" : endLessonCount;
            needLessonCount = TextUtils.isEmpty(needLessonCount) ? "0" : needLessonCount;
            String beginTime = map.getBeginTime();
            viewHolder.setText(R.id.item_class_tv_time, beginTime);
            if (TextUtils.isEmpty(beginTime)) {
                viewHolder.setVisible(R.id.item_class_tv_time, false);
            } else {
                viewHolder.setVisible(R.id.item_class_tv_time, true);
            }
            if (is_scheduling_over) {
                // 已排课
                viewHolder.setText(R.id.item_class_tv_status,
                        "已上".concat(endLessonCount)
                                .concat("节|")
                                .concat("剩")
                                .concat(needLessonCount)
                                .concat("节"));
                tvArrange.setTextColor(context.getResources().getColor(R.color.mainColor));
                tvArrange.setBackgroundResource(R.drawable.shape_radius_15_stroke_dp_1_color_maincolor);
            }

            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                String title = masterSetPriceEntity.getTitle();
                viewHolder.setText(R.id.item_class_tv_title, title);
            }
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isShow) {
                    if (isChecked) {
                        if (isSameCourse(mechanismClassEntity)) {
                            // 同一个商品，可以选中
                            mechanismClassEntities.add(mechanismClassEntity);
                            if (onListener != null) {
                                onListener.onMergeClassList(mechanismClassEntities);
                            }
                        }else {
                            checkBox.setChecked(false);
                            ToastUtils.showShortToast(context, "所选班级商品不同，请核对后选择");
                        }

                    } else {
                        mechanismClassEntities.remove(mechanismClassEntity);
                        if (onListener != null) {
                            onListener.onMergeClassList(mechanismClassEntities);
                        }
                    }
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow) {
                    if (containerClass(mechanismClassEntity.getId())) {
                        checkBox.setChecked(false);
                    } else if (isSameCourse(mechanismClassEntity)) {
                        // 同一个商品，可以选中
                        checkBox.setChecked(true);
                    } else {
                        ToastUtils.showShortToast(context, "所选班级商品不同，请核对后选择");
                    }
                }
            }
        });
    }

    private boolean isSameCourse(MechanismClassEntity mechanismClassEntity) {
        String id = "-1";
        MechanismClassEntity.Map map1 = mechanismClassEntity.getMap();
        if (map1 != null) {
            MasterSetPriceEntity masterSetPriceEntity = map1.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                id = masterSetPriceEntity.getId();
            }
        }
        if (mechanismClassEntities != null && mechanismClassEntities.size() > 0) {
            for (int i = 0; i < mechanismClassEntities.size(); i++) {
                MechanismClassEntity item = mechanismClassEntities.get(i);
                MechanismClassEntity.Map map = item.getMap();
                if (map != null) {
                    MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
                    if (masterSetPriceEntity != null) {
                        return masterSetPriceEntity.getId().equals(id);
                    }
                }
            }
        } else if (mechanismClassEntities == null || mechanismClassEntities.size() == 0) {
            return true;
        }
        return false;
    }

    private boolean containerClass(String id) {
        if (mechanismClassEntities != null) {
            for (int i = 0; i < mechanismClassEntities.size(); i++) {
                MechanismClassEntity mechanismClassEntity = mechanismClassEntities.get(i);
                if (mechanismClassEntity != null) {
                    return id.equals(mechanismClassEntity.getId());
                }
            }
        }
        return false;
    }

    public void showMerge(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    public interface OnListener {
        void onMergeClassList(List<MechanismClassEntity> mechanismClassEntities);
    }
}
