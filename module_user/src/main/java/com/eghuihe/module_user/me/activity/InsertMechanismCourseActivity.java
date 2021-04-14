package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.params.MechanismCourseParam;
import com.eghuihe.module_user.me.ItemBeanView;
import com.eghuihe.module_user.me.adapter.PicRvAdapter;
import com.eghuihe.module_user.me.mvp.InsertMechanismCourseContract;
import com.eghuihe.module_user.me.mvp.InsertMechanismCoursePresenter;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.picker.DateTimePicker;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.StringUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 添加机构课程
 */
public class InsertMechanismCourseActivity extends BaseMvpTitleActivity<InsertMechanismCoursePresenter>
        implements InsertMechanismCourseContract.View {

    public static final String KEY_MECHANISM_ID = "mechanism_id";
    public static final String SP_LINE = "#$*";
    //    @BindView(R2.id.addsalecourse_ItemBeanView_type)
//    ItemBeanView itemBeanViewType;
    @BindView(R2.id.addsalecourse_ItemBeanView_categories)
    ItemBeanView itemBeanViewCategories;
    @BindView(R2.id.addsalecourse_et_connect_peoplenum)
    EditText etConnectPeoplenum;
    @BindView(R2.id.addsalecourse_ll_connect_peoplenum)
    LinearLayout llConnectPeoplenum;
    @BindView(R2.id.addsalecourse_ItemBeanView_start_time)
    ItemBeanView itemBeanViewStartTime;
    @BindView(R2.id.addsalecourse_ItemBeanView_end_time)
    ItemBeanView itemBeanViewEndTime;
    @BindView(R2.id.addsalecourse_rv_course_cover)
    RecyclerViewFixed rvCourseCover;
    @BindView(R2.id.addsalecourse_et_theme)
    EditText etTheme;
    @BindView(R2.id.addsalecourse_et_label)
    EditText etLabel;
    @BindView(R2.id.addsalecourse_et_duration)
    EditText etDuration;
    @BindView(R2.id.addsalecourse_et_courseNumber)
    EditText etCourseNumber;
    @BindView(R2.id.addsalecourse_ll_classPrice)
    LinearLayout llClassPrice;
    @BindView(R2.id.addsalecourse_et_classPrice)
    EditText etClassPrice;
    @BindView(R2.id.addsalecourse_et_studentNumber)
    EditText etStudentNumber;
    @BindView(R2.id.addsalecourse_et_original_price)
    EditText etOriginalPrice;
    @BindView(R2.id.addsalecourse_et_course_introduction)
    EditText etCourseIntroduction;
    @BindView(R2.id.addsalecourse_rv_introduction_pic)
    RecyclerViewFixed rvIntroductionPic;
    @BindView(R2.id.addsalecourse_switch_Whether_there_is_attend_activities)
    Switch switch_is_attend_activities;
    @BindView(R2.id.addsalecourse_ItemBeanView_syllabus)
    ItemBeanView itemBeanViewSyllabus;
    @BindView(R2.id.addsalecourse_switch_Whether_there_is_a_syllabus)
    Switch switchSyllabus;
    @BindView(R2.id.addsalecourse_tv_save)
    TextView tvSave;
    @BindView(R2.id.addsalecourse_ll_activity_price)
    LinearLayout llActivityPrice;
    @BindView(R2.id.addsalecourse_et_activity_price)
    EditText etActivityPrice;
    private List<String> mCourseCoverList;
    private String mechanism_id;
    private SinglePicker courseTypePicker;
    private PicRvAdapter mIntroductionRvAdapter;
    private DateTimePicker endTimeDateTimePicker;
    MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
    private SinglePicker categoriesPicker;
    private String appointment_type;

    @OnClick({
            R2.id.addsalecourse_ItemBeanView_start_time,
            R2.id.addsalecourse_ItemBeanView_end_time,
            R2.id.addsalecourse_ll_introduction_pic,
            R2.id.addsalecourse_ll_course_cover,
            R2.id.addsalecourse_ItemBeanView_categories,
            R2.id.addsalecourse_tv_save,
            R2.id.addsalecourse_tv_save_as_draft,
    })
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            if (view.getId() == R.id.addsalecourse_ItemBeanView_categories) {
                selectCategories();
            } else if (view.getId() == R.id.addsalecourse_ll_course_cover) {
                selectCourseCover();
            } else if (view.getId() == R.id.addsalecourse_ll_introduction_pic) {
                selectIntroductionPic();
            } else if (view.getId() == R.id.addsalecourse_ItemBeanView_start_time) {
                // 选择活动课程开始时间
                selectCourseStartTimeDialog();
            } else if (view.getId() == R.id.addsalecourse_ItemBeanView_end_time) {
                // 选择活动课程结束时间
                selectCourseEndTimeDialog();
            } else if (view.getId() == R.id.addsalecourse_tv_save) {
                // 完成
                if (checkInput()) {
                    commit(2);
                }
            } else if (view.getId() == R.id.addsalecourse_tv_save_as_draft) {
                // 完成
                if (checkInput()) {
                    commit(3);
                }
            }
        }
    }

    private void selectCategories() {
        if (masterMechanismEntity != null) {
            String categoriesChild = masterMechanismEntity.getCategories_child();
            if (!TextUtils.isEmpty(categoriesChild)) {
                String[] split = categoriesChild.split("/");
                if (split != null && split.length > 1) {
                    categoriesPicker = new SinglePicker(
                            this,
                            split);
                    categoriesPicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
                        @Override
                        public void onItemPicked(int index, String item) {
                            itemBeanViewCategories.setRightTitle(item);
                        }
                    });
                    categoriesPicker.show();
                }
            }
        }

    }

    /**
     * @desc 选择课程开始时间
     */
    private void selectCourseStartTimeDialog() {
        DialogUtils.showDateTimePicker(this, new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                itemBeanViewStartTime.setRightTitle(
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

    /**
     * @desc 选择课程结束时间
     */
    private void selectCourseEndTimeDialog() {
        String startTime = itemBeanViewStartTime.getRightTitle();
        if (TextUtils.isEmpty(startTime)) {
            ToastUtils.showShortToast(this, "请选择开始时间");
            return;
        }
        startTime = startTime.concat(":00");
        endTimeDateTimePicker = DialogUtils.showDateTimePicker(
                this,
                startTime,
                DateUtils.YMDHMSFormatStr,
                new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        itemBeanViewEndTime.setRightTitle(
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

    private void selectIntroductionPic() {
        PhotoSelectUtils.selectMutiPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(List<String> picList) {
                        showUploading();
                        final List<String> commitList = new ArrayList<>();
                        new QiNiuUtils().loadMutiPic(0, picList,
                                new QiNiuUtils.OnUpMutiloadListener() {
                                    @Override
                                    public void loadFinish() {
                                        showIntroductionPic(commitList);
                                        closeLoading();
                                    }

                                    @Override
                                    public void loadPic(int position, String path) {
                                        if (commitList != null) {
                                            commitList.add(path);
                                        }
                                    }

                                    @Override
                                    public void loadError(int position) {

                                    }
                                });
                    }
                });
    }

    private void showIntroductionPic(List<String> commitList) {
        rvIntroductionPic.setVertical(3);
        rvIntroductionPic.addGridViewItemDecoration(3, DensityUtils.dp2px(this, 5));
        mIntroductionRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, commitList);
        rvIntroductionPic.setAdapter(mIntroductionRvAdapter);
    }

    private boolean hasIntroduction() {
        return mIntroductionRvAdapter != null && mIntroductionRvAdapter.getItemCount() > 0;
    }

    private void selectCourseType() {
        String[] courseTypeAtt = {
                "预约",
                "排课"
        };
        // appointment 预约 scheduling 排课
        courseTypePicker = new SinglePicker(
                this,
                courseTypeAtt);
        courseTypePicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                itemBeanViewType.setRightTitle(item);
            }
        });
        courseTypePicker.show();
    }

    /**
     * @desc 选择课程封面
     */
    private void selectCourseCover() {
        PhotoSelectUtils.selectPic(this,
                new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {
                        showUploading();
                        new QiNiuUtils().uploadPic(path,
                                Long.valueOf((new Date()).getTime()) + ".png", new QiNiuUtils.OnUploadListener() {
                                    @Override
                                    public void onUploadFinish(String picUrl) {
                                        showCourseCover(picUrl);
                                        closeLoading();
                                    }

                                    @Override
                                    public void onUploadFail(int code, String error) {
                                        closeLoading();
                                    }
                                });

                    }
                });
    }

    /**
     * @param path
     * @desc 显示课程封面
     */
    private void showCourseCover(String path) {
        mCourseCoverList = new ArrayList<>();
        mCourseCoverList.add(path);
        rvCourseCover.setVertical(3);
        PicRvAdapter mCourseCoverRvAdapter = new PicRvAdapter(R.layout.item_note_img, this, mCourseCoverList);
        rvCourseCover.setAdapter(mCourseCoverRvAdapter);
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Add_Mechanism_courses));
    }

    @Override
    protected void initView() {
        super.initView();
        tvSave.setBackgroundResource(R.drawable.shape_bg_radius_6_color_ff7300);
    }

    private void commit(int status) {
        String syllabus = "";
        if (!switchSyllabus.isChecked()) {
            // 默认大纲
            syllabus = getDefaultSyllabus();
            String courseCover = "";
            if (mCourseCoverList != null && mCourseCoverList.size() > 0) {
                courseCover = mCourseCoverList.get(0);
            }
            List<String> IntroductionUrlList = mIntroductionRvAdapter.getData();
            String introductionUrl = StringUtils.list2String(IntroductionUrlList, ",");
            showUploading();
            String price_list = "0-".concat(etDuration.getText().toString().trim()).concat("-").concat(etClassPrice.getText().toString());
            String startTime = itemBeanViewStartTime.getRightTitle();
            String endTime = itemBeanViewEndTime.getRightTitle();
            UserServiceImpl.insertMasterSetPrice(
                    "0",
                    etTheme.getText().toString(),
                    getAppointmentType(),
                    etLabel.getText().toString(),
                    etCourseNumber.getText().toString(),
                    etOriginalPrice.getText().toString(),
                    null,
                    etCourseIntroduction.getText().toString(),
                    status,
                    syllabus,
                    null,
                    "mechanism_offline",
                    etConnectPeoplenum.getText().toString().trim(),
                    mechanism_id,
                    courseCover,
                    introductionUrl,
                    etDuration.getText().toString().trim(),
                    price_list,
                    switch_is_attend_activities.isChecked(),
                    TextUtils.isEmpty(startTime) ? null : startTime.concat(":00"),
                    TextUtils.isEmpty(endTime) ? null : endTime.concat(":00"),
                    etActivityPrice.getText().toString().trim(),
                    itemBeanViewCategories.getRightTitle(),
                    new NetworkSubscriber<InsertInfoResultModel>(null) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            ToastUtils.showShortToast(InsertMechanismCourseActivity.this, getResources().getString(R.string.commit_success));
                            closeLoading();
                            EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISM_COURSE));
                            AppManager.getInstance().finishActivity(SelectMechanismCourseTypeActivity.class);
                            finish();
                        }

                        @Override
                        public void onComplete() {
                            super.onComplete();
                            closeLoading();
                        }
                    }
            );
        } else {
            // 自定义大纲和阶段性大纲
            String courseCover = "";
            if (mCourseCoverList != null && mCourseCoverList.size() > 0) {
                courseCover = mCourseCoverList.get(0);
            }
            List<String> IntroductionUrlList = mIntroductionRvAdapter.getData();
            String introductionUrl = StringUtils.list2String(IntroductionUrlList, ",");
            String price_list = "0-".concat(etDuration.getText().toString().trim()).concat("-").concat(etClassPrice.getText().toString());
            String startTime = itemBeanViewStartTime.getRightTitle();
            String endTime = itemBeanViewEndTime.getRightTitle();
            Intent intent = new Intent(this, SetMechanismCourseSyllabusActivity.class);
            intent.putExtra(ArgumentsConfig.KEY_MECHANISMCOURSEPARAM,
                    JsonUtil.toJson(new MechanismCourseParam(
                            "0",
                            etTheme.getText().toString(),
                            getAppointmentType(),
                            etLabel.getText().toString(),
                            etCourseNumber.getText().toString(),
                            etOriginalPrice.getText().toString(),
                            "0.0",
                            etCourseIntroduction.getText().toString(),
                            status,
                            syllabus,
                            null,
                            "mechanism_offline",
                            etConnectPeoplenum.getText().toString().trim(),
                            mechanism_id,
                            courseCover,
                            introductionUrl,
                            etDuration.getText().toString().trim(),
                            price_list,
                            switch_is_attend_activities.isChecked(),
                            TextUtils.isEmpty(startTime) ? null : startTime.concat(":00"),
                            TextUtils.isEmpty(endTime) ? null : endTime.concat(":00"),
                            etActivityPrice.getText().toString().trim(),
                            itemBeanViewCategories.getRightTitle()
                    )));
            startActivity(intent);
        }

    }

    private String getDefaultSyllabus() {
        StringBuffer sbSyllabus = new StringBuffer();
        int courseNum = Integer.valueOf(etCourseNumber.getText().toString().trim());
        for (int i = 0; i < courseNum; i++) {
            sbSyllabus.append(etTheme.getText().toString().trim()).append("-").append(i + 1);
            if (i != courseNum - 1) {
                sbSyllabus.append(SP_LINE);
            }
        }
        return sbSyllabus.toString().trim();
    }

    private boolean checkInput() {

        if (TextUtils.isEmpty(itemBeanViewCategories.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择课程类目");
            return false;
        }
        if (llConnectPeoplenum.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etConnectPeoplenum.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入班级人数");
            return false;
        }
        if (mCourseCoverList == null || mCourseCoverList.size() == 0) {
            ToastUtils.showShortToast(this, "请选择课程封面");
            return false;
        }
        if (TextUtils.isEmpty(etTheme.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入标题");
            return false;
        }
        if (TextUtils.isEmpty(etLabel.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入标签");
            return false;
        }
        if (TextUtils.isEmpty(etDuration.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入使用期限");
            return false;
        }
        if (TextUtils.isEmpty(etOriginalPrice.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入套餐价");
            return false;
        }
        if (TextUtils.isEmpty(etCourseNumber.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入课节数");
            return false;
        }

        if (llClassPrice.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etClassPrice.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入课节单价");
            return false;
        }

        float disVaule = Float.valueOf(etOriginalPrice.getText().toString().trim()) * 1.15f / Integer.valueOf(etCourseNumber.getText().toString().trim());
        if (llClassPrice.getVisibility() == View.VISIBLE
                && Float.valueOf(etClassPrice.getText().toString().trim()) > disVaule) {
            ToastUtils.showShortToast(this, "请勿超过(原价/课节数)*(1+15%)");
            return false;
        }

        if (TextUtils.isEmpty(etCourseIntroduction.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入课程简介");
            return false;
        }
        if (!hasIntroduction()) {
            ToastUtils.showShortToast(this, "请上传图片简介");
            return false;
        }

        if (itemBeanViewStartTime.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(itemBeanViewStartTime.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择开始时间");
            return false;
        }

        if (itemBeanViewEndTime.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(itemBeanViewEndTime.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择结束时间");
            return false;
        }

        if (llActivityPrice.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etActivityPrice.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入活动课程单价");
            return false;
        }
        if (!checkCourseNumber()) {
            return false;
        }
        return true;
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_addsalecourse;
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            mechanism_id = intent.getStringExtra(KEY_MECHANISM_ID);
            appointment_type = intent.getStringExtra(ArgumentsConfig.KEY_APPOINTMENT_TYPE);
        }
        llConnectPeoplenum.setVisibility(
                "fixed_scheduling".equals(appointment_type) ?
                        View.VISIBLE : View.GONE);
        initListener();
        getPresenter().queryMechanismDetailInfoListById(
                mechanism_id,
                "teach_paypal"
        );
    }

    private String getAppointmentType() {
        return appointment_type;
    }

    private void initListener() {
        switch_is_attend_activities.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        itemBeanViewStartTime.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                        itemBeanViewEndTime.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                        llActivityPrice.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                    }
                });
        etCourseNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString().equals("0")) {
                    if (etCourseNumber != null)
                        etCourseNumber.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean checkCourseNumber() {
        if (TextUtils.isEmpty(etCourseNumber.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_enter_course_number));
            return false;
        }
        if (Integer.valueOf(etCourseNumber.getText().toString()) <= 0) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_enter_course_number));
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected InsertMechanismCoursePresenter createPresenter() {
        return new InsertMechanismCoursePresenter();
    }

    @Override
    protected void onDestroy() {
        try {
            if (courseTypePicker != null) {
                courseTypePicker.dismiss();
            }
            if (categoriesPicker != null) {
                categoriesPicker.dismiss();
            }
            if (endTimeDateTimePicker != null) {
                endTimeDateTimePicker.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onMechanismInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity) {
        this.masterMechanismEntity = masterMechanismEntity;
        if (masterMechanismEntity != null) {
            String categoriesChild = masterMechanismEntity.getCategories_child();
            if (!TextUtils.isEmpty(categoriesChild)) {
                String[] split = categoriesChild.split("/");
                if (split != null && split.length == 1) {
                    itemBeanViewCategories.setRightTitle(split[0]);
                }
            }
        }
    }

}
