package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class ClassManagerModel implements ClassManagerContract.Model {
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

    @Override
    public DisposableObserver updateMergerClass(
            String id,
            String merger_ids,
            String status,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateMergerClass(
                id,
                merger_ids,
                status,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryExclusiveCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseList(
                mechanism_id,
                type,
                status,
                appointment_type,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
