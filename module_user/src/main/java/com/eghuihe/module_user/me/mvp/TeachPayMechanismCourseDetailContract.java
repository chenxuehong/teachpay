package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.LiveDetailEntity;
import com.huihe.base_lib.model.LiveDetailModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.OneCourseOrderModel;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.personal.CommentEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterCommentModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismCourseDetailContract {
    public interface Model extends IBaseModel {

        DisposableObserver aliPayExclusive(
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
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver wxPayExclusive(
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
                NetworkSubscriber<WxPayModel> subscriber);

        DisposableObserver payOneCourseOrder(
                String user_id,
                String member_duration,
                String rcharge_abstract,
                String studycard_id,
                String binding_mechanism_id,
                NetworkSubscriber<OneCourseOrderModel> subscriber);

        DisposableObserver userFollowMechanismInsert(
                String user_id,
                String mechanism_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver queryMechanismCourseListById(
                String id,
                NetworkSubscriber<CommodityOldModel> subscriber);

        DisposableObserver queryMechanismMasterSetPriceDisplay(
                String id,
                String live_streaming_id,
                NetworkSubscriber<MasterSetPriceDisplayModel> subscriber);

        DisposableObserver queryMechanismCourseCommentList(
                String mastersetprice_id,
                String type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<MechanismCommentModel> subscriber);

        DisposableObserver insertUserGrouping(
                String user_id,
                String master_set_price_id,
                String study_card_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver queryMasterSetPriceStreamDetail(
                String id,
                NetworkSubscriber<LiveDetailModel> subscriber);

    }

    public interface View extends IStateView {
        void onGetAliOrderInfo(String orderInfo);

        void onGetWxOrderInfo(WxPayModel.WxPayEntity wxPayEntity);

        void onMasterSetPriceStreamDetail(LiveDetailEntity liveDetailEntity);

        void onMechanismCourseInfo(List<MasterSetPriceEntity> masterSetPriceEntities);

        void onPayOneCourseOrder(StudentCoursePackageEntity studentCoursePackageEntity);

        void onUserFollowMechanismInsert();

        void onPingGroupSuccess(String content);

        void onMechanismCourseCommentList(List<MechanismCommentEntity> commentEntities);

        int getPageSize();

        int getCurrentPage();
    }

    public interface Presenter {

        void aliPayExclusive(
                String studycard_id,
                String user_id,
                String rcharge_type,
                String amount,
                String course_num,
                String member_duration,
                String mechanism_id,
                String study_type,
                String rcharge_abstract,
                String master_id);

        void wxPayExclusive(
                String studycard_id,
                String user_id,
                String rcharge_type,
                String amount,
                String course_num,
                String member_duration,
                String mechanism_id,
                String study_type,
                String rcharge_abstract,
                String master_id);

        void payOneCourseOrder(
                String user_id,
                String member_duration,
                String rcharge_abstract,
                String studycard_id,
                String binding_mechanism_id);

        void userFollowMechanismInsert(
                String user_id,
                String mechanism_id);

        void queryMechanismCourseListById(
                String id);

        void queryMechanismCourseCommentList(
                String mastersetprice_id,
                String type,
                Integer currentPage,
                Integer pageSize);

        void insertUserGrouping(
                String user_id,
                String master_set_price_id,
                String study_card_id);

        void queryMasterSetPriceStreamDetail(
                String id);
        void queryMechanismMasterSetPriceDisplay(
                String id,
                String live_streaming_id);
    }
}
