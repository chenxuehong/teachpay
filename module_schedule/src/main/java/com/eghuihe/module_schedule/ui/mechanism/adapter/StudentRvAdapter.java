package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.StudentBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class StudentRvAdapter extends EmptyRVAdapter<StudentBean> {
    List<StudentBean> checkedStudentList;

    public List<StudentBean> getCheckedStudentList() {
        return checkedStudentList;
    }

    public StudentRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
        checkedStudentList = new ArrayList<>();
    }

    @Override
    protected void convert(ViewHolder viewHolder, final StudentBean studentBean, int position) {
        final CheckBox cbStudent = viewHolder.getView(R.id.item_student_cb);
        RoundedImageView ivHead = viewHolder.getView(R.id.item_student_iv_head);
        StudentBean.Map map = studentBean.getMap();
        String original_course_num = studentBean.getOriginal_course_num();
        if (map != null) {
            UserInfoEntity userInfo = map.getUserInfo();
            if (userInfo != null) {
                String avatar = userInfo.getAvatar();
                String nick_name = userInfo.getNick_name();
                viewHolder.setText(R.id.item_student_tv_nickName, nick_name);
                GlideTools.loadImage(context, avatar, ivHead);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbStudent.setChecked(!cbStudent.isChecked());
            }
        });

        cbStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!cbStudent.isChecked()) {
                    checkedStudentList.remove(studentBean);
                } else {
                    checkedStudentList.add(studentBean);
                }
            }
        });
        viewHolder.setText(R.id.item_student_tv_courseNum, "剩余课程:".concat(original_course_num));
    }

}
