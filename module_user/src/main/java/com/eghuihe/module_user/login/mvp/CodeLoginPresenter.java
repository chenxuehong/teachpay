package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LoginResultModel;
import com.huihe.base_lib.model.login.WxLoginModel;
import com.huihe.base_lib.model.login.WxOpenidAppVerModel;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CodeLoginPresenter extends BasePresenter<CodeLoginContract.Model, CodeLoginContract.View> implements CodeLoginContract.Presenter {
    @Override
    public void sendMail(String mail, String type) {

        if (isViewAttached()) {
            getDisposableObservers().add(getModule().sendMail(mail, type, new NetworkSubscriber<GetMailCodeModel>(null) {
                @Override
                protected void onSuccess(GetMailCodeModel getMailCodeModel) {
                    getView().onSendSuccess();
                }
            }));
        }
    }

    @Override
    public void sendSms(String mobile, String type) {

        if (isViewAttached()) {
            getDisposableObservers().add(getModule().sendSms(mobile, type, new NetworkSubscriber<InsertInfoResultModel>(null) {
                @Override
                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                    getView().onSendSuccess();
                }
            }));
        }
    }

    @Override
    public void codeLogin(
            String login_name,
            String verification_code,
            Boolean is_teach_paypal) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().codeLogin(
                    login_name,
                    verification_code,
                    is_teach_paypal,
                    new NetworkSubscriber<LoginResultModel>(null) {
                        @Override
                        protected void onSuccess(LoginResultModel loginResultModel) {
                            getView().onLoginSuccess(loginResultModel.getData());
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
    public void wxOpenidAppVer(
            String wx_openid,
            Boolean is_teach_paypal) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().wxOpenidAppVer(
                            wx_openid,
                            is_teach_paypal,
                            new NetworkSubscriber<WxOpenidAppVerModel>(null) {
                @Override
                protected void onSuccess(WxOpenidAppVerModel wxOpenidAppVerModel) {
                    getView().onWxOpenidAppVer(wxOpenidAppVerModel.getData(), wx_openid);
                }
            }));
        }
    }

    @Override
    public void wxLogin(
            String login_name,
            String verification_code,
            String nick_name,
            String avatar,
            Integer sex,
            String wx_openid,
            Boolean is_teach_paypal) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().wxLogin(
                            login_name,
                            verification_code,
                            nick_name,
                            avatar,
                            sex,
                            wx_openid,
                            is_teach_paypal,
                            new NetworkSubscriber<WxLoginModel>(null) {
                @Override
                protected void onSuccess(WxLoginModel wxLoginModel) {
                    getView().onWxLogin(wxLoginModel.getData());
                }
            }));
        }
    }

    @Override
    protected CodeLoginContract.Model createModule() {
        return new CodeLoginModel();
    }

    @Override
    public void start() {

    }
}
