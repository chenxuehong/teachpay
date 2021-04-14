package com.eghuihe.module_home;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DensityUtils;

public class TeachingPaySearchView extends LinearLayout {

    private Context context;
    private TextView tvLoc;
    private TextView tvCity;
    private String searchHint;
    private int searchHeight;
    private float locTextSize;
    private float locCityTextSize;
    private Drawable search_background;
    private int locTextColor;
    private String locText;
    private FrameLayout flStatus;
    private int statusColor;
    private LinearLayout llSearchView;
    private int height;
    private FrameLayout flSearch;
    private TextView tvSearchHint;
    private View line;

    public TeachingPaySearchView(Context context) {
        this(context, null);
    }

    public TeachingPaySearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TeachingPaySearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(context, attrs);
        initView();
        initData();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TeachingPaySearchView);
        height = ta.getDimensionPixelSize(R.styleable.TeachingPaySearchView_height, DensityUtils.dp2px(context, 60));
        searchHint = ta.getString(R.styleable.TeachingPaySearchView_search_hint);
        locText = ta.getString(R.styleable.TeachingPaySearchView_locText);
        statusColor = ta.getColor(R.styleable.TeachingPaySearchView_status_color, getResources().getColor(R.color.transparent));
        search_background = ta.getDrawable(R.styleable.TeachingPaySearchView_search_background);
        locTextColor = ta.getColor(R.styleable.TeachingPaySearchView_locTextColor, getResources().getColor(R.color.white));
        searchHeight = ta.getDimensionPixelSize(R.styleable.TeachingPaySearchView_search_height, DensityUtils.dp2px(context, 40));
        locTextSize = ta.getDimension(R.styleable.TeachingPaySearchView_locTextSize, DensityUtils.sp2px(context, 15));
        locCityTextSize = ta.getDimension(R.styleable.TeachingPaySearchView_locCityTextSize, DensityUtils.sp2px(context, 15));
        ta.recycle();
    }

    private void initView() {
        View.inflate(context, R.layout.layout_teach_fubao_seachview, this);
        LinearLayout llLoc = findViewById(R.id.layout_teach_fubao_searchview_ll_loc);
        tvLoc = findViewById(R.id.layout_teach_fubao_searchview_tv_loc);
        tvCity = findViewById(R.id.layout_teach_fubao_searchview_tv_city);
        flStatus = findViewById(R.id.layout_teach_fubao_searchview_fl_status);
        llSearchView = findViewById(R.id.layout_teach_fubao_searchview_ll);
        flSearch = findViewById(R.id.layout_teach_fubao_searchview_ll_search);
        tvSearchHint = findViewById(R.id.layout_teach_fubao_searchview_tv_searchHint);
        line = findViewById(R.id.layout_teach_fubao_searchview_line);
        llLoc.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mOnListener != null) {
                    mOnListener.onLocationClicked(v);
                }
            }
        });
        flSearch.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mOnListener != null) {
                    mOnListener.onEnterQueryClicked(v);
                }
            }
        });

    }

    private void initData() {
        tvSearchHint.setText(searchHint);
        ViewGroup.LayoutParams searchLayoutParams = flSearch.getLayoutParams();
        searchLayoutParams.height = searchHeight;
        flSearch.setLayoutParams(searchLayoutParams);
        flSearch.setBackground(search_background);

        tvLoc.setTextColor(locTextColor);
        tvLoc.setTextSize(TypedValue.COMPLEX_UNIT_PX, locTextSize);
        tvLoc.setText(locText);

        tvCity.setTextColor(locTextColor);
        tvCity.setTextSize(TypedValue.COMPLEX_UNIT_PX, locCityTextSize);
        ViewGroup.LayoutParams layoutParams = flStatus.getLayoutParams();
        layoutParams.height = DensityUtils.getStatusHeight();
        flStatus.setLayoutParams(layoutParams);
        flStatus.setBackgroundColor(statusColor);

        ViewGroup.LayoutParams layoutParams1 = llSearchView.getLayoutParams();
        layoutParams1.height = height;
        llSearchView.setLayoutParams(layoutParams1);
    }

    public void setLocationContent(String content) {
        tvLoc.setText(content);
    }

    public void setLocationCity(String city) {
        tvCity.setText(city);
    }

    public void setLocationContentColor(int color) {
        tvLoc.setTextColor(color);
    }

    public void setLocationCityColor(int color) {
        tvCity.setTextColor(color);
    }

    public void showLine(boolean isShow) {
        line.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public String getLocationContent() {
        return tvLoc.getText().toString().trim();
    }

    private OnListener mOnListener;

    public void setOnListener(OnListener onListener) {
        mOnListener = onListener;
    }

    public interface OnListener {

        void onBack(View view);

        void onLocationClicked(View view);

        void onEnterQueryClicked(View view);
    }
}
