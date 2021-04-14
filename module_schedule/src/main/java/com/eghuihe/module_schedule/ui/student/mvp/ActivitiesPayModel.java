package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class ActivitiesPayModel implements ActivitiesPayContract.Model {
    @Override
    public DisposableObserver buyCouponByAli(
            String user_id,
            String rcharge_type,
            String source,
            String rcharge_abstract,
            Boolean is_one_time_payment,
            String pay_type,
            String invite_code,
            String course_num,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.buyCouponByAli(
                user_id,
                rcharge_type,
                source,
                rcharge_abstract,
                is_one_time_payment,
                pay_type,
                invite_code,
                course_num,
                subscriber
        );
    }

    @Override
    public DisposableObserver buyCouponByWx(
            String user_id,
            String rcharge_type,
            String source,
            String rcharge_abstract,
            Boolean is_one_time_payment,
            String pay_type,
            String invite_code,
            String course_num,
            DisposableObserver<WxPayModel> subscriber) {
        return UserServiceImpl.buyCouponByWx(
                user_id,
                rcharge_type,
                source,
                rcharge_abstract,
                is_one_time_payment,
                pay_type,
                invite_code,
                course_num,
                subscriber
        );
    }
}
