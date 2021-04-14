package com.huihe.base_lib.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;

import com.huihe.base_lib.R;
import com.ycbjie.notificationlib.NotificationUtils;


public class NotifyUtil {
    private static final long[] vibrate = new long[]{0, 500, 1000, 1500};
    private static NotificationUtils notificationUtils;

    //简单的通知
    public static void sendNotification(Context context,int nid, String title,String content) {
        if(notificationUtils==null){
            notificationUtils=new NotificationUtils(context);
        }
        //这三个属性是必须要的，否则异常
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.setPriority(Notification.PRIORITY_DEFAULT)
                .setVibrate(vibrate)
                .sendNotification(nid,title,content, R.mipmap.ic_launcher);
    }

    //带跳转的通知
    public static void sendNotificationAction(Context context,int nid, PendingIntent resultPendingIntent, String title, String content){
        if(notificationUtils==null){
            notificationUtils=new NotificationUtils(context);
        }

        notificationUtils
                .setContentIntent(resultPendingIntent)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setVibrate(vibrate)
                .sendNotificationCompat(nid,title, content, R.mipmap.ic_launcher);
    }

    public static void clear(){
        if(notificationUtils!=null){
            notificationUtils.clearNotification();
        }
    }


}
