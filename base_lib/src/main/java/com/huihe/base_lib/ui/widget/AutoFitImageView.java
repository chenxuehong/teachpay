package com.huihe.base_lib.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.huihe.base_lib.R;

/**
 * Created by 13198 on 2018/1/8.
 * 高度适配的ImageView
 */

@SuppressLint("AppCompatCustomView")
public class AutoFitImageView extends ImageView {

    private boolean autofitWidth;

    public AutoFitImageView(Context context) {
        this(context, null);
    }

    public AutoFitImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.AutoFitImageView);
        autofitWidth = typedArray.getBoolean(R.styleable.AutoFitImageView_autoFitWidth, false);
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
//            int width = drawable.getMinimumWidth();
//            int height = drawable.getMinimumHeight();
            if (!autofitWidth) {
                float scale = (float) height / width;

                int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
                int heightMeasure = (int) (widthMeasure * scale);

                heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightMeasure, MeasureSpec.EXACTLY);
            } else {
                float scale = (float) width / height;
                int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
                int widthMeasure = (int) (heightMeasure * scale);
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasure, MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}