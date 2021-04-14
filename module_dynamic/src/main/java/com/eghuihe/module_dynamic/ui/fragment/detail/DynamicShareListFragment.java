package com.eghuihe.module_dynamic.ui.fragment.detail;

import com.eghuihe.module_dynamic.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.dynamic.ShareHistoryListModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class DynamicShareListFragment extends BaseRVRefreshFragment<EmptyRVAdapter> {
    private NoteUserEntity noteUserEntity;
    private LoginResultEntity loginResultEntity;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected EmptyRVAdapter createAdapter() {
        return new EmptyRVAdapter<ShareHistoryListModel.ShareHistoryEntity>(R.layout.item_dynamic_share, getContext()) {
            @Override
            protected void convert(final ViewHolder viewHolder, final ShareHistoryListModel.ShareHistoryEntity shareHistoryEntity, final int position) {

                ShareHistoryListModel.ShareHistoryEntity.MapBean map = shareHistoryEntity.getMap();
                if (map != null) {

                    UserInfoEntity userinfo = map.getUserinfo();
                    if (userinfo != null) {

                        GlideTools.loadImage(context, userinfo.getAvatar(),
                                (CircleImageView) viewHolder.getView(R.id.item_dynamic_share_iv_head));
                        GlideTools.loadImage(context, userinfo.getNational_flag(),
                                (CircleImageView) viewHolder.getView(R.id.item_dynamic_share_iv_nationalFlag));
                        viewHolder.setText(R.id.item_dynamic_share_tv_nickName,
                                userinfo.getNick_name());
                        viewHolder.setImageResource(R.id.item_dynamic_share_iv_sex,
                                userinfo.getSex() == 1 ? R.mipmap.xingbie_nan : R.mipmap.xingbie_nv);
                    }
                }
                String create_time = shareHistoryEntity.getCreate_time();
                create_time = DateUtils.convertTimeToFormat(DateUtils.stringToLong(create_time, DateUtils.YMDHMSFormatStr));
                viewHolder.setText(R.id.item_dynamic_share_tv_duration, create_time);
//                String shareDesc = ShareSdkUtils.getShareDesc(shareHistoryEntity.getDestination());
//                viewHolder.setText(R.id.item_dynamic_share_tv_desc,shareDesc);

            }
        };
    }

    public void setData(NoteUserEntity noteUserEntity) {
        LogUtils.e("triggerAutoRefresh", "setData - > noteUserEntity = " + noteUserEntity);
        this.noteUserEntity = noteUserEntity;
    }

    @Override
    public void doRefresh() {
        LogUtils.e("triggerAutoRefresh", "doRefresh - > noteUserEntity = " + noteUserEntity);
        LogUtils.e("triggerAutoRefresh", "doRefresh - > note_id = " + noteUserEntity.getId());
        UserServiceImpl.queryShareHistoryListPage(noteUserEntity.getId(),
                "share", "note", getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize(), new NetworkSubscriber<ShareHistoryListModel>(this) {

                    @Override
                    protected void onSuccess(ShareHistoryListModel historyListModel) {

                        List<ShareHistoryListModel.ShareHistoryEntity> historyListModelData = historyListModel.getData();
                        initAdapter();
                        if (adapter != null) {
                            adapter.setData(historyListModelData);
                        }

                        if (historyListModelData == null || historyListModelData.size() < getLoadPagerManager().getPageSize()) {
                            finishRefreshWithNoMoreData();
                        }
                    }
                });

    }

    @Override
    public void doLoadMore() {
        UserServiceImpl.queryShareHistoryListPage(noteUserEntity.getId(),
                "share", "note", getLoadPagerManager().getCurrentPage(),
                getLoadPagerManager().getPageSize(), new NetworkSubscriber<ShareHistoryListModel>(this) {

                    @Override
                    protected void onSuccess(ShareHistoryListModel historyListModel) {

                        List<ShareHistoryListModel.ShareHistoryEntity> historyListModelData = historyListModel.getData();
                        if (adapter != null) {
                            adapter.addData(historyListModelData);
                        }

                        if (historyListModelData == null || historyListModelData.size() < getLoadPagerManager().getPageSize()) {
                            finishLoadMoreWithNoMoreData();
                        }
                    }
                });

    }

    @Override
    protected void initData() {
        loginResultEntity = LoginHelper.getLoginInfo();
        LogUtils.e("triggerAutoRefresh", "initData");
        triggerAutoRefresh();
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.REFRESH_DYNAMIC_DETAIL_SHARELIST_EVENT.equals(event.getAction()))
            initData();
    }
}
