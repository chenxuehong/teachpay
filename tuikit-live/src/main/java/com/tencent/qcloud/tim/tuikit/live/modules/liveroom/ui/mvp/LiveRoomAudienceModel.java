package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;

import io.reactivex.observers.DisposableObserver;

public class LiveRoomAudienceModel implements LiveRoomAudienceContract.Model {
    @Override
    public DisposableObserver queryMechanismDisplayIsLiving(
            String live_streaming_id,
            String status,
            NetworkSubscriber<MasterSetPriceModel> subscriber) {
        return UserServiceImpl.queryMechanismDisplayIsLiving(
                live_streaming_id,
                status,
                subscriber
        );
    }
}
