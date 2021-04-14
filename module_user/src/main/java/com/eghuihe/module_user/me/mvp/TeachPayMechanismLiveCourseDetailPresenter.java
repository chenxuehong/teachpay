package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldEntity;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class TeachPayMechanismLiveCourseDetailPresenter extends BasePresenter<TeachPayMechanismLiveCourseDetailContract.Model, TeachPayMechanismLiveCourseDetailContract.View>
        implements TeachPayMechanismLiveCourseDetailContract.Presenter {


    @Override
    public void userFollowMechanismInsert(String user_id, String mechanism_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().userFollowMechanismInsert(
                            user_id,
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onUserFollowMechanismInsert();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
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
    public void queryMechanismMasterSetPriceDisplay(
            final String id,
            final String live_streaming_id) {
        showLoading();
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismMasterSetPriceDisplay(
                            id,
                            live_streaming_id,
                            new NetworkSubscriber<MasterSetPriceDisplayModel>(null) {
                                @Override
                                protected void onSuccess(MasterSetPriceDisplayModel masterSetPriceDisplayModel) {
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        List<MasterSetPriceEntity> data = masterSetPriceDisplayModel.getData();
                                        if (data != null) {
                                            view.onMechanismCourseInfo(data);
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    queryMechanismCourseCommentList(
                                            id,
                                            "teach_paypal_course",
                                            getView().getCurrentPage(),
                                            getView().getPageSize()
                                    );
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryMechanismCourseCommentList(
            String mastersetprice_id,
            String type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismCourseCommentList(
                            mastersetprice_id,
                            type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<MechanismCommentModel>(null) {
                                @Override
                                protected void onSuccess(MechanismCommentModel mechanismCommentModel) {
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismCourseCommentList(mechanismCommentModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismLiveCourseDetailContract.View view = getView();
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
    protected TeachPayMechanismLiveCourseDetailContract.Model createModule() {
        return new TeachPayMechanismLiveCourseDetailModel();
    }

    @Override
    public void start() {

    }
}
