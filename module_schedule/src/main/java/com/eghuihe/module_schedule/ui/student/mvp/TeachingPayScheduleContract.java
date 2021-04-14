package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayScheduleContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryOfflineSchedule(
                String user_id,
                String type,
                String status,
                Boolean is_comment,
                Integer currentPage,
                Integer pageSize,
                DisposableObserver<StudentScheduleModel> subscriber);

        DisposableObserver sign(
                String id,
                String status,
                DisposableObserver<InsertInfoResultModel> subscriber);
        DisposableObserver updateCancelCourse(
                String id,
                DisposableObserver<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onOfflineScheduleList(List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities);

        void onUpdatestatusSuccess();
    }

    public interface Presenter {
        void queryOfflineSchedule(
                String user_id,
                String type,
                String status,
                Boolean is_comment,
                Integer currentPage,
                Integer pageSize);

        void sign(
                String id,
                String status);
        void updateCancelCourse(
                String id);
    }
}
