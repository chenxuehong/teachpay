package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismClassModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CourseManagePresenter extends BasePresenter<CourseManagerContract.Model, CourseManagerContract.View>
        implements CourseManagerContract.Presenter {
    @Override
    protected CourseManagerContract.Model createModule() {
        return new CourseManageModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismClasses(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String status,
            String master_set_price_id,
            Boolean is_scheduling_over) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismClasses(
                            mechanism_id,
                            currentPage,
                            pageSize,
                            status,
                            master_set_price_id,
                            is_scheduling_over,
                            new NetworkSubscriber<MechanismClassModel>(null){
                                @Override
                                protected void onSuccess(MechanismClassModel mechanismClassModel) {
                                    CourseManagerContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismClassList(mechanismClassModel.getData());
                                    }
                                }
                                @Override
                                public void onComplete() {
                                    CourseManagerContract.View view = getView();
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
