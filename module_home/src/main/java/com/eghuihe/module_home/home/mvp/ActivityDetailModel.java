package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class ActivityDetailModel implements ActivityDetailContract.Model {
    @Override
    public DisposableObserver insertH5GetCoupon(
            String login_name,
            String verification_code,
            String nick_name,
            String sex,
            String preference,
            String relationships,
            String age,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertH5GetCoupon(
                login_name,
                verification_code,
                nick_name,
                sex,
                preference,
                relationships,
                age,
                subscriber
        );
    }

    @Override
    public DisposableObserver sendSms(String mobile, NetworkSubscriber subscriber) {
        return UserServiceImpl.sendSms(mobile, null, null, subscriber);
    }
}
