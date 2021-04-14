package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import io.reactivex.observers.DisposableObserver;

public class ScheduleCommentContract {
    public interface Model extends IBaseModel {

        DisposableObserver masterCommentInsert(
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
                DisposableObserver<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onCommentSuccess();
    }

    public interface Presenter {
        void masterCommentInsert(
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
                String user_appointment_id);
    }
}
