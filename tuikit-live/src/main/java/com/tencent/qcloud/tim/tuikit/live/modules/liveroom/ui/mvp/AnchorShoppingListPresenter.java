package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class AnchorShoppingListPresenter extends BasePresenter<AnchorShoppingListContract.Model,AnchorShoppingListContract.View>
        implements AnchorShoppingListContract.Presenter {
    @Override
    protected AnchorShoppingListContract.Model createModule() {
        return new AnchorShoppingListModel();
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
                                    AnchorShoppingListContract.View view = getView();
                                    if (view!=null){
                                        view.showMasterSetPriceDisplayList(masterSetPriceDisplayModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    AnchorShoppingListContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void updateMasterSetPriceDisplay(String id, Boolean is_live_streaming) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().updateMasterSetPriceDisplay(
                            id,
                            is_live_streaming,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    AnchorShoppingListContract.View view = getView();
                                    if (view!=null){
                                        view.onUpdateSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    AnchorShoppingListContract.View view = getView();
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
