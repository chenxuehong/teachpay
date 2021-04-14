package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.BusinessActivityTypeModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ActivitiesPresenter extends BasePresenter<ActivitiesContract.Model,ActivitiesContract.View>
        implements ActivitiesContract.Presenter {
    @Override
    public void queryBusinessActivityTypeList(
            Integer currentPage,
            Integer pageSize,
            String status) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryBusinessActivityTypeList(
                            currentPage,
                            pageSize,
                            status,
                            new NetworkSubscriber<BusinessActivityTypeModel>(null){
                                @Override
                                protected void onSuccess(BusinessActivityTypeModel businessActivityTypeModel) {
                                    ActivitiesContract.View view = getView();
                                    if (view!=null){
                                        view.showBusinessActivityTypeList(businessActivityTypeModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ActivitiesContract.View view = getView();
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
    protected ActivitiesContract.Model createModule() {
        return new ActivitiesModel();
    }

    @Override
    public void start() {

    }
}
