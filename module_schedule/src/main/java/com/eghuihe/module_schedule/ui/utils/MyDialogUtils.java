package com.eghuihe.module_schedule.ui.utils;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyDialogUtils {

    private static Map<Integer,String> checkedWeekIndexList = new HashMap<>();

    private static String[] weekArr = {
            "周一",
            "周二",
            "周三",
            "周四",
            "周五",
            "周六",
            "周日",
    };

    public static String[] getWeekArr() {
        return weekArr;
    }

    public static Map<Integer,String>  getCheckedWeekIndexList() {
        return checkedWeekIndexList;
    }

    public static String getCheckedWeekIndexStr() {
        List<String> weekIndexList = new ArrayList<>();
        Iterator<Map.Entry<Integer, String>> iterator = checkedWeekIndexList.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            String week = next.getValue();
            weekIndexList.add(week);
        }
        String weekIndexStr = StringUtils.list2String(weekIndexList, ",");
        return weekIndexStr;
    }

    public static BaseDialog showSelectWeeksDialog(Context context, final OnListener onLitener) {
        checkedWeekIndexList.clear();
        BaseDialog selectWeeksDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_select_weeks;
            }

            @Override
            protected void initParams() {
                super.initParams();
                CheckBox cBoxMonday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_monday);
                CheckBox cBoxTuesday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_tuesday);
                CheckBox cBoxWednesday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_wednesday);
                CheckBox cBoxThursday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_thursday);
                CheckBox cBoxFriday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_friday);
                CheckBox cBoxSaturday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_saturday);
                CheckBox cBoxSunday = (CheckBox) getView(R.id.dialog_select_weeks_cbox_sunday);
                cBoxMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(0,weekArr[0]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[0]);
                        }
                    }
                });
                cBoxTuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(1, weekArr[1]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[1]);
                        }
                    }
                });
                cBoxWednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(2, weekArr[2]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[2]);
                        }
                    }
                });
                cBoxThursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(3, weekArr[3]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[3]);
                        }
                    }
                });
                cBoxFriday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(4, weekArr[4]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[4]);
                        }
                    }
                });
                cBoxSaturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(5, weekArr[5]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[5]);
                        }
                    }
                });
                cBoxSunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            insert2WeekIndexList(6, weekArr[6]);
                        } else {
                            checkedWeekIndexList.remove(weekArr[6]);
                        }
                    }
                });

            }

            @Override
            protected void initEvents() {
                super.initEvents();
                getView(R.id.dialog_select_weeks_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onLitener != null) {
                            onLitener.onCancel();
                        }
                    }
                });
                getView(R.id.dialog_select_weeks_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onLitener != null) {
                            onLitener.onSure();
                        }
                    }
                });
            }
        };
        selectWeeksDialog.setPerWidth(243f / 375);
        selectWeeksDialog.setCancelOutside(false);
        selectWeeksDialog.show();
        return selectWeeksDialog;
    }

    private static void insert2WeekIndexList(int position, String week) {
        if (!checkedWeekIndexList.containsKey(position)) {
            checkedWeekIndexList.put(position,week);
        }
    }

    public interface OnListener {
        void onCancel();

        void onSure();
    }
}
