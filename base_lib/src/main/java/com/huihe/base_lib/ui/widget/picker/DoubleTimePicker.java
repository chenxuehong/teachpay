package com.huihe.base_lib.ui.widget.picker;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.huihe.base_lib.ui.widget.picker.base.WheelPicker;
import com.huihe.base_lib.ui.widget.picker.base.WheelView;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class DoubleTimePicker extends WheelPicker {

    private ArrayList<String> hours = new ArrayList<>();
    private ArrayList<String> minutes = new ArrayList<>();
    private boolean resetWhileWheel = false;
    private String selectedHour = "", selectedMinute = "";
    private int startHour, startMinute = 0;
    private int endHour, endMinute = 59;

    private String hourLabel = "时", minuteLabel = "分";
    private String selectedEndMinute = "";
    private String selectedEndHour = "";

    public DoubleTimePicker(Activity activity) {
        super(activity);
        initStartHourData();
        initEndHourData();
        changeStartMinuteData(DateUtils.trimZero(selectedHour));
        changeEndMinuteData(DateUtils.trimZero(selectedEndHour));
    }

    private void initStartHourData() {
        hours.clear();
        int currentHour = 0;
        if (!resetWhileWheel) {
            currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }
        for (int i = startHour; i <= endHour; i++) {
            String hour = DateUtils.fillZero(i);
            if (!resetWhileWheel) {
                if (i == currentHour) {
                    selectedHour = hour;
                }
            }
            hours.add(hour);
        }
        if (hours.indexOf(selectedHour) == -1) {
            //当前设置的小时不在指定范围，则默认选中范围开始的小时
            selectedHour = hours.get(0);
        }
        if (!resetWhileWheel) {
            selectedMinute = DateUtils.fillZero(Calendar.getInstance().get(Calendar.MINUTE));
        }
    }

    private void initEndHourData() {
        hours.clear();
        int currentHour = 0;
        if (!resetWhileWheel) {
            currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }
        for (int i = startHour; i <= endHour; i++) {
            String hour = DateUtils.fillZero(i);
            if (!resetWhileWheel) {
                if (i == currentHour) {
                    selectedEndHour = hour;
                }
            }
            hours.add(hour);
        }
        if (hours.indexOf(selectedEndHour) == -1) {
            //当前设置的小时不在指定范围，则默认选中范围开始的小时
            selectedEndHour = hours.get(0);
        }
        if (!resetWhileWheel) {
            selectedEndMinute = DateUtils.fillZero(Calendar.getInstance().get(Calendar.MINUTE));
        }
    }

    private void changeStartMinuteData(int selectedHour) {
        minutes.clear();
        if (startHour == endHour) {
            if (startMinute > endMinute) {
                int temp = startMinute;
                startMinute = endMinute;
                endMinute = temp;
            }
            for (int i = startMinute; i <= endMinute; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else if (selectedHour == startHour) {
            for (int i = startMinute; i <= 59; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else if (selectedHour == endHour) {
            for (int i = 0; i <= endMinute; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i = 0; i <= 59; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        }
        if (minutes.indexOf(selectedMinute) == -1) {
            //当前设置的分钟不在指定范围，则默认选中范围开始的分钟
            selectedMinute = minutes.get(0);
        }
    }

    private void changeEndMinuteData(int selectedHour) {
        minutes.clear();
        if (startHour == endHour) {
            if (startMinute > endMinute) {
                int temp = startMinute;
                startMinute = endMinute;
                endMinute = temp;
            }
            for (int i = startMinute; i <= endMinute; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else if (selectedHour == startHour) {
            for (int i = startMinute; i <= 59; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else if (selectedHour == endHour) {
            for (int i = 0; i <= endMinute; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i = 0; i <= 59; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
        }
        if (minutes.indexOf(selectedEndMinute) == -1) {
            //当前设置的分钟不在指定范围，则默认选中范围开始的分钟
            selectedEndMinute = minutes.get(0);
        }
    }

    /**
     * 设置范围：开始的时分
     */
    public void setTimeRangeStart(int startHour, int startMinute) {

        boolean illegal = false;
        if (startHour < 0 || startHour > 24 || startMinute < 0 || startMinute > 59) {
            illegal = true;
        }

        if (illegal) {
            throw new IllegalArgumentException("Time out of range");
        }
        this.startHour = startHour;
        this.startMinute = startMinute;
        initStartHourData();
        initEndHourData();
    }

    /**
     * 设置范围：结束的时分
     */
    public void setTimeRangeEnd(int endHour, int endMinute) {
        boolean illegal = false;
        if (endHour < 0 || endHour > 24 || endMinute < 0 || endMinute > 59) {
            illegal = true;
        }

        if (illegal) {
            throw new IllegalArgumentException("Time out of range");
        }
        this.endHour = endHour;
        this.endMinute = endMinute;
        initStartHourData();
        initEndHourData();
    }

    /**
     * 设置默认选中的年月日时分
     */
    public void setSelectedItem(int startHour, int startMinute, int endHour, int endMinute) {
        selectedHour = DateUtils.fillZero(startHour);
        selectedMinute = DateUtils.fillZero(startMinute);
        selectedEndHour = DateUtils.fillZero(endHour);
        selectedEndMinute = DateUtils.fillZero(endMinute);
    }

    public String getSelectedHour() {
        return selectedHour;
    }

    public String getSelectedMinute() {
        return selectedMinute;
    }

    public String getSelectedEndHour() {
        return selectedEndHour;
    }

    public String getSelectedEndMinute() {
        return selectedEndMinute;
    }

    /**
     * 设置时分的显示单位
     */
    public void setLabel(String hourLabel, String minuteLabel) {
        this.hourLabel = hourLabel;
        this.minuteLabel = minuteLabel;
    }

    @NonNull
    @Override
    protected View makeCenterView() {

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, WRAP_CONTENT));

        LinearLayout titleLayout = new LinearLayout(activity);
        titleLayout.setOrientation(LinearLayout.HORIZONTAL);
        titleLayout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        layout.addView(titleLayout, titleParams);

        // 左标题
        TextView tvLeftTitle = new TextView(activity);
        tvLeftTitle.setText("开始时间");
        tvLeftTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams leftTitleParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1);
        titleLayout.addView(tvLeftTitle, leftTitleParams);

        // 右标题
        TextView tvRightTitle = new TextView(activity);
        tvRightTitle.setText("结束时间");
        tvRightTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams rightTitleParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1);
        titleLayout.addView(tvRightTitle, rightTitleParams);

        // 内容
        LinearLayout contentLayout = new LinearLayout(activity);
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);
        contentLayout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        layout.addView(contentLayout, contentParams);

        final WheelView startHourView = createWheelView();
        final WheelView startMinuteView = createWheelView();

        final WheelView endHourView = createWheelView();
        final WheelView endMinuteView = createWheelView();

        LinearLayout.LayoutParams startHourParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1);
        startHourView.setItems(hours, selectedHour);
        startHourView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedHour = hours.get(index);
                LogUtils.verbose(this, "change minutes after hour wheeled");
                changeStartMinuteData(DateUtils.trimZero(selectedHour));
                startMinuteView.setItems(minutes, selectedMinute);

                selectedEndHour = hours.get(startHourView.getSelectedIndex());
                endHourView.setItems(hours, selectedEndHour);
                selectedEndMinute = minutes.get(startMinuteView.getSelectedIndex());
                endMinuteView.setItems(minutes, selectedEndMinute);
            }
        });
        contentLayout.addView(startHourView, startHourParams);

        // 开始时钟单位
        TextView tvStartHourTitle = new TextView(activity);
        tvStartHourTitle.setText("时");
        tvStartHourTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams hourTitleParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(activity, 30), WRAP_CONTENT);
        contentLayout.addView(tvStartHourTitle, hourTitleParams);

        LinearLayout.LayoutParams startMinuteParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1);
        startMinuteView.setItems(minutes, selectedMinute);
        startMinuteView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedMinute = minutes.get(index);
                selectedEndHour = hours.get(startHourView.getSelectedIndex());
                endHourView.setItems(hours, selectedEndHour);
                selectedEndMinute = minutes.get(startMinuteView.getSelectedIndex());
                endMinuteView.setItems(minutes, selectedEndMinute);
            }
        });
        contentLayout.addView(startMinuteView, startMinuteParams);

        // 开始分钟单位
        TextView tvStartMinuteTitle = new TextView(activity);
        tvStartMinuteTitle.setText("分");
        tvStartMinuteTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams startMinuteTitleParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(activity, 30), WRAP_CONTENT);
        contentLayout.addView(tvStartMinuteTitle, startMinuteTitleParams);

        LinearLayout.LayoutParams endHourParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1);
        endHourView.setItems(hours, selectedEndHour);
        endHourView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedEndHour = hours.get(index);
                LogUtils.verbose(this, "change minutes after hour wheeled");
                changeEndMinuteData(DateUtils.trimZero(selectedEndHour));
                endHourView.setItems(hours, selectedEndHour);
                int startSelectedHourIndex = startHourView.getSelectedIndex();
                int endHourSelectedIndex = endHourView.getSelectedIndex();
                int startSelectedMinuteIndex = startMinuteView.getSelectedIndex();
                int endMinuteSelectedIndex = endMinuteView.getSelectedIndex();

                if (endHourSelectedIndex < startSelectedHourIndex) {
                    selectedEndHour = hours.get(startSelectedHourIndex);
                    endHourView.setItems(hours, selectedEndHour);
                } else if (endHourSelectedIndex == startSelectedHourIndex && endMinuteSelectedIndex < startSelectedMinuteIndex) {
                    selectedEndMinute = minutes.get(startSelectedMinuteIndex);
                    endMinuteView.setItems(minutes, selectedEndMinute);
                }

            }
        });
        contentLayout.addView(endHourView, endHourParams);

        // 结束时钟单位
        TextView tvEndHourTitle = new TextView(activity);
        tvEndHourTitle.setText("时");
        tvEndHourTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams endHourTitleParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(activity, 30), WRAP_CONTENT);
        contentLayout.addView(tvEndHourTitle, endHourTitleParams);

        LinearLayout.LayoutParams endMinuteParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1);
        endMinuteView.setItems(minutes, selectedEndMinute);
        endMinuteView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedEndMinute = minutes.get(index);
                int startSelectedHourIndex = startHourView.getSelectedIndex();
                int endHourSelectedIndex = endHourView.getSelectedIndex();
                int startSelectedMinuteIndex = startMinuteView.getSelectedIndex();
                int endMinuteSelectedIndex = endMinuteView.getSelectedIndex();

                if (endHourSelectedIndex < startSelectedHourIndex) {
                    selectedEndHour = hours.get(startSelectedHourIndex);
                    endHourView.setItems(hours, startSelectedHourIndex);
                } else if (endHourSelectedIndex == startSelectedHourIndex && endMinuteSelectedIndex < startSelectedMinuteIndex) {
                    selectedEndMinute = minutes.get(startSelectedMinuteIndex);
                    endMinuteView.setItems(minutes, startSelectedMinuteIndex);
                }
            }
        });
        contentLayout.addView(endMinuteView, endMinuteParams);

        // 结束分钟单位
        TextView tvEndMinuteTitle = new TextView(activity);
        tvEndMinuteTitle.setText("分");
        tvEndMinuteTitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams endMinuteTitleParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(activity, 30), WRAP_CONTENT);
        contentLayout.addView(tvEndMinuteTitle, endMinuteTitleParams);
        return layout;
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();
        if (onDoubleTimePickListener == null) {
            return;
        }
        String hour = getSelectedHour();
        String minute = getSelectedMinute();
        String endHour = getSelectedEndHour();
        String endMinute = getSelectedEndMinute();
        onDoubleTimePickListener.onDateTimePicked(hour, minute, endHour, endMinute);
    }

    private OnDoubleTimePickListener onDoubleTimePickListener;

    public void setOnDoubleTimePickListener(OnDoubleTimePickListener onDoubleTimePickListener) {
        this.onDoubleTimePickListener = onDoubleTimePickListener;
    }

    public interface OnDoubleTimePickListener {
        void onDateTimePicked(String hour, String minute, String endHour, String endMinute);
    }
}
