package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CreateLivePresenter extends BasePresenter<CreateLiveContract.Model,CreateLiveContract.View>
        implements CreateLiveContract.Presenter {
    @Override
    public void insertMasterSetPriceStream(
            String master_set_price_ids,
            String start_time,
            String title,
            String mechanism_id,
            String live_stream_prices,
            String living_single_session_prices) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertMasterSetPriceStream(
                            master_set_price_ids,
                            start_time,
                            title,
                            mechanism_id,
                            live_stream_prices,
                            living_single_session_prices,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    CreateLiveContract.View view = getView();
                                    if (view!=null){
                                        view.insertSuccess(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    CreateLiveContract.View view = getView();
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
    protected CreateLiveContract.Model createModule() {
        return new CreateLiveModel();
    }

    @Override
    public void start() {

    }
}
