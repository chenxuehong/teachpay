package com.eghuihe.module_user.me.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.MyCardFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyCardActivity extends BaseTitleActivity {

    @BindView(R2.id.layout_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewPager)
    ViewPager viewPager;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("我的卡劵");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.layout_viewpager_tablayout;
    }

    @Override
    protected void initData() {
        tabLayout.setupWithViewPager(viewPager);
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        titles.add("全部");
        titles.add("未使用");
        titles.add("已使用");
        titles.add("已过期");

        MyCardFragment allFragment = new MyCardFragment();
        fragments.add(allFragment);

        MyCardFragment unUsedFragment = new MyCardFragment();
        Bundle unUsedArg = new Bundle();
        unUsedArg.putString(ArgumentsConfig.KEY_STATUS, "1");
        unUsedFragment.setArguments(unUsedArg);
        fragments.add(unUsedFragment);

        MyCardFragment usedFragment = new MyCardFragment();
        Bundle usedArg = new Bundle();
        usedArg.putString(ArgumentsConfig.KEY_STATUS, "3");
        usedFragment.setArguments(usedArg);
        fragments.add(usedFragment);

        MyCardFragment overFragment = new MyCardFragment();
        Bundle overArg = new Bundle();
        overArg.putString(ArgumentsConfig.KEY_STATUS, "2");
        overFragment.setArguments(overArg);
        fragments.add(overFragment);
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments));
    }
}
