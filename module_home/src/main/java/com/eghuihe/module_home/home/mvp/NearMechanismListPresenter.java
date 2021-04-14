package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class NearMechanismListPresenter extends BasePresenter<NearMechanismListContract.Model,NearMechanismListContract.View>
        implements NearMechanismListContract.Presenter {
    @Override
    public void queryMechanismByType(
            Integer pageSize,
            Integer currentPage,
            String latitude,
            String longitude,
            String sortName) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismByType(
                             pageSize,
                             currentPage,
                             latitude,
                             longitude,
                             sortName,
                            new NetworkSubscriber<MasterMechanismModel>(null){
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    NearMechanismListContract.View view = getView();
                                    if (view!=null){
                                        view.showMechanismList(masterMechanismModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    NearMechanismListContract.View view = getView();
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
    protected NearMechanismListContract.Model createModule() {
        return new NearMechanismListModel();
    }

    @Override
    public void start() {

    }
}
