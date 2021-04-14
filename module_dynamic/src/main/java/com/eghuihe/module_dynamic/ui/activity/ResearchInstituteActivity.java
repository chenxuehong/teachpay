package com.eghuihe.module_dynamic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.R2;
import com.eghuihe.module_dynamic.ui.fragment.DynamicListFragment;
import com.eghuihe.module_dynamic.ui.mvp.ResearchInstituteContract;
import com.eghuihe.module_dynamic.ui.mvp.ResearchInstitutePresenter;
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

public class ResearchInstituteActivity extends BaseMvpTitleActivity<ResearchInstitutePresenter>
        implements ResearchInstituteContract.View {
    @BindView(R2.id.layout_viewpager_scrollable_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewpager_scrollable_viewPager)
    ViewPager viewPager;
    private String selected_classfiy;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("学研社");
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
    protected ResearchInstitutePresenter createPresenter() {
        return new ResearchInstitutePresenter();
    }

    @Override
    protected void initData() {
        getPresenter().queryMechanismCategoryChildList();
    }

    @Override
    public void onMechanismCategoryList(List<MechanismCategoryEntity> mechanismCategoryEntities) {
        List<String> classfiyList = convertModel(mechanismCategoryEntities);
        int selectIndex = getSelectedClassfiyIndex(classfiyList, selected_classfiy);

        tabLayout.setupWithViewPager(viewPager);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < classfiyList.size(); i++) {
            String classfiy = classfiyList.get(i);
            titles.add(classfiy);
            DynamicListFragment dynamicListFragment = new DynamicListFragment();
            Bundle args = new Bundle();
            args.putString(ArgumentsConfig.KEY_CLASSFIY, classfiy);
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
        MechanismCategoryEntity mechanismCategoryEntity = getResearchInstituteCategoryEntity(mechanismCategoryEntities);
        if (mechanismCategoryEntity != null) {
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
        return newChildList;
    }

    private MechanismCategoryEntity getResearchInstituteCategoryEntity(List<MechanismCategoryEntity> mechanismCategoryEntities) {

        for (int i = 0; i < mechanismCategoryEntities.size(); i++) {
            MechanismCategoryEntity mechanismCategoryEntity = mechanismCategoryEntities.get(i);
            String category = mechanismCategoryEntity.getName();
            if ("教辅学科".equals(category)){
                return mechanismCategoryEntity;
            }
        }
        return null;
    }
}
