package com.eghuihe.module_dynamic.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;

import com.eghuihe.module_dynamic.R;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.SubCommentBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.BaiDuTransUtils;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class DynamicDetailCommentRvAdapter extends EmptyRVAdapter<NoteCommentDetailListModel.NoteCommentEntity> {

    private String noteId;
    private final LoginResultEntity loginInfo;
    private CustomPopupWindow commentInputDialog;
    private CustomPopupWindow deleteCommentPopupWindow;
    private OnListener onListener;
    private SubCommentRvAdapter subCommentRvAdapter;

    public DynamicDetailCommentRvAdapter(int layoutId, Context context, String noteId, OnListener onListener) {
        super(layoutId, context);
        loginInfo = LoginHelper.getLoginInfo();
        this.noteId = noteId;
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, NoteCommentDetailListModel.NoteCommentEntity noteCommentEntity, int position) {
        viewHolder.setText(R.id.item_dynamic_comment_tv_time,
                DateUtils.convertTimeToFormat(DateUtils.stringToLong(noteCommentEntity.getCreate_time(), DateUtils.YMDHMSFormatStr)));
        final TextView tvLikeCount = viewHolder.getView(R.id.item_dynamic_comment_tv_likeCount);
        tvLikeCount.setText(String.valueOf(noteCommentEntity.getLike_count()));
        viewHolder.setText(R.id.item_dynamic_comment_tv_content, noteCommentEntity.getContent());

        viewHolder.setOnClickListener(R.id.item_dynamic_comment_iv_translate, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 翻译
                if (viewHolder.getView(R.id.item_dynamic_comment_tv_trans_content).getVisibility() == View.VISIBLE) {
                    viewHolder.getView(R.id.item_dynamic_comment_tv_trans_content).setVisibility(View.GONE);
                } else {
                    BaiDuTransUtils.transContent(noteCommentEntity.getContent(), LoginHelper.getLoginInfo().getUserInfoEntity().getMother_tongue(),
                            new BaiDuTransUtils.OnTranslateListener() {
                                @Override
                                public void onSuccess(String content) {
                                    viewHolder.setText(R.id.item_dynamic_comment_tv_trans_content, content);
                                    viewHolder.getView(R.id.item_dynamic_comment_tv_trans_content).setVisibility(View.VISIBLE);
                                }
                            });
                }
            }
        });

        bindChildCommentList(viewHolder, noteCommentEntity);
        viewHolder.setOnClickListener(R.id.item_dynamic_comment_ll_like, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 点赞
                if (onListener != null) {
                    onListener.onInsertLike(noteCommentEntity.getId());
                }
            }
        });

        if (noteCommentEntity != null && noteCommentEntity.getMap() != null) {
            UserInfoEntity userinfo = noteCommentEntity.getMap().getUserinfo();
            if (userinfo != null) {
                viewHolder.setText(R.id.item_dynamic_comment_tv_nickName, userinfo.getNick_name());
                GlideTools.loadImage(context, userinfo.getAvatar(), (CircleImageView) viewHolder.getView(R.id.item_dynamic_comment_iv_head));
                Integer sex = userinfo.getSex();
                viewHolder.setImageResource(R.id.item_dynamic_comment_iv_sex, sex == 1 ? R.mipmap.xingbie_nan : R.mipmap.xingbie_nv);
                GlideTools.loadImage(context, userinfo.getNational_flag(), (CircleImageView) viewHolder.getView(R.id.item_dynamic_comment_iv_national_flag));
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!LoginHelper.isMySelf(userinfo.getUser_id())) {
                            // 添加子评论
                            showInsertChildCommentDialog(noteCommentEntity);
                        } else {
                            // 删除该评论
                            showDeleteCommentDialog(noteCommentEntity, position);
                        }
                    }
                });

            }
        }
    }

    private void bindChildCommentList(ViewHolder viewHolder, final NoteCommentDetailListModel.NoteCommentEntity noteCommentEntity) {
        final List<SubCommentBean> subComment = noteCommentEntity.getMap().getSubComment();
        final RecyclerViewFixed recyclerViewFixed = viewHolder.getView(R.id.item_dynamic_comment_child_rv);
        recyclerViewFixed.setVertical(1);
        recyclerViewFixed.setNestedScrollingEnabled(false);
        recyclerViewFixed.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFixed.addGridViewItemDecoration(1, 30);
        if (subComment != null && subComment.size() == 0) {
            viewHolder.setVisible(R.id.item_dynamic_comment_rl_child_comment_list, false);
        } else {
            viewHolder.setVisible(R.id.item_dynamic_comment_rl_child_comment_list, true);
        }
        subCommentRvAdapter = new SubCommentRvAdapter(R.layout.item_dynamic_child_comment, context, subComment, onListener, noteId);
        recyclerViewFixed.setAdapter(subCommentRvAdapter);
    }

    private void showInsertChildCommentDialog(final NoteCommentDetailListModel.NoteCommentEntity noteCommentEntity) {
        NoteCommentDetailListModel.NoteCommentEntity.MapBeanX map = noteCommentEntity.getMap();
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                String hint = context.getResources().getString(R.string.reply)
                        .concat(" ")
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

    private void showDeleteCommentDialog(final NoteCommentDetailListModel.NoteCommentEntity noteCommentEntity, final int position) {
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

    public interface OnListener {
        void onInsertChildComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content);

        void onDeleteComment(String id, int position);

        void onInsertLike(String history_id);
    }

    public void closeDialog() {
        try {
            if (commentInputDialog != null) {
                commentInputDialog.dismiss();
            }
            if (deleteCommentPopupWindow != null) {
                deleteCommentPopupWindow.dismiss();
            }
            if (subCommentRvAdapter != null) {
                subCommentRvAdapter.closeDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
