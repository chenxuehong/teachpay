package com.huihe.base_lib.ui.widget.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.manager.AppManager;

public class CommonTitle extends LinearLayout implements View.OnClickListener {

    private Context context;
    private View statusBar;
    private ImageView ivBack;
    private TextView tvBack;
    private TextView tvTitle;
    private ImageView rightIcon1;
    private ImageView rightIcon2;
    private ImageView rightIcon3;
    private TextView tvRightTitle;
    private View line;

    private Drawable leftImg;
    private boolean leftImgVisible;
    private String leftText;
    private int leftTextColor;
    private String title;
    private int titleTextColor;
    private int backgroundColor;
    private Drawable rightImg1;
    private Drawable rightImg2;
    private Drawable rightImg3;
    private String rightText;
    private int rightTextColor;
    private boolean showStateBar;
    private int statusBarColor;
    private boolean showLine;
    private int showLineColor;
    private LinearLayout llBackground;
    private float titleTextSize;

    public CommonTitle(Context context) {
        this(context, null);
    }

    public CommonTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.common_title);
        leftImg = ta.getDrawable(R.styleable.common_title_myleftImg);
        leftImgVisible = ta.getBoolean(R.styleable.common_title_myleftImgVisible, true);
        leftText = ta.getString(R.styleable.common_title_myleftText);
        leftTextColor = ta.getColor(R.styleable.common_title_myleftTextColor, context.getResources().getColor(R.color.title_color));
        title = ta.getString(R.styleable.common_title_mytitle);
        titleTextSize = ta.getDimension(R.styleable.common_title_mytitleTextSize, DensityUtils.sp2px(context, 16));
        titleTextColor = ta.getColor(R.styleable.common_title_mytitleTextColor, context.getResources().getColor(R.color.title_color));
        backgroundColor = ta.getColor(R.styleable.common_title_mybackgroundColor, context.getResources().getColor(R.color.white));
        rightImg1 = ta.getDrawable(R.styleable.common_title_myrightImg1);
        rightImg2 = ta.getDrawable(R.styleable.common_title_myrightImg2);
        rightImg3 = ta.getDrawable(R.styleable.common_title_myrightImg3);

        rightText = ta.getString(R.styleable.common_title_myrightText);
        rightTextColor = ta.getColor(R.styleable.common_title_myrightTextColor, context.getResources().getColor(R.color.title_color));
        showStateBar = ta.getBoolean(R.styleable.common_title_myshowStateBar, false);
        statusBarColor = ta.getColor(R.styleable.common_title_mystatusBarColor, context.getResources().getColor(R.color.transparent));
        showLine = ta.getBoolean(R.styleable.common_title_myshowLine, true);
        showLineColor = ta.getColor(R.styleable.common_title_mylineColor, context.getResources().getColor(R.color.line));
        ta.recycle();
        initView();
        bindAttrsValue();
        initListener();
    }

    public void setRightIcon1(@DrawableRes int drawableId) {
        setRightIcon1Visible(true);
        rightIcon1.setImageResource(drawableId);
    }

    public void setRightIcon2(@DrawableRes int drawableId) {
        setRightIcon2Visible(true);
        rightIcon2.setImageResource(drawableId);
    }

    public void setRightIcon3(@DrawableRes int drawableId) {
        setRightIcon3Visible(true);
        rightIcon3.setImageResource(drawableId);
    }

    private void initView() {
        View.inflate(context, R.layout.layout_common_title, this);
        statusBar = findViewById(R.id.layout_common_title_view_statusBar);
        ivBack = findViewById(R.id.layout_common_title_iv_back);
        tvBack = findViewById(R.id.layout_common_title_tv_back);
        tvTitle = findViewById(R.id.layout_common_title_tv_title);
        rightIcon1 = findViewById(R.id.layout_common_title_iv_rightIcon1);
        rightIcon2 = findViewById(R.id.layout_common_title_iv_rightIcon2);
        rightIcon3 = findViewById(R.id.layout_common_title_iv_rightIcon3);
        tvRightTitle = findViewById(R.id.layout_common_title_tv_rightTitle);
        line = findViewById(R.id.layout_common_title_view_line);
        llBackground = findViewById(R.id.layout_common_title_ll_background);
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        rightIcon1.setOnClickListener(this);
        rightIcon2.setOnClickListener(this);
        rightIcon3.setOnClickListener(this);
        tvRightTitle.setOnClickListener(this);
    }

    private void bindAttrsValue() {

        ivBack.setImageDrawable(leftImg);
        showLeftImg(leftImgVisible);

        if (TextUtils.isEmpty(leftText)) {
            tvBack.setVisibility(View.GONE);
        } else {
            tvBack.setVisibility(View.VISIBLE);
        }
        tvBack.setText(leftText);
        tvBack.setTextColor(leftTextColor);

        tvTitle.setText(title);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        llBackground.setBackgroundColor(backgroundColor);
        if (rightImg1 != null) {
            rightIcon1.setVisibility(View.VISIBLE);
        } else {
            rightIcon1.setVisibility(View.GONE);
        }
        if (rightImg2 != null) {
            rightIcon2.setVisibility(View.VISIBLE);
        } else {
            rightIcon2.setVisibility(View.GONE);
        }
        if (rightImg3 != null) {
            rightIcon3.setVisibility(View.VISIBLE);
        } else {
            rightIcon3.setVisibility(View.GONE);
        }
        rightIcon1.setImageDrawable(rightImg1);
        rightIcon2.setImageDrawable(rightImg2);
        rightIcon3.setImageDrawable(rightImg3);

        if (TextUtils.isEmpty(rightText)) {
            tvRightTitle.setVisibility(View.GONE);
        } else {
            tvRightTitle.setVisibility(View.VISIBLE);
        }
        tvRightTitle.setText(rightText);
        tvRightTitle.setTextColor(rightTextColor);
        showStatusBar(showStateBar);
        statusBar.setBackgroundColor(statusBarColor);
        showLine(showLine);
        line.setBackgroundColor(showLineColor);
    }

    public void showLine(boolean isShow) {
        line.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void showStatusBar(boolean showStateBar) {
        if (showStateBar) {
            statusBar.getLayoutParams().height = DensityUtils.getStatusHeight(context);
        } else {
            statusBar.getLayoutParams().height = 0;
        }
    }

    public void showLeftImg(boolean leftImgVisible) {
        if (leftImgVisible) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
    }

    public String getRightTitleText() {
        return tvRightTitle.getText().toString();
    }

    public void setRightTitleText(String rigntTitle) {
        if (tvRightTitle != null) {
            if (TextUtils.isEmpty(rigntTitle)) {
                tvRightTitle.setVisibility(View.GONE);
            } else {
                tvRightTitle.setVisibility(View.VISIBLE);
            }
            tvRightTitle.setText(rigntTitle);
        }
    }

    public void setRightTitleTextColor(int color) {
        if (tvRightTitle != null) {
            tvRightTitle.setTextColor(color);
        }
    }

    public void setRightIcon1Visible(boolean isShow) {
        if (isShow) {

            rightIcon1.setVisibility(View.VISIBLE);
        } else {
            rightIcon1.setVisibility(View.GONE);
        }
    }

    public void setRightIcon2Visible(boolean isShow) {
        if (isShow) {

            rightIcon2.setVisibility(View.VISIBLE);
        } else {
            rightIcon2.setVisibility(View.GONE);
        }
    }

    public void setRightIcon3Visible(boolean isShow) {
        if (isShow) {

            rightIcon3.setVisibility(View.VISIBLE);
        } else {
            rightIcon3.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (EventUtils.isFastDoubleClick(v.getId())) {
            return;
        }
        if (v.getId() == ivBack.getId() || v.getId() == tvBack.getId()) {

            if (onCommonTitleListener != null) {
                onCommonTitleListener.onLeftTitleClicked(v);
            } else {
                // 返回
                AppManager.getInstance().finishCurActivity();
            }
        } else if (v.getId() == rightIcon1.getId()) {
            if (onCommonTitleListener != null) {
                onCommonTitleListener.onRightIcon1Clicked(v);
            }
        } else if (v.getId() == rightIcon2.getId()) {
            if (onCommonTitleListener != null) {
                onCommonTitleListener.onRightIcon2Clicked(v);
            }
        } else if (v.getId() == rightIcon3.getId()) {
            if (onCommonTitleListener != null) {
                onCommonTitleListener.onRightIcon3Clicked(v);
            }
        } else if (v.getId() == tvRightTitle.getId()) {
            if (onCommonTitleListener != null) {
                onCommonTitleListener.onRightTitleClicked(v);
            }
        }
    }

    private OnCommonTitleListener onCommonTitleListener;

    public void setOnCommonTitleListener(OnCommonTitleListener onCommonTitleListener) {
        this.onCommonTitleListener = onCommonTitleListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }
}
