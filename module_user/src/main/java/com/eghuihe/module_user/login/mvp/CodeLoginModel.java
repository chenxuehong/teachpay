package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;

import io.reactivex.observers.DisposableObserver;

public class CodeLoginModel implements CodeLoginContract.Model {
    @Override
    public DisposableObserver sendMail(String mail, String type, NetworkSubscriber subscriber) {
        return UserServiceImpl.sendMail(mail, type, subscriber);
    }

    @Override
    public DisposableObserver sendSms(String mobile, String type, NetworkSubscriber subscriber) {
        return UserServiceImpl.sendSms(mobile, type, null, subscriber);
    }

    @Override
    public DisposableObserver codeLogin(
            String login_name,
            String verification_code,
            Boolean is_teach_paypal,
            NetworkSubscriber subscriber) {
        return UserServiceImpl.codeLogin(
                login_name,
                verification_code,
                is_teach_paypal,
                subscriber);
    }

    @Override
    public DisposableObserver wxOpenidAppVer(
            String wx_openid,
            Boolean is_teach_paypal,
            NetworkSubscriber subscriber) {
        return UserServiceImpl.wxOpenidAppVer(
                wx_openid,
                is_teach_paypal,
                subscriber);
    }

    @Override
    public DisposableObserver wxLogin(
            String login_name,
            String verification_code,
            String nick_name,
            String avatar,
            Integer sex,
            String wx_openid,
            Boolean is_teach_paypal,
            NetworkSubscriber subscriber) {
        return UserServiceImpl.wxLogin(
                login_name,
                verification_code,
                nick_name,
                avatar,
                sex,
                wx_openid,
                is_teach_paypal,
                subscriber);
    }
}
