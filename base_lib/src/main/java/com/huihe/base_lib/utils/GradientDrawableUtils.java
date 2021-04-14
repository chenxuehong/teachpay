package com.huihe.base_lib.utils;

import android.graphics.drawable.GradientDrawable;

public class GradientDrawableUtils {

    /*
    * tv = findViewById(R.id.tv);
int[] colors = {0xFFFF9326,0xFFFFC54E};
GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,colors);
drawable.setCornerRadius(25);
drawable.setGradientType(GradientDrawable.RECTANGLE);
tv.setBackground(drawable);
    * */
    public static GradientDrawable getGradientDrawable(GradientDrawable.Orientation orientation, int[] colors,int cornerRadius,int gradientType) {
        GradientDrawable drawable = new GradientDrawable(orientation,colors);
        drawable.setCornerRadius(cornerRadius);
        drawable.setGradientType(gradientType);
        return drawable;
    }
}
