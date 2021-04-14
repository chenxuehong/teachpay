package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.ClassStudentBean;
import com.huihe.base_lib.model.UserAppointmentEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.glide.GlideTools;

public class StudentListRvAdapter extends EmptyRVAdapter<ClassStudentBean> {

    private OnListener onListener;

    public StudentListRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final ClassStudentBean classStudentBean, int position) {

        ClassStudentBean.Map map = classStudentBean.getMap();
        viewHolder.setText(R.id.item_class_student_tv_title, "");
        viewHolder.setText(R.id.item_class_student_tv_status, "未排课");
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                String nick_name = userinfo.getNick_name();
                viewHolder.setText(R.id.item_class_student_tv_nickName, nick_name);
                GlideTools.loadImage(context,userinfo.getAvatar(),(CircleImageView)viewHolder.getView(R.id.item_class_student_iv_head));
            }
            UserAppointmentEntity userAppointmentEntity = map.getUserAppointmentEntity();
            if (userAppointmentEntity != null) {
                String title = userAppointmentEntity.getTitle();
                String start_time = userAppointmentEntity.getStart_time();
                viewHolder.setText(R.id.item_class_student_tv_title, title);
                viewHolder.setText(R.id.item_class_student_tv_status, start_time);
            }
        }
        viewHolder.setOnClickListener(R.id.item_class_student_tv_chat,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener != null) {
                            onListener.onChat(classStudentBean);
                        }
                    }
                });
    }

    public interface OnListener {
        void onChat(ClassStudentBean classStudentBean);
    }
}
