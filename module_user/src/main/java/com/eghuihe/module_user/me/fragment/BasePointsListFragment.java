package com.eghuihe.module_user.me.fragment;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.MyPointsListRvAdapter;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;

public abstract class BasePointsListFragment<P extends BasePresenter> extends BaseMvpRVRefreshFragment<EmptyRVAdapter, P> {
    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected EmptyRVAdapter createAdapter() {
        return new MyPointsListRvAdapter(R.layout.item_my_points,getContext());
    }

    @Override
    public void doLoadMore() {
        loadData();
    }

    @Override
    public void doRefresh() {
        loadData();
    }

    protected abstract void loadData();
}
