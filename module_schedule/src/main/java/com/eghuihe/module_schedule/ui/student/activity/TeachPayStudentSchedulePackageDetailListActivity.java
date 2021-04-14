package com.eghuihe.module_schedule.ui.student.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.ui.student.Bean.SchedulePackageDetailBean;
import com.eghuihe.module_schedule.ui.student.adapter.TeachPayStudentSchedulePackageDetailListRvAdapter;
import com.eghuihe.module_schedule.ui.student.mvp.TeachPayStudentSchedulePackageDetailListContract;
import com.eghuihe.module_schedule.ui.student.mvp.TeachPayStudentSchedulePackageDetailListPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LongOrderPayInfoEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.ConvertUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.pay.PayUtils;
import com.huihe.base_lib.utils.pay.alipay.AlipayUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @desc 课程包二级页面
 */
@Route(path = ARouterConfig.SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEDETAILLISTACTIVITY)
public class TeachPayStudentSchedulePackageDetailListActivity extends BaseMvpRvRefreshTitleActivity
        <TeachPayStudentSchedulePackageDetailListRvAdapter, TeachPayStudentSchedulePackageDetailListPresenter>
        implements TeachPayStudentSchedulePackageDetailListContract.View, TeachPayStudentSchedulePackageDetailListRvAdapter.OnAppointmentListener {

    private String LINE = "#\\$\\*";
    private String title;
    private String titleurl;
    private List<String> titleList;
    private String id;
    private TextView tvBottomTitle;
    private StudentCoursePackageEntity studentCoursePackageEntity;
    private View bottomView;
    private String appointment_type;
    private CustomPopupWindow payDialog;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        titleList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra(ArgumentsConfig.KEY_ID);
            title = intent.getStringExtra(ArgumentsConfig.KEY_TITLE);
            titleurl = intent.getStringExtra(ArgumentsConfig.KEY_TITLE_URL);
            String json = intent.getStringExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY);
            studentCoursePackageEntity = JsonUtil.fromJson(json, StudentCoursePackageEntity.class);
            if (!TextUtils.isEmpty(titleurl)) {
                String[] titleArr = titleurl.split(LINE);
                titleList = ConvertUtils.toList(titleArr);
            }
        }
        commonTitle.setTitle(title);
    }

    @Override
    protected void initView() {
        super.initView();
        getSmartRefreshLayout().setBackgroundColor(getResources().getColor(R.color.color_88e6e6e6));
        bottomView = View.inflate(this, R.layout.layout_student_schedule_package_bottom, null);
        tvBottomTitle = bottomView.findViewById(R.id.layout_student_schedule_package_bottom_tv_title);
//        getFlBottom().addView(bottomView);
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(this, 15);
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryStudentOfflineSchedule(
                id,
                getCurrentPage(),
                getPageSize()
        );
        getPresenter().queryLongOrderPayInfo(id);
    }

    @Override
    protected void doLoadMore() {
        getPresenter().queryStudentOfflineSchedule(
                id,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected TeachPayStudentSchedulePackageDetailListRvAdapter getAdapter() {
        return new TeachPayStudentSchedulePackageDetailListRvAdapter(R.layout.item_teachpay_student_schedule_package_detail, this, this);
    }

    @Override
    protected TeachPayStudentSchedulePackageDetailListPresenter createPresenter() {
        return new TeachPayStudentSchedulePackageDetailListPresenter();
    }

    @Override
    protected void initData() {
        String course_num = studentCoursePackageEntity.getCourse_num();
        bottomView.setVisibility(studentCoursePackageEntity.getIs_one_time_payment() ? View.GONE : View.VISIBLE);
        triggerRefreshData();
    }

    @Override
    public void onStudentScheduleEntityList(List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities) {
        List<SchedulePackageDetailBean> schedulePackageDetailBeans = convertModel(studentScheduleEntities);
        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(schedulePackageDetailBeans);
                adapter.setOnClickListener(new EmptyRVAdapter.OnItemClickListener<SchedulePackageDetailBean>() {
                    @Override
                    public void onItemClicked(View v, SchedulePackageDetailBean schedulePackageDetailBean, int position) {

                    }

                    @Override
                    public void onItemLongClicked(View v, SchedulePackageDetailBean o, int position) {

                    }
                });
            }
        } else {
            if (adapter != null) {
                adapter.addData(schedulePackageDetailBeans);
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
    public void onGetLongOrderInfoDetail(LongOrderPayInfoEntity longOrderPayInfoEntity) {
        String payCourseCount = longOrderPayInfoEntity.payCourseCount;
        String payNum = longOrderPayInfoEntity.getPayNum();
        payCourseCount = payCourseCount == null ? "0" : payCourseCount;
        payNum = payNum == null ? "0" : payNum;
        tvBottomTitle.setText("购买剩余".concat(payCourseCount).concat("节，").concat("共".concat(payNum).concat("元")));
        String finalPayNum = payNum;
        String finalPayCourseCount = payCourseCount;
        tvBottomTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 转长单
                payDialog = PayUtils.popPayWindow(
                        getContext(),
                        finalPayNum,
                        new PayUtils.OnListener() {
                            @Override
                            public void alipay() {
                                getPresenter().getLongOrder(
                                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                        "specialty_sheets",
                                        "android",
                                        "1",
                                        studentCoursePackageEntity.getMaster_id(),
                                        "学生端转长单支付宝支付",
                                        studentCoursePackageEntity.getStudycard_id(),
                                        "mechanism_offline",
                                        true,
                                        studentCoursePackageEntity.getId(),
                                        "user",
                                        "ali",
                                        studentCoursePackageEntity.getMechanism_id()
                                );
                            }

                            @Override
                            public void weixinPay() {
                                getPresenter().getWxLongOrder(
                                        LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                        "specialty_sheets",
                                        "android",
                                        "1",
                                        studentCoursePackageEntity.getMaster_id(),
                                        "学生端转长单微信支付",
                                        studentCoursePackageEntity.getStudycard_id(),
                                        "mechanism_offline",
                                        true,
                                        studentCoursePackageEntity.getId(),
                                        "user",
                                        "wx",
                                        studentCoursePackageEntity.getMechanism_id()
                                );
                            }

                        });
            }
        });
    }

    @Override
    public void onGetAliOrder(String orderInfo) {
        if (!TextUtils.isEmpty(orderInfo)) {
            AlipayUtils.getInstance(getContext()).pay(orderInfo,
                    null);
        }

    }

    @Override
    public void onGetWxOrder(WxPayModel.WxPayEntity wxPayEntity) {
        if (wxPayEntity != null)
            EventBusUtils.sendEvent(new Event(EventAction.WX_PAY, wxPayEntity));
    }

    private List<SchedulePackageDetailBean> convertModel(List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities) {

        StudentCoursePackageEntity.Map map = studentCoursePackageEntity.getMap();
        if (map != null) {
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                appointment_type = masterSetPriceEntity.getAppointment_type();
            }
        }
        List<SchedulePackageDetailBean> schedulePackageDetailBeans = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            String title = titleList.get(i);
            StudentScheduleModel.StudentScheduleEntity studentScheduleEntity = contains(studentScheduleEntities, title);
            if (studentScheduleEntity != null) {
                schedulePackageDetailBeans.add(new SchedulePackageDetailBean(title, studentScheduleEntity, appointment_type));
            } else {
                schedulePackageDetailBeans.add(new SchedulePackageDetailBean(title, null, appointment_type));
            }
        }
        return schedulePackageDetailBeans;
    }

    private StudentScheduleModel.StudentScheduleEntity contains(List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities, String title) {
        if (studentScheduleEntities == null) {
            return null;
        }
        Iterator<StudentScheduleModel.StudentScheduleEntity> iterator = studentScheduleEntities.iterator();
        while (iterator.hasNext()) {
            StudentScheduleModel.StudentScheduleEntity studentScheduleEntity = iterator.next();
            String title2 = studentScheduleEntity.getTitle();
            if (title2 != null && title2.contains(title)) {
                return studentScheduleEntity;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public void onAppointmentClicked(ViewHolder viewHolder, SchedulePackageDetailBean schedulePackageDetailBean) {
        Intent intent = new Intent(this, TeachPayStudentAppointmentScheduleActivity.class);
        intent.putExtra(ArgumentsConfig.KEY_TITLE, schedulePackageDetailBean.title);
        intent.putExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY, JsonUtil.toJson(studentCoursePackageEntity));
        startActivity(intent);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.PAY_SUCCESS.equals(event.getAction())) {
            triggerRefreshData();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (payDialog != null) {
                payDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
