package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ClassStudentBean;
import com.huihe.base_lib.model.ClassStudentModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class StudentListPresenter extends BasePresenter<StudentListContract.Model,StudentListContract.View>
        implements StudentListContract.Presenter {

    @Override
    public void queryStudentInfoList(
            String type,
            String mechanism_id,
            String studycard_id,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryStudentInfoList(
                            type,
                            mechanism_id,studycard_id,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<ClassStudentModel>(null){
                                @Override
                                protected void onSuccess(ClassStudentModel classStudentModel) {
                                    StudentListContract.View view = getView();
                                    if (view!=null){
                                        ClassStudentModel.ClassStudentData data = classStudentModel.getData();
                                        if (data!=null){
                                            List<ClassStudentBean> rows = data.getRows();
                                            view.onStudentInfoList(rows);
                                        }
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    StudentListContract.View view = getView();
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
    protected StudentListContract.Model createModule() {
        return new StudentListModel();
    }

    @Override
    public void start() {

    }
}
