package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;

import io.reactivex.observers.DisposableObserver;

public class OnSaleCourseModel implements OnSaleCourseContract.Model {
    @Override
    public DisposableObserver queryExclusiveCourseList(
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
