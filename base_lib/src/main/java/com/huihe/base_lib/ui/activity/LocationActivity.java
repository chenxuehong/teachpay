package com.huihe.base_lib.ui.activity;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.huihe.base_lib.R;
import com.huihe.base_lib.constants.RequestCodeConfig;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.adapter.baidumap.PoiAdapter;
import com.huihe.base_lib.ui.adapter.baidumap.SuggestionAdapter;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.KeyBoardUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 百度地图定位
 */
public class LocationActivity extends BaseTitleActivity {

    public static final String KEY_IS_HOME = "is_home";
    private static final String TAG = LocationActivity.class.getSimpleName();
    public static final String KEY_DETAIL_ADDRESS = "keyDetailAddress";
    public static final String KEY_LATLNG = "latlng";
    private static final int REQUEST_CITY_CODE = 100;
    private LocationService locationService;
    private TextureMapView mMapView;
    private BaiduMap mBaiduMap;
    private BDLocationListener mLocationListener = new BDLocationListener();
    private boolean isFirstLoc = true;
    private GeoCoder geoCoder;
    private LinearLayout llSearch;
    private TextView tvCity;
    private EditText et_address;
    private RecyclerViewFixed rvSearch;
    private RecyclerViewFixed rvPoi;

    private String mSelectCity;
    private SuggestionAdapter suggestionAdapter;
    private RelativeLayout rlLocation;
    private PoiAdapter poiAdapter;
    private LatLng curLatlng;
    private PoiSearch poiSearch;
    private FrameLayout flPopDetailAddress;

    String[] mLocaPermissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
    private MPermission mPermission;
    private BaseDialog mLocationServiceDailog;
    private boolean isHome;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.select_address));
    }

    @Override
    protected boolean useButterKnife() {
        return false;
    }

    private void popPoiInfoWindow(final PoiInfo poiInfo) {

        final View inflate = View.inflate(this, R.layout.layout_detail_address, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, DensityUtils.getScreenHeight(this) * 3 / 5);
        flPopDetailAddress.addView(inflate, params);
        final int fromY = DensityUtils.getScreenHeight(this);
        final int toY = DensityUtils.getScreenHeight(this) * 2 / 5;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromY, toY);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                LogUtils.i("onAnimationUpdate", "value1 === " + value);
                inflate.setTranslationY(value);
                float alf = 1 - (value - toY) / (fromY - toY);
                LogUtils.i("onAnimationUpdate", "alf1 === " + alf);
                inflate.setAlpha(alf);
            }
        });
        valueAnimator.start();
        TextView tvName = inflate.findViewById(R.id.layout_detail_address_tv_name);
        TextView tvAddress = inflate.findViewById(R.id.location_tv_address);
        tvName.setText(poiInfo.name);
        tvAddress.setText(poiInfo.address);

        TextView tvUpdateAddress = inflate.findViewById(R.id.layout_detail_address_tv_update_address);
        TextView tvSaveAddress = inflate.findViewById(R.id.layout_detail_address_tv_save_address);
        final EditText etDetailAddress = inflate.findViewById(R.id.layout_detail_address_et_detail_address);
        tvSaveAddress.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {// 保存地址
                saveAddress(poiInfo, etDetailAddress);
            }
        });
        tvUpdateAddress.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

                if (flPopDetailAddress != null) {
                    flPopDetailAddress.removeAllViews();
                }
                rvPoi.setVisibility(View.VISIBLE);
                llSearch.setVisibility(View.VISIBLE);
            }
        });
        LatLng location = poiInfo.location;

        if (location == null) {
            return;
        }
        MyLocationData locData = new MyLocationData.Builder() // 定位数据
//                .accuracy(poiInfo.) // 定位精度bdLocation.getRadius()
//                .direction(poiInfo.getDirection())  // 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(location.latitude) // 经度
                .longitude(location.longitude) // 纬度
                .build(); // 构建

        mBaiduMap.setMyLocationData(locData); // 设置定位数据

        // 更新poi列表
        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
        // 设置反地理编码位置坐标
        reverseGeoCodeOption.location(location);
        if (geoCoder != null)
            geoCoder.reverseGeoCode(reverseGeoCodeOption);

        KeyBoardUtils.closeKeybord(et_address, LocationActivity.this);
        KeyBoardUtils.showSoftInput(LocationActivity.this);
    }

    /**
     * @param poiInfo
     * @param etDetailAddress
     * @desc 保存地址
     */
    private void saveAddress(PoiInfo poiInfo, EditText etDetailAddress) {
        // 地址
        String address = poiInfo.getAddress();
        String name = poiInfo.getName();
        // 房间号
        if (TextUtils.isEmpty(etDetailAddress.getText().toString())) {
            ToastUtils.showShortToast(LocationActivity.this, getResources().getString(R.string.Please_enter_the_detailed_address));
            return;
        }
        // 经纬度
        LatLng location = poiInfo.getLocation();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        intent.putExtra(KEY_LATLNG, location.longitude + "-" + location.latitude);
        intent.putExtra(KEY_DETAIL_ADDRESS, address + name + "-" + etDetailAddress.getText().toString());
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            isHome = intent.getBooleanExtra(KEY_IS_HOME, false);
        }
        rlLocation = inflate.findViewById(R.id.location_rl);
        mMapView = inflate.findViewById(R.id.location_textureMapView);
        llSearch = inflate.findViewById(R.id.location_ll_search);
        tvCity = inflate.findViewById(R.id.location_tv_city);
        et_address = inflate.findViewById(R.id.location_et_address);
        rvPoi = inflate.findViewById(R.id.location_rv_poi);
        rvSearch = inflate.findViewById(R.id.location_rv_search);
        flPopDetailAddress = inflate.findViewById(R.id.location_fl_popDetailAddress);
        rvPoi.setVertical(1);
        rvSearch.setVertical(1);
        ViewGroup.LayoutParams layoutParams = rvPoi.getLayoutParams();
        layoutParams.height = DensityUtils.getScreenHeight(this) * 2 / 5;
        rvPoi.setLayoutParams(layoutParams);
        initListener();
    }

    private void initListener() {
        et_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0 || TextUtils.isEmpty(mSelectCity)) {
                    rvSearch.setVisibility(View.GONE);
                    return;
                }
                rvSearch.setVisibility(View.VISIBLE);
                poiSearch.searchInCity((new PoiCitySearchOption())
                        .city(mSelectCity)//城市名称
                        .cityLimit(true)
                        .keyword(s.toString())//必填
                        .pageCapacity(10)//每页条数
                        .pageNum(1));//分页页码
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvCity.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                startActivityForResult(CitySelectActivity.class,
                        CitySelectActivity.KEY_SELECT_TYPE,
                        CitySelectActivity.TYPE_CITY, REQUEST_CITY_CODE);
            }
        });
    }

    OnGetPoiSearchResultListener onGetPoiSearchResultListener = new OnGetPoiSearchResultListener() {
        /**
         * 获取POI搜索结果
         * @param poiResult Poi检索结果，包括城市检索，周边检索，区域检索
         */
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {

                List<PoiInfo> allPoi = poiResult.getAllPoi();
                suggestionAdapter = new SuggestionAdapter(
                        R.layout.item_suggestion,
                        LocationActivity.this,
                        new ArrayList<PoiInfo>(), new EmptyRVAdapter.OnItemClickListener<PoiInfo>() {
                    @Override
                    public void onItemClicked(View v, PoiInfo poiInfo, int position) {
                        //你的业务
                        rvPoi.setVisibility(View.GONE);
                        et_address.clearFocus();
                        llSearch.setVisibility(View.GONE);
                        rvSearch.setVisibility(View.GONE);
                        if (isHome) {
                            returnData(poiInfo);
                        } else {
                            popPoiInfoWindow(poiInfo);
                        }
                    }

                    @Override
                    public void onItemLongClicked(View v, PoiInfo poiInfo, int position) {

                    }
                });
                rvSearch.setAdapter(suggestionAdapter);
                suggestionAdapter.setLatLng(curLatlng);
                suggestionAdapter.setData(allPoi);

                return;
            } else if (poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                suggestionAdapter = new SuggestionAdapter(
                        R.layout.item_suggestion,
                        LocationActivity.this,
                        new ArrayList<PoiInfo>(), null);
                rvSearch.setAdapter(suggestionAdapter);
                suggestionAdapter.setLatLng(curLatlng);
                suggestionAdapter.setData(null);
                return;
            } else {
                ToastUtils.showShortToast(LocationActivity.this, poiResult.error.name());
            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    private void returnData(PoiInfo poiInfo) {
        LatLng location = poiInfo.getLocation();
        LoginHelper.setLatitude(String.valueOf(location.latitude));
        LoginHelper.setLongitude(String.valueOf(location.longitude));
        LoginHelper.setAddrStr(poiInfo.name);
        LoginHelper.saveCity(poiInfo.city);
        finish();
    }


    @Override
    protected int getChildLayoutId() {
        return R.layout.acivity_location;
    }

    @Override
    protected void onDestroy() {
        if (locationService != null) {
            locationService.unregisterListener(mLocationListener);
            locationService.stop();
        }
        try {
            if (mLocationServiceDailog != null) {
                mLocationServiceDailog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mMapView != null)
            mMapView.onDestroy();
        if (geoCoder != null)
            geoCoder.destroy();
        mMapView = null;
        geoCoder = null;
        mLocationListener = null;
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    protected void initData() {
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        isFirstLoc = true;
        locationService = new LocationService(this);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        mBaiduMap = mMapView.getMap();
        MapStatus mapStatus = new MapStatus.Builder().zoom(15f).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mMapView.showScaleControl(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位图层显示方式
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, null));
        // 地图状态改变相关监听
        mBaiduMap.setOnMapStatusChangeListener(onMapStatusChangeListener);
        // 创建GeoCoder实例对象
        geoCoder = GeoCoder.newInstance();
        poiSearch = PoiSearch.newInstance();
        poiAdapter = new PoiAdapter(R.layout.item_poi, LocationActivity.this, new ArrayList<PoiInfo>());
        rvPoi.setAdapter(poiAdapter);
        // 设置查询结果监听者   ####这里很重要该回调接口有两个方法
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                final List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();
                LogUtils.d(TAG, "这里的值:" + poiInfos);
                if (poiInfos != null && !"".equals(poiInfos)) {
                    //地图选点附近的POI信息
                    poiAdapter.setData(poiInfos);
                    poiAdapter.setOnItemClickListener(new CommonRVAdapter.OnItemClickListener<PoiInfo>() {
                        @Override
                        public void onItemClicked(View v, PoiInfo poiInfo, int position) {
                            LogUtils.d(TAG, poiInfo.address + "  " + poiInfo.name);
                            //你的业务
                            rvPoi.setVisibility(View.GONE);
                            llSearch.setVisibility(View.GONE);
                            et_address.clearFocus();
                            if (isHome) {
                                returnData(poiInfo);
                            } else {
                                popPoiInfoWindow(poiInfo);
                            }
                        }

                        @Override
                        public void onItemLongClicked(View v, PoiInfo poiInfo, int i) {

                        }

                    });
                }
                closeLoading();
            }
        });
        poiSearch.setOnGetPoiSearchResultListener(onGetPoiSearchResultListener);
        locationService.registerListener(mLocationListener);
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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
        } else {
            startLoc();
        }
    }

    /**
     * 地图状态改变相关监听
     */
    private BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener = new BaiduMap.OnMapStatusChangeListener() {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            // 获取地图最后状态改变的中心点
            LatLng cenpt = mapStatus.target;
            LogUtils.d(TAG, "最后停止点:" + cenpt.latitude + "," + cenpt.longitude);
            //发起地理编码，当转化成功后调用onGetReverseGeoCodeResult()方法
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(cenpt));
        }
    };

    private class BDLocationListener extends BDAbstractLocationListener {

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
            super.onConnectHotSpotMessage(s, i);
            LogUtils.i(TAG, "onConnectHotSpotMessage -> s = " + s);
            LogUtils.i(TAG, "onConnectHotSpotMessage -> i = " + i);
        }

        @Override
        public void onLocDiagnosticMessage(int i, int i1, String s) {
            super.onLocDiagnosticMessage(i, i1, s);
            LogUtils.i(TAG, "onLocDiagnosticMessage -> s = " + s);
            LogUtils.i(TAG, "onLocDiagnosticMessage -> i = " + i);
            LogUtils.i(TAG, "onLocDiagnosticMessage -> i1 = " + i1);
        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
                ToastUtils.showShortToast(LocationActivity.this, getResources().getString(R.string.Location_fail));
            }
            // 如果bdLocation为空或mapView销毁后不再处理新数据接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder() // 定位数据
                    .accuracy(location.getRadius()) // 定位精度bdLocation.getRadius()
                    .direction(location.getDirection())  // 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(location.getLatitude()) // 经度
                    .longitude(location.getLongitude()) // 纬度
                    .build(); // 构建
            LoginHelper.saveLocation(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()),
                    String.valueOf(location.getRadius()), String.valueOf(location.getDirection()));
            mBaiduMap.setMyLocationData(locData); // 设置定位数据
            // 是否是第一次定位
            if (isFirstLoc) {
                isFirstLoc = false;
                curLatlng = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(curLatlng, 18f);
                mBaiduMap.animateMapStatus(u);
                // 获取城市，待会用于POISearch
                mSelectCity = location.getCity();
                if (tvCity != null)
                    tvCity.setText(mSelectCity);
                LoginHelper.saveCity(mSelectCity);
                // 发起反地理编码请求(经纬度->地址信息)
                ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
                // 设置反地理编码位置坐标
                reverseGeoCodeOption.location(curLatlng);
                geoCoder.reverseGeoCode(reverseGeoCodeOption);
            }
            closeLoading();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            setResult(Activity.RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CITY_CODE) {
            if (data != null) {
                String city = data.getStringExtra(CitySelectActivity.KEY_DATA);
                mSelectCity = city;
                if (tvCity != null)
                    tvCity.setText(mSelectCity);
            }
        } else if (requestCode == RequestCodeConfig.RC_GPS) {
            startLoc();
        }
    }

    private void startLoc() {
        mPermission = MPermission.with(this);
        if (mPermission.hasPermission(mLocaPermissions) != -1) {
            mPermission.setPermission(mLocaPermissions).requestPermission(new MPermission.OnCallBack() {
                @Override
                public void valdateSuccess() {
                    locationService.start();
                    showLoading();
                }

                @Override
                public void valdateFail() {

                }
            });
        } else {
            locationService.start();
            showLoading();
        }
    }


}
