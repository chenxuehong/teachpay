package com.eghuihe.module_order.ui.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_order.R;
import com.eghuihe.module_order.R2;
import com.eghuihe.module_order.ui.student.StudentConvertLongOrderListFragment;
import com.eghuihe.module_order.ui.student.StudentIntentOrderListFragment;
import com.eghuihe.module_order.ui.student.StudentOncePayOrderListFragment;
import com.eghuihe.module_order.ui.student.StudentOneLessonOrderListFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.ORDER_TEACHINGPAYSTUDENTORDER)
public class TeachingPayStudentOrderActivity extends BaseTitleActivity {

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
        titles.add("全部");
        titles.add("待付款");
        titles.add("已付款");
        List<Fragment> fragments = new ArrayList<>();
        StudentOrderListFragment allOrderFragment = new StudentOrderListFragment();

        StudentOrderListFragment unpayedOrderFragment = new StudentOrderListFragment();
        Bundle unpayedArgs = new Bundle();
        unpayedArgs.putString(ArgumentsConfig.KEY_STATUS,"1");
        unpayedOrderFragment.setArguments(unpayedArgs);

        StudentOrderListFragment payedOrderFragment = new StudentOrderListFragment();
        Bundle payedArgs = new Bundle();
        payedArgs.putString(ArgumentsConfig.KEY_STATUS,"2");
        payedOrderFragment.setArguments(payedArgs);

        fragments.add(allOrderFragment);
        fragments.add(unpayedOrderFragment);
        fragments.add(payedOrderFragment);
        viewPager.setAdapter(new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments));
    }
}
