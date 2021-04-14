package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class InsertMechanismTeacherContract {
    public interface Model extends IBaseModel {

        DisposableObserver insertMechanismMaster(
                String login_pass,
                String login_name,
                String nick_name,
                String register_platform,
                String mechanism_id,
                String avatar,
                String mobile,
                String sex,
                String status,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

    }

    public interface View extends IStateView {
        void onInsertSuccess();
    }

    public interface Presenter {

        void insertMechanismMaster(
                String login_pass,
                String login_name,
                String nick_name,
                String register_platform,
                String mechanism_id,
                String avatar,
                String mobile,
                String sex,
                String status);


    }
}
