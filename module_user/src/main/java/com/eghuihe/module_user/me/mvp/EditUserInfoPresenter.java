package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserCompanyModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.model.personal.UserSchoolModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class EditUserInfoPresenter extends BasePresenter<EditUserInfoContract.Model, EditUserInfoContract.View>
        implements EditUserInfoContract.Presenter {
    public static final String TYPE_HEAD = "type_head";
    public static final String TYPE_NICK_NAME = "type_nick";
    public static final String TYPE_BIRTHDAY = "type_birthday";
    public static final String TYPE_COUNTRY = "type_country";
    public static final String TYPE_HOMETOWN = "type_HomeTown";
    public static final String TYPE_CITY = "type_city";
    public static final String TYPE_MOTHER_TONGUE = "MotherTongue";
    public static final String TYPE_LANGUAGE = "language";

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
            String type,
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

                                    if (TYPE_HEAD.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                avatar,
                                                TYPE_HEAD
                                        );
                                    } else if (TYPE_NICK_NAME.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                nick_name,
                                                TYPE_NICK_NAME
                                        );
                                    } else if (TYPE_BIRTHDAY.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                birth,
                                                TYPE_BIRTHDAY
                                        );
                                    } else if (TYPE_COUNTRY.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                country,
                                                TYPE_COUNTRY
                                        );
                                    } else if (TYPE_HOMETOWN.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                hometown,
                                                TYPE_HOMETOWN
                                        );
                                    } else if (TYPE_CITY.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                city,
                                                TYPE_CITY
                                        );
                                    } else if (TYPE_MOTHER_TONGUE.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                mother_tongue,
                                                TYPE_MOTHER_TONGUE
                                        );
                                    } else if (TYPE_LANGUAGE.equals(type)) {
                                        getView().onUpdateUserInfo(
                                                languages,
                                                TYPE_LANGUAGE
                                        );
                                    }

                                }
                            }
                    )
            );
        }

    }

    @Override
    public void queryUserCompany(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryUserCompany(
                            user_id,
                            new NetworkSubscriber<UserCompanyModel>(null) {
                                @Override
                                protected void onSuccess(UserCompanyModel userCompanyModel) {
                                    getView().onUserCompanyList(userCompanyModel.getData());
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void userSchoolQueryListPage(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().userSchoolQueryListPage(
                            user_id,
                            new NetworkSubscriber<UserSchoolModel>(null) {
                                @Override
                                protected void onSuccess(UserSchoolModel userSchoolModel) {
                                    getView().onSchoolQueryList(userSchoolModel.getData());
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
    public void queryUserInfo(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryUserInfo(
                            user_id,
                            new NetworkSubscriber<UserInfoModel>(getView()) {
                                @Override
                                protected void onSuccess(UserInfoModel userInfoModel) {
                                    getView().onBindUserInfo(userInfoModel.getData());
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected EditUserInfoContract.Model createModule() {
        return new EditUserInfoModel();
    }

    @Override
    public void start() {

    }
}
