package com.eghuihe.module_schedule.ui.mechanism.activity;

import android.widget.FrameLayout;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.fragment.TeachingPayMechanismArrangeScheduleFixFragment;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ARouterUtils;

import butterknife.BindView;

public class TeachingPayMechanismArrangeScheduleFixActivity extends BaseTitleActivity {

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout flContainer;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("排课表");
        customerTitle.setLeftTextVisible(true);
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction().replace(
                flContainer.getId(),
                new TeachingPayMechanismArrangeScheduleFixFragment()
        ).commitNowAllowingStateLoss();
    }
}
