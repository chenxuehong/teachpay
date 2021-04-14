package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.TeachPaypalDetailEntity;
import com.huihe.base_lib.model.TeachPaypalDetailModel;
import com.huihe.base_lib.model.TeachPaypalUserGoldTypeModel;
import com.huihe.base_lib.model.personal.InsertSignDayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.manager.LoginHelper;

public class MyRewardPresenter extends BasePresenter<MyRewardContract.Model, MyRewardContract.View>
        implements MyRewardContract.Presenter {
    @Override
    protected MyRewardContract.Model createModule() {
        return new MyRewardModel();
    }

    @Override
    public void start() {
        showLoading();
        queryTeachPaypalDetail(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
    }

    @Override
    public void queryTeachPaypalDetail(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryTeachPaypalDetail(
                            user_id,
                            new NetworkSubscriber<TeachPaypalDetailModel>(null) {
                                @Override
                                protected void onSuccess(TeachPaypalDetailModel teachPaypalDetailModel) {
                                    TeachPaypalDetailEntity data = teachPaypalDetailModel.getData();
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.onTeachPaypalDetail(data);
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    super.onFail(code, message);
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    queryTeachPaypalUserGoldType(
                                            LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                                            "1",
                                            true
                                    );
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryTeachPaypalUserGoldType(
            String user_id,
            String status,
            Boolean is_teach_paypal) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryTeachPaypalUserGoldType(
                            user_id,
                            status,
                            is_teach_paypal,
                            new NetworkSubscriber<TeachPaypalUserGoldTypeModel>(null) {
                                @Override
                                protected void onSuccess(TeachPaypalUserGoldTypeModel teachPaypalUserGoldTypeModel) {
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.onUserGoldTypeList(teachPaypalUserGoldTypeModel.getData());
                                    }
                                    queryNearByCourse(
                                            2,
                                            1,
                                            18,
                                            LoginHelper.getLatitude(),
                                            LoginHelper.getLongitude(),
                                            "mechanism_offline"
                                    );
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    super.onFail(code, message);
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    MyRewardContract.View view = getView();
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
    public void queryNearByCourse(
            Integer status,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryNearByCourse(
                            status,
                            currentPage,
                            pageSize,
                            latitude,
                            longitude,
                            type,
                            new NetworkSubscriber<MasterSetPriceModel>(null) {
                                @Override
                                protected void onSuccess(MasterSetPriceModel masterSetPriceModel) {
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.onCourseList(masterSetPriceModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MyRewardContract.View view = getView();
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
    public void insertLoginSignIn(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertLoginSignIn(
                            user_id,
                            new NetworkSubscriber<InsertSignDayModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertSignDayModel insertInfoResultModel) {
                                    MyRewardContract.View view = getView();
                                    if (view != null) {
                                        view.onSignSucess();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
