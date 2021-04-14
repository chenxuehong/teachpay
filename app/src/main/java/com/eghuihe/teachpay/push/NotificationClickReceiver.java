package com.eghuihe.teachpay.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.eghuihe.teachpay.push.receive.NotificationHandler;


/**
 * 处理通知点击事件
 */
public class NotificationClickReceiver extends BroadcastReceiver {
    public static final String KEY_DATA = "data";
    private static final String TAG = NotificationClickReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra(KEY_DATA);
        NotificationHandler.handleGuiNotificationClickListener(context, data);
    }

}
