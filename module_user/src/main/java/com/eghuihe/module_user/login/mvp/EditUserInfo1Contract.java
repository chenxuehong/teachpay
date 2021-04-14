package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.LoginResultModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class EditUserInfo1Contract {
    public interface Model extends IBaseModel {
        DisposableObserver updateUserInfo(
                String user_id,
                String nick_name,
                String avatar,
                Integer sex,
                String birth,
                String country,
                String city,
                String hometown,
                String languages,
                String mother_tongue,
                String invite_code,
                Boolean is_teach_paypal,
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver login(
                String loginName,
                String password,
                String login_type,
                Boolean is_teach_paypal,
                DisposableObserver<LoginResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onUpdateUserInfo();
        void onLoginSuccess(LoginResultEntity loginResultEntity);
    }

    public interface Presenter {

        void updateUserInfo(
                String user_id,
                String nick_name,
                String avatar,
                Integer sex,
                String birth,
                String country,
                String city,
                String hometown,
                String languages,
                String mother_tongue,
                String invite_code,
                Boolean is_teach_paypal);
        void login(
                String loginName,
                String password,
                String login_type,
                Boolean is_teach_paypal);
    }

}
