package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.UserStatisticsModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayMeContract {
    public interface Model extends IBaseModel {


        DisposableObserver getBannerData(
                String type,
                String state,
                DisposableObserver<BannerModel> subscriber);

        DisposableObserver queryMechanismInfo(
                String user_id,
                Integer Status,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);

        DisposableObserver queryTeachPayUserStatistics(
                String user_id,
                NetworkSubscriber<UserStatisticsModel> subscriber);
        DisposableObserver queryUserInfo(
                String user_id,
                NetworkSubscriber<UserInfoModel> subscriber);
    }

    public interface View extends IStateView {
        void bannerData(List<BannerModel.BannerEntity> bannerEntities);

        void onMechanismInfo(List<MasterMechanismModel.MasterMechanismEntity> mechanismEntities);
        void onUserStatistics(UserStatisticsModel.UserStatisticsEntity userStatisticsEntity);
        void onUserInfo(UserInfoEntity userInfoEntity);
    }

    public interface Presenter {

        void getBannerData(
                String type,
                String state);

        void queryMechanismInfo(
                String user_id,
                Integer Status,
                String type);
        void queryTeachPayUserStatistics(
                String user_id);
        void queryUserInfo(
                String user_id);
    }
}
