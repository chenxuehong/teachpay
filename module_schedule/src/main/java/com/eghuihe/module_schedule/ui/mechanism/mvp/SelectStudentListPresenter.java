package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.study.StudentModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SelectStudentListPresenter extends BasePresenter<SelectStudentListContract.Model, SelectStudentListContract.View>
        implements SelectStudentListContract.Presenter {
    @Override
    protected SelectStudentListContract.Model createModule() {
        return new SelectStudentListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryStudentList(
            String mechanism_id,
            String status,
            String studycard_id,
            String type,
            Integer pageSize,
            Integer currentPage) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryStudentList(
                            mechanism_id,
                            status,
                            studycard_id,
                            type,
                            pageSize,
                            currentPage,
                            new NetworkSubscriber<StudentModel>(null) {
                                @Override
                                protected void onSuccess(StudentModel studentModel) {
                                    SelectStudentListContract.View view = getView();
                                    if (view != null) {
                                        view.onStudentInfo(studentModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectStudentListContract.View view = getView();
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
