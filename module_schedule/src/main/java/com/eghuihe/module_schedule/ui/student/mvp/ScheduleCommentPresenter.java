package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class ScheduleCommentPresenter extends BasePresenter<ScheduleCommentContract.Model,ScheduleCommentContract.View>
        implements ScheduleCommentContract.Presenter {
    @Override
    protected ScheduleCommentContract.Model createModule() {
        return new ScheduleCommentModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void masterCommentInsert(
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
            String user_appointment_id) {
        if (isViewAttached()){
            getModule().masterCommentInsert(
                    mastersetprice_id,
                    appointment_id,
                    mechanism_id,
                    score,
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
                    new NetworkSubscriber<InsertInfoResultModel>(getView()){
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            ScheduleCommentContract.View view = getView();
                            if (view!=null){
                                view.onCommentSuccess();
                            }
                        }

                        @Override
                        public void onComplete() {
                            ScheduleCommentContract.View view = getView();
                            if (view!=null){
                                view.closeLoading();
                            }
                        }
                    }
            );
        }
    }
}
