package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class SingleLessonPayActivityModel implements SingleLessonPayActivityContract.Model {
    @Override
    public DisposableObserver queryActivityListPageByType(
            Integer currentPage,
            Integer pageSize,
            String status,
            String type,
            String latitude,
            String longitude,
            DisposableObserver<MasterSetPriceModel> subscriber) {
        return UserServiceImpl.queryActivityListPageByType(
                currentPage,
                pageSize,
                status,
                type,
                latitude,
                longitude,
                subscriber
        );
    }

}
