package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class CourseOnTimePayContract {
    public interface Model extends IBaseModel {

        DisposableObserver payOntimeCourseAly(
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
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver payOntimeCourseWX(
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
                NetworkSubscriber<WxPayModel> subscriber);
    }

    public interface View extends IStateView {

       void onAliOrder(String orderInfo);
       void onWxOrder(WxPayModel.WxPayEntity wxPayEntity);
    }

    public interface Presenter {
        void payOntimeCourse(
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
                String mechanism_id);
    }
}
