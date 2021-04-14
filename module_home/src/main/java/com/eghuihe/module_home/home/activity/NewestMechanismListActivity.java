package com.eghuihe.module_home.home.activity;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.home.adapter.NearMechanismListRvAdapter;
import com.eghuihe.module_home.home.adapter.NewestMechanismListRvAdapter;
import com.eghuihe.module_home.home.mvp.NearMechanismListContract;
import com.eghuihe.module_home.home.mvp.NearMechanismListPresenter;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class NewestMechanismListActivity extends BaseMvpRvRefreshTitleActivity<EmptyRVAdapter, NearMechanismListPresenter>
        implements NearMechanismListContract.View {

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("最新入驻机构");
    }

    @Override
    protected NearMechanismListPresenter createPresenter() {
        return new NearMechanismListPresenter();
    }

    @Override
    protected void initData() {
        triggerRefreshData();
    }

    @Override
    protected void doRefresh() {

        getPresenter().queryMechanismByType(
                getPageSize(),
                getCurrentPage(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "id"
        );
    }

    @Override
    protected void doLoadMore() {

        getPresenter().queryMechanismByType(
                getPageSize(),
                getCurrentPage(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "id"
        );
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected EmptyRVAdapter getAdapter() {
        return new NewestMechanismListRvAdapter(R.layout.item_newest_mechanism_course,this);
    }

    @Override
    public void showMechanismList(List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (adapter != null) {
                adapter.setData(masterMechanismEntities);
            }
        } else {
            if (adapter != null) {
                adapter.addData(masterMechanismEntities);
            }
        }
        if (masterMechanismEntities == null || masterMechanismEntities.size() < getPageSize()) {
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        }

    }


}
