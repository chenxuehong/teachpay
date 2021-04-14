package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;

import io.reactivex.observers.DisposableObserver;

public class SelectMasterSetPriceListModel implements SelectMasterSetPriceListContract.Model {
    @Override
    public DisposableObserver queryMasterSetPriceList(
            String mechanism_id,
            String status,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseList(
                mechanism_id,
                null,
                status,
                null,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
