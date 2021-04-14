package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachingPayHomeContract {
    public interface Model extends IBaseModel {

        DisposableObserver getBannerData(
                String type,
                String state,
                DisposableObserver<BannerModel> subscriber);

        DisposableObserver queryActivityListPageByType(
                String status,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String type,
                NetworkSubscriber<MasterSetPriceModel> subscriber);

        DisposableObserver queryMechanismByType(
                Integer pageSize,
                Integer currentPage,
                String latitude,
                String longitude,
                String sortName,
                NetworkSubscriber<MasterMechanismModel> subscriber);

        DisposableObserver queryMechanismInfo(
                String user_id,
                Integer Status,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);

        // userCoupon/insertUserCollection
        DisposableObserver insertUserCollection(
                String user_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void showBannerList(List<BannerModel.BannerEntity> bannerEntities);

        void showHotMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities);

        void showMechanismList(List<MasterMechanismModel.MasterMechanismEntity> masterMechanismEntities, String type);

        void onMyMechanismInfo(List<MasterMechanismModel.MasterMechanismEntity> mechanismEntities);
        void onUserCollectionSuccess();
    }

    public interface Presenter {
        void queryActivityListPageByType(
                String status,
                Integer currentPage,
                Integer pageSize,
                String latitude,
                String longitude,
                String type);

        void queryMechanismByType(
                Integer pageSize,
                Integer currentPage,
                String latitude,
                String longitude,
                String sortName,
                String type,
                boolean loadMore);

        void getBannerData(
                String type,
                String state);

        void queryMechanismInfo(
                String user_id,
                Integer Status,
                String type);
        void insertUserCollection(
                String user_id);
    }
}
