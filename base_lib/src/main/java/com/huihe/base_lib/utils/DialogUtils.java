package com.huihe.base_lib.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.picker.DateTimePicker;
import com.huihe.base_lib.utils.manager.AppManager;

public class DialogUtils {

    private static DateTimePicker beginTimePicker;
    private static BaseDialog baseDialog;


    public static BaseDialog createOkCancelDialog(final Context context, final String message, final OnOkAndCancelListener listener) {

        try {
            if (baseDialog != null && baseDialog.isShowing()) {
                baseDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_tip_nostudycard;
            }

            @Override
            protected void initEvents() {
                TextView tvTipTitle = (TextView) getView(R.id.dialog_tip_title);
                TextView tvTipContent = (TextView) getView(R.id.dialog_tip_content);
                TextView tvTipCancel = (TextView) getView(R.id.dialog_tip_cancel);
                TextView tvTipOk = (TextView) getView(R.id.dialog_tip_ok);
                tvTipTitle.setText(context.getResources().getString(R.string.prompt));

                tvTipOk.setText(context.getResources().getString(R.string.accept));
                tvTipCancel.setText(context.getResources().getString(R.string.refuse));
                tvTipContent.setText(message);
                tvTipCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.cancelClicked();
                        dismiss();
                    }
                });
                tvTipOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.okClicked();
                        dismiss();
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        return baseDialog;

    }

    public static BaseDialog showTipDialog(final Context context, final String title, final String tipContent, final String cancelStr, final String okStr, final OnListener listener) {
        try {
            if (baseDialog != null && baseDialog.isShowing()) {
                baseDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDialog = new BaseDialog(AppManager.getInstance().currentActivity()) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_tip_nostudycard;
            }

            @Override
            protected void initEvents() {
                TextView tvTipTitle = (TextView) getView(R.id.dialog_tip_title);
                TextView tvTipContent = (TextView) getView(R.id.dialog_tip_content);
                TextView tvTipCancel = (TextView) getView(R.id.dialog_tip_cancel);
                TextView tvTipOk = (TextView) getView(R.id.dialog_tip_ok);
                if (TextUtils.isEmpty(title)) {
                    tvTipTitle.setText(context.getResources().getString(R.string.prompt));
                } else {
                    tvTipTitle.setText(title);
                }

                tvTipOk.setText(okStr);
                tvTipCancel.setText(cancelStr);
                tvTipContent.setText(tipContent);
                tvTipCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        listener.cancelClicked();
                    }
                });
                tvTipOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        listener.okClicked();
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }

    public static BaseDialog showTipDialog(final Context context, final String tipContent, final String okContent, final OnListener listener) {

        if (context == null) {
            return null;
        }
        try {
            if (baseDialog != null && baseDialog.isShowing()) {
                baseDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_tip;
            }

            @Override
            protected void initEvents() {
                TextView tvTipTitle = (TextView) getView(R.id.dialog_tip2_title);
                TextView tvTipContent = (TextView) getView(R.id.dialog_tip2_content);
                TextView tvTipOk = (TextView) getView(R.id.dialog_tip2_ok);
                tvTipContent.setText(tipContent);
                tvTipOk.setText(okContent);
                tvTipOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (listener != null)
                            listener.okClicked();
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }

    /**
     * 显示开课状态对话框 （开播或关播）
     *
     * @param context
     * @param title
     * @param content
     * @param okStr
     * @param listener
     */
    public static void showClassStateDialog(Context context, final String title, final String content, final String okStr, final OnListener listener) {
        try {
            if (baseDialog != null && baseDialog.isShowing()) {
                baseDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_class_star;
            }

            @Override
            protected void initEvents() {
                TextView tvTipTitle = (TextView) getView(R.id.dialog_class_star_tv_title);
                TextView tvTipContent = (TextView) getView(R.id.dialog_class_star_tv_content);
                TextView tvTipOk = (TextView) getView(R.id.dialog_class_star_tv_ok);
                tvTipOk.setText(okStr);
                tvTipTitle.setText(title);
                tvTipContent.setText(content);
                tvTipOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        listener.okClicked();
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
    }

    public static void showEnterDialog(final Context context, final String title, final String tip, final OnEnterListener onEnterListener) {
        try {
            if (baseDialog != null && baseDialog.isShowing()) {
                baseDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_enter;
            }

            @Override
            protected void initEvents() {
                TextView tvTitle = (TextView) getView(R.id.dialog_enter_tv_title);
                final EditText etContent = (EditText) getView(R.id.dialog_enter_et_content);
                TextView tvCancel = (TextView) getView(R.id.dialog_enter_tv_cancel);
                TextView tvSure = (TextView) getView(R.id.dialog_enter_tv_sure);
                tvTitle.setText(title);
                etContent.setHint(tip);

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        onEnterListener.cancelClicked();
                    }
                });

                tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
                            ToastUtils.showLongToast(context, context.getResources().getString(R.string.Please_enter_content));
                            return;
                        }
                        dismiss();
                        onEnterListener.okClicked(etContent.getText().toString().trim());
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
    }

    public static void showDateTimePicker(Activity activity, final DatePicker.OnYearMonthDayTimePickListener onListener) {

        try {
            if (beginTimePicker != null) {
                beginTimePicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        beginTimePicker = new DateTimePicker(activity, DateTimePicker.YEAR_MONTH_DAY, DateTimePicker.HOUR_24);
        String dateStr = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
        String weekStr = "";
        if (MultiLanguageUtil.getInstance().isZh()) {
            weekStr = DateUtils.getChineseWeek(dateStr, DateUtils.YMDFormatStr);
        } else {
            weekStr = DateUtils.getEngLishWeek(dateStr, DateUtils.YMDFormatStr);
        }
        beginTimePicker.setTitleText(DateUtils.getCurYear() + " (" + weekStr + ")");
        beginTimePicker.setOnDateTimePickListener(onListener);
        beginTimePicker.setResetWhileWheel(false);
        beginTimePicker.setDateRangeStart(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        String endTime = DateUtils.getCurDateStr(DateUtils.YMDHMSFormatStr);
        int year = DateUtils.getYear(endTime, DateUtils.YMDHMSFormatStr) + 4;
        int month = 12;
        int day = DateUtils.getMonthOfDay(year, 12);
        beginTimePicker.setDateRangeEnd(year, month, day);
        beginTimePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay(), DateUtils.getCurHour(), DateUtils.getCurMinute());
        beginTimePicker.show();
    }

    public static DateTimePicker showDateTimePicker(
            Activity activity,
            String startTime,
            String startFormatTimeStr,
            final DatePicker.OnYearMonthDayTimePickListener onListener) {

        DateTimePicker beginTimePicker = null;
        try {
            beginTimePicker = new DateTimePicker(activity, DateTimePicker.YEAR_MONTH_DAY, DateTimePicker.HOUR_24);
            String dateStr = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
            String weekStr = "";
            if (MultiLanguageUtil.getInstance().isZh()) {
                weekStr = DateUtils.getChineseWeek(dateStr, DateUtils.YMDFormatStr);
            } else {
                weekStr = DateUtils.getEngLishWeek(dateStr, DateUtils.YMDFormatStr);
            }
            beginTimePicker.setTitleText(DateUtils.getCurYear() + " (" + weekStr + ")");
            beginTimePicker.setOnDateTimePickListener(onListener);
            beginTimePicker.setResetWhileWheel(false);

            int startYear = DateUtils.getYear(startTime, startFormatTimeStr);
            int startMonth = DateUtils.getMonth(startTime, startFormatTimeStr);
            int startDay = DateUtils.getDay(startTime, startFormatTimeStr);
            beginTimePicker.setDateRangeStart(startYear, startMonth, startDay);

            int endYear = startYear + 4;
            int endMonth = 12;
            int endDay = DateUtils.getMonthOfDay(endYear, endMonth);
            beginTimePicker.setDateRangeEnd(endYear, endMonth, endDay);

            int selectedYear = startYear;
            int selectedMonth = startMonth;
            int selectedDay = startDay;

            int selectedHour = Integer.valueOf(DateUtils.getOtherDateStr(startTime, startFormatTimeStr, DateUtils.HFormatStr));
            int selectedMinute = Integer.valueOf(DateUtils.getOtherDateStr(startTime, startFormatTimeStr, DateUtils.MFormatStr));
            beginTimePicker.setSelectedItem(
                    selectedYear,
                    selectedMonth,
                    selectedDay,
                    selectedHour,
                    selectedMinute);
            beginTimePicker.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return beginTimePicker;
    }

    public static void showCallPhoneDialog(Context context, final String mobile, final OnOkAndCancelListener onOkAndCancelListener) {
        try {
            if (baseDialog != null && baseDialog.isShowing()) {
                baseDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_call_phone;
            }

            @Override
            protected void initParams() {
                TextView tvContent = (TextView) getView(R.id.dialog_call_phone_tv_mobile);
                tvContent.setText(mobile);
            }

            @Override
            protected void initEvents() {
                TextView tvCancel = (TextView) getView(R.id.dialog_call_phone_tv_cancel);
                TextView tvSure = (TextView) getView(R.id.dialog_call_phone_tv_sure);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (onOkAndCancelListener != null)
                            onOkAndCancelListener.cancelClicked();
                    }
                });
                tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (onOkAndCancelListener != null)
                            onOkAndCancelListener.okClicked();
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
    }

    public static BaseDialog showLocationServiceDialog(Context context,final OnListener listener) {
        BaseDialog  baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_location_service;
            }

            @Override
            protected void initParams() {
            }

            @Override
            protected void initEvents() {
                getView(R.id.dialog_location_service_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (listener!=null){
                            listener.cancelClicked();
                        }
                    }
                });
                getView(R.id.dialog_location_service_tv_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (listener!=null){
                            listener.okClicked();
                        }
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }

    public interface OnListener {
        void okClicked();

        void cancelClicked();
    }

    public interface OnOkAndCancelListener {
        void okClicked();

        void cancelClicked();
    }

    public interface OnEnterListener {
        void okClicked(String content);

        void cancelClicked();
    }


}