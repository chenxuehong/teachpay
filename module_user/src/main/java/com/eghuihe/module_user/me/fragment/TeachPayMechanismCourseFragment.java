package com.eghuihe.module_user.me.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.TeachPayCourseOnSaleRvAdapter;
import com.eghuihe.module_user.me.mvp.OnSaleCourseContract;
import com.eghuihe.module_user.me.mvp.OnSaleCoursePresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.event.MyWindowEvent;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.utils.EventBusUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@Route(path = ARouterConfig.ME_TEACHPAY_MECHANISM_COURSE)
public class TeachPayMechanismCourseFragment extends BaseMvpRVRefreshFragment<EmptyRVAdapter, OnSaleCoursePresenter>
        implements OnSaleCourseContract.View {
    public static final String KEY_MECHANISM_ID = ArgumentsConfig.KEY_MECHANISM_ID;
    public static final String KEY_STATUS = ArgumentsConfig.KEY_STATUS;
    public static final String KEY_IS_SELECT = ArgumentsConfig.KEY_IS_SELECT;
    public static final String KEY_APPOINTMENT_TYPE = ArgumentsConfig.KEY_APPOINTMENT_TYPE;
    private String mechanism_id;
    private String status;
    private boolean is_select;
    private String appointment_type;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected EmptyRVAdapter createAdapter() {
        return new TeachPayCourseOnSaleRvAdapter(R.layout.item_classical_course, getContext(), is_select);
    }

    @Override
    public void doRefresh() {
        getPresenter().queryExclusiveCourseList(
                mechanism_id,
                getType(),
                status,
                appointment_type,
               null,
               null
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().queryExclusiveCourseList(
                mechanism_id,
                getType(),
                status,
                appointment_type,
                null,
                null
        );
    }

    private String getType() {
        return "mechanism_offline";
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        mechanism_id = arguments.getString(KEY_MECHANISM_ID);
        status = arguments.getString(KEY_STATUS);
        appointment_type = arguments.getString(KEY_APPOINTMENT_TYPE);
        is_select = arguments.getBoolean(KEY_IS_SELECT, false);
        triggerAutoRefresh();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Subscribe
    public void updateCourse(Event mechanismCoursesEvent) {
        if (EventAction.INSERT_MECHANISM_COURSE.equals(mechanismCoursesEvent.getAction()))
            triggerAutoRefresh();
    }

    @Override
    public void showExclusiveCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities) {
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(exclusiveCoursesEntities);
                adapter.setOnClickListener(new EmptyRVAdapter.OnItemClickListener<MasterSetPriceEntity>() {
                    @Override
                    public void onItemClicked(View v, MasterSetPriceEntity masterSetPriceEntity, int position) {
                        if (is_select) {
                            EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_SELECT, masterSetPriceEntity));
                        } else {
                            EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
                        }

                    }

                    @Override
                    public void onItemLongClicked(View v, MasterSetPriceEntity masterSetPriceEntity, int position) {

                    }
                });
            }
        } else {
            if (adapter != null) {
                adapter.addData(exclusiveCoursesEntities);
            }
        }
        if (exclusiveCoursesEntities == null || exclusiveCoursesEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    protected OnSaleCoursePresenter createPresenter() {
        return new OnSaleCoursePresenter();
    }
}
