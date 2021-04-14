package com.huihe.base_lib.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.fragment.BaseFragment;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void retry() {
        super.retry();
        if (presenter != null) {
            presenter.dispose();
        }
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }
}
