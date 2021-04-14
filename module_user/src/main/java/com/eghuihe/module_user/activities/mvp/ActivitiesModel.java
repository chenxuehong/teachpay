package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.BusinessActivityTypeModel;

import io.reactivex.observers.DisposableObserver;

public class ActivitiesModel implements ActivitiesContract.Model {
    @Override
    public DisposableObserver queryBusinessActivityTypeList(
            Integer currentPage,
            Integer pageSize,
            String status,
            DisposableObserver<BusinessActivityTypeModel> subscriber) {
        return UserServiceImpl.queryBusinessActivityTypeList(
                currentPage,
                pageSize,
                status,
                subscriber
        );
    }
}
