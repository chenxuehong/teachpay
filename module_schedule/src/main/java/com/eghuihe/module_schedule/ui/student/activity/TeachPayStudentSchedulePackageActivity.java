package com.eghuihe.module_schedule.ui.student.activity;

import android.widget.FrameLayout;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ARouterUtils;

import butterknife.BindView;

public class TeachPayStudentSchedulePackageActivity extends BaseTitleActivity {

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout flContainer;

    private BaseFragment studentSchedulePackageFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEFRAGMENT);

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("预约");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initData() {

        getSupportFragmentManager().beginTransaction().replace(
                flContainer.getId(),
                studentSchedulePackageFragment
        ).commitNowAllowingStateLoss();
    }
}
