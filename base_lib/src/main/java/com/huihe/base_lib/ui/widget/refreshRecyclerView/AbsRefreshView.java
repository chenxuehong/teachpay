package com.huihe.base_lib.ui.widget.refreshRecyclerView;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;


public abstract class AbsRefreshView extends RecyclerView {

    private RefreshAdapter adapter;
    private float downY;

    public AbsRefreshView(@NonNull Context context) {
        this(context, null);
    }

    public AbsRefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsRefreshView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(RefreshAdapter adapter) {
        this.adapter = adapter;
        super.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = e.getY();
                int disY = (int) (moveY - downY);
                if (adapter.isEnableRefresh()) {
                    moveHeaderView(disY);
                    if (getRefreshHeaderViewPaddingTop() > getRefreshHeaderViewHeight() / 2) {

                        // 显示释放刷新试图
                        updateRefreshHeaderState(ViewHolderType.TYPE_REFRESH_RELEASE);
                    } else {
                        // 显示下拉刷新试图
                        updateRefreshHeaderState(ViewHolderType.TYPE_REFRESH_PULL_DOWN);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                int upY = (int) (e.getY() - downY);
                if (adapter.isEnableRefresh()) {

                }

                break;
        }
        return super.onTouchEvent(e);
    }

    private void updateRefreshHeaderState(int typeRefresh) {

        switch (typeRefresh) {
            case ViewHolderType.TYPE_REFRESH_RELEASE:
                showHeaderReleaseView();
                break;
            case ViewHolderType.TYPE_REFRESH_PULL_DOWN:
                showHeaderPullView();
                break;
        }
    }

    private void showHeaderPullView() {

    }

    private void showHeaderReleaseView() {

    }

    private int getRefreshHeaderViewPaddingTop() {
        return adapter.getRefreshHeaderView().getPaddingTop();
    }

    private void moveHeaderView(int disY) {
        if (adapter != null) {
            adapter.getRefreshHeaderView().setPadding(0, -getRefreshHeaderViewHeight() + disY, 0, disY);
        }
    }

    private int getRefreshHeaderViewHeight() {
        return adapter.getRefreshHeaderView().getMeasuredHeight();
    }

    private OnRefreshListener listener;

    public OnRefreshListener getOnRefreshListener() {
        return listener;
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

}
