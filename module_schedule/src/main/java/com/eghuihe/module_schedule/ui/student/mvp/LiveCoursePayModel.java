package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class LiveCoursePayModel implements LiveCoursePayContract.Model {
    @Override
    public DisposableObserver payOneCourseLiveStreamByAli(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String coupon_ids,
            String pay_type,
            String mechanism_id,
            String title,
            String type,
            String live_streaming_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.payOneCourseLiveStreamByAli(
                user_id,
                rcharge_type,
                source,
                course_num,
                member_duration,
                rcharge_abstract,
                studycard_id,
                study_type,
                is_one_time_payment,
                coupon_ids,
                pay_type,
                mechanism_id,
                title,
                type,
                live_streaming_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver payOneCourseLiveStreamByWx(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String coupon_ids,
            String pay_type,
            String mechanism_id,
            String title,
            String type,
            String live_streaming_id,
            NetworkSubscriber<WxPayModel> subscriber) {
        return UserServiceImpl.payOneCourseLiveStreamByWx(
                user_id,
                rcharge_type,
                source,
                course_num,
                member_duration,
                rcharge_abstract,
                studycard_id,
                study_type,
                is_one_time_payment,
                coupon_ids,
                pay_type,
                mechanism_id,
                title,
                type,
                live_streaming_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryPayLiveStreamDetails(
            String studycard_id,
            String user_id,
            String mechanism_id,
            Boolean is_one_time_payment,
            String live_streaming_id,
            NetworkSubscriber<PayDetailModel> subscriber) {
        return UserServiceImpl.queryPayLiveStreamDetails(
                studycard_id,
                user_id,
                mechanism_id,
                is_one_time_payment,
                live_streaming_id,
                subscriber
        );
    }
}
