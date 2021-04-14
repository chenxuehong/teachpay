package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class PinnedHeaderAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = PinnedHeaderAdapter.class.getSimpleName();
    private List<T> mData = new ArrayList<>();

    public List<T> getData() {
        return mData;
    }

    public Context context;
    private int layoutId;
    private int emptyDataLayoutId = -1;

    private boolean isEnable;

    public PinnedHeaderAdapter(int layoutId, Context context, List<T> data) {
        this.layoutId = layoutId;
        this.context = context;
        this.mData = data;
        setHasStableIds(true);
        isEnable = false;
    }

    public PinnedHeaderAdapter(int layoutId, Context context, List<T> data, boolean isEnable) {
        this.layoutId = layoutId;
        this.context = context;
        this.mData = data;
        setHasStableIds(true);
        this.isEnable = isEnable;
    }

    public PinnedHeaderAdapter(int layoutId, Context context) {
        this.layoutId = layoutId;
        this.context = context;
    }

    public PinnedHeaderAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        this.layoutId = layoutId;
        this.context = context;
        this.emptyDataLayoutId = emptyDataLayoutId;
    }

    public PinnedHeaderAdapter(int layoutId, Context context, List<T> data, int emptyDataLayoutId, boolean isEnable) {
        this.layoutId = layoutId;
        this.context = context;
        this.mData = data;
        this.emptyDataLayoutId = emptyDataLayoutId;
        this.isEnable = isEnable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (isEnable) {
            if (mData == null || mData.size() == 0) {
                return getViewHolder(context, viewGroup, emptyDataLayoutId != -1 ? emptyDataLayoutId : getEmptyViewLayout());
            } else {
                LogUtils.i(TAG,"i = " + i);
                LogUtils.i(TAG,"isPinnedPosition(i) = " + isPinnedPosition(i));
                LogUtils.i(TAG,"getViewHolder");
                return getViewHolder(context, viewGroup,layoutId);

            }
        } else {
            return getViewHolder(context, viewGroup,layoutId);
        }
    }

    private int getEmptyViewLayout() {
        return R.layout.layout_empty_view;
    }

    public ViewHolder getViewHolder(Context context, ViewGroup viewGroup, int layoutId) {

        return new ViewHolder(context, LayoutInflater.from(context).inflate(layoutId, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        if (mData != null && mData.size() != 0) {

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        listener.onItemClicked(v, mData.get(i),i);
                    }
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onItemLongClicked(v, mData.get(i),i);
                    }
                    return false;
                }
            });
            T t = mData.get(i);
            convert(viewHolder, t, i);

        } else {
            convertEmptyView(viewHolder, i);
        }

    }

    protected void convertEmptyView(ViewHolder viewHolder, int i) {

    }

    protected void convert(ViewHolder viewHolder, T t, int position) {

    }

    @Override
    public int getItemCount() {
        return mData == null || mData.size() == 0 ? (isEnable ? 1 : 0) : mData.size();
    }

    public void setOnClickListener( OnItemClickListener listener) {

        this.listener = listener;
    }

    private OnItemClickListener listener;


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

    public void remove(int position) {
        // 4 5 6
        // 3 4 5
        isEnable = false;
        mData.remove(position);
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

    /**
     * 判断该position对应的位置是要固定
     *
     * @param position adapter position
     * @return true or false
     */
    public abstract boolean isPinnedPosition(int position);

    public void hideView(View pinnedHeaderView){

    }
}
