package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class SettleMechanismTeachPayModel implements SettleMechanismTeachPayContract.Model {
    @Override
    public DisposableObserver settleMechanism(
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
            String facade_view,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.settleTeachPayMechanism(
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
                subscriber
        );
    }
}
