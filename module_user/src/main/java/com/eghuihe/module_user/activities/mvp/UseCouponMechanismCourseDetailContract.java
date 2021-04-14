package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class UseCouponMechanismCourseDetailContract {
    public interface Model extends IBaseModel {

        DisposableObserver userFollowMechanismInsert(
                String user_id,
                String mechanism_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver queryMechanismCourseListById(
                String id,
                NetworkSubscriber<CommodityOldModel> subscriber);

        DisposableObserver queryMechanismCourseCommentList(
                String mastersetprice_id,
                String type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<MechanismCommentModel> subscriber);
        DisposableObserver useCoupon(
                String id,
                String master_set_price_id,
                String user_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

    }

    public interface View extends IStateView {

        void onMechanismCourseInfo(List<MasterSetPriceEntity> masterSetPriceEntities);
        void onUserFollowMechanismInsert();
        void onMechanismCourseCommentList(List<MechanismCommentEntity> commentEntities);
        int getPageSize();
        int getCurrentPage();
        void onUseCouponSuccess();
    }

    public interface Presenter {
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
        void useCoupon(
                String id,
                String master_set_price_id,
                String user_id);
    }
}
