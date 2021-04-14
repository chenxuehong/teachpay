package com.eghuihe.module_order.ui.student;

import com.eghuihe.module_order.R;
import com.eghuihe.module_order.ui.adapter.StudentOrderRvAdapter;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;

public abstract class BaseStudentOrderListFragment<P extends BasePresenter> extends BaseMvpRVRefreshFragment<StudentOrderRvAdapter, P> {

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected StudentOrderRvAdapter createAdapter() {
        return new StudentOrderRvAdapter(R.layout.item_student_order, getContext(), isIntent());
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
