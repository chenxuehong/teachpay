package com.huihe.base_lib.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.recyclerview.HorizontalRecyclerViewFix;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.utils.DensityUtils;

public class TabTitleRecyclerView extends RelativeLayout {
    private Context context;
    private HorizontalRecyclerViewFix recyclerViewFixed;
    private TextView tvTitle;
    private float title_textSize;
    private String title;
    private int title_color;
    private TextView tvRightTitle;

    public TabTitleRecyclerView(Context context) {
        this(context, null);
    }

    public TabTitleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabTitleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(attrs);
        initView();
        bindAttr();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TabTitleRecyclerView);
        title = ta.getString(R.styleable.TabTitleRecyclerView_TabTitle);
        title_color = ta.getColor(R.styleable.TabTitleRecyclerView_title_color,
                getResources().getColor(R.color.color_333333));
        title_textSize = ta.getDimension(R.styleable.TabTitleRecyclerView_title_TextSize,
                DensityUtils.sp2px(context, 15));
        ta.recycle();
    }

    private void initView() {
        View.inflate(context, R.layout.tab_title_recyclerview, this);
        tvTitle = findViewById(R.id.tab_title_recyclerview_tv_title);
        tvRightTitle = findViewById(R.id.tab_title_recyclerview_tv_right_title);
        recyclerViewFixed = findViewById(R.id.tab_title_recyclerview_rv);
        recyclerViewFixed.setScrollingEnabled(false);
        tvRightTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMoreListener != null) {
                    onMoreListener.onMoreClicked(view);
                }
            }
        });
    }

    public void setRightTitle(String rightTitle) {
        tvRightTitle.setText(rightTitle);
    }

    private void bindAttr() {
        tvTitle.setText(title);
        tvTitle.setTextColor(title_color);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, title_textSize);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitleColor(int titleColor) {
        tvTitle.setTextColor(titleColor);
    }

    public void setTitleTextSize(float title_textSize) {
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, title_textSize);
    }

    public void setVertical(int spanCount) {
        recyclerViewFixed.setVertical(spanCount);
    }

    public void setHorizontal() {
        recyclerViewFixed.setScrollingEnabled(false);
        recyclerViewFixed.setHorizontal();
    }

    public void addGridViewItemDecoration(int column, int space) {
        recyclerViewFixed.addGridViewItemDecoration(column, space);
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        recyclerViewFixed.setAdapter(adapter);
    }

    private OnMoreListener onMoreListener;

    public void setOnMoreListener(OnMoreListener onMoreListener) {
        this.onMoreListener = onMoreListener;
    }

    public interface OnMoreListener {
        void onMoreClicked(View view);
    }
}
