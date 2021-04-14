package com.huihe.base_lib.ui.widget.image;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * A ImageView's Label implementation View.
 * Other View's Label implement can refer to this.
 */
public class LabelImageView extends ImageView {
    private LabelViewHelper mLabelViewHelper;
    private boolean mLabelVisable = true;

    public LabelImageView(Context context) {
        this(context, null);
    }

    public LabelImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLabelViewHelper = new LabelViewHelper(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLabelVisable) {
            mLabelViewHelper.drawLabel(this, canvas);
        }
    }

    public void setTextContent(String content) {
        mLabelViewHelper.setTextContent(content);
        invalidate();
    }

    public void setTextTitle(String title) {
        mLabelViewHelper.setTextTitle(title);
        invalidate();
    }

    public void setLabelBackGroundColor(int color) {
        mLabelViewHelper.setLabelBackGroundColor(color);
        invalidate();
    }

    public void setLabelVisable(boolean visable) {
        mLabelVisable = visable;
        postInvalidate();
    }
}
