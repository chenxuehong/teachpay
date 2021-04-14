package com.huihe.base_lib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.huihe.base_lib.R;

import java.math.BigDecimal;

public class NumberUtils {

    /**
     * 数值转换为整型
     */
    public static int tranToInt(float number) {
        return new BigDecimal(number).setScale(1, BigDecimal.ROUND_HALF_DOWN).intValue();
    }

    /**
     * 数值转换为整型
     */
    public static int tranToInt(String number) {
        if (TextUtils.isEmpty(number)) {
            number = "0";
        }
        try {
            int num = Integer.valueOf(number);
            return num;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 保留一位小数
     */
    public static float tranToADecimal(float number) {
        return new BigDecimal(number).setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    /**
     * 保留一位小数
     */
    public static BigDecimal tranToADecimal(BigDecimal number) {
        BigDecimal bigDecimal = number.setScale(1, BigDecimal.ROUND_HALF_DOWN);
        return bigDecimal;
    }

    /**
     * 保留一位小数
     */
    public static float tranToADecimal(double number) {
        return new BigDecimal(number).setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    /**
     * 保留2位小数
     */
    public static double tranToTwoDecimal(double number) {
        return new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 保留2位小数
     */
    public static float tranToTwoDecimal(float number) {
        return new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }


    /**
     * 保留2位小数
     */
    public static float tranToTwoDecimal(String number) {
        if (TextUtils.isEmpty(number)) {
            number = "0.00";
        }
        return new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    /**
     * 保留1位小数
     */
    public static float tranToADecimal(String number) {
        if (TextUtils.isEmpty(number)) {
            number = "0.0";
        }
        return new BigDecimal(number).setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    /**
     * 向下保留一位小数
     */
    public static float tranToADecimalDOWN(float number) {
        int v = (int) (number * 10);
        return (float) v / 10;
    }

    /**
     * 保留三位小数
     */
    public static float tranToThreeDecimal(float number) {
        return new BigDecimal(number).setScale(3, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    public static String getNumber(Context context, long number) {

        if (number < 1000) {
            return String.valueOf(number);
        }

        float w = number * 1.0f / 1000;
        return tranToADecimal(w) + context.getResources().getString(R.string.thousand);
    }

    public static String transMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            try {
                money = String.valueOf(tranToTwoDecimal(Float.valueOf(money)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            money = "0.0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("¥").append(money);
        return stringBuilder.toString();
    }

    public static String getNumerForW(Long num) {

        if (num == null) {
            return "0";
        }
        if (num < 10000) {
            return String.valueOf(num);
        }
        float w = num * 1.0f / 1000;
        return tranToADecimal(w) + "w";
    }

    public static String getDistance(double distance) {
        if (distance > 1000) {
            return String.valueOf(tranToADecimal(distance / 1000)).concat("km");
        }
        return String.valueOf(tranToADecimal(distance)).concat("m");
    }
}