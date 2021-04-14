package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.AppointmentinfoBean;
import com.huihe.base_lib.model.personal.CommentEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.emoji.ContainsEmojiEditText;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.ColorAndSizeTextUtil;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.KeyBoardUtils;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.TranslateTheme;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class CommentRVAdapter extends EmptyRVAdapter<CommentEntity> {

    private final LoginResultEntity loginResultEntity;

    public CommentRVAdapter(int layoutId, Context context, int layoutEmptyId) {
        super(layoutId, context, layoutEmptyId);
        loginResultEntity = LoginHelper.getLoginInfo();
    }

    public CommentRVAdapter(int layoutId, Context context) {
        super(layoutId, context);
        loginResultEntity = LoginHelper.getLoginInfo();
    }

    @Override
    protected void convert(ViewHolder viewHolder, CommentEntity commentEntity) {

    }

    @Override
    protected void convert(ViewHolder viewHolder1, CommentEntity commentEntity, final int position) {
        viewHolder1.setText(R.id.item_master_comment_tv_time,
                DateUtils.convertTimeToFormat(DateUtils.stringToLong(commentEntity.getUpdate_time(), DateUtils.YMDHMSFormatStr)));
        String average_score = commentEntity.average_score;
        viewHolder1.setText(R.id.item_master_comment_tv_pingfen, average_score);
        viewHolder1.setText(R.id.item_master_comment_tv_content, commentEntity.getContent());
        try {
            viewHolder1.setRating(R.id.item_master_comment_rb_pingfen, Float.valueOf(average_score));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        CommentEntity.Map map = commentEntity.getMap();
        if (map != null) {
            final UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                viewHolder1.setText(R.id.item_master_comment_tv_nick, userinfo.getNick_name());
                GlideTools.loadImage(context, userinfo.getAvatar(), (CircleImageView) viewHolder1.getView(R.id.item_master_comment_iv_head));
            }
        }

        String photo_url = commentEntity.photo_url;
        if (!TextUtils.isEmpty(photo_url)) {
            List<String> pics = ConvertUtils.toList(photo_url.split(","));
            RecyclerViewFixed rvPics = viewHolder1.getView(R.id.item_master_comment_rv_pics);
            initPicsRvAdapter(rvPics, pics);
        }
    }

    private void initPicsRvAdapter(RecyclerViewFixed rvPics, List<String> pics) {
        rvPics.setScrollingEnabled(false);
        rvPics.setVertical(3);
        rvPics.addGridViewItemDecoration(3, DensityUtils.dp2px(context, 12));
        rvPics.setAdapter(new CommentPicRvAdapter(R.layout.item_mechanism_course_comment_pic, context, pics));
    }

}
