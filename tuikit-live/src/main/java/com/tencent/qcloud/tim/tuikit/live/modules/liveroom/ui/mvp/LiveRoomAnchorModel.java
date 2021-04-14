package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class LiveRoomAnchorModel implements LiveRoomAnchorContract.Model {
    @Override
    public DisposableObserver updateAddClick(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateAddClick(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver updateOpenLiving(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateOpenLiving(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver updateCloseLiving(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateCloseLiving(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismInfo(
            String user_id,
            Integer Status,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismInfo(user_id, Status, type, subscriber);
    }
}
