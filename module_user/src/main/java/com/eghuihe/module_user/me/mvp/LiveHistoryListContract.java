package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.LiveEntity;
import com.huihe.base_lib.model.LiveModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class LiveHistoryListContract {

    public interface Model extends IBaseModel {
        DisposableObserver getLiveMasterSetPriceList(
                Integer currentPage,
                Integer pageSize,
                String mechanism_id,
                String status,
                DisposableObserver<LiveModel> subscriber);
    }

    public interface View extends IStateView {
        void showLiveMasterSetPriceList(List<LiveEntity> liveEntities);
    }

    public interface Presenter {

        void getLiveMasterSetPriceList(
                Integer currentPage,
                Integer pageSize,
                String mechanism_id,
                String status);
    }
}
