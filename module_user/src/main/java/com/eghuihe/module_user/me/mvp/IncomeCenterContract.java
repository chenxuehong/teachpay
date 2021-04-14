package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.MechanismIncomeEntity;
import com.huihe.base_lib.model.MechanismIncomeListModel;
import com.huihe.base_lib.model.MechanismIncomeStatisticsEntity;
import com.huihe.base_lib.model.MechanismIncomeStatisticsModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class IncomeCenterContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismOfflineDetailsCount(
                String mechanism_id,
                DisposableObserver<MechanismIncomeStatisticsModel> subscriber);

        /*查询收益列表*/
        DisposableObserver queryMechanismOfflineDetails(
                String mechanism_id,
                String study_type,
                Boolean finished,
                Integer currentPage,
                Integer pageSize,
                DisposableObserver<MechanismIncomeListModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismIncomeStaticticsInfo(MechanismIncomeStatisticsEntity mechanismIncomeStatisticsEntity);

        void onMechanismIncomeList(List<MechanismIncomeEntity> mechanismIncomeEntities);
    }

    public interface Presenter {
        void queryMechanismOfflineDetailsCount(
                String mechanism_id);

        void queryMechanismOfflineDetails(
                String mechanism_id,
                String study_type,
                Boolean finished,
                Integer currentPage,
                Integer pageSize);
    }
}
