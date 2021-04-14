package com.huihe.base_lib.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.IOException;

/**
 * @author Wlq
 * @description 系统工具类
 * @date 2018/3/17 上午11:06
 */
public class SystemUtils {

    private static MediaPlayer mMediaPlayer;

    /**
     * 判断App是否安装
     *
     * @param packageName
     * @return
     */
    public static boolean isAppInstall(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void callMobile(final Context context, final String phone) {
        if (context == null || TextUtils.isEmpty(phone)) {
            return;
        }
        MPermission.with((Activity) context).setPermission(new String[]{
                Manifest.permission.CALL_PHONE}).requestPermission(new MPermission.OnCallBack() {
            @Override
            public void valdateSuccess() {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));
                //开启系统拨号器
                context.startActivity(intent);
            }

            @Override
            public void valdateFail() {

            }
        });

    }

    public static void startAlarm(Context context) {
        if (mMediaPlayer == null)
            mMediaPlayer = MediaPlayer.create(context, getSystemDefultRingtoneUri(context));
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }

    public static void stopSystemSound() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    //获取系统默认铃声的Uri
    private static Uri getSystemDefultRingtoneUri(Context context) {
        return RingtoneManager.getActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_RINGTONE);
    }

}
