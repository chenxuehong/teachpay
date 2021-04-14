package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class UseCouponActivitiesListPresenter extends BasePresenter<UseCouponActivitiesListContract.Model,UseCouponActivitiesListContract.View>
        implements UseCouponActivitiesListContract.Presenter {
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
                                    UseCouponActivitiesListContract.View view = getView();
                                    if (view != null) {
                                        view.onCoureList(masterSetPriceModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UseCouponActivitiesListContract.View view = getView();
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
    protected UseCouponActivitiesListContract.Model createModule() {
        return new UseCouponActivitiesListModel();
    }

    @Override
    public void start() {

    }
}
