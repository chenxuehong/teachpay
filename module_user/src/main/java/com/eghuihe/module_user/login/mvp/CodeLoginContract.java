package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.login.WxLoginModel;
import com.huihe.base_lib.model.login.WxOpenidAppVerModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class CodeLoginContract {
    public interface Model extends IBaseModel {

        DisposableObserver sendMail(
                String mail,
                String type,
                NetworkSubscriber subscriber);

        DisposableObserver sendSms(
                String mobile,
                String type,
                NetworkSubscriber subscriber);

        DisposableObserver codeLogin(
                String login_name,
                String verification_code,
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

        void onLoginSuccess(LoginResultEntity loginResultEntity);

        void onSendSuccess();

        void onWxOpenidAppVer(
                LoginResultEntity wxOpenidAppVerEntity,
                String wx_openid);

        void onWxLogin(WxLoginModel.WxLoginEntity wxLoginEntity);
    }

    public interface Presenter {
        void sendMail(String mail,
                      String type);

        void sendSms(String mobile,
                     String type);

        void codeLogin(
                String login_name,
                String verification_code,
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
