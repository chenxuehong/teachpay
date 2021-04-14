package com.huihe.base_lib.ui.widget.CalendarView.listener;

import android.view.View;

import com.huihe.base_lib.ui.widget.CalendarView.bean.DateBean;

/**
 * 日期点击接口
 */
public interface OnSingleChooseListener {
    /**
     * @param view
     * @param date
     */
    void onSingleChoose(View view, DateBean date);
}
