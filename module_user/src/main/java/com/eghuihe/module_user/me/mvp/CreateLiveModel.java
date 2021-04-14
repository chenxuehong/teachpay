package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class CreateLiveModel implements CreateLiveContract.Model {
    @Override
    public DisposableObserver insertMasterSetPriceStream(
            String master_set_price_ids,
            String start_time,
            String title,
            String mechanism_id,
            String live_stream_prices,
            String living_single_session_prices,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMasterSetPriceStream(
                master_set_price_ids,
                start_time,
                title,
                mechanism_id,
                live_stream_prices,
                living_single_session_prices,
                subscriber
        );
    }
}
