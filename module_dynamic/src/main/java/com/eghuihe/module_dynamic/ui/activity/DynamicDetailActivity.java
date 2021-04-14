package com.eghuihe.module_dynamic.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.eghuihe.module_dynamic.ui.adapter.ItemNoteUserImsRvAdapter;
import com.eghuihe.module_dynamic.ui.fragment.detail.DynamicDetailCommentListFragment;
import com.eghuihe.module_dynamic.ui.fragment.detail.DynamicDetailLikeListFragment;
import com.eghuihe.module_dynamic.ui.mvp.DynamicDetailContract;
import com.eghuihe.module_dynamic.ui.mvp.DynamicDetailPresenter;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.event.DynamicDetailCommentCountEvent;
import com.huihe.base_lib.model.event.DynamicDetailLikeCountEvent;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.popup.PopupViewManager;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.BaiDuTransUtils;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.amin.AnimationUtils;
import com.huihe.base_lib.utils.amin.iml.AbsAnimationListener;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 动态详情Fragment
 */
public class DynamicDetailActivity extends BaseMvpTitleActivity<DynamicDetailPresenter> implements DynamicDetailContract.View {

    public static final String KEY_NOTEUSER_INFO = "KEY_NOTEUSER_INFO";
    @BindView(R2.id.item_dynamic_iv_note_user_head)
    CircleImageView ivHead;
    @BindView(R2.id.item_dynamic_tv_note_user_name)
    TextView tvUserName;
    @BindView(R2.id.item_dynamic_tv_note_user_xingbie)
    ImageView ivSex;
    @BindView(R2.id.item_dynamic_iv_note_user_national_flag)
    ImageView ivNationalFlag;
    @BindView(R2.id.item_dynamic_tv_note_user_time)
    TextView tvTime;
    @BindView(R2.id.item_dynamic_ll_note_user_more_detail)
    LinearLayout llMore;
    @BindView(R2.id.item_dynamic_iv_trans)
    ImageView ivTrans;
    @BindView(R2.id.item_dynamic_tv_note_user_content)
    ExpandableTextView tvContent;
    @BindView(R2.id.item_dynamic_tv_trans_content)
    TextView tvTransContent;
    @BindView(R2.id.item_dynamic_rv_note_user_imgs)
    RecyclerViewFixed rvImgs;

    @BindView(R2.id.dynamic_detail_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.dynamic_detail_vp)
    ViewPager viewPager;
    private LoginResultEntity resultEntity;
    private BaseFragmentStatePageAdapter baseFragmentStatePageAdapter;
    private NoteUserEntity noteUserEntity;
    private String noteId;


    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("宝宝秀详情");
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_dynamic_detail;
    }

    @OnClick({
            R2.id.item_dynamic_iv_trans,
            R2.id.dynamic_detail_tv_sendComment,
            R2.id.dynamic_detail_iv_dianzan,
            R2.id.dynamic_detail_iv_share
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.item_dynamic_iv_trans) {
            // 翻译
            translateContent();
        } else if (view.getId() == R.id.dynamic_detail_tv_sendComment) {
            // 发送评论
            sendComment();
        } else if (view.getId() == R.id.dynamic_detail_iv_dianzan) {
            dianZan(view);
        } else if (view.getId() == R.id.dynamic_detail_iv_share) {
            popShareDialog();
        }
    }

    private void translateContent() {
        if (tvTransContent.getVisibility() == View.VISIBLE) {
            tvTransContent.setVisibility(View.GONE);
        } else {
            BaiDuTransUtils.transContent(noteUserEntity.getContent(), LoginHelper.getLoginInfo().getUserInfoEntity().getMother_tongue(), new BaiDuTransUtils.OnTranslateListener() {
                @Override
                public void onSuccess(String content) {
                    tvTransContent.setText(content);
                    tvTransContent.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void sendComment() {
        View rootview = getWindow().getDecorView().getRootView();
        PopWindowUtils.popInputTextWindow(this, getResources().getString(R.string.comment_tip), rootview, new PopWindowUtils.OnInputListener() {

            @Override
            public void okClicked(final String content) {
                getPresenter().insertNoteComment(String.valueOf(resultEntity.getUserInfoEntity().getUser_id()), false,
                        String.valueOf(noteUserEntity.getMap().getUserinfo().getUser_id()), "0", noteUserEntity.getId(), content);
            }
        });
    }

    private void dianZan(View view) {
        AnimationUtils.startScaleAnimation(view, 1f, 0.5f, 100, 0, new AbsAnimationListener() {
            @Override
            public void onAnimationEnd() {
                // 点赞
                getPresenter().insertHistory(resultEntity.getUserInfoEntity().getUser_id(),
                        noteUserEntity.getId(), "like", "note", "动态点赞");
            }
        });
    }

    private void popCollectDialog(View view) {
        // 弹出屏蔽收藏或删除 Popup block favorites or delete
        String user_id = noteUserEntity.getUser_id();
        if (LoginHelper.isMySelf(user_id)) {
            // 弹出删除
            DialogUtils.showTipDialog(this, "", getResources().getString(R.string.delete_tip),
                    getResources().getString(R.string.cancel),
                    getResources().getString(R.string.sure),
                    new DialogUtils.OnListener() {
                        @Override
                        public void okClicked() {
                            getPresenter().deleteNoteUser(noteUserEntity.getId());
                        }

                        @Override
                        public void cancelClicked() {

                        }
                    });
        } else {
            View inflate = View.inflate(this, R.layout.include_layout_collect_shield, null);
            final PopupWindow popupWindow = PopupViewManager.getInstance().showCenterView(this,
                    view, inflate);
            // 屏蔽
            inflate.findViewById(R.id.item_dynamic_ll_shield).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                    getPresenter().insertHistory(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            noteUserEntity.getMap().getUserinfo().getUser_id(), "shield", "note",
                            null);
                }
            });
            // 收藏
            inflate.findViewById(R.id.item_dynamic_ll_collect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                    getPresenter().insertHistory(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            noteUserEntity.getId(), "collect", "note",
                            null);
                }
            });

        }
    }

    private void popShareDialog() {

    }

    @Override
    protected void initData() {

        noteId = getIntentData(KEY_NOTEUSER_INFO, String.class);
        resultEntity = LoginHelper.getLoginInfo();
        getPresenter().getNewsInformationInfoListByNoteId(noteId);
    }

    @Override
    public void onNoteUser(NoteUserEntity noteUserEntity) {
        this.noteUserEntity = noteUserEntity;
        bindTopData();
        initVp();
    }

    private void bindTopData() {
        if (noteUserEntity != null) {
            if (noteUserEntity.getMap() != null) {
                UserInfoEntity userinfo = noteUserEntity.getMap().getUserinfo();
                if (userinfo != null) {
                    GlideTools.loadImage(this, userinfo.getAvatar(), ivHead);
                    tvUserName.setText(userinfo.getNick_name());
                    if (userinfo.getSex() == 1) {
                        ivSex.setImageResource(R.mipmap.xingbie_nan);
                    } else {
                        ivSex.setImageResource(R.mipmap.xingbie_nv);
                    }
                    GlideTools.loadImage(this, userinfo.getNational_flag(), ivNationalFlag);
                }
            }

            tvTime.setText(DateUtils.convertTimeToFormat(DateUtils.stringToLong(noteUserEntity.getCreate_time(), DateUtils.YMDHMSFormatStr)));
            tvContent.setContent(noteUserEntity.getContent());

            String picts = noteUserEntity.getPicts();
            if (picts != null) {
                String[] pictArr = picts.split(",");
                if (pictArr != null && pictArr.length == 1) {
                    rvImgs.setVertical(1);
                    ViewGroup.LayoutParams rvParams = rvImgs.getLayoutParams();
                    rvParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    rvImgs.setLayoutParams(rvParams);

                } else {
                    rvImgs.setVertical(3);
                    ViewGroup.LayoutParams rvParams = rvImgs.getLayoutParams();
                    rvParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    rvImgs.setLayoutParams(rvParams);
                    rvImgs.addGridViewItemDecoration(3, 2, 0);
                }
                if (pictArr != null & pictArr.length == 1) {
                    if ("".equals(pictArr[0])) {
                        pictArr = null;
                    }
                }
                rvImgs.setAdapter(new ItemNoteUserImsRvAdapter(R.layout.item_play_imageview, this, pictArr == null ? new ArrayList<>() : Arrays.asList(pictArr), noteUserEntity));
            }
        }
    }

    private void initVp() {
        if (noteUserEntity != null && noteUserEntity.getMap() != null) {

            List<String> titles = new ArrayList<>();

            int commentCount = noteUserEntity.getMap().getCommentCount();
            titles.add(String.format(getResources().getString(R.string.tab_comment_count), String.valueOf(commentCount)));
            int like_count = noteUserEntity.getLike_count();
            titles.add(String.format(getResources().getString(R.string.tab_like_count), String.valueOf(like_count)));
            //            share_count = noteUserEntity.getShare_count();
//            titles.add(String.format(getResources().getString(R.string.tab_share_count), String.valueOf(share_count)));

            List<Fragment> fragments = new ArrayList<>();
            DynamicDetailCommentListFragment dynamicDetailCommentListFragment = new DynamicDetailCommentListFragment();
            dynamicDetailCommentListFragment.setNoteId(noteUserEntity.getId());
            DynamicDetailLikeListFragment dynamicDetailLikeListFragment = new DynamicDetailLikeListFragment();
            dynamicDetailLikeListFragment.setData(noteUserEntity);
            fragments.add(dynamicDetailCommentListFragment);
            fragments.add(dynamicDetailLikeListFragment);
            //            DynamicShareListFragment dynamicShareListFragment = new DynamicShareListFragment();
//            dynamicShareListFragment.setData(noteUserEntity);
//            fragments.add(dynamicShareListFragment);
            baseFragmentStatePageAdapter = new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments);
            viewPager.setAdapter(baseFragmentStatePageAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Subscribe
    public void updateLikeCount(DynamicDetailLikeCountEvent dynamicDetailLikeCountEvent) {
        if (baseFragmentStatePageAdapter != null)
            baseFragmentStatePageAdapter.setPageTitle(1, String.format(getResources().getString(R.string.tab_like_count), String.valueOf(dynamicDetailLikeCountEvent.likeCount)));
    }

    @Subscribe
    public void updateCommentCount(DynamicDetailCommentCountEvent
                                           dynamicDetailCommentCountEvent) {
        if (baseFragmentStatePageAdapter != null) {
            int count = dynamicDetailCommentCountEvent.count;
            baseFragmentStatePageAdapter.setPageTitle(0, String.format(getResources().getString(R.string.tab_comment_count), String.valueOf(count)));
        }
    }

    @Override
    public void onInsertNoteCommentSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.DYNAMICDETAILCOMMENTLIST_REFRESH));
    }

    @Override
    public void onInsertHistorySuccess(String operation_type) {

        switch (operation_type) {
            case "share":
                noteUserEntity.setShare_count(noteUserEntity.getShare_count() + 1);
                if (baseFragmentStatePageAdapter != null)
                    baseFragmentStatePageAdapter.setPageTitle(2, String.format(getResources().getString(R.string.tab_share_count), String.valueOf(noteUserEntity.getShare_count())));
                EventBusUtils.sendEvent(new Event(EventAction.REFRESH_DYNAMIC_DETAIL_SHARELIST_EVENT));
                break;
            case "like":
                noteUserEntity.setShare_count(noteUserEntity.getLike_count() + 1);
                if (baseFragmentStatePageAdapter != null)
                    baseFragmentStatePageAdapter.setPageTitle(1, String.format(getResources().getString(R.string.tab_like_count), String.valueOf(noteUserEntity.getLike_count())));
                EventBusUtils.sendEvent(new Event(EventAction.REFRESH_DYNAMIC_DETAIL_LIKELIST_EVENT));
                break;
        }
    }

    @Override
    public void onQueryHistorySuccess(List<LikeInfoModel.LikeInfoEntity> likeInfoList) {

    }

    @Override
    public void onDeleteNoteUserSuccess() {
    }

    @Override
    protected DynamicDetailPresenter createPresenter() {
        return new DynamicDetailPresenter();
    }
}
