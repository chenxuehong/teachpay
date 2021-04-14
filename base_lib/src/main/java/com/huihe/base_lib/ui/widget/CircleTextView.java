package com.huihe.base_lib.ui.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;


import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;

import com.huihe.base_lib.R;


public class CircleTextView extends AppCompatImageView {
    private static final ImageView.ScaleType SCALE_TYPE;
    private static final Config BITMAP_CONFIG;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_CIRCLE_BACKGROUND_COLOR = 0;
    private static final int DEFAULT_TEXT_COLOR = -16777216;
    private static final int DEFAULT_TEXT_SIZE = 22;
    private static final int DEFAULT_TEXT_PADDING = 4;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private final RectF mDrawableRect;
    private final RectF mBorderRect;
    private final Matrix mShaderMatrix;
    private final Paint mBitmapPaint;
    private final Paint mBorderPaint;
    private final Paint mCircleBackgroundColorPaint;
    private final Paint mTextPaint;
    private int mBorderColor;
    private int mBorderWidth;
    private int mCircleBackgroundColor;
    private String mTextString;
    private int mTextColor;
    private int mTextSize;
    private int mTextPadding;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private float mDrawableRadius;
    private float mBorderRadius;
    private ColorFilter mColorFilter;
    private volatile boolean mReady;
    private volatile boolean mSetupPending;
    private boolean mBorderOverlay;
    private boolean mDisableCircularTransformation;

    public CircleTextView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mCircleBackgroundColorPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        this.mCircleBackgroundColor = 0;
        this.mTextColor = -16777216;
        this.mTextSize = 22;
        this.mTextPadding = 4;
        this.init();
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mCircleBackgroundColorPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        this.mCircleBackgroundColor = 0;
        this.mTextColor = -16777216;
        this.mTextSize = 22;
        this.mTextPadding = 4;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView, defStyle, 0);
        this.mBorderWidth = typedArray.getDimensionPixelSize( R.styleable.CircleTextView_borderWidth, 0);
        this.mBorderColor = typedArray.getColor( R.styleable.CircleTextView_borderColor, -16777216);
        this.mBorderOverlay = typedArray.getBoolean( R.styleable.CircleTextView_borderOverlay, false);
        this.mTextString = typedArray.getString( R.styleable.CircleTextView_android_text);
        this.mTextColor = typedArray.getColor( R.styleable.CircleTextView_android_textColor, -16777216);
        this.mTextSize = typedArray.getDimensionPixelSize( R.styleable.CircleTextView_android_textSize, 22);
        this.mTextPadding = typedArray.getDimensionPixelSize( R.styleable.CircleTextView_textPadding, 4);
        this.mCircleBackgroundColor = typedArray.getColor( R.styleable.CircleTextView_backColor, 0);
        typedArray.recycle();
        this.init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (VERSION.SDK_INT >= 21) {
            this.setOutlineProvider(new CircleTextView.OutlineProvider());
        }

        if (this.mSetupPending) {
            this.setup();
            this.mSetupPending = false;
        }

    }

    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDisableCircularTransformation) {
            super.onDraw(canvas);
        } else {
            if (this.mCircleBackgroundColor != 0) {
                canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), this.mDrawableRadius, this.mCircleBackgroundColorPaint);
            }

            if (this.mBitmap != null) {
                canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), this.mDrawableRadius, this.mBitmapPaint);
            }

            if (this.mBorderWidth > 0) {
                canvas.drawCircle(this.mBorderRect.centerX(), this.mBorderRect.centerY(), this.mBorderRadius, this.mBorderPaint);
            }

            if (!TextUtils.isEmpty(this.mTextString)) {
                FontMetricsInt fontMetricsInt = this.mTextPaint.getFontMetricsInt();
                canvas.drawText(this.mTextString, this.mDrawableRect.centerX() - this.mTextPaint.measureText(this.mTextString) / 2.0F, this.mDrawableRect.centerY() - (float) fontMetricsInt.descent + (float) ((fontMetricsInt.bottom - fontMetricsInt.top) / 2), this.mTextPaint);
            }

        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.setup();
    }

    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        this.setup();
    }

    public String getTextString() {
        return this.mTextString;
    }

    public void setText(@StringRes int TextResId) {
        this.setText(this.getResources().getString(TextResId));
    }

    public void setText(String textString) {
        this.mTextString = textString;
        this.invalidate();
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public void setTextColor(@ColorInt int mTextColor) {
        this.mTextColor = mTextColor;
        this.mTextPaint.setColor(mTextColor);
        this.invalidate();
    }

    public void setTextColorResource(@ColorRes int colorResource) {
        this.setTextColor(this.getResources().getColor(colorResource));
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
        this.mTextPaint.setTextSize((float) textSize);
        this.invalidate();
    }

    public int getTextPadding() {
        return this.mTextPadding;
    }

    public void setTextPadding(int mTextPadding) {
        this.mTextPadding = mTextPadding;
        this.invalidate();
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
        this.setup();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public void setBorderColor(@ColorInt int borderColor) {
        if (borderColor != this.mBorderColor) {
            this.mBorderColor = borderColor;
            this.mBorderPaint.setColor(this.mBorderColor);
            this.invalidate();
        }
    }

    public int getCircleBackgroundColor() {
        return this.mCircleBackgroundColor;
    }

    public void setCircleBackgroundColor(@ColorInt int circleBackgroundColor) {
        if (circleBackgroundColor != this.mCircleBackgroundColor) {
            this.mCircleBackgroundColor = circleBackgroundColor;
            this.mCircleBackgroundColorPaint.setColor(circleBackgroundColor);
            this.invalidate();
        }
    }

    public void setCircleBackgroundColorResource(@ColorRes int circleBackgroundRes) {
        this.setCircleBackgroundColor(this.getContext().getResources().getColor(circleBackgroundRes));
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth != this.mBorderWidth) {
            this.mBorderWidth = borderWidth;
            this.setup();
        }
    }

    public boolean isBorderOverlay() {
        return this.mBorderOverlay;
    }

    public void setBorderOverlay(boolean borderOverlay) {
        if (borderOverlay != this.mBorderOverlay) {
            this.mBorderOverlay = borderOverlay;
            this.setup();
        }
    }

    public boolean isDisableCircularTransformation() {
        return this.mDisableCircularTransformation;
    }

    public void setDisableCircularTransformation(boolean disableCircularTransformation) {
        if (this.mDisableCircularTransformation != disableCircularTransformation) {
            this.mDisableCircularTransformation = disableCircularTransformation;
            this.initializeBitmap();
        }
    }

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.initializeBitmap();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.initializeBitmap();
    }

    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        this.initializeBitmap();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.initializeBitmap();
    }

    public void setColorFilter(ColorFilter cf) {
        if (cf != this.mColorFilter) {
            this.mColorFilter = cf;
            this.applyColorFilter();
            this.invalidate();
        }
    }

    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    private void applyColorFilter() {
        if (this.mBitmapPaint != null) {
            this.mBitmapPaint.setColorFilter(this.mColorFilter);
        }

    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            try {
                Bitmap bitmap;
                if (drawable instanceof ColorDrawable) {
                    bitmap = Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
                } else {
                    bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
                }

                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return bitmap;
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    private void initializeBitmap() {
        if (this.mDisableCircularTransformation) {
            this.mBitmap = null;
        } else {
            this.mBitmap = this.getBitmapFromDrawable(this.getDrawable());
        }

        this.setup();
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
        } else if (this.getWidth() != 0 || this.getHeight() != 0) {
            if (!TextUtils.isEmpty(this.mTextString)) {
                this.mTextPaint.setAntiAlias(true);
                this.mTextPaint.setColor(this.mTextColor);
                this.mTextPaint.setTextSize((float) this.mTextSize);
            }

            this.mBorderPaint.setStyle(Style.STROKE);
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setColor(this.mBorderColor);
            this.mBorderPaint.setStrokeWidth((float) this.mBorderWidth);
            this.mCircleBackgroundColorPaint.setStyle(Style.FILL);
            this.mCircleBackgroundColorPaint.setAntiAlias(true);
            this.mCircleBackgroundColorPaint.setColor(this.mCircleBackgroundColor);
            this.mBorderRect.set(this.calculateBounds());
            this.mBorderRadius = Math.min((this.mBorderRect.height() - (float) this.mBorderWidth) / 2.0F, (this.mBorderRect.width() - (float) this.mBorderWidth) / 2.0F);
            this.mDrawableRect.set(this.mBorderRect);
            if (!this.mBorderOverlay && this.mBorderWidth > 0) {
                this.mDrawableRect.inset((float) this.mBorderWidth - 1.0F, (float) this.mBorderWidth - 1.0F);
            }

            this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0F, this.mDrawableRect.width() / 2.0F);
            if (this.mBitmap != null) {
                this.mBitmapShader = new BitmapShader(this.mBitmap, TileMode.CLAMP, TileMode.CLAMP);
                this.mBitmapHeight = this.mBitmap.getHeight();
                this.mBitmapWidth = this.mBitmap.getWidth();
                this.mBitmapPaint.setAntiAlias(true);
                this.mBitmapPaint.setShader(this.mBitmapShader);
                this.applyColorFilter();
                this.updateShaderMatrix();
            }

            this.invalidate();
        }
    }

    private RectF calculateBounds() {
        int availableWidth = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
//        int availableHeight = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int availableHeight = availableWidth;
        int sideLength = Math.min(availableWidth, availableHeight);
        float left = (float) this.getPaddingLeft() + (float) (availableWidth - sideLength) / 2.0F;
        float top = (float) this.getPaddingTop() + (float) (availableHeight - sideLength) / 2.0F;
        return new RectF(left, top, left + (float) sideLength, top + (float) sideLength);
    }

    private void updateShaderMatrix() {
        float dx = 0.0F;
        float dy = 0.0F;
        this.mShaderMatrix.set((Matrix) null);
        float scale;
        if ((float) this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * (float) this.mBitmapHeight) {
            scale = this.mDrawableRect.height() / (float) this.mBitmapHeight;
            dx = (this.mDrawableRect.width() - (float) this.mBitmapWidth * scale) * 0.5F;
        } else {
            scale = this.mDrawableRect.width() / (float) this.mBitmapWidth;
            dy = (this.mDrawableRect.height() - (float) this.mBitmapHeight * scale) * 0.5F;
        }

        this.mShaderMatrix.setScale(scale, scale);
        this.mShaderMatrix.postTranslate((float) ((int) (dx + 0.5F)) + this.mDrawableRect.left, (float) ((int) (dy + 0.5F)) + this.mDrawableRect.top);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.inTouchableArea(event.getX(), event.getY()) && super.onTouchEvent(event);
    }

    private boolean inTouchableArea(float x, float y) {
        return Math.pow((double) (x - this.mBorderRect.centerX()), 2.0D) + Math.pow((double) (y - this.mBorderRect.centerY()), 2.0D) <= Math.pow((double) this.mBorderRadius, 2.0D);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (!TextUtils.isEmpty(this.mTextString)) {
            int textMeasuredSize = (int) this.mTextPaint.measureText(this.mTextString);
            textMeasuredSize += 2 * this.mTextPadding;
            if (widthMeasureSpecMode == -2147483648 && heightMeasureSpecMode == -2147483648 && (textMeasuredSize > this.getMeasuredWidth() || textMeasuredSize > this.getMeasuredHeight())) {
                this.setMeasuredDimension(textMeasuredSize, textMeasuredSize);
            }
        }

    }

    static {
        SCALE_TYPE = ScaleType.CENTER_CROP;
        BITMAP_CONFIG = Config.ARGB_8888;
    }

    @RequiresApi(
            api = 21
    )
    private class OutlineProvider extends ViewOutlineProvider {
        private OutlineProvider() {
        }

        public void getOutline(View view, Outline outline) {
            Rect bounds = new Rect();
            mBorderRect.roundOut(bounds);
            outline.setRoundRect(bounds, (float) bounds.width() / 2.0F);
        }
    }
}
