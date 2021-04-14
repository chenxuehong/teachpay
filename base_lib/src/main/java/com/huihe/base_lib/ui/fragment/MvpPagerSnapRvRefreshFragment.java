package com.huihe.base_lib.ui.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.widget.recyclerview.FadingEdgeRecyclerView;
import com.huihe.base_lib.ui.widget.recyclerview.OnPageChangeListener;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.recyclerview.layoutmamanger.PagerLayoutManager;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

public abstract class MvpPagerSnapRvRefreshFragment<Adapter extends RecyclerView.Adapter, P extends BasePresenter> extends BaseMvpFragment<P> implements OnPageChangeListener {
    SmartRefreshLayout mSmartRefreshLayout;
    RecyclerViewFixed mRecyclerViewFixed;
    private PagerLayoutManager pagerLayoutManager;
    private FadingEdgeRecyclerView mFERecyclerViewFixed;

    public SmartRefreshLayout getSmartRefreshLayout() {
        return mSmartRefreshLayout;
    }

    public RecyclerViewFixed getRecyclerViewFixed() {
        return mFERecyclerViewFixed;
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
        mSmartRefreshLayout = mContainer.findViewById(R.id.fm_layout_refresh_refresh);
        mRecyclerViewFixed = mContainer.findViewById(R.id.fm_layout_refresh_rv);
        mFERecyclerViewFixed = mContainer.findViewById(R.id.fm_layout_FadingEdgeRecyclerView_rv);
        mRecyclerViewFixed.setVertical(View.GONE);
        mFERecyclerViewFixed.setVertical(View.GONE);
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
        mRecyclerViewFixed.setScrollingEnabled(false);

        if (mRecyclerViewFixed != null) {
            pagerLayoutManager = new PagerLayoutManager(getContext());
            mRecyclerViewFixed.setLayoutManager(pagerLayoutManager);
            pagerLayoutManager.setOnPageChangeListener(this);
        }
    }

    protected int getBackgroundColor() {
        return getResources().getColor(R.color.white);
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

    public void reset(){
        if (pagerLayoutManager!=null){
            pagerLayoutManager.reset();
        }
    }
}
