package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismIncomeListModel;
import com.huihe.base_lib.model.MechanismIncomeStatisticsModel;

import io.reactivex.observers.DisposableObserver;

public class IncomeCenterModel implements IncomeCenterContract.Model {
    @Override
    public DisposableObserver queryMechanismOfflineDetailsCount(
            String mechanism_id,
            DisposableObserver<MechanismIncomeStatisticsModel> subscriber) {
        return UserServiceImpl.queryMechanismOfflineDetailsCount(
                mechanism_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismOfflineDetails(
            String mechanism_id,
            String study_type,
            Boolean finished,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<MechanismIncomeListModel> subscriber) {
        return UserServiceImpl.queryMechanismOfflineDetails(
                mechanism_id,
                study_type,
                finished,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
