package com.eghuihe.module_user.me.fragment;

import com.eghuihe.module_user.me.mvp.MyRewardListContract;
import com.eghuihe.module_user.me.mvp.MyRewardListPresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MyPointsEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @desc 积分支出列表
 */
public class PointsExpensesListFragment extends BasePointsListFragment<MyRewardListPresenter>
        implements MyRewardListContract.View {

    private String startTime;

    @Override
    protected MyRewardListPresenter createPresenter() {
        return new MyRewardListPresenter();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.POINTS_LIST.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof String) {
                startTime = (String) data;
                triggerAutoRefresh();
            }
        }
    }

    @Override
    protected void loadData() {
        getPresenter().getUserPointsList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                false,
                startTime
        );
    }

    @Override
    protected void initData() {
        super.initData();
        String curYMDateStr = DateUtils.getCurDateStr(DateUtils.YMFormatStr);
        startTime = curYMDateStr.concat("-01").concat(" 00:00:00");
        triggerAutoRefresh();
    }

    @Override
    public void onMyPointsList(List<MyPointsEntity> myPointsEntities) {
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(myPointsEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(myPointsEntities);
            }
        }
        if (myPointsEntities == null || myPointsEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
        closeLoading();
    }
}
