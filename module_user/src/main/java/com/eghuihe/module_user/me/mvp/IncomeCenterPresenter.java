package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismIncomeListModel;
import com.huihe.base_lib.model.MechanismIncomeStatisticsModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class IncomeCenterPresenter extends BasePresenter<IncomeCenterContract.Model, IncomeCenterContract.View>
        implements IncomeCenterContract.Presenter {
    @Override
    protected IncomeCenterContract.Model createModule() {
        return new IncomeCenterModel();
    }

    @Override
    public void start() {
    }

    @Override
    public void queryMechanismOfflineDetailsCount(
            String mechanism_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismOfflineDetailsCount(
                            mechanism_id,
                            new NetworkSubscriber<MechanismIncomeStatisticsModel>(getView()) {
                                @Override
                                protected void onSuccess(MechanismIncomeStatisticsModel mechanismIncomeStatisticsModel) {
                                    IncomeCenterContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismIncomeStaticticsInfo(mechanismIncomeStatisticsModel.getData());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    IncomeCenterContract.View view = getView();
                                    if (view != null) {
                                        view.onError("网络错误");
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    IncomeCenterContract.View view = getView();
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
    public void queryMechanismOfflineDetails(
            String mechanism_id,
            String study_type,
            Boolean finished,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismOfflineDetails(
                            mechanism_id,
                            study_type,
                            finished,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<MechanismIncomeListModel>(currentPage == 1 ? getView() : null) {
                                @Override
                                protected void onSuccess(MechanismIncomeListModel mechanismIncomeListModel) {
                                    IncomeCenterContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismIncomeList(mechanismIncomeListModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    IncomeCenterContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
