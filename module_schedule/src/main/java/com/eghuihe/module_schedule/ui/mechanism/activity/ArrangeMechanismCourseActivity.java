package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.adapter.SelectedStudentListRvAdapter;
import com.eghuihe.module_schedule.ui.mechanism.mvp.ArrangeMechanismCourseContract;
import com.eghuihe.module_schedule.ui.mechanism.mvp.ArrangeMechanismCoursePresenter;
import com.google.gson.reflect.TypeToken;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.StudentBean;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.picker.DateTimePicker;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
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
public class ArrangeMechanismCourseActivity extends BaseMvpTitleActivity<ArrangeMechanismCoursePresenter>
        implements ArrangeMechanismCourseContract.View {

    private static final int REQUEST_CODE_SELECT_COURSE = 100;
    private static final int REQUEST_CODE_SELECT_STUDENT = 101;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_title)
    TextView tvTitle;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_childtitle)
    TextView tvChildTitle;
    @BindView(R2.id.activity_arrange_mechanism_course_et_studentNum)
    EditText etStuNum;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_unArrange)
    TextView tvUnArrangeTeacher;
    @BindView(R2.id.activity_arrange_mechanism_course_iv_arranged)
    CircleImageView ivArrangeTeachered;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_start_time)
    TextView tvStartTime;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_end_time)
    TextView tvEndTime;
    @BindView(R2.id.activity_arrange_mechanism_course_rv_student)
    RecyclerViewFixed rvStudent;
    @BindView(R2.id.activity_arrange_mechanism_course_rl_add_student)
    RelativeLayout rlStudent;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_studentNum)
    TextView tvStudentNum;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_selectAddressTitle)
    TextView tvSelectAddressTitle;
    @BindView(R2.id.activity_arrange_mechanism_course_ll_selectAddress)
    LinearLayout llSelectAddress;
    @BindView(R2.id.activity_arrange_mechanism_course_tv_selectAddress)
    TextView tvSelectAddress;

    private MasterSetPriceEntity masterSetPriceEntity;
    private SinglePicker childTitlePicker;
    private MasterInfoHomeModel.MasterInfoHomeEntity masterInfoHomeEntity;
    private List<StudentBean> checkedStudentList;
    private SelectedStudentListRvAdapter selectedStudentListRvAdapter;
    private String appointment_type;
    private Integer number_of_lessons;
    private SinglePicker addressPicker;

    @OnClick({
            R2.id.activity_arrange_mechanism_course_ll_title,
            R2.id.activity_arrange_mechanism_course_ll_childtitle,
            R2.id.activity_arrange_mechanism_course_fl_start_time,
            R2.id.activity_arrange_mechanism_course_fl_end_time,
            R2.id.activity_arrange_mechanism_course_fl_arrange,
            R2.id.activity_arrange_mechanism_course_add_student,
            R2.id.activity_arrange_mechanism_course_ll_selectAddress,
            R2.id.activity_arrange_mechanism_course_tv_commit,
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_arrange_mechanism_course_ll_title) {
            selectCourse();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_ll_childtitle) {
            selectChildCourse();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_fl_start_time) {
            selectStartTime();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_fl_end_time) {
            selectEndTime();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_fl_arrange) {
            arrangeTeacher();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_add_student) {
            arrangeStudent();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_ll_selectAddress) {
            if (checkStartTime())
                selectAddress();
        } else if (view.getId() == R.id.activity_arrange_mechanism_course_tv_commit) {
            if (checkInput()) {
                commit();
            }
        }
    }

    private boolean checkStartTime() {
        if (TextUtils.isEmpty(tvStartTime.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择开始时间");
            return false;
        }
        return true;
    }

    private void selectCourse() {
        Intent intent = new Intent(this, MechanismCourseListActivity.class);
        intent.putExtra(ArgumentsConfig.KEY_APPOINTMENT_TYPE, appointment_type);
        startActivityForResult(intent, REQUEST_CODE_SELECT_COURSE);
    }

    private static final String SP_LINE = "#\\$\\*";

    private void selectChildCourse() {

        if (TextUtils.isEmpty(tvTitle.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请先选择课程标题");
            return;
        }
        // 五六年级英语创新班01#$*五六年级英语创新班02#$*五六年级英语创新班03#$*五六年级英语创新班04#$*五六年级英语创新班05#$*五六年级英语创新班06#$*五六年级英语创新班07#$*五六年级英语创新班08#$*五六年级英语创新班09#$*五六年级英语创新班10#$*五六年级英语创新班11#$*五六年级英语创新班12#$*五六年级英语创新班13#$*五六年级英语创新班14#$*五六年级英语创新班15
        String titile_url = masterSetPriceEntity.getTitile_url();
        if (TextUtils.isEmpty(titile_url)) {
            ToastUtils.showShortToast(this, "没有课节");
            return;
        }
        String[] split = titile_url.split(SP_LINE);
        childTitlePicker = new SinglePicker(
                this,
                split);
        childTitlePicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                number_of_lessons = index;
                tvChildTitle.setText(item);
            }
        });
        childTitlePicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_COURSE) {
            if (data != null) {
                String json = data.getStringExtra(MechanismCourseListActivity.KEY_COURSE_ENTITY);
                masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
                tvTitle.setText(masterSetPriceEntity.getTitle());
            }
        } else if (requestCode == REQUEST_CODE_SELECT_STUDENT) {
            if (data != null) {
                String json = data.getStringExtra(SelectStudentListActivity.KEY_STUDENT_LIST);
                checkedStudentList = JsonUtil.fromJson(json, new TypeToken<List<StudentBean>>() {
                }.getType());
                showStudentList();
            }
        }
    }

    private void showStudentList() {
        rvStudent.setVertical(4);
        rvStudent.setScrollingEnabled(false);
        rvStudent.addGridViewItemDecoration(4, DensityUtils.dp2px(this, 15));
        selectedStudentListRvAdapter = new SelectedStudentListRvAdapter(R.layout.item_student_selected, this, checkedStudentList);
        rvStudent.setAdapter(selectedStudentListRvAdapter);
    }

    private boolean hasSelectedStudentList() {
        return selectedStudentListRvAdapter != null && selectedStudentListRvAdapter.getItemCount() > 0;
    }

    private void selectStartTime() {
        DialogUtils.showDateTimePicker(this, new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                tvStartTime.setText(
                        year.concat("-")
                                .concat(month)
                                .concat("-")
                                .concat(day)
                                .concat(" ")
                                .concat(hour)
                                .concat(":")
                                .concat(minute)
                );
                getPresenter().queryMechanismClassroom(
                        LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                        tvStartTime.getText().toString().trim().concat(":00"),
                        "2"
                );
            }
        });
    }

    private void selectEndTime() {
        DialogUtils.showDateTimePicker(this, new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                tvEndTime.setText(
                        year.concat("-")
                                .concat(month)
                                .concat("-")
                                .concat(day)
                                .concat(" ")
                                .concat(hour)
                                .concat(":")
                                .concat(minute)
                );
            }
        });
    }


    private void arrangeTeacher() {
        Map<String, String> params = new HashMap<>();
        params.put(ArgumentsConfig.KEY_IS_SELECT, "true");
        params.put(ArgumentsConfig.KEY_STATUS, "2");
        ActivityToActivity.toActivity(ARouterConfig.ME_TEACHPAY_MECHANISM_TEACHER_LIST_ACTIVITY, params);
    }

    private void arrangeStudent() {
        if (TextUtils.isEmpty(tvTitle.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请先选择课程主题");
            return;
        }
        String id = masterSetPriceEntity.getId();
        Intent intent = new Intent(this, SelectStudentListActivity.class);
        intent.putExtra(SelectStudentListActivity.KEY_GOOD_ID, id);
        startActivityForResult(intent, REQUEST_CODE_SELECT_STUDENT);
    }

    private void selectAddress() {
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
                    tvSelectAddress.setText(item);
                }
            });
            addressPicker.show();
        } else {
            DialogUtils.showTipDialog(AppManager.getInstance().currentActivity(),
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

    private boolean checkInput() {
        if (TextUtils.isEmpty(tvTitle.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择课程主题");
            return false;
        }
        if (TextUtils.isEmpty(tvChildTitle.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择课节主题");
            return false;
        }
        if (TextUtils.isEmpty(tvStartTime.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择上课开始时间");
            return false;
        }
        if (TextUtils.isEmpty(tvEndTime.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择上课结束时间");
            return false;
        }
        if (TextUtils.isEmpty(tvSelectAddress.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请选择上课教室或场馆");
            return false;
        }
        if (etStuNum.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etStuNum.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入该节课程最多可上课学生数量");
            return false;
        }
        if (ivArrangeTeachered.getVisibility() == View.GONE) {
            ToastUtils.showShortToast(this, "请选择上课老师");
            return false;
        }
        if (rlStudent.getVisibility() == View.VISIBLE
                && !hasSelectedStudentList()) {
            ToastUtils.showShortToast(this, "请选择上课学生");
            return false;
        }
        return true;
    }

    private void commit() {
        getPresenter().insertOfflineCourse(
                "mechanism_offline",
                "android",
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                masterInfoHomeEntity.getUser_id(),
                tvChildTitle.getText().toString().trim(),
                tvStartTime.getText().toString().trim().concat(":00"),
                tvEndTime.getText().toString().trim().concat(":00"),
                String.valueOf(DateUtils.getCurTimeZoneOffset()),
                "offline",
                masterSetPriceEntity.getId(),
                "teach_paypal_course",
                tvSelectAddress.getText().toString().trim(),
                etStuNum.getText().toString().trim(),
                String.valueOf(number_of_lessons + 1)
        );

    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("预约排课");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_arrange_mechanism_course;
    }

    @Override
    protected ArrangeMechanismCoursePresenter createPresenter() {
        return new ArrangeMechanismCoursePresenter();
    }

    @Override
    protected void initData() {
        appointment_type = "appointment";
        rlStudent.setVisibility("scheduling".equals(appointment_type) ? View.VISIBLE : View.GONE);
        tvStudentNum.setVisibility(!"scheduling".equals(appointment_type) ? View.VISIBLE : View.GONE);
        etStuNum.setVisibility(!"scheduling".equals(appointment_type) ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        try {
            if (childTitlePicker != null) {
                childTitlePicker.dismiss();
            }
            if (addressPicker != null) {
                addressPicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.KEY_TEACHER_ENTITY.equals(event.getAction())) {
            masterInfoHomeEntity = (MasterInfoHomeModel.MasterInfoHomeEntity) event.getData();
            tvUnArrangeTeacher.setVisibility(View.GONE);
            ivArrangeTeachered.setVisibility(View.VISIBLE);
            GlideTools.loadImage(this, masterInfoHomeEntity.getPhoto(), ivArrangeTeachered);
        }
    }

    @Override
    public void onInsertSuccess() {
        ToastUtils.showShortToast(this, "提交成功");
        EventBusUtils.sendEvent(new Event(EventAction.ARRANGE_COURSE, appointment_type));
        finish();
    }

    private List<ClassRoomEntity> classRoomEntities;

    @Override
    public void onClassRoomList(List<ClassRoomEntity> classRoomEntities) {
        this.classRoomEntities = classRoomEntities;
        if (classRoomEntities != null && classRoomEntities.size() == 1) {
            ClassRoomEntity classRoomEntity = classRoomEntities.get(0);
            tvSelectAddressTitle.setVisibility(View.VISIBLE);
            llSelectAddress.setVisibility(View.VISIBLE);
            tvSelectAddress.setText(classRoomEntity.getName());
        } else if (classRoomEntities != null && classRoomEntities.size() > 1) {
            tvSelectAddressTitle.setVisibility(View.VISIBLE);
            llSelectAddress.setVisibility(View.VISIBLE);
        } else {
            tvSelectAddressTitle.setVisibility(View.GONE);
            llSelectAddress.setVisibility(View.GONE);
        }
    }
}
