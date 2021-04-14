package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismCategoryModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ResearchInstitutePresenter extends BasePresenter<ResearchInstituteContract.Model, ResearchInstituteContract.View>
        implements ResearchInstituteContract.Presenter {
    @Override
    public void queryMechanismCategoryChildList() {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismCategoryChildList(
                            new NetworkSubscriber<MechanismCategoryModel>(null) {

                                @Override
                                protected void onSuccess(MechanismCategoryModel mechanismCategoryModel) {
                                    ResearchInstituteContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismCategoryList(mechanismCategoryModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ResearchInstituteContract.View view = getView();
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
    protected ResearchInstituteContract.Model createModule() {
        return new ResearchInstituteModel();
    }

    @Override
    public void start() {

    }
}
