package com.eghuihe.module_order.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismOrderModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class StudentOrderListPresenter extends BasePresenter<StudentOrderListContract.Model, StudentOrderListContract.View>
        implements StudentOrderListContract.Presenter {
    @Override
    public void queryStudentOrderList(
            String study_type,
            String user_id,
            Integer pageSize,
            Integer currentPage,
            String status) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryStudentOrderList(
                            study_type,
                            user_id,
                            pageSize,
                            currentPage,
                            status,
                            new NetworkSubscriber<MechanismOrderModel>(null){
                                @Override
                                protected void onSuccess(MechanismOrderModel mechanismOrderModel) {
                                    StudentOrderListContract.View view = getView();
                                    if (view!=null){
                                        view.MechanismOrderList(mechanismOrderModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    StudentOrderListContract.View view = getView();
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
    protected StudentOrderListContract.Model createModule() {
        return new StudentOrderListModel();
    }

    @Override
    public void start() {

    }
}
