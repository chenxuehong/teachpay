package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.LiveDetailModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.OneCourseOrderModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterCommentModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismCourseDetailModel implements TeachPayMechanismCourseDetailContract.Model {
    @Override
    public DisposableObserver aliPayExclusive(
            String studycard_id,
            String user_id,
            String rcharge_type,
            String amount,
            String course_num,
            String member_duration,
            String mechanism_id,
            String study_type,
            String rcharge_abstract,
            String master_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.aliPayExclusive(
                studycard_id,
                user_id,
                rcharge_type,
                amount,
                course_num,
                member_duration,
                mechanism_id,
                study_type,
                rcharge_abstract,
                master_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver wxPayExclusive(
            String studycard_id,
            String user_id,
            String rcharge_type,
            String amount,
            String course_num,
            String member_duration,
            String mechanism_id,
            String study_type,
            String rcharge_abstract,
            String master_id,
            NetworkSubscriber<WxPayModel> subscriber) {
        return UserServiceImpl.wxPayExclusive(
                studycard_id,
                user_id,
                rcharge_type,
                amount,
                course_num,
                member_duration,
                mechanism_id,
                study_type,
                rcharge_abstract,
                master_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver payOneCourseOrder(
            String user_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String binding_mechanism_id,
            NetworkSubscriber<OneCourseOrderModel> subscriber) {
        return UserServiceImpl.payOneCourseOrder(
                user_id,
                member_duration,
                rcharge_abstract,
                studycard_id,
                binding_mechanism_id,
                subscriber
        );
    }

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

    @Override
    public DisposableObserver insertUserGrouping(
            String user_id,
            String master_set_price_id,
            String study_card_id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertUserGrouping(
                user_id,
                master_set_price_id,
                study_card_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMasterSetPriceStreamDetail(
            String id,
            NetworkSubscriber<LiveDetailModel> subscriber) {
        return UserServiceImpl.queryMasterSetPriceStreamDetail(
                id,
                subscriber
        );
    }

}
