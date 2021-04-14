package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MechanismSchedulePresenter extends BasePresenter<MechanismScheduleContract.Model,MechanismScheduleContract.View>
implements MechanismScheduleContract.Presenter{
    @Override
    public void queryMechanismOfflineSchedule(
            String mechanism_id,
            String type,
            String status,
            String identity_type,
            Integer currentPage,
            Integer pageSize,
            String start_time,
            String end_time,
            String offset) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismOfflineSchedule(
                            mechanism_id,
                            type,
                            status,
                            identity_type,
                            currentPage,
                            pageSize,
                            start_time,
                            end_time,
                            offset,
                            new NetworkSubscriber<MechanismOfflineScheduleModel>(null){
                                @Override
                                protected void onSuccess(MechanismOfflineScheduleModel mechanismOfflineScheduleModel) {
                                    MechanismScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.onMechanismOfflineScheduleList(mechanismOfflineScheduleModel.getData());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    MechanismScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.onError("网络错误");
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismScheduleContract.View view = getView();
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
    public void deleteMasterAppointment(String id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().deleteMasterAppointment(id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    MechanismScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.onDeleteCourseSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismScheduleContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            })
            );
        }
    }

    @Override
    protected MechanismScheduleContract.Model createModule() {
        return new MechanismScheduleModel();
    }

    @Override
    public void start() {

    }
}
