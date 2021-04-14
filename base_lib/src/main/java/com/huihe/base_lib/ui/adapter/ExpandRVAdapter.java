package com.huihe.base_lib.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ExpandRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mRealAdpter;
    private List<View> mHanders = new ArrayList<>();
    private List<View> mFoots = new ArrayList<>();

    public ExpandRVAdapter(RecyclerView.Adapter realAdpter) {
        this.mRealAdpter = realAdpter;
    }

    /**
     * 由于下面需要获取ViewHolder，那肯定是需要当前位置，但是onCreateViewHolder唯一与position有关的就是viewType
     * 所以我们需要复写getItemViewType,让其返回position而不是type
     *
     * @param parent
     * @param position
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        //如果是头部就返回头部的ViewHolder
        int handersNumber = mHanders.size();
        if (position < handersNumber) {
            return new RecyclerView.ViewHolder(mHanders.get(position)) {
            };
        }
        //如果是正常条目就返回正常条目的ViewHolder
        //获取正常条目位置
        int adjPosition = position - handersNumber;
        int mRealAdpterItemCount = 0;
        if (mRealAdpter != null) {
            mRealAdpterItemCount = mRealAdpter.getItemCount();
            if (adjPosition < mRealAdpterItemCount) {
                //这里的第二个参数还是要传type，不然无法适配多布局
                return mRealAdpter.onCreateViewHolder(parent, mRealAdpter.getItemViewType(adjPosition));
            }
        }
        //如果是底部就返回底部的ViewHolder
        return new RecyclerView.ViewHolder(mFoots.get(adjPosition - mRealAdpterItemCount)) {
        };
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //如果是头部就不要绑定数据
        int handersNumber = mHanders.size();
        if (position < handersNumber) {
            return;
        }
        //如果是正常条目就返回正常条目的ViewHolder
        //获取正常条目位置
        int adjPosition = position - handersNumber;
        int mRealAdpterItemCount = 0;
        if (mRealAdpter != null) {
            mRealAdpterItemCount = mRealAdpter.getItemCount();
            if (adjPosition < mRealAdpterItemCount) {
                //这里的第二个参数还是要传type，不然无法适配多布局
                mRealAdpter.onBindViewHolder(holder, adjPosition);
            }
        }
    }

    @Override
    public int getItemCount() {
        //数量=正常条目数+头部数+底部数
        return mRealAdpter.getItemCount() + mHanders.size() + mFoots.size();
    }

    private RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    if (i < mHanders.size()) {
                        return gridLayoutManager.getSpanCount();
                    } else if (i > mRealAdpter.getItemCount() + mHanders.size() - 1) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public void showEmptyDataLayoutManager(final boolean showEmpty) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    if (showEmpty) {
                        return gridLayoutManager.getSpanCount();
                    } else {
                        if (i < mHanders.size()) {
                            return gridLayoutManager.getSpanCount();
                        } else if (i > mRealAdpter.getItemCount() + mHanders.size() - 1) {
                            return gridLayoutManager.getSpanCount();
                        }
                        return 1;
                    }
                }
            });
        }
    }

    /**
     * 添加头部
     *
     * @param view
     */
    public void addHanderView(View view) {
        if (!mHanders.contains(view)) {
            mHanders.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部
     *
     * @param view
     */
    public void addFootView(View view) {
        if (!mFoots.contains(view)) {
            mFoots.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除头部
     *
     * @param view
     */
    public void removeHanderView(View view) {
        if (mHanders.contains(view)) {
            mHanders.remove(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除底部
     *
     * @param view
     */
    public void removeFootView(View view) {
        if (mFoots.contains(view)) {
            mFoots.remove(view);
            notifyDataSetChanged();
        }
    }
}
