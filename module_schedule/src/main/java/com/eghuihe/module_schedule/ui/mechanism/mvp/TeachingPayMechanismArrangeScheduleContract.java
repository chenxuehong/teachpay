package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayMechanismArrangeScheduleContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryIsMechanismCourse(
                String mechanism_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onIsMechanismCourse(String status);
    }

    public interface Presenter {
        void queryIsMechanismCourse(
                String mechanism_id);
    }
}
