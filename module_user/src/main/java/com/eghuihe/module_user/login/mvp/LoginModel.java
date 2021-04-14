package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;

import io.reactivex.observers.DisposableObserver;

public class LoginModel implements LoginContract.Model {

    @Override
    public DisposableObserver login(
            String loginName,
            String password,
            String login_type,
            Boolean is_teach_paypal,
            NetworkSubscriber subscriber) {
        return UserServiceImpl.login(
                loginName,
                password,
                "1",
                is_teach_paypal,
                subscriber);
    }

    @Override
    public DisposableObserver wxOpenidAppVer(
            String wx_openid,
            Boolean is_teach_paypal,
            NetworkSubscriber subscriber) {
        return UserServiceImpl.wxOpenidAppVer(wx_openid,is_teach_paypal, subscriber);
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
