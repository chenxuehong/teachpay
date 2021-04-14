package com.eghuihe.module_home.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.R2;
import com.eghuihe.module_home.home.fragment.MechanismListByCategoriesFragment;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;

/**
 * @desc 通过机构名查询机构
 */
public class MechanismListByQueryActivity extends BaseTitleActivity {
    public static final String KEY_MECHANISM_NAME = "mechanism_name";
    private String mechanism_name;

    @BindView(R2.id.activity_fragmentlayout_container)
    FrameLayout frameLayout;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("机构");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_framelayout;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            mechanism_name = intent.getStringExtra(KEY_MECHANISM_NAME);
        }
    }

    @Override
    protected void initData() {
        MechanismListByCategoriesFragment mechanismListByCategoriesFragment = new MechanismListByCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(MechanismListByCategoriesFragment.KEY_MECHANISM_NAME, mechanism_name);
        args.putString(MechanismListByCategoriesFragment.KEY_LATITUDE, LoginHelper.getLatitude());
        args.putString(MechanismListByCategoriesFragment.KEY_LONGITUDE, LoginHelper.getLongitude());
        mechanismListByCategoriesFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), mechanismListByCategoriesFragment).commitNowAllowingStateLoss();
    }
}
