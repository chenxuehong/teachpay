package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.MechanismUserEntity;
import com.huihe.base_lib.model.MechanismUserModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class CustomerManagerContract {
    public interface Model extends IBaseModel {
        DisposableObserver getMechanismUserList(
                Integer currentPage,
                Integer pageSize,
                String mechanism_id,
                DisposableObserver<MechanismUserModel> subscriber);
        DisposableObserver mechanismUserStatus(
                String id,
                Boolean is_new,
                DisposableObserver<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismUserList(List<MechanismUserEntity> mechanismUserEntities);
        void onIsNewCustom(boolean isNewCustom);
    }

    public interface Presenter {
        void getMechanismUserList(
                Integer currentPage,
                Integer pageSize,
                String mechanism_id);
        void mechanismUserStatus(
                String id,
                Boolean is_new);
    }
}
