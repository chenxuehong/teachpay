package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LoginResultModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class EditUserInfo1Presenter extends BasePresenter<EditUserInfo1Contract.Model, EditUserInfo1Contract.View>
        implements EditUserInfo1Contract.Presenter {

    @Override
    public void updateUserInfo(
            String user_id,
            String nick_name,
            String avatar,
            Integer sex,
            String birth,
            String country,
            String city,
            String hometown,
            String languages,
            String mother_tongue,
            String invite_code,
            Boolean is_teach_paypal) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateUserInfo(
                            user_id,
                            nick_name,
                            avatar,
                            sex,
                            birth,
                            country,
                            city,
                            hometown,
                            languages,
                            mother_tongue,
                            invite_code,
                            is_teach_paypal,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    getView().onUpdateUserInfo();
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    getView().closeLoading();
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void login(
            String loginName,
            String password,
            String login_type,
            Boolean is_teach_paypal) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().login(
                            loginName,
                            password,
                            login_type,
                            is_teach_paypal,
                            new NetworkSubscriber<LoginResultModel>(getView()) {
                                @Override
                                protected void onSuccess(LoginResultModel loginResultModel) {
                                    getView().onLoginSuccess(loginResultModel.getData());
                                }
                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    getView().closeLoading();
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected EditUserInfo1Contract.Model createModule() {
        return new EditUserInfo1Model();
    }

    @Override
    public void start() {

    }
}
