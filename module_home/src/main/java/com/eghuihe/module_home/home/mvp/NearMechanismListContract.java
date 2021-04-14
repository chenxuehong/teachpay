package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class NearMechanismListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismByType(
                Integer pageSize,
                Integer currentPage,
                String latitude,
                String longitude,
                String sortName,
                NetworkSubscriber<MasterMechanismModel> subscriber);
    }

    public interface View extends IStateView {
        void showMechanismList(List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities);
    }

    public interface Presenter {
        void queryMechanismByType(
                Integer pageSize,
                Integer currentPage,
                String latitude,
                String longitude,
                String sortName);

    }
}
