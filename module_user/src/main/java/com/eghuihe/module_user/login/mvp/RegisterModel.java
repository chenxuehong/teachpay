package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class RegisterModel implements RegisterContract.Model {
    @Override
    public DisposableObserver sendMail(String mail, String type, NetworkSubscriber<GetMailCodeModel> subscriber) {
        return UserServiceImpl.sendMail(mail, type, subscriber);
    }

    @Override
    public DisposableObserver sendSms(String mobile, String type, NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.sendSms(mobile, type, null, subscriber);
    }

    @Override
    public DisposableObserver register(String verification_code, String login_name, NetworkSubscriber<RegisterUserInfoModel> subscriber) {
        return UserServiceImpl.register(verification_code, login_name, subscriber);
    }
}
