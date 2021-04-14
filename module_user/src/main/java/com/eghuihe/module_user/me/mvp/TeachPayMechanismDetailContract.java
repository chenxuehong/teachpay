package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismAverageScoreEntity;
import com.huihe.base_lib.model.MechanismAverageScoreModel;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.study.MasterInfoHomeModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismDetailContract {
    public interface Model extends IBaseModel {

        DisposableObserver userFollowMechanismInsert(
                String user_id,
                String mechanism_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver queryMechanismMasterInfoList(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String type,
                String status,
                NetworkSubscriber<MasterInfoHomeModel> subscriber);

        DisposableObserver queryMechanismDetailInfoListById(
                String id,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);

        DisposableObserver queryMechanismCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<CommodityModel> subscriber);

        DisposableObserver queryMechanismAverageScore(
                String mechanism_id,
                NetworkSubscriber<MechanismAverageScoreModel> subscriber);

        DisposableObserver queryMechanismCommentList(
                String mechanism_id,
                String type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<MechanismCommentModel> subscriber);
    }

    public interface View extends IStateView {
        void onUserFollowMechanismInsert();

        void onMechanismMasters(List<MasterInfoHomeModel.MasterInfoHomeEntity> masterInfoHomeEntities);

        void onMechanismInfo(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity);

        void onMasterSetPriceList(List<MasterSetPriceEntity> masterSetPriceEntities);

        void onMechanismAverageScore(MechanismAverageScoreEntity mechanismAverageScoreEntity);
        void onMechanismCommentList(List<MechanismCommentEntity> mechanismCommentEntities);
    }

    public interface Presenter {

        void userFollowMechanismInsert(
                String user_id,
                String mechanism_id);

        void queryMechanismMasterInfoList(
                String mechanism_id,
                Integer currentPage,
                Integer pageSize,
                String type,
                String status);

        void queryMechanismDetailInfoListById(
                String id,
                String type
        );

        void queryMechanismCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize);

        void queryMechanismAverageScore(
                String mechanism_id);

        void queryMechanismCommentList(
                String mechanism_id,
                String type,
                Integer currentPage,
                Integer pageSize);
    }
}
