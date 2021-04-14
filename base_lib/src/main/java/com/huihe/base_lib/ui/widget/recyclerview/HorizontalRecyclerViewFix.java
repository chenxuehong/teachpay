package com.huihe.base_lib.ui.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewDivider.GridViewItemDecoration;
import com.huihe.base_lib.ui.widget.recyclerview.layoutmamanger.MyLinearLayoutManager;

public class HorizontalRecyclerViewFix extends RecyclerView {

    private Context context;
    private int lastX;
    private int lastY;
    public HorizontalRecyclerViewFix(@NonNull Context context) {
        this(context,null);
    }

    public HorizontalRecyclerViewFix(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalRecyclerViewFix(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public HorizontalRecyclerViewFix setScrollingEnabled(boolean enable) {

        setNestedScrollingEnabled(enable);
        return this;
    }

    public HorizontalRecyclerViewFix setHorizontal() {
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        return this;
    }

    public HorizontalRecyclerViewFix addGridViewItemDecoration(int column, int space) {
        int itemDecorationCount = getItemDecorationCount();
        if (itemDecorationCount == 0)
            addItemDecoration(new GridViewItemDecoration(column, space, 0));
        return this;
    }

    public HorizontalRecyclerViewFix setVertical(int spanCount) {

        if (spanCount > 1) {
            setLayoutManager(new GridLayoutManager(context, spanCount));
        } else {
            setLayoutManager(new MyLinearLayoutManager(context));
        }
        return this;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        /*--------解决垂直RecyclerView嵌套水平RecyclerView横向滑动不流畅问题 start --------*/
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        int dealtX = 0;
        int dealtY = 0;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dealtX = 0;
                dealtY = 0;
                // 保证子View能够接收到Action_move事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                dealtX += Math.abs(x - lastX);
                dealtY += Math.abs(y - lastY);
                // Log.i("dispatchTouchEvent", "dealtX:=" + dealtX);
                // Log.i("dispatchTouchEvent", "dealtY:=" + dealtY);
                // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (dealtX >= dealtY) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        /*--------解决垂直RecyclerView嵌套水平RecyclerView横向滑动不流畅问题 end --------*/

        return super.dispatchTouchEvent(ev);
    }
}
