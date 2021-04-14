package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class TeachingPayMechanismArrangeSchedulePresenter extends BasePresenter<TeachingPayMechanismArrangeScheduleContract.Model, TeachingPayMechanismArrangeScheduleContract.View>
        implements TeachingPayMechanismArrangeScheduleContract.Presenter {

    @Override
    public void queryIsMechanismCourse(String mechanism_id) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryIsMechanismCourse(
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachingPayMechanismArrangeScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.onIsMechanismCourse(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachingPayMechanismArrangeScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.onError("网络错误");
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachingPayMechanismArrangeScheduleContract.View view = getView();
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
    protected TeachingPayMechanismArrangeScheduleContract.Model createModule() {
        return new TeachingPayMechanismArrangeScheduleModel();
    }

    @Override
    public void start() {

    }
}
