package com.huihe.base_lib.ui.fragment;




import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewDivider.GridViewItemDecoration;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;

public abstract class BaseRVFragment<P extends RecyclerView.Adapter> extends BaseFragment {

    public RecyclerViewFixed recyclerViewFixed;
    public P adapter;

    @Override
    protected void initView() {

        recyclerViewFixed = mContainer.findViewById(R.id.base_vp_rv);
        int spanCount = getSpanCount();
        vertical(spanCount);
        addItemDecoration(spanCount,getSpace());
    }

    protected abstract int getSpace();

    protected abstract int getSpanCount();

    private void vertical(int spanCount) {
        if (spanCount > 1) {
            recyclerViewFixed.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        } else {
            recyclerViewFixed.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    private void addItemDecoration(int column, int space) {
        recyclerViewFixed.addItemDecoration(new GridViewItemDecoration(column, space, 0));
    }

    public void initAdapter() {

        adapter = getAdapter();
        recyclerViewFixed.setAdapter(adapter);
    }

    protected abstract P getAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_vp;
    }
}
