package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismUserModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CustomerManagerPresenter extends BasePresenter<CustomerManagerContract.Model, CustomerManagerContract.View>
        implements CustomerManagerContract.Presenter {
    @Override
    protected CustomerManagerContract.Model createModule() {
        return new CustomerManagerModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getMechanismUserList(
            Integer currentPage,
            Integer pageSize,
            String mechanism_id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getMechanismUserList(
                            currentPage,
                            pageSize,
                            mechanism_id,
                            new NetworkSubscriber<MechanismUserModel>(null) {
                                @Override
                                protected void onSuccess(MechanismUserModel mechanismUserModel) {
                                    CustomerManagerContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismUserList(mechanismUserModel.getData());
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void mechanismUserStatus(
            String id,
           final Boolean is_new) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().mechanismUserStatus(
                            id,
                            is_new,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    CustomerManagerContract.View view = getView();
                                    if (view!=null){
                                        view.onIsNewCustom(is_new);
                                    }
                                }
                            }
                    )
            );
        }
    }
}
