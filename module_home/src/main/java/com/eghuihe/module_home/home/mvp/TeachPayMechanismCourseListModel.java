package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismCourseListModel implements TeachPayMechanismCourseListContract.Model {
    @Override
    public DisposableObserver queryMechanismCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseList(
                mechanism_id,
                type,
                status,
                appointment_type,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
