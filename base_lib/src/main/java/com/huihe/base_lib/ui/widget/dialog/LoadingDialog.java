package com.huihe.base_lib.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.LVCircularRing;

public class LoadingDialog {
    private LVCircularRing mLoadingView;
    private Dialog mLoadingDialog;
    private Context context;
    private String msg = "加载中···";
    private boolean cancelable = false;
    private boolean isShow;

    public LoadingDialog(Context context) {
        this.context = context;
        msg = context.getResources().getString(R.string.Loading);
        // 创建自定义样式的Dialog
        mLoadingDialog = new Dialog(context, R.style.LoadingDialog);
    }

    /**
     * 设置提示信息
     */
    public LoadingDialog setTitleText(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 返回键是否可用
     */
    public LoadingDialog setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public void show() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            View view = View.inflate(context, R.layout.dialog_loading, null);
            // 获取整个布局
            LinearLayout layout = view.findViewById(R.id.dialog_view);
            // 页面中的LoadingView
            mLoadingView = view.findViewById(R.id.lvcr_loading);
            // 页面中显示文本
            TextView loadingText = view.findViewById(R.id.loading_text);
            // 显示文本
            loadingText.setText(msg);
            // 设置返回键无效
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            mLoadingDialog.show();
            mLoadingView.startAnim();
            isShow = true;
        }

    }

    public void dismiss() {
        if (mLoadingDialog != null && isShow) {
            if (mLoadingView != null)
                mLoadingView.stopAnim();
            mLoadingDialog.dismiss();
            isShow = false;
        }
    }
    public boolean isShowing() {
        return isShow;
    }
}
