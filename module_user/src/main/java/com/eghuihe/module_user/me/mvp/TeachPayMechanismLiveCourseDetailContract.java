package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.LiveDetailEntity;
import com.huihe.base_lib.model.LiveDetailModel;
import com.huihe.base_lib.model.MasterSetPriceDisplayModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.MechanismCommentEntity;
import com.huihe.base_lib.model.MechanismCommentModel;
import com.huihe.base_lib.model.OneCourseOrderModel;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayMechanismLiveCourseDetailContract {
    public interface Model extends IBaseModel {

        DisposableObserver userFollowMechanismInsert(
                String user_id,
                String mechanism_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

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

    }

    public interface View extends IStateView {

        void onMechanismCourseInfo(List<MasterSetPriceEntity> masterSetPriceEntities);

        void onUserFollowMechanismInsert();

        void onMechanismCourseCommentList(List<MechanismCommentEntity> commentEntities);
        int getPageSize();

        int getCurrentPage();
    }

    public interface Presenter {


        void userFollowMechanismInsert(
                String user_id,
                String mechanism_id);
        void queryMechanismCourseCommentList(
                String mastersetprice_id,
                String type,
                Integer currentPage,
                Integer pageSize);
        void queryMechanismMasterSetPriceDisplay(
                String id,
                String live_streaming_id);

    }
}
