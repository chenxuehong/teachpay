package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class ArrangeMechanismCourseModel implements ArrangeMechanismCourseContract.Model {
    @Override
    public DisposableObserver insertMechanismCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String study_card_ids,
            String classroom,
            String number_of_lessons,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMechanismCourse(
                type,
                source,
                mechanism_id,
                master_id,
                title,
                start_time,
                end_time,
                offset,
                service_type,
                master_set_price_id,
                identity_type,
                study_card_ids,
                classroom,
                Integer.valueOf(number_of_lessons),
                number_of_lessons,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertOfflineCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String classroom,
            String connect_peoplenum,
            String number_of_lessons,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertOfflineCourse(
                type,
                source,
                mechanism_id,
                master_id,
                title,
                start_time,
                end_time,
                offset,
                service_type,
                master_set_price_id,
                identity_type,
                classroom,
                connect_peoplenum,
                number_of_lessons,
                subscriber
        );
    }

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
}
