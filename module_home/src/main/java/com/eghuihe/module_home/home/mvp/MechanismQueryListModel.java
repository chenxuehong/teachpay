package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class MechanismQueryListModel implements MechanismQueryListContract.Model {
    @Override
    public DisposableObserver queryMechanismByEs(
            String mechanism_name,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String sortName,
            String categories,
            String categories_child,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismByEs(
                mechanism_name,
                currentPage,
                pageSize,
                latitude,
                longitude,
                sortName,
                categories,
                categories_child,
                type,
                subscriber
        );
    }
}
