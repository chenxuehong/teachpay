package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class AbountWeContract {
    public interface Model extends IBaseModel {

        DisposableObserver versionIteration(
                String version,
                String platform,
                NetworkSubscriber<VersionIterationModel> subscriber);
    }

    public interface View extends IStateView {
        void onVersionIteration(VersionIterationModel.VersionIterationEntity versionIterationEntity);
    }

    public interface Presenter {
        void versionIteration(
                String version,
                String platform);
    }
}
