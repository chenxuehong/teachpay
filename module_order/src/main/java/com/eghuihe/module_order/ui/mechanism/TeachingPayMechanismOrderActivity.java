package com.eghuihe.module_order.ui.mechanism;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_order.R;
import com.eghuihe.module_order.R2;
import com.eghuihe.module_order.ui.mechanism.MechanismConvertLongOrderListFragment;
import com.eghuihe.module_order.ui.mechanism.MechanismIntentOrderListFragment;
import com.eghuihe.module_order.ui.mechanism.MechanismOncePayOrderListFragment;
import com.eghuihe.module_order.ui.mechanism.MechanismOneLessonOrderListFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.ORDER_TEACHINGPAYMECHANISMORDER)
public class TeachingPayMechanismOrderActivity extends BaseTitleActivity {

    @BindView(R2.id.acivity_teachpay_mechanism_order_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.acivity_teachpay_mechanism_order_viewpager)
    ViewPager viewPager;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("订单");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_teachpay_mechanism_order;
    }

    @Override
    protected void initData() {
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<String> titles = new ArrayList<>();
        titles.add("报名");
        titles.add("单节付");
        titles.add("转长单");
        titles.add("全额购");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MechanismIntentOrderListFragment());
        fragments.add(new MechanismOneLessonOrderListFragment());
        fragments.add(new MechanismConvertLongOrderListFragment());
        fragments.add(new MechanismOncePayOrderListFragment());
        viewPager.setAdapter(new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments));
    }
}
