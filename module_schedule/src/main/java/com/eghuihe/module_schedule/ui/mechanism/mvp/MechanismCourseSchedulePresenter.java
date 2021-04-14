package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MechanismCourseSchedulePresenter extends BasePresenter<MechanismCourseScheduleContract.Model,MechanismCourseScheduleContract.View>
        implements MechanismCourseScheduleContract.Presenter {
    @Override
    public void queryMechanismCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismCourseList(
                            mechanism_id,
                            type,
                            status,
                            appointment_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(null){
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    MechanismCourseScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.showMechanismCourseList(commodityModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    MechanismCourseScheduleContract.View view = getView();
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
    protected MechanismCourseScheduleContract.Model createModule() {
        return new MechanismCourseScheduleModel();
    }

    @Override
    public void start() {

    }
}
