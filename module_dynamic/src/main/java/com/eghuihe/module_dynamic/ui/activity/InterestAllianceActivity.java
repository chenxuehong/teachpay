package com.eghuihe.module_dynamic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.eghuihe.module_dynamic.ui.fragment.DynamicListFragment;
import com.eghuihe.module_dynamic.ui.mvp.InterestAllianceContract;
import com.eghuihe.module_dynamic.ui.mvp.InterestAlliancePresenter;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MechanismCategoryChildEntity;
import com.huihe.base_lib.model.MechanismCategoryEntity;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InterestAllianceActivity extends BaseMvpTitleActivity<InterestAlliancePresenter>
        implements InterestAllianceContract.View {
    @BindView(R2.id.layout_viewpager_scrollable_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewpager_scrollable_viewPager)
    ViewPager viewPager;
    private String selected_classfiy;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("兴趣联盟");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.layout_viewpager_scrollable_tablayout;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            selected_classfiy = intent.getStringExtra(ArgumentsConfig.KEY_CLASSFIY);
        }
    }

    @Override
    protected void initData() {

        getPresenter().queryMechanismCategoryChildList();
    }

    @Override
    public void onMechanismCategoryList(List<MechanismCategoryEntity> mechanismCategoryEntities) {
        List<String> classfiyList = convertModel(mechanismCategoryEntities);
        classfiyList.add(0, "推荐");
        int selectIndex = getSelectedClassfiyIndex(classfiyList, selected_classfiy);

        tabLayout.setupWithViewPager(viewPager);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < classfiyList.size(); i++) {
            String classfiy =  classfiyList.get(i);
            titles.add(classfiy);
            String catory = null;
            if (i == 0) {
                catory = null;
            }else {
                catory = classfiy;
            }
            DynamicListFragment dynamicListFragment = new DynamicListFragment();
            Bundle args = new Bundle();
            args.putString(ArgumentsConfig.KEY_CLASSFIY, catory);
            dynamicListFragment.setArguments(args);
            fragments.add(dynamicListFragment);
        }
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments));
        if (selectIndex < fragments.size()) {
            viewPager.setCurrentItem(selectIndex);
        }
    }

    private int getSelectedClassfiyIndex(List<String> classfiyList, String selected_classfiy) {
        if (classfiyList != null) {
            for (int i = 0; i < classfiyList.size(); i++) {
                String classfiy = classfiyList.get(i);
                if (!TextUtils.isEmpty(classfiy) && classfiy.equals(selected_classfiy)) {
                    return i;
                }
            }
        }
        return 0;
    }

    private List<String> convertModel(List<MechanismCategoryEntity> mechanismCategoryEntities) {

        List<String> newChildList = new ArrayList<>();
        if (mechanismCategoryEntities != null) {
            for (int i = 0; i < mechanismCategoryEntities.size(); i++) {
                MechanismCategoryEntity mechanismCategoryEntity = mechanismCategoryEntities.get(i);
                MechanismCategoryEntity.Map map = mechanismCategoryEntity.getMap();
                if (map != null) {
                    List<MechanismCategoryChildEntity> childList = map.getChildList();
                    if (childList != null) {
                        for (int i1 = 0; i1 < childList.size(); i1++) {
                            MechanismCategoryChildEntity mechanismCategoryChildEntity = childList.get(i1);
                            String name = mechanismCategoryChildEntity.getName();
                            if (!TextUtils.isEmpty(name)) {
                                newChildList.add(name);
                            }
                        }
                    }
                }
            }
        }
        return newChildList;
    }

    @Override
    protected InterestAlliancePresenter createPresenter() {
        return new InterestAlliancePresenter();
    }
}
