package com.huihe.base_lib.ui.fragment.baidumap;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

/**
 * @desc 附近marker地图模式
 */
public abstract class NearMarkerMapFragment<T> extends BaseFragment {

    private TextureMapView mMapView;
    private LocationService locationService;
    private BaiduMap mBaiduMap;
    private boolean isFirstLoc;
    private LatLng curLatlng;

    @Override
    protected boolean useButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_near_privateeducation_map;
    }

    @Override
    protected void initView() {
        super.initView();
        mMapView = mContainer.findViewById(R.id.fragment_near_privateeducation_map_textureMapView);
    }

    @Override
    protected void initData() {
        super.initData();
        isFirstLoc = true;
        mBaiduMap = mMapView.getMap();
        mMapView.showScaleControl(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位图层显示方式
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, null));
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (curLatlng != null) {

                    popMarkerWindow(marker.getExtraInfo(), curLatlng.latitude, curLatlng.longitude);
                }
                return true;
            }
        });
        startShow();
    }

    protected abstract void popMarkerWindow(Bundle extraInfo, double latitude, double longitude);

    protected abstract void startShow();

    public void createMarker(View view, String longitude, String latitude, T t) {
        longitude = TextUtils.isEmpty(longitude) ? "0" : longitude;
        latitude = TextUtils.isEmpty(latitude) ? "0" : latitude;
        //定义Maker坐标点
        LatLng point = new LatLng(Double.valueOf(latitude)
                , Double.valueOf(longitude));   //横纵坐标值
        //构建Marker图标
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(view);
        //构建MarkerOption，用于在地图上添加Marker
        MarkerOptions optionPosition = new MarkerOptions()
                .position(point)
                .icon(bitmapDescriptor)
                .zIndex(9)
                .draggable(false);
        //在地图上添加Marker，并显示
        Marker marker = (Marker) mBaiduMap.addOverlay(optionPosition);
        //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
        Bundle bundle = new Bundle();
        bundle.putString("info", JsonUtil.toJson(t));
        marker.setExtraInfo(bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null)
            mMapView.onPause();
    }

    public void startLocation() {

        MyLocationData locData = new MyLocationData.Builder() // 定位数据
                .accuracy(Float.valueOf(LoginHelper.getRadius())) // 定位精度bdLocation.getRadius()
                .direction(Float.valueOf(LoginHelper.getDirection()))  // 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(Double.valueOf(LoginHelper.getLatitude())) // 经度
                .longitude(Double.valueOf(LoginHelper.getLongitude())) // 纬度
                .build(); // 构建

        mBaiduMap.setMyLocationData(locData); // 设置定位数据
        curLatlng = new LatLng(Double.valueOf(LoginHelper.getLatitude()),
                Double.valueOf(LoginHelper.getLongitude()));
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(curLatlng, 18f);
        mBaiduMap.animateMapStatus(u);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (locationService != null) {
            locationService.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }
}
