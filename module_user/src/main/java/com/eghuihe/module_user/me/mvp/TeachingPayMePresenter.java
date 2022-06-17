package com.eghuihe.module_user.me.mvp;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.UserStatisticsModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.Utils;
import com.huihe.base_lib.utils.manager.LoginHelper;

public class TeachingPayMePresenter extends BasePresenter<TeachingPayMeContract.Model, TeachingPayMeContract.View>
        implements TeachingPayMeContract.Presenter {
    @Override
    protected TeachingPayMeContract.Model createModule() {
        return new TeachingPayMeModel();
    }

    @Override
    public void start() {
        TeachingPayMeContract.View view = getView();
        if (view != null) {
            view.showLoading();
        }
        getBannerData(
                "teachpay_mine",
                "1");
    }

    @Override
    public void getBannerData(String type, String state) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getBannerData(
                            type,
                            state,
                            new NetworkSubscriber<BannerModel>(null) {
                                @Override
                                protected void onSuccess(BannerModel bannerModel) {
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.bannerData(bannerModel.getData());
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    super.onFail(code, message);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UserInfoEntity userInfoEntity = LoginHelper.getLoginInfo().getUserInfoEntity();
                                    if (Utils.IdentityType.IS_STUDENT == Utils.getIdentityType()) {
                                        // 学生
                                       queryUserInfo(userInfoEntity.getUser_id());
                                    } else if (Utils.IdentityType.IS_MECHANISM == Utils.getIdentityType()) {
                                        // 机构
                                        if (Utils.isSwitchMechanismIdentity()) {
                                            queryMechanismInfo(userInfoEntity.getUser_id(), null, "teach_paypal");
                                        } else {
                                            queryUserInfo(userInfoEntity.getUser_id());
                                        }
                                    } else {
                                        queryUserInfo(userInfoEntity.getUser_id());
                                    }

                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryMechanismInfo(String user_id, Integer Status, String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismInfo(
                            user_id,
                            Status,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(null) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismInfo(masterMechanismModel.getData());
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    super.onFail(code, message);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    if (Utils.IdentityType.IS_STUDENT == Utils.getIdentityType()
                                            || !Utils.isSwitchMechanismIdentity()) {
                                        queryTeachPayUserStatistics(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
                                    } else {
                                        TeachingPayMeContract.View view = getView();
                                        if (view != null) {
                                            view.closeLoading();
                                        }
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryTeachPayUserStatistics(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryTeachPayUserStatistics(
                            user_id,
                            new NetworkSubscriber<UserStatisticsModel>(null) {
                                @Override
                                protected void onSuccess(UserStatisticsModel userStatisticsModel) {
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.onUserStatistics(userStatisticsModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachingPayMeContract.View view = getView();
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
    public void queryUserInfo(String user_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryUserInfo(
                            user_id,
                            new NetworkSubscriber<UserInfoModel>(null){
                                @Override
                                protected void onSuccess(UserInfoModel userInfoModel) {
                                    super.onSuccess(userInfoModel);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.onUserInfo(userInfoModel.getData());
                                    }
                                }
                                @Override
                                protected void onFail(Integer code, String message) {
                                    super.onFail(code, message);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachingPayMeContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    if (Utils.IdentityType.IS_STUDENT==Utils.getIdentityType()
                                            || !Utils.isSwitchMechanismIdentity()) {
                                        queryTeachPayUserStatistics(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
                                    } else {
                                        TeachingPayMeContract.View view = getView();
                                        if (view != null) {
                                            view.closeLoading();
                                        }
                                    }
                                }
                            }
                    )
            );
        }
    }


}
