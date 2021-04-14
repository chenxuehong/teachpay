package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class ActivityDetailContract {
    public interface Model extends IBaseModel {

        DisposableObserver insertH5GetCoupon(
                String login_name,
                String verification_code,
                String nick_name,
                String sex,
                String preference,
                String relationships,
                String age,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver sendSms(
                String mobile,
                NetworkSubscriber subscriber);
    }

    public interface View extends IStateView {

        void onInsertSuccess();
        void onGetCodeSuccess();
    }

    public interface Presenter {
        void insertH5GetCoupon(
                String login_name,
                String verification_code,
                String nick_name,
                String sex,
                String preference,
                String relationships,
                String age);
        void sendSms(String mobile);
    }
}
