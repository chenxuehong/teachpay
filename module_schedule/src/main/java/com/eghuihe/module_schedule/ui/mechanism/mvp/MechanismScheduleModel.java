package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class MechanismScheduleModel implements MechanismScheduleContract.Model {
    @Override
    public DisposableObserver queryMechanismOfflineSchedule(
            String mechanism_id,
            String type,
            String status,
            String identity_type,
            Integer currentPage,
            Integer pageSize,
            String start_time,
            String end_time,
            String offset,
            NetworkSubscriber<MechanismOfflineScheduleModel> subscriber) {
        return UserServiceImpl.queryMechanismOfflineStatusSchedule(
                mechanism_id,
                type,
                status,
                identity_type,
                currentPage,
                pageSize,
                start_time,
                end_time,
                offset,
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
}
