package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayCourseListContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryNearByCourse(
                Integer status,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String type,
                NetworkSubscriber<MasterSetPriceModel> subscriber);
    }

    public interface View extends IStateView {
        void showHotMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities);
    }

    public interface Presenter {

        void queryNearByCourse(
                Integer status,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String type);
    }
}
