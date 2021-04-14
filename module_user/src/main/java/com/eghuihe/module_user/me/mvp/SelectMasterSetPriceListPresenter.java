package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class SelectMasterSetPriceListPresenter extends BasePresenter<SelectMasterSetPriceListContract.Model,SelectMasterSetPriceListContract.View>
        implements SelectMasterSetPriceListContract.Presenter {
    @Override
    public void queryMasterSetPriceList(
            String mechanism_id,
            String status,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMasterSetPriceList(
                            mechanism_id,
                            status,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(null){
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    SelectMasterSetPriceListContract.View view = getView();
                                    if (view!=null){
                                        view.showMasterSetPriceList(commodityModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    SelectMasterSetPriceListContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected SelectMasterSetPriceListContract.Model createModule() {
        return new SelectMasterSetPriceListModel();
    }

    @Override
    public void start() {

    }
}
