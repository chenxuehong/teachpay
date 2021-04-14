package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MechanismAverageScoreModel;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismDetailModel implements TeachPayMechanismDetailContract.Model {
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
    public DisposableObserver queryMechanismMasterInfoList(
            String mechanism_id,
            Integer currentPage,
            Integer pageSize,
            String type,
            String status,
            NetworkSubscriber<MasterInfoHomeModel> subscriber) {
        return UserServiceImpl.queryMechanismMasterInfoList(
                mechanism_id,
                currentPage,
                pageSize,
                type,
                status,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismDetailInfoListById(
            String id,
            String type,
            NetworkSubscriber<MasterMechanismModel> subscriber) {
        return UserServiceImpl.queryMechanismDetailInfoListById(
                id,
                type,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<CommodityModel> subscriber) {
        return UserServiceImpl.queryMechanismCourseList(
                mechanism_id,
                type,
                status,
                appointment_type,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismAverageScore(
            String mechanism_id,
            NetworkSubscriber<MechanismAverageScoreModel> subscriber) {
        return UserServiceImpl.queryMechanismAverageScore(
                mechanism_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver queryMechanismCommentList(
            String mechanism_id,
            String type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<MechanismCommentModel> subscriber) {
        return UserServiceImpl.queryMechanismCommentList(
                mechanism_id,
                type,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
