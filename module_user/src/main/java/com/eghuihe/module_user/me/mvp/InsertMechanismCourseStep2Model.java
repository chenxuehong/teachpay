package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class InsertMechanismCourseStep2Model implements InsertMechanismCourseStep2Contract.Model {
    @Override
    public DisposableObserver insertMasterSetPrice(
            String user_id,
            String title,
            String appointment_type,
            String label,
            String course_num,
            String amout,
            String discount_amout,
            String introduction_content,
            Integer status,
            String titile_url,
            Boolean first_free,
            String type,
            String connect_peoplenum,
            String mechanism_id,
            String face_url,
            String introduction_url,
            String duration,
            String price_list,
            Boolean is_attend_activities,
            String start_time,
            String end_time,
            String activity_price,
            String categories,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMasterSetPrice(
                user_id,
                title,
                appointment_type,
                label,
                course_num,
                amout,
                discount_amout,
                introduction_content,
                status,
                titile_url,
                first_free,
                type,
                connect_peoplenum,
                mechanism_id,
                face_url,
                introduction_url,
                duration,
                price_list,
                is_attend_activities,
                start_time,
                end_time,
                activity_price,
                categories,
                subscriber
        );
    }
}
