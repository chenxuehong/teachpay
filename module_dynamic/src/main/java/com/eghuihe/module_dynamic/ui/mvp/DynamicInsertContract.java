package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class DynamicInsertContract {
    public interface Model extends IBaseModel {

        DisposableObserver noteUserInsert(
                String content,
                String user_id,
                Integer status,
                String picts,
                Integer style,
                String classfiy,
                String master_set_price_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onInsertSuccess();
    }

    public interface Presenter {
        void noteUserInsert(
                String content,
                String user_id,
                Integer status,
                String picts,
                Integer style,
                String classfiy,
                String master_set_price_id);
    }
}
