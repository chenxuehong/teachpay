package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.model.CommodityOldModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.MechanismClassroomSelectModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ArrangeSchedulingMechanismCourseContract {
    public interface Model extends IBaseModel {
        DisposableObserver insertMechanismCourse(
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
                String status,
                DisposableObserver<InsertInfoResultModel> subscriber);


        DisposableObserver queryMechanismClassroom(
                String mechanism_id,
                String start_time,
                String status,
                DisposableObserver<MechanismClassroomSelectModel> subscriber);
        DisposableObserver queryMechanismCourseListById(
                String id,
                NetworkSubscriber<CommodityOldModel> subscriber);
    }

    public interface View extends IStateView {

        void onInsertSuccess(String id);
        void onClassRoomList(List<ClassRoomEntity> classRoomEntities);
        void onMechanismCourse(MasterSetPriceEntity masterSetPriceEntity);
    }

    public interface Presenter {
        void insertMechanismCourse(
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
                String status
        );

        void queryMechanismClassroom(
                String mechanism_id,
                String start_time,
                String status);
        void queryMechanismCourseListById(
                String id);
    }
}
