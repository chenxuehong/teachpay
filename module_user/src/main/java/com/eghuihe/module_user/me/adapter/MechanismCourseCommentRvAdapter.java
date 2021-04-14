package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.glide.GlideTools;

import java.util.List;

public class MechanismCourseCommentRvAdapter extends EmptyRVAdapter<MechanismCommentEntity> {
    public MechanismCourseCommentRvAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context,emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MechanismCommentEntity mechanismCommentEntity, int position) {
        MechanismCommentEntity.Map map = mechanismCommentEntity.getMap();
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                String avatar = userinfo.getAvatar();
                String nick_name = userinfo.getNick_name();
                viewHolder.setText(R.id.item_mechanism_course_comment_tv_nickName, nick_name);
                CircleImageView ivHead = viewHolder.getView(R.id.item_mechanism_course_comment_iv_head);
                GlideTools.loadImage(context, avatar, ivHead);
            }
        }
        String score = StringUtils.noIsNull(mechanismCommentEntity.getScore());
        String create_time = StringUtils.noIsNull(mechanismCommentEntity.getCreate_time());
        String content = StringUtils.noIsNull(mechanismCommentEntity.getContent());
        viewHolder.setText(R.id.item_mechanism_course_comment_tv_score, score.concat("分"));
        viewHolder.setText(R.id.item_mechanism_course_comment_tv_time, create_time);
        viewHolder.setText(R.id.item_mechanism_course_comment_tv_content, content);
        viewHolder.setVisible(R.id.item_mechanism_course_comment_tv_content, !TextUtils.isEmpty(content));

        String photo_url = mechanismCommentEntity.getPhoto_url();
        if (!TextUtils.isEmpty(photo_url)) {
            List<String> pics = ConvertUtils.toList(photo_url.split(","));
            RecyclerViewFixed rvPics = viewHolder.getView(R.id.item_mechanism_course_comment_rv_pics);
            initPicsRvAdapter(rvPics, pics);
        }

    }

    private void initPicsRvAdapter(RecyclerViewFixed rvPics, List<String> pics) {
        rvPics.setScrollingEnabled(false);
        rvPics.setVertical(3);
        rvPics.addGridViewItemDecoration(3, DensityUtils.dp2px(context, 12));
        rvPics.setAdapter(new CommentPicRvAdapter(R.layout.item_mechanism_course_comment_pic, context, pics));
    }

    @Override
    protected void convertEmptyView(ViewHolder viewHolder, int i) {
        viewHolder.setText(R.id.tv_no_data,"暂无评论");
    }
}
