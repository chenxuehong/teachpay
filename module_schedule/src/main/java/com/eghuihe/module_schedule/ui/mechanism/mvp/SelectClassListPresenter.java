package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismClassModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SelectClassListPresenter extends BasePresenter<SelectClassListContract.Model,SelectClassListContract.View>
        implements SelectClassListContract.Presenter {
    @Override
    public void queryMechanismClasses(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String master_set_price_id,
            Boolean is_scheduling_over,
            String status) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismClasses(
                            mechanism_id,
                            currentPage,
                            pageSize,
                            master_set_price_id,
                            is_scheduling_over,
                            status,
                            new NetworkSubscriber<MechanismClassModel>(null){
                                @Override
                                protected void onSuccess(MechanismClassModel mechanismClassModel) {
                                    SelectClassListContract.View view = getView();
                                    if (view!=null){
                                        view.onMechanismClassList(mechanismClassModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectClassListContract.View view = getView();
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
    public void insertMechanismClasses(
            String mechanism_id,
            String name,
            String master_set_price_id,
            String student_count_max) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertMechanismClasses(
                            mechanism_id,
                            name,
                            master_set_price_id,
                            student_count_max,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SelectClassListContract.View view = getView();
                                    if (view!=null){
                                        view.onInsertMechanismClassSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectClassListContract.View view = getView();
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
    protected SelectClassListContract.Model createModule() {
        return new SelectClassListModel();
    }

    @Override
    public void start() {

    }
}
