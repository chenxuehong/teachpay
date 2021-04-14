package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.glide.GlideTools;

public class TeachPayMechanismTeacherRvAdapter extends EmptyRVAdapter<MasterInfoHomeModel.MasterInfoHomeEntity> {

    public TeachPayMechanismTeacherRvAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MasterInfoHomeModel.MasterInfoHomeEntity masterInfoEntity) {
        CircleImageView ivHead = viewHolder.getView(R.id.item_mechanism_teacher_iv_head);
        TextView tvType = viewHolder.getView(R.id.item_mechanism_teacher_tv_type);
        TextView tvNickName = viewHolder.getView(R.id.item_mechanism_teacher_tv_nickName);

        String avatar = masterInfoEntity.getPhoto();
        String nick_name = masterInfoEntity.getFull_name();
        tvNickName.setText(nick_name);
        GlideTools.loadImage(context, avatar, ivHead);
        String type = masterInfoEntity.getType();

    }
}
