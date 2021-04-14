package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.TeachingCommentListFragment;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import butterknife.BindView;
@Route(path = ARouterConfig.ME_MECHANISMCOURSECOMMENTLISTACTIVITY)
public class MechanismCourseCommentListActivity extends BaseTitleActivity {

    private String appointment_id;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("课程评价");
    }

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout frameLayout;

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            appointment_id = intent.getStringExtra(ArgumentsConfig.KEY_APPOINTMENT_ID);
        }
        TeachingCommentListFragment teachingCommentListFragment = new TeachingCommentListFragment();
        Bundle agrs = new Bundle();
        agrs.putString(ArgumentsConfig.KEY_APPOINTMENT_ID, appointment_id);
        agrs.putString(ArgumentsConfig.KEY_TYPE, "teach_paypal_course");
        teachingCommentListFragment.setArguments(agrs);
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), teachingCommentListFragment).commitNowAllowingStateLoss();
    }
}
