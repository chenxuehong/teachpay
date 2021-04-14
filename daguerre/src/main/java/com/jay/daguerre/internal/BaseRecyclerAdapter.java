package com.jay.daguerre.internal;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by jay on 2017/11/23 下午4:14
 */
abstract class BaseRecyclerAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    final Context mContext;
    private List<D> mDatas;
    LayoutInflater mLayoutInflater;


    BaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    BaseRecyclerAdapter(Context context, List<D> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setData(List<D> datas) {
        mDatas = datas;
    }

    D getItem(int position) {
        return mDatas.get(position);
    }

}
