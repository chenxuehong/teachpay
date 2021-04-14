package com.eghuihe.module_user.me.activity;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.adapter.RewardCourseRvAdapter;
import com.eghuihe.module_user.me.adapter.SignDayRvAdapter;
import com.eghuihe.module_user.me.adapter.TaskRvAdapter;
import com.eghuihe.module_user.me.mvp.MyRewardContract;
import com.eghuihe.module_user.me.mvp.MyRewardPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.TeachPaypalDetailEntity;
import com.huihe.base_lib.model.UserGoldTypeEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.ARouterUtils;
import com.huihe.base_lib.utils.ColorAndSizeTextUtil;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 我的积分
 */
public class MyRewardActivity extends BaseMvpTitleActivity<MyRewardPresenter>
        implements MyRewardContract.View {

    @BindView(R2.id.activity_my_reward_tv_points)
    TextView tvPoints;
    @BindView(R2.id.my_reward_signlist_card_tv_signDays)
    TextView tvSignDays;
    @BindView(R2.id.my_reward_signlist_card_tv_sign)
    TextView tvSign;
    @BindView(R2.id.my_reward_signlist_card_rv_signDayList)
    RecyclerViewFixed rvSignDayList;
    @BindView(R2.id.activity_my_reward_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.activity_my_reward_RecyclerViewFixed)
    RecyclerViewFixed recyclerViewFixed;

    private RefreshLayout mRefreshLayout;
    private LoadPagerManager loadPagerManager;
    private TaskRvAdapter taskRvAdapter;
    private TeachPayCourseListRvAdapter rewardCourseRvAdapter;
    private ExpandRVAdapter expandRVAdapter;

    @OnClick({
            R2.id.activity_my_reward_tv_points_detail
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_my_reward_tv_points_detail) {
            startActivity(MyRewardListActivity.class);
        }
    }


    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle("我的积分");
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_my_reward;
    }

    @Override
    protected void initView() {
        super.initView();
        loadPagerManager = new LoadPagerManager();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.scrollToFirst();
                    loadData(loadPagerManager.getCurrentPage(), loadPagerManager.getPageSize());
                }
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.currentPageUp();
                    loadData(loadPagerManager.getCurrentPage(), loadPagerManager.getPageSize());
                }
            }
        });
        String points = LoginHelper.getLoginInfo().getUserInfoEntity().getPoints();
        tvPoints.setText(points);
    }

    private void loadData(int currentPage, int pageSize) {
        getPresenter().queryNearByCourse(
                2,
                currentPage,
                pageSize,
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "mechanism_offline"
        );
    }

    @Override
    protected void initData() {
        rewardCourseRvAdapter =  new TeachPayCourseListRvAdapter(R.layout.item_query_mechanism_course, this,R.layout.layout_no_data);
        rewardCourseRvAdapter.showLine(true);
        expandRVAdapter = new ExpandRVAdapter(rewardCourseRvAdapter);
        View taskView = View.inflate(this, R.layout.layout_sign_title, null);
        TextView tvTitle = taskView.findViewById(R.id.layout_sign_title_tv_title);
        tvTitle.setText("做任务赚积分");
        expandRVAdapter.addHanderView(taskView);

        View taskListView = View.inflate(this, R.layout.layout_sign_list, null);
        RecyclerViewFixed rvSignTask = taskListView.findViewById(R.id.layout_sign_list_rv);
        rvSignTask.setScrollingEnabled(false);
        rvSignTask.setVertical(1);
        taskRvAdapter = new TaskRvAdapter(R.layout.item_reward_task, this);
        rvSignTask.setAdapter(taskRvAdapter);
        expandRVAdapter.addHanderView(taskListView);

        View courseView = View.inflate(this, R.layout.layout_sign_title, null);
        TextView tvCourseTitle = courseView.findViewById(R.id.layout_sign_title_tv_title);
        tvCourseTitle.setText("热销课程");
        expandRVAdapter.addHanderView(courseView);
        recyclerViewFixed.setVertical(1);
        recyclerViewFixed.addGridViewItemDecoration(1, DensityUtils.dp2px(this, 12), 3);
        recyclerViewFixed.setAdapter(expandRVAdapter);
        getPresenter().start();
    }

    private void finishRefreshWithNoMoreData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefreshWithNoMoreData();
        }
    }

    private void finishLoadMoreWithNoMoreData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    protected MyRewardPresenter createPresenter() {
        return new MyRewardPresenter();
    }

    @Override
    public void onTeachPaypalDetail(TeachPaypalDetailEntity teachPaypalDetailEntity) {
        // 签到列表
        ColorAndSizeTextUtil.setColorText(
                tvSignDays,
                "已连续签到".concat(teachPaypalDetailEntity.singInDay).concat("天"),
                new String[]{teachPaypalDetailEntity.singInDay},
                R.color.color_FF7300,
                16,
                null
        );
        if (teachPaypalDetailEntity.todaySignIn) {
            tvSign.setText("已签到");
        } else {
            tvSign.setText("签到领积分");
            tvSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPresenter().insertLoginSignIn(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
                }
            });
        }
        tvSign.setEnabled(!teachPaypalDetailEntity.todaySignIn);
        List<TeachPaypalDetailEntity.GoldEntity> goldList = getDayList(teachPaypalDetailEntity.goldList);
        rvSignDayList.setVertical(7);
        rvSignDayList.setScrollingEnabled(false);
        rvSignDayList.setAdapter(new SignDayRvAdapter(R.layout.item_reward_sign_day, this, goldList, teachPaypalDetailEntity.singInDay));
    }

    private List<TeachPaypalDetailEntity.GoldEntity> getDayList(List<TeachPaypalDetailEntity.GoldEntity> goldList) {
        List<TeachPaypalDetailEntity.GoldEntity> newGoldList = new ArrayList<>();
        if (goldList != null && goldList.size() > 7) {
            for (int i = 0; i < 7; i++) {
                newGoldList.add(goldList.get(i));
            }
        }
        return newGoldList;
    }

    @Override
    public void onUserGoldTypeList(List<UserGoldTypeEntity> userGoldTypeEntities) {
        // 任务列表
        taskRvAdapter.setData(userGoldTypeEntities);
        expandRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCourseList(List<MasterSetPriceEntity> masterSetPriceEntities) {
        if (loadPagerManager != null) {
            int currentPage = loadPagerManager.getCurrentPage();
            int pageSize = loadPagerManager.getPageSize();
            if (currentPage == 1) {
                rewardCourseRvAdapter.setData(masterSetPriceEntities);
            } else {
                rewardCourseRvAdapter.addData(masterSetPriceEntities);
            }
            closeLoading();
            if (masterSetPriceEntities == null || masterSetPriceEntities.size() < pageSize) {
                if (currentPage == 1) {
                    finishRefreshWithNoMoreData();
                } else {
                    finishLoadMoreWithNoMoreData();
                }
            }
        }
        expandRVAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSignSucess() {
        getPresenter().start();
        EventBusUtils.sendEvent(new Event(EventAction.RESET_USERINFO));
    }
}
