package com.eghuihe.module_home.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.R2;
import com.eghuihe.module_home.home.fragment.MechanismListByCategoriesFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MechanismListByTypeActivity extends BaseTitleActivity {

    public static final String KEY_CATEGORIES = "categories";
    @BindView(R2.id.activity_mechanism_list_by_type_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.activity_mechanism_list_by_type_viewpager)
    ViewPager viewPager;

    private String categories;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        Intent intent = getIntent();
        if (intent != null) {
            categories = intent.getStringExtra(KEY_CATEGORIES);
        }
        customerTitle.setTitle(categories);
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_mechanism_list_by_type;
    }

    @Override
    protected void initData() {
        tabLayout.setupWithViewPager(viewPager);
        String[] childCategories = DataLoader.getInstance().getCategoriesChildArr(categories);
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        if (childCategories != null && childCategories.length > 0) {
            for (int i = 0; i < childCategories.length; i++) {
                titles.add(childCategories[i]);
                MechanismListByCategoriesFragment mechanismListByCategoriesFragment = new MechanismListByCategoriesFragment();
                Bundle args = new Bundle();
                args.putString(MechanismListByCategoriesFragment.KEY_CATEGORIES, categories);
                args.putString(MechanismListByCategoriesFragment.KEY_CATEGORIES_CHILD, childCategories[i]);
                args.putString(MechanismListByCategoriesFragment.KEY_LATITUDE, LoginHelper.getLatitude());
                args.putString(MechanismListByCategoriesFragment.KEY_LONGITUDE, LoginHelper.getLongitude());
                mechanismListByCategoriesFragment.setArguments(args);
                fragments.add(mechanismListByCategoriesFragment);
            }
        } else {
            titles.add("其他");
            MechanismListByCategoriesFragment mechanismListByCategoriesFragment = new MechanismListByCategoriesFragment();
            Bundle args = new Bundle();
            args.putString(MechanismListByCategoriesFragment.KEY_CATEGORIES, categories);
            args.putString(MechanismListByCategoriesFragment.KEY_CATEGORIES_CHILD, null);
            args.putString(MechanismListByCategoriesFragment.KEY_LATITUDE, LoginHelper.getLatitude());
            args.putString(MechanismListByCategoriesFragment.KEY_LONGITUDE, LoginHelper.getLongitude());
            mechanismListByCategoriesFragment.setArguments(args);
            fragments.add(mechanismListByCategoriesFragment);
        }
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getSupportFragmentManager(), fragments, titles));
    }
}
