package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LiveModel;

import io.reactivex.observers.DisposableObserver;

public class LiveHistoryListModel implements LiveHistoryListContract.Model {
    @Override
    public DisposableObserver getLiveMasterSetPriceList(
            Integer currentPage,
            Integer pageSize,
            String mechanism_id,
            String status,
            DisposableObserver<LiveModel> subscriber) {
        return UserServiceImpl.getLiveMasterSetPriceList(
                currentPage,
                pageSize,
                mechanism_id,
                status,
                subscriber
        );
    }
}
