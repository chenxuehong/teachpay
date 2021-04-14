package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.SummaryInfoModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SummaryDetailPresenter extends BasePresenter<SummaryDetailContract.Model,SummaryDetailContract.View>
        implements SummaryDetailContract.Presenter {
    @Override
    protected SummaryDetailContract.Model createModule() {
        return new SummaryDetailModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void querySummaryList(String appointment_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().querySummaryList(
                            appointment_id,
                            new NetworkSubscriber<SummaryInfoModel>(getView()){
                                @Override
                                protected void onSuccess(SummaryInfoModel summaryInfoModel) {
                                    SummaryDetailContract.View view = getView();
                                    if (view!=null){
                                        view.onSummaryList(summaryInfoModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }
}
