package com.huihe.base_lib.ui.widget.fitViewPager;

import android.content.Context;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 将上下滑动事件交给父控件处理
 */
public class NoVerticalScrollViewPager extends AutoFitViewPager {

    PointF downP = new PointF();
    PointF curP = new PointF();
    private float xDown;// 记录手指按下时的横坐标。
    private float xMove;// 记录手指移动时的横坐标。
    private float yDown;// 记录手指按下时的纵坐标。

    public NoVerticalScrollViewPager(@NonNull Context context) {
        this(context,null);
    }

    public NoVerticalScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 每次进行onTouch事件都记录当前的按下的坐标
        curP.x = ev.getX();
        curP.y = ev.getY();
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            downP.x = ev.getX();
            downP.y = ev.getY();

            xDown = ev.getX();
            yDown = ev.getY();

            // 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        //移动的时候进行判断
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {

            xMove = ev.getX();
            float yMove = ev.getY();
            // 这里判断是横向还是纵向移动，
            if (Math.abs(yMove - yDown) < Math.abs(xMove - xDown) && Math.abs(xMove - xDown) > 2) {
                // 横向滑动的处理
                if (Math.abs(xMove - xDown) > 2) {
                    // 左右滑动的时候进行拦截，自己处理
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    // 自己进行处理，不在上传给父布局
                    return false;
                }
            } else {
                // 父布局进行事件拦截
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onTouchEvent(ev);
    }
}
