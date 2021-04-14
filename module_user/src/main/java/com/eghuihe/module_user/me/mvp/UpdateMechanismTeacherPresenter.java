package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class UpdateMechanismTeacherPresenter extends BasePresenter<UpdateMechanismTeacherContract.Model, UpdateMechanismTeacherContract.View>
        implements UpdateMechanismTeacherContract.Presenter {
    @Override
    public void updateMechanismMaster(
            String user_id,
            String login_pass,
            String login_name,
            String nick_name,
            String register_platform,
            String mechanism_id,
            String avatar,
            String mobile,
            String sex) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateMechanismMaster(
                            user_id,
                            login_pass,
                            login_name,
                            nick_name,
                            register_platform,
                            mechanism_id,
                            avatar,
                            mobile,
                            sex,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    UpdateMechanismTeacherContract.View view = getView();
                                    if (view != null) {
                                        view.onUpdateSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UpdateMechanismTeacherContract.View view = getView();
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
    public void resignTeacher(String id, String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().resignTeacher(
                            id,
                            status,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    UpdateMechanismTeacherContract.View view = getView();
                                    if (view != null) {
                                        view.onResignSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UpdateMechanismTeacherContract.View view = getView();
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
    protected UpdateMechanismTeacherContract.Model createModule() {
        return new UpdateMechanismTeacherModel();
    }

    @Override
    public void start() {

    }
}
