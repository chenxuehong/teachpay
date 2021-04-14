package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismAverageScoreModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class TeachPayMechanismDetailPresenter extends BasePresenter<TeachPayMechanismDetailContract.Model, TeachPayMechanismDetailContract.View>
        implements TeachPayMechanismDetailContract.Presenter {
    @Override
    public void userFollowMechanismInsert(
            String user_id,
            String mechanism_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().userFollowMechanismInsert(
                            user_id,
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayMechanismDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onUserFollowMechanismInsert();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismDetailContract.View view = getView();
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
    public void queryMechanismMasterInfoList(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String type,
            String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismMasterInfoList(
                            mechanism_id,
                            currentPage,
                            pageSize,
                            type,
                            status,
                            new NetworkSubscriber<MasterInfoHomeModel>(getView()) {
                                @Override
                                protected void onSuccess(MasterInfoHomeModel masterInfoHomeModel) {
                                    TeachPayMechanismDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismMasters(masterInfoHomeModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismDetailContract.View view = getView();
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
    public void queryMechanismDetailInfoListById(final String mechanism_id, String type) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismDetailInfoListById(
                            mechanism_id,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(getView()) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    TeachPayMechanismDetailContract.View view = getView();
                                    if (view != null) {
                                        List<MasterMechanismModel.MasterMechanismEntity> data = masterMechanismModel.getData();
                                        if (data != null && data.size() > 0) {
                                            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = data.get(0);
                                            view.onMechanismInfo(masterMechanismEntity);
                                        }
                                    }

                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismDetailContract.View view = getView();
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
    public void queryMechanismCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize
    ) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismCourseList(
                            mechanism_id,
                            type,
                            status,
                            appointment_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(getView()) {
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    TeachPayMechanismDetailContract.View view = getView();
                                    if (view != null) {
                                        List<MasterSetPriceEntity> masterSetPriceEntities = commodityModel.getData();
                                        view.onMasterSetPriceList(masterSetPriceEntities);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismDetailContract.View view = getView();
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
    public void queryMechanismAverageScore(String mechanism_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismAverageScore(
                            mechanism_id,
                            new NetworkSubscriber<MechanismAverageScoreModel>(getView()) {
                                @Override
                                protected void onSuccess(MechanismAverageScoreModel mechanismAverageScoreModel) {
                                    TeachPayMechanismDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismAverageScore(mechanismAverageScoreModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismDetailContract.View view = getView();
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
    public void queryMechanismCommentList(
            String mechanism_id,
            String type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismCommentList(
                            mechanism_id,
                            type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<MechanismCommentModel>(null) {
                                @Override
                                protected void onSuccess(MechanismCommentModel mechanismCommentModel) {
                                    TeachPayMechanismDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismCommentList(mechanismCommentModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismDetailContract.View view = getView();
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
    protected TeachPayMechanismDetailContract.Model createModule() {
        return new TeachPayMechanismDetailModel();
    }

    @Override
    public void start() {

    }
}
