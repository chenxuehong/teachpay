package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.view.View;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import butterknife.OnClick;

/**
 * @desc 选择课程类型
 */
public class SelectMechanismCourseTypeActivity extends BaseTitleActivity {

    private String mechanism_id;

    @OnClick({
            R2.id.activity_select_mechanism_course_type_iv_fixed_scheduling,
            R2.id.activity_select_mechanism_course_type_iv_appointment,
            R2.id.activity_select_mechanism_course_type_iv_scheduling,
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_select_mechanism_course_type_iv_fixed_scheduling) {
            // 添加机构课程
            addMechanismCourse("fixed_scheduling");
        } else if (view.getId() == R.id.activity_select_mechanism_course_type_iv_appointment) {
            addMechanismCourse("appointment");
        } else if (view.getId() == R.id.activity_select_mechanism_course_type_iv_scheduling) {
            addMechanismCourse("scheduling");
        }
    }

    private void addMechanismCourse(String appointment_type) {
        Intent intent = new Intent(SelectMechanismCourseTypeActivity.this,
                InsertMechanismCourseActivity.class);
        intent.putExtra(ArgumentsConfig.KEY_MECHANISM_ID,
                mechanism_id);
        intent.putExtra(ArgumentsConfig.KEY_APPOINTMENT_TYPE,
                appointment_type);
        startActivity(intent);
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("选择课程类型");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_select_mechanism_course_type;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mechanism_id = intent.getStringExtra(ArgumentsConfig.KEY_MECHANISM_ID);
        }
    }
}
