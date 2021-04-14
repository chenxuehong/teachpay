package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class CourseOnTimePayModel implements CourseOnTimePayContract.Model {
    @Override
    public DisposableObserver payOntimeCourseAly(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String pay_type,
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.payOntimeCourseAly(
                user_id,
                rcharge_type,
                source,
                course_num,
                master_id,
                member_duration,
                rcharge_abstract,
                studycard_id,
                study_type,
                is_one_time_payment,
                pay_type,
                mechanism_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver payOntimeCourseWX(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String pay_type,
            String mechanism_id,
            NetworkSubscriber<WxPayModel> subscriber) {
        return UserServiceImpl.payOntimeCourseWx(
                user_id,
                rcharge_type,
                source,
                course_num,
                master_id,
                member_duration,
                rcharge_abstract,
                studycard_id,
                study_type,
                is_one_time_payment,
                pay_type,
                mechanism_id,
                subscriber
        );
    }
}
