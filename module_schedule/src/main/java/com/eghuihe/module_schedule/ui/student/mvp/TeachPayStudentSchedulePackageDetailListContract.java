package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.model.LongOrderPayInfoEntity;
import com.huihe.base_lib.model.LongOrderPayInfoModel;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayStudentSchedulePackageDetailListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryStudentOfflineSchedule(
                String study_card_id,
                Integer currentPage,
                Integer pageSize,
                DisposableObserver<StudentScheduleModel> subscriber);
        DisposableObserver queryLongOrderPayInfo(
                String id,
                DisposableObserver<LongOrderPayInfoModel> subscriber);

        DisposableObserver getLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id,
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver getWxLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id,
                DisposableObserver<WxPayModel> subscriber);
    }

    public interface View extends IStateView {

        void onStudentScheduleEntityList(List<StudentScheduleModel.StudentScheduleEntity> studentScheduleEntities);
        void onGetLongOrderInfoDetail(LongOrderPayInfoEntity longOrderPayInfoEntity);
        void onGetAliOrder(String orderInfo);
        void onGetWxOrder(WxPayModel.WxPayEntity wxPayEntity);
    }

    public interface Presenter {
        void queryStudentOfflineSchedule(
                String study_card_id,
                Integer currentPage,
                Integer pageSize);
        void queryLongOrderPayInfo(
                String id);
        void getLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id);

        void getWxLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id);
    }
}
