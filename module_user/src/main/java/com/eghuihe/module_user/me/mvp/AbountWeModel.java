package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;

import io.reactivex.observers.DisposableObserver;

public class AbountWeModel implements AbountWeContract.Model {
    @Override
    public DisposableObserver versionIteration(
            String version,
            String platform,
            NetworkSubscriber<VersionIterationModel> subscriber) {
        return UserServiceImpl.versionIteration(
                version,
                platform,
                subscriber
        );
    }
}
