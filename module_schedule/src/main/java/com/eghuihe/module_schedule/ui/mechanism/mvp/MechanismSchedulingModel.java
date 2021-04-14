package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class MechanismSchedulingModel implements MechanismSchedulingContract.Model {
    @Override
    public DisposableObserver queryMechanismOfflineSchedule(
            String mechanism_id,
            String type,
            String status,
            String start_time,
            String end_time,
            String offset,
            String identity_type,
            String create_type,
            NetworkSubscriber<MechanismOfflineScheduleModel> subscriber) {
        return UserServiceImpl.queryMechanismOfflineSchedule(
                mechanism_id,
                type,
                status,
                start_time,
                end_time,
                offset,
                identity_type,
                create_type,
                subscriber
        );
    }

    @Override
    public DisposableObserver deleteMasterAppointment(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.deleteMasterAppointment(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertCopyCourse(
            String id,
            String start_time,
            String end_time,
            String dates,
            String type,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertCopyCourse(
                id,
                start_time,
                end_time,
                dates,
                type,
                subscriber
        );
    }
}
