package com.eghuihe.module_user.me.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.IncomeCenterFragment;
import com.huihe.base_lib.ui.activity.BaseActivity;

import butterknife.BindView;

public class IncomeCenterActivity extends BaseActivity {

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout frameLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initData() {
        IncomeCenterFragment incomeCenterFragment = new IncomeCenterFragment();
        Bundle args = new Bundle();
        args.putBoolean(IncomeCenterFragment.KEY_SHOW_BACK, true);
        incomeCenterFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(
                frameLayout.getId(), incomeCenterFragment
        ).commitNowAllowingStateLoss();
    }
}
