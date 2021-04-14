package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MainContract {
    public interface Model extends IBaseModel {
        DisposableObserver getBannerData(
                String type,
                String state,
                NetworkSubscriber subscriber);
    }

    public interface View extends IStateView {

        void showBanner(List<BannerModel.BannerEntity> bannerEntities);
    }

    public interface Presenter {

        void getBannerData(
                String type,
                String state);
    }
}
