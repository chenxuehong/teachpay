package com.eghuihe.module_schedule.ui.student.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.UserAppointmentEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.glide.GlideTools;

public class TeachPayStudentSchedulePackageRvAdapter extends EmptyRVAdapter<StudentCoursePackageEntity> {
    private OnListener onListener;

    public TeachPayStudentSchedulePackageRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(final ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity, int position) {
        String user_group_id = studentCoursePackageEntity.getUser_group_id();
        if (TextUtils.isEmpty(user_group_id) || "0".equals(user_group_id)) {
            viewHolder.setText(R.id.item_teachpay_student_schedule_package_tv_pingtuan, "拼团");
        } else {
            viewHolder.setText(R.id.item_teachpay_student_schedule_package_tv_pingtuan, "查看拼团");
        }
        viewHolder.setVisible(R.id.item_teachpay_student_schedule_package_tv_pingtuan,studentCoursePackageEntity.getWhether_grouping());
        StudentCoursePackageEntity.Map map = studentCoursePackageEntity.getMap();
        viewHolder.setText(R.id.item_teachpay_student_schedule_package_tv_status, "暂无上课记录");
        viewHolder.setOnClickListener(R.id.item_teachpay_student_schedule_package_tv_appointment,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener != null) {
                            onListener.onGoAppointment(viewHolder,studentCoursePackageEntity);
                        }
                    }
                });
        if (map != null) {
            MasterMechanismModel.MasterMechanismEntity mechanismEntity = map.getMechanismEntity();
            if (mechanismEntity != null) {
                String mechanism_name = mechanismEntity.getMechanism_name();
                String mechanism_logo = mechanismEntity.getMechanism_logo();
                viewHolder.setText(R.id.item_teachpay_student_schedule_package_tv_mechanism_name, mechanism_name);
                CircleImageView ivMechanismLogo = viewHolder.getView(R.id.item_teachpay_student_schedule_package_iv_mechanism_logo);
                GlideTools.loadImage(context, mechanism_logo, ivMechanismLogo);
            }
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                String title = masterSetPriceEntity.getTitle();
                viewHolder.setText(R.id.item_teachpay_student_schedule_package_tv_title, title);
            }
            UserAppointmentEntity userAppointmentEntity = map.getUserAppointmentEntity();
            if (userAppointmentEntity != null) {
                String title = userAppointmentEntity.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    String start_time = userAppointmentEntity.getStart_time();
                    viewHolder.setText(R.id.item_teachpay_student_schedule_package_tv_status, "最近上课:《".concat(title).concat("》  ").concat(start_time));
                }
            }
            viewHolder.setOnClickListener(R.id.item_teachpay_student_schedule_package_tv_pingtuan,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (onListener != null) {
                                if (TextUtils.isEmpty(user_group_id) || "0".equals(user_group_id)) {
                                    onListener.onPingtuan(viewHolder, studentCoursePackageEntity);
                                } else {
                                    onListener.onViewPingtuan(viewHolder, studentCoursePackageEntity);
                                }

                            }
                        }
                    });
        }

    }

    public interface OnListener {
        void onGoAppointment(ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity);
        void onPingtuan(ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity);

        void onViewPingtuan(ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity);
    }
}
