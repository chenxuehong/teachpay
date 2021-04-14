package com.eghuihe.module_main.main.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.ToastUtils;

public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View>
        implements MainContract.Presenter {
    @Override
    protected MainContract.Model createModule() {
        return new MainModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryUserInfo(String user_id) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryUserInfo(
                            user_id,
                            new NetworkSubscriber<UserInfoModel>(null){
                                @Override
                                protected void onSuccess(UserInfoModel userInfoModel) {
                                    MainContract.View view = getView();
                                    if (view!=null){
                                        view.onUserInfo(userInfoModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MainContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void versionIteration(
            String version,
            String platform) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().versionIteration(
                            version,
                            platform,
                            new NetworkSubscriber<VersionIterationModel>(null){
                                @Override
                                protected void onSuccess(VersionIterationModel versionIterationModel) {
                                    MainContract.View view = getView();
                                    if (view!=null){
                                        view.onVersionIteration(versionIterationModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    MainContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }


    @Override
    public void userDeviceInsert(
            String model,
            String clientid,
            String user_id,
            String unique_id,
            String teach_pay_token) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().userDeviceInsert(
                            model,
                            clientid,
                            user_id,
                            unique_id,
                            teach_pay_token,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                                    LogUtils.i("userDeviceInsert","success");
                                }
                            }
                    )
            );
        }
    }
}
