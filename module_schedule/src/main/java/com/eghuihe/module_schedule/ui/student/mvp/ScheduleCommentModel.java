package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class ScheduleCommentModel implements ScheduleCommentContract.Model {
    @Override
    public DisposableObserver masterCommentInsert(
            String mastersetprice_id,
            String appointment_id,
            String mechanism_id,
            String score,
            String content,
            String user_id,
            String type,
            String photo_url,
            String course_quality,
            String environment,
            String faculty,
            String cost_effectiveness,
            String attitude,
            String average_score,
            String master_id,
            String user_appointment_id,
            DisposableObserver<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertTeachPayMechanismComment(
                mastersetprice_id,
                appointment_id,
                mechanism_id,
                content,
                user_id,
                type,
                photo_url,
                course_quality,
                environment,
                faculty,
                cost_effectiveness,
                attitude,
                average_score,
                master_id,
                user_appointment_id,
                subscriber
        );
    }
}
