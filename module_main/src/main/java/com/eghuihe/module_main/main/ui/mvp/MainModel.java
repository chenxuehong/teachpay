package com.eghuihe.module_main.main.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserInfoModel;

import io.reactivex.observers.DisposableObserver;

public class MainModel implements MainContract.Model {
    @Override
    public DisposableObserver queryUserInfo(
            String user_id,
            NetworkSubscriber<UserInfoModel> subscriber) {
        return UserServiceImpl.queryUserInfo(
                user_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver userDeviceInsert(
            String model,
            String clientid,
            String user_id,
            String unique_id,
            String teach_pay_token,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.userDeviceInsert(
                model,
                clientid,
                user_id,
                unique_id,
                teach_pay_token,
                subscriber
        );
    }

}
