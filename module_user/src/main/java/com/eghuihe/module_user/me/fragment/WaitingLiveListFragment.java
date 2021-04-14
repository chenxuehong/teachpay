package com.eghuihe.module_user.me.fragment;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.WaitingLiveListRvAdapter;
import com.eghuihe.module_user.me.mvp.WaitingLiveListContract;
import com.eghuihe.module_user.me.mvp.WaitingLiveListPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LiveEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.request.AnchorLiveParam;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class WaitingLiveListFragment extends BaseMvpRVRefreshFragment<EmptyRVAdapter, WaitingLiveListPresenter>
        implements WaitingLiveListContract.View, WaitingLiveListRvAdapter.OnListener {
    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(getContext(), 15);
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected EmptyRVAdapter createAdapter() {
        return new WaitingLiveListRvAdapter(R.layout.item_waiting_live_mastersetprice, getContext(), this);
    }

    @Override
    protected WaitingLiveListPresenter createPresenter() {
        return new WaitingLiveListPresenter();
    }

    @Override
    public void doRefresh() {
        getPresenter().getLiveMasterSetPriceList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "1"
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().getLiveMasterSetPriceList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "1"
        );
    }

    @Override
    protected void initData() {
        super.initData();
        triggerAutoRefresh();
    }

    @Override
    public void showLiveMasterSetPriceList(List<LiveEntity> liveEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null)
                adapter.setData(liveEntities);
        } else {
            if (adapter != null)
                adapter.addData(liveEntities);
        }
        if (liveEntities == null || liveEntities.size() < getLoadPagerManager().getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void startLive(LiveEntity liveEntity) {
        String room_id = LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id();
        TUIKit.startLive(getContext(), JsonUtil.toJson(new AnchorLiveParam(
                liveEntity.getId(),
                room_id,
                liveEntity.getTitle()
        )));
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEventBus(Event event) {
        if (EventAction.CREATE_LIVE.equals(event.getAction())) {
            doRefresh();
        } else if (EventAction.CLOSE_LIVE_SUCCESS.equals(event.getAction())) {
            doRefresh();
        }
    }
}
