package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.BusinessActivityTypeModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ActivitiesContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryBusinessActivityTypeList(
                Integer currentPage,
                Integer pageSize,
                String status,
                DisposableObserver<BusinessActivityTypeModel> subscriber);
    }

    public interface View extends IStateView {
        void showBusinessActivityTypeList(List<BusinessActivityTypeEntity> activityTypeEntities);
    }

    public interface Presenter {
        void queryBusinessActivityTypeList(
                Integer currentPage,
                Integer pageSize,
                String status);
    }
}
