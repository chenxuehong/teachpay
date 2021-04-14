package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class UpdateMechanismTeacherContract {
    public interface Model extends IBaseModel {

        DisposableObserver updateMechanismMaster(
                String user_id,
                String login_pass,
                String login_name,
                String nick_name,
                String register_platform,
                String mechanism_id,
                String avatar,
                String mobile,
                String sex,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        /**
         * @param id
         * @param status
         * @param subscriber
         * @return
         * @desc 老师离职
         */
        DisposableObserver resignTeacher(
                String id,
                String status,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onUpdateSuccess();
        void onResignSuccess();
    }

    public interface Presenter {


        void updateMechanismMaster(
                String user_id,
                String login_pass,
                String login_name,
                String nick_name,
                String register_platform,
                String mechanism_id,
                String avatar,
                String mobile,
                String sex);
        void resignTeacher(
                String id,
                String status);
    }
}
