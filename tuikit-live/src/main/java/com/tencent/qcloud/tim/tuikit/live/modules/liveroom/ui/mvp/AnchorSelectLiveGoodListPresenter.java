package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class AnchorSelectLiveGoodListPresenter extends BasePresenter<AnchorSelectLiveGoodListContract.Model,AnchorSelectLiveGoodListContract.View>
        implements AnchorSelectLiveGoodListContract.Presenter {
    @Override
    protected AnchorSelectLiveGoodListContract.Model createModule() {
        return new AnchorSelectLiveGoodListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryExclusiveCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryExclusiveCourseList(
                            mechanism_id,
                            type,
                            status,
                            appointment_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(null) {
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    AnchorSelectLiveGoodListContract.View view = getView();
                                    if (view != null) {
                                        view.showExclusiveCourseList(commodityModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    AnchorSelectLiveGoodListContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void insertMasterSetPriceDisplay(
            String master_set_price_ids,
            String mechanism_id,
            String live_stream_prices,
            String live_streaming_id,
            String living_single_session_prices) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertMasterSetPriceDisplay(
                            master_set_price_ids,
                            mechanism_id,
                            live_stream_prices,
                            live_streaming_id,
                            living_single_session_prices,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    AnchorSelectLiveGoodListContract.View view = getView();
                                    if (view!=null){
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    AnchorSelectLiveGoodListContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
