package com.huihe.base_lib.ui.widget.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @desc 顶部透明渐变列表控件
 * 使用步骤：
 * （1）布局中设置
 * <androidx.recyclerview.widget.RecyclerView
 * android:id="@+id/rv_list"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:requiresFadingEdge="vertical"
 * android:fadingEdgeLength="40dp"/>
 */
public class FadingEdgeRecyclerView extends RecyclerViewFixed {
    public FadingEdgeRecyclerView(@NonNull Context context) {
        super(context);
    }

    public FadingEdgeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FadingEdgeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重写这个方法，返回值是0,去掉顶部阴影
     *
     * @return
     */
    @Override
    protected float getTopFadingEdgeStrength() {
        return super.getTopFadingEdgeStrength();
    }

    /**
     * 重写这个方法，返回值是0,去底顶部阴影
     *
     * @return
     */
    @Override
    protected float getBottomFadingEdgeStrength() {
        //return super.getBottomFadingEdgeStrength();
        return 0;
    }

    /**
     * 重写这个方法，返回值是0,去左顶部阴影
     *
     * @return
     */
    @Override
    protected float getLeftFadingEdgeStrength() {
        return 0;
    }

    /**
     * 重写这个方法，返回值是0,去底右部阴影
     *
     * @return
     */
    @Override
    protected float getRightFadingEdgeStrength() {
        return 0;
    }
}
