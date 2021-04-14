package com.eghuihe.module_order.ui.mechanism;

import com.eghuihe.module_order.R;
import com.eghuihe.module_order.ui.adapter.MechanismOrderRvAdapter;
import com.eghuihe.module_order.ui.adapter.StudentOrderRvAdapter;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;

public abstract class BaseMechanismOrderListFragment<P extends BasePresenter> extends BaseMvpRVRefreshFragment<MechanismOrderRvAdapter, P> {

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected MechanismOrderRvAdapter createAdapter() {
        return new MechanismOrderRvAdapter(R.layout.item_mechanism_order, getContext(), isIntent());
    }

    protected abstract boolean isIntent();

    @Override
    protected void initData() {
        super.initData();
        triggerAutoRefresh();
    }

    @Override
    public void doRefresh() {
        super.doRefresh();
        loadData();
    }

    @Override
    public void doLoadMore() {
        super.doLoadMore();
        loadData();
    }

    protected abstract void loadData();

}
