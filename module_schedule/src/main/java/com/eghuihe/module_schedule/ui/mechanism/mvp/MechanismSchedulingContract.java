package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MechanismSchedulingContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismOfflineSchedule(
                String mechanism_id,
                String type,
                String status,
                String start_time,
                String end_time,
                String offset,
                String identity_type,
                String create_type,
                NetworkSubscriber<MechanismOfflineScheduleModel> subscriber);

        DisposableObserver deleteMasterAppointment(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver insertCopyCourse(
                String id,
                String start_time,
                String end_time,
                String dates,
                String type,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onMechanismOfflineScheduleList(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities);
        void onDeleteCourseSuccess();
        void onCopyCourseSuccess();
    }

    public interface Presenter {
        void queryMechanismOfflineSchedule(
                String mechanism_id,
                String type,
                String status,
                String start_time,
                String end_time,
                String offset,
                String identity_type,
                String create_type);
        void deleteMasterAppointment(
                String id);
        void insertCopyCourse(
                String id,
                String start_time,
                String end_time,
                String dates,
                String type);
    }
}
