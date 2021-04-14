package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MechanismQueryListPresenter extends BasePresenter<MechanismQueryListContract.Model, MechanismQueryListContract.View>
        implements MechanismQueryListContract.Presenter {
    @Override
    public void queryMechanismByEs(
            String mechanism_name,
            Integer currentPage,
            Integer pageSize,
            String latitude,
            String longitude,
            String sortName,
            String categories,
            String categories_child,
            String type
    ) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismByEs(
                            mechanism_name,
                            currentPage,
                            pageSize,
                            latitude,
                            longitude,
                            sortName,
                            categories,
                            categories_child,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(null) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    MechanismQueryListContract.View view = getView();
                                    if (view != null) {
                                        view.showMechanismList(masterMechanismModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MechanismQueryListContract.View view = getView();
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
    protected MechanismQueryListContract.Model createModule() {
        return new MechanismQueryListModel();
    }

    @Override
    public void start() {

    }
}
