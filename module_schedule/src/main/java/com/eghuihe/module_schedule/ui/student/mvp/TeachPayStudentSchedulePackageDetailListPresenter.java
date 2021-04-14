package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LongOrderPayInfoModel;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class TeachPayStudentSchedulePackageDetailListPresenter extends BasePresenter<TeachPayStudentSchedulePackageDetailListContract.Model, TeachPayStudentSchedulePackageDetailListContract.View>
        implements TeachPayStudentSchedulePackageDetailListContract.Presenter {

    @Override
    protected TeachPayStudentSchedulePackageDetailListContract.Model createModule() {
        return new TeachPayStudentSchedulePackageDetailListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryStudentOfflineSchedule(
            String study_card_id,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryStudentOfflineSchedule(
                            study_card_id,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<StudentScheduleModel>(null) {
                                @Override
                                protected void onSuccess(StudentScheduleModel studentScheduleModel) {
                                    TeachPayStudentSchedulePackageDetailListContract.View view = getView();
                                    if (view != null) {
                                        view.onStudentScheduleEntityList(studentScheduleModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayStudentSchedulePackageDetailListContract.View view = getView();
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
    public void queryLongOrderPayInfo(String id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryLongOrderPayInfo(
                            id,
                            new NetworkSubscriber<LongOrderPayInfoModel>(getView()){
                                @Override
                                protected void onSuccess(LongOrderPayInfoModel longOrderPayInfoModel) {
                                    TeachPayStudentSchedulePackageDetailListContract.View view = getView();
                                    if (view!=null){
                                        view.onGetLongOrderInfoDetail(longOrderPayInfoModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }


    @Override
    public void getLongOrder(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String user_study_card_id,
            String paying_for_identity,
            final String pay_type,
            String mechanism_id
    ) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getLongOrder(
                            user_id,
                            rcharge_type,
                            source,
                            course_num,
                            master_id,
                            rcharge_abstract,
                            studycard_id,
                            study_type,
                            is_one_time_payment,
                            user_study_card_id,
                            paying_for_identity,
                            pay_type,
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayStudentSchedulePackageDetailListContract.View view = getView();
                                    if (view != null) {
                                        view.onGetAliOrder(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayStudentSchedulePackageDetailListContract.View view = getView();
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
    public void getWxLongOrder(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String user_study_card_id,
            String paying_for_identity,
            String pay_type,
            String mechanism_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().getWxLongOrder(
                            user_id,
                            rcharge_type,
                            source,
                            course_num,
                            master_id,
                            rcharge_abstract,
                            studycard_id,
                            study_type,
                            is_one_time_payment,
                            user_study_card_id,
                            paying_for_identity,
                            pay_type,
                            mechanism_id,
                            new NetworkSubscriber<WxPayModel>(getView()){
                                @Override
                                protected void onSuccess(WxPayModel wxPayModel) {
                                    TeachPayStudentSchedulePackageDetailListContract.View view = getView();
                                    if (view!=null){
                                        view.onGetWxOrder(wxPayModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }
}
