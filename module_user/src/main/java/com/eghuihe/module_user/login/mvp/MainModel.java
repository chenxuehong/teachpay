package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;

import io.reactivex.observers.DisposableObserver;

public class MainModel implements MainContract.Model {

    @Override
    public DisposableObserver getBannerData(String type, String state, NetworkSubscriber subscriber) {
       return UserServiceImpl.getBannerData(type, state, subscriber);
    }

}
