package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class ActivitiesPayContract {
    public interface Model extends IBaseModel {

        // 购买劵
        DisposableObserver buyCouponByAli(
                String user_id,
                String rcharge_type,
                String source,
                String rcharge_abstract,
                Boolean is_one_time_payment,
                String pay_type,
                String invite_code,
                String course_num,
                DisposableObserver<InsertInfoResultModel> subscriber);

        // 购买劵
        DisposableObserver buyCouponByWx(
                String user_id,
                String rcharge_type,
                String source,
                String rcharge_abstract,
                Boolean is_one_time_payment,
                String pay_type,
                String invite_code,
                String course_num,
                DisposableObserver<WxPayModel> subscriber);
    }

    public interface View extends IStateView {

        void onGetAliOrderInfo(String orderInfo);

        void onGetWxOrderInfo(WxPayModel.WxPayEntity wxPayEntity);
    }

    public interface Presenter {

        void buyCouponByAli(
                String user_id,
                String rcharge_type,
                String source,
                String rcharge_abstract,
                Boolean is_one_time_payment,
                String pay_type,
                String invite_code,
                String course_num);

        void buyCouponByWx(
                String user_id,
                String rcharge_type,
                String source,
                String rcharge_abstract,
                Boolean is_one_time_payment,
                String pay_type,
                String invite_code,
                String course_num);
    }
}
