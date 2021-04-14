package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.ClassRoomModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class ClassRoomManagerModel implements ClassRoomManagerContract.Model {
    @Override
    public DisposableObserver mechanismClassroomInsert(
            String mechanism_id,
            String name,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.mechanismClassroomInsert(
                mechanism_id,
                name,
                subscriber
        );
    }

    @Override
    public DisposableObserver mechanismClassroomUpdate(
            String id,
            String name,
            String status,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.mechanismClassroomUpdate(
                id,
                name,
                status,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryManagerClassroomListPage(
            String mechanism_id,
            Integer pageSize,
            Integer currentPage,
            DisposableObserver<ClassRoomModel> subscriber) {
        return UserServiceImpl.queryManagerClassroomListPage(
                mechanism_id,
                pageSize,
                currentPage,
                subscriber
        );
    }
}
