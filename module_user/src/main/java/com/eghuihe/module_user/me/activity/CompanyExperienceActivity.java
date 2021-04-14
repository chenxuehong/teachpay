package com.eghuihe.module_user.me.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventUtils;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class CompanyExperienceActivity extends BaseTitleActivity {

    @BindView(R2.id.company_experience_et_Company_name)
    EditText etCompanyName;
    @BindView(R2.id.company_experience_et_line_of_business)
    EditText etLineOfBusiness;
    @BindView(R2.id.company_experience_et_Duty)
    EditText etDuty;

    @BindView(R2.id.company_experience_tv_start_time)
    TextView tvStartTime;
    @BindView(R2.id.company_experience_tv_end_time)
    TextView tvEndTime;
    @BindView(R2.id.company_experience_switch_public)
    Switch switchPublic;

    private boolean is_visible;

    @OnClick({
            R2.id.company_experience_tv_start_time,
            R2.id.company_experience_tv_end_time,
            R2.id.company_experience_tv_save
    })
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() ==R.id.company_experience_tv_start_time ){
                // 开始时间
                DatePicker datePicker = new DatePicker(CompanyExperienceActivity.this, DatePicker.YEAR_MONTH_DAY);
                datePicker.setLabel(getResources().getString(R.string.year), getResources().getString(R.string.month), getResources().getString(R.string.day));
                datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String startTime = year + "-" + month + "-" + day;
                        tvStartTime.setText(startTime);
                    }
                });
                datePicker.setRangeStart(DateUtils.getCurYear() - 40, 1, 1);
                datePicker.setRangeEnd(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
                datePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
                datePicker.setResetWhileWheel(false);
                datePicker.show();
            }else if (view.getId() ==  R.id.company_experience_tv_end_time){
                // 结束时间
                DatePicker endDatePicker = new DatePicker(CompanyExperienceActivity.this, DatePicker.YEAR_MONTH_DAY);
                endDatePicker.setLabel(getResources().getString(R.string.year), getResources().getString(R.string.month), getResources().getString(R.string.day));
                endDatePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String Day) {
                        String endTime = year + "-" + month + "-" + Day;
                        tvEndTime.setText(endTime);
                    }
                });
                endDatePicker.setRangeStart(DateUtils.getCurYear() - 40, 1, 1);
                endDatePicker.setRangeEnd(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
                endDatePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
                endDatePicker.setResetWhileWheel(false);
                endDatePicker.show();
            }else if (view.getId() ==  R.id.company_experience_tv_save){
                // 保存
                String company_name = getResources().getString(R.string.tip_enter_company_name).equals(etCompanyName.getText().toString()) ?
                        "" : etCompanyName.getText().toString();
                String company_industry = getResources().getString(R.string.tip_enter_line_of_business).equals(etLineOfBusiness.getText().toString()) ?
                        "" : etLineOfBusiness.getText().toString();
                String duty = etDuty.getText().toString();
                String start_time = tvStartTime.getText().toString();
                String end_time = tvEndTime.getText().toString();
                // is_visible 是否公开
                save(company_name, company_industry, duty, start_time, end_time, is_visible);
            }
        }
    }

    protected void save(String company_name, String company_industry, String duty, String start_time, String end_time, boolean is_visible) {

    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Company_experience));
        initRightTitle(customerTitle);
    }

    protected void initRightTitle(CustomerTitle customerTitle) {

    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_insert_company_experience;
    }

    @Override
    protected void initView() {
        super.initView();
        is_visible = true;
        switchPublic.setChecked(true);
        switchPublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleSwitch(isChecked);
            }
        });
    }

    public void toggleSwitch(boolean isChecked) {
        is_visible = isChecked;
    }
}
