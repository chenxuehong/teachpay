package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class InsertMechanismCourseStep2Presenter extends BasePresenter<InsertMechanismCourseStep2Contract.Model,InsertMechanismCourseStep2Contract.View>
        implements InsertMechanismCourseStep2Contract.Presenter {
    @Override
    public void insertMasterSetPrice(
            String user_id,
            String title,
            String appointment_type,
            String label,
            String course_num,
            String amout,
            String discount_amout,
            String introduction_content,
            Integer status,
            String titile_url,
            Boolean first_free,
            String type,
            String connect_peoplenum,
            String mechanism_id,
            String face_url,
            String introduction_url,
            String duration,
            String price_list,
            Boolean is_attend_activities,
            String start_time,
            String end_time,
            String activity_price,
            String categories) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertMasterSetPrice(
                            user_id,
                            title,
                            appointment_type,
                            label,
                            course_num,
                            amout,
                            discount_amout,
                            introduction_content,
                            status,
                            titile_url,
                            first_free,
                            type,
                            connect_peoplenum,
                            mechanism_id,
                            face_url,
                            introduction_url,
                            duration,
                            price_list,
                            is_attend_activities,
                            start_time,
                            end_time,
                            activity_price,
                            categories,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    InsertMechanismCourseStep2Contract.View view = getView();
                                    if (view!=null){
                                        view.onMechanismCourseSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    InsertMechanismCourseStep2Contract.View view = getView();
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
    protected InsertMechanismCourseStep2Contract.Model createModule() {
        return new InsertMechanismCourseStep2Model();
    }

    @Override
    public void start() {

    }
}
