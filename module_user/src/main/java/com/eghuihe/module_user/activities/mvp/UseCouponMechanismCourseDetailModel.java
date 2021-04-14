package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class UseCouponMechanismCourseDetailModel implements UseCouponMechanismCourseDetailContract.Model {

    @Override
    public DisposableObserver userFollowMechanismInsert(
            String user_id,
            String mechanism_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.userFollowMechanismInsert(
                user_id,
                mechanism_id,
                subscriber
        );
    }
    @Override
    public DisposableObserver queryMechanismCourseListById(
            String id,
            NetworkSubscriber<CommodityOldModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseListById(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismCourseCommentList(
            String mastersetprice_id,
            String type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<MechanismCommentModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseCommentList(
                mastersetprice_id,
                type,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver useCoupon(
            String id,
            String master_set_price_id,
            String user_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.useCoupon(
                id,
                master_set_price_id,
                user_id,
                subscriber
        );
    }
}
