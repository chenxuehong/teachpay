package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.PayDetailModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class LiveCoursePayPresenter extends BasePresenter<LiveCoursePayContract.Model, LiveCoursePayContract.View>
        implements LiveCoursePayContract.Presenter {
    @Override
    public void payOneCourseLiveStreamByAli(
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
            String live_streaming_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().payOneCourseLiveStreamByAli(
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
                    new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            LiveCoursePayContract.View view = getView();
                            if (view != null) {
                                view.onAliOrderInfo(insertInfoResultModel.getData());
                            }
                        }

                        @Override
                        public void onComplete() {
                            LiveCoursePayContract.View view = getView();
                            if (view != null) {
                                view.closeLoading();
                            }
                        }
                    }
            ));
        }
    }

    @Override
    public void payOneCourseLiveStreamByWx(
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
            String live_streaming_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().payOneCourseLiveStreamByWx(
                    user_id,
                    rcharge_type,
                    source,
                    course_num,
                    member_duration,
                    rcharge_abstract,
                    studycard_id,
                    study_type,
                    is_one_time_payment,
                    coupon_id,
                    pay_type,
                    mechanism_id,
                    title,
                    type,
                    live_streaming_id,
                    new NetworkSubscriber<WxPayModel>(getView()) {
                        @Override
                        protected void onSuccess(WxPayModel wxPayModel) {
                            LiveCoursePayContract.View view = getView();
                            if (view != null) {
                                view.onWxOrderInfo(wxPayModel.getData());
                            }
                        }

                        @Override
                        public void onComplete() {
                            LiveCoursePayContract.View view = getView();
                            if (view != null) {
                                view.closeLoading();
                            }
                        }
                    }
            ));
        }

    }

    @Override
    public void queryPayLiveStreamDetails(
            String studycard_id,
            String user_id,
            String mechanism_id,
            Boolean is_one_time_payment,
            String live_streaming_id) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryPayLiveStreamDetails(
                            studycard_id,
                            user_id,
                            mechanism_id,
                            is_one_time_payment,
                            live_streaming_id,
                            new NetworkSubscriber<PayDetailModel>(getView()){
                                @Override
                                protected void onSuccess(PayDetailModel payDetailModel) {
                                    LiveCoursePayContract.View view = getView();
                                    if (view!=null){
                                        view.onShowLivePayDetail(payDetailModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    LiveCoursePayContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected LiveCoursePayContract.Model createModule() {
        return new LiveCoursePayModel();
    }

    @Override
    public void start() {

    }
}
