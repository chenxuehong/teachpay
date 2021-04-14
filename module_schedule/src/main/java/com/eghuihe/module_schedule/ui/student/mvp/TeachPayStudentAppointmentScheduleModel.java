package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayStudentAppointmentScheduleModel implements TeachPayStudentAppointmentScheduleContract.Model {
    @Override
    public DisposableObserver queryMechanismOfflineAppointmentSchedule(
            String create_type,
            String end_time,
            String identity_type,
            String master_set_price_id,
            String mechanism_id,
            String offset,
            String start_time,
            String type,
            NetworkSubscriber<MechanismOfflineScheduleModel> subscriber) {
        return UserServiceImpl.queryMechanismOfflineAppointmentSchedule(
                create_type,
                end_time,
                identity_type,
                master_set_price_id,
                mechanism_id,
                offset,
                start_time,
                type,
                subscriber
        );
    }

}
