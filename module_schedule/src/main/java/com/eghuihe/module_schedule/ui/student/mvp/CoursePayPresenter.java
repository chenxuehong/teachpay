package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CoursePayPresenter extends BasePresenter<CoursePayContract.Model,CoursePayContract.View>
        implements CoursePayContract.Presenter {
    @Override
    protected CoursePayContract.Model createModule() {
        return new CoursePayModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void studentScheduleAlipay(
            String user_id,
            String rcharge_type,
            String amount,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String appointment_id,
            String user_study_card_id,
            String coupon_id,
            String points,
            String pay_type,
            String mechanism_id,
            String user_appointment_id,
            String title) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().studentScheduleAlipay(
                            user_id,
                            rcharge_type,
                            amount,
                            source,
                            course_num,
                            master_id,
                            member_duration,
                            rcharge_abstract,
                            studycard_id,
                            study_type,
                            is_one_time_payment,
                            appointment_id,
                            user_study_card_id,
                            coupon_id,
                            points,
                            pay_type,
                            mechanism_id,
                            user_appointment_id,
                             title,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    CoursePayContract.View view = getView();
                                    if (view != null) {
                                        view.onAlipayOrder(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    CoursePayContract.View view = getView();
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
    public void studentScheduleWxpay(
            String user_id,
            String rcharge_type,
            String amount,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String appointment_id,
            String user_study_card_id,
            String coupon_id,
            String points,
            String pay_type,
            String mechanism_id,
            String user_appointment_id,
            String title) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().studentScheduleWxpay(
                            user_id,
                            rcharge_type,
                            amount,
                            source,
                            course_num,
                            master_id,
                            member_duration,
                            rcharge_abstract,
                            studycard_id,
                            study_type,
                            is_one_time_payment,
                            appointment_id,
                            user_study_card_id,
                            coupon_id,
                            points,
                            pay_type,
                            mechanism_id,
                            user_appointment_id,
                            title,
                            new NetworkSubscriber<WxPayModel>(getView()){
                                @Override
                                protected void onSuccess(WxPayModel wxPayModel) {
                                    CoursePayContract.View view = getView();
                                    if (view!=null){
                                        view.onWxpayOrder(wxPayModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    CoursePayContract.View view = getView();
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
                                    CoursePayContract.View view = getView();
                                    if (view!=null){
                                        view.onPayDetailEntity(payDetailModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }
}
