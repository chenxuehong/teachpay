package com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling;

import android.content.Intent;
import android.os.Bundle;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.activity.ArrangeFixedMechanismCourseActivity;
import com.eghuihe.module_schedule.ui.mechanism.activity.ArrangeMechanismCourseActivity;
import com.eghuihe.module_schedule.ui.mechanism.activity.StudentListActivity;
import com.eghuihe.module_schedule.ui.mechanism.adapter.MechanismCourseScheduleRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismCourseScheduleContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismCourseSchedulePresenter;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MechanismCourseScheduleFragment extends BaseMvpRVRefreshFragment<MechanismCourseScheduleRvAdapter, MechanismCourseSchedulePresenter>
        implements MechanismCourseScheduleContract.View, MechanismCourseScheduleRvAdapter.OnListener {

    private String appointment_type;

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(getContext(), 15);
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected MechanismCourseScheduleRvAdapter createAdapter() {
        return new MechanismCourseScheduleRvAdapter(R.layout.item_mechanism_course_schedule, getContext(), this);
    }

    @Override
    protected MechanismCourseSchedulePresenter createPresenter() {
        return new MechanismCourseSchedulePresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            appointment_type = arguments.getString(ArgumentsConfig.KEY_APPOINTMENT_TYPE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        triggerAutoRefresh();
    }

    @Override
    public void doRefresh() {
        super.doRefresh();
        getPresenter().queryMechanismCourseList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "2",
                appointment_type,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    public void doLoadMore() {
        super.doLoadMore();
        getPresenter().queryMechanismCourseList(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                "2",
                appointment_type,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    public void showMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(masterSetPriceEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(masterSetPriceEntities);
            }
        }

        if (masterSetPriceEntities == null || masterSetPriceEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onArrangeClicked(ViewHolder viewHolder, MasterSetPriceEntity masterSetPriceEntity) {
        if ("fixed_scheduling".equals(appointment_type)) {
            // 固定课程
            Intent intent = new Intent(getContext(), ArrangeFixedMechanismCourseActivity.class);
            intent.putExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY,
                    JsonUtil.toJson(masterSetPriceEntity));
            startActivity(intent);
        } else if ("appointment".equals(appointment_type)) {
            // 预约课程
            Intent intent = new Intent(getContext(), ArrangeMechanismCourseActivity.class);
            startActivity(intent);
        } else {
            // 自由课程
            Intent intent = new Intent(getContext(), StudentListActivity.class);
            intent.putExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY,
                    JsonUtil.toJson(masterSetPriceEntity));
            startActivity(intent);
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.INSERT_MECHANISM_COURSE.equals(event.getAction())) {
            doRefresh();
        }
    }
}
