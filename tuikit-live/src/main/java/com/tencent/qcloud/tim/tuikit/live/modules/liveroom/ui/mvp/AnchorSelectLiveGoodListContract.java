package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class AnchorSelectLiveGoodListContract {

    public interface Model extends IBaseModel {

        DisposableObserver queryExclusiveCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<CommodityModel> subscriber);

        DisposableObserver insertMasterSetPriceDisplay(
                String master_set_price_ids,
                String mechanism_id,
                String live_stream_prices,
                String live_streaming_id,
                String living_single_session_prices,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

    }

    public interface View extends IStateView {
        void showExclusiveCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities);
        void onInsertSuccess();
    }

    public interface Presenter {

        void queryExclusiveCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize);
        void insertMasterSetPriceDisplay(
                String master_set_price_ids,
                String mechanism_id,
                String live_stream_prices,
                String live_streaming_id,
                String living_single_session_prices);
    }
}
