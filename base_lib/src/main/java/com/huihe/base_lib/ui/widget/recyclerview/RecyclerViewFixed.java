package com.huihe.base_lib.ui.widget.recyclerview;

import android.content.Context;
import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewDivider.GridViewItemDecoration;
import com.huihe.base_lib.ui.widget.recyclerview.layoutmamanger.MyLinearLayoutManager;


public class RecyclerViewFixed extends RecyclerView {

    private Context context;
    boolean isShowTitleBar = true;
    private int disy;

    public RecyclerViewFixed(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerViewFixed(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
        isShowTitleBar = true;
    }

    public RecyclerViewFixed(@NonNull final Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    GlideTools.resumeRequest(context);
//                } else {
//                    GlideTools.pauseRequest(context);
//                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                int firstVisibleItem = 0;
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                    //根据索引来获取对应的itemView
                    View firstVisiableChildView = linearLayoutManager
                            .findViewByPosition(firstVisibleItem);
                    if (firstVisibleItem == 0) {
                        int top = firstVisiableChildView.getTop();
                        if (!isShowTitleBar && top <= 0) {
                            if (onTitleBarListener != null) {
                                onTitleBarListener.showToolBar();
                            }
                            isShowTitleBar = true;
                        } else if (isShowTitleBar && top > 0) {
                            if (onTitleBarListener != null) {
                                onTitleBarListener.hideToolBar();
                            }
                            isShowTitleBar = false;
                        }

                        if (top >= -180) {
                            // 滑动改变颜色状态
                            if (onTitleBarListener != null) {
                                float alpha = (180 - Math.abs(top)) * 1f / 180;
                                onTitleBarListener.scrollChangeStatus(alpha);
                            }
                        }
                    } else {
                        if (onTitleBarListener != null) {
                            float alpha = 0;
                            onTitleBarListener.scrollChangeStatus(alpha);
                        }
                    }

                }

            }
        });

    }

    private OnTitleBarListener onTitleBarListener;

    public void setOnTitleBarListener(OnTitleBarListener onTitleBarListener) {
        this.onTitleBarListener = onTitleBarListener;
    }

    public interface OnTitleBarListener {
        void showToolBar();

        void hideToolBar();

        void scrollChangeStatus(float alpa);
    }

    public RecyclerViewFixed addGridViewItemDecoration(int column, int space) {
        int itemDecorationCount = getItemDecorationCount();
        if (itemDecorationCount == 0)
            addItemDecoration(new GridViewItemDecoration(column, space, 0));
        return this;
    }

    public RecyclerViewFixed addGridViewItemDecoration(int column, int space, int reduceColumn) {
        int itemDecorationCount = getItemDecorationCount();
        if (itemDecorationCount == 0)
            addItemDecoration(new GridViewItemDecoration(column, space, reduceColumn));
        return this;
    }

    public RecyclerViewFixed addGridViewItemDecoration(int column, int left, int right, int top, int bottom, int reduceColumn) {
        int itemDecorationCount = getItemDecorationCount();
        if (itemDecorationCount == 0)
            addItemDecoration(new GridViewItemDecoration(column, left, right, top, bottom, reduceColumn));
        return this;
    }

    public RecyclerViewFixed addGridViewItemDecoration(int column, int left, int right, int top, int bottom) {
        int itemDecorationCount = getItemDecorationCount();
        if (itemDecorationCount == 0)
            addItemDecoration(new GridViewItemDecoration(column, left, right, top, bottom, 0));
        return this;
    }

    public RecyclerViewFixed setVertical(int spanCount) {

        if (spanCount > 1) {
            setLayoutManager(new GridLayoutManager(context, spanCount));
        } else {
            setLayoutManager(new MyLinearLayoutManager(context));
        }
        return this;
    }

    public RecyclerViewFixed setScrollingEnabled(boolean enable) {

        setNestedScrollingEnabled(enable);
        return this;
    }

    public RecyclerViewFixed setHorizontal() {
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        return this;
    }

    private OnScrollToTopListener onScrollToTopListener;

    public interface OnScrollToTopListener {
        void show();

        void hide();
    }

    public void setOnScrollToTopListener(OnScrollToTopListener onScrollToTopListener) {
        this.onScrollToTopListener = onScrollToTopListener;
    }

    public interface OnPinnedHeaderClickListener {

        void onPinnedHeaderClick(int adapterPosition);
    }

    private OnPinnedHeaderClickListener mPinnedHeaderClickListener;

    public void setOnPinnedHeaderClickListener(OnPinnedHeaderClickListener listener) {
        mPinnedHeaderClickListener = listener;
    }

    private boolean mPinnedHeaderHandle;


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (mPinnedHeaderClickListener == null) {
            return super.onInterceptTouchEvent(e);
        }
        IPinnedHeaderDecoration pinnedHeaderInterface = getPinnedHeaderDecoration();
        if (pinnedHeaderInterface == null) {
            return super.onInterceptTouchEvent(e);
        }
        Rect pinnedHeaderRect = pinnedHeaderInterface.getPinnedHeaderRect();
        int pinnedHeaderPosition = pinnedHeaderInterface.getPinnedHeaderPosition();
        if (pinnedHeaderRect == null || pinnedHeaderPosition == -1) {
            return super.onInterceptTouchEvent(e);
        }
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (pinnedHeaderRect.contains((int) e.getX(), (int) e.getY())) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(e);
    }


    /**
     * 如果有固定的header的情况
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mPinnedHeaderClickListener == null) {
            return super.onTouchEvent(ev);
        }
        IPinnedHeaderDecoration pinnedHeaderInterface = getPinnedHeaderDecoration();
        if (pinnedHeaderInterface == null) {
            return super.onTouchEvent(ev);
        }
        Rect pinnedHeaderRect = pinnedHeaderInterface.getPinnedHeaderRect();
        int pinnedHeaderPosition = pinnedHeaderInterface.getPinnedHeaderPosition();
        if (pinnedHeaderRect == null || pinnedHeaderPosition == -1) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPinnedHeaderHandle = false;
                if (pinnedHeaderRect.contains((int) ev.getX(), (int) ev.getY())) {
                    mPinnedHeaderHandle = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mPinnedHeaderHandle) {
                    if (!pinnedHeaderRect.contains((int) ev.getX(), (int) ev.getY())) {
                        MotionEvent cancel = MotionEvent.obtain(ev);
                        cancel.setAction(MotionEvent.ACTION_CANCEL);
                        super.dispatchTouchEvent(cancel);

                        MotionEvent down = MotionEvent.obtain(ev);
                        down.setAction(MotionEvent.ACTION_DOWN);
                        return super.dispatchTouchEvent(down);
                    } else {
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                float x = ev.getX();
                float y = ev.getY();
                // 如果 HeaderView 是可见的 , 点击在 HeaderView 内 , 那么触发pinned header 点击
                if (mPinnedHeaderHandle && pinnedHeaderRect.contains((int) x, (int) y)) {
                    mPinnedHeaderClickListener.onPinnedHeaderClick(pinnedHeaderPosition);
                    mPinnedHeaderHandle = false;
                    return true;
                }
                mPinnedHeaderHandle = false;
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public IPinnedHeaderDecoration getPinnedHeaderDecoration() {
        int decorationIndex = 0;
        ItemDecoration itemDecoration;
        do {
            itemDecoration = getItemDecorationAt(decorationIndex);
            if (itemDecoration instanceof IPinnedHeaderDecoration) {
                return (IPinnedHeaderDecoration) itemDecoration;
            }
            decorationIndex++;
        } while (itemDecoration != null);
        return null;
    }
}
