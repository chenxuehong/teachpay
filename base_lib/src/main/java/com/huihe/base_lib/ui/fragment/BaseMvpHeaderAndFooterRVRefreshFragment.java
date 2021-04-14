package com.huihe.base_lib.ui.fragment;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;


import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.widget.DragFloatActionButton;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


public abstract class BaseMvpHeaderAndFooterRVRefreshFragment<P extends BasePresenter> extends BaseMvpFragment<P> {

    SmartRefreshLayout mSmartRefreshLayout;
    RecyclerViewFixed mRecyclerViewFixed;
    private RefreshLayout mRefreshLayou;
    public ExpandRVAdapter adapter;
    private LoadPagerManager loadPagerManager;
    private DragFloatActionButton dragFloatActionButton;
    private FrameLayout flBackTop;
    private CommonTitle commonTitle;
    private FrameLayout flTopBg;

    public SmartRefreshLayout getSmartRefreshLayout() {
        return mSmartRefreshLayout;
    }

    public RecyclerViewFixed getRecyclerViewFixed() {
        return mRecyclerViewFixed;
    }

    public LoadPagerManager getLoadPagerManager() {
        if (loadPagerManager == null) {
            loadPagerManager = new LoadPagerManager();
        }
        return loadPagerManager;
    }

    @Override
    protected void initView() {
        super.initView();
        loadPagerManager = new LoadPagerManager();
        commonTitle = mContainer.findViewById(R.id.fm_layout_refresh_titleBar);
        flTopBg = mContainer.findViewById(R.id.fm_layout_refresh_fl_top_bg);
        mSmartRefreshLayout = mContainer.findViewById(R.id.fm_layout_refresh_refresh);
        mRecyclerViewFixed = mContainer.findViewById(R.id.fm_layout_refresh_rv);
        dragFloatActionButton = mContainer.findViewById(R.id.fm_layout_refresh_dragFloatActionBtn);
        flBackTop = mContainer.findViewById(R.id.fm_layout_refresh_fl_back_top);
        mSmartRefreshLayout.setEnableLoadMore(enableLoadMore());
        mSmartRefreshLayout.setEnableRefresh(enableRefresh());
        mSmartRefreshLayout.setEnableAutoLoadMore(enableAutoRefresh());
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
                if (loadPagerManager != null)
                    loadPagerManager.scrollToFirst();
                mRefreshLayou = refreshLayout;
                autoRefresh();
            }
        });
        flBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecyclerViewFixed() != null)
                    getRecyclerViewFixed().smoothScrollToPosition(0);
            }
        });
        int spanCount = getSpanCount();
        vertical(spanCount);
        addItemDecoration(spanCount, getSpace(), getReduceColumn());
        setDragFloatActionButton(dragFloatActionButton);
        boolean isBackTop = isEnableBackTop();
        if (isBackTop) {
            mRecyclerViewFixed.setOnScrollToTopListener(new RecyclerViewFixed.OnScrollToTopListener() {
                @Override
                public void show() {
                    flBackTop.setVisibility(View.VISIBLE);
                }

                @Override
                public void hide() {
                    flBackTop.setVisibility(View.GONE);
                }
            });
        }
        mSmartRefreshLayout.setBackgroundColor(getBackgroundColor());
        initTitle(commonTitle);
        if (getMarginTop() != 1)
            flTopBg.getLayoutParams().height = DensityUtils.getStatusHeight() + getMarginTop();
    }

    protected int getMarginTop() {
        return 1;
    }

    protected int getBackgroundColor() {
        return getResources().getColor(R.color.transparent);
    }

    protected void initTitle(CommonTitle commonTitle) {

    }

    protected boolean enableAutoRefresh() {
        return true;
    }

    protected boolean isEnableBackTop() {
        return false;
    }

    protected int getReduceColumn() {
        return 0;
    }

    protected void setDragFloatActionButton(DragFloatActionButton dragFloatActionButton) {

    }

    protected boolean enableRefresh() {
        return true;
    }

    protected boolean enableLoadMore() {
        return true;
    }

    protected abstract int getSpace();

    protected abstract int getSpanCount();

    public void addItemDecoration(int column, int space, int reduceColumn) {
        mRecyclerViewFixed.addGridViewItemDecoration(column, space, reduceColumn);
    }

    public void doLoadMore() {

    }

    public void autoRefresh() {

    }

    public void vertical(int spanCount) {
        if (mRecyclerViewFixed != null) {
            if (spanCount > 1) {
                mRecyclerViewFixed.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
            } else {
                mRecyclerViewFixed.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }

    }

    public void initAdapter() {
        if (mRecyclerViewFixed != null) {
            adapter = createAdapter();
            mRecyclerViewFixed.setAdapter(adapter);
            addHeaderAndFooter(mRecyclerViewFixed, adapter);
        }
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
        mSmartRefreshLayout.finishRefreshWithNoMoreData();
    }

    protected abstract void addHeaderAndFooter(RecyclerViewFixed mRecyclerViewFixed, ExpandRVAdapter adapter);

    protected abstract ExpandRVAdapter createAdapter();

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
