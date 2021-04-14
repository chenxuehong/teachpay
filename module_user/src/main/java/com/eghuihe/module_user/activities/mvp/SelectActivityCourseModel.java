package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class SelectActivityCourseModel implements SelectActivityCourseContract.Model {
    @Override
    public DisposableObserver queryCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseList(
                mechanism_id,
                type,
                status,
                appointment_type,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertBusinessSinglePaymentActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String price,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertBusinessSinglePaymentActivity(
                is_new_customers,
                master_set_price_ids,
                mechanism_id,
                start_time,
                end_time,
                type,
                tags,
                price,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertBusinessSalesCourseActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String coupon_time,
            String discount_amount,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertBusinessSalesCourseActivity(
                is_new_customers,
                master_set_price_ids,
                mechanism_id,
                start_time,
                end_time,
                type,
                tags,
                coupon_time,
                discount_amount,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertBusinessGroupingActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String number_of_people,
            String each_time_percentage,
            String each_time_percentage_max,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertBusinessGroupingActivity(
                is_new_customers,
                master_set_price_ids,
                mechanism_id,
                start_time,
                end_time,
                type,
                tags,
                number_of_people,
                each_time_percentage,
                each_time_percentage_max,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertBusinessExperienceSpecialsActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String coupon_time,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertBusinessExperienceSpecialsActivity(
                is_new_customers,
                master_set_price_ids,
                mechanism_id,
                start_time,
                end_time,
                type,
                tags,
                coupon_time,
                subscriber
        );
    }
}
