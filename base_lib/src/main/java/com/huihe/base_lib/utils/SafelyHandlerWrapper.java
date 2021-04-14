package com.huihe.base_lib.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.huihe.base_lib.ui.activity.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * 对handler内存泄漏的处理
 */
public class SafelyHandlerWrapper {

    public static class MyHandler extends Handler {

        private WeakReference<Activity> activityWeakReference;

        public MyHandler(Activity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = activityWeakReference.get();
            if (activity != null) {
                realHandleMessage(msg);
            }
        }

        protected void realHandleMessage(Message msg) {

        }
    }

}
