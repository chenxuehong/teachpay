package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class TeachingPaySchedulePresenter extends BasePresenter<TeachingPayScheduleContract.Model, TeachingPayScheduleContract.View>
        implements TeachingPayScheduleContract.Presenter {
    @Override
    protected TeachingPayScheduleContract.Model createModule() {
        return new TeachingPayScheduleModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryOfflineSchedule(
            String user_id,
            String type,
            String status,
            Boolean is_comment,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryOfflineSchedule(
                            user_id,
                            type,
                            status,
                            is_comment,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<StudentScheduleModel>(null) {
                                @Override
                                protected void onSuccess(StudentScheduleModel studentScheduleModel) {
                                    TeachingPayScheduleContract.View view = getView();
                                    if (view != null) {
                                        view.onOfflineScheduleList(studentScheduleModel.getData());
                                        List<StudentScheduleModel.StudentScheduleEntity> data = studentScheduleModel.getData();
                                        if (data==null ||data.size()==0){
                                            view.onEmpty();
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachingPayScheduleContract.View view = getView();
                                    if (view != null) {
                                        view.onError("网络异常");
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachingPayScheduleContract.View view = getView();
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
    public void sign(String id, String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().sign(
                            id,
                            status,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachingPayScheduleContract.View view = getView();
                                    if (view != null)
                                        getView().onUpdatestatusSuccess();
                                }

                                @Override
                                public void onComplete() {
                                    getView().closeLoading();
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void updateCancelCourse(String id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateCancelCourse(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachingPayScheduleContract.View view = getView();
                                    if (view != null)
                                        getView().onUpdatestatusSuccess();
                                }
                                @Override
                                public void onComplete() {
                                    getView().closeLoading();
                                }
                            }
                    )
            );
        }
    }

}
