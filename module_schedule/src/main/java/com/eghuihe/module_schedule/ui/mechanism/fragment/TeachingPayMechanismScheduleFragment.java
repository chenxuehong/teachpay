package com.eghuihe.module_schedule.ui.mechanism.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.mechanism.fragment.schdule.ScheduleMechanismOverCourseFragment;
import com.eghuihe.module_schedule.ui.mechanism.fragment.schdule.ScheduleMechanismWaitingClassCourseFragment;
import com.eghuihe.module_schedule.ui.mechanism.fragment.schdule.ScheduleMechanismWaitingClassScheduleFragment;
import com.eghuihe.module_schedule.ui.mechanism.fragment.schdule.ScheduleMechanismOverScheduleFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @desc 教付保机构课程表
 */
@Route(path = ARouterConfig.SCHEDULE_MECHANISMSCHEDULE_FRAGMENT)
public class TeachingPayMechanismScheduleFragment extends BaseFragment {

    @BindView(R2.id.fragment_teaching_pay_schedule_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.fragment_teaching_pay_schedule_viewpager)
    ViewPager viewPager;
    @BindView(R2.id.fragment_teaching_pay_schedule_titleBar)
    CommonTitle commonTitle;
    private int selectedModeId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teaching_pay_shedule;
    }

    @Override
    protected void initView() {
        super.initView();
        selectedModeId = R.mipmap.mode_view;
        commonTitle.setTitle("课程表");
        commonTitle.setRightIcon3(selectedModeId);
        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
            @Override
            public void onRightIcon3Clicked(View view) {
                if (selectedModeId == R.mipmap.mode_list_white) {
                    // 切换到课程表
                    selectedModeId = R.mipmap.mode_view;
                    switch2ListMode(true);
                } else {
                    // 切换到排课表
                    selectedModeId = R.mipmap.mode_list_white;
                    switch2ListMode(false);
                }
                commonTitle.setRightIcon3(selectedModeId);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void switch2ListMode(boolean isListMode) {
        if (isListMode) {
            List<String> titles = new ArrayList<>();
            titles.add("待上课");
            titles.add("已结束");
            List<Fragment> fragments = new ArrayList<>();
            fragments.add(new ScheduleMechanismWaitingClassCourseFragment());
            fragments.add(new ScheduleMechanismOverCourseFragment());
            viewPager.setOffscreenPageLimit(titles.size());
            viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getChildFragmentManager(), fragments, titles));
        } else {
            List<String> titles = new ArrayList<>();
            List<Fragment> fragments = new ArrayList<>();
            titles.add("待上课");
            titles.add("已结束");
            fragments.add(new ScheduleMechanismWaitingClassScheduleFragment());
            fragments.add(new ScheduleMechanismOverScheduleFragment());
            viewPager.setOffscreenPageLimit(titles.size());
            viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getChildFragmentManager(), fragments, titles));
        }
    }

    @Override
    protected void initData() {
        super.initData();
        switch2ListMode(true);
    }
}
