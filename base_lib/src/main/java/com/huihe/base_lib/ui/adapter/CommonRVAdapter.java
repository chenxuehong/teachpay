package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.EventUtils;

import java.util.List;

public abstract class CommonRVAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private int layoutId;
    public Context context;
    protected List<T> mDatas;

    public List<T> getData() {
        return mDatas;
    }

    public void setData(List<T> datas) {
        mDatas = datas;
        while (maxShowNum != -1 && mDatas.size() > maxShowNum) {
            mDatas.remove(mDatas.size() - 1);
        }
        notifyDataSetChanged();
    }

    public void addData(List<T> datas) {
        if (datas != null && datas.size() > 0) {

            // 1 2 3
            // 0 1 2

            // 4 5 6
            // 3 4 5
            if (maxShowNum == -1) {
                mDatas.addAll(datas);
            } else {
                if (maxShowNum < mDatas.size()) {
                    mDatas.addAll(datas);
                }
            }
            notifyItemRangeChanged(mDatas.size(), datas.size());
        }
    }

    public void addData(T t, int position) {
        if (t != null) {

            // 1 2 3
            // 0 1 2

            // 4 5 6
            // 3 4 5
            if (maxShowNum == -1) {
                mDatas.add(position, t);
            } else {
                if (maxShowNum < mDatas.size()) {
                    mDatas.add(position, t);
                }
            }
            notifyItemRangeChanged(position, 1);
        }
    }

    public void addData(List<T> datas, int position) {
        if (datas != null && datas.size() > 0) {

            // 1 2 3
            // 0 1 2

            // 4 5 6
            // 3 4 5
            if (maxShowNum == -1) {
                mDatas.addAll(position, datas);
            } else {
                if (maxShowNum < mDatas.size()) {
                    mDatas.addAll(position, datas);
                }
            }
            notifyItemRangeChanged(position, datas.size());
        }
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public CommonRVAdapter(int layoutId, Context context, List<T> data) {
        this.layoutId = layoutId;
        this.context = context;
        this.mDatas = data;
        maxShowNum = -1;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = new ViewHolder(context, LayoutInflater.from(context).inflate(layoutId, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        final T t = mDatas.get(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    if (!EventUtils.isFastDoubleClick(v))
                        listener.onItemClicked(v, t, i);
                }

            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (listener != null) {
                    if (!EventUtils.isFastDoubleClick(v))
                        listener.onItemLongClicked(v, t, i);
                }
                return false;
            }
        });
        covert(viewHolder, t);
        covert(viewHolder, t, i);

    }

    //清除数据
    public void clearData() {
        if (getData() != null) {
            getData().clear();
        }
    }

    protected void covert(ViewHolder viewHolder, T t) {
    }

    protected void covert(ViewHolder viewHolder, T t, int position) {
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private int maxShowNum = -1;

    public void setMaxShowNum(int maxShowNum) {
        this.maxShowNum = maxShowNum;
    }

    public interface OnItemClickListener<T> {
        void onItemClicked(View v, T t, int i);

        void onItemLongClicked(View v, T t, int i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

