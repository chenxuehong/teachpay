package com.huihe.base_lib.ui.widget.title;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.KeyBoardUtils;
import com.huihe.base_lib.utils.manager.AppManager;

public class CustomerSearchTitle extends RelativeLayout {

    private SearchView search;
    private EditText textView;
    private String color;
    private String text = "搜索";
    private boolean leftVisible;
    private boolean rightVisible;
    private ImageView ivDelete;
    private Context context;
    private View mImgLeft;

    public CustomerSearchTitle(Context context) {
        super(context);
        this.context = context;
    }

    public CustomerSearchTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.customer_title);
        color = ta.getString(R.styleable.customer_title_backgroundColor);
        text = ta.getString(R.styleable.customer_title_title);
        leftVisible = ta.getBoolean(R.styleable.customer_title_leftVisible, false);
        rightVisible = ta.getBoolean(R.styleable.customer_title_rightVisible, false);
        init();
        ta.recycle();
    }

    public CustomerSearchTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomerSearchTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        View inflate = inflate(getContext(), R.layout.common_search_title, this);

        if (!TextUtils.isEmpty(color)) {
            inflate.findViewById(R.id.mTitleBar).setBackgroundColor(Color.parseColor(color));
        }
        mImgLeft = inflate.findViewById(R.id.mImgLeft);
        mImgLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (leftVisible) {
            mImgLeft.setVisibility(VISIBLE);
        } else {
            mImgLeft.setVisibility(GONE);
        }

        search = inflate.findViewById(R.id.search);
        if (search == null) {
            return;
        }

        int imgId = getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
        ImageView searchButton = search.findViewById(imgId);
        searchButton.setImageResource(R.mipmap.ic_im_search);
        search.setIconifiedByDefault(false);

        int id = getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        textView = search.findViewById(id);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(getContext().getResources().getColor(R.color.color_1a1a1a));
        textView.setHintTextColor(getContext().getResources().getColor(R.color.color_a0a0a0));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (TextUtils.isEmpty(text)) {
            textView.setHint(context.getResources().getString(R.string.search));
        } else {
            textView.setHint(text);
        }

        int plate = getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        LinearLayout search_plate = search.findViewById(plate);
        search_plate.setBackgroundColor(Color.WHITE);
        search_plate.setGravity(Gravity.CENTER_VERTICAL);

        View txt_cancel = inflate.findViewById(R.id.txt_cancel);
        ivDelete = inflate.findViewById(R.id.common_search_title_iv_delete);
        txt_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setSearch("");
                setDeleteVisiable(false);
            }
        });

        if (rightVisible) {
            txt_cancel.setVisibility(VISIBLE);
        } else {
            txt_cancel.setVisibility(INVISIBLE);
        }
//        inflate.findViewById(R.id.status).getLayoutParams().height = DensityUtils.getStatusHeight();
    }

    private void finish() {
        if (getContext() instanceof Activity) {
            KeyBoardUtils.forceCloseKayBoard((Activity) getContext());
            AppManager.getInstance().finishActivity((AppCompatActivity) getContext());
        }
    }

    public void setDeleteVisiable(boolean visiable) {
        ivDelete.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    public void setSearch(String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setQueryHint(String str) {
        if (search != null) {
            search.setQueryHint(str);
        }
    }

    public void setOnQueryTextListener(SearchView.OnQueryTextListener listener) {
        search.setOnQueryTextListener(listener);
    }

}