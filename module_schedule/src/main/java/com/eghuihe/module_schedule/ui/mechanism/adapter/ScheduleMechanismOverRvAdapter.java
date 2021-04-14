package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.PinnedHeaderAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.glide.GlideTools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleMechanismOverRvAdapter extends PinnedHeaderAdapter<MechanismOfflineScheduleEntity> {

    private static final int VIEW_TYPE_ITEM_TIME = 0;
    private static final int VIEW_TYPE_ITEM_CONTENT = 1;

    public ScheduleMechanismOverRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    public boolean isPinnedPosition(int position) {
        return getItemViewType(position) == VIEW_TYPE_ITEM_TIME;
    }

    @Override
    public void hideView(View pinnedHeaderView) {
        pinnedHeaderView.findViewById(R.id.item_mechanism_course_over_timeline_ll).setVisibility(View.GONE);
    }

    @Override
    public int getItemViewType(int position) {

        List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities = getData();
        if (mechanismOfflineScheduleEntities == null || mechanismOfflineScheduleEntities.size() == 0) {

            return VIEW_TYPE_ITEM_CONTENT;
        }
        MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity = mechanismOfflineScheduleEntities.get(position);
        String start_time = mechanismOfflineScheduleEntity.getStart_time();
        String end_time = mechanismOfflineScheduleEntity.getEnd_time();
        start_time = DateUtils.translateZoneTimeStr(start_time, mechanismOfflineScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        end_time = DateUtils.translateZoneTimeStr(end_time, mechanismOfflineScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        String curYMDate = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        if (position == 0) {
            // 显示title
            return VIEW_TYPE_ITEM_TIME;
        } else {
            MechanismOfflineScheduleEntity lastItem = getData().get(position - 1);
            String lastItemStart_time = lastItem.getStart_time();
            lastItemStart_time = DateUtils.translateZoneTimeStr(lastItemStart_time, lastItem.getOffset(),
                    DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
            String LastYMDate = DateUtils.getOtherDateStr(lastItemStart_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
            if (curYMDate.equals(LastYMDate)) {
                // 隐藏title
                return VIEW_TYPE_ITEM_CONTENT;
            } else {
                // 显示title
                return VIEW_TYPE_ITEM_TIME;
            }
        }
    }

    @Override
    protected void convert(ViewHolder viewHolder, MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity, int position) {

        if (getItemViewType(position) == VIEW_TYPE_ITEM_CONTENT) {
            viewHolder.setVisible(R.id.item_mechanism_course_over_timeline_ll_timetitle, false);
        } else {
            viewHolder.setVisible(R.id.item_mechanism_course_over_timeline_ll_timetitle, true);
        }

        MechanismOfflineScheduleEntity.Map map = mechanismOfflineScheduleEntity.getMap();
        if (map != null) {
            UserInfoEntity masterInfo = map.getMasterInfo();
            if (masterInfo != null) {
                // 上课老师信息
                CircleImageView ivHead = viewHolder.getView(R.id.item_mechanism_course_over_iv_head);
                GlideTools.loadImage(context, masterInfo.getAvatar(), ivHead);
                viewHolder.setText(R.id.item_mechanism_course_over_tv_nickName, masterInfo.getNick_name());
            }
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                viewHolder.setText(R.id.item_mechanism_course_over_tv_title, masterSetPriceEntity.getTitle());
            }

        }
        // 判断是否显示今日
        boolean isToday = false;
        String start_time = mechanismOfflineScheduleEntity.getStart_time();
        if (!TextUtils.isEmpty(start_time)) {
            long time = DateUtils.stringToLong(start_time, DateUtils.YMDHMSFormatStr);
            isToday = DateUtils.isToday(time);
        }
        viewHolder.setVisible(R.id.item_mechanism_course_over_tv_isToday, isToday);

        // 课程信息
        String title = mechanismOfflineScheduleEntity.getTitle();
        String classroom = mechanismOfflineScheduleEntity.getClassroom();
        viewHolder.setText(R.id.item_mechanism_course_over_tv_childTitle, title);
        viewHolder.setText(R.id.item_mechanism_course_over_tv_classroom, classroom);
        String end_time = mechanismOfflineScheduleEntity.getEnd_time();
        start_time = DateUtils.translateZoneTimeStr(start_time, mechanismOfflineScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        end_time = DateUtils.translateZoneTimeStr(end_time, mechanismOfflineScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
        String startHM = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
        String end_HM = DateUtils.getOtherDateStr(end_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
        String week = DateUtils.getWeekOrTodayORYesterdayOrBeforeday(context, start_time, DateUtils.YMDHMSFormatStr);
        String ymd = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        viewHolder.setText(R.id.item_mechanism_course_over_timeline_tv_timetitle, week);
        viewHolder.setText(R.id.item_mechanism_course_over_timeline_tv_month, ymd);
        viewHolder.setText(R.id.item_mechanism_course_over_timeline_tv_duration,
                startHM.concat("\n-\n").concat(end_HM));
        viewHolder.setOnClickListener(R.id.item_mechanism_course_over_tv_view_summary,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 看总结
                        Map<String,String> params = new HashMap<>();
                        params.put(ArgumentsConfig.KEY_APPOINTMENT_ID,mechanismOfflineScheduleEntity.getId());
                        ActivityToActivity.toActivity(ARouterConfig.SCHEDULE_SUMMARYDETAILACTIVITY,params);
                    }
                });
        viewHolder.setOnClickListener(R.id.item_mechanism_course_over_tv_view_comment,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 看评论
                        Map<String,String> params = new HashMap<>();
                        params.put(ArgumentsConfig.KEY_APPOINTMENT_ID,mechanismOfflineScheduleEntity.getId());
                        ActivityToActivity.toActivity(ARouterConfig.ME_MECHANISMCOURSECOMMENTLISTACTIVITY,params);
                    }
                });
    }
}
