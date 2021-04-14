package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import io.reactivex.observers.DisposableObserver;

public class EditMechanismInfoModel implements EditMechanismInfoContract.Model {
    @Override
    public DisposableObserver updateMasterMechanism(
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
            String facade_view,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.updateMasterMechanism(
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
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismInfo(String user_id, Integer Status, String type, NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismInfo(user_id, Status,type, subscriber);
    }
}
