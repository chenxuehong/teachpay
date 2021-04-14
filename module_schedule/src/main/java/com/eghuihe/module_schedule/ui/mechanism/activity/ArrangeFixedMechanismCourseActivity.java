package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.mvp.ArrangeMechanismCourseFixContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.ArrangeMechanismCourseFixPresenter;
import com.eghuihe.module_schedule.ui.utils.MyDialogUtils;
import com.eghuihe.module_schedule.ui.widget.CalendarDialogFragment;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.picker.DoubleTimePicker;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 排课
 */
public class ArrangeFixedMechanismCourseActivity extends BaseMvpTitleActivity<ArrangeMechanismCourseFixPresenter>
        implements ArrangeMechanismCourseFixContract.View {
    private static final String SP_LINE = "#\\$\\*";
    private static final int REQUEST_CODE_SELECT_CLASS = 100;
    private MasterSetPriceEntity masterSetPriceEntity;

    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_class_title)
    TextView tvClassTitle;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_rg_method)
    RadioGroup rgArrangeMethod;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_week_title)
    TextView tvWeekTitle;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_select_week)
    TextView tvSelectWeek;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_week_content)
    TextView tvWeekContent;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_repeat_title)
    TextView tvRepeatTitle;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_switch_repeat)
    Switch switchRepeat;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_fixed_time)
    TextView tvFixedTime;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_select_teacher)
    TextView tvSelectTeacher;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_select_classRoom)
    TextView tvSelectClassRoom;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_select_start_date)
    TextView tvSelectStartDate;
    @BindView(R2.id.acivity_arrange_mechanism_coursefix_tv_select_end_date)
    TextView tvSelectEndDate;
    private BaseDialog selectWeeksDialog;
    private CalendarDialogFragment calendarDialogFragment;
    private MechanismClassEntity mechanismClassEntity;
    private DoubleTimePicker doubleTimePicker;
    private List<ClassRoomEntity> classRoomEntities;
    private SinglePicker addressPicker;
    private BaseDialog tipAddClassRoomDialog;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private String type;
    private MasterInfoHomeModel.MasterInfoHomeEntity masterInfoHomeEntity;

    @OnClick({
            R2.id.acivity_arrange_mechanism_coursefix_rl_selectClass,
            R2.id.acivity_arrange_mechanism_coursefix_tv_select_week,
            R2.id.acivity_arrange_mechanism_coursefix_ll_fixed_time,
            R2.id.acivity_arrange_mechanism_coursefix_ll_select_teacher,
            R2.id.acivity_arrange_mechanism_coursefix_ll_select_classRoom,
            R2.id.acivity_arrange_mechanism_coursefix_ll_select_start_date,
            R2.id.acivity_arrange_mechanism_coursefix_ll_select_end_date,
            R2.id.acivity_arrange_mechanism_coursefix_tv_finish
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_rl_selectClass) {
            // 选择班级
            selectClass();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_tv_select_week) {
            // 选择周几或日历
            selectWeekOrCalendar();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_ll_fixed_time) {
            // 选择固定时间
            selectFixedTime();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_ll_select_teacher) {
            // 选择老师
            selectTeacher();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_ll_select_classRoom) {
            // 选择教室
            selectClassRoom();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_ll_select_start_date) {
            // 开始日期
            selectStartDate();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_ll_select_end_date) {
            // 结束日期
            if (TextUtils.isEmpty(tvSelectStartDate.getText().toString().trim())) {
                ToastUtils.showShortToast(this, "请先选择开始日期");
                return;
            }
            selectEndDate();
        } else if (view.getId() == R.id.acivity_arrange_mechanism_coursefix_tv_finish) {
            // 完成
            if (inputCheck()) {
                commitData();
            }
        }
    }

    private boolean inputCheck() {
        if (TextUtils.isEmpty(tvClassTitle.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择班级");
            return false;
        }
        if (tvWeekContent.getVisibility() == View.GONE || TextUtils.isEmpty(tvWeekContent.getText().toString().trim())) {
            String weekTitle = tvWeekTitle.getText().toString();
            if (weekTitle.equals("按照周几排课")) {
                // 按照周几排课
                ToastUtils.showShortToast(this, "请选择周几");
            } else {
                // 按照日历排课
                ToastUtils.showShortToast(this, "请选择日历");
            }
            return false;
        }
        if (TextUtils.isEmpty(tvFixedTime.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择上课时间");
            return false;
        }
        if (TextUtils.isEmpty(tvSelectTeacher.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择老师");
            return false;
        }
        if (TextUtils.isEmpty(tvSelectClassRoom.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择教室");
            return false;
        }
        if (TextUtils.isEmpty(tvSelectStartDate.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择开始日期");
            return false;
        }
        if (TextUtils.isEmpty(tvSelectEndDate.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择结束日期");
            return false;
        }
        return true;
    }

    private void selectClass() {
        Intent intent = new Intent(this, SelectClassListActivity.class);
        intent.putExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY,
                JsonUtil.toJson(masterSetPriceEntity));
        startActivityForResult(intent,REQUEST_CODE_SELECT_CLASS);
    }

    private void selectWeekOrCalendar() {
        String weekTitle = tvWeekTitle.getText().toString();
        if (weekTitle.equals("按照周几排课")) {
            // 按照周几排课
            selectWeeks();
        } else {
            // 按照日历排课
            selectCalendar();
        }
    }

    private void selectWeeks() {
        selectWeeksDialog = MyDialogUtils.showSelectWeeksDialog(this,
                new MyDialogUtils.OnListener() {

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onSure() {
                        Map<Integer, String> checkedWeekIndexList = MyDialogUtils.getCheckedWeekIndexList();
                        if (checkedWeekIndexList != null && checkedWeekIndexList.size() > 0) {
                            String checkedWeekIndexStr = MyDialogUtils.getCheckedWeekIndexStr();
                            tvWeekContent.setText(checkedWeekIndexStr);
                            tvWeekContent.setVisibility(View.VISIBLE);
                        } else {
                            tvWeekContent.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void selectCalendar() {
        calendarDialogFragment = new CalendarDialogFragment();
        calendarDialogFragment.show(getSupportFragmentManager(), "CalendarDialogFragment");
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.CALENDAR_DAY_LIST.equals(event.getAction())) {
            Object data = event.getData();
            if (data != null) {
                try {
                    List<String> dayList = (List<String>) data;
                    if (dayList != null && dayList.size() > 0) {
                        String dayListStr = StringUtils.list2String(dayList, ",");
                        tvWeekContent.setText(dayListStr);
                        tvWeekContent.setVisibility(View.VISIBLE);
                    } else {
                        tvWeekContent.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                tvWeekContent.setVisibility(View.GONE);
            }
        } else if (EventAction.KEY_TEACHER_ENTITY.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof MasterInfoHomeModel.MasterInfoHomeEntity) {
                masterInfoHomeEntity = (MasterInfoHomeModel.MasterInfoHomeEntity) data;
                String login_name = masterInfoHomeEntity.getLogin_name();
                tvSelectTeacher.setText(login_name);
            }
        }
    }

    private void selectFixedTime() {
        doubleTimePicker = new DoubleTimePicker(this);
        doubleTimePicker.setTimeRangeStart(0, 0);
        doubleTimePicker.setTimeRangeEnd(24, 59);
        doubleTimePicker.setSelectedItem(0, 0, 0, 0);
        doubleTimePicker.setOnDoubleTimePickListener(new DoubleTimePicker.OnDoubleTimePickListener() {
            @Override
            public void onDateTimePicked(String hour, String minute, String endHour, String endMinute) {
                tvFixedTime.setText(
                        hour.concat(":")
                                .concat(minute)
                                .concat("-")
                                .concat(endHour)
                                .concat(":")
                                .concat(endMinute));
            }
        });
        doubleTimePicker.show();
    }

    private void selectTeacher() {
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_IS_SELECT, "true");
        params.put(ArgumentsConfig.KEY_STATUS, "2");
        ActivityToActivity.toActivity(ARouterConfig.ME_TEACHPAY_MECHANISM_TEACHER_LIST_ACTIVITY, params);
    }

    private void selectClassRoom() {
        List<String> list = new ArrayList<>();
        if (classRoomEntities != null) {
            for (int i = 0; i < classRoomEntities.size(); i++) {
                ClassRoomEntity classRoomEntity = classRoomEntities.get(i);
                String name = classRoomEntity.getName();
                list.add(name);
            }
        }
        if (list.size() > 0) {
            addressPicker = new SinglePicker(
                    this,
                    list);
            addressPicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    tvSelectClassRoom.setText(item);
                }
            });
            addressPicker.show();
        } else {
            tipAddClassRoomDialog = DialogUtils.showTipDialog(AppManager.getInstance().currentActivity(),
                    "",
                    "您暂时还没有添加教室，是否前往添加?",
                    BaseApplication.getInstance().getResources().getString(R.string.cancel),
                    BaseApplication.getInstance().getResources().getString(R.string.sure),
                    new DialogUtils.OnListener() {
                        @Override
                        public void okClicked() {
                            ActivityToActivity.toActivity(ARouterConfig.ME_CLASSROOMMANAGERACTIVITY);
                        }

                        @Override
                        public void cancelClicked() {

                        }
                    });
        }
    }

    private void selectStartDate() {
        startDatePicker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
        startDatePicker.setLabel(
                getResources().getString(com.huihe.base_lib.R.string.year),
                getResources().getString(com.huihe.base_lib.R.string.month),
                getResources().getString(com.huihe.base_lib.R.string.day));
        startDatePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                String curYMDDateStr = year.concat("-").concat(month).concat("-").concat(day);
                tvSelectStartDate.setText(curYMDDateStr);
            }
        });
        startDatePicker.setRangeStart(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        startDatePicker.setRangeEnd(DateUtils.getCurYear() + 4, 12, DateUtils.getMonthOfDay(DateUtils.getCurYear() + 4, 12));
        startDatePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        startDatePicker.setResetWhileWheel(false);
        startDatePicker.show();
    }

    private void selectEndDate() {
        String selectStartDate = tvSelectStartDate.getText().toString();
        if (!TextUtils.isEmpty(selectStartDate)) {
            String[] split = selectStartDate.split("-");
            if (split.length == 3) {
                endDatePicker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
                endDatePicker.setLabel(
                        getResources().getString(com.huihe.base_lib.R.string.year),
                        getResources().getString(com.huihe.base_lib.R.string.month),
                        getResources().getString(com.huihe.base_lib.R.string.day));
                endDatePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String curYMDDateStr = year.concat("-").concat(month).concat("-").concat(day);
                        tvSelectEndDate.setText(curYMDDateStr);
                    }
                });
                endDatePicker.setRangeStart(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
                endDatePicker.setRangeEnd(DateUtils.getCurYear() + 4, 12, DateUtils.getMonthOfDay(DateUtils.getCurYear() + 4, 12));
                endDatePicker.setSelectedItem(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
                endDatePicker.setResetWhileWheel(false);
                endDatePicker.show();
            }
        }
    }

    private void commitData() {
        String fixedTime = tvFixedTime.getText().toString().trim();
        String[] split = fixedTime.split("-");
        if (split.length == 2) {
            String start_time = split[0].concat(":00");
            String end_time = split[1].concat(":00");
            String start_date = tvSelectStartDate.getText().toString().trim().concat(" 00:00:00");
            String end_date = tvSelectEndDate.getText().toString().trim().concat(" 00:00:00");
            getPresenter().arrangeMechanismFixCourse(
                    mechanismClassEntity.getId(),
                    type,
                    tvWeekContent.getText().toString().trim(),
                    switchRepeat.isChecked(),
                    start_time,
                    end_time,
                    masterInfoHomeEntity.getId(),
                    start_date,
                    end_date,
                    tvSelectClassRoom.getText().toString().trim()
            );
        }

    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("排课");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_arrange_mechanism_coursefix;
    }

    @Override
    protected ArrangeMechanismCourseFixPresenter createPresenter() {
        return new ArrangeMechanismCourseFixPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY);
            masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
        }
        rgArrangeMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int isCheckedId) {
                selectArrangeMethod(isCheckedId);
            }
        });
    }

    @Override
    protected void initData() {
        type = "week";
        getPresenter().queryMechanismClassroom(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                null,
                "2"
        );
    }

    private void selectArrangeMethod(int isCheckedId) {

        if (isCheckedId == R.id.acivity_arrange_mechanism_coursefix_rbt_week) {
            tvWeekTitle.setText("按照周几排课");
            tvSelectWeek.setHint("请选择周几");
            tvRepeatTitle.setText("每周重复");
            type = "week";
        } else if (isCheckedId == R.id.acivity_arrange_mechanism_coursefix_rbt_calendar) {
            tvWeekTitle.setText("按照日历排课");
            tvSelectWeek.setHint("请选择日历");
            tvRepeatTitle.setText("每月重复");
            type = "calendar";
        }
        tvWeekContent.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_SELECT_CLASS == requestCode) {
            if (data != null) {
                String json = data.getStringExtra(ArgumentsConfig.KEY_MECHANISM_MECHANISMCLASS);
                mechanismClassEntity = JsonUtil.fromJson(json, MechanismClassEntity.class);
                if (mechanismClassEntity != null) {
                    tvClassTitle.setText(mechanismClassEntity.getName());
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (selectWeeksDialog != null) {
                selectWeeksDialog.dismiss();
            }
            if (calendarDialogFragment != null) {
                calendarDialogFragment.dismiss();
            }
            if (doubleTimePicker != null) {
                doubleTimePicker.dismiss();
            }
            if (addressPicker != null) {
                addressPicker.dismiss();
            }
            if (tipAddClassRoomDialog != null) {
                tipAddClassRoomDialog.dismiss();
            }
            if (startDatePicker != null) {
                startDatePicker.dismiss();
            }
            if (endDatePicker != null) {
                endDatePicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onClassRoomList(List<ClassRoomEntity> classRoomEntities) {
        this.classRoomEntities = classRoomEntities;
        if (classRoomEntities != null && classRoomEntities.size() == 1) {
            ClassRoomEntity classRoomEntity = classRoomEntities.get(0);
            tvSelectClassRoom.setText(classRoomEntity.getName());
        }
    }

    @Override
    public void onInsertSuccess() {
        EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISM_COURSE));
        finish();
    }
}
