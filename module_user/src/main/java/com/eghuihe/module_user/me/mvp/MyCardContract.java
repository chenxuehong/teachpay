package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.UserCouponEntity;
import com.huihe.base_lib.model.UserCouponModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MyCardContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryUserCouponList(
                String user_id,
                String status,
                Integer pageSize,
                Integer currentPage,
                NetworkSubscriber<UserCouponModel> subscriber
        );
    }

    public interface View extends IStateView {
        void onUserCouponList(List<UserCouponEntity> userCouponEntities);
    }

    public interface Presenter {
        void queryUserCouponList(
                String user_id,
                String status,
                Integer pageSize,
                Integer currentPage
        );
    }
}
