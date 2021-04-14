package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LongOrderPayInfoModel;
import com.huihe.base_lib.model.StudentScheduleModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;

import io.reactivex.observers.DisposableObserver;

public class TeachPayStudentSchedulePackageDetailListModel implements TeachPayStudentSchedulePackageDetailListContract.Model {
    @Override
    public DisposableObserver queryStudentOfflineSchedule(
            String study_card_id,
            Integer currentPage,
            Integer pageSize,
            DisposableObserver<StudentScheduleModel> subscriber) {
        return UserServiceImpl.queryStudentOfflineSchedulePackageDetail(
                study_card_id,
                currentPage,
                pageSize,
                subscriber
        );
    }
    @Override
    public DisposableObserver queryLongOrderPayInfo(
            String id,
            DisposableObserver<LongOrderPayInfoModel> subscriber) {
        return UserServiceImpl.queryLongOrderPayInfo(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver getLongOrder(
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
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.getLongOrder(
                user_id,
                rcharge_type,
                source,
                course_num,
                master_id,
                rcharge_abstract,
                studycard_id,
                study_type,
                is_one_time_payment,
                user_study_card_id,
                paying_for_identity,
                pay_type,
                mechanism_id,
                subscriber
        );
    }

    @Override
    public DisposableObserver getWxLongOrder(
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
            DisposableObserver<WxPayModel> subscriber) {
        return UserServiceImpl.getWxLongOrder(
                user_id,
                rcharge_type,
                source,
                course_num,
                master_id,
                rcharge_abstract,
                studycard_id,
                study_type,
                is_one_time_payment,
                user_study_card_id,
                paying_for_identity,
                pay_type,
                mechanism_id,
                subscriber
        );
    }
}
