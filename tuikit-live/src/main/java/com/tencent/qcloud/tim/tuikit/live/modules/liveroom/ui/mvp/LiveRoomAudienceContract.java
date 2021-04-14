package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class LiveRoomAudienceContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismDisplayIsLiving(
                String live_streaming_id,
                String status,
                NetworkSubscriber<MasterSetPriceModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismDisplay(MasterSetPriceEntity masterSetPriceEntity);
    }

    public interface Presenter {
        void queryMechanismDisplayIsLiving(
                String live_streaming_id,
                String status);
    }
}
