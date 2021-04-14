package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class EditMechanismInfoPresenter extends BasePresenter<EditMechanismInfoContract.Model, EditMechanismInfoContract.View>
        implements EditMechanismInfoContract.Presenter {
    @Override
    protected EditMechanismInfoContract.Model createModule() {
        return new EditMechanismInfoModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void updateMasterMechanism(
            String id,
            String user_id,
            String categories,
            String categories_child,
            String mechanism_logo,
            String mechanism_name,
            String mechanism_addr,
            Double longitude,
            Double latitude,
            String mechanism_telephone,
            String contact_telephone,
            String contacts,
            String introduction_pic,
            String introduction_content,
            String support_means,
            String facade_view) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateMasterMechanism(
                            id,
                            user_id,
                            categories,
                            categories_child,
                            mechanism_logo,
                            mechanism_name,
                            mechanism_addr,
                            longitude,
                            latitude,
                            mechanism_telephone,
                            contact_telephone,
                            contacts,
                            introduction_pic,
                            introduction_content,
                            support_means,
                            facade_view,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    EditMechanismInfoContract.View view = getView();
                                    if (view != null) {
                                        view.onUpdateSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    EditMechanismInfoContract.View view = getView();
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
    public void queryMechanismInfo(String user_id, Integer status, String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismInfo(
                            user_id,
                            status,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(getView()) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    EditMechanismInfoContract.View view = getView();
                                    if (view != null) {
                                        List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities = masterMechanismModel.getData();
                                        if (masterMechanismEntities != null && masterMechanismEntities.size() > 0) {
                                            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = masterMechanismEntities.get(0);
                                            view.onMechanismInfo(masterMechanismEntity);
                                        }
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    EditMechanismInfoContract.View view = getView();
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
