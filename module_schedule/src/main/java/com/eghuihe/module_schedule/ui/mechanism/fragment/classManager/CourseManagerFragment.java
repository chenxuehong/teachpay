package com.eghuihe.module_schedule.ui.mechanism.fragment.classManager;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.mechanism.activity.ClassManagerActivity;
import com.eghuihe.module_schedule.ui.mechanism.adapter.CourseManagerRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.CourseManagePresenter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.CourseManagerContract;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @desc 课程
 */
public class CourseManagerFragment extends BaseMvpRVRefreshFragment<CourseManagerRvAdapter, CourseManagePresenter>
        implements CourseManagerContract.View, CourseManagerRvAdapter.OnListener {

    private List<MechanismClassEntity> mechanismClassEntities;

    private Boolean is_scheduling_over;
    private ClassManagerActivity classManagerActivity;

    public List<MechanismClassEntity> getMechanismClassEntities() {
        return mechanismClassEntities;
    }

    private String master_set_price_id;

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(getContext(), 15);
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected CourseManagerRvAdapter createAdapter() {
        return new CourseManagerRvAdapter(R.layout.item_class, getContext(), this);
    }

    public void showMerge(boolean isShow, ClassManagerActivity classManagerActivity) {
        if (adapter != null) {
            adapter.showMerge(isShow);
        }
        this.classManagerActivity = classManagerActivity;
    }

    @Override
    protected CourseManagePresenter createPresenter() {
        return new CourseManagePresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        resetData();
        triggerAutoRefresh();
    }

    private void resetData() {
        is_scheduling_over = null;
        master_set_price_id = null;
    }

    @Override
    public void doRefresh() {
        getPresenter().queryMechanismClasses(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getCurrentPage(),
                getPageSize(),
                "2",
                master_set_price_id,
                is_scheduling_over
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().queryMechanismClasses(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                getCurrentPage(),
                getPageSize(),
                "2",
                master_set_price_id,
                is_scheduling_over
        );
    }

    public void updateByMasterSetPriceEntity(MasterSetPriceEntity masterSetPriceEntity) {
        master_set_price_id = masterSetPriceEntity.getId();
        if ("-1".equals(master_set_price_id)) {
            master_set_price_id = null;
        }
        triggerAutoRefresh();
    }

    public void updateByStatus(Boolean is_scheduling_over) {
        this.is_scheduling_over = is_scheduling_over;
        triggerAutoRefresh();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.INSERT_MECHANISMCLASS_SUCCESS.equals(event.getAction())) {
            triggerAutoRefresh();
        }
    }

    @Override
    public void onMechanismClassList(List<MechanismClassEntity> mechanismClassEntities) {
        if (getCurrentPage() == 1) {
            initAdapter();
            this.mechanismClassEntities = null;
            if (classManagerActivity != null) {
                classManagerActivity.showMergeView(false);
            }
            if (adapter != null) {
                adapter.setData(mechanismClassEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(mechanismClassEntities);
            }
        }
        if (mechanismClassEntities == null || mechanismClassEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }

    }

    @Override
    public void onMergeClassList(List<MechanismClassEntity> mechanismClassEntities) {
        this.mechanismClassEntities = mechanismClassEntities;
    }

    public void changeAlpha(boolean isChecked) {
        SmartRefreshLayout smartRefreshLayout = getSmartRefreshLayout();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setAlpha(isChecked ? 0.8f : 1f);
        }
    }
}
