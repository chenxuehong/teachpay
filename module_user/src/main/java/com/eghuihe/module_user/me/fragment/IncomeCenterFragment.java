package com.eghuihe.module_user.me.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.adapter.MechanismIncomeRvAdapter;
import com.eghuihe.module_user.me.mvp.IncomeCenterContract;
import com.eghuihe.module_user.me.mvp.IncomeCenterPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.model.MechanismIncomeEntity;
import com.huihe.base_lib.model.MechanismIncomeStatisticsEntity;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterConfig.ME_MECHANISM_INCOMECENTERFRAGMENT)
public class IncomeCenterFragment extends BaseMvpFragment<IncomeCenterPresenter>
        implements IncomeCenterContract.View {

    public static final String KEY_SHOW_BACK = "showBack";
    @BindView(R2.id.activity_income_CommonTitle)
    CommonTitle commonTitle;
    @BindView(R2.id.activity_income_tv_totalIncome)
    TextView tvTotalIncome;
    @BindView(R2.id.activity_income_tv_todayIncome)
    TextView tvTodayIncome;
    @BindView(R2.id.activity_income_tv_settled)
    TextView tvSettled;
    @BindView(R2.id.activity_income_tv_unSettle)
    TextView tvUnSettled;
    @BindView(R2.id.activity_income_tv_selectYM)
    TextView tvSelectYM;
    @BindView(R2.id.activity_income_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.activity_income_rv)
    RecyclerViewFixed rvList;
    private boolean isShowBack;

    private RefreshLayout mRefreshLayout;
    private LoadPagerManager loadPagerManager;
    private MechanismIncomeRvAdapter mechanismIncomeRvAdapter;

    @OnClick({
            R2.id.activity_income_tv_selectYM
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_income_tv_selectYM) {
            showSelectYMDialog();
        }
    }

    @Override
    protected IncomeCenterPresenter createPresenter() {
        return new IncomeCenterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_income_center;
    }

    @Override
    protected void initView() {
        super.initView();
        isShowBack = false;
        Bundle arguments = getArguments();
        if (arguments != null) {
            isShowBack = arguments.getBoolean(KEY_SHOW_BACK, false);
        }
        commonTitle.showLeftImg(isShowBack);
        commonTitle.showStatusBar(!isShowBack);
        rvList.setVertical(1);
        rvList.setScrollingEnabled(false);
        loadPagerManager = new LoadPagerManager();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.scrollToFirst();
                }
                doRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.currentPageUp();
                }
                doLoadMore();
            }
        });
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadMore();
        }
    }

    private void initAdapter() {
        mechanismIncomeRvAdapter = new MechanismIncomeRvAdapter(R.layout.item_mechanism_income, getContext());
        rvList.setAdapter(mechanismIncomeRvAdapter);
    }

    private void doRefresh() {
        getPresenter().queryMechanismOfflineDetails(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                true,
                getCurrentPage(),
                getPageSize()
        );
    }

    private Integer getPageSize() {
        if (loadPagerManager != null) {
            int pageSize = loadPagerManager.getPageSize();
            return pageSize;
        }
        return 10;
    }

    private Integer getCurrentPage() {
        if (loadPagerManager != null) {
            int currentPage = loadPagerManager.getCurrentPage();
            return currentPage;
        }
        return 1;
    }

    private void doLoadMore() {
        getPresenter().queryMechanismOfflineDetails(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id(),
                "mechanism_offline",
                true,
                getCurrentPage(),
                getPageSize()
        );
    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().queryMechanismOfflineDetailsCount(
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id()
        );
        doRefresh();
    }

    @Override
    public void onMechanismIncomeStaticticsInfo(MechanismIncomeStatisticsEntity mechanismIncomeStatisticsEntity) {
        try {
            String earnTotal = mechanismIncomeStatisticsEntity.getEarnTotal();
            String earnDay = mechanismIncomeStatisticsEntity.getEarnDay();
            String settled = mechanismIncomeStatisticsEntity.getSettled();
            String noSettlement = mechanismIncomeStatisticsEntity.getNoSettlement();
            earnTotal = String.valueOf(NumberUtils.tranToTwoDecimal(earnTotal));
            earnDay = String.valueOf(NumberUtils.tranToTwoDecimal(earnDay));
            settled = TextUtils.isEmpty(settled) ? "0" : settled;
            noSettlement = TextUtils.isEmpty(noSettlement) ? "0" : noSettlement;
            tvTotalIncome.setText(earnTotal);
            tvTodayIncome.setText(earnDay);
            tvSettled.setText(String.valueOf(NumberUtils.tranToInt(settled)));
            tvUnSettled.setText(String.valueOf(NumberUtils.tranToInt(noSettlement)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMechanismIncomeList(List<MechanismIncomeEntity> mechanismIncomeEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (mechanismIncomeRvAdapter != null) {
                mechanismIncomeRvAdapter.setData(mechanismIncomeEntities);
            }
        } else {
            if (mechanismIncomeRvAdapter != null) {
                mechanismIncomeRvAdapter.addData(mechanismIncomeEntities);
            }
        }

        if (mechanismIncomeEntities == null || mechanismIncomeEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    private void finishLoadMoreWithNoMoreData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    private void finishRefreshWithNoMoreData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefreshWithNoMoreData();
        }
    }

    @Override
    public void retry() {
        super.retry();
        initData();
    }
}
