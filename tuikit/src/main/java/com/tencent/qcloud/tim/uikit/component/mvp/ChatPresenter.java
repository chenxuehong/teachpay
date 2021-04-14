package com.tencent.qcloud.tim.uikit.component.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldEntity;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ClassMessageHelper;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

import java.util.List;

public class ChatPresenter extends BasePresenter<ChatContract.Model, ChatContract.View>
        implements ChatContract.Presenter {
    @Override
    protected ChatContract.Model createModule() {
        return new ChatModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismCourseListById(String id) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismCourseListById(
                            id,
                            new NetworkSubscriber<CommodityOldModel>(null) {
                                @Override
                                protected void onSuccess(CommodityOldModel commodityOldModel) {
                                    ChatContract.View view = getView();
                                    if (view != null) {
                                        CommodityOldEntity data = commodityOldModel.getData();
                                        if (data != null) {
                                            List<MasterSetPriceEntity> list = data.list;
                                            view.onMechanismCourseList(list);
                                        }
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    ChatContract.View view = getView();
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
    public void updateUserAppointmentUserConfirm(
            String id,
            final Boolean whether,
            final ClassMessageHelper classMessageHelper,
            final MessageInfo messageInfo) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().updateUserAppointmentUserConfirm(
                            id,
                            whether,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ChatContract.View view = getView();
                                    if (view != null) {
                                        view.onUpdateUserAppointmentUserConfirm(whether, classMessageHelper,messageInfo);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ChatContract.View view = getView();
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
    public void queryMasterAppointmentStatusById(String id, final MessageInfo messageInfo,final ClassMessageHelper classMessageHelper) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMasterAppointmentStatusById(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ChatContract.View view = getView();
                                    if (view != null) {
                                        view.onMasterAppointmentStatus(messageInfo,insertInfoResultModel.getData(),classMessageHelper);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ChatContract.View view = getView();
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
