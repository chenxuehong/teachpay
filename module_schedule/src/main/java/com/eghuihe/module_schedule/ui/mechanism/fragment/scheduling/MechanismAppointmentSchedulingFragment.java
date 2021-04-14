package com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.activity.ArrangeMechanismCourseActivity;
import com.eghuihe.module_schedule.ui.mechanism.adapter.DayListRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.adapter.MechanismAppointmentOfflineScheduleRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismSchedulingContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.MechanismSchedulingPresenter;
import com.eghuihe.module_schedule.ui.widget.CopyScheduleCalendarDialogFragment;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.DragFloatActionButton;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 排课表-预约排课
 */
public class MechanismAppointmentSchedulingFragment extends BaseMvpFragment<MechanismSchedulingPresenter>
        implements MechanismSchedulingContract.View, DayListRvAdapter.OnDaySelectListener, MechanismAppointmentOfflineScheduleRvAdapter.OnListener {

    @BindView(R2.id.fragment_appointment_schedule_tv_selectYMD)
    TextView tvSelectYMD;
    @BindView(R2.id.fragment_appointment_schedule_tv_week)
    TextView tvRightWeek;

    @BindView(R2.id.fragment_appointment_schedule_rv_daylist)
    RecyclerViewFixed rvDayList;
    @BindView(R2.id.fragment_appointment_schedule_rv_course)
    RecyclerViewFixed rvCourseList;
    @BindView(R2.id.fragment_appointment_schedule_dragFloatActionBtn)
    DragFloatActionButton dragFloatActionButton;
    private CopyScheduleCalendarDialogFragment calendarDialogFragment;

    private MechanismOfflineScheduleEntity selectMechanismOfflineScheduleEntity;

    @OnClick({
            R2.id.fragment_appointment_schedule_tv_selectYMD
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.fragment_appointment_schedule_tv_selectYMD) {
            showSelectYMDDialog();
        }
    }

    private void showSelectYMDDialog() {
        DatePicker datePicker = new DatePicker(getActivity(), DatePicker.YEAR_MONTH_DAY);
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
                String start_time = yMDDateStr.concat(" 00:00:00");
                String end_time = yMDDateStr.concat(" 23:59:59");
                loadData(start_time, end_time);
            }
        });
        datePicker.setRangeStart(DateUtils.getCurYear() - 10, 1, 1);
        datePicker.setRangeEnd(DateUtils.getCurYear() + 10, 1, 1);
        datePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        datePicker.setResetWhileWheel(false);
        datePicker.show();
    }

    private static String[] weeks = {
            "一",
            "二",
            "三",
            "四",
            "五",
            "六",
            "日"
    };
    private String start_time;
    private String end_time;

    @Override
    protected MechanismSchedulingPresenter createPresenter() {
        return new MechanismSchedulingPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        dragFloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 预约课程
                Intent intent = new Intent(getContext(), ArrangeMechanismCourseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        String yMDDateStr = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
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
        rvDayList.setAdapter(new DayListRvAdapter(R.layout.item_teachpay_schedule_day, getContext(), dayList, this, selectYMDStr));
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

    private void loadData(String start_time, String end_time) {
        getPresenter().queryMechanismOfflineSchedule(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                null,
                start_time,
                end_time,
                String.valueOf(DateUtils.getCurTimeZoneOffset()),
                "teach_paypal_course",
                "appointment"

        );
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appointment_schedule;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.ARRANGE_COURSE.equals(event.getAction())) {
            String appointment_type = (String) event.getData();
            if ("appointment".equals(appointment_type)) {
                reLoadData();
            }
        } else if (EventAction.INSERT_MECHANISM_COURSE.equals(event.getAction())) {
            reLoadData();
        } else if (EventAction.CALENDAR_DATE_LIST.equals(event.getAction())) {
            List<String> checkedDateList = (List<String>) event.getData();
            String checkedDateStr = StringUtils.list2String(checkedDateList, ",");
            if (selectMechanismOfflineScheduleEntity != null) {

                getPresenter().insertCopyCourse(
                        selectMechanismOfflineScheduleEntity.getId(),
                        selectMechanismOfflineScheduleEntity.getStart_time(),
                        selectMechanismOfflineScheduleEntity.getEnd_time(),
                        checkedDateStr,
                        "date"
                );
            }
        }
    }

    @Override
    public void onMechanismOfflineScheduleList(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {
        if (rvCourseList != null) {
            rvCourseList.setVertical(1);
            rvCourseList.addGridViewItemDecoration(1, DensityUtils.dp2px(getContext(), 15));
            MechanismAppointmentOfflineScheduleRvAdapter mechanismAppointmentOfflineScheduleRvAdapter =
                    new MechanismAppointmentOfflineScheduleRvAdapter(R.layout.item_mechanism_appointment_schedule, getContext(), this);
            rvCourseList.setAdapter(mechanismAppointmentOfflineScheduleRvAdapter);
            mechanismAppointmentOfflineScheduleRvAdapter.setData(mechanismOfflineScheduleEntities);
        }
    }

    @Override
    public void onDeleteCourseSuccess() {
        reLoadData();
        ToastUtils.showShortToast(getContext(), "取消成功");
    }

    @Override
    public void onCopyCourseSuccess() {
        ToastUtils.showShortToast(getContext(), "复制成功");
    }

    private void reLoadData() {
        loadData(start_time, end_time);
    }

    @Override
    public void onDayClicked(ViewHolder viewHolder, BaseSchedulingFragment.DateBean dateBean) {
        showHeadTitle(dateBean.ymdStr, dateBean.week);
        String start_time = dateBean.ymdStr.concat(" 00:00:00");
        String end_time = dateBean.ymdStr.concat(" 23:59:59");
        loadData(start_time, end_time);
    }

    @Override
    public void onEdit(ViewHolder viewHolder, MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity) {

    }

    @Override
    public void onCopy(ViewHolder viewHolder, MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity) {

        selectMechanismOfflineScheduleEntity = mechanismOfflineScheduleEntity;
        String start_time = mechanismOfflineScheduleEntity.getStart_time();
        calendarDialogFragment = new CopyScheduleCalendarDialogFragment();

        calendarDialogFragment.setSelectedYM(start_time);
        calendarDialogFragment.show(getChildFragmentManager(), "CopyScheduleCalendarDialogFragment");
    }

    @Override
    public void onDestroy() {
        if (calendarDialogFragment != null) {
            calendarDialogFragment.dismiss();
        }
        super.onDestroy();
    }
}
