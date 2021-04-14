package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class NearMechanismListModel implements NearMechanismListContract.Model {
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
}
