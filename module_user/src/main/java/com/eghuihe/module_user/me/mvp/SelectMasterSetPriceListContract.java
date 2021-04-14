package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class SelectMasterSetPriceListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMasterSetPriceList(
                String mechanism_id,
                String status,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<CommodityModel> subscriber);

    }

    public interface View extends IStateView {
        void showMasterSetPriceList(List<MasterSetPriceEntity> exclusiveCoursesEntities);
    }

    public interface Presenter {

        void queryMasterSetPriceList(
                String mechanism_id,
                String status,
                Integer currentPage,
                Integer pageSize);
    }
}
