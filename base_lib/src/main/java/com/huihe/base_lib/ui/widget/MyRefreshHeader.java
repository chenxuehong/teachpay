package com.huihe.base_lib.ui.widget;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

public class MyRefreshHeader extends InternalAbstract implements RefreshHeader {

    public static String REFRESH_HEADER_PULLING = "下拉可以刷新";//"下拉可以刷新";
    public static String REFRESH_HEADER_LOADING = "正在加载...";//"正在加载...";
    public static String REFRESH_HEADER_RELEASE = "释放立即刷新";
    public static String REFRESH_HEADER_FINISH = "刷新成功";//"刷新完成";
    public static String REFRESH_HEADER_FAILED = "刷新失败";//"刷新失败";

    private ImageView
            refreshHeader;
    private Animatable animationDrawable;

    private Context context;

    public MyRefreshHeader(@NonNull Context wrapped) {
        this(wrapped, null);
    }

    public MyRefreshHeader(@NonNull Context wrappedView, @Nullable AttributeSet wrappedInternal) {
        this(wrappedView, wrappedInternal, 0);
    }

    public MyRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_refresh_head, this);
        refreshHeader = view.findViewById(R.id.iv_refresh_header);
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return super.onFinish(refreshLayout, success);
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        super.onStateChanged(refreshLayout, oldState, newState);
        GlideTools.loadGifImage(BaseApplication.getInstance(), R.drawable.loading_head, refreshHeader);
//        switch (newState) {
//            case PullDownToRefresh: //下拉过程
//                GlideTools.loadGifImage(context,R.drawable.loading_head,refreshHeader);
////                if (refreshHeader!=null){
////                    refreshHeader.setBackgroundResource(R.drawable.header_1);
////                }
//
//                break;
//            case Refreshing: //loading中
//
//                break;
//            case RefreshReleased: //松开刷新
//
////                if (refreshHeader!=null){
////                    refreshHeader.setBackgroundResource(R.drawable.refresh_header_anim);
////                    animationDrawable = (AnimationDrawable) refreshHeader.getBackground();
////                    animationDrawable.start();
////                }
//
//                break;
//            case RefreshFinish:
//
////                if (animationDrawable!=null){
////                    animationDrawable.stop();
////                }
////                if (refreshHeader!=null){
////                    refreshHeader.clearAnimation();
////                }
//                break;
//        }

    }
}

