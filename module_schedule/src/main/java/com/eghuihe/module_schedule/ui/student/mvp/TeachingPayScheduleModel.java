package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayScheduleModel implements TeachingPayScheduleContract.Model {
    @Override
    public DisposableObserver queryOfflineSchedule(
            String user_id,
            String type,
            String status,
            Boolean is_comment,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<StudentScheduleModel> subscriber) {
        return UserServiceImpl.queryStudentOfflineSchedule(
                user_id,
                type,
                status,
                is_comment,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver sign(
            String id,
            String status,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.sign(
                id,
                status,
                subscriber
        );
    }

    @Override
    public DisposableObserver updateCancelCourse(
            String id,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateCancelCourse(
                id,
                subscriber
        );
    }

}
