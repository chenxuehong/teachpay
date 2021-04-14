package com.huihe.base_lib.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpTitleActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    private CustomerTitle customerTitle;
    public View inflate;
    private ViewStub emptyView;
    private Unbinder unbinder;

    public CustomerTitle getCustomerTitle() {
        return customerTitle;
    }

    @Override
    protected boolean useButterKnife() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.acivity_base;
    }

    @Override
    protected void initView() {
        customerTitle = findViewById(R.id.activity_base_customer_title);
        inflate = getLayoutInflater().inflate(getChildLayoutId(), null);
        ((ViewGroup) findViewById(R.id.fl_content)).addView(inflate);
        unbinder = ButterKnife.bind(this, this.inflate);
        initTitle(customerTitle);
    }

    protected void initTitle(CustomerTitle customerTitle) {

    }

    public void setTitle(String title) {
        customerTitle.setTitle(title);
    }

    public void setTitle(int resId) {
        customerTitle.setTitle(getResources().getString(resId));
    }

    protected abstract int getChildLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        super.showLoading();
        hideEmptyView();
    }

    //***************************************空页面方法*************************************
    protected void showEmptyView() {
        showEmptyOrErrorView(getString(R.string.no_Data), R.drawable.kong_error);
    }


    protected void showErrorView() {
        showEmptyOrErrorView(getString(R.string.Network_request_failed), R.drawable.bg_no_net);
    }

    public void showEmptyOrErrorView(String text, int img) {
        if (emptyView == null) {
            emptyView = findViewById(R.id.vs_empty);
        }
        emptyView.setVisibility(View.VISIBLE);
        findViewById(R.id.iv_empty).setBackgroundResource(img);
        ((TextView) findViewById(R.id.tv_empty)).setText(text);
        findViewById(R.id.tv_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPageClick();
            }
        });
    }

    protected void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    /**
     * 空页面被点击
     */
    protected void onPageClick() {

    }
}
