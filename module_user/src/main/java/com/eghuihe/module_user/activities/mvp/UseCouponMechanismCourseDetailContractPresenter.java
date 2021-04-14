package com.eghuihe.module_user.activities.mvp;

import com.eghuihe.module_user.me.mvp.TeachPayMechanismCourseDetailContract;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldEntity;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class UseCouponMechanismCourseDetailContractPresenter extends BasePresenter<UseCouponMechanismCourseDetailContract.Model,UseCouponMechanismCourseDetailContract.View>
        implements UseCouponMechanismCourseDetailContract.Presenter {

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
                                    UseCouponMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onUserFollowMechanismInsert();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UseCouponMechanismCourseDetailContract.View view = getView();
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
    public void queryMechanismCourseListById(final String id) {
        showLoading();
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismCourseListById(
                            id,
                            new NetworkSubscriber<CommodityOldModel>(null) {
                                @Override
                                protected void onSuccess(CommodityOldModel commodityOldModel) {
                                    UseCouponMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        CommodityOldEntity data = commodityOldModel.getData();
                                        if (data != null) {
                                            view.onMechanismCourseInfo(data.list);
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    UseCouponMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    UseCouponMechanismCourseDetailContract.View view = getView();
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
                                    UseCouponMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismCourseCommentList(mechanismCommentModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UseCouponMechanismCourseDetailContract.View view = getView();
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
    public void useCoupon(String id, String master_set_price_id, String user_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().useCoupon(
                            id,
                            master_set_price_id,
                            user_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    UseCouponMechanismCourseDetailContract.View view = getView();
                                    if (view!=null){
                                        view.onUseCouponSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    UseCouponMechanismCourseDetailContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected UseCouponMechanismCourseDetailContract.Model createModule() {
        return new UseCouponMechanismCourseDetailModel();
    }

    @Override
    public void start() {

    }
}
