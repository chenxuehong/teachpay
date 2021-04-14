package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayHomeModel implements TeachingPayHomeContract.Model {
    @Override
    public DisposableObserver getBannerData(
            String type,
            String state,
            DisposableObserver<BannerModel> subscriber) {
        return UserServiceImpl.getBannerData(
                type,
                state,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryActivityListPageByType(
            String status,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String type,
            NetworkSubscriber<MasterSetPriceModel> subscriber) {
        return UserServiceImpl.queryActivityListPageByType(
                currentPage,
                pageSize,
                status,
                type,
                latitude,
                longitude,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismByType(
            Integer pageSize,
            Integer currentPage,
            String latitude,
            String longitude,
            String sortName,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismByType(
                pageSize,
                currentPage,
                latitude,
                longitude,
                sortName,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismInfo(
            String user_id,
            Integer Status,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismInfo(
                user_id,
                Status,
                type,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertUserCollection(
            String user_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertUserCollection(
                user_id,
                subscriber
        );
    }
}
