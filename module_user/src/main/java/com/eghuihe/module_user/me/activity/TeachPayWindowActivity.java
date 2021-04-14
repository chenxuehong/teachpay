package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.TeachPayMechanismCourseFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventBusUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @desc 课程管理
 */
public class TeachPayWindowActivity extends BaseTitleActivity {

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_MECHANISM_ID = "mechanism_id";

    @BindView(R2.id.layout_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.layout_viewPager)
    ViewPager viewPager;
    private String mechanism_id;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("课程管理");
        customerTitle.setRightImg(R.mipmap.ic_im_add_to);
        customerTitle.setImgRightListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                // 添加机构课程
                Intent intent = new Intent(TeachPayWindowActivity.this, SelectMechanismCourseTypeActivity.class);
                intent.putExtra(ArgumentsConfig.KEY_MECHANISM_ID,
                        mechanism_id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.layout_viewpager_tablayout;
    }

    @Override
    protected void initData() {
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        Intent intent = getIntent();
        if (intent != null) {
            mechanism_id = intent.getStringExtra(KEY_MECHANISM_ID);
        }
        List<String> titles = new ArrayList<>();
        titles.add("已上架");
        titles.add("已下架");
        titles.add("草稿");

        List<Fragment> fragments = new ArrayList<>();
        TeachPayMechanismCourseFragment mechanismCourseUpFragment = new TeachPayMechanismCourseFragment();
        Bundle arg1 = new Bundle();
        arg1.putString(TeachPayMechanismCourseFragment.KEY_MECHANISM_ID, mechanism_id);
        arg1.putString(TeachPayMechanismCourseFragment.KEY_STATUS, "2");
        mechanismCourseUpFragment.setArguments(arg1);
        fragments.add(mechanismCourseUpFragment);

        TeachPayMechanismCourseFragment mechanismCourseOffFragment = new TeachPayMechanismCourseFragment();
        Bundle arg2 = new Bundle();
        arg2.putString(TeachPayMechanismCourseFragment.KEY_MECHANISM_ID, mechanism_id);
        arg2.putString(TeachPayMechanismCourseFragment.KEY_STATUS, "1");
        mechanismCourseOffFragment.setArguments(arg2);
        fragments.add(mechanismCourseOffFragment);

        TeachPayMechanismCourseFragment mechanismCourseDraftFragment = new TeachPayMechanismCourseFragment();
        Bundle arg3 = new Bundle();
        arg3.putString(TeachPayMechanismCourseFragment.KEY_MECHANISM_ID, mechanism_id);
        arg3.putString(TeachPayMechanismCourseFragment.KEY_STATUS, "3");
        mechanismCourseDraftFragment.setArguments(arg3);
        fragments.add(mechanismCourseDraftFragment);

        viewPager.setAdapter(new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments));
    }

}