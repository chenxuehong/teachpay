package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.model.StudentBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.glide.GlideTools;

import java.util.List;

public class SelectedStudentListRvAdapter extends CommonRVAdapter<StudentBean> {
    public SelectedStudentListRvAdapter(int layoutId, Context context, List<StudentBean> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, StudentBean studentBean, int position) {
        StudentBean.Map map = studentBean.getMap();
        if (map != null) {
            UserInfoEntity userInfo = map.getUserInfo();
            if (userInfo != null) {
                String nick_name = userInfo.getNick_name();
                String avatar = userInfo.getAvatar();
                CircleImageView ivHead = viewHolder.getView(R.id.item_student_selected_iv_head);
                viewHolder.setText(R.id.item_student_selected_tv_nickName, nick_name);
                GlideTools.loadImage(context, avatar, ivHead);
            }
        }

    }
}
