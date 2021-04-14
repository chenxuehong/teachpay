package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.login.WxOpenidAppVerModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class LoginContract {
    public interface Model extends IBaseModel {

        DisposableObserver login(
                String loginName,
                String password,
                String login_type,
                Boolean is_teach_paypal,
                NetworkSubscriber subscriber);

        DisposableObserver wxOpenidAppVer(
                String wx_openid,
                Boolean is_teach_paypal,
                NetworkSubscriber subscriber);

        DisposableObserver wxLogin(
                String login_name,
                String verification_code,
                String nick_name,
                String avatar,
                Integer sex,
                String wx_openid,
                Boolean is_teach_paypal,
                NetworkSubscriber subscriber);
    }

    public interface View extends IStateView {

        void onLoginSuccess();

        void onWxOpenidAppVerSuccess(LoginResultEntity wxOpenidAppVerEntity, String wx_openid);

        void onWxLoginSuccess();
    }

    public interface Presenter {
        void login(String loginName,
                   String password,
                   String login_type,
                   Boolean is_teach_paypal);

        void wxOpenidAppVer(
                String wx_openid,
                Boolean is_teach_paypal);

        void wxLogin(
                String login_name,
                String verification_code,
                String nick_name,
                String avatar,
                Integer sex,
                String wx_openid,
                Boolean is_teach_paypal);
    }
}
