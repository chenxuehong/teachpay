package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class CoursePayModel implements CoursePayContract.Model {
    @Override
    public DisposableObserver studentScheduleAlipay(
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
            String title,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.studentScheduleAlipay(
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
                subscriber
        );

    }

    @Override
    public DisposableObserver studentScheduleWxpay(
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
            String title,
            DisposableObserver<WxPayModel> subscriber) {
        return UserServiceImpl.studentScheduleWxpay(
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
                subscriber
        );
    }

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
}
