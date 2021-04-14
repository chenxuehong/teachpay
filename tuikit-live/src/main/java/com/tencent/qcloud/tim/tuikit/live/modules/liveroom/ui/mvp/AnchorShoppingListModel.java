package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class AnchorShoppingListModel implements AnchorShoppingListContract.Model {
    @Override
    public DisposableObserver queryMasterSetPriceDisplay(
            Integer currentPage,
            Integer pageSize,
            String status,
            String live_streaming_id,
            NetworkSubscriber<MasterSetPriceDisplayModel> subscriber) {
        return UserServiceImpl.queryMasterSetPriceDisplay(
                currentPage,
                pageSize,
                status,
                live_streaming_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver updateMasterSetPriceDisplay(
            String id,
            Boolean is_live_streaming,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateMasterSetPriceDisplay(
                id,
                is_live_streaming,
                subscriber
        );
    }
}
