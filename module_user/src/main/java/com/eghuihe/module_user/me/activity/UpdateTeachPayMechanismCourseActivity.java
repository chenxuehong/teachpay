package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.bean.params.MechanismCourseParam;
import com.eghuihe.module_user.me.ItemBeanView;
import com.eghuihe.module_user.me.adapter.PicRvAdapter;
import com.eghuihe.module_user.me.adapter.StageSyllabusRvAdapter;
import com.eghuihe.module_user.me.mvp.UpdateTeachPayMechanismCourseContract;
import com.eghuihe.module_user.me.mvp.UpdateTeachPayMechanismCoursePresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ActivityEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.picker.DateTimePicker;
import com.huihe.base_lib.ui.widget.picker.SinglePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ConvertUtils;
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
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.ME_UPDATETEACHPAYMECHANISMCOURSEACTIVITY)
public class UpdateTeachPayMechanismCourseActivity extends BaseMvpTitleActivity<UpdateTeachPayMechanismCoursePresenter>
        implements UpdateTeachPayMechanismCourseContract.View {

    private MasterSetPriceEntity masterSetPriceEntity;
    @BindView(R2.id.updatesalecourse_ItemBeanView_type)
    ItemBeanView itemBeanViewType;
    @BindView(R2.id.updatesalecourse_ItemBeanView_categories)
    ItemBeanView itemBeanViewCategories;
    @BindView(R2.id.updatesalecourse_rv_course_cover)
    RecyclerViewFixed rvCourseCover;
    @BindView(R2.id.updatesalecourse_et_theme)
    EditText etTheme;
    @BindView(R2.id.updatesalecourse_et_label)
    EditText etLabel;
    @BindView(R2.id.updatesalecourse_ll_connect_peoplenum)
    LinearLayout llConnectPeoplenum;
    @BindView(R2.id.updatesalecourse_et_connect_peoplenum)
    EditText etConnectPeoplenum;
    @BindView(R2.id.updatesalecourse_et_duration)
    EditText etDuration;
    @BindView(R2.id.updatesalecourse_et_courseNumber)
    EditText etCourseNumber;
    @BindView(R2.id.updatesalecourse_et_classPrice)
    EditText etClassPrice;
    @BindView(R2.id.updatesalecourse_ll_classPrice)
    LinearLayout llClassPrice;
    @BindView(R2.id.updatesalecourse_et_original_price)
    EditText etOriginalPrice;
    @BindView(R2.id.updatesalecourse_et_course_introduction)
    EditText etCourseIntroduction;
    @BindView(R2.id.updatesalecourse_rv_introduction_pic)
    RecyclerViewFixed rvIntroductionPic;
    @BindView(R2.id.updatesalecourse_switch_Whether_there_is_attend_activities)
    Switch switch_is_attend_activities;
    @BindView(R2.id.updatesalecourse_ll_activity_price)
    LinearLayout llActivityPrice;
    @BindView(R2.id.updatesalecourse_et_activity_price)
    EditText etActivityPrice;
    @BindView(R2.id.updatesalecourse_ItemBeanView_start_time)
    ItemBeanView itemBeanViewStartTime;
    @BindView(R2.id.updatesalecourse_ItemBeanView_end_time)
    ItemBeanView itemBeanViewEndTime;
    @BindView(R2.id.updatesalecourse_switch_Whether_there_is_a_syllabus)
    Switch switchSyllabus;

    private SinglePicker courseTypePicker;
    private PicRvAdapter mIntroductionRvAdapter;
    private List<String> mCourseCoverList;
    public static final String SP_LINE = "#$*";
    private String id;
    private String titile_url;
    private DateTimePicker endTimeDateTimePicker;
    MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
    private SinglePicker categoriesPicker;
    private StageSyllabusRvAdapter stageSyllabusRvAdapter;

    @OnClick({
            R2.id.updatesalecourse_ItemBeanView_start_time,
            R2.id.updatesalecourse_ItemBeanView_end_time,
            R2.id.updatesalecourse_ll_introduction_pic,
            R2.id.updatesalecourse_ItemBeanView_categories,
            R2.id.updatesalecourse_ll_course_cover,
            R2.id.updatesalecourse_tv_save_as_draft,
            R2.id.updatesalecourse_tv_save,
    })
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            if (view.getId() == R.id.updatesalecourse_ItemBeanView_type) {
                selectCourseType();
            } else if (view.getId() == R.id.updatesalecourse_ItemBeanView_categories) {
                selectCategories();
            } else if (view.getId() == R.id.updatesalecourse_ll_course_cover) {
                selectCourseCover();
            } else if (view.getId() == R.id.updatesalecourse_ll_introduction_pic) {
                selectIntroductionPic();
            } else if (view.getId() == R.id.updatesalecourse_ItemBeanView_start_time) {
                // 选择课程开始时间
                selectCourseStartTimeDialog();
            } else if (view.getId() == R.id.updatesalecourse_ItemBeanView_end_time) {
                // 选择课程结束时间
                selectCourseEndTimeDialog();
            } else if (view.getId() == R.id.updatesalecourse_tv_save_as_draft) {
                // 完成
                if (checkInput()) {
                    commit(3);
                }
            } else if (view.getId() == R.id.updatesalecourse_tv_save) {
                // 完成
                if (checkInput()) {
                    commit(2);
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

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("修改");
    }

    @Override
    protected void initView() {
        super.initView();
        itemBeanViewType.setRightTitle("排课");
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
                "排课",
                "固定班级"
        };
        // appointment 预约 scheduling 排课 fixed_scheduling 固定班级
        courseTypePicker = new SinglePicker(
                this,
                courseTypeAtt);
        courseTypePicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                itemBeanViewType.setRightTitle(item);
            }
        });
        courseTypePicker.show();
    }

    private boolean checkInput() {
/*
        if (itemBeanViewType.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(itemBeanViewType.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择课程类型");
            return false;
        }*/

        if (TextUtils.isEmpty(itemBeanViewType.getRightTitle())) {
            ToastUtils.showShortToast(this, "请选择课程类目");
            return false;
        }

        if (llConnectPeoplenum.getVisibility() == View.VISIBLE
                && TextUtils.isEmpty(etConnectPeoplenum.getText().toString().trim())) {
            ToastUtils.showShortToast(this, "请输入班级人数");
            return false;
        }

        if (mCourseCoverList == null || mCourseCoverList.size() == 0) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_select_course_cover));
            return false;
        }

        if (TextUtils.isEmpty(etTheme.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.theme_tip));
            return false;
        }
        if (TextUtils.isEmpty(etLabel.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_enter_the_label));
            return false;
        }
        if (TextUtils.isEmpty(etDuration.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入使用期限");
            return false;
        }
        if (TextUtils.isEmpty(etOriginalPrice.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_enter_Original_price));
            return false;
        }
        if (TextUtils.isEmpty(etCourseNumber.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_enter_course_number));
            return false;
        }
        if (llClassPrice.getVisibility() == View.VISIBLE && TextUtils.isEmpty(etClassPrice.getText().toString())) {
            ToastUtils.showShortToast(this, "请输入课节单价");
            return false;
        }

        float disVaule = Float.valueOf(etOriginalPrice.getText().toString().trim()) * 1.15f / Integer.valueOf(etCourseNumber.getText().toString().trim());
        if (llClassPrice.getVisibility() == View.VISIBLE && Float.valueOf(etClassPrice.getText().toString().trim()) > disVaule) {
            ToastUtils.showShortToast(this, "请勿超过(原价/课节数)*(1+15%)");
            return false;
        }
        if (TextUtils.isEmpty(etCourseIntroduction.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_enter_the_course_introduction));
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

    private void commit(int status) {
        String syllabus = "";
        if (!switchSyllabus.isChecked()) {
            syllabus = getDefaultSyllabus();
            String path = mCourseCoverList.get(0);
            List<String> IntroductionUrlList = mIntroductionRvAdapter.getData();
            String introductionUrl = StringUtils.list2String(IntroductionUrlList, ",");
            showUploading();
            String price_list = "0-".concat(etDuration.getText().toString().trim()).concat("-").concat(etClassPrice.getText().toString());
            String startTime = itemBeanViewStartTime.getRightTitle();
            String endTime = itemBeanViewEndTime.getRightTitle();
            getPresenter().updateMasterSetPrice(
                    id,
                    null,
                    getAppointmentType(),
                    etTheme.getText().toString(),
                    etLabel.getText().toString(),
                    etCourseNumber.getText().toString(),
                    etOriginalPrice.getText().toString(),
                    etCourseIntroduction.getText().toString(),
                    status,
                    syllabus,
                    null,
                    "mechanism_offline",
                    etConnectPeoplenum.getText().toString().trim(),
                    LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                    path,
                    introductionUrl,
                    etDuration.getText().toString().trim(),
                    price_list,
                    switch_is_attend_activities.isChecked(),
                    TextUtils.isEmpty(startTime) ? null : startTime.concat(":00"),
                    TextUtils.isEmpty(endTime) ? null : endTime.concat(":00"),
                    etActivityPrice.getText().toString().trim(),
                    itemBeanViewCategories.getRightTitle()
            );
        } else {
            syllabus = titile_url;
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
            Intent intent = new Intent(this, UpdateMechanismCourseSyllabusActivity.class);
            intent.putExtra(ArgumentsConfig.KEY_MECHANISMCOURSEPARAM,
                    JsonUtil.toJson(new MechanismCourseParam(
                            id,
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
                            null,
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

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_update_teachpay_mechanism_course;
    }

    @Override
    protected UpdateTeachPayMechanismCoursePresenter createPresenter() {
        return new UpdateTeachPayMechanismCoursePresenter();
    }

    @Override
    protected void initData() {
        initListener();
        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra(ArgumentsConfig.KEY_MASTERSETPRICEENTITY);
            masterSetPriceEntity = JsonUtil.fromJson(json, MasterSetPriceEntity.class);
            getPresenter().queryMechanismDetailInfoListById(
                    LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                    "teach_paypal"
            );
            bingData(masterSetPriceEntity);
        }

    }

    private void bingData(MasterSetPriceEntity exclusiveCoursesEntity) {
        id = exclusiveCoursesEntity.getId();
        String face_url = exclusiveCoursesEntity.getFace_url();
        String title = exclusiveCoursesEntity.getTitle();
        String course_num = exclusiveCoursesEntity.getCourse_num();
        String amout = exclusiveCoursesEntity.getAmout();
        String label = exclusiveCoursesEntity.getLabel();
        String connect_peoplenum = exclusiveCoursesEntity.getConnect_peoplenum();
        String discount_amout = exclusiveCoursesEntity.getDiscount_amout();
        showCourseCover(face_url);
        String introduction_content = exclusiveCoursesEntity.getIntroduction_content();
        titile_url = exclusiveCoursesEntity.getTitile_url();
        String introduction_url = exclusiveCoursesEntity.getIntroduction_url();
        String duration = exclusiveCoursesEntity.getDuration();
        String price_list = exclusiveCoursesEntity.getPrice_list();
        etTheme.setText(title);
        etCourseNumber.setText(course_num);
        etOriginalPrice.setText(amout);
        etLabel.setText(label);
        String appointment_type = exclusiveCoursesEntity.getAppointment_type();
        String categories = exclusiveCoursesEntity.getCategories();
        if ("appointment".equals(appointment_type)) {
            itemBeanViewType.setRightTitle("预约");
            llConnectPeoplenum.setVisibility(View.GONE);
        } else if ("scheduling".equals(appointment_type)) {
            itemBeanViewType.setRightTitle("排课");
            llConnectPeoplenum.setVisibility(View.GONE);
        } else {
            itemBeanViewType.setRightTitle("固定班级");
            llConnectPeoplenum.setVisibility(View.VISIBLE);
            etConnectPeoplenum.setText(exclusiveCoursesEntity.getConnect_peoplenum());
        }
        itemBeanViewCategories.setRightTitle(categories);
        MasterSetPriceEntity.Map map = exclusiveCoursesEntity.getMap();
        if (map != null) {
            List<MasterSetPriceEntity.Map.PriceEntity> priceList = map.getPriceList();
            if (priceList != null && priceList.size() > 0) {
                MasterSetPriceEntity.Map.PriceEntity priceEntity = priceList.get(0);
                String price = priceEntity.getPrice();
                etClassPrice.setText(price);
            }
            // 活动信息
            ActivityEntity activityEntity = map.getActivityEntity();
            if (activityEntity != null) {
                String start_time = activityEntity.getStart_time();
                String end_time = activityEntity.getEnd_time();
                start_time = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDHMFormatStr);
                end_time = DateUtils.getOtherDateStr(end_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDHMFormatStr);
                itemBeanViewStartTime.setRightTitle(start_time);
                itemBeanViewEndTime.setRightTitle(end_time);
                String activity_price = activityEntity.getPrice();
                etActivityPrice.setText(activity_price);
            }
        }
        etCourseIntroduction.setText(introduction_content);
        etDuration.setText(duration);

        Boolean is_attend_activities = exclusiveCoursesEntity.getIs_attend_activities();
        is_attend_activities = is_attend_activities == null ? false : is_attend_activities;
        switch_is_attend_activities.setChecked(is_attend_activities);
        if (!TextUtils.isEmpty(introduction_url)) {
            String[] split = introduction_url.split(",");
            showIntroductionPic(ConvertUtils.toList(split));
        }
        String titile_url = exclusiveCoursesEntity.getTitile_url();
        if (!TextUtils.isEmpty(titile_url)) {
            String[] split = titile_url.split("#\\$\\*");
            if (split != null && split.length > 0) {
                switchSyllabus.setChecked(true);
            }
        }
    }

    private void initListener() {
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
//        switch_is_attend_activities.setOnCheckedChangeListener(
//                new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                        itemBeanViewStartTime.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                        itemBeanViewEndTime.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                        llActivityPrice.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                    }
//                });
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

    private String getAppointmentType() {
        if ("预约".equals(itemBeanViewType.getRightTitle())) {
            return "appointment";
        } else if ("排课".equals(itemBeanViewType.getRightTitle())) {
            return "scheduling";
        } else {
            return "fixed_scheduling";
        }
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
    protected void onDestroy() {
        try {
            if (categoriesPicker != null) {
                categoriesPicker.dismiss();
            }
            if (courseTypePicker != null) {
                courseTypePicker.dismiss();
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
    public void onUpdateSuccess() {
        ToastUtils.showShortToast(this, "提交成功");
        EventBusUtils.sendEvent(new Event(EventAction.INSERT_MECHANISM_COURSE));
        finish();
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
