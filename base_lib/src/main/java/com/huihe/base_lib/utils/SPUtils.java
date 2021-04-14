package com.huihe.base_lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.huihe.base_lib.ui.activity.BaseApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bruce on 15/12/7.
 */
public class SPUtils {

    private static SPUtils mSPUtils;
    private SharedPreferences sp;

    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "hh_qmore_data";

    public static SPUtils getIntance() {

        if (mSPUtils == null) {
            synchronized (SPUtils.class) {

                if (mSPUtils == null) {
                    mSPUtils = new SPUtils();
                }
            }
        }
        return mSPUtils;
    }

    public SPUtils() {
        sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putFloat(String key, float value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public <T> void putObject(String key, T t) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, JsonUtil.toJson(t));
        editor.apply();
    }

    public <T> T getObject(String key, Class<T> clazz){
        String json = sp.getString(key, "");
        return JsonUtil.fromJson(json, clazz);
    }

    public <T> void putList(String key, List<T> list) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, JsonUtil.toJson(list));
        editor.apply();
    }

    public <T> List<T> getList(String key, Type typeOfT) {
        String json = sp.getString(key, "");
        return JsonUtil.fromJson(json, typeOfT);
    }

    public String getString(String key, String defVaule) {

        return sp.getString(key, defVaule);
    }

    public int getInt(String key, int defValue) {

        return sp.getInt(key, defValue);
    }

    public boolean getBoolean(String key,boolean flag) {

        return sp.getBoolean(key, flag);
    }

    public float getFloat(String key, float defValue) {

        return sp.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {

        return sp.getLong(key, defValue);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

}