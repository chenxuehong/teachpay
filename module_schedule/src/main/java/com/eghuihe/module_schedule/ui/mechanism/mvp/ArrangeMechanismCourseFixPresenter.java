package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ArrangeMechanismCourseFixPresenter extends BasePresenter<ArrangeMechanismCourseFixContract.Model,ArrangeMechanismCourseFixContract.View>
        implements ArrangeMechanismCourseFixContract.Presenter {

    @Override
    protected ArrangeMechanismCourseFixContract.Model createModule() {
        return new ArrangeMechanismCourseFixModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismClassroom(
            String mechanism_id,
            String start_time,
            String status) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismClassroom(
                            mechanism_id,
                            start_time,
                            status,
                            new NetworkSubscriber<MechanismClassroomSelectModel>(null){
                                @Override
                                protected void onSuccess(MechanismClassroomSelectModel mechanismClassroomSelectModel) {
                                    ArrangeMechanismCourseFixContract.View view = getView();
                                    if (view!=null){
                                        view.onClassRoomList(mechanismClassroomSelectModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeMechanismCourseFixContract.View view = getView();
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
    public void arrangeMechanismFixCourse(
            String id,
            String type,
            String weekOfDays,
            Boolean is_repeat,
            String start_time,
            String end_time,
            String master_id,
            String start_date,
            String date,
            String classroom) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().arrangeMechanismFixCourse(
                            id,
                            type,
                            weekOfDays,
                            is_repeat,
                            start_time,
                            end_time,
                            master_id,
                            start_date,
                            date,
                            classroom,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ArrangeMechanismCourseFixContract.View view = getView();
                                    if (view!=null){
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeMechanismCourseFixContract.View view = getView();
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
