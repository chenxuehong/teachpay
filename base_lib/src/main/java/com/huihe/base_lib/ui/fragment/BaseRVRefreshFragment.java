package com.huihe.base_lib.ui.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.DragFloatActionButton;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

public abstract class BaseRVRefreshFragment<Adapter extends RecyclerView.Adapter> extends BaseFragment {

    SmartRefreshLayout mSmartRefreshLayout;
    RecyclerViewFixed mRecyclerViewFixed;
    private DragFloatActionButton dragFloatActionButton;
    private CommonTitle commonTitle;

    public SmartRefreshLayout getSmartRefreshLayout() {
        return mSmartRefreshLayout;
    }

    public RecyclerViewFixed getRecyclerViewFixed() {
        return mRecyclerViewFixed;
    }

    private RefreshLayout mRefreshLayou;
    public Adapter adapter;
    private LoadPagerManager loadPagerManager;

    public LoadPagerManager getLoadPagerManager() {
        return loadPagerManager;
    }

    @Subscribe
    public int getCurrentPage() {
        if (loadPagerManager != null) {
            return loadPagerManager.getCurrentPage();
        }
        return 1;
    }

    @Subscribe
    public int getPageSize() {
        if (loadPagerManager != null) {
            return loadPagerManager.getPageSize();
        }
        return 10;
    }

    @Override
    protected void initView() {
        loadPagerManager = new LoadPagerManager();
        commonTitle = mContainer.findViewById(R.id.fm_layout_refresh_titleBar);
        mSmartRefreshLayout = mContainer.findViewById(R.id.fm_layout_refresh_refresh);
        mRecyclerViewFixed = mContainer.findViewById(R.id.fm_layout_refresh_rv);
        dragFloatActionButton = mContainer.findViewById(R.id.fm_layout_refresh_dragFloatActionBtn);
        mSmartRefreshLayout.setEnableRefresh(enableRefresh());
        mSmartRefreshLayout.setEnableLoadMore(enableLoadMore());
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayou = refreshLayout;
                loadPagerManager.currentPageUp();
                doLoadMore();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadPagerManager.scrollToFirst();
                mRefreshLayou = refreshLayout;
                doRefresh();
            }
        });
        mSmartRefreshLayout.setBackgroundColor(getBackgroundColor());
        int spanCount = getSpanCount();
        vertical(spanCount);
        addItemDecoration(spanCount, getSpace());
        setDragFloatActionButton(dragFloatActionButton);
        initTitle(commonTitle);
    }

    protected void initTitle(CommonTitle commonTitle) {

    }

    protected void setDragFloatActionButton(DragFloatActionButton dragFloatActionButton) {

    }

    protected int getBackgroundColor() {
        return getResources().getColor(R.color.white);
    }

    protected boolean enableLoadMore() {
        return true;
    }

    protected boolean enableRefresh() {
        return true;
    }

    protected abstract int getSpace();

    protected abstract int getSpanCount();

    public void vertical(int spanCount) {

        if (mRecyclerViewFixed != null) {
            if (spanCount > 1) {
                mRecyclerViewFixed.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
            } else {
                mRecyclerViewFixed.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }


    }

    public void addItemDecoration(int column, int space) {
        if (mRecyclerViewFixed != null)
            mRecyclerViewFixed.addGridViewItemDecoration(column, space, 0);
    }

    public void triggerAutoRefresh() {

        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.autoRefresh();
    }

    public void finishLoadMoreWithNoMoreData() {
        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    public void finishRefreshWithNoMoreData() {
        if (mSmartRefreshLayout != null)
            mSmartRefreshLayout.finishRefreshWithNoMoreData();
    }

    public void doLoadMore() {

    }

    public void doRefresh() {

    }

    public void initAdapter() {
        if (mRecyclerViewFixed != null) {
            adapter = createAdapter();
            mRecyclerViewFixed.setAdapter(adapter);
            initItemListener();
        }
    }

    protected void initItemListener() {

    }

    protected abstract Adapter createAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_layout_refresh;
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (mRefreshLayou != null)
            mRefreshLayou.finishRefresh();
        if (mRefreshLayou != null)
            mRefreshLayou.finishLoadMore();
    }

}
