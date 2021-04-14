package com.eghuihe.module_schedule.ui.mechanism.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling.MechanismAppointmentSchedulingFragment;
import com.eghuihe.module_schedule.ui.mechanism.fragment.scheduling.MechanismCourseScheduleFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;
import com.huihe.base_lib.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @desc 教付保机构排课表修改版
 */

public class TeachingPayMechanismArrangeScheduleFixFragment extends BaseFragment {

    @BindView(R2.id.layout_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_viewpager_tablayout;
    }

    @Override
    protected void initView() {
        super.initView();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<String> titles = new ArrayList<>();
        titles.add("固定");
        titles.add("约课");
        titles.add("自由");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            MechanismCourseScheduleFragment mechanismCourseScheduleFragment = new MechanismCourseScheduleFragment();
            Bundle args = new Bundle();
            if ("固定".equals(title)) {
                args.putString(ArgumentsConfig.KEY_APPOINTMENT_TYPE, "fixed_scheduling");
                mechanismCourseScheduleFragment.setArguments(args);
                fragments.add(mechanismCourseScheduleFragment);
            } else if ("约课".equals(title)) {
                fragments.add(new MechanismAppointmentSchedulingFragment());
            } else {
                args.putString(ArgumentsConfig.KEY_APPOINTMENT_TYPE, "scheduling");
                mechanismCourseScheduleFragment.setArguments(args);
                fragments.add(mechanismCourseScheduleFragment);
            }

        }
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getChildFragmentManager(), fragments, titles));
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
