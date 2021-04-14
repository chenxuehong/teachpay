package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserCompanyModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.model.personal.UserSchoolModel;

import io.reactivex.observers.DisposableObserver;

public class EditUserInfoModel implements EditUserInfoContract.Model {
    @Override
    public DisposableObserver queryUserInfo(
            String user_id,
            NetworkSubscriber<UserInfoModel> subscriber) {
        return UserServiceImpl.queryUserInfo(
                user_id,
                subscriber
        );
    }

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
    public DisposableObserver userSchoolQueryListPage(
            String user_id,
            NetworkSubscriber<UserSchoolModel> subscriber) {
        return UserServiceImpl.userSchoolQueryListPage(
                null,
                user_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryUserCompany(String user_id, NetworkSubscriber<UserCompanyModel> subscriber) {
        return UserServiceImpl.queryUserCompany(
                null,
                user_id,
                subscriber
        );
    }
}
