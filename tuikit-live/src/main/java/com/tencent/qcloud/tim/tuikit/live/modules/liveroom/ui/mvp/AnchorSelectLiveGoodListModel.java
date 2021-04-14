package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class AnchorSelectLiveGoodListModel implements AnchorSelectLiveGoodListContract.Model {
    @Override
    public DisposableObserver queryExclusiveCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseList(
                mechanism_id,
                type,
                status,
                appointment_type,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertMasterSetPriceDisplay(
            String master_set_price_ids,
            String mechanism_id,
            String live_stream_prices,
            String live_streaming_id,
            String living_single_session_prices,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMasterSetPriceDisplay(
                master_set_price_ids,
                mechanism_id,
                live_stream_prices,
                live_streaming_id,
                living_single_session_prices,
                subscriber
        );
    }
}
