package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.UserCouponModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.manager.LoginHelper;

public class MyCardPresenter extends BasePresenter<MyCardContract.Model,MyCardContract.View>
        implements MyCardContract.Presenter {
    @Override
    protected MyCardContract.Model createModule() {
        return new MyCardModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryUserCouponList(
            String user_id,
            String status,
            Integer pageSize,
            Integer currentPage) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryUserCouponList(
                            LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id(),
                            status,
                            pageSize,
                            currentPage,
                            new NetworkSubscriber<UserCouponModel>(null){
                                @Override
                                protected void onSuccess(UserCouponModel userCouponModel) {
                                    MyCardContract.View view = getView();
                                    if (view!=null){
                                        view.onUserCouponList(userCouponModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    MyCardContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
