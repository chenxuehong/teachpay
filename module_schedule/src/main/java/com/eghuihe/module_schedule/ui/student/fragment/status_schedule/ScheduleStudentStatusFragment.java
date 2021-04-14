package com.eghuihe.module_schedule.ui.student.fragment.status_schedule;

import android.os.Bundle;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.student.adapter.StudentScheduleRvAdapter;
import com.eghuihe.module_schedule.ui.student.mvp.TeachingPayScheduleContract;
import com.eghuihe.module_schedule.ui.student.mvp.TeachingPaySchedulePresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleStudentStatusFragment extends BaseMvpRVRefreshFragment<StudentScheduleRvAdapter, TeachingPaySchedulePresenter>
        implements TeachingPayScheduleContract.View, StudentScheduleRvAdapter.OnStatusListener {

    private String status;
    private Boolean is_comment;

    //    2 预约 3 完成 8 待付款 9 付款完成
    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected StudentScheduleRvAdapter createAdapter() {
        return new StudentScheduleRvAdapter(R.layout.item_student_schedule, getContext(), this);
    }

    @Override
    protected TeachingPaySchedulePresenter createPresenter() {
        return new TeachingPaySchedulePresenter();
    }

    @Override
    public void doRefresh() {

        getPresenter().queryOfflineSchedule(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                "mechanism_offline",
                status,
                is_comment,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().queryOfflineSchedule(
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                "mechanism_offline",
                status,
                is_comment,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            status = arguments.getString(ArgumentsConfig.KEY_STATUS);
            is_comment = arguments.getBoolean(ArgumentsConfig.KEY_IS_COMMENT);
        }
        triggerAutoRefresh();
    }

    @Override
    public void onOfflineScheduleList(List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(studentScheduleEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(studentScheduleEntities);
            }
        }

        if (studentScheduleEntities == null || studentScheduleEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onSignClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity) {

        getPresenter().sign(
                studentScheduleEntity.getId(),
                "3"
        );
    }

    @Override
    public void onCancelClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity) {
        DialogUtils.showTipDialog(
                getContext(),
                "提醒",
                "是否取消课程",
                "取消",
                "确定",
                new DialogUtils.OnListener() {
                    @Override
                    public void okClicked() {
                        getPresenter().updateCancelCourse(studentScheduleEntity.getId());
                    }

                    @Override
                    public void cancelClicked() {

                    }
                }
        );

    }

    @Override
    public void onPayClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity) {
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_STUDENT_SCHEDULE_ENTITY, JsonUtil.toJson(studentScheduleEntity));
        ActivityToActivity.toActivity(
                ARouterConfig.SCHEDULE_COURSEPAYACTIVITY,
                params);
    }

    @Override
    public void onCommentClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity) {

        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_STUDENT_SCHEDULE_ENTITY, JsonUtil.toJson(studentScheduleEntity));
        ActivityToActivity.toActivity(ARouterConfig.SCHEDULE_SCHEDULECOMMENTACTIVITY, params);
    }

    @Override
    public void onViewSummaryClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity) {
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_APPOINTMENT_ID, studentScheduleEntity.getAppointment_id());
        ActivityToActivity.toActivity(ARouterConfig.SCHEDULE_SUMMARYDETAILACTIVITY, params);
    }

    @Override
    public void onViewCommentClicked(ViewHolder viewHolder, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity) {
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_APPOINTMENT_ID, studentScheduleEntity.getAppointment_id());
        ActivityToActivity.toActivity(ARouterConfig.ME_MECHANISMCOURSECOMMENTLISTACTIVITY, params);
    }

    @Override
    public void onUpdatestatusSuccess() {
        doRefresh();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.PAY_SUCCESS.equals(event.getAction())) {
            doRefresh();
        } else if (EventAction.STIDENT_COMMENT_SUCCESS.equals(event.getAction())) {
            doRefresh();
        }
    }

    @Override
    public void retry() {
        super.retry();
        doRefresh();
    }
}
