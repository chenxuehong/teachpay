package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismClassModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class SelectClassListModel implements SelectClassListContract.Model {
    @Override
    public DisposableObserver queryMechanismClasses(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String master_set_price_id,
            Boolean is_scheduling_over,
            String status,
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
    @Override
    public DisposableObserver insertMechanismClasses(
            String mechanism_id,
            String name,
            String master_set_price_id,
            String student_count_max,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMechanismClasses(
                mechanism_id,
                name,
                master_set_price_id,
                student_count_max,
                subscriber
        );
    }
}
