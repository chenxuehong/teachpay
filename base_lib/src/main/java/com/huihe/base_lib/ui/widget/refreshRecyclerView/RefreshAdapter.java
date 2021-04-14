package com.huihe.base_lib.ui.widget.refreshRecyclerView;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public abstract class RefreshAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private int mLayoutResId;
    private List<T> mData;
    private View mRefreshHeaderView;
    private View mLoadMoreFooterView;

    private boolean enableRefresh;
    private boolean enableLoadMore;

    public void setRefreshHeaderView(View mRefreshHeaderView) {
        this.mRefreshHeaderView = mRefreshHeaderView;
    }

    public void setLoadMoreFooterView(View mLoadMoreFooterView) {
        this.mLoadMoreFooterView = mLoadMoreFooterView;
    }

    public View getRefreshHeaderView() {
        return mRefreshHeaderView;
    }

    public View getLoadMoreFooterView() {
        return mLoadMoreFooterView;
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
    }

    public boolean isEnableRefresh() {

        return enableRefresh;
    }

    public boolean isEnableLoadMore() {

        return enableLoadMore;
    }

    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
    }

    public RefreshAdapter(Context context, int nLayoutResId, List<T> mData) {
        this.context = context;
        this.mLayoutResId = nLayoutResId;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {

        if (isEnableRefresh() && position == 0) {

            return ViewHolderType.TYPE_REFRESH_HEADER;
        }
        if (isEnableLoadMore() && position == getItemCount() - 1) {

            return ViewHolderType.TYPE_LOAD_MORE_FOOTER;
        }

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == ViewHolderType.TYPE_REFRESH_HEADER) {

            return new ViewHolder(context, getRefreshHeaderView());
        } else if (itemViewType == ViewHolderType.TYPE_LOAD_MORE_FOOTER) {
            return new ViewHolder(context, getLoadMoreFooterView());
        } else {
            return new ViewHolder(context, inflateView(mLayoutResId, viewGroup));
        }
    }

    private View inflateView(int mLayoutResId, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(mLayoutResId, viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (mData != null) {

            T t = mData.get(i);
            convert(viewHolder, t, i);
        }
    }

    protected abstract void convert(ViewHolder viewHolder, T t, int i);

    @Override
    public int getItemCount() {
        int count = 0;
        if (isEnableRefresh()) {
            count++;
        }
        if (isEnableLoadMore()) {
            count++;
        }

        return mData != null ? mData.size() + count : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isLoadMoreFooter(position) || isRefreshHeader(position))
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    private boolean isRefreshHeader(int position) {
        return isEnableRefresh() && position == getItemCount() - 1;
    }

    private boolean isLoadMoreFooter(int position) {
        return isEnableLoadMore() && position == 0;
    }

}
