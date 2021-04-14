package com.huihe.base_lib.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.LogUtils;

public class RatioLinearLayout extends LinearLayout {

    private static final String TAG = RatioLinearLayout.class.getSimpleName();
    float mWidthRatio = 4f / 3;
    private int childount;
    private float padding;

    public RatioLinearLayout(Context context) {
        super(context);
    }

    public RatioLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public RatioLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.RatioLinearLayout);
        padding = a.getDimension(R.styleable.RatioLinearLayout_padding, 0f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (mWidthRatio > 0) {
            childount = getChildCount();
            int visiableChildCount = 0;
            for (int i = 0; i < childount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() == View.VISIBLE) {
                    visiableChildCount++;
                }
            }
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int childHeight = (int) ((height - (visiableChildCount - 1) * padding) / visiableChildCount);
            LogUtils.i(TAG, "onMeasure - childHeight = " + childHeight);
            super.onMeasure(MeasureSpec.makeMeasureSpec(
                    (int) (childHeight * mWidthRatio), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
