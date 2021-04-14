package com.huihe.base_lib.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IdRes;

import com.huihe.base_lib.R;


/**
 * @author Wlq
 * @description 对话框基类
 * @date 2017/11/15 上午11:36
 */
public abstract class BaseDialog extends Dialog {

    private double perWidth = -1;
    private double whPer = 0.80;
    private boolean canceledOnTouchOutside = true;
    private boolean canceled = true;
    private static int theme = R.style.customDialogTheme;
    private boolean enableScreenAdapter = false;
    private Window win;
    private WindowManager.LayoutParams lp;

    public WindowManager.LayoutParams getLp() {
        return lp;
    }

    public Window getWin() {
        return win;
    }

    public static int getTheme() {
        theme = R.style.customDialogTheme;
        return theme;
    }

    private View root;
    protected Context mContext;


    /**
     * 设置点击返回键是否dismiss
     */
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    /**
     * 设置点击外部是否dismiss
     */
    public void setCancelOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
    }

    /**
     * 设置宽度百分比
     *
     * @param perWidth
     */
    public void setPerWidth(double perWidth) {
        this.perWidth = perWidth;
        enableScreenAdapter = true;
    }

    /**
     * @param whPer
     * @desc 设置宽高比
     */
    public void setWhPer(double whPer) {
        this.whPer = whPer;
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    public BaseDialog(Context context) {
        super(context, theme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        whPer = -1;
        root = LayoutInflater.from(mContext).inflate(getLayoutResId(), null);
        win = this.getWindow();
        lp = win.getAttributes();
        lp.windowAnimations = theme;
        setContentView(root);
        screenAdapter();
        initParams();
        initEvents();
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        setCancelable(canceled);
        setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    ((Activity) mContext).finish();
                }
                return false;
            }
        });
    }

    public void resetAttributes() {
        win.setAttributes(lp);
    }

    public void setEnableScreenAdapter(boolean enableScreenAdapter) {

        this.enableScreenAdapter = enableScreenAdapter;
    }

    /**
     * 获取布局
     *
     * @return
     */
    abstract protected int getLayoutResId();

    protected void initParams() {
    }

    protected void initEvents() {
    }

    public View getView(@IdRes int resId) {
        return root.findViewById(resId);
    }

    /**
     * 屏幕宽度适配
     */
    protected void screenAdapter() {
        if (null == mContext || !enableScreenAdapter) {
            return;
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = per2pxWidth(mContext.getResources(), perWidth);
        if (whPer > 0) {
            params.height = (int) (params.width / whPer);
        }
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.CENTER);
    }

    /**
     * 百分比转换为实际的像素值（宽度）
     *
     * @param per
     * @return
     */
    public static int per2pxWidth(Resources res, double per) {
        if (null == res) {
            return 0;
        }
        if (per < 0 || per > 1) {
            return 0;
        }
        DisplayMetrics dm = res.getDisplayMetrics();
        return (int) Math.ceil(dm.widthPixels * per);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
