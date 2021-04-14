package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class CreateLiveContract {
    public interface Model extends IBaseModel {

        DisposableObserver insertMasterSetPriceStream(
                String master_set_price_ids,
                String start_time,
                String title,
                String mechanism_id,
                String live_stream_prices,
                String living_single_session_prices,
                DisposableObserver<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void insertSuccess(String data);
    }

    public interface Presenter {
        void insertMasterSetPriceStream(
                String master_set_price_ids,
                String start_time,
                String title,
                String mechanism_id,
                String live_stream_prices,
                String living_single_session_prices);
    }
}
