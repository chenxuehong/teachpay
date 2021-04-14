package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldEntity;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.LiveDetailEntity;
import com.huihe.base_lib.model.LiveDetailModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.OneCourseOrderModel;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class TeachPayMechanismCourseDetailPresenter extends BasePresenter<TeachPayMechanismCourseDetailContract.Model, TeachPayMechanismCourseDetailContract.View>
        implements TeachPayMechanismCourseDetailContract.Presenter {
    @Override
    public void aliPayExclusive(
            String studycard_id,
            String user_id,
            String rcharge_type,
            String amount,
            String course_num,
            String member_duration,
            String mechanism_id,
            String study_type,
            String rcharge_abstract,
            String master_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().aliPayExclusive(
                            studycard_id,
                            user_id,
                            rcharge_type,
                            amount,
                            course_num,
                            member_duration,
                            mechanism_id,
                            study_type,
                            rcharge_abstract,
                            master_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onGetAliOrderInfo(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
    public void wxPayExclusive(
            String studycard_id,
            String user_id,
            String rcharge_type,
            String amount,
            String course_num,
            String member_duration,
            String mechanism_id,
            String study_type,
            String rcharge_abstract,
            String master_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().wxPayExclusive(
                            studycard_id,
                            user_id,
                            rcharge_type,
                            amount,
                            course_num,
                            member_duration,
                            mechanism_id,
                            study_type,
                            rcharge_abstract,
                            master_id,
                            new NetworkSubscriber<WxPayModel>(getView()) {
                                @Override
                                protected void onSuccess(WxPayModel wxPayModel) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onGetWxOrderInfo(wxPayModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
    public void payOneCourseOrder(
            String user_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String binding_mechanism_id) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().payOneCourseOrder(
                            user_id,
                            member_duration,
                            rcharge_abstract,
                            studycard_id,
                            binding_mechanism_id,
                            new NetworkSubscriber<OneCourseOrderModel>(getView()) {
                                @Override
                                protected void onSuccess(OneCourseOrderModel insertInfoResultModel) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        List<StudentCoursePackageEntity> data = insertInfoResultModel.getData();
                                        if (data != null && data.size() > 0) {
                                            StudentCoursePackageEntity studentCoursePackageEntity = data.get(0);
                                            view.onPayOneCourseOrder(studentCoursePackageEntity);
                                        }

                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
    public void userFollowMechanismInsert(String user_id, String mechanism_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().userFollowMechanismInsert(
                            user_id,
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onUserFollowMechanismInsert();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }

                                @Override
                                protected void onFail(Integer code, String message) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismCourseCommentList(mechanismCommentModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
    public void insertUserGrouping(String user_id, String master_set_price_id, String study_card_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertUserGrouping(
                            user_id,
                            master_set_price_id,
                            study_card_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view != null) {
                                        view.onPingGroupSuccess(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
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
    public void queryMasterSetPriceStreamDetail(String id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMasterSetPriceStreamDetail(
                            id,
                            new NetworkSubscriber<LiveDetailModel>(null){
                                @Override
                                protected void onSuccess(LiveDetailModel liveDetailModel) {
                                    TeachPayMechanismCourseDetailContract.View view = getView();
                                    if (view!=null){
                                        List<LiveDetailEntity> data = liveDetailModel.getData();
                                        if (data!=null&& data.size()>0){
                                            LiveDetailEntity liveDetailEntity = data.get(0);
                                            view.onMasterSetPriceStreamDetail(liveDetailEntity);
                                        }
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected TeachPayMechanismCourseDetailContract.Model createModule() {
        return new TeachPayMechanismCourseDetailModel();
    }

    @Override
    public void start() {

    }
}
