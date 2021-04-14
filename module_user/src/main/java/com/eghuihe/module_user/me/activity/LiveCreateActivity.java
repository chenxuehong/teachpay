package com.eghuihe.module_user.me.activity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.AppointmentLiveFragment;
import com.eghuihe.module_user.me.fragment.CreateLiveFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveCreateActivity extends BaseActivity {

    @BindView(R2.id.activity_live_create_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.activity_live_create_viewPager)
    ViewPager viewPager;
    @OnClick({
            R2.id.activity_live_create_fl_back
    })
    public void onViewClicked(View view) {

        if (view.getId() == R.id.activity_live_create_fl_back) {
            finish();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_live_create;
    }

    @Override
    protected void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("创建直播");
        titles.add("预约直播");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CreateLiveFragment());
        fragments.add(new AppointmentLiveFragment());
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getSupportFragmentManager(), fragments, titles));
    }
}
