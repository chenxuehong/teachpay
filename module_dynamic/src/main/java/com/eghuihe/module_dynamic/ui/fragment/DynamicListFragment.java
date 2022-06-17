package com.eghuihe.module_dynamic.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.ui.activity.DynamicDetailActivity;
import com.eghuihe.module_dynamic.ui.activity.InterestAllianceActivity;
import com.eghuihe.module_dynamic.ui.adapter.NoteUserRvAdapter;
import com.eghuihe.module_dynamic.ui.mvp.DynamicContract;
import com.eghuihe.module_dynamic.ui.mvp.DynamicPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.popup.PopupViewManager;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = ARouterConfig.DYNAMIC_DYNAMICLISTFRAGMENT)
public class DynamicListFragment extends BaseMvpRVRefreshFragment<NoteUserRvAdapter, DynamicPresenter>
        implements DynamicContract.View, NoteUserRvAdapter.OnListener {

    private PopupWindow popupWindow;
    private CustomPopupWindow popInputTextWindow;
    private String classfiy;
    private NoteUserEntity mNewsInformationInfoEntity;
    private ViewHolder mViewHolder;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected NoteUserRvAdapter createAdapter() {
        return new NoteUserRvAdapter(R.layout.item_dynamic, getContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void doRefresh() {
        getPresenter().getNewsInformationInfoList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                classfiy);
    }

    @Override
    public void doLoadMore() {
        getPresenter().getNewsInformationInfoList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                classfiy);
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            classfiy = arguments.getString(ArgumentsConfig.KEY_CLASSFIY);
        }
        triggerAutoRefresh();
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.DYNAMIC_LIST_FRESH.equals(event.getAction())) {
            doRefresh();
        } else if (EventAction.DYNAMIC_SHARE_SUCCESS.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof String) {
                String name = (String) data;
                if (mNewsInformationInfoEntity != null)
                    getPresenter().insertHistory(
                            LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            mNewsInformationInfoEntity.getId(),
                            "share",
                            "note",
                            name,
                            0,
                            mViewHolder,
                            mNewsInformationInfoEntity

                    );
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showNewsInformationInfoList(List<NoteUserEntity> noteUserEntities) {
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(noteUserEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(noteUserEntities);
            }
        }
        if (noteUserEntities == null || noteUserEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
        closeLoading();
    }

    @Override
    public int getCurrentPage() {
        return getLoadPagerManager().getCurrentPage();
    }

    @Override
    public int getPageSize() {
        return getLoadPagerManager().getPageSize();
    }

    @Override
    protected DynamicPresenter createPresenter() {
        return new DynamicPresenter();
    }

    @Override
    public void retry() {
        super.retry();
        initData();
    }

    @Override
    public void showShieldDialog(final NoteUserEntity newsInformationInfoEntity, final int position, ViewHolder viewHolder) {
        // 弹出屏蔽收藏或删除
        String user_id = newsInformationInfoEntity.getUser_id();
        if (LoginHelper.isMySelf(user_id)) {
            showPopDeleteNoteDialog(newsInformationInfoEntity, position);
        } else {
            View inflate = View.inflate(getContext(), R.layout.include_layout_collect_shield, null);
            popupWindow = PopupViewManager.getInstance().showCenterView(getActivity(),
                    viewHolder.itemView, inflate);
            // 屏蔽
            inflate.findViewById(R.id.item_dynamic_ll_shield).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    getPresenter().insertHistory(
                            LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            newsInformationInfoEntity.getMap().getUserinfo().getUser_id(),
                            "shield",
                            "note",
                            null,
                            position,
                            viewHolder,
                            newsInformationInfoEntity);
                }
            });
            // 收藏
            inflate.findViewById(R.id.item_dynamic_ll_collect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    getPresenter().insertHistory(
                            LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            newsInformationInfoEntity.getId(),
                            "collect",
                            "note",
                            null, position,
                            viewHolder,
                            newsInformationInfoEntity);
                }
            });
        }
    }

    private void showPopDeleteNoteDialog(final NoteUserEntity newsInformationInfoEntity, final int position) {
        // 弹出删除
        DialogUtils.showTipDialog(getContext(),
                "",
                getResources().getString(R.string.delete_tip),
                getResources().getString(R.string.cancel),
                getResources().getString(R.string.sure),
                new DialogUtils.OnListener() {
                    @Override
                    public void okClicked() {
                        getPresenter().deleteNoteUser(newsInformationInfoEntity.getId(), position);
                    }

                    @Override
                    public void cancelClicked() {

                    }
                });
    }

    /**
     * @param viewHolder
     * @param newsInformationInfoEntity
     * @desc 发送动态
     */
    @Override
    public void sendNoteComment(ViewHolder viewHolder, final NoteUserEntity newsInformationInfoEntity) {
        View rootview = getActivity().getWindow().getDecorView().getRootView();
        NoteUserEntity.MapBean map = newsInformationInfoEntity.getMap();
        if (map != null) {
            UserInfoEntity userinfo = map.getUserinfo();
            if (userinfo != null) {
                final String user_id = userinfo.getUser_id();
                popInputTextWindow = PopWindowUtils.popInputTextWindow(
                        getContext(),
                        "说出你的想法",
                        rootview,
                        new PopWindowUtils.OnInputListener() {
                            @Override
                            public void okClicked(String content) {
                                getPresenter().insertNoteComment(
                                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                        false,
                                        user_id,
                                        "0",
                                        newsInformationInfoEntity.getId(),
                                        content,
                                        viewHolder,
                                        newsInformationInfoEntity
                                );
                            }
                        });
            }
        }
    }

    /**
     * @param viewHolder
     * @param newsInformationInfoEntity
     * @desc 点赞
     */
    @Override
    public void giveThumb(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity) {

        getPresenter().insertHistory(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                newsInformationInfoEntity.getId(),
                "like",
                "note",
                "",
                0,
                viewHolder,
                newsInformationInfoEntity
        );
    }

    @Override
    public void onInsertHistorySuccess(String operation_type, int position, ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity) {
        if ("shield".equals(operation_type)) {
            onDeleteNoteSuccess(position);
        } else if ("like".equals(operation_type)) {
            // 点赞
            getPresenter().queryNoteUserfindById(newsInformationInfoEntity.getId(), viewHolder);
        }else {
            getPresenter().queryNoteUserfindById(newsInformationInfoEntity.getId(), viewHolder);
        }
    }

    @Override
    public void onInsertNoteCommentSuccess(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity) {
        getPresenter().queryNoteUserfindById(newsInformationInfoEntity.getId(), viewHolder);
    }

    @Override
    public void showNoteUserEntity(NoteUserEntity noteUserEntity, ViewHolder viewHolder) {
        if (adapter != null) {
            adapter.notifyItemData(viewHolder, noteUserEntity);
        }
    }

    @Override
    public void onDeleteNoteSuccess(int position) {
        if (adapter != null) {
            adapter.remove(position);
        }
    }

    /**
     * @param newsInformationInfoEntity
     * @param viewHolder
     * @desc 分享
     */
    @Override
    public void showSharePopWindow(NoteUserEntity newsInformationInfoEntity, ViewHolder viewHolder) {
        mNewsInformationInfoEntity = newsInformationInfoEntity;
        mViewHolder = viewHolder;
        NoteUserEntity.MapBean useMap = newsInformationInfoEntity.getMap();
        String imgUrl = null;
        if (useMap != null) {
            UserInfoEntity userinfo = useMap.getUserinfo();
            if (userinfo != null) {
                imgUrl = userinfo.getAvatar();
            }
        }
        String picts = newsInformationInfoEntity.getPicts();
        if (!TextUtils.isEmpty(picts)) {
            String[] split = picts.split(",");
            if (split.length > 0) {
                imgUrl = split[0];
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put(ArgumentsConfig.KEY_URL,
                "");
        map.put(ArgumentsConfig.KEY_TITLE,
                newsInformationInfoEntity.getContent());
        map.put(ArgumentsConfig.KEY_IMGURL,
                imgUrl);
        map.put(ArgumentsConfig.KEY_WXPATH,
                AppConfigs.Cooperation.ZH.BAOBAOXIU_WX_PATH
                        .concat(newsInformationInfoEntity.getId()));
        EventBusUtils.sendEvent(new Event(EventAction.BAOBAOXIU_SHARE, map));
    }

    @Override
    public void enterFriendInfoUI(String user_id) {

    }

    @Override
    public void viewNoteDetail(String id) {
        startActivity(DynamicDetailActivity.class,
                new ExtraEntity(DynamicDetailActivity.KEY_NOTEUSER_INFO, id));

    }

    @Override
    public void viewCategories(NoteUserEntity noteUserEntity) {
        // 进入兴趣联盟
        Intent intent = new Intent(getContext(), InterestAllianceActivity.class);
        String classfiy = noteUserEntity.getClassfiy();
        if (!TextUtils.isEmpty(classfiy)) {
            intent.putExtra(ArgumentsConfig.KEY_CLASSFIY, classfiy);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        if (adapter != null) {
            adapter.clearTask();
        }
        if (popInputTextWindow != null) {
            popInputTextWindow.dismiss();
        }
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        super.onDestroy();
    }
}
