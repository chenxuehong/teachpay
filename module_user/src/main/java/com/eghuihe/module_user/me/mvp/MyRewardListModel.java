package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MyPointsModel;

import io.reactivex.observers.DisposableObserver;

public class MyRewardListModel implements MyRewardListContract.Model {
    @Override
    public DisposableObserver getUserPointsList(
            Integer currentPage,
            Integer pageSize,
            String user_id,
            Boolean is_earnings,
            String start_time,
            DisposableObserver<MyPointsModel> subscriber) {
        return UserServiceImpl.getUserPointsList(
                currentPage,
                pageSize,
                user_id,
                is_earnings,
                start_time,
                subscriber
        );
    }
}
