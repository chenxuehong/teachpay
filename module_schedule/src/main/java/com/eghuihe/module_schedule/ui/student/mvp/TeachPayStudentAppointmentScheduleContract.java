package com.eghuihe.module_schedule.ui.student.mvp;


import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayStudentAppointmentScheduleContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismOfflineAppointmentSchedule(
                String create_type,
                String end_time,
                String identity_type,
                String master_set_price_id,
                String mechanism_id,
                String offset,
                String start_time,
                String type,
                NetworkSubscriber<MechanismOfflineScheduleModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismOfflineAppointmentSchedule(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities);
    }

    public interface Presenter {
        void queryMechanismOfflineAppointmentSchedule(
                String create_type,
                String end_time,
                String identity_type,
                String master_set_price_id,
                String mechanism_id,
                String offset,
                String start_time,
                String type);
    }
}
