package com.eghuihe.module_user.login.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> implements MainContract.Presenter {

    @Override
    protected MainContract.Model createModule() {
        return new MainModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getBannerData(String type, String state) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getBannerData(type, state, new NetworkSubscriber<BannerModel>(null) {
                        @Override
                        protected void onSuccess(BannerModel bannerModel) {
                            super.onSuccess(bannerModel);
                            List<BannerModel.BannerEntity> bannerEntityList = bannerModel.getData();
                            getView().showBanner(bannerEntityList);
                        }
                    })
            );
        }
    }
}
