package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterContract.Model, RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void sendMail(String mail, String type) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().sendMail(
                            mail,
                            type,
                            new NetworkSubscriber<GetMailCodeModel>(null) {
                                @Override
                                protected void onSuccess(GetMailCodeModel getMailCodeModel) {
                                    getView().showSendCodeSuccess();
                                }
                            })
            );
        }
    }

    @Override
    public void sendSms(String mobile, String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().sendSms(
                            mobile,
                            type,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    getView().showSendCodeSuccess();
                                }
                            })
            );
        }
    }

    @Override
    public void register(String verification_code, String login_name) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().register(
                            verification_code,
                            login_name,
                            new NetworkSubscriber<RegisterUserInfoModel>(null) {
                                @Override
                                protected void onSuccess(RegisterUserInfoModel registerUserInfoModel) {
                                    getView().onRegisterSuccess(registerUserInfoModel);
                                }
                            })
            );
        }
    }

    @Override
    protected RegisterContract.Model createModule() {
        return new RegisterModel();
    }

    @Override
    public void start() {

    }
}
