package com.eghuihe.module_user.me.activity;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.fragment.PointsAllListFragment;
import com.eghuihe.module_user.me.fragment.PointsExpensesListFragment;
import com.eghuihe.module_user.me.fragment.PointsRevenueListFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePageAdapter;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyRewardListActivity extends BaseTitleActivity {

    @BindView(R2.id.activity_my_reward_list_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.activity_my_reward_list_tv_select_ym)
    TextView tvSelectYM;
    @BindView(R2.id.activity_my_reward_list_viewpager)
    ViewPager viewPager;

    @OnClick({
            R2.id.activity_my_reward_list_tv_select_ym
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_my_reward_list_tv_select_ym) {
            showSelectYMDialog();
        }
    }

    /**
     * @desc 显示选中年月
     */
    private void showSelectYMDialog() {
        DatePicker datePicker = new DatePicker(this, DatePicker.YEAR_MONTH);
        datePicker.setLabel(
                getResources().getString(com.huihe.base_lib.R.string.year),
                getResources().getString(com.huihe.base_lib.R.string.month),
                getResources().getString(com.huihe.base_lib.R.string.day));
        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                String curYMDateStr = year + "-" + month;
                String curYDateStr = DateUtils.getOtherDateStr(curYMDateStr, DateUtils.YMFormatStr, DateUtils.YFormatStr2);
                String curMDateStr = DateUtils.getOtherDateStr(curYMDateStr, DateUtils.YMFormatStr, DateUtils.MFormatStr2);
                int monthOfDay = DateUtils.getMonthOfDay(DateUtils.trimZero(curYDateStr), DateUtils.trimZero(curMDateStr));
                String startTime = curYMDateStr.concat("-01").concat(" 00:00:00");
                String endTime = curYMDateStr.concat("-").concat(String.valueOf(monthOfDay)).concat(" 00:00:00");
                selectedYM(curYMDateStr, startTime, endTime);
            }
        });
        datePicker.setRangeStart(DateUtils.getCurYear() - 10, 1);
        datePicker.setRangeEnd(DateUtils.getCurYear(), DateUtils.getCurMonth());
        datePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth());
        datePicker.setResetWhileWheel(false);
        datePicker.show();
    }

    private void selectedYM(String curYMDateStr, String startTime, String endTime) {
        tvSelectYM.setText(curYMDateStr);
        loadData(startTime, endTime);
    }

    private void loadData(String startTime, String endTime) {
        EventBusUtils.sendEvent(new Event(EventAction.POINTS_LIST, startTime));
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("积分明细");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_my_reward_list;
    }

    @Override
    protected void initView() {
        super.initView();
        tabLayout.setupWithViewPager(viewPager);
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("收入");
        titles.add("支出");
        viewPager.setOffscreenPageLimit(titles.size());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PointsAllListFragment());
        fragments.add(new PointsRevenueListFragment());
        fragments.add(new PointsExpensesListFragment());
        viewPager.setAdapter(new BaseFragmentStatePageAdapter(getSupportFragmentManager(), titles, fragments));
    }

    @Override
    protected void initData() {
        String curYMDateStr = DateUtils.getCurDateStr(DateUtils.YMFormatStr);
        tvSelectYM.setText(curYMDateStr);
    }
}
