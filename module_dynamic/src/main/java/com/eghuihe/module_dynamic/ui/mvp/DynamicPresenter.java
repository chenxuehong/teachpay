package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.dynamic.NoteUserModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

public class DynamicPresenter extends BasePresenter<DynamicContract.Model, DynamicContract.View>
        implements DynamicContract.Presenter {

    @Override
    public void getNewsInformationInfoList(String user_id, String classfiy) {

        if (isViewAttached()) {
            getDisposableObservers().add(getModule().getNewsInformationInfoList(
                    user_id,
                    classfiy,
                    getView().getCurrentPage(),
                    getView().getPageSize(),
                    new NetworkSubscriber<NoteUserModel>(null) {
                        @Override
                        protected void onSuccess(NoteUserModel noteUserModel) {
                            List<NoteUserEntity> noteUserEntities = noteUserModel.getData();
                            DynamicContract.View view = getView();
                            if (view != null) {
                                view.showNewsInformationInfoList(noteUserEntities);
                            }
                        }

                        @Override
                        public void onComplete() {
                            DynamicContract.View view = getView();
                            if (view != null) {
                                view.closeLoading();
                            }
                        }
                    }));
        }
    }

    @Override
    public void deleteNoteUser(String id, final int position) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().deleteNoteUser(
                    id,
                    new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            DynamicContract.View view = getView();
                            if (view != null) {
                                view.onDeleteNoteSuccess(position);
                            }
                        }

                        @Override
                        public void onComplete() {
                            DynamicContract.View view = getView();
                            if (view != null) {
                                view.closeLoading();
                            }
                        }
                    }));
        }
    }

    @Override
    public void insertHistory(
            String user_id,
            String history_id,
            final String operation_type,
            String history_type,
            String destination,
            final int position,
            final ViewHolder viewHolder,
            NoteUserEntity newsInformationInfoEntity) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertHistory(
                            user_id,
                            history_id,
                            operation_type,
                            history_type,
                            destination,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    DynamicContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertHistorySuccess(
                                                operation_type,
                                                position,
                                                viewHolder,
                                                newsInformationInfoEntity);
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void insertNoteComment(
            String user_id,
            Boolean is_reply,
            String reply_id,
            String parent_id,
            String note_id,
            String content,
            final ViewHolder viewHolder,
            final NoteUserEntity newsInformationInfoEntity) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().insertNoteComment(
                            user_id,
                            is_reply,
                            reply_id,
                            parent_id,
                            note_id,
                            content,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    DynamicContract.View view = getView();
                                    if (view != null) {
                                        view.onInsertNoteCommentSuccess(viewHolder, newsInformationInfoEntity);
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicContract.View view = getView();
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
    public void queryNoteUserfindById(String id, final ViewHolder viewHolder) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryNoteUserfindById(
                            id,
                            new NetworkSubscriber<NoteUserModel>(null) {
                                @Override
                                protected void onSuccess(NoteUserModel noteUserSingleModel) {
                                    DynamicContract.View view = getView();
                                    if (view != null) {
                                        List<NoteUserEntity> noteUserEntities = noteUserSingleModel.getData();
                                        if (noteUserEntities != null && noteUserEntities.size() > 0) {
                                            view.showNoteUserEntity(noteUserEntities.get(0), viewHolder);
                                        }
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicContract.View view = getView();
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
    protected DynamicContract.Model createModule() {
        return new DynamicModel();
    }

    @Override
    public void start() {

    }

}
