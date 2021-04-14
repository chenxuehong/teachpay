package com.eghuihe.module_schedule.ui.mechanism.fragment;

import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.fragment.BaseTitleFragment;
import com.huihe.base_lib.ui.widget.title.CommonTitle;

import butterknife.BindView;

@Route(path = ARouterConfig.SCHEDULE_MECHANIARRANGESMSCHEDULEFIX_FRAGMENT)
public class TeachingPayMechanismArrangeScheduleFixWrapFragment extends BaseTitleFragment {

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout flContainer;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("排课表");
        commonTitle.showStatusBar(true);
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initData() {
        getChildFragmentManager().beginTransaction().replace(
                flContainer.getId(),
                new TeachingPayMechanismArrangeScheduleFixFragment()
        ).commitNowAllowingStateLoss();
    }
}
