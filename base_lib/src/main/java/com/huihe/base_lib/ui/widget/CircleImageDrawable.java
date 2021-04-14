package com.huihe.base_lib.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.huihe.base_lib.utils.DensityUtils;

public class CircleImageDrawable extends Drawable {
    /**
     * 显示图片
     */
    private Bitmap mBitmap;
    /**
     * BitmapShader
     */
    private BitmapShader mBitmapShader;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 圆心
     */
    private float cx, cy;
    /**
     * 半径
     */
    private float radius;

    private int size;

    public CircleImageDrawable(Context context,Bitmap bitmap,int dp) {
        this.mBitmap = bitmap;
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mBitmapShader);


        size = DensityUtils.dp2px(context,dp);

        cx = size / 2;
        cy = size / 2;
        radius = size / 2;

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

    @Override
    public int getIntrinsicHeight() {
        return size;
    }

    @Override
    public int getIntrinsicWidth() {
        return size;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
