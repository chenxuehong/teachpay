package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MechanismCourseScheduleContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<CommodityModel> subscriber);
    }

    public interface View extends IStateView {
        void showMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities);
    }

    public interface Presenter {
        void queryMechanismCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize);

    }
}
