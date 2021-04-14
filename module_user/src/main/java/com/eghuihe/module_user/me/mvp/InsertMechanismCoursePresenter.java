package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class InsertMechanismCoursePresenter extends BasePresenter<InsertMechanismCourseContract.Model, InsertMechanismCourseContract.View>
        implements InsertMechanismCourseContract.Presenter {
    @Override
    public void queryMechanismDetailInfoListById(String mechanism_id, String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismDetailInfoListById(
                            mechanism_id,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(getView()) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    InsertMechanismCourseContract.View view = getView();
                                    if (view != null) {
                                        List<MasterMechanismModel.MasterMechanismEntity> data = masterMechanismModel.getData();
                                        if (data != null && data.size() > 0) {
                                            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = data.get(0);
                                            view.onMechanismInfo(masterMechanismEntity);
                                        }
                                    }

                                }

                                @Override
                                public void onComplete() {
                                    InsertMechanismCourseContract.View view = getView();
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
    protected InsertMechanismCourseContract.Model createModule() {
        return new InsertMechanismCourseModel();
    }

    @Override
    public void start() {

    }
}
