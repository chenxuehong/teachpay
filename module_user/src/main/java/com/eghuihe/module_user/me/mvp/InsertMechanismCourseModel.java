package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class InsertMechanismCourseModel implements InsertMechanismCourseContract.Model {
    @Override
    public DisposableObserver queryMechanismDetailInfoListById(
            String id, String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismDetailInfoListById(
                id,
                type,
                subscriber
        );
    }
}
