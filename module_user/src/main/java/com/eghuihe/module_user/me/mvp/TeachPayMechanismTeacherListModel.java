package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismTeacherListModel implements TeachPayMechanismTeacherListContract.Model {
    @Override
    public DisposableObserver queryMechanismMasterInfoList(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String type,
            String status,
            NetworkSubscriber<MasterInfoHomeModel> subscriber) {
        return UserServiceImpl.queryMechanismMasterInfoList(
                mechanism_id,
                currentPage,
                pageSize,
                type,
                status,
                subscriber
        );
    }
}
