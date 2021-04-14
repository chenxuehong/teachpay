package com.tencent.qcloud.tim.uikit.component.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

import io.reactivex.observers.DisposableObserver;

public class ChatModel implements ChatContract.Model {
    @Override
    public DisposableObserver queryMechanismCourseListById(
            String id,
            NetworkSubscriber<CommodityOldModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseListById(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver updateUserAppointmentUserConfirm(
            String id,
            Boolean whether,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateUserAppointmentUserConfirm(
                id,
                whether,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMasterAppointmentStatusById(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.queryMasterAppointmentStatusById(
                id,
                subscriber
        );
    }
}
