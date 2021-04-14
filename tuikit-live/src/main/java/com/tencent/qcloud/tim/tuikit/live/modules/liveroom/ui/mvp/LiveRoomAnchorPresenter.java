package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.Utils;
import com.huihe.base_lib.utils.manager.LoginHelper;

public class LiveRoomAnchorPresenter extends BasePresenter<LiveRoomAnchorContract.Model, LiveRoomAnchorContract.View>
        implements LiveRoomAnchorContract.Presenter {
    @Override
    protected LiveRoomAnchorContract.Model createModule() {
        return new LiveRoomAnchorModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void updateOpenLiving(String id) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateOpenLiving(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view != null) {
                                        view.onOpenSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void updateCloseLiving(String id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateCloseLiving(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view != null) {
                                        view.onCloseSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void queryMechanismInfo(String user_id, Integer Status, String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismInfo(
                            user_id,
                            Status,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(null) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view != null) {
                                        view.onMechanismInfo(masterMechanismModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void updateAddClick(String id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().updateAddClick(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    LiveRoomAnchorContract.View view = getView();
                                    if (view!=null){
                                        view.onUpdateViewNumSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    LiveRoomAnchorContract.View view = getView();
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
