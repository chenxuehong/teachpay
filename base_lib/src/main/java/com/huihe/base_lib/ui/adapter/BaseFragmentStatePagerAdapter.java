package com.huihe.base_lib.ui.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragments;
    private List<String> titles;

    public BaseFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (titles != null) {

            String title = titles.get(position);
            return title;
        }

        return super.getPageTitle(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}