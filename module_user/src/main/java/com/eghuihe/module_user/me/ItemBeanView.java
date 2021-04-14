package com.eghuihe.module_user.me;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.utils.DensityUtils;

public class ItemBeanView extends LinearLayout {

    private Context context;
    private ImageView ivLeftIcon;
    private TextView tvTitle;
    private float titleTextSize;
    private String title;
    private int titleColor;
    private Drawable leftIcon;
    private TextView tvRightContent;
    private String rightTitle;
    private int rightTitleColor;
    private float rightTitleTextSize;

    public ItemBeanView(Context context) {
        this(context, null);
    }

    public ItemBeanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemBeanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(attrs);
        initView();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemBeanView);
        titleTextSize = ta.getDimension(R.styleable.ItemBeanView_Item_title_TextSize, DensityUtils.sp2px(context, 15));
        title = ta.getString(R.styleable.ItemBeanView_Item_title);
        titleColor = ta.getColor(R.styleable.ItemBeanView_Item_title_color, getResources().getColor(R.color.color_333333));
        leftIcon = ta.getDrawable(R.styleable.ItemBeanView_Item_leftIcon);
        rightTitle = ta.getString(R.styleable.ItemBeanView_Item_right_title);
        rightTitleColor = ta.getColor(R.styleable.ItemBeanView_Item_right_title_color, getResources().getColor(R.color.color_999999));
        rightTitleTextSize = ta.getDimension(R.styleable.ItemBeanView_Item_right_title_TextSize, DensityUtils.sp2px(context, 15));
        ta.recycle();
    }

    private void initView() {

        View.inflate(context, R.layout.layout_item_view, this);
        ivLeftIcon = findViewById(R.id.layout_item_view_iv_left_icon);
        tvTitle = findViewById(R.id.layout_item_view_tv_title);
        tvRightContent = findViewById(R.id.layout_item_view_tv_rightContent);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        tvTitle.setTextColor(titleColor);
        tvTitle.setText(title);
        tvRightContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTitleTextSize);
        tvRightContent.setTextColor(rightTitleColor);
        tvRightContent.setHint(rightTitle);
        ivLeftIcon.setImageDrawable(leftIcon);
    }

    public String getRightTitle() {
        return tvRightContent.getText().toString().trim();
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setRightHintTitle(String title) {
        tvRightContent.setHint(title);
    }
    public void setRightTitle(String title) {
        tvRightContent.setText(title);
    }

    public void setLeftIcon(int leftIcon) {
        ivLeftIcon.setImageResource(leftIcon);
    }
}
