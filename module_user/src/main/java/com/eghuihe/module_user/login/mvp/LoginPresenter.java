package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LoginResultModel;
import com.huihe.base_lib.model.login.WxLoginModel;
import com.huihe.base_lib.model.login.WxOpenidAppVerModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> implements LoginContract.Presenter {
    @Override
    protected LoginContract.Model createModule() {
        return new LoginModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void login(
            String loginName,
            String password,
            String login_type,
            Boolean is_teach_paypal) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().login(
                    loginName,
                    password,
                    login_type,
                    is_teach_paypal,
                    new NetworkSubscriber<LoginResultModel>(null) {
                        @Override
                        protected void onSuccess(LoginResultModel loginResultModel) {
                            LoginHelper.saveUserData(loginResultModel.getData());
                            getView().closeLoading();
                            LoginHelper.saveBoolean(LoginHelper.IS_IM_LOGIN, true);
                            getView().onLoginSuccess();
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
                                    getView().onWxOpenidAppVerSuccess(wxOpenidAppVerModel.getData(), wx_openid);
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
                            new NetworkSubscriber<WxLoginModel>(getView()) {
                                @Override
                                protected void onSuccess(WxLoginModel wxLoginModel) {
                                    LoginHelper.saveUserData(wxLoginModel.getData());
                                    getView().onWxLoginSuccess();
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    super.onFail(code, message);
                                    ToastUtils.showLongToast(getContext(), message);
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    getView().closeLoading();
                                }
                            }));
        }
    }
}
