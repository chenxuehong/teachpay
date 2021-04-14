package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.PayDetailEntity;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class CourseAppointmentPayContract {
    public interface Model extends IBaseModel {


        DisposableObserver queryPayDetails(
                String course_num,
                String studycard_id,
                String user_study_card_id,
                String user_id,
                String mechanism_id,
                Boolean is_one_time_payment,
                DisposableObserver<PayDetailModel> subscriber);

        DisposableObserver insertUserAppointment(
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
                String mechanism_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

    }

    public interface View extends IStateView {

        void onPayDetailEntity(PayDetailEntity payDetailEntity);
        void onAppointmentSuccess(String orderInfo);

    }

    public interface Presenter {

        void queryPayDetails(
                String course_num,
                String studycard_id,
                String user_study_card_id,
                String user_id,
                String mechanism_id,
                Boolean is_one_time_payment);

        void insertUserAppointment(
                String user_id,
                String appointment_id,
                String study_card_id,
                String offline_mobile,
                String title,
                String classroom,
                String number_of_lessons,
                String source,
                String coupon_id,
                String points,
                String mechanism_id);
    }
}
