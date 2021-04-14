package com.huihe.base_lib.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by bruce on 2017/2/10.
 */
public class EventUtils {
    private static SparseArray<Long> lastClickTimeMap = new SparseArray<>();

    private static long intervalMillis = 1000;

    public static boolean isFastDoubleClick(int resId) {
        long cur = System.currentTimeMillis();
        long last = lastClickTimeMap.get(resId, cur - 1050);
        long distance = cur - last;
        if (distance < intervalMillis) {
            return true;
        }
        lastClickTimeMap.put(resId, cur);
        return false;
    }

    public static boolean isFastDoubleClick(View view) {
        long cur = System.currentTimeMillis();
        long last = lastClickTimeMap.get(view.getId(), cur - 1050);
        long distance = cur - last;
        if (distance < intervalMillis) {
            return true;
        }
        lastClickTimeMap.put(view.getId(), cur);
        return false;
    }

    public static boolean isFastDoubleLongClick(int resId) {
        long cur = System.currentTimeMillis();
        long last = lastClickTimeMap.get(resId, cur - 1050);
        long distance = cur - last;
        if (distance < 1000) {
            return true;
        }
        lastClickTimeMap.put(resId, cur);
        return false;
    }

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD < intervalMillis) {

            return true;
        }
        lastClickTime = time;
        return false;

    }
}