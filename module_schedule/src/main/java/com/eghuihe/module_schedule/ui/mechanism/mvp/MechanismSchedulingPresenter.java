package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MechanismSchedulingPresenter extends BasePresenter<MechanismSchedulingContract.Model, MechanismSchedulingContract.View>
        implements MechanismSchedulingContract.Presenter {
    @Override
    protected MechanismSchedulingContract.Model createModule() {
        return new MechanismSchedulingModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismOfflineSchedule(
            String mechanism_id,
            String type,
            String status,
            String start_time,
            String end_time,
            String offset,
            String identity_type,
            String create_type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismOfflineSchedule(
                            mechanism_id,
                            type,
                            status,
                            start_time,
                            end_time,
                            offset,
                            identity_type,
                            create_type,
                            new NetworkSubscriber<MechanismOfflineScheduleModel>(null) {
                                @Override
                                protected void onSuccess(MechanismOfflineScheduleModel mechanismOfflineScheduleModel) {
                                    MechanismSchedulingContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismOfflineScheduleList(mechanismOfflineScheduleModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismSchedulingContract.View view = getView();
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
    public void deleteMasterAppointment(String id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().deleteMasterAppointment(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    MechanismSchedulingContract.View view = getView();
                                    if (view!=null){
                                        view.onDeleteCourseSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismSchedulingContract.View view = getView();
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
    public void insertCopyCourse(
            String id,
            String start_time,
            String end_time,
            String dates,
            String type) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertCopyCourse(
                            id,
                            start_time,
                            end_time,
                            dates,
                            type,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    MechanismSchedulingContract.View view = getView();
                                    if (view!=null){
                                        view.onCopyCourseSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismSchedulingContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
