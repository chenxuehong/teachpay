package com.eghuihe.module_dynamic.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.eghuihe.module_dynamic.R;


public class RoundPointView extends View {

    private final int defaultPointColor = Color.BLACK;
    private Paint paint;
    private int pointNumber;
    private int pointColor;
    private boolean isFill;
    private boolean isStroke;
    private int strokeWidth;
    private float radius = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RoundPointView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RoundPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundPointViewStyle);
//        pointNumber = typedArray.getInt(R.styleable.RoundPointViewStyle_pointNumber, -1);
        pointColor = typedArray.getColor(R.styleable.RoundPointViewStyle_pointColor, defaultPointColor);
        isFill = typedArray.getBoolean(R.styleable.RoundPointViewStyle_fill, false);
        isStroke = typedArray.getBoolean(R.styleable.RoundPointViewStyle_stroke, false);
        strokeWidth = typedArray.getInt(R.styleable.RoundPointViewStyle_strokeWidth, 0);
        radius = typedArray.getInt(R.styleable.RoundPointViewStyle_radius, 0);

        typedArray.recycle();
//        paint.setColor(pointColor);
        if (isFill)
            paint.setStyle(Paint.Style.FILL);
        if (isStroke) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
        }
        paint.setColor(pointColor);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, paint);
    }
}
