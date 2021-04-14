package com.eghuihe.module_schedule.ui.student.fragment.schedulePackage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.student.activity.TeachPayStudentAppointmentScheduleActivity;
import com.eghuihe.module_schedule.ui.student.activity.TeachPayStudentSchedulePackageDetailListActivity;
import com.eghuihe.module_schedule.ui.student.adapter.TeachPayStudentSchedulePackageRvAdapter;
import com.eghuihe.module_schedule.ui.student.mvp.TeachPayStudentSchedulePackageContract;
import com.eghuihe.module_schedule.ui.student.mvp.TeachPayStudentSchedulePackagePresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = ARouterConfig.SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEFRAGMENT)
public class TeachPayStudentSchedulePackageFragment extends BaseMvpRVRefreshFragment<TeachPayStudentSchedulePackageRvAdapter, TeachPayStudentSchedulePackagePresenter>
        implements TeachPayStudentSchedulePackageContract.View, TeachPayStudentSchedulePackageRvAdapter.OnListener {
    private static final String SP_LINE = "#\\$\\*";
    private boolean isSelect;
    private MasterSetPriceEntity masterSetPriceEntity;

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(getContext(), 15);
    }

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("预约");
        commonTitle.setVisibility(View.GONE);
        commonTitle.showLine(true);
        commonTitle.showStatusBar(true);
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected TeachPayStudentSchedulePackageRvAdapter createAdapter() {
        return new TeachPayStudentSchedulePackageRvAdapter(R.layout.item_teachpay_student_schedule_package, getContext(), this);
    }

    @Override
    protected TeachPayStudentSchedulePackagePresenter createPresenter() {
        return new TeachPayStudentSchedulePackagePresenter();
    }

    @Override
    public void doRefresh() {
        getPresenter().queryStudentExclusiveCoursesList(
                "mechanism_offline",
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    public void doLoadMore() {
        getPresenter().queryStudentExclusiveCoursesList(
                "mechanism_offline",
                LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments!=null){
            isSelect = arguments.getBoolean(ArgumentsConfig.KEY_IS_SELECT, false);
        }
        triggerAutoRefresh();
    }

    @Override
    public void onStudentCoursePackageEntityList(List<StudentCoursePackageEntity> studentCoursePackageEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(studentCoursePackageEntities);
                adapter.setOnClickListener(new EmptyRVAdapter.OnItemClickListener<StudentCoursePackageEntity>() {
                    @Override
                    public void onItemClicked(View v, StudentCoursePackageEntity studentCoursePackageEntity, int position) {

                        if (!isSelect){
                            clickedEnter(studentCoursePackageEntity);
                        }else {
                            // 携带数据
                            EventBusUtils.sendEvent(new Event(EventAction.SEND_DATA_STUDENTCOURSEPACKAGEENTITY,
                                    JsonUtil.toJson(studentCoursePackageEntity)));
                        }
                    }

                    @Override
                    public void onItemLongClicked(View v, StudentCoursePackageEntity o, int position) {

                    }
                });
            }
        } else {
            if (adapter != null) {
                adapter.addData(studentCoursePackageEntities);
            }
        }
        if (studentCoursePackageEntities == null || studentCoursePackageEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    private void clickedEnter(StudentCoursePackageEntity studentCoursePackageEntity) {
        StudentCoursePackageEntity.Map map = studentCoursePackageEntity.getMap();
        if (map != null) {
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                boolean is_singleCourse = false;
                String titile_url = masterSetPriceEntity.getTitile_url();
                String childTitle = "";
                if (!TextUtils.isEmpty(titile_url)) {
                    String[] split = titile_url.split(SP_LINE);
                    if (split != null && split.length == 1) {
                        is_singleCourse = true;
                        childTitle = split[0];
                    } else if (split == null) {
                        is_singleCourse = true;
                    } else {
                        is_singleCourse = false;
                    }
                } else {
                    is_singleCourse = false;
                }
                if (is_singleCourse) {
                    String appointment_type = masterSetPriceEntity.getAppointment_type();
                    if ("appointment".equals(appointment_type)) {
                        Intent intent = new Intent(getContext(), TeachPayStudentAppointmentScheduleActivity.class);
                        intent.putExtra(ArgumentsConfig.KEY_TITLE, childTitle);
                        intent.putExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY, JsonUtil.toJson(studentCoursePackageEntity));
                        startActivity(intent);
                    } else {
                        String title = masterSetPriceEntity.getTitle();
                        Intent intent = new Intent(getContext(), TeachPayStudentSchedulePackageDetailListActivity.class);
                        intent.putExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY, JsonUtil.toJson(studentCoursePackageEntity));
                        intent.putExtra(ArgumentsConfig.KEY_ID, studentCoursePackageEntity.getId());
                        intent.putExtra(ArgumentsConfig.KEY_TITLE, title);
                        intent.putExtra(ArgumentsConfig.KEY_TITLE_URL, titile_url);
                        startActivity(intent);
                    }
                } else {
                    String title = masterSetPriceEntity.getTitle();
                    Intent intent = new Intent(getContext(), TeachPayStudentSchedulePackageDetailListActivity.class);
                    intent.putExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY, JsonUtil.toJson(studentCoursePackageEntity));
                    intent.putExtra(ArgumentsConfig.KEY_ID, studentCoursePackageEntity.getId());
                    intent.putExtra(ArgumentsConfig.KEY_TITLE, title);
                    intent.putExtra(ArgumentsConfig.KEY_TITLE_URL, titile_url);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public void retry() {
        super.retry();
        doRefresh();
    }

    @Override
    public void onGoAppointment(ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity) {
        clickedEnter(studentCoursePackageEntity);
    }

    @Override
    public void onPingtuan(ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity) {
        StudentCoursePackageEntity.Map map = studentCoursePackageEntity.getMap();
        if (map != null) {
             masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                getPresenter().insertUserGrouping(
                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                        masterSetPriceEntity.getId(),
                        studentCoursePackageEntity.getId()
                );
            }
        }

    }

    @Override
    public void onPingGroupSuccess(String group_id) {
        if (masterSetPriceEntity!=null){
            Map<String, String> map = new HashMap<>();
            map.put(ArgumentsConfig.KEY_URL,
                    AppConfigs.Cooperation.ZH.PING_TUAN.concat(group_id));
            map.put(ArgumentsConfig.KEY_TITLE,
                    masterSetPriceEntity.getTitle());
            map.put(ArgumentsConfig.KEY_IMGURL,
                    masterSetPriceEntity.getFace_url());
            map.put(ArgumentsConfig.KEY_WXPATH,
                    AppConfigs.Cooperation.ZH.WX_PATH.concat(group_id));
            EventBusUtils.sendEvent(new Event(EventAction.PING_GROUP,map));
        }


    }

    @Override
    public void onViewPingtuan(ViewHolder viewHolder, StudentCoursePackageEntity studentCoursePackageEntity) {
        StudentCoursePackageEntity.Map map1 = studentCoursePackageEntity.getMap();
        if (map1!=null){
            MasterSetPriceEntity masterSetPriceEntity = map1.getMasterSetPriceEntity();
            if (masterSetPriceEntity!=null){
                String user_group_id = studentCoursePackageEntity.getUser_group_id();
                Map<String, String> map = new HashMap<>();
                map.put(ArgumentsConfig.KEY_URL,
                        AppConfigs.Cooperation.ZH.PING_TUAN.concat(user_group_id)
                                .concat("+")
                                .concat(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id()));
                map.put(ArgumentsConfig.KEY_TITLE,
                        masterSetPriceEntity.getTitle());
                map.put(ArgumentsConfig.KEY_IMGURL,
                        masterSetPriceEntity.getFace_url());
                map.put(ArgumentsConfig.KEY_WXPATH,
                        AppConfigs.Cooperation.ZH.WX_PATH.concat(user_group_id));
                EventBusUtils.sendEvent(new Event(EventAction.PING_GROUP,map));
            }
        }

    }

}
