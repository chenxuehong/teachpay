package com.eghuihe.module_dynamic.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eghuihe.module_dynamic.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.SubCommentBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.BaiDuTransUtils;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.amin.AnimationUtils;
import com.huihe.base_lib.utils.amin.iml.AbsAnimationListener;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class SubCommentRvAdapter extends CommonRVAdapter<SubCommentBean> {

    private final LoginResultEntity loginInfo;

    private DynamicDetailCommentRvAdapter.OnListener onListener;
    private CustomPopupWindow commentInputDialog;
    private CustomPopupWindow deleteCommentPopupWindow;
    private String noteId;

    public SubCommentRvAdapter(
            int layoutId,
            Context context,
            List<SubCommentBean> data,
            DynamicDetailCommentRvAdapter.OnListener onListener,
            String noteId) {
        super(layoutId, context, data);
        loginInfo = LoginHelper.getLoginInfo();
        this.onListener = onListener;
        this.noteId = noteId;
    }

    @Override
    protected void covert(ViewHolder viewHolder, SubCommentBean subCommentBean, int position) {
        final SubCommentBean.MapBean map = subCommentBean.getMap();
        final UserInfoEntity userinfo = map.getUserinfo();
        final UserInfoEntity replyinfo = map.getReplyinfo();
        if (userinfo != null)
            GlideTools.loadImage(context, userinfo.getAvatar(), (CircleImageView) viewHolder.getView(R.id.item_dynamic_child_comment_iv_head));
        if (userinfo != null && replyinfo != null) {
            viewHolder.setText(R.id.item_dynamic_child_comment_tv_reply_title,
                    userinfo.getNick_name()
                            .concat(" ")
                            .concat(context.getResources().getString(R.string.reply))
                            .concat(" ")
                            .concat(replyinfo.getNick_name()));
        }
        viewHolder.setText(R.id.item_dynamic_child_comment_tv_reply_content, subCommentBean.getContent());
        if (!TextUtils.isEmpty(subCommentBean.getCreate_time())) {
            long time = DateUtils.stringToLong(subCommentBean.getCreate_time(), DateUtils.YMDHMSFormatStr);
            viewHolder.setText(R.id.item_dynamic_child_comment_tv_reply_time, DateUtils.convertTimeToFormat(time));
        }

        viewHolder.setText(R.id.item_dynamic_child_comment_tv_reply_loveNum, String.valueOf(subCommentBean.getLike_count()));
        final TextView tvLikeCount = viewHolder.getView(R.id.item_dynamic_child_comment_tv_reply_loveNum);
        Integer like_count = subCommentBean.getLike_count();
        tvLikeCount.setText(String.valueOf(like_count));
        bindChildCommentLike(viewHolder, subCommentBean);
        viewHolder.setOnClickListener(R.id.item_dynamic_child_comment_ll_reply_love, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点赞
                if (onListener != null) {
                    onListener.onInsertLike(subCommentBean.getId());
                }
            }
        });
        viewHolder.setOnClickListener(R.id.item_dynamic_child_comment_iv_translate, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 翻译
                if (viewHolder.getView(R.id.item_dynamic_child_comment_tv_trans_content).getVisibility() == View.VISIBLE) {
                    viewHolder.getView(R.id.item_dynamic_child_comment_tv_trans_content).setVisibility(View.GONE);
                } else {
                    BaiDuTransUtils.transContent(subCommentBean.getContent(), LoginHelper.getLoginInfo().getUserInfoEntity().getMother_tongue(), new BaiDuTransUtils.OnTranslateListener() {
                        @Override
                        public void onSuccess(String content) {
                            viewHolder.setText(R.id.item_dynamic_child_comment_tv_trans_content, content);
                            viewHolder.getView(R.id.item_dynamic_child_comment_tv_trans_content).setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 子评论
                if (!LoginHelper.isMySelf(userinfo.getUser_id())) {
                    // 添加子评论
                    showInsertChildCommentDialog(subCommentBean);
                } else {
                    // 删除该评论
                    showDeleteCommentDialog(subCommentBean, position);
                }
            }
        });
    }

    private void bindChildCommentLike(final ViewHolder viewHolder, SubCommentBean subCommentBean) {

        UserServiceImpl.queryHistory(loginInfo.getUserToken(), loginInfo.getUserInfoEntity().getUser_id(), subCommentBean.getId(),
                "like", "notecomment", new NetworkSubscriber<LikeInfoModel>(null) {

                    @Override
                    protected void onSuccess(LikeInfoModel likeInfoModel) {

                        List<LikeInfoModel.LikeInfoEntity> likeInfoEntities = likeInfoModel.getData();
                        boolean add = false;
                        if (likeInfoEntities != null && likeInfoEntities.size() > 0) {
                            add = true;
                        } else {
                            add = false;
                        }
                        boolean finalAdd = add;
                        final ImageView imageView = viewHolder.getView(R.id.item_dynamic_child_comment_iv_reply_love);
                        AnimationUtils.startScaleAnimation(imageView, 1f, 0.5f, 100, 0, new AbsAnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                if (finalAdd) {
                                    imageView.setImageResource(R.mipmap.love_is_friend);
                                } else {
                                    imageView.setImageResource(R.mipmap.gray_love);
                                }
                            }
                        });
                    }
                });
    }


    private void showInsertChildCommentDialog(final SubCommentBean noteCommentEntity) {
        SubCommentBean.MapBean map = noteCommentEntity.getMap();
        if (map != null) {
            final UserInfoEntity userinfo = map.getUserinfo();
            final UserInfoEntity replyinfo = map.getReplyinfo();
            if (userinfo != null) {
                String hint = context.getResources().getString(R.string.reply).concat(" ")
                        .concat(TextUtils.isEmpty(userinfo.getNick_name()) ? userinfo.getUser_id() : userinfo.getNick_name());
                View rootview = ((BaseActivity) context).getWindow().getDecorView().getRootView();
                commentInputDialog = PopWindowUtils.popInputTextWindow(context, hint, rootview, new PopWindowUtils.OnInputListener() {

                    @Override
                    public void okClicked(String content) {
                        commentInputDialog.dismiss();
                        if (onListener != null) {
                            onListener.onInsertChildComment(
                                    LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                    true,
                                    userinfo.getUser_id(),
                                    noteCommentEntity.getId(),
                                    noteId,
                                    content);
                        }
                    }
                });
            }
        }

    }

    private void showDeleteCommentDialog(final SubCommentBean noteCommentEntity, final int position) {
        View rootview = ((BaseActivity) context).getWindow().getDecorView().getRootView();
        deleteCommentPopupWindow = PopWindowUtils.popBottomDialog(rootview, context, R.layout.select_delete, true);
        deleteCommentPopupWindow.setOnClickListener(R.id.select_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCommentPopupWindow.dismiss();
                if (onListener != null) {
                    onListener.onDeleteComment(noteCommentEntity.getId(), position);
                }
            }
        });
        deleteCommentPopupWindow.setOnClickListener(R.id.delete_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCommentPopupWindow.dismiss();
            }
        });
    }

    public void closeDialog(){
        try {
            if (commentInputDialog != null) {
                commentInputDialog.dismiss();
            }
            if (deleteCommentPopupWindow != null) {
                deleteCommentPopupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
