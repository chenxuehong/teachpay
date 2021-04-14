package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.DefineSyllsabusFragment;
import com.eghuihe.module_user.me.fragment.StageSyllsabusFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SetMechanismCourseSyllabusActivity extends BaseTitleActivity {

    @BindView(R2.id.layout_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewPager)
    ViewPager viewPager;
    private String mechanismCourseParam;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        super.initTitle(customerTitle);
        customerTitle.setTitle(getResources().getString(R.string.Add_Mechanism_courses));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.layout_viewpager_tablayout;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent!=null){
            mechanismCourseParam = intent.getStringExtra(ArgumentsConfig.KEY_MECHANISMCOURSEPARAM);
        }
    }

    @Override
    protected void initData() {
        tabLayout.setupWithViewPager(viewPager);
        List<String> titles = new ArrayList<>();
        titles.add("自定义大纲");
        titles.add("阶段性大纲");

        List<Fragment> fragments = new ArrayList<>();
        DefineSyllsabusFragment defineSyllsabusFragment = new DefineSyllsabusFragment();
        StageSyllsabusFragment stageSyllsabusFragment = new StageSyllsabusFragment();

        Bundle defineSyllsabusArgs = new Bundle();
        defineSyllsabusArgs.putString(ArgumentsConfig.KEY_MECHANISMCOURSEPARAM,mechanismCourseParam);
        defineSyllsabusFragment.setArguments(defineSyllsabusArgs);

        Bundle stageSyllsabusArgs = new Bundle();
        stageSyllsabusArgs.putString(ArgumentsConfig.KEY_MECHANISMCOURSEPARAM,mechanismCourseParam);
        stageSyllsabusFragment.setArguments(stageSyllsabusArgs);

        fragments.add(defineSyllsabusFragment);
        fragments.add(stageSyllsabusFragment);
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentStatePageAdapter(
                getSupportFragmentManager(),
                titles,
                fragments));
    }
}
