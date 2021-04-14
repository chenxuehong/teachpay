package com.eghuihe.module_home.home.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_home.R;
import com.eghuihe.module_home.R2;
import com.eghuihe.module_home.TeachingPaySearchView;
import com.eghuihe.module_home.home.activity.ActivityDetailActivity;
import com.eghuihe.module_home.home.activity.MechanismListByTypeActivity;
import com.eghuihe.module_home.home.activity.MechanismQueryListActivity;
import com.eghuihe.module_home.home.activity.NearMechanismListActivity;
import com.eghuihe.module_home.home.activity.NewActivityDetailActivity;
import com.eghuihe.module_home.home.activity.NewestMechanismListActivity;
import com.eghuihe.module_home.home.activity.SklbActivityDetailActivity;
import com.eghuihe.module_home.home.activity.TeachPayCourseListActivity;
import com.eghuihe.module_home.home.adapter.LatestMechanismListAdapter;
import com.eghuihe.module_home.home.adapter.SortMechanismRvAdapter;
import com.eghuihe.module_home.home.adapter.TeachingPayHomeTabListAdapter;
import com.eghuihe.module_home.home.mvp.TeachingPayHomeContract;
import com.eghuihe.module_home.home.mvp.TeachingPayHomePresenter;
import com.eghuihe.module_home.utils.MyDialogUtils;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.constants.RequestCodeConfig;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.ItemViewBean;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.activity.LocationActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.adapter.ExpandRVAdapter;
import com.huihe.base_lib.ui.adapter.TeachPayCourseListRvAdapter;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.ui.widget.TabTitleRecyclerView;
import com.huihe.base_lib.ui.widget.banner.OnItemClickListener;
import com.huihe.base_lib.ui.widget.banner.XBanner;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.fitViewPager.transformers.ScalePageTransformer;
import com.huihe.base_lib.ui.widget.recyclerview.HorizontalRecyclerViewFix;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoadPagerManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.HOME_FRAGMENT)
public class TeachingPayHomeFragment extends BaseMvpFragment<TeachingPayHomePresenter>
        implements TeachingPayHomeContract.View, TeachingPaySearchView.OnListener, OnItemClickListener {

    @BindView(R2.id.teaching_pay_home_teachingPaySearchView)
    TeachingPaySearchView searchView;
    @BindView(R2.id.teaching_pay_home_rv)
    RecyclerViewFixed recyclerViewFixed;
    @BindView(R2.id.teaching_pay_home_SmartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private SortMechanismRvAdapter sortMechanismRvAdapter;
    private LoadPagerManager loadPagerManager;
    private LatestMechanismListAdapter mechanismAdapter;
    private TeachPayCourseListRvAdapter hotMechanismAdapter;
    private XBanner xBanner;
    private MPermission mPermission;
    private BaseDialog locationTipDialog;
    private boolean hasLocation;
    private LocationService locationService;
    private BaseDialog couponDialog;
    private ExpandRVAdapter expandRVAdapter;
    private BaseDialog mLocationServiceDailog;

    @Override
    protected TeachingPayHomePresenter createPresenter() {
        return new TeachingPayHomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teaching_pay_home;
    }

    private RefreshLayout mRefreshLayout;
    private final static String TYPE_SORT_LATEST = "sort_latest"; // 最新排序
    private String type_sort = "TYPE_SORT_JULI";

    String[] mLocaPermissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void initView() {
        super.initView();
        type_sort = TYPE_SORT_LATEST;
        searchView.setLocationContent(getResources().getString(R.string.Positioning));
        searchView.setLocationCity(getResources().getString(R.string.Positioning));
        searchView.setOnListener(this);
        loadPagerManager = new LoadPagerManager();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout = refreshLayout;
                if (loadPagerManager != null) {
                    loadPagerManager.scrollToFirst();
                }
                doRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(
                new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                        mRefreshLayout = refreshLayout;
                        if (loadPagerManager != null) {
                            loadPagerManager.currentPageUp();
                        }
                        doLoadMore();
                    }
                });
        recyclerViewFixed.setOnTitleBarListener(
                new RecyclerViewFixed.OnTitleBarListener() {
                    @Override
                    public void showToolBar() {
                        searchView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void hideToolBar() {
                        searchView.setVisibility(View.GONE);
                    }

                    @Override
                    public void scrollChangeStatus(float alpha) {
                        if (alpha > 0.5) {
                            searchView.setAlpha(alpha);
                        } else {
                            searchView.setAlpha(1 - alpha);
                        }
                        LogUtils.i("scrollChangeStatus", "alpha = " + alpha);
                        if (alpha > 0.5) {
                            // 显示白色文案
                            searchView.showLine(false);
                            searchView.setBackgroundColor(getResources().getColor(R.color.transparent));
                            searchView.setLocationContentColor(getResources().getColor(R.color.white));
                            searchView.setLocationCityColor(getResources().getColor(R.color.white));
                        } else {
                            // 显示黑色文案
                            searchView.showLine(true);
                            searchView.setBackgroundColor(getResources().getColor(R.color.white));
                            searchView.setLocationContentColor(getResources().getColor(R.color.color_333333));
                            searchView.setLocationCityColor(getResources().getColor(R.color.color_333333));
                        }
                    }
                });
    }

    private void doRefresh() {
        loadNoLocationData();
        checkLocAndLoadLocData();
    }

    private void doLoadMore() {
        getPresenter().queryMechanismByType(
                loadPagerManager.getPageSize(),
                loadPagerManager.getCurrentPage(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "is_recommend",
                "sort",
                true
        );
    }

    @Override
    protected void initData() {
        super.initData();
        recyclerViewFixed.setVertical(1);
        sortMechanismRvAdapter = new SortMechanismRvAdapter(R.layout.item_teaching_pay_home_sort, getContext(), R.layout.layout_no_data);
        expandRVAdapter = new ExpandRVAdapter(sortMechanismRvAdapter);
        addBannerView(expandRVAdapter);
        addTabViewList(expandRVAdapter);
        addActivityView(expandRVAdapter);
        addMechanismHotCourseList(expandRVAdapter);
        addMechanismList(expandRVAdapter);
        addSortMechanismView(expandRVAdapter);
        loadNoLocationData();
        checkLocAndLoadLocData();
        try {
            recyclerViewFixed.setAdapter(expandRVAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkLocAndLoadLocData() {
        hasLocation = false;
        String addrStr = LoginHelper.getAddrStr();
        String city = LoginHelper.getCity();
        if (TextUtils.isEmpty(addrStr)) {
            searchView.setLocationContent(getResources().getString(R.string.Positioning));
            searchView.setLocationCity(getResources().getString(R.string.Positioning));
            mPermission = MPermission.with(this);
            if (mPermission.hasPermission(mLocaPermissions) != -1) {
                showLocationTipDialog();
            } else {
                startLoc();
            }
        } else {
            searchView.setLocationCity(city);
            searchView.setLocationContent(addrStr);
            loadLocationData();
        }
    }

    private void loadNoLocationData() {
        getPresenter().getBannerData(
                "teach_pay",
                "1");
    }

    private void startLoc() {
        LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            searchView.setLocationCity("开启定位");
            searchView.setLocationContent("开启定位");
            // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
            showLocationServiceDialog();
            return;
        }
        locationService = new LocationService(getContext());
        locationService.initBaiduMap();
        locationService.registerAndStartLocationListener(new LocationService.OnLocationListener() {
            @Override
            public void onLocation(double latitude, double longitude, float radius, float direction) {
                if (hasLocation) {
                    return;
                }
                String addrStr = LoginHelper.getAddrStr();
                String city = LoginHelper.getCity();
                LoginHelper.saveLocation(String.valueOf(latitude), String.valueOf(longitude), String.valueOf(radius), String.valueOf(direction));
                searchView.setLocationContent(addrStr);
                searchView.setLocationCity(city);
                loadLocationData();
                if (TextUtils.isEmpty(addrStr)) {
                    searchView.setLocationContent("定位失败");
                    searchView.setLocationCity("定位失败");
                }
                hasLocation = true;
            }
        });
        locationService.start();
    }

    private void loadLocationData() {
        getPresenter().queryActivityListPageByType(
                "2",
                1,
                10,
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "experience_specials"
        );
        getPresenter().queryMechanismByType(
                10,
                1,
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "id",
                null,
                false
        );
        getPresenter().queryMechanismByType(
                loadPagerManager.getPageSize(),
                loadPagerManager.getCurrentPage(),
                LoginHelper.getLatitude(),
                LoginHelper.getLongitude(),
                "is_recommend",
                "sort",
                false
        );
    }

    private void showLocationTipDialog() {
        locationTipDialog = MyDialogUtils.showLocationTipDialog(
                getContext(),
                new MyDialogUtils.OnListener() {
                    @Override
                    public void onCancel() {
                        searchView.setLocationContent("未定位");
                        searchView.setLocationCity("未定位");
                        if (smartRefreshLayout != null)
                            smartRefreshLayout.autoRefresh();
                    }

                    @Override
                    public void onSure() {
                        if (mPermission != null)
                            mPermission.setPermission(mLocaPermissions).requestPermission(new MPermission.OnCallBack() {
                                @Override
                                public void valdateSuccess() {
                                    startLoc();
                                }

                                @Override
                                public void valdateFail() {
                                    if (smartRefreshLayout != null)
                                        smartRefreshLayout.autoRefresh();
                                }
                            });
                    }
                });

    }

    /**
     * @param expandRVAdapter
     * @desc 教付保首页广告
     */
    private void addBannerView(ExpandRVAdapter expandRVAdapter) {
        View bannerView = View.inflate(getContext(), R.layout.layout_teaching_pay_home_banner, null);
        xBanner = bannerView.findViewById(R.id.layout_teaching_pay_home_banner);
        expandRVAdapter.addHanderView(bannerView);
    }

    /**
     * @param expandRVAdapter
     * @desc 教付保tab
     */
    private void addTabViewList(ExpandRVAdapter expandRVAdapter) {
        // data
        List<ItemViewBean> itemViewBeans = new ArrayList<>();
        itemViewBeans.add(new ItemViewBean(R.mipmap.home_near,
                "附近"));
        itemViewBeans.add(new ItemViewBean(R.mipmap.arts,
                "才艺"));
        itemViewBeans.add(new ItemViewBean(R.mipmap.sport,
                "体育运动"));
        itemViewBeans.add(new ItemViewBean(R.mipmap.subject,
                "教辅学科"));
        itemViewBeans.add(new ItemViewBean(R.mipmap.puzzle,
                "益智"));
        itemViewBeans.add(new ItemViewBean(R.mipmap.home_other,
                "其他"));
        // view
        View tabView = View.inflate(getContext(), R.layout.layout_teaching_pay_home_tab_list, null);
        HorizontalRecyclerViewFix rvTabList = tabView.findViewById(R.id.layout_teaching_pay_home_tab_list_rv);
        rvTabList.setScrollingEnabled(false);
        rvTabList.setHorizontal();
        TeachingPayHomeTabListAdapter teachingPayHomeTabListAdapter = new TeachingPayHomeTabListAdapter(
                R.layout.item_teaching_pay_home_tab,
                getContext(),
                itemViewBeans);
        teachingPayHomeTabListAdapter.setOnItemClickListener(new CommonRVAdapter.OnItemClickListener<ItemViewBean>() {

            @Override
            public void onItemClicked(View v, ItemViewBean itemViewBean, int i) {
                if (itemViewBean.iconResId == R.mipmap.home_near) {
                    // 附近机构
                    startActivity(NearMechanismListActivity.class);
                } else if (itemViewBean.iconResId == R.mipmap.arts) {
                    Intent intent = new Intent(getContext(), MechanismListByTypeActivity.class);
                    intent.putExtra(MechanismListByTypeActivity.KEY_CATEGORIES, "才艺");
                    startActivity(intent);
                } else if (itemViewBean.iconResId == R.mipmap.sport) {
                    Intent intent = new Intent(getContext(), MechanismListByTypeActivity.class);
                    intent.putExtra(MechanismListByTypeActivity.KEY_CATEGORIES, "体育运动");
                    startActivity(intent);
                } else if (itemViewBean.iconResId == R.mipmap.subject) {
                    Intent intent = new Intent(getContext(), MechanismListByTypeActivity.class);
                    intent.putExtra(MechanismListByTypeActivity.KEY_CATEGORIES, "教辅学科");
                    startActivity(intent);
                } else if (itemViewBean.iconResId == R.mipmap.puzzle) {
                    Intent intent = new Intent(getContext(), MechanismListByTypeActivity.class);
                    intent.putExtra(MechanismListByTypeActivity.KEY_CATEGORIES, "益智");
                    startActivity(intent);
                } else if (itemViewBean.iconResId == R.mipmap.home_other) {
                    Intent intent = new Intent(getContext(), MechanismListByTypeActivity.class);
                    intent.putExtra(MechanismListByTypeActivity.KEY_CATEGORIES, "其他");
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClicked(View v, ItemViewBean itemViewBean, int i) {

            }
        });
        rvTabList.setAdapter(teachingPayHomeTabListAdapter);
        expandRVAdapter.addHanderView(tabView);
    }

    /**
     * @param expandRVAdapter
     * @desc 活动
     */
    private void addActivityView(ExpandRVAdapter expandRVAdapter) {
        View activityView = View.inflate(getContext(), R.layout.layout_teaching_pay_home_actvity, null);
        activityView.findViewById(R.id.layout_teaching_pay_home_actvity_iv_single_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), NewActivityDetailActivity.class);
                intent.putExtra(ArgumentsConfig.KEY_TYPE, "single_payment");
                startActivity(intent);
            }
        });
        activityView.findViewById(R.id.layout_teaching_pay_home_actvity_iv_grouping_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewActivityDetailActivity.class);
                intent.putExtra(ArgumentsConfig.KEY_TYPE, "grouping");
                startActivity(intent);
            }
        });
        activityView.findViewById(R.id.layout_teaching_pay_home_actvity_iv_cj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SklbActivityDetailActivity.class);
                intent.putExtra(ArgumentsConfig.KEY_TYPE, "experience_specials");
                startActivity(intent);
            }
        });
        expandRVAdapter.addHanderView(activityView);
    }

    /**
     * @param expandRVAdapter
     * @desc 机构热门课程
     */
    private void addMechanismHotCourseList(ExpandRVAdapter expandRVAdapter) {
        TabTitleRecyclerView tabTitleRecyclerView = new TabTitleRecyclerView(getContext());
        tabTitleRecyclerView.setOnMoreListener(new TabTitleRecyclerView.OnMoreListener() {
            @Override
            public void onMoreClicked(View view) {
                startActivity(TeachPayCourseListActivity.class);
            }
        });
        tabTitleRecyclerView.setTitle("天赋课程");
        tabTitleRecyclerView.setTitleColor(getResources().getColor(R.color.color_333333));
        tabTitleRecyclerView.setTitleTextSize(DensityUtils.sp2px(getContext(), 18));
        hotMechanismAdapter = new TeachPayCourseListRvAdapter(
                R.layout.item_query_mechanism_course,
                getContext(), R.layout.layout_no_data);
        hotMechanismAdapter.showLine(true);
        tabTitleRecyclerView.setAdapter(hotMechanismAdapter);
        tabTitleRecyclerView.setVertical(1);
        expandRVAdapter.addHanderView(tabTitleRecyclerView);
    }

    /**
     * @param expandRVAdapter
     * @desc 最新入驻机构
     */
    private void addMechanismList(ExpandRVAdapter expandRVAdapter) {
        TabTitleRecyclerView tabTitleRecyclerView = new TabTitleRecyclerView(getContext());
        tabTitleRecyclerView.setTitle("最新入驻机构");
        tabTitleRecyclerView.setTitleColor(getResources().getColor(R.color.color_333333));
        tabTitleRecyclerView.setTitleTextSize(DensityUtils.sp2px(getContext(), 18));
        tabTitleRecyclerView.setOnMoreListener(new TabTitleRecyclerView.OnMoreListener() {
            @Override
            public void onMoreClicked(View view) {
                Intent intent = new Intent(getContext(), NewestMechanismListActivity.class);
                startActivity(intent);
            }
        });
        mechanismAdapter = new LatestMechanismListAdapter(
                R.layout.item_latest_mechanism,
                getContext(),
                R.layout.layout_no_data);
        tabTitleRecyclerView.setAdapter(mechanismAdapter);
        tabTitleRecyclerView.setHorizontal();
        expandRVAdapter.addHanderView(tabTitleRecyclerView);
    }

    private void addSortMechanismView(ExpandRVAdapter expandRVAdapter) {
        View sortMechanismView = View.inflate(
                getContext(),
                R.layout.layout_teaching_pay_home_sort_mechanismtab,
                null);
        expandRVAdapter.addHanderView(sortMechanismView);
    }

    @Override
    public void onBack(View view) {
        getActivity().finish();
    }

    @Override
    public void onLocationClicked(View view) {
        // 定位
        if ("开启定位".equals(searchView.getLocationContent())) {
            showLocationServiceDialog();
        } else if (getResources().getString(R.string.Positioning).equals(searchView.getLocationContent())) {
            // 正在定位
        } else {
            // 定位成功
            Intent intent = new Intent(getContext(),LocationActivity.class);
            intent.putExtra(LocationActivity.KEY_IS_HOME,true);
            startActivityForResult(intent,RequestCodeConfig.RC_GET_ADDRESS);
        }
    }

    @Override
    public void onEnterQueryClicked(View view) {
        startActivity(MechanismQueryListActivity.class);
    }

    @Override
    public void showBannerList(List<BannerModel.BannerEntity> bannerEntities) {
        //刷新数据之后，需要重新设置是否支持自动轮播
        if (xBanner != null) {
            xBanner.setViewPagerClipChildren(true);
            xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                    //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                    BannerModel.BannerEntity entity = (BannerModel.BannerEntity) model;
                    ImageView imageView = (ImageView) view;
                    GlideTools.loadRoundedImage(getContext(), entity.getPic(), DensityUtils.dp2px(getContext(), 15), imageView);
                }
            });
            ;
            xBanner.setAutoPlayAble(bannerEntities.size() > 1);
            xBanner.setBannerData(bannerEntities);
            xBanner.setCustomPageTransformer(new ScalePageTransformer());
            xBanner.setOnItemClickListener(this);
        }
    }

    @Override
    public void showHotMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities) {
        if (masterSetPriceEntities != null && masterSetPriceEntities.size() > 3) {
            masterSetPriceEntities = masterSetPriceEntities.subList(0, 3);
        }
        if (hotMechanismAdapter != null)
            hotMechanismAdapter.setData(masterSetPriceEntities);
        Boolean is_collection = LoginHelper.getLoginInfo().getUserInfoEntity().getIs_collection();

        if (is_collection != null && !is_collection) {
            showGetCouponDialog();
        }
    }

    private void showGetCouponDialog() {
        try {
            if (couponDialog != null || couponDialog.isShowing()) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        couponDialog = MyDialogUtils.showCouponDialog(getContext(), new MyDialogUtils.OnListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onSure() {
                startActivity(ActivityDetailActivity.class);
            }
        });
    }

    private void showLocationServiceDialog() {
        mLocationServiceDailog = DialogUtils.showLocationServiceDialog(
                getContext(), new DialogUtils.OnListener() {
                    @Override
                    public void okClicked() {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        //PermissionConstants.RC_GPS是请求码，可以在onActivityResult中根据该请求码判断用户是否已经在设置页面打开位置服务
                        startActivityForResult(intent, RequestCodeConfig.RC_GPS);
                    }

                    @Override
                    public void cancelClicked() {

                    }
                });
    }

    @Override
    public void showMechanismList(List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities, String type) {
        if (TextUtils.isEmpty(type)) {
            // 最新入驻机构
            if (mechanismAdapter != null)
                mechanismAdapter.setData(masterMechanismEntities);
        } else {
            // 排序机构
            if (loadPagerManager != null && sortMechanismRvAdapter != null) {
                int currentPage = loadPagerManager.getCurrentPage();
                if (currentPage == 1) {
                    sortMechanismRvAdapter.setData(masterMechanismEntities);
                } else {
                    sortMechanismRvAdapter.addData(masterMechanismEntities);
                    closeLoading();
                }
                if (expandRVAdapter != null) {
                    expandRVAdapter.notifyDataSetChanged();
                }
                if (mRefreshLayout != null) {
                    if (masterMechanismEntities == null || masterMechanismEntities.size() < loadPagerManager.getPageSize())
                        if (currentPage == 1) {
                            mRefreshLayout.finishRefreshWithNoMoreData();
                        } else {
                            mRefreshLayout.finishLoadMoreWithNoMoreData();
                        }
                }

            }
        }
    }

    @Override
    public void onMyMechanismInfo(List<MasterMechanismModel.MasterMechanismEntity> mechanismEntities) {
        if (mechanismEntities != null && mechanismEntities.size() > 0) {
            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = mechanismEntities.get(0);
            Integer status = masterMechanismEntity.getStatus();
            if (status == 2) {
                ToastUtils.showShortToast(getContext(), "机构已经入驻");
            } else {
                ToastUtils.showShortToast(getContext(), "机构正在审核");
            }
        } else {
            ActivityToActivity.toActivity(ARouterConfig.ME_SETTLEMECHANISM);
        }
    }

    @Override
    public void onUserCollectionSuccess() {
        ToastUtils.showShortToast(getContext(), "领取成功");
        EventBusUtils.sendEvent(new Event(EventAction.RESET_USERINFO));
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void onItemClick(XBanner banner, Object model, View view, int position) {
        EventBusUtils.sendEvent(new Event(EventAction.BANNER, (BannerModel.BannerEntity) model));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RequestCodeConfig.RC_GPS == requestCode) {
            startLoc();
        }else if (RequestCodeConfig.RC_GET_ADDRESS == requestCode){
            String addrStr = LoginHelper.getAddrStr();
            String city = LoginHelper.getCity();
            searchView.setLocationContent(addrStr);
            searchView.setLocationCity(city);
            loadLocationData();
        }
    }

    @Override
    public void onDestroyView() {
        try {
            if (locationTipDialog != null && locationTipDialog.isShowing()) {
                locationTipDialog.dismiss();
            }
            if (mLocationServiceDailog != null && mLocationServiceDailog.isShowing()) {
                mLocationServiceDailog.dismiss();
            }
            if (locationService != null) {
                locationService.stop();
                locationService.unRegisterLocationListener();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        if (locationService != null) {
            locationService.stop();
            locationService.unRegisterLocationListener();
        }
        try {
            if (couponDialog != null) {
                couponDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }
}
