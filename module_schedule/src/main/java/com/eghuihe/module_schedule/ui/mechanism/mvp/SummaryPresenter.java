package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SummaryPresenter extends BasePresenter<SummaryContract.Model,SummaryContract.View>
        implements SummaryContract.Presenter {
    @Override
    public void insertSummaryOffline(
            String appointment_id,
            String content,
            String master_id,
            String mechanism_id,
            String photo_url) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertSummaryOffline(
                            appointment_id,
                            content,
                            master_id,
                            mechanism_id,
                            photo_url,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SummaryContract.View view = getView();
                                    if (view!=null){
                                        view.onSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SummaryContract.View view = getView();
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
    protected SummaryContract.Model createModule() {
        return new SummaryModel();
    }

    @Override
    public void start() {

    }
}
