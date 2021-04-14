package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class UpdateTeachPayMechanismCourseModel implements UpdateTeachPayMechanismCourseContract.Model {
    @Override
    public DisposableObserver updateMasterSetPrice(
            String id,
            String user_id,
            String appointment_type,
            String title,
            String label,
            String course_num,
            String amout,
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
            String default_discount_price,
            String categories,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateMasterSetPriceActivityInfo(
                 id,
                 user_id,
                 appointment_type,
                 title,
                 label,
                 course_num,
                 amout,
                 null,
                 null,
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
                default_discount_price,
                categories,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismDetailInfoListById(
            String id, String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismDetailInfoListById(
                id,
                type,
                subscriber
        );
    }
}
