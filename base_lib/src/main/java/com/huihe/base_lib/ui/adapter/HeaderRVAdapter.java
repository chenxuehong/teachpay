package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public abstract class HeaderRVAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private static final int TYPE_VIEW_HEAD = 100;
    public Context context;
    private int headLayoutId;
    private int realLayoutId;
    private List<T> mData;

    public HeaderRVAdapter(Context context, int headLayoutId, int realLayoutId, List<T> mData) {
        this.context = context;
        this.headLayoutId = headLayoutId;
        this.realLayoutId = realLayoutId;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_VIEW_HEAD;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    if (getItemViewType(i)==TYPE_VIEW_HEAD){

                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (getItemViewType(i) == TYPE_VIEW_HEAD) {
            return ViewHolder.createViewHolder(context,viewGroup,headLayoutId);
        } else {
            return ViewHolder.createViewHolder(context,viewGroup,realLayoutId);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (getItemViewType(i) == TYPE_VIEW_HEAD) {

            initHeadView(viewHolder, i);
        } else {
            T t = mData.get(i - 1);

            convert(t, viewHolder, i-1);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listner!=null){
                        listner.onItemClick();
                    }
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listner!=null){
                        listner.onItemLongClick();
                    }
                    return false;
                }
            });
        }
    }

    protected abstract void convert(T t, ViewHolder viewHolder, int i);

    protected abstract void initHeadView(ViewHolder viewHolder, int i);

    @Override
    public int getItemCount() {
        return 1 + (mData != null ? mData.size() : 0);
    }

    private OnItemClickListner listner;

    public void setOnItemClickListner(OnItemClickListner listner) {
        this.listner = listner;
    }

    public interface OnItemClickListner {
        void onItemClick();
        void onItemLongClick();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
