package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class RegisterContract {
    public interface Model extends IBaseModel {

        DisposableObserver sendMail(String mail, String type, NetworkSubscriber<GetMailCodeModel> subscriber);

        DisposableObserver sendSms(String mobile,
                                   String type,
                                   NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver register(String verification_code, String login_name, NetworkSubscriber<RegisterUserInfoModel> subscriber);
    }

    public interface View extends IStateView {

        void showSendCodeSuccess();
        void onRegisterSuccess(RegisterUserInfoModel registerUserInfoEntity);
    }

    public interface Presenter {

        void sendMail(
                String mail,
                String type);

        void sendSms(String mobile,
                     String type);
        void register(String verification_code, String login_name);
    }
}
