package com.huihe.base_lib.ui;

import android.content.Context;

public interface IStateView {

    boolean isShowLoading();
    void showLoading();
    void showProgress(String msg);
    void showUploading();
    void closeLoading();
    void retry();
    /**
     * 空数据
     */
    void onEmpty();

    /**
     * 错误数据
     * @param errorMsg 错误信息
     */
    void onError(String errorMsg);
    /**
     * 上下文
     *
     * @return context
     */
    Context getContext();
}
