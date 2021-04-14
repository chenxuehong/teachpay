package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @ desc：
 * 1、修正了RecyclerVew刷新是item闪烁的问题。
 * 出现问题原因：
 * RecyclerView使用StaggeredGridLayoutManager和GridLayoutManager布局管理器动画问题导致item闪烁
 * 解决方法：
 * （1）在adapter被设置给RecyclerView之前给adapter设置  setHasStableIds(true);
 * （2）再重写getItemId方法，如下
 * @Override public long getItemId(int position) {
 * return position;
 * }
 * 2、优化问题：
 * 新添加进去集合的item才会去刷新。
 */
public abstract class EmptyRVAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> mData = new ArrayList<>();

    public List<T> getData() {
        return mData;
    }

    public Context context;
    private int layoutId;
    private int emptyDataLayoutId = -1;

    private boolean isEnable;
    private OnItemClickListener onItemClickListener;

    public EmptyRVAdapter(int layoutId, Context context, List<T> data,OnItemClickListener<T> onItemClickListener) {
        this.layoutId = layoutId;
        this.context = context;
        this.mData = data;
        this.onItemClickListener = onItemClickListener;
        setHasStableIds(true);
        isEnable = false;
    }

    public EmptyRVAdapter(int layoutId, Context context, List<T> data, boolean isEnable) {
        this.layoutId = layoutId;
        this.context = context;
        this.mData = data;
        setHasStableIds(true);
        this.isEnable = isEnable;
    }

    public EmptyRVAdapter(int layoutId, Context context) {
        this.layoutId = layoutId;
        this.context = context;
    }

    public EmptyRVAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        this.layoutId = layoutId;
        this.context = context;
        this.emptyDataLayoutId = emptyDataLayoutId;
    }

    public EmptyRVAdapter(int layoutId, Context context, List<T> data, int emptyDataLayoutId, boolean isEnable) {
        this.layoutId = layoutId;
        this.context = context;
        this.mData = data;
        this.emptyDataLayoutId = emptyDataLayoutId;
        this.isEnable = isEnable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (isEnable)
            return mData == null || mData.size() == 0 ? getViewHolder(context, viewGroup, emptyDataLayoutId != -1 ? emptyDataLayoutId : getEmptyViewLayout()) : getViewHolder(context, viewGroup, layoutId);

        return getViewHolder(context, viewGroup, layoutId);
    }

    private int getEmptyViewLayout() {
        return R.layout.layout_empty_view;
    }

    private ViewHolder getViewHolder(Context context, ViewGroup viewGroup, int layoutId) {

        return new ViewHolder(context, LayoutInflater.from(context).inflate(layoutId, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        if (mData != null && mData.size() != 0) {

            viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(v, mData.get(i),i);
                    }
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemLongClicked(v, mData.get(i),i);
                    }
                    return false;
                }
            });
            T t = mData.get(i);
            convert(viewHolder, t);
            convert(viewHolder, t, i);
        } else {
            convertEmptyView(viewHolder, i);
        }

    }

    protected void convertEmptyView(ViewHolder viewHolder, int i) {

    }

    protected void convert(ViewHolder viewHolder, T t) {
    }

    protected void convert(ViewHolder viewHolder, T t, int position) {
    }

    @Override
    public int getItemCount() {
        return mData == null || mData.size() == 0 ? (isEnable ? 1 : 0) : mData.size();
    }


    public void setOnClickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
    }

    public void addData(List<T> t) {
        isEnable = false;
        if (t != null && t.size() > 0) {

            // 1 2 3
            // 0 1 2

            // 4 5 6
            // 3 4 5
            mData.addAll(t);
            notifyItemRangeChanged(mData.size(), t.size());
        }
    }

    public void addData(int position, T t) {
        isEnable = false;
        if (t != null) {

            // 1 2 3
            // 0 1 2

            // 4 5 6
            // 3 4 5
            mData.add(position, t);
            notifyItemChanged(position);
        }
    }

    public void setData(List<T> t) {
        isEnable = true;
        mData = t;
        notifyDataSetChanged();
    }

    public void setData(boolean isEnable, List<T> t) {
        this.isEnable = isEnable;
        mData = t;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        // 4 5 6
        // 3 4 5
        isEnable = false;
        mData.remove(position);
        notifyDataSetChanged();
    }

    public void removeAll(List<T> removeDataList) {
        // 4 5 6
        // 3 4 5
        isEnable = false;
        mData.removeAll(removeDataList);
        notifyDataSetChanged();
    }

    public void setDataAndNoRefresh(List<T> t) {
        isEnable = true;
        mData = t;
    }

    public void addDataAndNoRefresh(List<T> t) {
        isEnable = false;
        if (t != null && t.size() > 0) {

            // 1 2 3
            // 0 1 2

            // 4 5 6
            // 3 4 5
            mData.addAll(t);
        }
    }

    public interface OnItemClickListener<T> {

        void onItemClicked(View v, T t, int position);

        void onItemLongClicked(View v, T t, int position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
