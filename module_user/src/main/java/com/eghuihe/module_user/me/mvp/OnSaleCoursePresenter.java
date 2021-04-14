package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class OnSaleCoursePresenter extends BasePresenter<OnSaleCourseContract.Model, OnSaleCourseContract.View>
        implements OnSaleCourseContract.Presenter {
    @Override
    public void queryExclusiveCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize
    ) {
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
                                    OnSaleCourseContract.View view = getView();
                                    if (view != null) {
                                        view.showExclusiveCourseList(commodityModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    OnSaleCourseContract.View view = getView();
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
    protected OnSaleCourseContract.Model createModule() {
        return new OnSaleCourseModel();
    }

    @Override
    public void start() {

    }
}
