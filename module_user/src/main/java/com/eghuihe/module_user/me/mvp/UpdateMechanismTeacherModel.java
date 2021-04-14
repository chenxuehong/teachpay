package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class UpdateMechanismTeacherModel implements UpdateMechanismTeacherContract.Model {
    @Override
    public DisposableObserver updateMechanismMaster(
            String user_id,
            String login_pass,
            String login_name,
            String nick_name,
            String register_platform,
            String mechanism_id,
            String avatar,
            String mobile,
            String sex,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateMechanismMaster(
                user_id,
                login_pass,
                login_name,
                nick_name,
                register_platform,
                mechanism_id,
                avatar,
                mobile,
                sex,
                subscriber
        );
    }

    @Override
    public DisposableObserver resignTeacher(
            String id,
            String status,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.resignTeacher(
                id,
                status,
                subscriber
        );
    }
}
