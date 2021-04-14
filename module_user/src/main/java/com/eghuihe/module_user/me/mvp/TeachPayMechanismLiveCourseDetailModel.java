package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismLiveCourseDetailModel implements TeachPayMechanismLiveCourseDetailContract.Model {

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
    public DisposableObserver queryMechanismMasterSetPriceDisplay(
            String id,
            String live_streaming_id,
            NetworkSubscriber<MasterSetPriceDisplayModel> subscriber) {
        return UserServiceImpl.queryMasterSetPriceDisplayDetail(
                id,
                live_streaming_id,
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

}
