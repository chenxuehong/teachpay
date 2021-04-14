package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SettingPresenter extends BasePresenter<SettingContract.Model, SettingContract.View> implements SettingContract.Presenter {

    @Override
    public void closeAccount(String user_id) {

        if (isViewAttached()) {
            getDisposableObservers().add(getModule().closeAccount(user_id, "4", new NetworkSubscriber<InsertInfoResultModel>(null) {
                @Override
                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                    getView().closeLoading();
                    getView().showCloseActivityFinish();
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    getView().closeLoading();
                }
            }));
        }
    }

    @Override
    protected SettingContract.Model createModule() {
        return new SettingModel();
    }

    @Override
    public void start() {

    }
}
