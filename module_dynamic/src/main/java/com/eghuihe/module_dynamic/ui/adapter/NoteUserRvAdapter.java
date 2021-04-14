package com.eghuihe.module_dynamic.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.ctetin.expandabletextviewlibrary.app.LinkType;
import com.eghuihe.module_dynamic.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ActivityEntity;
import com.huihe.base_lib.model.LikeInfoEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.NoteCommentEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.DrawableLeftCenterTextView;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.BaiDuTransUtils;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.amin.AnimationUtils;
import com.huihe.base_lib.utils.amin.iml.AbsAnimationListener;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NoteUserRvAdapter extends EmptyRVAdapter<NoteUserEntity> {

    private OnListener onListener;

    public NoteUserRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final NoteUserEntity newsInformationInfoEntity, final int position) {

        viewHolder.setText(R.id.item_dynamic_tv_note_user_time,
                DateUtils.convertTimeToFormat(DateUtils.stringToLong(newsInformationInfoEntity.getCreate_time(),
                        DateUtils.YMDHMSFormatStr)));
        ExpandableTextView view = viewHolder.getView(R.id.item_dynamic_tv_note_user_content);
        view.setContent(newsInformationInfoEntity.getContent());
        NoteUserEntity.MapBean map = newsInformationInfoEntity.getMap();
        if (map != null) {
            bindUserInfo(viewHolder, newsInformationInfoEntity, map);
        }
        notifyItemData(viewHolder, newsInformationInfoEntity);
        initListener(viewHolder, newsInformationInfoEntity, position);
    }

    public void notifyItemData(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity) {
        // 动态图片列表
        initNotePictsRvAdapter(viewHolder, newsInformationInfoEntity);
        viewHolder.setText(R.id.item_dynamic_tv_note_user_share_count,
                String.valueOf(newsInformationInfoEntity.getShare_count()));
        // 点赞列表
        updateLikeInfoList(viewHolder, newsInformationInfoEntity);
        // 评论列表
        initCommentRvAdapter(viewHolder, newsInformationInfoEntity);
        bindCourseInfo(viewHolder, newsInformationInfoEntity);
        viewHolder.setVisible(R.id.item_dynamic_ll_collect_and_shield, false);
    }

    private void bindCourseInfo(ViewHolder viewHolder, final NoteUserEntity newsInformationInfoEntity) {

        RoundedImageView ivCourseCover = viewHolder.getView(R.id.include_course_info_iv_CourseCover);
        NoteUserEntity.MapBean map = newsInformationInfoEntity.getMap();
        viewHolder.setVisible(R.id.include_course_info_ll_courseinfo, false);
        viewHolder.setText(R.id.include_course_info_tv_amount,
                NumberUtils.transMoney("0.0").concat("/节"));
        if (map != null) {
            final MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                viewHolder.setOnClickListener(R.id.include_course_info_ll_courseinfo,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
                            }
                        });
                String face_url = masterSetPriceEntity.getFace_url();
                GlideTools.loadImage(context, face_url, ivCourseCover);
                String course_num = masterSetPriceEntity.getCourse_num();
                course_num = TextUtils.isEmpty(course_num) ? "1" : course_num;
                String title = masterSetPriceEntity.getTitle();
                viewHolder.setText(R.id.include_course_info_tv_title,
                        "【".concat(course_num).concat("节】").concat(title));

                ExpandableTextView expandableTextView = viewHolder.getView(R.id.item_dynamic_tv_note_user_content);
                final String categories = newsInformationInfoEntity.getClassfiy();
                if (!TextUtils.isEmpty(categories)) {
                    String content = "@".concat(categories).concat(" ").concat(newsInformationInfoEntity.getContent());
                    expandableTextView.setContent(content);
                    expandableTextView.setNeedLink(true);
                    expandableTextView.setLinkClickListener(new ExpandableTextView.OnLinkClickListener() {
                        @Override
                        public void onLinkClickListener(LinkType type, String content, String selfContent) {
                            if (onListener != null) {
                                onListener.viewCategories(newsInformationInfoEntity);
                            }
                        }
                    });
                }
                String discount_amout = masterSetPriceEntity.getDiscount_amout();
                String original_price = masterSetPriceEntity.getOriginal_price();
                try {
                    Boolean is_attend_activities = masterSetPriceEntity.getIs_attend_activities();
                    if (is_attend_activities!=null && is_attend_activities){
                        viewHolder.setText(R.id.include_course_info_tv_discount_amount,
                                NumberUtils.transMoney(discount_amout).concat("/节"));
                        viewHolder.setStrikeThrough(R.id.include_course_info_tv_amount,
                                NumberUtils.transMoney(original_price).concat("/节"));
                        viewHolder.setVisible(R.id.include_course_info_tv_amount, true);
                    }else {
                        viewHolder.setText(R.id.include_course_info_tv_discount_amount,
                                NumberUtils.transMoney(original_price).concat("/节"));
                        viewHolder.setVisible(R.id.include_course_info_tv_amount, false);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                viewHolder.setVisible(R.id.include_course_info_ll_courseinfo, true);
            }

        }

    }

    private void initNotePictsRvAdapter(ViewHolder viewHolder,final NoteUserEntity newsInformationInfoEntity) {
        final RecyclerViewFixed rvImgs = viewHolder.getView(R.id.item_dynamic_rv_note_user_imgs2);
        rvImgs.setVisibility(View.GONE);
        rvImgs.addGridViewItemDecoration(2, DensityUtils.dp2px(context, 6));
        viewHolder.setVisible(R.id.item_dynamic_tv_view_detail, false);
        if (newsInformationInfoEntity.getPicts() != null) {
            String pictsList = newsInformationInfoEntity.getPicts();
            if (pictsList != null) {
                String[] picts = pictsList.split(",");
                if (picts != null & picts.length == 1) {
                    if ("".equals(picts[0])) {
                        picts = null;
                    }
                }
                if (picts != null && picts.length > 0) {
                    List<String> picList = Arrays.asList(picts);
                    List<String> newPicList = new ArrayList();
                    if (picList.size() == 1) {
                        rvImgs.setVertical(1);
                        newPicList = picList;
                    } else if (picList.size() == 2) {
                        rvImgs.setVertical(2);
                        newPicList = picList;
                    } else if (picList.size() == 3) {
                        rvImgs.setVertical(2);
                        newPicList = picList.subList(0, 3);
                    } else if (picList.size() >= 4) {
                        rvImgs.setVertical(2);
                        newPicList = picList.subList(0, 4);
                    }

                    if (picList.size() > 4) {
                        viewHolder.setVisible(R.id.item_dynamic_tv_view_detail, true);
                        viewHolder.setOnClickListener(R.id.item_dynamic_tv_view_detail, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onListener != null) {
                                    onListener.viewNoteDetail(newsInformationInfoEntity.getId());
                                }
                            }
                        });
                    }
                    rvImgs.setAdapter(
                            new NoteUserImgsRvAdapter(
                                    R.layout.item_dynamic_img,
                                    context,
                                    newPicList,
                                    newsInformationInfoEntity.getStyle(),
                                    picList));
                    rvImgs.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    private void bindUserInfo(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity, NoteUserEntity.MapBean map) {
        UserInfoEntity userinfo = map.getUserinfo();
        if (userinfo != null) {
            GlideTools.loadImage(context,
                    userinfo.getAvatar(),
                    (CircleImageView) viewHolder.getView(R.id.item_dynamic_iv_note_user_head));
            viewHolder.setOnClickListener(R.id.item_dynamic_iv_note_user_head, new OnDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    if (onListener != null) {
                        onListener.enterFriendInfoUI(userinfo.getUser_id());
                    }
                }
            });
            viewHolder.setText(R.id.item_dynamic_tv_note_user_name, userinfo.getNick_name());
            if (userinfo.getSex() == 1) {
                viewHolder.setImageResource(
                        R.id.item_dynamic_tv_note_user_xingbie,
                        R.mipmap.xingbie_nan);
            } else {
                viewHolder.setImageResource(
                        R.id.item_dynamic_tv_note_user_xingbie,
                        R.mipmap.xingbie_nv);
            }
            GlideTools.loadImage(
                    context,
                    userinfo.getNational_flag(),
                    (CircleImageView) viewHolder.getView(R.id.item_dynamic_iv_note_user_national_flag));
        }

    }

    private void updateLikeInfoList(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity) {

        viewHolder.setText(R.id.item_dynamic_tv_note_user_dianzan_count,
                String.valueOf(newsInformationInfoEntity.getLike_count()));
        DrawableLeftCenterTextView tvDianzan = viewHolder.getView(R.id.item_dynamic_tv_note_user_dianzan_count);
        tvDianzan.setCompoundDrawables(null, null,
                context.getResources().getDrawable(R.mipmap.dianzan, null), null);

        Drawable undianzanDrawableLeft = context.getResources().getDrawable(R.mipmap.undianzan);
        Drawable dianzanDrawableLeft = context.getResources().getDrawable(R.mipmap.dianzan);
        undianzanDrawableLeft.setBounds(0, 0, undianzanDrawableLeft.getMinimumWidth(), undianzanDrawableLeft.getMinimumHeight());
        dianzanDrawableLeft.setBounds(0, 0, dianzanDrawableLeft.getMinimumWidth(), dianzanDrawableLeft.getMinimumHeight());
        tvDianzan.setCompoundDrawables(dianzanDrawableLeft, null, null, null);
        NoteUserEntity.MapBean map = newsInformationInfoEntity.getMap();
        if (map != null) {
            boolean is_like = map.isIs_like();
            tvDianzan.setCompoundDrawables(
                    is_like ? undianzanDrawableLeft : dianzanDrawableLeft,
                    null,
                    null,
                    null);
        }
        tvDianzan.setCompoundDrawablePadding(DensityUtils.dp2px(context, 8));
        if (map != null && map.getLikeinfo() != null && map.getLikeinfo().size() > 0) {
            List<LikeInfoEntity> likeInfoEntities = newsInformationInfoEntity.getMap().getLikeinfo();
            viewHolder.getView(R.id.item_dynamic_ll_like).setVisibility(View.VISIBLE);
            StringBuffer stringBuffer = new StringBuffer();
            int showlikeCount = 6;
            if (likeInfoEntities.size() < showlikeCount) {
                showlikeCount = likeInfoEntities.size();
            }
            for (int i = 0; i < showlikeCount; i++) {
                LikeInfoEntity likeInfoEntity = likeInfoEntities.get(i);
                if (i == likeInfoEntities.size() - 1) {
                    stringBuffer.append(TextUtils.isEmpty(likeInfoEntity.getNick_name())
                            ? String.valueOf(likeInfoEntity.getUser_id()) : likeInfoEntity.getNick_name());
                } else {
                    stringBuffer.append(TextUtils.isEmpty(likeInfoEntity.getNick_name())
                            ? String.valueOf(likeInfoEntity.getUser_id()) : likeInfoEntity.getNick_name()).append(",");
                }
            }
            viewHolder.setText(R.id.item_dynamic_tv_like_list, stringBuffer.toString());
        } else {
            viewHolder.getView(R.id.item_dynamic_ll_like).setVisibility(View.GONE);
        }
    }

    private void initCommentRvAdapter(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity) {
        viewHolder.setText(R.id.item_dynamic_tv_note_user_comment_count,
                String.valueOf(newsInformationInfoEntity.getComment_count()));
        RecyclerViewFixed rvComments = viewHolder.getView(R.id.item_dynamic_rv_comments);
        NoteUserEntity.MapBean map = newsInformationInfoEntity.getMap();
        final List<NoteCommentEntity> noteCommentEntities = map != null ? map.getNoteCommentEntities() : new ArrayList<>();
        final List<NoteCommentEntity> newnoteCommentEntities = new ArrayList<>();
        if (map != null && newsInformationInfoEntity.getMap().getNoteCommentEntities() != null && noteCommentEntities != null) {
            for (int i = 0; i < (noteCommentEntities.size() > 6 ? 6 : noteCommentEntities.size()); i++) {
                NoteCommentEntity noteCommentEntity = noteCommentEntities.get(i);
                newnoteCommentEntities.add(noteCommentEntity);
            }
        }
        rvComments.setVertical(1);
        rvComments.setScrollingEnabled(false);
        rvComments.addGridViewItemDecoration(1, 12, 0);
        NoteCommentRvAdapter noteCommentRvAdapter = new NoteCommentRvAdapter(
                R.layout.item_note_comment,
                context,
                newnoteCommentEntities);
        noteCommentRvAdapter.setMaxShowNum(6);
        rvComments.setAdapter(noteCommentRvAdapter);
    }

    private void initListener(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity, int position) {
        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                // 进入境友详情页面
                if (onListener != null) {
                    onListener.viewNoteDetail(newsInformationInfoEntity.getId());
                }
            }
        });
        viewHolder.getView(R.id.item_dynamic_ll_note_user_more_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出屏蔽收藏或删除
                if (onListener != null) {
                    onListener.showShieldDialog(newsInformationInfoEntity, position, viewHolder);
                }
            }
        });

        // 分享
        viewHolder.getView(R.id.item_dynamic_tv_note_user_share_count).setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onListener != null) {
                    onListener.showSharePopWindow(newsInformationInfoEntity, viewHolder);
                }
            }
        });
        viewHolder.getView(R.id.item_dynamic_tv_note_user_comment_count).setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                // 评论
                if (onListener != null) {
                    onListener.sendNoteComment(viewHolder, newsInformationInfoEntity);
                }
            }

        });
        viewHolder.getView(R.id.item_dynamic_tv_note_user_dianzan_count).setOnClickListener(new OnDoubleClickListener() {

            @Override
            public void onNoDoubleClick(View v) {
                AnimationUtils.startScaleAnimation(
                        v,
                        1f,
                        0.5f,
                        100,
                        0,
                        new AbsAnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                // 点赞
                                if (onListener != null) {
                                    onListener.giveThumb(viewHolder, newsInformationInfoEntity);
                                }
                            }
                        });
            }
        });
    }

    public void clearTask() {
        BaiDuTransUtils.clearTask();
    }

    public interface OnListener {

        void enterFriendInfoUI(String user_id);

        void viewNoteDetail(String id);

        void viewCategories(NoteUserEntity noteUserEntity);

        void showShieldDialog(NoteUserEntity newsInformationInfoEntity, int position, ViewHolder viewHolder);

        void sendNoteComment(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity);

        void giveThumb(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity);

        void showSharePopWindow(NoteUserEntity newsInformationInfoEntity, ViewHolder viewHolder);
    }
}
