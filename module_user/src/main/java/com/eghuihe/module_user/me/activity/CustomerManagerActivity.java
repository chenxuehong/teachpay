package com.eghuihe.module_user.me.activity;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.me.adapter.CustomerManageRvAdapter;
import com.eghuihe.module_user.me.mvp.CustomerManagerContract;
import com.eghuihe.module_user.me.mvp.CustomerManagerPresenter;
import com.huihe.base_lib.model.MechanismUserEntity;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagerActivity extends BaseMvpRvRefreshTitleActivity<EmptyRVAdapter, CustomerManagerPresenter>
        implements CustomerManagerContract.View, CustomerManageRvAdapter.OnListener {

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("客户管理");
    }

    @Override
    protected void doRefresh() {

        getPresenter().getMechanismUserList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id()
        );
    }

    @Override
    protected void doLoadMore() {
        getPresenter().getMechanismUserList(
                getCurrentPage(),
                getPageSize(),
                LoginHelper.getLoginInfo().getUserInfoEntity().getMechanism_id()
        );
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected int getSpace() {
        return DensityUtils.dp2px(this, 15);
    }

    @Override
    protected EmptyRVAdapter getAdapter() {
        return new CustomerManageRvAdapter(R.layout.item_customer_manager, this,this);
    }

    @Override
    protected void initData() {
        doRefresh();
    }

    @Override
    protected CustomerManagerPresenter createPresenter() {
        return new CustomerManagerPresenter();
    }


    @Override
    public void onMechanismUserList(List<MechanismUserEntity> mechanismUserEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(mechanismUserEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(mechanismUserEntities);
            }
        }
        if (mechanismUserEntities == null || mechanismUserEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onIsNewCustom(boolean isNewCustom) {
        doRefresh();
    }

    @Override
    public void setNewCustomer(ViewHolder viewHolder, MechanismUserEntity mechanismUserEntity) {
        getPresenter().mechanismUserStatus(
                mechanismUserEntity.getId(),
                true
        );
    }

    @Override
    public void setOldCustomer(ViewHolder viewHolder, MechanismUserEntity mechanismUserEntity) {
        getPresenter().mechanismUserStatus(
                mechanismUserEntity.getId(),
                false
        );
    }
}
