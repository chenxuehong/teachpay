package com.huihe.base_lib.utils;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils {


    private long mSecond;

    /**
     * 记时器
     */
    private class BroadcastTimerTask extends TimerTask {

        Activity appCompatActivity;
        OnTimerUpdateListener listener;

        public BroadcastTimerTask(Activity appCompatActivity, OnTimerUpdateListener listener) {
            this.appCompatActivity = appCompatActivity;
            this.listener = listener;
        }

        public void run() {
            //Log.i(TAG, "timeTask ");
            ++mSecond;
            appCompatActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onBroadcasterTimeUpdate(mSecond);
                }
            });
        }
    }

    private Timer mBroadcastTimer;
    private BroadcastTimerTask mBroadcastTimerTask;

    public void startTimer(Activity activity, OnTimerUpdateListener listener) {
        //直播时间
        if (mBroadcastTimer == null) {
            mBroadcastTimer = new Timer(true);
        }
        mBroadcastTimerTask = new BroadcastTimerTask(activity,listener);
        mBroadcastTimer.schedule(mBroadcastTimerTask, 1000, 1000);
    }

    public void stopTimer() {
        //直播时间
        if (null != mBroadcastTimer) {
            mBroadcastTimerTask.cancel();
        }
        mSecond = 0;
    }

    public interface OnTimerUpdateListener{
        void onBroadcasterTimeUpdate(long mSecond);
    }
}
