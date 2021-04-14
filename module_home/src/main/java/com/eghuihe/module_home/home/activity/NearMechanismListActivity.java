package com.eghuihe.module_home.home.activity;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.home.adapter.NearMechanismListRvAdapter;
import com.eghuihe.module_home.home.mvp.NearMechanismListContract;
import com.eghuihe.module_home.home.mvp.NearMechanismListPresenter;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpRvRefreshTitleActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class NearMechanismListActivity extends BaseMvpRvRefreshTitleActivity<EmptyRVAdapter, NearMechanismListPresenter>
        implements NearMechanismListContract.View {

    private LocationService locationService;
    private boolean isLoadBaiduData;
    private int realCurrentPage;

    @Override
    protected void initTitle(CommonTitle commonTitle) {
        commonTitle.setTitle("附近机构");
    }

    @Override
    protected NearMechanismListPresenter createPresenter() {
        return new NearMechanismListPresenter();
    }

    @Override
    protected void initData() {
        locationService = new LocationService(getContext());
        locationService.initBaiduMap();
        isLoadBaiduData = false;
        triggerRefreshData();
    }

    @Override
    protected void doRefresh() {
        isLoadBaiduData = false;
        getPresenter().queryMechanismByType(
                getPageSize(),
                getCurrentPage(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                null
        );
    }

    @Override
    protected void doLoadMore() {
        if (isLoadBaiduData){
            // 请求百度数据
            locationService.initPoiSearch(
                    LoginHelper.getCity(),
                    getCurrentPage() - realCurrentPage - 1,
                    getPageSize(),
                    "教学机构",
                    new LocationService.OnPoiSearchResultListener() {
                        @Override
                        public void OnPoiSearchResult(List<MasterMechanismModel.MasterMechanismEntity> mMechanismDetailInfoEntities) {
                            if (adapter != null) {
                                if (getCurrentPage() == 1) {
                                    List<MasterMechanismModel.MasterMechanismEntity> newMechanismDetailInfoEntities = new ArrayList<>();
                                    newMechanismDetailInfoEntities.addAll(adapter.getData());
                                    newMechanismDetailInfoEntities.addAll(mMechanismDetailInfoEntities);
                                    adapter.setData(newMechanismDetailInfoEntities);
                                } else {
                                    adapter.addData(mMechanismDetailInfoEntities);
                                }
                                if (mMechanismDetailInfoEntities == null || mMechanismDetailInfoEntities.size() < getPageSize()) {
                                    if (getCurrentPage() == 1) {
                                        finishRefreshWithNoMoreData();
                                    } else {
                                        finishLoadMoreWithNoMoreData();
                                    }
                                }
                            }
                            closeLoading();
                        }

                        @Override
                        public void OnPoiDetailSearchResult(String detailUrl) {

                        }
                    });
        }else {
            getPresenter().queryMechanismByType(
                    getPageSize(),
                    getCurrentPage(),
                    LoginHelper.getLatitude(),
                    LoginHelper.getLongitude(),
                    null
            );
        }
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
        return new NearMechanismListRvAdapter(R.layout.item_near_mechanism_course,this, locationService);
    }

    @Override
    public void showMechanismList(List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities) {

        if (getCurrentPage() == 1) {
            initAdapter();
            if (masterMechanismEntities != null && masterMechanismEntities.size() > 0) {
                if (adapter != null) {
                    adapter.setData(masterMechanismEntities);
                }
            }

        } else {
            if (masterMechanismEntities != null && masterMechanismEntities.size() > 0) {
                if (adapter != null) {
                    adapter.addData(masterMechanismEntities);
                }
            }
        }
        if (masterMechanismEntities == null || masterMechanismEntities.size() < getPageSize()) {
            isLoadBaiduData = true;
            doLoadMore();
            if (getCurrentPage() == 1) {
                finishRefreshWithNoMoreData();
            } else {
                finishLoadMoreWithNoMoreData();
            }
        } else {
            realCurrentPage = getCurrentPage();
        }

    }


}
