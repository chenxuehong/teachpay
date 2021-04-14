package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class MotherTongueSettingModel implements MotherTongueSettingContract.Model {
    @Override
    public DisposableObserver setMotherTongue(
            String user_id,
            String mother_tongue,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.setMotherTongue(
                user_id,
                mother_tongue,
                subscriber
        );
    }
}
