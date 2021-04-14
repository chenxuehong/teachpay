package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.TeachPaypalDetailModel;
import com.huihe.base_lib.model.TeachPaypalUserGoldTypeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.InsertSignDayModel;

import io.reactivex.observers.DisposableObserver;

public class MyRewardModel implements MyRewardContract.Model {
    @Override
    public DisposableObserver queryTeachPaypalDetail(
            String user_id,
            NetworkSubscriber<TeachPaypalDetailModel> subscriber) {
        return UserServiceImpl.queryTeachPaypalDetail(
                user_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryTeachPaypalUserGoldType(
            String user_id,
            String status,
            Boolean is_teach_paypal,
            NetworkSubscriber<TeachPaypalUserGoldTypeModel> subscriber) {
        return UserServiceImpl.queryTeachPaypalUserGoldType(
                user_id,
                status,
                is_teach_paypal,
                subscriber
        );
    }

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

    @Override
    public DisposableObserver insertLoginSignIn(
            String user_id,
            NetworkSubscriber<InsertSignDayModel> subscriber) {
        return UserServiceImpl.insertLoginSignIn(
                user_id,
                subscriber
        );
    }
}
