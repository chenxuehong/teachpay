package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class ClassManagerPresenter extends BasePresenter<ClassManagerContract.Model, ClassManagerContract.View>
        implements ClassManagerContract.Presenter {
    @Override
    protected ClassManagerContract.Model createModule() {
        return new ClassManagerModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void insertMechanismClasses(
            String mechanism_id,
            String name,
            String master_set_price_id,
            String student_count_max) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertMechanismClasses(
                            mechanism_id,
                            name,
                            master_set_price_id,
                            student_count_max,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ClassManagerContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertMechanismClassSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ClassManagerContract.View view = getView();
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
    public void updateMergerClass(String id, String merger_ids, String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateMergerClass(
                            id,
                            merger_ids,
                            status,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ClassManagerContract.View view = getView();
                                    if (view != null) {
                                        view.onUpdateMergeClassSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ClassManagerContract.View view = getView();
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
    public void queryExclusiveCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryExclusiveCourseList(
                            mechanism_id,
                            type,
                            status,
                            appointment_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(null) {
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    ClassManagerContract.View view = getView();
                                    if (view != null) {
                                        List<MasterSetPriceEntity> masterSetPriceEntities = commodityModel.getData();
                                        view.showExclusiveCourseList(masterSetPriceEntities);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ClassManagerContract.View view = getView();
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
