package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.model.ClassRoomModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ClassRoomManagerContract {
    public interface Model extends IBaseModel {
        DisposableObserver mechanismClassroomInsert(
                String mechanism_id,
                String name,
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver mechanismClassroomUpdate(
                String id,
                String name,
                String status,
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver queryManagerClassroomListPage(
                String mechanism_id,
                Integer pageSize,
                Integer currentPage,
                DisposableObserver<ClassRoomModel> subscriber);
    }

    public interface View extends IStateView {

        void onInsertSuccess();

        void onUpdateSuccess();

        void onClassRoomList(List<ClassRoomEntity> classRoomEntities);
    }

    public interface Presenter {

        void mechanismClassroomInsert(
                String mechanism_id,
                String name);

        void mechanismClassroomUpdate(
                String id,
                String name,
                String status);

        void queryManagerClassroomListPage(
                String mechanism_id,
                Integer pageSize,
                Integer currentPage);
    }
}
