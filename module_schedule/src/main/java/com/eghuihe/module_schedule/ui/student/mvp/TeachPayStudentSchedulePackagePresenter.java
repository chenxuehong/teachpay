package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.StudentCoursePackageModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class TeachPayStudentSchedulePackagePresenter extends BasePresenter<TeachPayStudentSchedulePackageContract.Model, TeachPayStudentSchedulePackageContract.View>
        implements TeachPayStudentSchedulePackageContract.Presenter {
    @Override
    protected TeachPayStudentSchedulePackageContract.Model createModule() {
        return new TeachPayStudentSchedulePackageModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryStudentExclusiveCoursesList(
            String type,
            String user_id,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryStudentExclusiveCoursesList(
                            type,
                            user_id,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<StudentCoursePackageModel>(null) {
                                @Override
                                protected void onSuccess(StudentCoursePackageModel studentCoursePackageModel) {
                                    TeachPayStudentSchedulePackageContract.View view = getView();
                                    if (view != null) {
                                        List<StudentCoursePackageEntity> data = studentCoursePackageModel.getData();
                                        view.onStudentCoursePackageEntityList(data);
                                        if (data ==null || data.size()==0){
                                            view.onEmpty();
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachPayStudentSchedulePackageContract.View view = getView();
                                    if (view != null) {
                                        view.onError("网络异常");
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayStudentSchedulePackageContract.View view = getView();
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
    public void insertUserGrouping(
            String user_id,
            String master_set_price_id,
            String study_card_id) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertUserGrouping(
                            user_id,
                            master_set_price_id,
                            study_card_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayStudentSchedulePackageContract.View view = getView();
                                    if (view!=null){
                                        view.onPingGroupSuccess(insertInfoResultModel.getData());
                                    }
                                }
                            }

                    )
            );
        }
    }
}
