package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.StudentCoursePackageModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayStudentSchedulePackageModel implements TeachPayStudentSchedulePackageContract.Model {
    @Override
    public DisposableObserver queryStudentExclusiveCoursesList(
            String type,
            String user_id,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<StudentCoursePackageModel> subscriber) {
        return UserServiceImpl.queryStudentExclusiveCoursesList(
                type,
                user_id,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertUserGrouping(
            String user_id,
            String master_set_price_id,
            String study_card_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertUserGrouping(
                user_id,
                master_set_price_id,
                study_card_id,
                subscriber
        );
    }
}
