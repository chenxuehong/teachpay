package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.glide.GlideTools;

public class TeachPayMechanismTeacherListRvAdapter extends EmptyRVAdapter<MasterInfoHomeModel.MasterInfoHomeEntity> {
    public TeachPayMechanismTeacherListRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MasterInfoHomeModel.MasterInfoHomeEntity masterInfoHomeEntity, int position) {
        String photo = masterInfoHomeEntity.getPhoto();
        String full_name = masterInfoHomeEntity.getFull_name();
        String mobile = masterInfoHomeEntity.getMobile();
        CircleImageView ivHead = viewHolder.getView(R.id.item_teachpay_mechanism_teacher_iv_head);
        GlideTools.loadImage(context, photo, ivHead);
        if (TextUtils.isEmpty(full_name)) {
            full_name = "";
        }
        viewHolder.setText(R.id.item_teachpay_mechanism_teacher_tv_tel, mobile);
        String status = masterInfoHomeEntity.getStatus();
        if ("4".equals(status)) {
            viewHolder.setText(R.id.item_teachpay_mechanism_teacher_tv_nickName, full_name.concat("(已离职)"));
        } else {
            viewHolder.setText(R.id.item_teachpay_mechanism_teacher_tv_nickName, full_name);
        }
    }
}
