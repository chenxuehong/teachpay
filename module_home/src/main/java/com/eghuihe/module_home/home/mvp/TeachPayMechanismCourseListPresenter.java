package com.eghuihe.module_home.home.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class TeachPayMechanismCourseListPresenter extends BasePresenter<TeachPayMechanismCourseListContract.Model,TeachPayMechanismCourseListContract.View>
        implements TeachPayMechanismCourseListContract.Presenter {
    @Override
    public void queryMechanismCourseList(
            String mechanism_id,
            String type,
            String status,
            String appointment_type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismCourseList(
                            mechanism_id,
                            type,
                            status,
                            appointment_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<CommodityModel>(null){
                                @Override
                                protected void onSuccess(CommodityModel commodityModel) {
                                    TeachPayMechanismCourseListContract.View view = getView();
                                    if (view!=null){
                                        view.showMechanismCourseList(commodityModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    TeachPayMechanismCourseListContract.View view = getView();
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
    protected TeachPayMechanismCourseListContract.Model createModule() {
        return new TeachPayMechanismCourseListModel();
    }

    @Override
    public void start() {

    }
}
