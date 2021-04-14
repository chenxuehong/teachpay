package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MotherTongueSettingPresenter extends BasePresenter<MotherTongueSettingContract.Model, MotherTongueSettingContract.View>
        implements MotherTongueSettingContract.Presenter {
    @Override
    public void setMotherTongue(
            String user_id,
           final String mother_tongue) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().setMotherTongue(
                            user_id,
                            mother_tongue,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    MotherTongueSettingContract.View view = getView();
                                    if (view != null) {
                                        view.onSetMotherTongueSuccess(mother_tongue);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MotherTongueSettingContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected MotherTongueSettingContract.Model createModule() {
        return new MotherTongueSettingModel();
    }

    @Override
    public void start() {

    }
}
