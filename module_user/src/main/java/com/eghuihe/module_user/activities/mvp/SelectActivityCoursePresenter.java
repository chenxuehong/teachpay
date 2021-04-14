package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SelectActivityCoursePresenter extends BasePresenter<SelectActivityCourseContract.Model, SelectActivityCourseContract.View>
        implements SelectActivityCourseContract.Presenter {
    @Override
    protected SelectActivityCourseContract.Model createModule() {
        return new SelectActivityCourseModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryCourseList(
                            mechanism_id,
                            type,
                            status,
                            appointment_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(null) {
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    SelectActivityCourseContract.View view = getView();
                                    if (view != null) {
                                        view.showCourseList(commodityModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectActivityCourseContract.View view = getView();
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
    public void insertBusinessSinglePaymentActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String price) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertBusinessSinglePaymentActivity(
                            is_new_customers,
                            master_set_price_ids,
                            mechanism_id,
                            start_time,
                            end_time,
                            type,
                            tags,
                            price,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SelectActivityCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertBusinessActivitySuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectActivityCourseContract.View view = getView();
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
    public void insertBusinessSalesCourseActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String coupon_time,
            String discount_amount) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertBusinessSalesCourseActivity(
                            is_new_customers,
                            master_set_price_ids,
                            mechanism_id,
                            start_time,
                            end_time,
                            type,
                            tags,
                            coupon_time,
                            discount_amount,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SelectActivityCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertBusinessActivitySuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectActivityCourseContract.View view = getView();
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
    public void insertBusinessGroupingActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String number_of_people,
            String each_time_percentage,
            String each_time_percentage_max) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertBusinessGroupingActivity(
                            is_new_customers,
                            master_set_price_ids,
                            mechanism_id,
                            start_time,
                            end_time,
                            type,
                            tags,
                            number_of_people,
                            each_time_percentage,
                            each_time_percentage_max,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SelectActivityCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertBusinessActivitySuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectActivityCourseContract.View view = getView();
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
    public void insertBusinessExperienceSpecialsActivity(
            Boolean is_new_customers,
            String master_set_price_ids,
            String mechanism_id,
            String start_time,
            String end_time,
            String type,
            String tags,
            String coupon_time) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertBusinessExperienceSpecialsActivity(
                            is_new_customers,
                            master_set_price_ids,
                            mechanism_id,
                            start_time,
                            end_time,
                            type,
                            tags,
                            coupon_time,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SelectActivityCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertBusinessActivitySuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectActivityCourseContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
