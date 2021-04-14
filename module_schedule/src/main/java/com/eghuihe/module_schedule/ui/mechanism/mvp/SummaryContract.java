package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.study.StudentModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class SummaryContract {
    public interface Model extends IBaseModel {

        DisposableObserver insertSummaryOffline(
                String appointment_id,
                String content,
                String master_id,
                String mechanism_id,
                String photo_url,
                DisposableObserver<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onSuccess();
    }

    public interface Presenter {
        void insertSummaryOffline(
                String appointment_id,
                String content,
                String master_id,
                String mechanism_id,
                String photo_url);
    }
}
