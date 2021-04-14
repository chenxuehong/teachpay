package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismTeacherListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismMasterInfoList(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String type,
                String status,
                NetworkSubscriber<MasterInfoHomeModel> subscriber
        );
    }

    public interface View extends IStateView {

        void onMechanismMasterInfoList(List<MasterInfoHomeModel.MasterInfoHomeEntity> masterInfoHomeEntities);
    }

    public interface Presenter {
        void queryMechanismMasterInfoList(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String type,
                String status
        );
    }
}
