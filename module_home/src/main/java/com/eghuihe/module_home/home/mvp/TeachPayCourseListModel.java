package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayCourseListModel implements TeachPayCourseListContract.Model {
    @Override
    public DisposableObserver queryNearByCourse(
            Integer status,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String type,
            NetworkSubscriber<MasterSetPriceModel> subscriber) {
        return UserServiceImpl.queryNearByCourse(
                status,
                currentPage,
                pageSize,
                latitude,
                longitude,
                type,
                subscriber
        );
    }
}
