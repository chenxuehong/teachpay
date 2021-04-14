package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.SummaryInfoModel;

import io.reactivex.observers.DisposableObserver;

public class SummaryDetailModel implements SummaryDetailContract.Model {
    @Override
    public DisposableObserver querySummaryList(
            String appointment_id,
            DisposableObserver<SummaryInfoModel> subscriber) {
        return UserServiceImpl.querySummaryList(
                appointment_id,
                subscriber
        );
    }
}
