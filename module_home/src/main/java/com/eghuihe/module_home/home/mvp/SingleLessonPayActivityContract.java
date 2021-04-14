package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class SingleLessonPayActivityContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryActivityListPageByType(
                Integer currentPage,
                Integer pageSize,
                String status,
                String type,
                String latitude,
                String longitude,
                DisposableObserver<MasterSetPriceModel> subscriber);
    }

    public interface View extends IStateView {

        void onCoureList(List<MasterSetPriceEntity> masterSetPriceEntities);
    }

    public interface Presenter {
        void queryActivityListPageByType(
                Integer currentPage,
                Integer pageSize,
                String status,
                String type,
                String latitude,
                String longitude);
    }
}
