package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class EditUserInfo1Model implements EditUserInfo1Contract.Model {
    @Override
    public DisposableObserver updateUserInfo(
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
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateUserInfo(
                user_id,
                nick_name,
                avatar,
                sex,
                birth,
                country,
                city,
                hometown,
                languages,
                mother_tongue,
                invite_code,
                is_teach_paypal,
                subscriber
        );
    }

    @Override
    public DisposableObserver login(
            String loginName,
            String password,
            String login_type,
            Boolean is_teach_paypal,
            DisposableObserver<LoginResultModel> subscriber) {
        return UserServiceImpl.login(
                loginName,
                password,
                login_type,
                is_teach_paypal,
                subscriber
        );
    }
}
