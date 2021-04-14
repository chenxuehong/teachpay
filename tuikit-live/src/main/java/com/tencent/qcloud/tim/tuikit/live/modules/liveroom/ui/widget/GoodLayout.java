package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.huihe.base_lib.utils.DensityUtils;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.utils.GlideEngine;

public class GoodLayout extends ConstraintLayout {

    private ImageView ivFaceUrl;
    private TextView tvTitle;
    private TextView tvAmount;
    private TextView tvPayNum;

    private Context context;

    public GoodLayout(@NonNull Context context) {
        this(context, null);
    }

    public GoodLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        inflate(context, R.layout.live_layout_good, this);
        initView();
    }

    private void initView() {
        ivFaceUrl = findViewById(R.id.live_layout_good_iv_faceUrl);
        tvTitle = findViewById(R.id.live_layout_good_tv_title);
        ImageView ivClose = findViewById(R.id.live_layout_good_iv_close);
        tvAmount = findViewById(R.id.live_layout_good_tv_amount);
        tvPayNum = findViewById(R.id.live_layout_good_tv_payNum);
        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               if (onListener!=null){
                   onListener.onClosed();
               }
            }
        });
    }

    public void setFaceUrl(String url, @DrawableRes int resourceId, int radius) {
        GlideEngine.loadImage(ivFaceUrl, url, resourceId, radius);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setAmount(String amount) {
        tvAmount.setText(amount);
    }

    public void setPayNum(String payNum) {
        tvPayNum.setText(payNum);
    }

    private OnCloseListener onListener;

    public void setOnCloseListener(OnCloseListener onListener) {
        this.onListener = onListener;
    }

    public interface OnCloseListener{
        void onClosed();
    }
}
