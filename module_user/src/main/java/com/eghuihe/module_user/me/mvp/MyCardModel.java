package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.UserCouponModel;

import io.reactivex.observers.DisposableObserver;

public class MyCardModel implements MyCardContract.Model {
    @Override
    public DisposableObserver queryUserCouponList(
            String user_id,
            String status,
            Integer pageSize,
            Integer currentPage,
            NetworkSubscriber<UserCouponModel> subscriber) {
        return UserServiceImpl.queryUserCouponList(
                user_id,
                status,
                pageSize,
                currentPage,
                subscriber
        );
    }
}
