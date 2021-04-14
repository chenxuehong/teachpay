package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class TeachPayCourseListPresenter extends BasePresenter<TeachPayCourseListContract.Model, TeachPayCourseListContract.View>
        implements TeachPayCourseListContract.Presenter {
    @Override
    protected TeachPayCourseListContract.Model createModule() {
        return new TeachPayCourseListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryNearByCourse(
            Integer status,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryNearByCourse(
                            status,
                            currentPage,
                            pageSize,
                            latitude,
                            longitude,
                            type,
                            new NetworkSubscriber<MasterSetPriceModel>(null) {
                                @Override
                                protected void onSuccess(MasterSetPriceModel masterSetPriceModel) {
                                    TeachPayCourseListContract.View view = getView();
                                    if (view != null) {
                                        view.showHotMechanismCourseList(masterSetPriceModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayCourseListContract.View view = getView();
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
