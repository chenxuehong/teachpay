package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class PageGridViewAdapter<T> extends BaseAdapter {

    private List<T> mDatas;
    public Context context;

    public List<T> getDatas() {
        return mDatas;
    }

    /**
     * 页数下标,从0开始
     */
    public int mIndex;

    /**
     * 每页显示最大条目个数
     */
    public final int mPageSize;

    public PageGridViewAdapter(Context context, List<T> mDatas, int mIndex, int mPageSize) {
        this.context = context;
        this.mDatas = mDatas;
        this.mIndex = mIndex;
        this.mPageSize = mPageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (mIndex+1)*mPageSize,
     * 如果够，则直接返回每一页显示的最大条目个数mPageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - mIndex * mPageSize);
     */
    @Override
    public int getCount() {
        return mDatas.size() > (mIndex + 1) * mPageSize ? mPageSize : (mDatas.size() - mIndex * mPageSize);
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }

}
