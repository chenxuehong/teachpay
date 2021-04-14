package com.huihe.base_lib.ui.adapter;

import android.content.Context;

import java.util.List;

public abstract class LuckyCodeAdapter<T> extends CommonRVAdapter<T> {

    private boolean isHide;

    public boolean isHide() {
        return isHide;
    }

    public LuckyCodeAdapter(int layoutId, Context context, List<T> data) {
        super(layoutId, context, data);
    }

    //隐藏
    public void setHideList(List<T> newList) {
        setData(newList);
        notifyDataSetChanged();
        this.isHide = true;
    }

    //展开
    public void setOpenList(List<T> openList) {
        setData(openList);
        notifyDataSetChanged();
        this.isHide = false;
    }

    //不需要隐藏/展开
    public void setRealList(List<T> realList) {
        setData(realList);
        this.isHide = false;
    }

}
