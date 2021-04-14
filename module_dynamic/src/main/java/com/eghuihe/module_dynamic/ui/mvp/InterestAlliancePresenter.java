package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismCategoryModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class InterestAlliancePresenter extends BasePresenter<InterestAllianceContract.Model,InterestAllianceContract.View>
        implements InterestAllianceContract.Presenter {
    @Override
    public void queryMechanismCategoryChildList() {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismCategoryChildList(
                            new NetworkSubscriber<MechanismCategoryModel>(getView()){
                                @Override
                                protected void onSuccess(MechanismCategoryModel mechanismCategoryModel) {
                                    InterestAllianceContract.View view = getView();
                                    if (view!=null){
                                        view.onMechanismCategoryList(mechanismCategoryModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    InterestAllianceContract.View view = getView();
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
    protected InterestAllianceContract.Model createModule() {
        return new InterestAllianceModel();
    }

    @Override
    public void start() {

    }
}
