package com.jay.daguerre.utils;

public class StringUtils {

    public static boolean isNotEmptyString(final String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isEmptyString(final String str) {
        return str == null || str.length() <= 0;
    }
}