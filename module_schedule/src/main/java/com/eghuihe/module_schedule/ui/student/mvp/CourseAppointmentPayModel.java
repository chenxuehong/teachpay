package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class CourseAppointmentPayModel implements CourseAppointmentPayContract.Model {

    @Override
    public DisposableObserver queryPayDetails(
            String course_num,
            String studycard_id,
            String user_study_card_id,
            String user_id,
            String mechanism_id,
            Boolean is_one_time_payment,
            DisposableObserver<PayDetailModel> subscriber) {
        return UserServiceImpl.queryPayDetails(
                course_num,
                studycard_id,
                user_study_card_id,
                user_id,
                mechanism_id,
                is_one_time_payment,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertUserAppointment(
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
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMechanismUserAppointment(
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
                subscriber
        );
    }
}
