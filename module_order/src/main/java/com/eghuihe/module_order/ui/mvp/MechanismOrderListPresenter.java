package com.eghuihe.module_order.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LongOrderPayInfoModel;
import com.huihe.base_lib.model.MechanismOrderModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MechanismOrderListPresenter extends BasePresenter<MechanismOrderListContract.Model, MechanismOrderListContract.View>
        implements MechanismOrderListContract.Presenter {
    @Override
    protected MechanismOrderListContract.Model createModule() {
        return new MechanismOrderListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismOrderList(
            String type,
            String rcharge_type,
            String user_id,
            String mechanism_id,
            Boolean finished,
            Boolean is_one_time_payment,
            Integer pageSize,
            Integer currentPage,
            String study_type,
            String course_num,
            String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismOrderList(
                            type,
                            rcharge_type,
                            user_id,
                            mechanism_id,
                            finished,
                            is_one_time_payment,
                            pageSize,
                            currentPage,
                            study_type,
                            course_num,
                            status,
                            new NetworkSubscriber<MechanismOrderModel>(null) {
                                @Override
                                protected void onSuccess(MechanismOrderModel mechanismOrderModel) {
                                    MechanismOrderListContract.View view = getView();
                                    if (view != null) {
                                        view.MechanismOrderList(mechanismOrderModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismOrderListContract.View view = getView();
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
    public void getLongOrder(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String user_study_card_id,
            String paying_for_identity,
           final String pay_type,
            String mechanism_id
    ) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getLongOrder(
                            user_id,
                            rcharge_type,
                            source,
                            course_num,
                            master_id,
                            rcharge_abstract,
                            studycard_id,
                            study_type,
                            is_one_time_payment,
                            user_study_card_id,
                            paying_for_identity,
                            pay_type,
                            mechanism_id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    MechanismOrderListContract.View view = getView();
                                    if (view != null) {
                                        view.onGetAliOrder(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismOrderListContract.View view = getView();
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
    public void getWxLongOrder(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String user_study_card_id,
            String paying_for_identity,
            String pay_type,
            String mechanism_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().getWxLongOrder(
                            user_id,
                            rcharge_type,
                            source,
                            course_num,
                            master_id,
                            rcharge_abstract,
                            studycard_id,
                            study_type,
                            is_one_time_payment,
                            user_study_card_id,
                            paying_for_identity,
                            pay_type,
                            mechanism_id,
                            new NetworkSubscriber<WxPayModel>(getView()){
                                @Override
                                protected void onSuccess(WxPayModel wxPayModel) {
                                    MechanismOrderListContract.View view = getView();
                                    if (view!=null){
                                        view.onGetWxOrder(wxPayModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryLongOrderPayInfo(String id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryLongOrderPayInfo(
                            id,
                            new NetworkSubscriber<LongOrderPayInfoModel>(getView()){
                                @Override
                                protected void onSuccess(LongOrderPayInfoModel longOrderPayInfoModel) {
                                    MechanismOrderListContract.View view = getView();
                                    if (view!=null){
                                        view.onGetLongOrderInfoDetail(longOrderPayInfoModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }
}
