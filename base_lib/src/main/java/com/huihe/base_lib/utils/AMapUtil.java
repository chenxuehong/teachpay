package com.huihe.base_lib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.huihe.base_lib.R;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class AMapUtil {

    /**
     *  打开百度地图app
     *
     * @paramcontext      上下文对象
     *   @paramendLon      终点经度
     *   @paramendLat      终点纬度
     *    @paramendDescrible 终点地址 
     */
    public static void openBaiduMap(final Context context, double endLon, double endLat) {

        try {
            /*调用导航*/
            StringBuffer loc = new StringBuffer("baidumap://map/navi?");
            loc.append("&src=").append(R.string.application_name + "|" + R.string.application_name);
            loc.append("&location=").append(endLat + "," + endLon);
            Intent intent = Intent.getIntent(loc.toString());
            context.startActivity(intent); //启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开高德地图app
     *
     * @paramcontext      上下文对象
     * @paramendLon      终点经度
     * @paramendDescrible 终点地址
     *    
     */
    public static void openAMap(final Context context, double endLon, double endLat) {
        double[] doubles = bd_decrypt(endLon, endLat);
        try {
            /*调用导航*/
            StringBuilder loc = new StringBuilder();
            loc.append("androidamap://navi?sourceApplication=");
            loc.append(R.string.application_name);
            loc.append("&lat=");
            loc.append(doubles[1]);
            loc.append("&lon=");
            loc.append(doubles[0]);
            loc.append("&dev=");//起终 点是否偏移(0:lat和lon是已经加密后的,不需要国测加密: 1:需要国测加密)
            loc.append(0);//百度坐标调用高德的时候设置为0，因为百度地图坐标已经加密了
//            loc.append("&dname=").append(endDescrible);
            loc.append("&style=");
            loc.append(2);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(loc.toString()));
            intent.setPackage("com.autonavi.minimap");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static double[] bd_decrypt(double bd_lon, double bd_lat) {
        double[] gd_lat_lon = new double[2];
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) - 0.000003 * cos(x * Math.PI);
        gd_lat_lon[0] = z * cos(theta);
        gd_lat_lon[1] = z * sin(theta);
        return gd_lat_lon;
    }

}
