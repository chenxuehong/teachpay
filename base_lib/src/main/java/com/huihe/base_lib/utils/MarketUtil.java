package com.huihe.base_lib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.huihe.base_lib.constants.AppConfigs;

import java.util.ArrayList;
import java.util.List;

public class MarketUtil {
    /**
     * 判断应用市场是否存在的方法
     *
     * @param context
     * @param packageName 主流应用商店对应的包名
     *                    com.android.vending  -----Google Play
     *                    com.tencent.android.qqdownloader     -----应用宝
     *                    com.qihoo.appstore   -----360手机助手
     *                    com.baidu.appsearch  -----百度手机助手
     *                    com.xiaomi.market    -----小米应用商店
     *                    com.wandoujia.phoenix2   -----豌豆荚
     *                    com.huawei.appmarket -----华为应用市场
     *                    com.taobao.appcenter -----淘宝手机助手
     *                    com.hiapk.marketpho  -----安卓市场
     *                    cn.goapk.market      -----安智市场
     */
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> pName = new ArrayList<String>();
        // 从pinfo中将包名字取出
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pf = pinfo.get(i).packageName;
                pName.add(pf);
            }
        }
        // 判断pName中是否有目标程序的包名，有true，没有false
        return pName.contains(packageName);
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面
     */
    public static void launchAppDetail(Context mContext, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchGoogleMarketDetail(Context mContext, String appPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                //有浏览器
                mContext.startActivity(intent);
            } else {
                Toast.makeText(mContext, "您没安装应用市场，连浏览器也没有", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 跳转到应用宝的网页版地址
    private final static String WEB_YINGYONGBAO_MARKET_URL = "https://a.app.qq.com/o/simple.jsp?pkgname=com.eghuihe.qmore";

    private final static String MARKET_PKG_NAME_MI = "com.xiaomi.market"; // 小米应用商店
    private final static String MARKET_PKG_NAME_360 = "com.qihoo.appstore"; // 360手机助手
    private final static String MARKET_PKG_NAME_VIVO = "com.bbk.appstore"; // vivo应用市场
    private final static String MARKET_PKG_NAME_OPPO = "com.oppo.market"; // oppo应用市场
    private final static String MARKET_PKG_NAME_YINGYONGBAO = "com.tencent.android.qqdownloader"; // 应用宝
    private final static String MARKET_PKG_NAME_HUAWEI = "com.huawei.appmarket"; // 华为应用市场
    private final static String MARKET_PKG_NAME_BAIDU = "com.baidu.appsearch"; // 百度手机助手
    private final static String MARKET_PKG_NAME_MEIZU = "com.meizu.mstore"; //魅族
    private final static String MARKET_PKG_NAME_WANDOUJIA = "com.wandoujia.phoenix2"; // 豌豆荚
    private final static String MARKET_PKG_NAME_GOOGLE_PLAY = "com.android.vending"; // 谷歌

    /**
     * 跳转到渠道对应的市场，如果没有该市场，就跳转到应用宝（App或者网页版）
     *
     * @param context
     */
    public static boolean goToAppMarket(Context context) {
        try {
            // 过去当前渠道channelName
            String channelName = getCurrentChannelName(context);

            PackageManager pm = context.getPackageManager();
            List<PackageInfo> pinfos = pm.getInstalledPackages(0);

            String pkgName = "";

            switch (channelName) {
                case "yingyongbao":
                    pkgName = MARKET_PKG_NAME_YINGYONGBAO;
                    break;
                case "baidu":
                    pkgName = MARKET_PKG_NAME_BAIDU;
                    break;
                case "huawei":
                    pkgName = MARKET_PKG_NAME_HUAWEI;
                    break;
                case "oppo":
                    pkgName = MARKET_PKG_NAME_OPPO;
                    break;
                case "360":
                    pkgName = MARKET_PKG_NAME_360;
                    break;
                case "vivo":
                    pkgName = MARKET_PKG_NAME_VIVO;
                    break;
                case "xiaomi":
                    pkgName = MARKET_PKG_NAME_MI;
                    break;
                case "meizu":
                    pkgName = MARKET_PKG_NAME_MEIZU;
                    break;
                case "wandoujia":
                    pkgName = MARKET_PKG_NAME_WANDOUJIA;
                    break;
                case "google":
                    pkgName = MARKET_PKG_NAME_GOOGLE_PLAY;
                    break;
            }

            // 给一个默认的 应用宝
            if (TextUtils.isEmpty(pkgName)) {
                pkgName = MARKET_PKG_NAME_YINGYONGBAO;
            }

            // 筛选指定包名的市场intent
            if (pinfos.size() > 0) {
                for (int i = 0; i < pinfos.size(); i++) {
                    PackageInfo packageInfo = pinfos.get(i);
                    String packageName = packageInfo.packageName;
                    if (packageName.toLowerCase().equals(pkgName)) {
                        launchAppDetail(context, context.getApplicationContext().getPackageName(), pkgName);
                        return true;
                    }
                }
            }
            // 未匹配到
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常，跳转到应用宝网页版
            return false;
        }
    }

    /**
     * 获取渠道名
     *
     * @param context
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getCurrentChannelName(Context context) {
        if (context == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.
                        getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = String.valueOf(applicationInfo.metaData.get("UMENG_CHANNEL"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channelName;
    }

    public static String getCuPlatform() {
        return AppConfigs.Platform.PLATFORM_ANDROID;
    }
}
