package com.eghuihe.module_schedule.ui.student.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.PinnedHeaderAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.glide.GlideTools;

import java.util.List;

public class StudentScheduleRvAdapter extends PinnedHeaderAdapter<StudentScheduleModel.StudentScheduleEntity> {

    private static final int VIEW_TYPE_ITEM_TIME = 0;
    private static final int VIEW_TYPE_ITEM_CONTENT = 1;
    private OnStatusListener onStatusListener;

    public StudentScheduleRvAdapter(int layoutId, Context context, OnStatusListener onStatusListener) {
        super(layoutId, context);
        this.onStatusListener = onStatusListener;
    }

    @Override
    public boolean isPinnedPosition(int position) {
        return getItemViewType(position) == VIEW_TYPE_ITEM_TIME;
    }

    @Override
    public void hideView(View pinnedHeaderView) {
        pinnedHeaderView.findViewById(R.id.item_student_schedule_timeline_ll).setVisibility(View.GONE);
    }

    @Override
    public int getItemViewType(int position) {

        List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities = getData();
        if (studentScheduleEntities == null || studentScheduleEntities.size() == 0) {

            return VIEW_TYPE_ITEM_CONTENT;
        }
        StudentScheduleModel.StudentScheduleEntity studentScheduleEntity = studentScheduleEntities.get(position);
        String start_time = studentScheduleEntity.getStart_time();
        String end_time = studentScheduleEntity.getEnd_time();
        start_time = DateUtils.translateZoneTimeStr(start_time, studentScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        end_time = DateUtils.translateZoneTimeStr(end_time, studentScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        String curYMDDate = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        if (position == 0) {
            // 显示title
            return VIEW_TYPE_ITEM_TIME;
        } else {
            StudentScheduleModel.StudentScheduleEntity lastItem = getData().get(position - 1);
            String lastItemStart_time = lastItem.getStart_time();
            lastItemStart_time = DateUtils.translateZoneTimeStr(lastItemStart_time, lastItem.getOffset(),
                    DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
            String LastYMDate = DateUtils.getOtherDateStr(lastItemStart_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
            if (curYMDDate.equals(LastYMDate)) {
                // 隐藏title
                return VIEW_TYPE_ITEM_CONTENT;
            } else {
                // 显示title
                return VIEW_TYPE_ITEM_TIME;
            }
        }
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final StudentScheduleModel.StudentScheduleEntity studentScheduleEntity, int position) {
        StudentScheduleModel.StudentScheduleEntity.Map map = studentScheduleEntity.getMap();
        if (getItemViewType(position) == VIEW_TYPE_ITEM_CONTENT) {
            viewHolder.setVisible(R.id.item_student_schedule_timeline_ll_timetitle, false);
        } else {
            viewHolder.setVisible(R.id.item_student_schedule_timeline_ll_timetitle, true);
        }
        if (map != null) {
            final MasterMechanismModel.MasterMechanismEntity mechanismEntity = map.getMechanismEntity();
            MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity = map.getMasterAppointment();
            if (mechanismEntity != null) {
                // 上课机构信息
                CircleImageView ivHead = viewHolder.getView(R.id.item_student_schedule_iv_head);
                GlideTools.loadImage(context, mechanismEntity.getMechanism_logo(), ivHead);
                viewHolder.setText(R.id.item_student_schedule_tv_nickName, mechanismEntity.getMechanism_name());
                viewHolder.setOnClickListener(R.id.item_student_schedule_tv_nickName,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String mechanism_id = mechanismEntity.getId();
                                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL, mechanism_id));
                            }
                        });
            }
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                viewHolder.setText(R.id.item_student_schedule_tv_title, masterSetPriceEntity.getTitle());
            }
            if (mechanismOfflineScheduleEntity != null) {
                // 课程信息
                String classroom = mechanismOfflineScheduleEntity.getClassroom();

                viewHolder.setText(R.id.item_student_schedule_tv_classroom, "上课地点:".concat(classroom));
            }
            String title = studentScheduleEntity.getTitle();
            viewHolder.setText(R.id.item_student_schedule_tv_childTitle, title);
        }

        // 判断是否显示今日
        boolean isToday = false;
        String start_time = studentScheduleEntity.getStart_time();
        if (!TextUtils.isEmpty(start_time)) {
            long time = DateUtils.stringToLong(start_time, DateUtils.YMDHMSFormatStr);
            isToday = DateUtils.isToday(time);
        }
        viewHolder.setVisible(R.id.item_student_schedule_tv_isToday, isToday);
        String end_time = studentScheduleEntity.getEnd_time();
        start_time = DateUtils.translateZoneTimeStr(start_time, studentScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        end_time = DateUtils.translateZoneTimeStr(end_time, studentScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        String startHM = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
        String end_HM = DateUtils.getOtherDateStr(end_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
        String week = DateUtils.getWeekOrTodayORYesterdayOrBeforeday(context, start_time, DateUtils.YMDHMSFormatStr);
        String ymd = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        viewHolder.setText(R.id.item_student_schedule_timeline_tv_timetitle, week);
        viewHolder.setText(R.id.item_student_schedule_timeline_tv_date, ymd);
        viewHolder.setText(R.id.item_student_schedule_timeline_tv_duration, startHM.concat("\n-\n").concat(end_HM));

        Integer status = studentScheduleEntity.getStatus();
        Boolean is_comment = studentScheduleEntity.getIs_comment();
        viewHolder.setVisible(R.id.item_student_schedule_tv_cancel, false);
        if (status != null) {
            if (status == 2 && !is_comment) {
                // 签到
                viewHolder.setVisible(R.id.item_student_schedule_tv_cancel, true);
                viewHolder.setText(R.id.item_student_schedule_tv_sign, "签到");
            } else if (status == 3 && !is_comment) {
                // 待总结
                viewHolder.setText(R.id.item_student_schedule_tv_sign, "待总结");
            } else if (status == 8 && !is_comment) {
                // 待付款
                viewHolder.setText(R.id.item_student_schedule_tv_sign, "付款");
            } else if (status == 9 && !is_comment) {
                // 待评论
                viewHolder.setText(R.id.item_student_schedule_tv_sign, "评论");
            } else {
                viewHolder.setText(R.id.item_student_schedule_tv_sign, "查看总结");
                viewHolder.setText(R.id.item_student_schedule_tv_cancel, "查看评论");
                viewHolder.setVisible(R.id.item_student_schedule_tv_cancel, true);
            }
        }
        viewHolder.setOnClickListener(R.id.item_student_schedule_tv_sign,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (status == 2 && !is_comment) {
                            // 签到
                            if (onStatusListener != null) {
                                onStatusListener.onSignClicked(viewHolder, studentScheduleEntity);
                            }
                        } else if (status == 3 && !is_comment) {
                            // 待总结
                        } else if (status == 8 && !is_comment) {
                            // 付款
                            if (onStatusListener != null) {
                                onStatusListener.onPayClicked(viewHolder, studentScheduleEntity);
                            }
                        } else if (status == 9 && !is_comment) {
                            // 评论
                            if (onStatusListener != null) {
                                onStatusListener.onCommentClicked(viewHolder, studentScheduleEntity);
                            }
                        } else {
                            if (onStatusListener != null) {
                                onStatusListener.onViewSummaryClicked(viewHolder, studentScheduleEntity);
                            }
                        }
                    }
                });
        viewHolder.setOnClickListener(R.id.item_student_schedule_tv_cancel,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (status == 2 && !is_comment) {
                            // 取消
                            if (onStatusListener != null) {
                                onStatusListener.onCancelClicked(viewHolder, studentScheduleEntity);
                            }
                        } else if (status == 9 && is_comment) {
                            // 查看评论
                            if (onStatusListener != null) {
                                if (onStatusListener != null) {
                                    onStatusListener.onViewCommentClicked(viewHolder, studentScheduleEntity);
                                }
                            }
                        }
                    }
                });
    }

    public interface OnStatusListener {
        void onSignClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity);

        void onCancelClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity);

        void onPayClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity);

        void onCommentClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity);

        void onViewSummaryClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity);

        void onViewCommentClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity);
    }
}
