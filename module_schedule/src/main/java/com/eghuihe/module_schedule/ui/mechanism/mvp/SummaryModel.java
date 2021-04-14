package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class SummaryModel implements SummaryContract.Model {
    @Override
    public DisposableObserver insertSummaryOffline(
            String appointment_id,
            String content,
            String master_id,
            String mechanism_id,
            String photo_url,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertSummaryOffline(
                appointment_id,
                content,
                master_id,
                mechanism_id,
                photo_url,
                subscriber
        );
    }
}
