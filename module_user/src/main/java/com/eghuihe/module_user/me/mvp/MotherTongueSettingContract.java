package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class MotherTongueSettingContract {
    public interface Model extends IBaseModel {

        DisposableObserver setMotherTongue(
                String user_id,
                String mother_tongue,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onSetMotherTongueSuccess(String mother_tongue);
    }

    public interface Presenter {

        void setMotherTongue(
                String user_id,
                String mother_tongue);
    }
}
