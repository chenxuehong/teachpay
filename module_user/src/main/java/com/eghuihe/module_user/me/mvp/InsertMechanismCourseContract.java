package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class InsertMechanismCourseContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismDetailInfoListById(
                String id,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);
    }

    public interface View extends IStateView {

        void onMechanismInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity);
    }

    public interface Presenter {

        void queryMechanismDetailInfoListById(
                String id,
                String type
        );
    }
}
