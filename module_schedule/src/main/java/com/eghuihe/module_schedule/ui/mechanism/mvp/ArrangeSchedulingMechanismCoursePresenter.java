package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityOldEntity;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class ArrangeSchedulingMechanismCoursePresenter extends BasePresenter<ArrangeSchedulingMechanismCourseContract.Model,ArrangeSchedulingMechanismCourseContract.View>
        implements ArrangeSchedulingMechanismCourseContract.Presenter {
    @Override
    public void insertMechanismCourse(
            String type,
            String source,
            String mechanism_id,
            String master_id,
            String title,
            String start_time,
            String end_time,
            String offset,
            String service_type,
            String master_set_price_id,
            String identity_type,
            String classroom,
            Integer number_of_lessons,
            String study_card_ids,
            String status) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertMechanismCourse(
                            type,
                            source,
                            mechanism_id,
                            master_id,
                            title,
                            start_time,
                            end_time,
                            offset,
                            service_type,
                            master_set_price_id,
                            identity_type,
                            classroom,
                            number_of_lessons,
                            study_card_ids,
                            status,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ArrangeSchedulingMechanismCourseContract.View view = getView();
                                    if (view!=null){
                                        view.onInsertSuccess(insertInfoResultModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeSchedulingMechanismCourseContract.View view = getView();
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
    public void queryMechanismClassroom(
            String mechanism_id,
            String start_time,
            String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismClassroom(
                            mechanism_id,
                            start_time,
                            status,
                            new NetworkSubscriber<MechanismClassroomSelectModel>(null) {
                                @Override
                                protected void onSuccess(MechanismClassroomSelectModel mechanismClassroomSelectModel) {
                                    ArrangeSchedulingMechanismCourseContract.View view = getView();
                                    if (view != null) {
                                        view.onClassRoomList(mechanismClassroomSelectModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ArrangeSchedulingMechanismCourseContract.View view = getView();
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
    public void queryMechanismCourseListById(String id) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryMechanismCourseListById(
                            id,
                            new NetworkSubscriber<CommodityOldModel>(null){
                                @Override
                                protected void onSuccess(CommodityOldModel commodityOldModel) {
                                    ArrangeSchedulingMechanismCourseContract.View view = getView();
                                    if (view!=null){
                                        CommodityOldEntity data = commodityOldModel.getData();
                                        if (data!=null){
                                            List<MasterSetPriceEntity> list = data.list;
                                            if (list!=null && list.size()>0){
                                                MasterSetPriceEntity masterSetPriceEntity = list.get(0);
                                                view.onMechanismCourse(masterSetPriceEntity);
                                            }
                                        }
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected ArrangeSchedulingMechanismCourseContract.Model createModule() {
        return new ArrangeSchedulingMechanismCourseModel();
    }

    @Override
    public void start() {

    }
}
