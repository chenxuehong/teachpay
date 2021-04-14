package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;

import io.reactivex.observers.DisposableObserver;

public class SettingModel implements SettingContract.Model {

    @Override
    public DisposableObserver closeAccount(String user_id, String status, NetworkSubscriber subscriber) {
       return UserServiceImpl.closeAccount(user_id,status,subscriber);
    }
}
