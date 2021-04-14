package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class UpdateTeachPayMechanismCoursePresenter extends BasePresenter<UpdateTeachPayMechanismCourseContract.Model,UpdateTeachPayMechanismCourseContract.View>
        implements UpdateTeachPayMechanismCourseContract.Presenter {
    @Override
    public void updateMasterSetPrice(
            String id,
            String user_id,
            String appointment_type,
            String title,
            String label,
            String course_num,
            String amout,
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
            String default_discount_price,
            String categories
            ) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().updateMasterSetPrice(
                            id,
                            user_id,
                            appointment_type,
                            title,
                            label,
                            course_num,
                            amout,
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
                            default_discount_price,
                            categories,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    UpdateTeachPayMechanismCourseContract.View view = getView();
                                    if (view!=null){
                                        view.onUpdateSuccess();
                                    }
                                }
                            }
                    )
            );
        }
    }

    public void queryMechanismDetailInfoListById(String mechanism_id, String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryMechanismDetailInfoListById(
                            mechanism_id,
                            type,
                            new NetworkSubscriber<MasterMechanismModel>(getView()) {
                                @Override
                                protected void onSuccess(MasterMechanismModel masterMechanismModel) {
                                    UpdateTeachPayMechanismCourseContract.View view = getView();
                                    if (view != null) {
                                        List<MasterMechanismModel.MasterMechanismEntity> data = masterMechanismModel.getData();
                                        if (data != null && data.size() > 0) {
                                            MasterMechanismModel.MasterMechanismEntity masterMechanismEntity = data.get(0);
                                            view.onMechanismInfo(masterMechanismEntity);
                                        }
                                    }

                                }

                                @Override
                                public void onComplete() {
                                    UpdateTeachPayMechanismCourseContract.View view = getView();
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
    protected UpdateTeachPayMechanismCourseContract.Model createModule() {
        return new UpdateTeachPayMechanismCourseModel();
    }

    @Override
    public void start() {

    }
}
