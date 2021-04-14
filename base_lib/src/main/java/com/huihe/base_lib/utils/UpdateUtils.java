package com.huihe.base_lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.huihe.base_lib.constants.ApiConfig;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateUtils {

    /**
     * 静默下载apk
     * @param context
     * @param url
     */
    public static void down(final Context context, final String url) {
        if (isWifi(context) && url.endsWith(".apk")) {
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .readTimeout(1000, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(1000, TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                    .build();
            final Request request = new Request.Builder().url(url).build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Guo", "onFailure", e);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    InputStream inputStream = response.body().byteStream();
                    FileOutputStream fileOutputStream;
                    try {
                        File file = getApkDirectory();
                        if (!file.exists()) {
                            file.mkdirs();
                        }

                        if (file.isDirectory()) {
                            File[] files = file.listFiles();
                            if (files != null) {
                                for (File file1 : files) {
                                    if (file1.getName().contains(context.getPackageName())) {
                                        file1.delete();
                                    }
                                }
                            }
                        }
                        String s = MD5.toMD5(url);
                        File update = new File(file, context.getPackageName() + "." + s + ".apk");
                        if (update.exists()) {
                            update.delete();
                        }
                        fileOutputStream = new FileOutputStream(update.getAbsolutePath());
                        byte[] buffer = new byte[2048];
                        int len = 0;
                        while ((len = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, len);
                        }
                        fileOutputStream.flush();
                    } catch (IOException e) {
                        Logger.e("err", e);
                    }
                }
            });
        }
    }

    public static File getApkDirectory() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    /**
     * 获取apk下载路径
     * @param context
     * @param url
     * @return
     */
    public static File getApkPath(Context context, String url) {
        String md5 = MD5.toMD5(url);
        File apkDirectory = getApkDirectory();
        if (apkDirectory.isDirectory()) {
            File[] files = apkDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().contains(context.getPackageName())
                            && file.getName().endsWith(".apk") && file.getName().contains(md5)) {
                        try {
                            PackageManager packageManager = context.getPackageManager();
                            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
                            if (packageArchiveInfo != null) {
                                return file;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }

                    }
                }
            }
        }
        return null;
    }


    public static boolean isLastVersion(Context context, String url) {
        File apkPath = getApkPath(context, url);
        return null != apkPath;
    }

    private static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }


    public static void update(final Activity activity,int isForciblyUpdate, String updateContent, final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (isForciblyUpdate != 0) {//1 强制更新 0 无更新内容 2有更新内容
            boolean isForce = isForciblyUpdate == 1;
//            UpdateAppDialog dialog = new UpdateAppDialog(activity, R.style.dialog_trans, isForce,
//                    "升级到新版本：", updateContent, new UpdateAppDialog.OnShareSelectListener() {
//                @Override
//                public void onSelectType(int type) {
//                    if (0 == type) {    //取消更新，后台静默下载
//                        int updateTipCount = (int) SPUtils.get("update_cancel_count_" + ApiConfig.NEWEST_APP_VERSION, 0);
//                        updateTipCount++;
//                        SPUtils.put("update_cancel_count_" + ApiConfig.NEWEST_APP_VERSION, updateTipCount);
//                        SPUtils.put("update_cancel_time_" + ApiConfig.NEWEST_APP_VERSION, System.currentTimeMillis());
//                        UpdateUtils.down(activity, url);
//                    } else {        //确认更新
//                        Intent intent = new Intent();
//                        intent.setAction("android.intent.action.VIEW");
//                        intent.setData(Uri.parse(url));
//                        activity.startActivity(intent);
//                    }
//                }
//            });
//            dialog.show();
        }
    }

    public static void install(final Activity activity, int isForciblyUpdate, String updateContent, final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

//        if (isForciblyUpdate != 0) {
//            boolean isForce = isForciblyUpdate == 1;
//            UpdateAppDialog dialog = new UpdateAppDialog(activity, R.style.dialog_trans, isForce,
//                    "升级到新版本：", updateContent, new UpdateAppDialog.OnShareSelectListener() {
//                @Override
//                public void onSelectType(int type) {
//                    if (0 == type) {    //取消更新
//                        int updateTipCount = (int) SPUtils.get("update_cancel_count_" + ApiConfig.NEWEST_APP_VERSION,
//                                0);
//                        updateTipCount++;
//                        SPUtils.put("update_cancel_count_" + ApiConfig.NEWEST_APP_VERSION, updateTipCount);
//                        SPUtils.put("update_cancel_time_" + ApiConfig.NEWEST_APP_VERSION, System.currentTimeMillis());
//                    } else {    //确认更新
//                        File apkPath = UpdateUtils.getApkPath(activity, url);
//                        if (null != apkPath) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                            intent.setDataAndType(Uri.fromFile(apkPath), "application/vnd.android.package-archive");
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            activity.startActivity(intent);
//                        }
//                    }
//                }
//            });
//            dialog.show();
//        }
    }

    public static  void update(Activity activity) {
//        if (BuildConfig.DEBUG || HttpEngineCore.END_POINT.contains("218.75.36.107")) {//debug/线下 版本不执行
//            return;
//        }

        if (UpdateUtils.isLastVersion(activity, ApiConfig.versionUpdateDescriptionUrl)) {
            UpdateUtils.install(activity, ApiConfig.isForciblyUpdate, ApiConfig.versionUpdateDescription + "",
                    ApiConfig.versionUpdateDescriptionUrl);
        } else {
                UpdateUtils.update(activity, ApiConfig.isForciblyUpdate, ApiConfig.versionUpdateDescription + "",
                        ApiConfig.versionUpdateDescriptionUrl);
            

        }
    }
}