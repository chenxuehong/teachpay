package com.huihe.base_lib.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;


import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.utils.manager.AppManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import static android.text.TextUtils.isEmpty;

/**
 * Created by bruce on 2017/2/10.
 */
public class DeviceUtils {
    /**
     * 获取App安装包信息
     */
    public static PackageInfo getPackageInfo() {
        Activity activity = AppManager.getInstance().currentActivity();

        PackageInfo info = null;
        try {
            info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null) info = new PackageInfo();
        return info;
    }

    /**
     * 获取注册设备信息
     */
    public static String getDeviceRegisterInfo() {
        return "deviceId=" + getUUID() +
                "&packageName=com.readyidu.pockettv" +
                "&deviceType=android" +
                "&deviceName=" + getDeviceName() +
                "&sysVersion=" + getOSVersion();
//                "&resolution=" + getResolution() +
//                "&country=" + getCountry() +
//                "language=" + getLanguage();
    }

    /**
     * 获取设备所属国家
     */
    private static String getCountry() {
        return AppManager.getInstance().currentActivity().getResources().getConfiguration().locale.getCountry();
    }

    /**
     * 获取设备当前语言
     */
    public static String getLanguage() {
        return AppManager.getInstance().currentActivity().getResources().getConfiguration().locale.getLanguage();
    }

    /**
     * 获取设备唯一识别码
     */
    public static String getUUID() {
        String uuid = SPUtils.getIntance().getString(AppConfigs.SP_KEY_UUID, "");
        if (isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SPUtils.getIntance().getString(AppConfigs.SP_KEY_UUID, uuid);
        }

        return uuid;
    }

    /**
     * 获取设备型号
     */
    public static String getModel() {
        String model = android.os.Build.MODEL;

        return model;
    }

    /**
     * 设备名称
     */
    private static String getDeviceName() {
        return Build.DEVICE;
    }

    /**
     * 系统版本号
     */
    private static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();//获取包管理器
        try {
            //通过当前的包名获取包的信息
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);//获取包对象信息
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取坂本明
     */
    public static String getVersionName(Context context) {

        PackageManager manager = context.getPackageManager();
        try {
            //第二个参数代表额外的信息，例如获取当前应用中的所有的Activity
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 版本号比较
     * 0代表相等，1代表version1大于version2，-1代表version1小于version2
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    /**
     * 获取分辨率
     */
    private static String getResolution() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        AppManager.getInstance().currentActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels + "*" + displaymetrics.heightPixels;
    }


}