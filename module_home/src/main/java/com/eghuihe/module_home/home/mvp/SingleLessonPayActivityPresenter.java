package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import io.reactivex.observers.DisposableObserver;

public class SingleLessonPayActivityPresenter extends BasePresenter<SingleLessonPayActivityContract.Model, SingleLessonPayActivityContract.View>
        implements SingleLessonPayActivityContract.Presenter {
    @Override
    protected SingleLessonPayActivityContract.Model createModule() {
        return new SingleLessonPayActivityModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryActivityListPageByType(
            Integer currentPage,
            Integer pageSize,
            String status,
            String type,
            String latitude,
            String longitude) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryActivityListPageByType(
                            currentPage,
                            pageSize,
                            status,
                            type,
                            latitude,
                            longitude,
                            new NetworkSubscriber<MasterSetPriceModel>(null) {

                                @Override
                                protected void onSuccess(MasterSetPriceModel masterSetPriceModel) {
                                    SingleLessonPayActivityContract.View view = getView();
                                    if (view != null) {
                                        view.onCoureList(masterSetPriceModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SingleLessonPayActivityContract.View view = getView();
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
