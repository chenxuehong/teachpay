package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class AudienceShoppingListPresenter extends BasePresenter<AudienceShoppingListContract.Model,AudienceShoppingListContract.View>
        implements AudienceShoppingListContract.Presenter {
    @Override
    protected AudienceShoppingListContract.Model createModule() {
        return new AudienceShoppingListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMasterSetPriceDisplay(
            Integer currentPage,
            Integer pageSize,
            String status,
            String live_streaming_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMasterSetPriceDisplay(
                            currentPage,
                            pageSize,
                            status,
                            live_streaming_id,
                            new NetworkSubscriber<MasterSetPriceDisplayModel>(null){
                                @Override
                                protected void onSuccess(MasterSetPriceDisplayModel masterSetPriceDisplayModel) {
                                    AudienceShoppingListContract.View view = getView();
                                    if (view!=null){
                                        view.showMasterSetPriceDisplayList(masterSetPriceDisplayModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    AudienceShoppingListContract.View view = getView();
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
