package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MasterSetPriceModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class LiveRoomAudiencePresenter extends BasePresenter<LiveRoomAudienceContract.Model,LiveRoomAudienceContract.View>
        implements LiveRoomAudienceContract.Presenter {
    @Override
    protected LiveRoomAudienceContract.Model createModule() {
        return new LiveRoomAudienceModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void queryMechanismDisplayIsLiving(String live_streaming_id, String status) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismDisplayIsLiving(
                            live_streaming_id,
                            status,
                            new NetworkSubscriber<MasterSetPriceModel>(null){
                                @Override
                                protected void onSuccess(MasterSetPriceModel masterSetPriceModel) {
                                    LiveRoomAudienceContract.View view = getView();
                                    if (view!=null){
                                        List<MasterSetPriceEntity> data = masterSetPriceModel.getData();
                                        if (data!=null && data.size()>0){
                                            MasterSetPriceEntity masterSetPriceEntity = data.get(0);
                                            view.onMechanismDisplay(masterSetPriceEntity);
                                        }
                                    }
                                }
                            }
                    )
            );
        }
    }
}
