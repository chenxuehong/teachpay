package com.eghuihe.module_schedule.ui.teacher.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.teacher.fragment.schedule.ScheduleTeacherOverFragment;
import com.eghuihe.module_schedule.ui.teacher.fragment.schedule.ScheduleTeacherWaitingClassFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CommonTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @desc 教付保机构课程表
 */
@Route(path = ARouterConfig.SCHEDULE_TEACHERSCHEDULE_FRAGMENT)
public class TeachingPayTeacherScheduleFragment extends BaseFragment {

    @BindView(R2.id.fragment_teaching_pay_schedule_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.fragment_teaching_pay_schedule_viewpager)
    ViewPager viewPager;
    @BindView(R2.id.fragment_teaching_pay_schedule_titleBar)
    CommonTitle commonTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teaching_pay_shedule;
    }

    @Override
    protected void initData() {
        super.initData();
        commonTitle.setTitle("课程表");
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<String> titles = new ArrayList<>();
        titles.add("待上课");
        titles.add("已结束");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ScheduleTeacherWaitingClassFragment());
        fragments.add(new ScheduleTeacherOverFragment());
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getChildFragmentManager(), fragments, titles));
    }
}
