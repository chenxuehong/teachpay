package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismUserModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class CustomerManagerModel implements CustomerManagerContract.Model {
    @Override
    public DisposableObserver getMechanismUserList(
            Integer currentPage,
            Integer pageSize,
            String mechanism_id,
            DisposableObserver<MechanismUserModel> subscriber) {
        return UserServiceImpl.getMechanismUserList(
                currentPage,
                pageSize,
                mechanism_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver mechanismUserStatus(
            String id,
            Boolean is_new,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.mechanismUserStatus(
                id,
                is_new,
                subscriber
        );
    }
}
