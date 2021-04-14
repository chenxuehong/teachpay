package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ActivityDetailPresenter extends BasePresenter<ActivityDetailContract.Model, ActivityDetailContract.View>
        implements ActivityDetailContract.Presenter {
    @Override
    public void insertH5GetCoupon(
            String login_name,
            String verification_code,
            String nick_name,
            String sex,
            String preference,
            String relationships,
            String age) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertH5GetCoupon(
                            login_name,
                            verification_code,
                            nick_name,
                            sex,
                            preference,
                            relationships,
                            age,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ActivityDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ActivityDetailContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void sendSms(String mobile) {

        if (isViewAttached()) {
            getDisposableObservers().add(getModule().sendSms(mobile, new NetworkSubscriber<InsertInfoResultModel>(null) {
                @Override
                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                    getView().onGetCodeSuccess();
                }
            }));
        }
    }

    @Override
    protected ActivityDetailContract.Model createModule() {
        return new ActivityDetailModel();
    }

    @Override
    public void start() {

    }
}
