package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class LiveRoomAnchorContract {
    public interface Model extends IBaseModel {

        DisposableObserver updateAddClick(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver updateOpenLiving(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver updateCloseLiving(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver queryMechanismInfo(
                String user_id,
                Integer Status,
                String type,
                NetworkSubscriber<MasterMechanismModel> subscriber);
    }

    public interface View extends IStateView {
        void onOpenSuccess();
        void onCloseSuccess();
        void onUpdateViewNumSuccess();
        void onMechanismInfo(List<MasterMechanismModel.MasterMechanismEntity> mechanismEntities);
    }

    public interface Presenter {
        void updateOpenLiving(
                String id);
        void updateCloseLiving(
                String id);
        void queryMechanismInfo(
                String user_id,
                Integer Status,
                String type);
        void updateAddClick(
                String id);
    }
}
