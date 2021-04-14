package com.huihe.base_lib.utils;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseApplication;

public class ColorUtils {
    private static int titleColor = 0;
    private static int contentColor = 0;
    private static int tipColor = 0;
    private static int themeColor = 0;
    private static int stressColor = 0;

    public static int getTitleColor() {
        if (titleColor == 0) {
            titleColor = BaseApplication.getInstance().getResources().getColor(R.color.title_color);
        }
        return titleColor;
    }

    public static int getContentColor() {
        if (contentColor == 0) {
            contentColor = BaseApplication.getInstance().getResources().getColor(R.color.content_color);
        }
        return contentColor;
    }

    public static int getTipColor() {
        if (tipColor == 0) {
            tipColor = BaseApplication.getInstance().getResources().getColor(R.color.tip_color);
        }
        return tipColor;
    }

    public static int getThemeColor() {
        if (themeColor == 0) {
            themeColor = BaseApplication.getInstance().getResources().getColor(R.color.theme_color);
        }
        return themeColor;
    }

    public static int getStressColor() {
        if (stressColor == 0) {
            stressColor = BaseApplication.getInstance().getResources().getColor(R.color.stress_color);
        }
        return stressColor;
    }
}
