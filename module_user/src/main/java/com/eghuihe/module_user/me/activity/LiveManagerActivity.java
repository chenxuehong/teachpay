package com.eghuihe.module_user.me.activity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.LiveHistoryListFragment;
import com.eghuihe.module_user.me.fragment.WaitingLiveListFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;
import com.huihe.base_lib.ui.widget.DragFloatActionButton;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveManagerActivity extends BaseTitleActivity {

    @BindView(R2.id.layout_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewPager)
    ViewPager viewPager;
    @BindView(R2.id.activity_live_manager_dragFloatActionBtn)
    DragFloatActionButton dragFloatActionButton;

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_live_manager;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("直播管理");
    }

    @Override
    protected void initView() {
        super.initView();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        dragFloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建直播
                startActivity(LiveCreateActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("待开播");
        titles.add("直播历史");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WaitingLiveListFragment());
        fragments.add(new LiveHistoryListFragment());
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getSupportFragmentManager(), fragments, titles));
    }
}
