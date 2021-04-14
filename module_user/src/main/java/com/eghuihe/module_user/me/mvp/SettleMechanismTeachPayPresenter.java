package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SettleMechanismTeachPayPresenter extends BasePresenter<SettleMechanismTeachPayContract.Model, SettleMechanismTeachPayContract.View>
        implements SettleMechanismTeachPayContract.Presenter {
    @Override
    public void settleMechanism(
            String user_id,
            String type,
            String categories,
            String mechanism_logo,
            String mechanism_name,
            String mechanism_addr,
            Boolean is_support_teach_paypal,
            double longitude,
            double latitude,
            String mechanism_telephone,
            String categories_child,
            String contact_telephone,
            String contacts,
            String payee_logon_id,
            String introduction_content,
            String support_means,
            String introduction_pic,
            String facade_view
    ) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().settleMechanism(
                            user_id,
                            type,
                            categories,
                            mechanism_logo,
                            mechanism_name,
                            mechanism_addr,
                            is_support_teach_paypal,
                            longitude,
                            latitude,
                            mechanism_telephone,
                            categories_child,
                            contact_telephone,
                            contacts,
                            payee_logon_id,
                            introduction_content,
                            support_means,
                            introduction_pic,
                            facade_view,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    SettleMechanismTeachPayContract.View view = getView();
                                    if (view != null) {
                                        view.onSettleSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SettleMechanismTeachPayContract.View view = getView();
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
    protected SettleMechanismTeachPayContract.Model createModule() {
        return new SettleMechanismTeachPayModel();
    }

    @Override
    public void start() {

    }
}
