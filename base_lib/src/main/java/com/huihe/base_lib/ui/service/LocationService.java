package com.huihe.base_lib.ui.service;

import android.content.Context;
import android.webkit.WebView;

import com.baidu.location.Address;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 定位服务LocationClient 相关
 *
 * @author baidu
 */
public class LocationService {
    private LocationClient client = null;
    private LocationClientOption mOption;
    private LocationClientOption DIYoption;
    private Object objLock;

    /***
     * 初始化 LocationClient
     *
     * @param locationContext
     */
    public LocationService(Context locationContext) {
        objLock = new Object();
        synchronized (objLock) {
            if (client == null) {
                client = new LocationClient(locationContext);
                client.setLocOption(getDefaultLocationClientOption());
            }
        }
    }

    /***
     * 注册定位监听
     *
     * @param listener
     * @return
     */

    public boolean registerListener(BDAbstractLocationListener listener) {
        boolean isSuccess = false;
        if (listener != null) {
            client.registerLocationListener(listener);
            isSuccess = true;
        }
        return isSuccess;
    }

    public void unregisterListener(BDAbstractLocationListener listener) {
        if (listener != null) {
            client.unRegisterLocationListener(listener);
            listener = null;
        }
    }

    private OnLocationListener mlocationListener;

    private BDAbstractLocationListener bdAbstractLocationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            double latitude = bdLocation.getLatitude();
            double longitude = bdLocation.getLongitude();
            float radius = bdLocation.getRadius();
            float direction = bdLocation.getDirection();
            String city = bdLocation.getCity();
            String addrStr = bdLocation.getAddrStr();
            List<Poi> poiList = bdLocation.getPoiList();
            String buildingName = bdLocation.getBuildingName();
            String floor = bdLocation.getFloor();
            String indoorLocationSurpportBuidlingName = bdLocation.getIndoorLocationSurpportBuidlingName();
            String indoorSurpportPolygon = bdLocation.getIndoorSurpportPolygon();
            PoiRegion poiRegion = bdLocation.getPoiRegion();
            Address address = bdLocation.getAddress();
            String province = address.province;
            String city1 = address.city;
            String town = address.town;
            String address1 = address.address;
            String district = address.district;

            if (poiList != null && poiList.size() > 0) {
                Poi poi = poiList.get(0);
                addrStr = poi.getName();
            }
            LoginHelper.setAddrStr(addrStr);
            LoginHelper.saveCity(city);
            if (mlocationListener != null)
                mlocationListener.onLocation(latitude, longitude, radius, direction);
        }
    };

    public void registerAndStartLocationListener(final OnLocationListener locationListener) {
        this.mlocationListener = locationListener;
        registerListener(bdAbstractLocationListener);
    }

    public void unRegisterLocationListener() {
        unregisterListener(bdAbstractLocationListener);
    }

    public interface OnLocationListener {

        void onLocation(double latitude, double longitude, float radius, float direction);
    }

    /**
     * @return 获取sdk版本
     */
    public String getSDKVersion() {
        if (client != null) {
            String version = client.getVersion();
            return version;
        }
        return null;
    }

    /***
     * 设置定位参数
     *
     * @param option
     * @return isSuccessSetOption
     */
    public boolean setLocationOption(LocationClientOption option) {
        boolean isSuccess = false;
        if (option != null) {
            if (client.isStarted()) {
                client.stop();
            }
            DIYoption = option;
            client.setLocOption(option);
        }
        return isSuccess;
    }

    /**
     * 开发者应用如果有H5页面使用了百度JS接口，该接口可以辅助百度JS更好的进行定位
     *
     * @param webView 传入webView控件
     */
    public void enableAssistanLocation(WebView webView) {
        if (client != null) {
            client.enableAssistantLocation(webView);
        }
    }

    /**
     * 停止H5辅助定位
     */
    public void disableAssistantLocation() {
        if (client != null) {
            client.disableAssistantLocation();
        }
    }

    public void initBaiduMap() {
        setLocationOption(getDefaultLocationClientOption());
    }

    /***
     *
     * @return DefaultLocationClientOption  默认O设置
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationMode.Hight_Accuracy); // 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll"); // 可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
//            mOption.setScanSpan(3000); // 可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true); // 可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true); // 可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false); // 可选，设置是否需要设备方向结果
            mOption.setLocationNotify(true); // 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true); // 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop
            mOption.setIsNeedLocationDescribe(true); // 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
            mOption.setIsNeedLocationPoiList(true); // 可选，默认false，设置是否需要POI结果，可以在BDLocation
            mOption.SetIgnoreCacheException(false); // 可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.setOpenGps(true); // 可选，默认false，设置是否开启Gps定位
            mOption.setIsNeedAltitude(false); // 可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return mOption;
    }

    static OnGetPoiSearchResultListener onGetPoiSearchResultListener = new OnGetPoiSearchResultListener() {
        /**
         * 获取POI搜索结果
         * @param poiResult Poi检索结果，包括城市检索，周边检索，区域检索
         */
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                List<PoiInfo> allPoi = poiResult.getAllPoi();
                if (allPoi != null && allPoi.size() > 0) {
                    List<MasterMechanismModel.MasterMechanismEntity> mechanismDetailInfoEntities = new ArrayList<>();
                    for (int i = 0; i < allPoi.size(); i++) {

                        PoiInfo poiInfo = allPoi.get(i);
                        String uid = poiInfo.getUid();
                        String name = poiInfo.getName();
                        String phoneNum = poiInfo.getPhoneNum();
                        String address = poiInfo.getAddress();
                        LatLng location = poiInfo.getLocation();
                        float serviceRating = 0.0f;
                        PoiDetailInfo poiDetailInfo = poiInfo.getPoiDetailInfo();
                        if (poiDetailInfo != null) {
                            serviceRating = (float) poiDetailInfo.serviceRating;
                        }
                        MasterMechanismModel.MasterMechanismEntity mechanismDetailInfoEntity = new MasterMechanismModel.MasterMechanismEntity();
                        mechanismDetailInfoEntity.setMechanism_name(name);
                        mechanismDetailInfoEntity.setMechanism_telephone(phoneNum);
                        mechanismDetailInfoEntity.setMechanism_addr(address);
                        mechanismDetailInfoEntity.setCategories_child(mPoi);
                        mechanismDetailInfoEntity.setAvg_rating(serviceRating);
                        mechanismDetailInfoEntity.setLatitude(location.latitude);
                        mechanismDetailInfoEntity.setLongitude(location.longitude);
                        mechanismDetailInfoEntity.setCooperative(false);
                        mechanismDetailInfoEntity.setUid(uid);
                        mechanismDetailInfoEntities.add(mechanismDetailInfoEntity);
                    }
                    if (mOnPoiSearchResultListener != null) {
                        mOnPoiSearchResultListener.OnPoiSearchResult(mechanismDetailInfoEntities);
                    }
                }

            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            if (mOnPoiSearchResultListener != null) {
                List<PoiDetailInfo> poiDetailInfoList = poiDetailSearchResult.getPoiDetailInfoList();
                if (poiDetailInfoList != null && poiDetailInfoList.size() > 0) {
                    PoiDetailInfo poiDetailInfo = poiDetailInfoList.get(0);
                    mOnPoiSearchResultListener.OnPoiDetailSearchResult(poiDetailInfo.detailUrl);
                }
            }
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            if (mOnPoiSearchResultListener != null) {
                String detailUrl = poiDetailResult.getDetailUrl();
                mOnPoiSearchResultListener.OnPoiDetailSearchResult(detailUrl);
            }
        }
    };

    private static OnPoiSearchResultListener mOnPoiSearchResultListener;
    private static String mPoi;

    public static void initPoiSearch(String city, int currentPage, int pageSize, String poi, OnPoiSearchResultListener onPoiSearchResultListener) {
        mPoi = poi;
        mOnPoiSearchResultListener = onPoiSearchResultListener;
        PoiSearch poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(onGetPoiSearchResultListener);
        poiSearch.searchInCity((new PoiCitySearchOption())
                .city(city)//城市名称
                .cityLimit(false)
                .keyword(poi)//必填
                .pageCapacity(pageSize)//每页条数
                .pageNum(currentPage));//分页页码
    }

    public static void initPoiDetailSearch(String uid, OnPoiSearchResultListener onPoiSearchResultListener) {
        mOnPoiSearchResultListener = onPoiSearchResultListener;
        PoiSearch poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(onGetPoiSearchResultListener);
        PoiDetailSearchOption poiDetailSearchOption = new PoiDetailSearchOption();
        poiDetailSearchOption = poiDetailSearchOption.poiUid(uid);
        poiSearch.searchPoiDetail(poiDetailSearchOption);
    }


    public interface OnPoiSearchResultListener {
        void OnPoiSearchResult(List<MasterMechanismModel.MasterMechanismEntity> mechanismDetailInfoEntities);

        void OnPoiDetailSearchResult(String detailUrl);
    }

    /**
     * @return DIYOption 自定义Option设置
     */
    public LocationClientOption getOption() {
        if (DIYoption == null) {
            DIYoption = new LocationClientOption();
        }
        return DIYoption;
    }

    public void start() {
        synchronized (objLock) {
            if (client != null && !client.isStarted()) {
                client.start();
            }
        }
    }

    public void restart() {
        synchronized (objLock) {
            if (client != null && !client.isStarted()) {
                client.restart();
            }
        }
    }

    public void requestLocation() {
        if (client != null) {
            client.requestLocation();
        }
    }

    public void stop() {
        synchronized (objLock) {
            if (client != null && client.isStarted()) {
                client.stop();
            }
        }
    }

    public boolean isStart() {
        return client.isStarted();
    }

    public boolean requestHotSpotState() {
        return client.requestHotSpotState();
    }
}
