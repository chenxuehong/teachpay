package com.eghuihe.module_main.main.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class MainContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryUserInfo(
                String user_id,
                NetworkSubscriber<UserInfoModel> subscriber);

        DisposableObserver userDeviceInsert(
                String model,
                String clientid,
                String user_id,
                String unique_id,
                String teach_pay_token,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver versionIteration(
                String version,
                String platform,
                NetworkSubscriber<VersionIterationModel> subscriber);
    }

    public interface View extends IStateView {
        void onUserInfo(UserInfoEntity userInfoEntity);
        void onVersionIteration(VersionIterationModel.VersionIterationEntity versionIterationEntity);
    }

    public interface Presenter {
        void queryUserInfo(
                String user_id);

        void userDeviceInsert(
                String model,
                String clientid,
                String user_id,
                String unique_id,
                String teach_pay_token);
        void versionIteration(
                String version,
                String platform);
    }
}
