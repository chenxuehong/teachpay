package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class SettingContract {
    public interface Model extends IBaseModel {

        DisposableObserver closeAccount(
                String user_id,
                String status,
                NetworkSubscriber subscriber);
    }

    public interface View extends IStateView {

        void showCloseActivityFinish();
    }

    public interface Presenter {

        void closeAccount(
                String user_id);
    }
}
