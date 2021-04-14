package com.huihe.base_lib.utils;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataStatusHelper {
    private List<RecyclerView.Adapter> adapters = new ArrayList<>();
    private List<String> datas = new ArrayList<>();

    public List<String> getDatas() {
        return datas;
    }

    public List<RecyclerView.Adapter> getAdapters() {
        return adapters;
    }

    private static DataStatusHelper dataStatusHelper;

    public static DataStatusHelper getInstance() {
        if (dataStatusHelper == null) {
            synchronized (DataStatusHelper.class) {
                if (dataStatusHelper == null) {
                    dataStatusHelper = new DataStatusHelper();
                }
            }
        }
        return dataStatusHelper;
    }

    public void addAdapter(RecyclerView.Adapter adapter) {
        if (!adapters.contains(adapter))
            adapters.add(adapter);
    }

    public void addData(String data) {
        if (!datas.contains(data))
            datas.add(data);
    }

    public void clearData(){
        datas.clear();
    }

    public void clear(){
        clearData();
        adapters.clear();
    }
}
