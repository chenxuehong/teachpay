package com.huihe.base_lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bruce on 2017/2/10.
 * <p>
 * Update by fangzhiyuan on 2017/12/12
 */
public class ToastUtils {

    private static final String TAG = ToastUtils.class.getSimpleName();
    private static Toast toast;
    private static Toast mNoticeToast;
    private static Timer mNoticeTimer;

    /**
     * 显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLongToast(Context context, String message) {
        if (context != null) {
            Toast toast = getLongToast(context, message);
            toast.show();
        }
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShortToast(Context context, String message) {
        if (context != null) {
            Toast toast = getShortToast(context, message);
            toast.show();
        }
        LogUtils.i(TAG, "showShortToast - currentThread - " + Thread.currentThread().getName());
    }

    public static void showNoticeToast(Context context, String text, int duration) {
        if (mNoticeToast == null) {
            mNoticeToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }

        if (mNoticeTimer == null) {
            mNoticeTimer = new Timer();
        }

        mNoticeToast.setText(text);
        mNoticeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mNoticeToast.show();
            }
        }, 0, duration);

    }

    public static void showSuccess(String message) {
        View mtoastView = LayoutInflater.from(BaseApplication.getInstance().getApplicationContext()).inflate(R.layout.view_toast_image, null);
        LinearLayout relativeLayout = mtoastView.findViewById(R.id.toast_linear);
        //动态设置toast控件的宽高度，宽高分别是130dp
        //这里用了一个将dp转换为px的工具类PxUtil
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                DensityUtils.dp2px(BaseApplication.getInstance().getApplicationContext(), 130),
                DensityUtils.dp2px(BaseApplication.getInstance().getApplicationContext(), 130f));
        relativeLayout.setLayoutParams(layoutParams);
        TextView textView = mtoastView.findViewById(R.id.tv_toast_clear);
        textView.setText(message);
        Toast toast = new Toast(BaseApplication.getInstance().getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mtoastView);
        toast.show();
    }

    public static void hideNoticeToast() {
        if (mNoticeToast != null) {
            mNoticeToast.cancel();
            mNoticeToast = null;
        }
        if (mNoticeTimer != null) {
            mNoticeTimer.cancel();
            mNoticeTimer = null;
        }
    }

    @SuppressLint("getShortToast")
    private static Toast getShortToast(Context c, String message) {
        if (toast == null) {
            toast = Toast.makeText(c.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();
            toast = Toast.makeText(c.getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.setText(message);
        }
        return toast;
    }

    @SuppressLint("getLongToast")
    private static Toast getLongToast(Context c, String message) {
        if (toast == null) {
            toast = Toast.makeText(c.getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.cancel();
            toast = Toast.makeText(c.getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setText(message);
        }
        return toast;
    }
}