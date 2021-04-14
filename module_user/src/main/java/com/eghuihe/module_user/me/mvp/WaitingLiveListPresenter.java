package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LiveModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class WaitingLiveListPresenter extends BasePresenter<WaitingLiveListContract.Model,WaitingLiveListContract.View>
        implements WaitingLiveListContract.Presenter {
    @Override
    protected WaitingLiveListContract.Model createModule() {
        return new WaitingLiveListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getLiveMasterSetPriceList(
            Integer currentPage,
            Integer pageSize,
            String mechanism_id,
            String status) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().getLiveMasterSetPriceList(
                            currentPage,
                            pageSize,
                            mechanism_id,
                            status,
                            new NetworkSubscriber<LiveModel>(null){

                                @Override
                                protected void onSuccess(LiveModel liveModel) {
                                    WaitingLiveListContract.View view = getView();
                                    if (view!=null){
                                        view.showLiveMasterSetPriceList(liveModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    WaitingLiveListContract.View view = getView();
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
