package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.manager.LoginHelper;

public class TeachingPayHomePresenter extends BasePresenter<TeachingPayHomeContract.Model, TeachingPayHomeContract.View>
        implements TeachingPayHomeContract.Presenter {
    @Override
    protected TeachingPayHomeContract.Model createModule() {
        return new TeachingPayHomeModel();
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
                                    TeachingPayHomeContract.View view = getView();
                                    if (view != null) {
                                        view.showBannerList(bannerModel.getData());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachingPayHomeContract.View view = getView();
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
    public void queryMechanismInfo(
            String user_id,
            Integer Status,
            String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismInfo(
                            user_id,
                            Status,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(null) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    TeachingPayHomeContract.View view = getView();
                                    if (view != null) {
                                        view.onMyMechanismInfo(masterMechanismModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachingPayHomeContract.View view = getView();
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
    public void insertUserCollection(String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertUserCollection(
                            LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachingPayHomeContract.View view = getView();
                                    if (view != null) {
                                        view.onUserCollectionSuccess();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryActivityListPageByType(
            String status,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryActivityListPageByType(
                            status,
                            currentPage,
                            pageSize,
                            latitude,
                            longitude,
                            type,
                            new NetworkSubscriber<MasterSetPriceModel>(null) {
                                @Override
                                protected void onSuccess(MasterSetPriceModel masterSetPriceModel) {
                                    TeachingPayHomeContract.View view = getView();
                                    if (view != null) {
                                        view.showHotMechanismCourseList(masterSetPriceModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachingPayHomeContract.View view = getView();
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
    public void queryMechanismByType(
            Integer pageSize,
            Integer currentPage,
            String latitude,
            String longitude,
            String sortName,
            final String type,
            boolean loadMore) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismByType(
                            pageSize,
                            currentPage,
                            latitude,
                            longitude,
                            sortName,
                            new NetworkSubscriber<MasterMechanismModel>(null) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    TeachingPayHomeContract.View view = getView();
                                    if (view != null) {
                                        view.showMechanismList(masterMechanismModel.getData(), type);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachingPayHomeContract.View view = getView();
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
    public void start() {

    }

}
