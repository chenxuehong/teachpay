package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ArrangeMechanismCourseFixContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryMechanismClassroom(
                String mechanism_id,
                String start_time,
                String status,
                DisposableObserver<MechanismClassroomSelectModel> subscriber);

        // 安排固定课程
        DisposableObserver arrangeMechanismFixCourse(
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
                DisposableObserver<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onClassRoomList(List<ClassRoomEntity> classRoomEntities);

        void onInsertSuccess();
    }

    public interface Presenter {
        void queryMechanismClassroom(
                String mechanism_id,
                String start_time,
                String status);

        void arrangeMechanismFixCourse(
                String id,
                String type,
                String weekOfDays,
                Boolean is_repeat,
                String start_time,
                String end_time,
                String master_id,
                String start_date,
                String date,
                String classroom);
    }
}
