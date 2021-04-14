package com.huihe.base_lib.ui.activity;

import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public abstract class BaseMvpRvRefreshTitleActivity<Adapter extends RecyclerView.Adapter, P extends BasePresenter> extends BaseMvpActivity<P> {

    private CommonTitle commonTitle;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerViewFixed recyclerViewFixed;
    private RefreshLayout mRefreshLayout;
    public Adapter adapter;
    private LoadPagerManager loadPagerManager;

    public SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;
    }

    public LoadPagerManager getLoadPagerManager() {
        return loadPagerManager;
    }

    private FrameLayout flBottom;

    public FrameLayout getFlBottom() {
        return flBottom;
    }

    @Override
    protected boolean useButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.acivity_base_refresh;
    }

    @Override
    protected void initView() {
        super.initView();
        commonTitle = findViewById(R.id.activity_base_customer_title);
        smartRefreshLayout = findViewById(R.id.activity_base_smartRefreshLayout);
        recyclerViewFixed = findViewById(R.id.activity_base_rv);
        flBottom = findViewById(R.id.activity_base_refresh_fl_bottom);
        initTitle(commonTitle);
        initListener();
    }

    protected abstract void initTitle(CommonTitle commonTitle);

    private void initListener() {
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
        int backgroundColor = getBackgroundColor();
//        smartRefreshLayout.setBackgroundColor(backgroundColor);
    }

    protected int getBackgroundColor() {
        return getResources().getColor(R.color.white);
    }

    public int getCurrentPage() {
        if (loadPagerManager != null) {
            return loadPagerManager.getCurrentPage();
        }
        return 1;
    }

    public int getPageSize() {
        if (loadPagerManager != null) {
            return loadPagerManager.getPageSize();
        }
        return 10;
    }

    protected abstract void doRefresh();

    protected abstract void doLoadMore();

    private void initRecyclerView() {
        int spanCount = getSpanCount();
        vertical(spanCount);
        addItemDecoration(spanCount, getSpace());
    }

    protected abstract int getSpanCount();

    private void vertical(int spanCount) {
        if (recyclerViewFixed != null) {
            if (spanCount > 1) {
                recyclerViewFixed.setLayoutManager(new GridLayoutManager(this, spanCount));
            } else {
                recyclerViewFixed.setLayoutManager(new LinearLayoutManager(this));
            }
        }
    }

    protected abstract int getSpace();

    public void addItemDecoration(int column, int space) {
        if (recyclerViewFixed != null) {
            recyclerViewFixed.addGridViewItemDecoration(column, space, 0);
        }
    }

    public void initAdapter() {
        initRecyclerView();
        if (recyclerViewFixed != null) {
            adapter = getAdapter();
            recyclerViewFixed.setAdapter(adapter);
        }
    }

    protected abstract Adapter getAdapter();

    public void triggerRefreshData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.autoRefresh();
        }
    }

    public void finishRefreshWithNoMoreData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefreshWithNoMoreData();
        }
    }

    public void finishLoadMoreWithNoMoreData() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
        }
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishLoadMore();
            smartRefreshLayout.finishRefresh();
        }
    }
}
