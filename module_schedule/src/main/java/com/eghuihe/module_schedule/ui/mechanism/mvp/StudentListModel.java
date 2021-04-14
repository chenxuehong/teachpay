package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.ClassStudentModel;

import io.reactivex.observers.DisposableObserver;

public class StudentListModel implements StudentListContract.Model {
    @Override
    public DisposableObserver queryStudentInfoList(
            String type,
            String mechanism_id,
            String studycard_id,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<ClassStudentModel> subscriber) {
        return UserServiceImpl.queryStudentInfoList(
                type,
                mechanism_id,
                studycard_id,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
