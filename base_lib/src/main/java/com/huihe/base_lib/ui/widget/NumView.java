package com.huihe.base_lib.ui.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.EventUtils;

public class NumView extends LinearLayout implements View.OnClickListener {

    private ImageView ivReduce;
    private TextView tvNum;
    private ImageView ivAdd;
    private int maxNum = 10;
    private int count;

    public int getCount() {
        return count;
    }

    public NumView(Context context) {
        this(context, null);
    }

    public NumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_num_view, this);
        ivReduce = view.findViewById(R.id.layout_num_view_iv_reduce);
        tvNum = view.findViewById(R.id.layout_num_view_tv_num);
        ivAdd = view.findViewById(R.id.layout_num_view_iv_add);
        count = 1;
        initListener();
    }

    private void initListener() {
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
    }

    public void setNum(int count) {
        tvNum.setText(String.valueOf(count));
        this.count = count;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layout_num_view_iv_add) {

            changeCount(true);
        } else if (v.getId() == R.id.layout_num_view_iv_reduce) {
            changeCount(false);
        }
        if (listener != null) {
            listener.onCountChanged(count);
        }
    }

    private void changeCount(boolean add) {

        if (add) {
            addCount();
        } else {
            reduceCount();
        }
    }

    private void reduceCount() {
        if (count <= 1) {
            return;
        }
        count--;
        tvNum.setText(String.valueOf(count));
    }

    private void addCount() {
        if (count >= maxNum) {
            return;
        }
        count++;
        tvNum.setText(String.valueOf(count));
    }

    private OnCountChangedListener listener;

    public void setOnCountChangedListener(OnCountChangedListener listener) {
        this.listener = listener;
    }

    public interface OnCountChangedListener {
        void onCountChanged(int count);
    }
}
