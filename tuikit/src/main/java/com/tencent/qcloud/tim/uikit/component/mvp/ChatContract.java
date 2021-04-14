package com.tencent.qcloud.tim.uikit.component.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.holder.ClassMessageHelper;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ChatContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismCourseListById(
                String id,
                NetworkSubscriber<CommodityOldModel> subscriber);
        DisposableObserver updateUserAppointmentUserConfirm(
                String id,
                Boolean whether,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver queryMasterAppointmentStatusById(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onMechanismCourseList(List<MasterSetPriceEntity> masterSetPriceEntities);
        void onUpdateUserAppointmentUserConfirm(Boolean whether, ClassMessageHelper classMessageHelper,MessageInfo messageInfo);
        void onMasterAppointmentStatus(MessageInfo messageInfo, String result, ClassMessageHelper classMessageHelper);
    }

    public interface Presenter {
        void queryMechanismCourseListById(
                String id);
        void updateUserAppointmentUserConfirm(
                String id,
                Boolean whether,
                ClassMessageHelper classMessageHelper,
                MessageInfo messageInfo);
        void queryMasterAppointmentStatusById(
                String id,
                MessageInfo messageInfo, ClassMessageHelper classMessageHelper);
    }
}
