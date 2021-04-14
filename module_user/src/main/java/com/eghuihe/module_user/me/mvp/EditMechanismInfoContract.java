package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class EditMechanismInfoContract {
    public interface Model extends IBaseModel {

        DisposableObserver updateMasterMechanism(
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
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver queryMechanismInfo(
                String user_id,
                Integer Status,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);
    }

    public interface View extends IStateView {

        void onUpdateSuccess();
        void onMechanismInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity);
    }

    public interface Presenter {
        void updateMasterMechanism(
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
                String facade_view);

        void queryMechanismInfo(
                String user_id,
                Integer Status,
                String type
                );
    }
}
