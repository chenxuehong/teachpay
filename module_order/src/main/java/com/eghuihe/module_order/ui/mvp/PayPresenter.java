package com.eghuihe.module_order.ui.mvp;

import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class PayPresenter extends BasePresenter<PayContract.Model,PayContract.View>
        implements PayContract.Presenter {
    @Override
    protected PayContract.Model createModule() {
        return new PayModel();
    }

    @Override
    public void start() {

    }
}
