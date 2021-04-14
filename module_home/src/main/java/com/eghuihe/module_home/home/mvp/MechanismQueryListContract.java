package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MechanismQueryListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismByEs(
                String mechanism_name,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String sortName,
                String categories,
                String categories_child,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);
    }

    public interface View extends IStateView {
        void showMechanismList(List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities);
    }

    public interface Presenter {

        void queryMechanismByEs(
                String mechanism_name,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String sortName,
                String categories,
                String categories_child,
                String type);

    }
}
