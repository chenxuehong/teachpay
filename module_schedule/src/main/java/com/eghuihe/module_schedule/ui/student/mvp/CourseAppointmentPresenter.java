package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CourseAppointmentPresenter extends BasePresenter<CourseAppointmentPayContract.Model,CourseAppointmentPayContract.View>
        implements CourseAppointmentPayContract.Presenter {
    @Override
    public void queryPayDetails(
            String course_num,
            String studycard_id,
            String user_study_card_id,
            String user_id,
            String mechanism_id,
            Boolean is_one_time_payment) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryPayDetails(
                            course_num,
                            studycard_id,
                            user_study_card_id,
                            user_id,
                            mechanism_id,
                            is_one_time_payment,
                            new NetworkSubscriber<PayDetailModel>(getView()){
                                @Override
                                protected void onSuccess(PayDetailModel payDetailModel) {
                                    CourseAppointmentPayContract.View view = getView();
                                    if (view!=null){
                                        view.onPayDetailEntity(payDetailModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void insertUserAppointment(
            String user_id,
            String appointment_id,
            String study_card_id,
            String offline_mobile,
            String title,
            String classroom,
            String number_of_lessons,
            String source,
            String coupon_id,
            String point,
            String mechanism_id) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertUserAppointment(
                            user_id,
                            appointment_id,
                            study_card_id,
                            offline_mobile,
                            title,
                            classroom,
                            number_of_lessons,
                            source,
                            coupon_id,
                            point,
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    CourseAppointmentPayContract.View view = getView();
                                    if (view!=null){
                                        view.onAppointmentSuccess(insertInfoResultModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected CourseAppointmentPayContract.Model createModule() {
        return new CourseAppointmentPayModel();
    }

    @Override
    public void start() {

    }
}
