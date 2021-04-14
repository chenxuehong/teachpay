package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.UserStatisticsModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.UserInfoModel;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayMeModel implements TeachingPayMeContract.Model {
    @Override
    public DisposableObserver getBannerData(String type, String state, DisposableObserver<BannerModel> subscriber) {
        return UserServiceImpl.getBannerData(type, state, subscriber);
    }

    @Override
    public DisposableObserver queryMechanismInfo(String user_id, Integer Status, String type, NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismInfo(user_id, Status, type, subscriber);
    }

    @Override
    public DisposableObserver queryTeachPayUserStatistics(
            String user_id, NetworkSubscriber<UserStatisticsModel> subscriber) {
        return UserServiceImpl.queryTeachPayUserStatistics(
                user_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryUserInfo(
            String user_id,
            NetworkSubscriber<UserInfoModel> subscriber) {
        return UserServiceImpl.queryUserInfo(
                user_id,
                subscriber
        );
    }
}
