package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.MyPointsEntity;
import com.huihe.base_lib.model.MyPointsModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MyRewardListContract {
    public interface Model extends IBaseModel {
        DisposableObserver getUserPointsList(
                Integer currentPage,
                Integer pageSize,
                String user_id,
                Boolean is_earnings,
                String start_time,
                DisposableObserver<MyPointsModel> subscriber);
    }

    public interface View extends IStateView {

        void onMyPointsList(List<MyPointsEntity> myPointsEntities);
    }

    public interface Presenter {
        void getUserPointsList(
                Integer currentPage,
                Integer pageSize,
                String user_id,
                Boolean is_earnings,
                String start_time);
    }
}
