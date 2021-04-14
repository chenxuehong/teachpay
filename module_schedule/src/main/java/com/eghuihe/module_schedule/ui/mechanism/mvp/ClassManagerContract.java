package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismOfflineScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ClassManagerContract {
    public interface Model extends IBaseModel {
        DisposableObserver insertMechanismClasses(
                String mechanism_id,
                String name,
                String master_set_price_id,
                String student_count_max,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver updateMergerClass(
                String id,
                String merger_ids,
                String status,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver queryExclusiveCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<CommodityModel> subscriber);
    }

    public interface View extends IStateView {
        void onInsertMechanismClassSuccess();
        void onUpdateMergeClassSuccess();
        void showExclusiveCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities);
    }

    public interface Presenter {
        void insertMechanismClasses(
                String mechanism_id,
                String name,
                String master_set_price_id,
                String student_count_max);
        void updateMergerClass(
                String id,
                String merger_ids,
                String status);
        void queryExclusiveCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize);
    }
}
