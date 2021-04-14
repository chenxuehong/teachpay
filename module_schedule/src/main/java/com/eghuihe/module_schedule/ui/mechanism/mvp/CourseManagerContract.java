package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismClassEntity;
import com.huihe.base_lib.model.MechanismClassModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class CourseManagerContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryMechanismClasses(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String status,
                String master_set_price_id,
                Boolean is_scheduling_over,
                NetworkSubscriber<MechanismClassModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismClassList(List<MechanismClassEntity> mechanismClassEntities);
    }

    public interface Presenter {
        void queryMechanismClasses(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String status,
                String master_set_price_id,
                Boolean is_scheduling_over);
    }
}
