package com.eghuihe.module_schedule.ui.student.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.student.Bean.SchedulePackageDetailBean;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;

public class TeachPayStudentSchedulePackageDetailListRvAdapter extends EmptyRVAdapter<SchedulePackageDetailBean> {

    private OnAppointmentListener onAppointmentListener;

    public TeachPayStudentSchedulePackageDetailListRvAdapter(int layoutId, Context context, OnAppointmentListener onAppointmentListener) {
        super(layoutId, context);
        this.onAppointmentListener = onAppointmentListener;
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final SchedulePackageDetailBean schedulePackageDetailBean, int position) {
        viewHolder.setText(R.id.item_teachpay_student_schedule_package_detail_tv_title, "课题：".concat(schedulePackageDetailBean.getTitle()));
        viewHolder.setText(R.id.item_teachpay_student_schedule_package_detail_tv_appointment, "预约");
        viewHolder.setText(R.id.item_teachpay_student_schedule_package_detail_tv_status, "暂无上课记录");
        viewHolder.setVisible(R.id.item_teachpay_student_schedule_package_detail_tv_appointment,
                !"scheduling".equals(schedulePackageDetailBean.appointment_type));
        StudentScheduleModel.StudentScheduleEntity studentScheduleEntity = schedulePackageDetailBean.getStudentScheduleEntity();
        if (studentScheduleEntity != null) {
            viewHolder.setVisible(R.id.item_teachpay_student_schedule_package_detail_tv_appointment,
                    false);
            StudentScheduleModel.StudentScheduleEntity.Map map = studentScheduleEntity.getMap();
            if (map != null) {
                MechanismOfflineScheduleEntity masterAppointment = map.getMasterAppointment();
                if (masterAppointment != null) {
                    String title = masterAppointment.getTitle();
                    String start_time = masterAppointment.getStart_time();
                    if (!TextUtils.isEmpty(title)) {
                        viewHolder.setText(R.id.item_teachpay_student_schedule_package_detail_tv_status,
                                title.concat("  ").concat(start_time));
                    }
                }
            }
        }
        viewHolder.setOnClickListener(R.id.item_teachpay_student_schedule_package_detail_tv_appointment, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 预约
                if (onAppointmentListener != null) {
                    onAppointmentListener.onAppointmentClicked(viewHolder, schedulePackageDetailBean);
                }
            }
        });

    }

    public interface OnAppointmentListener {
        void onAppointmentClicked(ViewHolder viewHolder, SchedulePackageDetailBean schedulePackageDetailBean);
    }
}
