package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;

import io.reactivex.observers.DisposableObserver;

public class UseCouponActivitiesListModel implements UseCouponActivitiesListContract.Model {
    @Override
    public DisposableObserver queryActivityListPageByType(
            Integer currentPage,
            Integer pageSize,
            String status,
            String type,
            String latitude,
            String longitude,
            DisposableObserver<MasterSetPriceModel> subscriber) {
        return UserServiceImpl.queryActivityListPageByType(
                currentPage,
                pageSize,
                status,
                type,
                latitude,
                longitude,
                subscriber
        );
    }
}
