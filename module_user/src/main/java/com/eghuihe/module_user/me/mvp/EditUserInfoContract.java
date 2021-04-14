package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserCompanyModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.model.personal.UserSchoolModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class EditUserInfoContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryUserInfo(
                String user_id,
                NetworkSubscriber<UserInfoModel> subscriber
        );

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
                DisposableObserver<InsertInfoResultModel> subscriber
        );

        DisposableObserver userSchoolQueryListPage(
                String user_id,
                NetworkSubscriber<UserSchoolModel> subscriber
        );

        DisposableObserver queryUserCompany(
                String user_id,
                NetworkSubscriber<UserCompanyModel> subscriber
        );
    }

    public interface View extends IStateView {

        void onUpdateUserInfo(
                String content,
                String type
        );

        void onBindUserInfo(
                UserInfoEntity userInfoEntity
        );

        void onSchoolQueryList(
                List<UserSchoolModel.UserSchoolEntity> userSchoolEntities
        );

        void onUserCompanyList(
                List<UserCompanyModel.UserCompanyEntity> userCompanyEntities
        );
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
                String type,
                Boolean is_teach_paypal
        );

        void userSchoolQueryListPage(
                String user_id
        );

        void queryUserCompany(
                String user_id
        );

        void queryUserInfo(
                String user_id
        );
    }
}
