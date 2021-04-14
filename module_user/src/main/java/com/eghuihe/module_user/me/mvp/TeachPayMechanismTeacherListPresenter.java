package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class TeachPayMechanismTeacherListPresenter extends BasePresenter<TeachPayMechanismTeacherListContract.Model, TeachPayMechanismTeacherListContract.View>
        implements TeachPayMechanismTeacherListContract.Presenter {

    @Override
    protected TeachPayMechanismTeacherListContract.Model createModule() {
        return new TeachPayMechanismTeacherListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismMasterInfoList(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String type,
            String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismMasterInfoList(
                            mechanism_id,
                            currentPage,
                            pageSize,
                            type,
                            status,
                            new NetworkSubscriber<MasterInfoHomeModel>(getView()) {
                                @Override
                                protected void onSuccess(MasterInfoHomeModel masterInfoHomeModel) {
                                    TeachPayMechanismTeacherListContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismMasterInfoList(masterInfoHomeModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismTeacherListContract.View view = getView();
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
