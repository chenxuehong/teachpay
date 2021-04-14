package com.huihe.base_lib.ui.fragment;

import android.view.View;
import android.view.ViewStub;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.widget.title.CommonTitle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpTitleFragment<P extends BasePresenter> extends BaseMvpFragment<P> {

    private CommonTitle commonTitle;
    private ViewStub vsContainer;
    private Unbinder unbinder;

    @Override
    protected boolean useButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_title;
    }

    @Override
    protected void initView() {
        commonTitle = mContainer.findViewById(R.id.fragment_title_commonTitle);
        vsContainer = mContainer.findViewById(R.id.fragment_title_vs_container);
        vsContainer.setLayoutResource(getChildLayoutId());
        View inflate = vsContainer.inflate();
        unbinder = ButterKnife.bind(this, inflate);
        initTitle(commonTitle);
    }

    protected abstract int getChildLayoutId();

    protected void initTitle(CommonTitle commonTitle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
