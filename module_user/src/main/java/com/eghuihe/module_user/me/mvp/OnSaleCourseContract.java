package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class OnSaleCourseContract {
    public interface Model extends IBaseModel {

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
        void showExclusiveCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities);
    }

    public interface Presenter {

        void queryExclusiveCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize);
    }
}
