package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class ArrangeMechanismCourseFixModel implements ArrangeMechanismCourseFixContract.Model {
    @Override
    public DisposableObserver queryMechanismClassroom(
            String mechanism_id,
            String start_time,
            String status,
            DisposableObserver<MechanismClassroomSelectModel> subscriber) {
        return UserServiceImpl.queryMechanismClassroom(
                mechanism_id,
                start_time,
                status,
                subscriber
        );
    }

    @Override
    public DisposableObserver arrangeMechanismFixCourse(
            String id,
            String type,
            String weekOfDays,
            Boolean is_repeat,
            String start_time,
            String end_time,
            String master_id,
            String start_date,
            String date,
            String classroom,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.arrangeMechanismFixCourse(
                id,
                type,
                weekOfDays,
                is_repeat,
                start_time,
                end_time,
                master_id,
                start_date,
                date,
                classroom,
                subscriber
        );
    }
}
