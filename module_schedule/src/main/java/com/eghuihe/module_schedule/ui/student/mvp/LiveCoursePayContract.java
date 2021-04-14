package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.PayDetailEntity;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class LiveCoursePayContract {
    public interface Model extends IBaseModel {

        DisposableObserver payOneCourseLiveStreamByAli(
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
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver payOneCourseLiveStreamByWx(
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
                NetworkSubscriber<WxPayModel> subscriber);

        DisposableObserver queryPayLiveStreamDetails(
                String studycard_id,
                String user_id,
                String mechanism_id,
                Boolean is_one_time_payment,
                String live_streaming_id,
                NetworkSubscriber<PayDetailModel> subscriber);

    }

    public interface View extends IStateView {

        void onAliOrderInfo(String orderInfo);

        void onWxOrderInfo(WxPayModel.WxPayEntity wxPayEntity);
        void onShowLivePayDetail(PayDetailEntity livePayDetailEntity);
    }

    public interface Presenter {

        void payOneCourseLiveStreamByAli(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String member_duration,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String coupon_id,
                String pay_type,
                String mechanism_id,
                String title,
                String type,
                String live_streaming_id);

        void payOneCourseLiveStreamByWx(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String member_duration,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String coupon_id,
                String pay_type,
                String mechanism_id,
                String title,
                String type,
                String live_streaming_id);

        void queryPayLiveStreamDetails(
                String studycard_id,
                String user_id,
                String mechanism_id,
                Boolean is_one_time_payment,
                String live_streaming_id);
    }
}
