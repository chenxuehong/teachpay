package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class InsertMechanismTeacherPresenter extends BasePresenter<InsertMechanismTeacherContract.Model, InsertMechanismTeacherContract.View>
        implements InsertMechanismTeacherContract.Presenter {
    @Override
    public void insertMechanismMaster(
            String login_pass,
            String login_name,
            String nick_name,
            String register_platform,
            String mechanism_id,
            String avatar,
            String mobile,
            String sex,
            String status) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertMechanismMaster(
                            login_pass,
                            login_name,
                            nick_name,
                            register_platform,
                            mechanism_id,
                            avatar,
                            mobile,
                            sex,
                            status,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    InsertMechanismTeacherContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    InsertMechanismTeacherContract.View view = getView();
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
    protected InsertMechanismTeacherContract.Model createModule() {
        return new InsertMechanismTeacherModel();
    }

    @Override
    public void start() {

    }
}
