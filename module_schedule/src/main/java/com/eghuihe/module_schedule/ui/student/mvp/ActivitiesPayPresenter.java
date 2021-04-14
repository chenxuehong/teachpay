package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ActivitiesPayPresenter extends BasePresenter<ActivitiesPayContract.Model,ActivitiesPayContract.View>
        implements ActivitiesPayContract.Presenter {
    @Override
    public void buyCouponByAli(
            String user_id,
            String rcharge_type,
            String source,
            String rcharge_abstract,
            Boolean is_one_time_payment,
            String pay_type,
            String invite_code,
            String course_num) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().buyCouponByAli(
                            user_id,
                            rcharge_type,
                            source,
                            rcharge_abstract,
                            is_one_time_payment,
                            pay_type,
                            invite_code,
                            course_num,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ActivitiesPayContract.View view = getView();
                                    if (view!=null){
                                        view.onGetAliOrderInfo(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ActivitiesPayContract.View view = getView();
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
    public void buyCouponByWx(
            String user_id,
            String rcharge_type,
            String source,
            String rcharge_abstract,
            Boolean is_one_time_payment,
            String pay_type,
            String invite_code,
            String course_num) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().buyCouponByWx(
                            user_id,
                            rcharge_type,
                            source,
                            rcharge_abstract,
                            is_one_time_payment,
                            pay_type,
                            invite_code,
                            course_num,
                            new NetworkSubscriber<WxPayModel>(getView()){
                                @Override
                                protected void onSuccess(WxPayModel wxPayModel) {
                                    ActivitiesPayContract.View view = getView();
                                    if (view!=null){
                                        view.onGetWxOrderInfo(wxPayModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ActivitiesPayContract.View view = getView();
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
    protected ActivitiesPayContract.Model createModule() {
        return new ActivitiesPayModel();
    }

    @Override
    public void start() {

    }
}
