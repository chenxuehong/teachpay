package com.eghuihe.module_schedule.ui.student.fragment.status_schedule;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_schedule.R;
import com.eghuihe.module_schedule.R2;
import com.eghuihe.module_schedule.ui.student.Bean.StudentScheduleParam;
import com.eghuihe.module_schedule.ui.student.activity.TeachPayStudentSchedulePackageActivity;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;
import com.huihe.base_lib.utils.ARouterUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @desc 教付保学生课程表
 */
@Route(path = ARouterConfig.SCHEDULE_STUDENTSCHEDULE_FRAGMENT)
public class TeachingPayStudentScheduleFragment extends BaseFragment {

    @BindView(R2.id.fragment_teaching_pay_schedule_titleBar)
    CommonTitle commonTitle;
    @BindView(R2.id.fragment_teaching_pay_schedule_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.fragment_teaching_pay_schedule_viewpager)
    ViewPager viewPager;

    private BaseFragment studentSchedulePackageFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_TEACHPAYSTUDENTSCHEDULEPACKAGEFRAGMENT);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teaching_pay_shedule;
    }

    @Override
    protected void initView() {
        super.initView();
        commonTitle.setTitle("课程表");
//        commonTitle.setRightTitleText("预约");
//        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
//            @Override
//            public void onRightTitleClicked(View view) {
//                // 预约
//                startActivity(TeachPayStudentSchedulePackageActivity.class);
//            }
//        });
        List<StudentScheduleParam> studentScheduleParams = new ArrayList<>();
        studentScheduleParams.add(new StudentScheduleParam("待上课", "2", false));
        studentScheduleParams.add(new StudentScheduleParam("待总结", "3", false));
//        studentScheduleParams.add(new StudentScheduleParam("待付款", "8", false));
        studentScheduleParams.add(new StudentScheduleParam("待评论", "9", false));
        studentScheduleParams.add(new StudentScheduleParam("已结束", "9", true));

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        viewPager.setOffscreenPageLimit(titles.size());
        // 待预约
        titles.add("待预约");
        fragments.add(studentSchedulePackageFragment);

        // 已预约
        for (int i = 0; i < studentScheduleParams.size(); i++) {
            StudentScheduleParam studentScheduleParam = studentScheduleParams.get(i);
            titles.add(studentScheduleParam.title);
            ScheduleStudentStatusFragment scheduleStudentStatusFragment = new ScheduleStudentStatusFragment();
            Bundle args = new Bundle();
            args.putString(ArgumentsConfig.KEY_STATUS, studentScheduleParam.status);
            args.putBoolean(ArgumentsConfig.KEY_IS_COMMENT, studentScheduleParam.is_comment);
            scheduleStudentStatusFragment.setArguments(args);
            fragments.add(scheduleStudentStatusFragment);
        }
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getChildFragmentManager(), fragments, titles));
    }
}
