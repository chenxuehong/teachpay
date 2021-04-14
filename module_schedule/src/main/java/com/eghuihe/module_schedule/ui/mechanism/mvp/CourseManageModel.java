package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismClassModel;

import io.reactivex.observers.DisposableObserver;

public class CourseManageModel implements CourseManagerContract.Model {
    @Override
    public DisposableObserver queryMechanismClasses(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String status,
            String master_set_price_id,
            Boolean is_scheduling_over,
            NetworkSubscriber<MechanismClassModel> subscriber) {
        return UserServiceImpl.queryMechanismClasses(
                mechanism_id,
                currentPage,
                pageSize,
                master_set_price_id,
                is_scheduling_over,
                status,
                subscriber
        );
    }
}
