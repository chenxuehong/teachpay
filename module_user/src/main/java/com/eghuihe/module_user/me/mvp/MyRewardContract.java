package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.TeachPaypalDetailEntity;
import com.huihe.base_lib.model.TeachPaypalDetailModel;
import com.huihe.base_lib.model.TeachPaypalUserGoldTypeModel;
import com.huihe.base_lib.model.UserGoldTypeEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.InsertSignDayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MyRewardContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryTeachPaypalDetail(
                String user_id,
                NetworkSubscriber<TeachPaypalDetailModel> subscriber
        );

        DisposableObserver queryTeachPaypalUserGoldType(
                String user_id,
                String status,
                Boolean is_teach_paypal,
                NetworkSubscriber<TeachPaypalUserGoldTypeModel> subscriber
        );
        DisposableObserver queryNearByCourse(
                Integer status,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String type,
                NetworkSubscriber<MasterSetPriceModel> subscriber);

        DisposableObserver insertLoginSignIn(
                String user_id,
                NetworkSubscriber<InsertSignDayModel> subscriber);
    }

    public interface View extends IStateView {
        void onTeachPaypalDetail(TeachPaypalDetailEntity teachPaypalDetailEntity);

        void onUserGoldTypeList(List<UserGoldTypeEntity> userGoldTypeEntities);
        void onCourseList(List<MasterSetPriceEntity> masterSetPriceEntities);
        void onSignSucess();
    }

    public interface Presenter {

        void queryTeachPaypalDetail(
                String user_id
        );

        void queryTeachPaypalUserGoldType(
                String user_id,
                String status,
                Boolean is_teach_paypal
        );
        void queryNearByCourse(
                Integer status,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String type);

        void insertLoginSignIn(
                String user_id);
    }
}
