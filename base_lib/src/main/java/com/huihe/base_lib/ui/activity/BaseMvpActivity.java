package com.huihe.base_lib.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P presenter;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
