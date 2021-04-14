package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.model.PayDetailEntity;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class CoursePayContract {
    public interface Model extends IBaseModel {

        DisposableObserver studentScheduleAlipay(
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
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver studentScheduleWxpay(
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
                DisposableObserver<WxPayModel> subscriber);

        DisposableObserver queryPayDetails(
                String course_num,
                String studycard_id,
                String user_study_card_id,
                String user_id,
                String mechanism_id,
                Boolean is_one_time_payment,
                DisposableObserver<PayDetailModel> subscriber);
    }

    public interface View extends IStateView {

        void onAlipayOrder(String orderInfo);

        void onWxpayOrder(WxPayModel.WxPayEntity wxPayEntity);

        void onPayDetailEntity(PayDetailEntity payDetailEntity);

    }

    public interface Presenter {

        void studentScheduleAlipay(
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
                String title);

        void studentScheduleWxpay(
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
                String title);

        void queryPayDetails(
                String course_num,
                String studycard_id,
                String user_study_card_id,
                String user_id,
                String mechanism_id,
                Boolean is_one_time_payment);
    }
}
