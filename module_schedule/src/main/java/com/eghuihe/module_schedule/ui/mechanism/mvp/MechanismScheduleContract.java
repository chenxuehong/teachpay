package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MechanismScheduleContract {

    public interface Model extends IBaseModel {
        DisposableObserver queryMechanismOfflineSchedule(
                String mechanism_id,
                String type,
                String status,
                String identity_type,
                Integer currentPage,
                Integer pageSize,
                String start_time,
                String end_time,
                String offset,
                NetworkSubscriber<MechanismOfflineScheduleModel> subscriber);

        DisposableObserver deleteMasterAppointment(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onMechanismOfflineScheduleList(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities);
        void onDeleteCourseSuccess();
    }

    public interface Presenter {
        void queryMechanismOfflineSchedule(
                String mechanism_id,
                String type,
                String status,
                String identity_type,
                Integer currentPage,
                Integer pageSize,
                String start_time,
                String end_time,
                String offset);
        void deleteMasterAppointment(
                String id);
    }
}
