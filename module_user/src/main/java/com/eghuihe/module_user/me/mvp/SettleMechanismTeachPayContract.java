package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class SettleMechanismTeachPayContract {
    public interface Model extends IBaseModel {

        DisposableObserver settleMechanism(
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
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onSettleSuccess();
    }

    public interface Presenter {

        void settleMechanism(
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
        );

    }
}
