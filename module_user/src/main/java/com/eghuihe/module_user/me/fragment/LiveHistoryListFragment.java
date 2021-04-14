package com.eghuihe.module_user.me.fragment;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.LiveMasterSetPriceHistoryListRvAdapter;
import com.eghuihe.module_user.me.mvp.LiveHistoryListContract;
import com.eghuihe.module_user.me.mvp.LiveHistoryListPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LiveEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class LiveHistoryListFragment extends BaseMvpRVRefreshFragment<EmptyRVAdapter, LiveHistoryListPresenter>
        implements LiveHistoryListContract.View {
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
        return new LiveMasterSetPriceHistoryListRvAdapter(R.layout.item_history_live_mastersetprice,getContext());
    }

    @Override
    protected LiveHistoryListPresenter createPresenter() {
        return new LiveHistoryListPresenter();
    }

    @Override
    public void doRefresh() {
        getPresenter().getLiveMasterSetPriceList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "3"
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().getLiveMasterSetPriceList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "3"
        );
    }

    @Override
    protected void initData() {
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
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEventBus(Event event){
        if (EventAction.CREATE_LIVE.equals(event.getAction())){
            doRefresh();
        }
    }
}
