package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CourseOnTimePayPresenter extends BasePresenter<CourseOnTimePayContract.Model,CourseOnTimePayContract.View>
        implements CourseOnTimePayContract.Presenter {
    @Override
    protected CourseOnTimePayContract.Model createModule() {
        return new CourseOnTimePayModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void payOntimeCourse(
            String user_id,
            String rcharge_type,
            String source,
            String course_num,
            String master_id,
            String member_duration,
            String rcharge_abstract,
            String studycard_id,
            String study_type,
            Boolean is_one_time_payment,
            String pay_type,
            String mechanism_id) {
        if (isViewAttached()){
            if ("ali".equals(pay_type)){
                getDisposableObservers().add(
                        getModule().payOntimeCourseAly(
                                user_id,
                                rcharge_type,
                                source,
                                course_num,
                                master_id,
                                member_duration,
                                rcharge_abstract,
                                studycard_id,
                                study_type,
                                is_one_time_payment,
                                pay_type,
                                mechanism_id,
                                new NetworkSubscriber<InsertInfoResultModel>(null){
                                    @Override
                                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                        CourseOnTimePayContract.View view = getView();
                                        if (view!=null){
                                            view.onAliOrder(insertInfoResultModel.getData());
                                        }
                                    }
                                }
                        )
                );
            }else {
                getDisposableObservers().add(
                        getModule().payOntimeCourseWX(
                                user_id,
                                rcharge_type,
                                source,
                                course_num,
                                master_id,
                                member_duration,
                                rcharge_abstract,
                                studycard_id,
                                study_type,
                                is_one_time_payment,
                                pay_type,
                                mechanism_id,
                                new NetworkSubscriber<WxPayModel>(null){
                                    @Override
                                    protected void onSuccess(WxPayModel wxPayModel) {
                                        CourseOnTimePayContract.View view = getView();
                                        if (view!=null){
                                            view.onWxOrder(wxPayModel.getData());
                                        }
                                    }
                                }
                        )

                );
            }
        }
    }
}
