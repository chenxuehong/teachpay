package com.huihe.base_lib.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ClickableImageView extends AppCompatImageView {
    public ClickableImageView(Context context) {
        super(context);
    }

    public ClickableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }
}
