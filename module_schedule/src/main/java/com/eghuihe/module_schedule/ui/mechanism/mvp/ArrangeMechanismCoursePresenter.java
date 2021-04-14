package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ArrangeMechanismCoursePresenter extends BasePresenter<ArrangeMechanismCourseContract.Model, ArrangeMechanismCourseContract.View>
        implements ArrangeMechanismCourseContract.Presenter {
    @Override
    protected ArrangeMechanismCourseContract.Model createModule() {
        return new ArrangeMechanismCourseModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void insertMechanismCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String study_card_ids,
            String classroom,
            String number_of_lessons
    ) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertMechanismCourse(
                            type,
                            source,
                            mechanism_id,
                            master_id,
                            title,
                            start_time,
                            end_time,
                            offset,
                            service_type,
                            master_set_price_id,
                            identity_type,
                            study_card_ids,
                            classroom,
                            number_of_lessons,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel studentModel) {
                                    ArrangeMechanismCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeMechanismCourseContract.View view = getView();
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
    public void insertOfflineCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String classroom,
            String connect_peoplenum,
            String number_of_lessons) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertOfflineCourse(
                            type,
                            source,
                            mechanism_id,
                            master_id,
                            title,
                            start_time,
                            end_time,
                            offset,
                            service_type,
                            master_set_price_id,
                            identity_type,
                            classroom,
                            connect_peoplenum,
                            number_of_lessons,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ArrangeMechanismCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeMechanismCourseContract.View view = getView();
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
    public void queryMechanismClassroom(
            String mechanism_id,
            String start_time,
            String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismClassroom(
                            mechanism_id,
                            start_time,
                            status,
                            new NetworkSubscriber<MechanismClassroomSelectModel>(null) {
                                @Override
                                protected void onSuccess(MechanismClassroomSelectModel mechanismClassroomSelectModel) {
                                    ArrangeMechanismCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onClassRoomList(mechanismClassroomSelectModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeMechanismCourseContract.View view = getView();
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
