package com.huihe.base_lib.utils.manager;

import android.text.TextUtils;

import com.huihe.base_lib.utils.SPUtils;

import java.lang.reflect.Type;
import java.util.List;

public class DataCacheManager {

    public void putString(String key, String value) {
        if(value != null && !TextUtils.isEmpty(key)) {
            SPUtils.getIntance().putString(key,value);
        }
    }
    public void putInt(String key, int value) {
        if(key != null) {
            SPUtils.getIntance().putInt(key,value);
        }
    }
    public void putBoolean(String key, boolean value) {
        if(key != null && !TextUtils.isEmpty(key)) {
            SPUtils.getIntance().putBoolean(key,value);
        }
    }
    public void putLong(String key, long value) {
        if(key != null && !TextUtils.isEmpty(key)) {
            SPUtils.getIntance().putLong(key,value);
        }
    }
    public long getLong(String key) {
        if(key != null && !TextUtils.isEmpty(key)) {
            return SPUtils.getIntance().getLong(key,-1);
        }
        return -1;
    }
    public boolean getBoolean(String key) {
        if(key != null && !TextUtils.isEmpty(key)) {
            return SPUtils.getIntance().getBoolean(key,false);
        }
        return false;
    }
    public boolean getBoolean(String key,boolean defaultValue) {
        if(key != null && !TextUtils.isEmpty(key)) {
            return SPUtils.getIntance().getBoolean(key,defaultValue);
        }
        return false;
    }

    public String getString(String key) {
        if(!TextUtils.isEmpty(key)) {
           return SPUtils.getIntance().getString(key,"");
        }
        return "";
    }

    public int getInt(String key,int defValue) {
        if(!TextUtils.isEmpty(key)) {
           return SPUtils.getIntance().getInt(key,defValue);
        }
        return defValue;
    }

    public static class Holder{
        private static DataCacheManager instance = new DataCacheManager();
    }

    public static DataCacheManager getInstance(){
        return Holder.instance;
    }

    
    public void putJsonModel(String key, Object model){
        if(model != null && !TextUtils.isEmpty(key)) {
            try{
                SPUtils.getIntance().putObject(key,model);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public <T> void putJsonList(String key, List<T> list) {
        if (list!=null&& !TextUtils.isEmpty(key)){
            try{
                SPUtils.getIntance().putList(key,list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public <T> T getJsonModel(String key, Class<T> type){
        try{
            return SPUtils.getIntance().getObject(key,type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getJsonList(String key, Type typeOfT){
        try{
            return SPUtils.getIntance().getList(key,typeOfT);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void clear(){
        SPUtils.getIntance().clear();
    }
    public void remove(String key){
        SPUtils.getIntance().remove(key);
    }
}
