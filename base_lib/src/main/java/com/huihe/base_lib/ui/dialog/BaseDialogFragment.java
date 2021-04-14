package com.huihe.base_lib.ui.dialog;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.widget.dialog.LoadingDialog;
import com.huihe.base_lib.utils.DensityUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment extends AppCompatDialogFragment implements IStateView {

    private Unbinder unbinder;
    protected View mContainer;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContainer = inflater.inflate(R.layout.fragment_dialog, container, false);
        ((ViewGroup) mContainer.findViewById(R.id.fragment_dialog_fl_container)).addView(getLayoutInflater().inflate(getLayoutId(), null));
        unbinder = ButterKnife.bind(this, mContainer);
        return mContainer;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected void initView() {

    }

    @Override
    public void onStart() {
        super.onStart();
        setDialog();
    }

    private void setDialog() {
        // 圆角背景
        Window window = getDialog().getWindow();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setStroke(0, Color.WHITE);
        window.setBackgroundDrawable(drawable);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        window.setAttributes(lp);

        // 设置宽高和位置
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                DensityUtils.getScreenHeight(getContext()) * 2 / 3);

        // 设置显示和退出动画
        window.setWindowAnimations(R.style.showPopupAnimation);

        // 是否为强制对话框
        this.setCancelable(true);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    @Override
    public void showLoading() {
        if (isShowLoading()) {
            if (loadingDialog != null) {
                loadingDialog.setCancelable(true);
                loadingDialog.show();
            }
        }
    }

    @Override
    public void showProgress(String msg) {
        if (loadingDialog != null) {
            loadingDialog.setCancelable(true);
            loadingDialog.setTitleText(msg);
            loadingDialog.show();
        }
    }

    @Override
    public void showUploading() {
        if (loadingDialog != null) {
            loadingDialog.setCancelable(true);
            loadingDialog.setTitleText("正在上传...");
            loadingDialog.show();
        }
    }

    @Override
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(String errorMsg) {

    }
}
