package com.eghuihe.module_order.ui.student;

import android.os.Bundle;

import com.eghuihe.module_order.R;
import com.eghuihe.module_order.ui.adapter.StudentOrderRvAdapter;
import com.eghuihe.module_order.ui.adapter.StudentOrderRvAdapter2;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.holder.ViewHolder;

public abstract class BaseStudentOrderListFragment2<P extends BasePresenter> extends BaseMvpRVRefreshFragment<StudentOrderRvAdapter2, P> implements StudentOrderRvAdapter2.OnChildListener {

    private String status;

    public String getStatus() {
        return status;
    }

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected StudentOrderRvAdapter2 createAdapter() {
        return new StudentOrderRvAdapter2(R.layout.item_student_order2, getContext(),this);
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle arguments = getArguments();
        if (arguments!=null){
            status = arguments.getString(ArgumentsConfig.KEY_STATUS);
        }
    }

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
