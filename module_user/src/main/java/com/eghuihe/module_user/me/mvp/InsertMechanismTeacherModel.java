package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class InsertMechanismTeacherModel implements InsertMechanismTeacherContract.Model {
    @Override
    public DisposableObserver insertMechanismMaster(
            String login_pass,
            String login_name,
            String nick_name,
            String register_platform,
            String mechanism_id,
            String avatar,
            String mobile,
            String sex,
            String status,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertMechanismMaster(
                login_pass,
                login_name,
                nick_name,
                register_platform,
                mechanism_id,
                avatar,
                mobile,
                sex,
                status,
                subscriber
        );
    }

}
