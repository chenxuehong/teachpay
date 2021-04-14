package com.jay.daguerre.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DensityUtils {
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static int getScreenHeight(Context context) {

        int heightPixels = getDisplayMetrics(context).heightPixels;
        return heightPixels;
    }

    public static int getScreenWidth(Context context) {
        int widthPixels = getDisplayMetrics(context).widthPixels;
        return widthPixels;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }


    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getDisplayMetrics(context));
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static float sp2px(Context context, float spVal) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return spVal * fontScale;
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        float scale = getDisplayMetrics(context).density;
        return (pxVal / scale);
    }

    /**
     * sp转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float sp2dp(Context context, float pxVal) {
        return px2dp(context, sp2px(context, pxVal));
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / getDisplayMetrics(context).scaledDensity);
    }
}
