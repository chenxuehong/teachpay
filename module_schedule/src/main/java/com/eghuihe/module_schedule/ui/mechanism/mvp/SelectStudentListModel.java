package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.study.StudentModel;

import io.reactivex.observers.DisposableObserver;

public class SelectStudentListModel implements SelectStudentListContract.Model {
    @Override
    public DisposableObserver queryStudentList(
            String mechanism_id,
            String status,
            String studycard_id,
            String type,
            Integer pageSize,
            Integer currentPage,
            DisposableObserver<StudentModel> subscriber) {
        return UserServiceImpl.queryStudentList(
                mechanism_id,
                status,
                studycard_id,
                type,
                pageSize,
                currentPage,
                subscriber
        );
    }
}
