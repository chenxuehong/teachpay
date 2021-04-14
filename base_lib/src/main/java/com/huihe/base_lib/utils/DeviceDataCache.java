package com.huihe.base_lib.utils;

import android.content.Context;

import java.io.File;
import java.util.Date;

/**
 * author : xuchunrong
 * date : 2019/5/8 14:59
 * description : 请用一句话概括功能
 */
public class DeviceDataCache {
    public final static String CACHE_DEVICE_DATA = "cache_device_data";
    private static DeviceDataCache instance;
    private static Context context;

    private DeviceDataCache(Context context) {
        this.context = context;
    }

    public static DeviceDataCache getInstance(Context context){
        if(instance == null){
            synchronized (DeviceDataCache.class){
                if(instance == null){
                    instance = new DeviceDataCache(context);
                }
            }
        }
        return instance;
    }

    /**
     * 存
     * @param key
     * @param value
     * @param dataTimestamp 该条数据所属时间
     */
    public void putString(String key, String value, long dataTimestamp){
        dataTimestamp = formatTimestamp(dataTimestamp);
        ACache aCache = ACache.get(context, CACHE_DEVICE_DATA + File.separator + dataTimestamp);
        aCache.put(key, value);
    }

    /**
     * 取
     * @param key
     * @param dataTimestamp 该条数据所属时间
     * @return
     */
    public String getString(String key, long dataTimestamp){
        dataTimestamp = formatTimestamp(dataTimestamp);
        ACache aCache = ACache.get(context, CACHE_DEVICE_DATA + File.separator + dataTimestamp);
        return aCache.getAsString(key);
    }

    /**
     * 删除一项
     * @param key
     * @param dataTimestamp 该条数据所属时间
     */
    public void remove(String key, long dataTimestamp){
        dataTimestamp = formatTimestamp(dataTimestamp);
        ACache aCache = ACache.get(context, CACHE_DEVICE_DATA + File.separator + dataTimestamp);
        aCache.remove(key);
    }

    /**
     * 清理多少天前的数据
     * @param timestamp 开始计数的时间戳
     * @param days 该时间戳前多少天
     */
    public void clearCacheByEndTime(long timestamp, int days){
        timestamp -= (days + 1) * 24 * 60 * 60 * 1000;
        File file = new File(context.getCacheDir(), CACHE_DEVICE_DATA);
        if(file.exists()){
            String[] fileNames = file.list();
            for(String fileName : fileNames){
                try{
                    long fileTimestamp = Long.valueOf(fileName);
                    if(fileTimestamp < timestamp){
                        clearCacheByTimestamp(fileTimestamp);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearCacheByTimestamp(long dataTimestamp){
        dataTimestamp = formatTimestamp(dataTimestamp);
        File file = new File(context.getCacheDir() + File.separator + CACHE_DEVICE_DATA, String.valueOf(dataTimestamp));
        if(file.exists()){
            deleteFiles(file);
        }
    }

    private void deleteFiles(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File child : files){
                deleteFiles(child);
            }
        }
        file.delete();
    }

    private long formatTimestamp(long timestamp){
        Date date = new Date(timestamp);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date.getTime() / 100000 * 100000;
    }

}
