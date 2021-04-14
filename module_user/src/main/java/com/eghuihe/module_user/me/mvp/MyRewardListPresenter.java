package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MyPointsModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class MyRewardListPresenter extends BasePresenter<MyRewardListContract.Model, MyRewardListContract.View>
        implements MyRewardListContract.Presenter {
    @Override
    protected MyRewardListContract.Model createModule() {
        return new MyRewardListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getUserPointsList(
            Integer currentPage,
            Integer pageSize,
            String user_id,
            Boolean is_earnings,
            String start_time) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getUserPointsList(
                            currentPage,
                            pageSize,
                            user_id,
                            is_earnings,
                            start_time,
                            new NetworkSubscriber<MyPointsModel>(null) {
                                @Override
                                protected void onSuccess(MyPointsModel myPointsModel) {
                                    MyRewardListContract.View view = getView();
                                    if (view != null) {
                                        view.onMyPointsList(myPointsModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    MyRewardListContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
