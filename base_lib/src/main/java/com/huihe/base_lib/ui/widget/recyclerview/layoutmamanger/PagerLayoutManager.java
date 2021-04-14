package com.huihe.base_lib.ui.widget.recyclerview.layoutmamanger;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.huihe.base_lib.ui.widget.recyclerview.OnPageChangeListener;
import com.huihe.base_lib.utils.LogUtils;

public class PagerLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {

    private static final String TAG = PagerLayoutManager.class.getSimpleName();
    private PagerSnapHelper pagerSnapHelper;
    private boolean haveSelect;
    private int currentPosition;
    //位移，用来判断移动方向
    private int mDrift;

    public void reset() {
        haveSelect = false;
    }

    public PagerLayoutManager(Context context) {
        super(context);
        initView();
    }

    public PagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        initView();
    }

    public PagerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        pagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        pagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        LogUtils.i(TAG, "onChildViewAttachedToWindow == viewId = " + view.getId());
        if (!haveSelect) {
            haveSelect = true;
            currentPosition = getPosition(view);
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageSelected(currentPosition, view);
            }
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        int id = view.getId();
        LogUtils.i(TAG, "onChildViewDetachedFromWindow == viewId = " + id);
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            View snapView = pagerSnapHelper.findSnapView(this);
            if (snapView!=null){
                int position = getPosition(snapView);
                if (currentPosition != position) {
                    currentPosition = position;
                    if (onPageChangeListener != null) {
                        onPageChangeListener.onPageSelected(currentPosition, snapView);
                    }
                }
            }

        }
        super.onScrollStateChanged(state);
    }

    private OnPageChangeListener onPageChangeListener;

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    /**
     * 监听移动方向
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
}
