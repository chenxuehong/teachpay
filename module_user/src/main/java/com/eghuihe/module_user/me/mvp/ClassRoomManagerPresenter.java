package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ClassRoomEntity;
import com.huihe.base_lib.model.ClassRoomModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

import java.util.List;

public class ClassRoomManagerPresenter extends BasePresenter<ClassRoomManagerContract.Model, ClassRoomManagerContract.View>
        implements ClassRoomManagerContract.Presenter {
    @Override
    protected ClassRoomManagerContract.Model createModule() {
        return new ClassRoomManagerModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void mechanismClassroomInsert(String mechanism_id, String name) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().mechanismClassroomInsert(
                            mechanism_id,
                            name,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ClassRoomManagerContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ClassRoomManagerContract.View view = getView();
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
    public void mechanismClassroomUpdate(
            String id,
            String name,
            String status) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().mechanismClassroomUpdate(
                            id,
                            name,
                            status,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    ClassRoomManagerContract.View view = getView();
                                    if (view != null) {
                                        view.onUpdateSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ClassRoomManagerContract.View view = getView();
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
    public void queryManagerClassroomListPage(String mechanism_id, Integer pageSize, Integer currentPage) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryManagerClassroomListPage(
                            mechanism_id,
                            pageSize,
                            currentPage,
                            new NetworkSubscriber<ClassRoomModel>(null) {
                                @Override
                                protected void onSuccess(ClassRoomModel classRoomModel) {
                                    ClassRoomManagerContract.View view = getView();
                                    if (view != null) {
                                        ClassRoomModel.ClassRoomEntity2 data = classRoomModel.getData();
                                        if (data != null) {
                                            List<ClassRoomEntity> rows = data.getRows();
                                            view.onClassRoomList(rows);
                                        }
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    ClassRoomManagerContract.View view = getView();
                                    if (view != null) {
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }
}
