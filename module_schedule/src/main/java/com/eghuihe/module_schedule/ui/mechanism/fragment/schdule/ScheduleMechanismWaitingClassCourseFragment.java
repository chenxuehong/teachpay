package com.eghuihe.module_schedule.ui.mechanism.fragment.schdule;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.adapter.ScheduleMechanismWaitingClassRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismScheduleContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismSchedulePresenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @desc 机构待上课课程
 */
public class ScheduleMechanismWaitingClassCourseFragment extends BaseMvpRVRefreshFragment<ScheduleMechanismWaitingClassRvAdapter, MechanismSchedulePresenter>
        implements MechanismScheduleContract.View {
    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected ScheduleMechanismWaitingClassRvAdapter createAdapter() {
        return new ScheduleMechanismWaitingClassRvAdapter(R.layout.item_mechanism_course_waiting, getContext());
    }

    @Override
    protected MechanismSchedulePresenter createPresenter() {
        return new MechanismSchedulePresenter();
    }

    @Override
    public void doRefresh() {

        getPresenter().queryMechanismOfflineSchedule(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "1",
                "teach_paypal_course",
                getCurrentPage(),
                getPageSize(),
                null,
                null,
                null
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().queryMechanismOfflineSchedule(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "1",
                "teach_paypal_course",
                getCurrentPage(),
                getPageSize(),
                null,
                null,
                null
        );
    }

    @Override
    protected void initData() {
        super.initData();
        triggerAutoRefresh();
    }

    @Override
    public void onMechanismOfflineScheduleList(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {

        if (getCurrentPage() == 1) {

            initAdapter();
            if (adapter != null) {
                adapter.setData(mechanismOfflineScheduleEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(mechanismOfflineScheduleEntities);
            }
        }
        if (mechanismOfflineScheduleEntities == null || mechanismOfflineScheduleEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onDeleteCourseSuccess() {

    }

    @Override
    public void retry() {
        super.retry();
        doRefresh();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.MECHANISM_SUMMARY_SUCCESS.equals(event.getAction())) {
            // 总结成功
            triggerAutoRefresh();
        }
    }
}
