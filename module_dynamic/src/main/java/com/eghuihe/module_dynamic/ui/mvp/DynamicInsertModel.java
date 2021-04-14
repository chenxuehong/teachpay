package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class DynamicInsertModel implements DynamicInsertContract.Model {
    @Override
    public DisposableObserver noteUserInsert(
            String content,
            String user_id,
            Integer status,
            String picts,
            Integer style,
            String classfiy,
            String master_set_price_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.noteUserInsert(
                content,
                user_id,
                status,
                picts,
                style,
                classfiy,
                master_set_price_id,
                subscriber
        );
    }
}
