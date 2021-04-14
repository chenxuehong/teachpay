package com.eghuihe.module_home.home.fragment;

import android.os.Bundle;

import com.eghuihe.module_home.R;
import com.eghuihe.module_home.home.adapter.NearMechanismListRvAdapter;
import com.eghuihe.module_home.home.mvp.MechanismQueryListContract;
import com.eghuihe.module_home.home.mvp.MechanismQueryListPresenter;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.fragment.BaseMvpRVRefreshFragment;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class MechanismListByCategoriesFragment extends BaseMvpRVRefreshFragment<NearMechanismListRvAdapter, MechanismQueryListPresenter>
        implements MechanismQueryListContract.View {
    public static final String KEY_MECHANISM_NAME = "mechanism_name";
    public static final String KEY_CATEGORIES = "categories";
    public static final String KEY_CATEGORIES_CHILD = "categories_child";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_SORTNAME = "sortName";
    private String mechanism_name;
    private String categories;
    private String categories_child;
    private String latitude;
    private String longitude;
    private String sortName;
    private String[] categoriesChildArr;
    private LocationService locationService;
    private int realCurrentPage;

    @Override
    protected int getSpace() {
        return 0;
    }

    @Override
    protected int getSpanCount() {
        return 1;
    }

    @Override
    protected NearMechanismListRvAdapter createAdapter() {
        return new NearMechanismListRvAdapter(R.layout.item_near_mechanism_course, getContext(), locationService);
    }

    @Override
    protected MechanismQueryListPresenter createPresenter() {
        return new MechanismQueryListPresenter();
    }

    private boolean isLoadBaiduData;

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
        } else {
            realCurrentPage = getCurrentPage();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            mechanism_name = arguments.getString(KEY_MECHANISM_NAME);
            categories = arguments.getString(KEY_CATEGORIES);
            categories_child = arguments.getString(KEY_CATEGORIES_CHILD);
            latitude = arguments.getString(KEY_LATITUDE);
            longitude = arguments.getString(KEY_LONGITUDE);
            sortName = arguments.getString(KEY_SORTNAME);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        categoriesChildArr = DataLoader.getInstance().getCategoriesChildArr(categories);
        locationService = new LocationService(getContext());
        locationService.initBaiduMap();
        isLoadBaiduData = false;
        triggerAutoRefresh();
    }

    @Override
    public void doRefresh() {
        isLoadBaiduData = false;
        getPresenter().queryMechanismByEs(
                mechanism_name,
                getCurrentPage(),
                getPageSize(),
                latitude,
                longitude,
                sortName,
                categoriesChildArr != null && categoriesChildArr.length > 0 ? null : categories,
                categories_child,
                "teach_paypal"
        );
    }

    @Override
    public void doLoadMore() {
        if (isLoadBaiduData) {
            // 请求百度数据
            locationService.initPoiSearch(
                    LoginHelper.getCity(),
                    getCurrentPage() - realCurrentPage - 1,
                    getPageSize(),
                    categories_child,
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
        } else {
            // 请求后台数据
            getPresenter().queryMechanismByEs(
                    mechanism_name,
                    getCurrentPage(),
                    getPageSize(),
                    latitude,
                    longitude,
                    sortName,
                    categoriesChildArr != null && categoriesChildArr.length > 0 ? null : categories,
                    categories_child,
                    "teach_paypal"
            );
        }
    }
}
