package com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling;

import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.adapter.DayListRvAdapter;
import com.eghuihe.module_schedule.ui.widget.DateScheduleView;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseSchedulingFragment<P extends BasePresenter> extends BaseMvpFragment<P> implements DayListRvAdapter.OnDaySelectListener {

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_scheduling;
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

    public void reLoadData() {
        loadData(start_time, end_time);
    }

    private void showHeadTitle(String yMDDateStr, String week) {
        tvSelectYMD.setText(DateUtils.getOtherDateStr(yMDDateStr, DateUtils.YMDFormatStr, DateUtils.YMFormatStr));
        tvRightWeek.setText(week.length() == 1 ? "周".concat(week) : week);
    }

    private void initDayList(String selectYMDStr) {
        String selectYMStr = DateUtils.getOtherDateStr(selectYMDStr, DateUtils.YMDFormatStr, DateUtils.YMFormatStr);
        rvDayList.setHorizontal();
        List<DateBean> dayList = getDayList(selectYMStr);
        rvDayList.setScrollingEnabled(false);
        rvDayList.setAdapter(new DayListRvAdapter(R.layout.item_teachpay_schedule_day, getContext(), dayList, this, selectYMDStr));
        scrollSelectDay(dayList, selectYMDStr);
    }

    private void scrollSelectDay(List<DateBean> dayList, String selectYMDStr) {
        int index = getSelectDayIndex(dayList, selectYMDStr);
        rvDayList.scrollToPosition(index);
    }

    private int getSelectDayIndex(List<DateBean> dayList, String selectYMDStr) {
        if (dayList != null) {
            for (int i = 0; i < dayList.size(); i++) {
                if (dayList.get(i).ymdStr.equals(selectYMDStr)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private List<DateBean> getDayList(String selectYMStr) {
        String yStr = DateUtils.getOtherDateStr(selectYMStr, DateUtils.YMFormatStr, DateUtils.YFormatStr);
        String mStr = DateUtils.getOtherDateStr(selectYMStr, DateUtils.YMFormatStr, DateUtils.MFormatStr2);
        int year = DateUtils.trimZero(yStr);
        int month = DateUtils.trimZero(mStr);
        List<DateBean> dayList = new ArrayList<>();
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
                DateBean dateBean = new DateBean(ymdStr, week, true);
                dayList.add(dateBean);
            } else {
                DateBean dateBean = new DateBean(ymdStr, week, false);
                dayList.add(dateBean);
            }

        }
        return dayList;
    }

    protected abstract void loadData(String start_time, String end_time);

    @Override
    public void onDayClicked(ViewHolder viewHolder, BaseSchedulingFragment.DateBean dateBean) {
        showHeadTitle(dateBean.ymdStr, dateBean.week);
        String start_time = dateBean.ymdStr.concat(" 00:00:00");
        String end_time = dateBean.ymdStr.concat(" 23:59:59");
        loadData(start_time, end_time);
    }

    public static class DateBean {
        public String ymdStr;
        public String week;
        public boolean isToday;

        public DateBean(String ymdStr, String week, boolean isToday) {
            this.ymdStr = ymdStr;
            this.week = week;
            this.isToday = isToday;
        }
    }
}
