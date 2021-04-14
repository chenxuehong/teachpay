package com.huihe.base_lib.ui.widget.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatImageView;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.StatusBarUtil;

import java.lang.reflect.Field;


public class CustomerTitle extends RelativeLayout {

    private String text;
    private boolean leftVisible;
    private String leftText;
    private String rightText;
    private Drawable rightDrawable;
    private Drawable right2Drawable;
    private Drawable right3Drawable;
    private ImageView mImgLeft;
    private TextView txtLeftText;
    private TextView mMainTitle;
    private TextView mTxtRight;
    private ImageView mImgRight;
    private ImageView mImgRight2;
    private ImageView mImgRight3;
    private int color;
    private boolean showStateBar;
    private boolean showLine;
    private View line;
    private View viewById;
    private TextView tvLeftText;

    private Context context;
    private int leftTextColor;
    private Drawable leftImg;
    private int titleTextColor;
    private int rightTextColor;
    private int statusBarColor;
    private int titleTextSize;
    private RelativeLayout titleBar;
    private RelativeLayout mRlRight;
    private TextView tvImUnread;

    public TextView getTxtRight() {
        return mTxtRight;
    }

    public CustomerTitle(Context context) {
        this(context, null);
    }

    public CustomerTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.customer_title);
        leftImg = ta.getDrawable(R.styleable.customer_title_leftImg);
        color = ta.getColor(R.styleable.customer_title_backgroundColor, context.getResources().getColor(R.color.white));
        text = ta.getString(R.styleable.customer_title_title);
        titleTextColor = ta.getColor(R.styleable.customer_title_titleTextColor, context.getResources().getColor(R.color.title_color));
        leftVisible = ta.getBoolean(R.styleable.customer_title_leftVisible, true);
        leftText = ta.getString(R.styleable.customer_title_leftText);
        leftTextColor = ta.getColor(R.styleable.customer_title_leftTextColor, context.getResources().getColor(R.color.title_color));
        rightTextColor = ta.getColor(R.styleable.customer_title_rightTextColor, context.getResources().getColor(R.color.title_color));
        statusBarColor = ta.getColor(R.styleable.customer_title_statusBarColor, context.getResources().getColor(R.color.title_color));
        rightText = ta.getString(R.styleable.customer_title_rightText);
        rightDrawable = ta.getDrawable(R.styleable.customer_title_rightImg);
        right2Drawable = ta.getDrawable(R.styleable.customer_title_rightImg2);
        right3Drawable = ta.getDrawable(R.styleable.customer_title_rightImg3);
        showStateBar = ta.getBoolean(R.styleable.customer_title_showStateBar, false);
        showLine = ta.getBoolean(R.styleable.customer_title_showLine, true);


        ta.recycle();
        init();
    }

    public void setLeftTitle(String title) {
        txtLeftText.setText(title);
    }

    public void setRight2Img(int resId) {
        mImgRight2.setVisibility(VISIBLE);
        mImgRight2.setImageResource(resId);
//        GlideTools.loadImage(context,Integer.valueOf(resId),mImgRight2);
    }

    public void setRight3Img(int resId) {
        mImgRight3.setVisibility(VISIBLE);
//        GlideTools.loadImage(context,Integer.valueOf(resId),mImgRight3);

        mImgRight3.setImageResource(resId);
    }


    private void init() {
        View inflate = View.inflate(getContext(), R.layout.common_title, this);

        titleBar = inflate.findViewById(R.id.mTitleBar);
        titleBar.setBackgroundColor(color);
        mImgLeft = findViewById(R.id.mImgLeft);
        if (leftVisible) {
            mImgLeft.setVisibility(VISIBLE);
        } else {
            mImgLeft.setVisibility(INVISIBLE);
        }
        mImgLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fixInputMethodManagerLeak(getContext());
                ((AppCompatActivity) context).finish();
            }
        });
        mImgLeft.setImageDrawable(leftImg);
        txtLeftText = findViewById(R.id.txt_left_text);
        if (TextUtils.isEmpty(leftText)) {
            txtLeftText.setVisibility(INVISIBLE);
        } else {
            txtLeftText.setVisibility(VISIBLE);
            txtLeftText.setText(leftText);
        }
        txtLeftText.setTextColor(leftTextColor);
        txtLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fixInputMethodManagerLeak(getContext());
                ((AppCompatActivity) context).finish();
            }
        });

        mMainTitle = findViewById(R.id.mMainTitle);
        mMainTitle.setText(text);
        mMainTitle.setTextColor(titleTextColor);
        mMainTitle.setLines(1);
        mMainTitle.setEllipsize(TextUtils.TruncateAt.END);

        mTxtRight = findViewById(R.id.mTxtRight);
        tvImUnread = inflate.findViewById(R.id.tv_im_unread);
        if (rightText == null) {
            mTxtRight.setVisibility(GONE);
        } else {
            mTxtRight.setVisibility(VISIBLE);
            mTxtRight.setText(rightText);
        }
        mTxtRight.setTextColor(rightTextColor);

        mImgRight = findViewById(R.id.mImgRight);
        if (rightDrawable == null) {
            mImgRight.setVisibility(GONE);
        } else {
            mImgRight.setVisibility(VISIBLE);
            mImgRight.setImageDrawable(rightDrawable);
        }

        mImgRight2 = findViewById(R.id.mImgRight2);
        if (right2Drawable == null) {
            mImgRight2.setVisibility(GONE);
        } else {
            mImgRight2.setVisibility(VISIBLE);
            mImgRight2.setImageDrawable(right2Drawable);
        }

        mImgRight3 = findViewById(R.id.mImgRight3);
        if (right3Drawable == null) {
            mImgRight3.setVisibility(GONE);
        } else {
            mImgRight3.setVisibility(VISIBLE);
            mImgRight3.setImageDrawable(right3Drawable);
        }

        line = findViewById(R.id.line);

        if (showLine) {
            line.setVisibility(VISIBLE);
        } else {
            line.setVisibility(GONE);
        }

        this.viewById = inflate.findViewById(R.id.container_title);

        View rlStatusBar = inflate.findViewById(R.id.status);
        if (showStateBar) {
//            mMainTitle.setTextColor(Color.WHITE);
//            txtLeftText.setTextColor(Color.WHITE);
//            mTxtRight.setTextColor(Color.WHITE);
//            inflate.findViewById(R.id.mTitleBar).setBackgroundColor(ColorUtils.getThemeColor());
            rlStatusBar.setVisibility(View.VISIBLE);
            int statusBarHeight = StatusBarUtil.getStatusBarHeight(context);
            rlStatusBar.setBackgroundColor(statusBarColor);
            rlStatusBar.getLayoutParams().height = statusBarHeight;
        } else {
            rlStatusBar.setVisibility(View.GONE);
        }

        tvLeftText = this.viewById.findViewById(R.id.txt_left_text);
    }

    public void setRightTextColor(@ColorInt int color) {
        mTxtRight.setTextColor(color);
    }

    public void setRightTextVisibility(boolean isShow) {
        mTxtRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    public void setUnrad(String unread) {
        if (TextUtils.isEmpty(unread)){
            tvImUnread.setVisibility(View.GONE);
        }else {
            tvImUnread.setVisibility(View.VISIBLE);
            tvImUnread.setText(unread);
        }
    }

    public void setTitleBarBackgroudColor(int color) {
        titleBar.setBackgroundColor(color);
    }

    public void setTitle(String title) {
        mMainTitle.setText(title);
        requestLayout();
    }

    public void setTitleColor(int titleColor) {
        mMainTitle.setTextColor(titleColor);
    }

    public void setTitleSize(float size) {
        mMainTitle.setTextSize(size);
    }

    public void setLeftImgVisible(boolean visible) {
        if (visible) {
            mImgLeft.setVisibility(VISIBLE);
        } else {
            mImgLeft.setVisibility(INVISIBLE);
        }
    }

    public void setLeftImg(int leftImg) {
        mImgLeft.setImageResource(leftImg);
    }

    public void setLeftTextVisible(boolean visible) {
        if (visible) {
            tvLeftText.setVisibility(VISIBLE);
        } else {
            tvLeftText.setVisibility(GONE);
        }
    }

    public void setLeftTextColor(int color) {
        tvLeftText.setTextColor(color);
    }

    public void setLeftTexSize(float size) {
        tvLeftText.setTextSize(size);
    }

    public void setRightImg(int resId) {
        mImgRight.setVisibility(VISIBLE);
        mImgRight.setImageResource(resId);
    }

    public void setLineVisible(boolean showLine) {
        if (showLine) {
            line.setVisibility(VISIBLE);
        } else {
            line.setVisibility(GONE);
        }
    }

    public void setRightImgVisible(boolean visible) {
        if (visible) {
            mImgRight.setVisibility(VISIBLE);
        } else {
            mImgRight.setVisibility(GONE);
        }
    }

    public void setRightImg2Visible(boolean visible) {
        if (visible) {
            mImgRight2.setVisibility(VISIBLE);
        } else {
            mImgRight2.setVisibility(GONE);
        }
    }

    public void setRightImg3Visible(boolean visible) {
        if (visible) {
            mImgRight3.setVisibility(VISIBLE);
        } else {
            mImgRight3.setVisibility(GONE);
        }
    }

    public void setImgRightListener(OnClickListener listener) {
        mImgRight.setOnClickListener(listener);
    }

    public void setImgRight2Listener(OnClickListener listener) {

        mImgRight2.setOnClickListener(listener);
    }

    public void setImgRight3Listener(OnClickListener listener) {

        mImgRight3.setOnClickListener(listener);
    }

    public void setRightText(String text) {
        mTxtRight.setText(text);
        mTxtRight.setVisibility(View.VISIBLE);
    }

    public void setRightTextSize(float size) {
        mTxtRight.setTextSize(size);
    }

    public void setLeftListener(OnClickListener listener) {
        mImgLeft.setOnClickListener(listener);
        txtLeftText.setOnClickListener(listener);
    }

    /**
     * 设置背景透明的
     *
     * @param alpha
     */
    public void setBackgroundAlpha(int alpha) {
        findViewById(R.id.mTitleBar).getBackground().mutate().setAlpha(alpha);
        findViewById(R.id.status).getBackground().mutate().setAlpha(alpha);
    }

    public void setRightTextListener(OnClickListener listener) {
        if (mTxtRight != null)
            mTxtRight.setOnClickListener(listener);
    }

    public void fixInputMethodManagerLeak(Context context) {
        if (context == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                }
                obj = f.get(imm);
                if (obj != null && obj instanceof View) {
                    View vGet = (View) obj;
                    if (vGet.getContext() == context) {
                        f.set(imm, null);
                    } else {
                        break;
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}