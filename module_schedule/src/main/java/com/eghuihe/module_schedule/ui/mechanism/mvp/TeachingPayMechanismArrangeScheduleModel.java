package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayMechanismArrangeScheduleModel implements TeachingPayMechanismArrangeScheduleContract.Model {
    @Override
    public DisposableObserver queryIsMechanismCourse(
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.queryIsMechanismCourse(
                mechanism_id,
                subscriber
        );
    }
}
