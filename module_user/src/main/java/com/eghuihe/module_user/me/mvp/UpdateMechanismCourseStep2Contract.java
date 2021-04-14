package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class UpdateMechanismCourseStep2Contract {
    public interface Model extends IBaseModel {

        DisposableObserver updateMasterSetPrice(
                String id,
                String user_id,
                String appointment_type,
                String title,
                String label,
                String course_num,
                String amout,
                String discount,
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
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onUpdateMechanismCourseSuccess();
    }

    public interface Presenter {

        void updateMasterSetPrice(
                String id,
                String user_id,
                String appointment_type,
                String title,
                String label,
                String course_num,
                String amout,
                String discount,
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
                String categories);
    }
}
