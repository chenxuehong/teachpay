package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class TeachPayStudentAppointmentSchedulePresenter extends BasePresenter<TeachPayStudentAppointmentScheduleContract.Model,TeachPayStudentAppointmentScheduleContract.View>
        implements TeachPayStudentAppointmentScheduleContract.Presenter {
    @Override
    protected TeachPayStudentAppointmentScheduleContract.Model createModule() {
        return new TeachPayStudentAppointmentScheduleModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismOfflineAppointmentSchedule(
            String create_type,
            String end_time,
            String identity_type,
            String master_set_price_id,
            String mechanism_id,
            String offset,
            String start_time,
            String type) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismOfflineAppointmentSchedule(
                            create_type,
                            end_time,
                            identity_type,
                            master_set_price_id,
                            mechanism_id,
                            offset,
                            start_time,
                            type,
                            new NetworkSubscriber<MechanismOfflineScheduleModel>(getView()){
                                @Override
                                protected void onSuccess(MechanismOfflineScheduleModel mechanismOfflineScheduleModel) {
                                    TeachPayStudentAppointmentScheduleContract.View view = getView();
                                    if (view!=null){
                                        getView().onMechanismOfflineAppointmentSchedule(mechanismOfflineScheduleModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayStudentAppointmentScheduleContract.View view = getView();
                                    if (view!=null){
                                        getView().closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
