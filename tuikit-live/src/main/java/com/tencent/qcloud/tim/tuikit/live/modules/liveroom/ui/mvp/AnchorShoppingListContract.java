package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class AnchorShoppingListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMasterSetPriceDisplay(
                Integer currentPage,
                Integer pageSize,
                String status,
                String live_streaming_id,
                NetworkSubscriber<MasterSetPriceDisplayModel> subscriber);

        DisposableObserver updateMasterSetPriceDisplay(
                String id,
                Boolean is_live_streaming,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void showMasterSetPriceDisplayList(List<MasterSetPriceEntity> masterSetPriceEntities);
        void onUpdateSuccess();
    }

    public interface Presenter {
        void queryMasterSetPriceDisplay(
                Integer currentPage,
                Integer pageSize,
                String status,
                String live_streaming_id);
        void updateMasterSetPriceDisplay(
                String id,
                Boolean is_live_streaming);
    }
}
