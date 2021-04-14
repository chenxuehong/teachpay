package com.huihe.base_lib.ui.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class BaseFragmentStatePageAdapter extends FragmentStatePagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;

    public BaseFragmentStatePageAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    public BaseFragmentStatePageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {

        if (fragments != null) {
            Fragment fragment = fragments.get(i);
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    // 动态设置我们标题的方法
    public void setPageTitle(int position, String title)
    {
        if(position >= 0 && position < titles.size())
        {
            titles.set(position, title);
            notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (titles != null) {
            return titles.get(position);
        }
        return super.getPageTitle(position);
    }
}
