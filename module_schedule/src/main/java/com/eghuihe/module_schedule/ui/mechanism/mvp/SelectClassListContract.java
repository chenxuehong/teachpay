package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.model.MechanismClassModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class SelectClassListContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryMechanismClasses(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String master_set_price_id,
                Boolean is_scheduling_over,
                String status,
                NetworkSubscriber<MechanismClassModel> subscriber);
        DisposableObserver insertMechanismClasses(
                String mechanism_id,
                String name,
                String master_set_price_id,
                String student_count_max,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismClassList(List<MechanismClassEntity> mechanismClassEntities);
        void onInsertMechanismClassSuccess();
    }

    public interface Presenter {
        void queryMechanismClasses(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String master_set_price_id,
                Boolean is_scheduling_over,
                String status);
        void insertMechanismClasses(
                String mechanism_id,
                String name,
                String master_set_price_id,
                String student_count_max);
    }
}
