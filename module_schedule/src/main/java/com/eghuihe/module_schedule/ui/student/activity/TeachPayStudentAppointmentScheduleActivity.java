package com.eghuihe.module_schedule.ui.student.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.adapter.DayListRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling.BaseSchedulingFragment;
import com.eghuihe.module_schedule.ui.student.mvp.TeachPayStudentAppointmentScheduleContract;
import com.eghuihe.module_schedule.ui.student.mvp.TeachPayStudentAppointmentSchedulePresenter;
import com.eghuihe.module_schedule.ui.widget.DateScheduleView;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeachPayStudentAppointmentScheduleActivity extends BaseMvpTitleActivity<TeachPayStudentAppointmentSchedulePresenter>
        implements TeachPayStudentAppointmentScheduleContract.View, DayListRvAdapter.OnDaySelectListener {


    @BindView(R2.id.fm_base_scheduling_tv_selectYMD)
    TextView tvSelectYMD;
    @BindView(R2.id.fm_base_scheduling_tv_week)
    TextView tvRightWeek;
    @BindView(R2.id.fm_base_scheduling_dateScheduleView)
    public DateScheduleView dateScheduleView;

    @BindView(R2.id.fm_base_scheduling_rv_daylist)
    RecyclerViewFixed rvDayList;
    private static String[] weeks = {
            "一",
            "二",
            "三",
            "四",
            "五",
            "六",
            "日"
    };
    private StudentCoursePackageEntity studentCoursePackageEntity;
    private String studycard_id;
    private String start_time;
    private String end_time;

    @OnClick({
            R2.id.fm_base_scheduling_tv_selectYMD
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.fm_base_scheduling_tv_selectYMD) {
            showSelectYMDDialog();
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("预约");
    }

    private void showSelectYMDDialog() {
        DatePicker datePicker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
        datePicker.setLabel(
                getResources().getString(com.huihe.base_lib.R.string.year),
                getResources().getString(com.huihe.base_lib.R.string.month),
                getResources().getString(com.huihe.base_lib.R.string.day));
        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                String yMDDateStr = year.concat("-").concat(month).concat("-").concat(day);
                String week = DateUtils.getWeek(yMDDateStr, DateUtils.YMDFormatStr);
                showHeadTitle(yMDDateStr, week);
                initDayList(yMDDateStr);
                start_time = yMDDateStr.concat(" 00:00:00");
                end_time = yMDDateStr.concat(" 23:59:59");
                loadData(start_time, end_time);
            }
        });
        datePicker.setRangeStart(DateUtils.getCurYear() - 10, 1, 1);
        datePicker.setRangeEnd(DateUtils.getCurYear() + 10, 1, 1);
        datePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        datePicker.setResetWhileWheel(false);
        datePicker.show();
    }

    private void loadData(String start_time, String end_time) {

        StudentCoursePackageEntity.Map map = studentCoursePackageEntity.getMap();
        if (map != null) {
            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
            if (masterSetPriceEntity != null) {
                studycard_id = masterSetPriceEntity.getId();
                getPresenter().queryMechanismOfflineAppointmentSchedule(
                        "appointment",
                        end_time,
                        "teach_paypal_course",
                        studycard_id,
                        studentCoursePackageEntity.getMechanism_id(),
                        String.valueOf(DateUtils.getCurTimeZoneOffset()),
                        start_time,
                        "mechanism_offline"
                );
            }
        }

    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_student_appointment_schedule;
    }

    @Override
    protected TeachPayStudentAppointmentSchedulePresenter createPresenter() {
        return new TeachPayStudentAppointmentSchedulePresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_STUDENTCOURSEPACKAGEENTITY);
            studentCoursePackageEntity = JsonUtil.fromJson(json, StudentCoursePackageEntity.class);
        }
    }

    @Override
    protected void initData() {
        String start_time1 = studentCoursePackageEntity.getStart_time();
        String yMDDateStr = DateUtils.getOtherDateStr(start_time1, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        String week = DateUtils.getWeek(yMDDateStr, DateUtils.YMDFormatStr);
        showHeadTitle(yMDDateStr, week);
        initDayList(yMDDateStr);
        start_time = yMDDateStr.concat(" 00:00:00");
        end_time = yMDDateStr.concat(" 23:59:59");
        loadData(start_time, end_time);
    }

    private void showHeadTitle(String yMDDateStr, String week) {
        tvSelectYMD.setText(DateUtils.getOtherDateStr(yMDDateStr, DateUtils.YMDFormatStr, DateUtils.YMFormatStr));
        tvRightWeek.setText(week.length() == 1 ? "周".concat(week) : week);
    }

    private void initDayList(String selectYMDStr) {
        String selectYMStr = DateUtils.getOtherDateStr(selectYMDStr, DateUtils.YMDFormatStr, DateUtils.YMFormatStr);
        rvDayList.setHorizontal();
        List<BaseSchedulingFragment.DateBean> dayList = getDayList(selectYMStr);
        rvDayList.setScrollingEnabled(false);
        rvDayList.setAdapter(new DayListRvAdapter(R.layout.item_teachpay_schedule_day, this, dayList, this, selectYMDStr));
        scrollSelectDay(dayList, selectYMDStr);
    }

    private void scrollSelectDay(List<BaseSchedulingFragment.DateBean> dayList, String selectYMDStr) {
        int index = getSelectDayIndex(dayList, selectYMDStr);
        rvDayList.scrollToPosition(index);
    }

    private int getSelectDayIndex(List<BaseSchedulingFragment.DateBean> dayList, String selectYMDStr) {
        if (dayList != null) {
            for (int i = 0; i < dayList.size(); i++) {
                if (dayList.get(i).ymdStr.equals(selectYMDStr)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private List<BaseSchedulingFragment.DateBean> getDayList(String selectYMStr) {
        String yStr = DateUtils.getOtherDateStr(selectYMStr, DateUtils.YMFormatStr, DateUtils.YFormatStr);
        String mStr = DateUtils.getOtherDateStr(selectYMStr, DateUtils.YMFormatStr, DateUtils.MFormatStr2);
        int year = DateUtils.trimZero(yStr);
        int month = DateUtils.trimZero(mStr);
        List<BaseSchedulingFragment.DateBean> dayList = new ArrayList<>();
        // 计算year年month月的天数
        int monthOfDay = DateUtils.getMonthOfDay(year, month);
        for (int i = 0; i < monthOfDay; i++) {
            String fillDay = DateUtils.fillZero(i + 1);
            String ymdStr = selectYMStr.concat("-").concat(fillDay);
            int dayofWeek = DateUtils.getDayofWeek(ymdStr, DateUtils.YMDFormatStr);
            if (dayofWeek == 0) {
                dayofWeek = 6;
            } else {
                dayofWeek--;
            }

            if (dayofWeek > 6) {
                dayofWeek = 6;
            }
            String week = weeks[dayofWeek];
            String toDay = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
            if (toDay.equals(ymdStr)) {
                BaseSchedulingFragment.DateBean dateBean = new BaseSchedulingFragment.DateBean(ymdStr, week, true);
                dayList.add(dateBean);
            } else {
                BaseSchedulingFragment.DateBean dateBean = new BaseSchedulingFragment.DateBean(ymdStr, week, false);
                dayList.add(dateBean);
            }
        }
        return dayList;
    }

    @Override
    public void onDayClicked(ViewHolder viewHolder, BaseSchedulingFragment.DateBean dateBean) {
        showHeadTitle(dateBean.ymdStr, dateBean.week);
        start_time = dateBean.ymdStr.concat(" 00:00:00");
        end_time = dateBean.ymdStr.concat(" 23:59:59");
        loadData(start_time, end_time);
    }

    @Override
    public void onMechanismOfflineAppointmentSchedule(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {
        List<DateScheduleView.ItemBean> itemBeans = convertModel(mechanismOfflineScheduleEntities);
        dateScheduleView.setOnScheduleListener(itemBeans,
                new DateScheduleView.OnScheduleListener<MechanismOfflineScheduleEntity>() {
                    @Override
                    public void onScheduleItem(FrameLayout itemView, final MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_mechanism_schedule, null);
                        TextView tvTime = view.findViewById(R.id.item_mechanism_schedule_tv_time);
                        TextView tvTitle = view.findViewById(R.id.item_mechanism_schedule_tv_title);
                        TextView tvChildTitle = view.findViewById(R.id.item_mechanism_schedule_tv_childTitle);
                        TextView tvEdit = view.findViewById(R.id.item_mechanism_schedule_tv_edit);

                        String start_time = mechanismOfflineScheduleEntity.getStart_time();
                        start_time = DateUtils.translateZoneTimeStr(start_time, mechanismOfflineScheduleEntity.getOffset(), DateUtils.getCurTimeZoneOffset(), DateUtils.YMDHMSFormatStr);
                        start_time = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.HMFormatStr);
                        String nickName = "";
                        String studentNum = "0";
                        MechanismOfflineScheduleEntity.Map map = mechanismOfflineScheduleEntity.getMap();
                        if (map != null) {
                            UserInfoEntity masterInfo = map.getMasterInfo();
                            MasterSetPriceEntity masterSetPriceEntity = map.getMasterSetPriceEntity();
                            if (masterInfo != null) {
                                nickName = masterInfo.getNick_name();
                            }
                            if (masterSetPriceEntity != null) {
                                String title = masterSetPriceEntity.getTitle();
                                tvTitle.setText(title);
                            }
                            studentNum = map.getStudentNum();
                        }
                        String status = mechanismOfflineScheduleEntity.getStatus();
                        String connect_peoplenum = mechanismOfflineScheduleEntity.getConnect_peoplenum();
                        if ("1".equals(status)){
                            tvEdit.setText("可预约");
                        }else if ("2".equals(status)){
                            tvEdit.setText("已结束");
                        }
                        if (!TextUtils.isEmpty(studentNum)&&studentNum.equals(connect_peoplenum)){
                            tvEdit.setText("已约满");
                        }
                        tvChildTitle.setText(mechanismOfflineScheduleEntity.getTitle());
                        tvTime.setText(start_time.concat(" ").concat(nickName));
                        tvEdit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // 点击预约
                                String status = mechanismOfflineScheduleEntity.getStatus();
                                if (!"1".equals(status)) {
                                    ToastUtils.showShortToast(TeachPayStudentAppointmentScheduleActivity.this, "不可预约");
                                } else {
                                    DialogUtils.showTipDialog(TeachPayStudentAppointmentScheduleActivity.this,
                                            "预约提醒",
                                            "确定要预约此课时？",
                                            "取消", "确定",
                                            new DialogUtils.OnListener() {
                                                @Override
                                                public void okClicked() {
                                                    Map<String, String> params = new HashMap<>();
                                                    params.put(ArgumentsConfig.KEY_USER_STUDY_CARD_ID, studentCoursePackageEntity.getId());
                                                    params.put(ArgumentsConfig.KEY_STUDYCARD_ID, studycard_id);
                                                    params.put(ArgumentsConfig.KEY_MECHANISMOFFLINESCHEDULEENTITY, JsonUtil.toJson(mechanismOfflineScheduleEntity));
                                                    ActivityToActivity.toActivity(ARouterConfig.SCHEDULE_COURSEAPPOINTMENTPAY, params);
                                                }

                                                @Override
                                                public void cancelClicked() {

                                                }
                                            });
                                }
                            }
                        });
                        itemView.addView(view);
                    }
                });
    }

    private List<DateScheduleView.ItemBean> convertModel(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {
        List<DateScheduleView.ItemBean> itemBeans = new ArrayList<>();
        List<MechanismOfflineScheduleEntity> amList = new ArrayList<>();
        List<MechanismOfflineScheduleEntity> pmList = new ArrayList<>();
        DateScheduleView.ItemBean amitemBeans = new DateScheduleView.ItemBean(true, amList);
        DateScheduleView.ItemBean pmitemBeans = new DateScheduleView.ItemBean(false, pmList);
        if (mechanismOfflineScheduleEntities != null) {
            for (int i = 0; i < mechanismOfflineScheduleEntities.size(); i++) {
                MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity = mechanismOfflineScheduleEntities.get(i);
                if (mechanismOfflineScheduleEntity != null) {
                    String start_time = mechanismOfflineScheduleEntity.getStart_time();
                    String apm = DateUtils.getApm(start_time, DateUtils.YMDHMSFormatStr);
                    if ("上午".equals(apm)) {
                        amList.add(mechanismOfflineScheduleEntity);
                    } else {
                        pmList.add(mechanismOfflineScheduleEntity);
                    }
                }
            }
        }
        itemBeans.add(amitemBeans);
        itemBeans.add(pmitemBeans);
        return itemBeans;
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.PAY_SUCCESS.equals(event.getAction())) {
            loadData(start_time, end_time);
        }
    }
}
