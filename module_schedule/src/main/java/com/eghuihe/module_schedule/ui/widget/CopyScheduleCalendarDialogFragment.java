package com.eghuihe.module_schedule.ui.widget;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.widget.CalendarView.bean.DateBean;
import com.huihe.base_lib.ui.widget.CalendarView.listener.OnMultiChooseListener;
import com.huihe.base_lib.ui.widget.CalendarView.listener.OnPagerChangeListener;
import com.huihe.base_lib.ui.widget.CalendarView.weiget.CalendarView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CopyScheduleCalendarDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = CopyScheduleCalendarDialogFragment.class.getSimpleName();
    protected View mContainer;
    private Unbinder unbinder;

    @BindView(R2.id.dialogfragment_calendar_calendar)
    CalendarView calendarView;
    @BindView(R2.id.dialogfragment_calendar_tv_title)
    TextView tvTitle;

    private String start_time;

    private List<String> checkedDateList;

    public List<String> getCheckedDayList() {
        return checkedDateList;
    }

    @OnClick({
            R2.id.dialogfragment_calendar_iv_lastMonth,
            R2.id.dialogfragment_calendar_iv_nextMonth,
            R2.id.dialogfragment_calendar_tv_finish,
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.dialogfragment_calendar_iv_lastMonth) {
            calendarView.lastMonth();
        } else if (view.getId() == R.id.dialogfragment_calendar_iv_nextMonth) {
            calendarView.nextMonth();
        } else if (view.getId() == R.id.dialogfragment_calendar_tv_finish) {
            if (checkedDateList != null && checkedDateList.size() > 0) {
                Collections.sort(checkedDateList);
            }
            EventBusUtils.sendEvent(new Event(EventAction.CALENDAR_DATE_LIST, checkedDateList));
            dismiss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContainer = inflater.inflate(R.layout.fragment_dialog, container, false);
        ((ViewGroup) mContainer.findViewById(R.id.fragment_dialog_fl_container)).addView(getLayoutInflater().inflate(getLayoutId(), null));
        unbinder = ButterKnife.bind(this, mContainer);
        return mContainer;
    }

    private int getLayoutId() {
        return R.layout.dialogfragment_copy_calendar;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialog();
    }

    private void initView() {
        checkedDateList = new ArrayList<>();
        String startYMDateStr = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMFormatStr3);
        String startYMDDateStr = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr4);
        int startDay = DateUtils.getDay(start_time, DateUtils.YMDHMSFormatStr);
        int startYear = DateUtils.getYear(start_time, DateUtils.YMDHMSFormatStr);
        int endYear = startYear + 2;
        String endYearMStr = String.valueOf(endYear).concat(".12");

        String disableStartYMDDateStr = startYMDateStr.concat(".").concat(String.valueOf(startDay + 1));
        //日历init，年月日之间用点号隔开
        calendarView
                .setStartEndDate(startYMDateStr, endYearMStr)
                .setInitDate(startYMDateStr)
                .setDisableStartEndDate(disableStartYMDDateStr, "")
//                .setMultiDate(curDateStr2)
                .init();
        tvTitle.setText(startYMDateStr);
        //月份切换回调
        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {

                if (date != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < date.length; i++) {
                        if (i == 0) {
                            stringBuilder.append(date[i]).append(".");
                        } else if (i == 1) {
                            stringBuilder.append(date[i]);
                        }
                    }
                    tvTitle.setText(stringBuilder.toString().trim());
                }

            }
        });
        // 多选回调
        calendarView.setOnMultiChooseListener(new OnMultiChooseListener() {
            @Override
            public void onMultiChoose(View view, DateBean date, boolean flag) {
                int[] solar = date.getSolar();

                String ymd = getDate(solar);
                if (flag) {
                    if (!checkedDateList.contains(ymd)) {
                        checkedDateList.add(ymd);
                    }
                } else {
                    checkedDateList.remove(ymd);
                }
                if (checkedDateList != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < checkedDateList.size(); i++) {
                        stringBuilder.append(checkedDateList.get(i)).append(" ");
                    }
                    LogUtils.i(TAG, stringBuilder.toString().trim());
                }
            }
        });
    }

    private String getDate(int[] solar) {
        StringBuilder stringBuilder = new StringBuilder();
        if (solar != null && solar.length == 3) {
            stringBuilder.append(solar[0]).append("-").append(DateUtils.fillZero(solar[1])).append("-").append(DateUtils.fillZero(solar[2]));
        }
        return stringBuilder.toString().trim();
    }

    private void setDialog() {
        // 圆角背景
        Window window = getDialog().getWindow();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setStroke(0, Color.WHITE);
        window.setBackgroundDrawable(drawable);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        window.setAttributes(lp);

        // 设置宽高和位置
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                DensityUtils.getScreenHeight(getContext()) * 2 / 3);

        // 设置显示和退出动画
        window.setWindowAnimations(com.huihe.base_lib.R.style.showPopupAnimation);

        // 是否为强制对话框
        this.setCancelable(true);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    public void setSelectedYM(String start_time) {
        this.start_time = start_time;
    }
}
