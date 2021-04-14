package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class DynamicDetailCommenPresenter extends BasePresenter<DynamicDetailCommentContract.Model, DynamicDetailCommentContract.View>
        implements DynamicDetailCommentContract.Presenter {
    @Override
    public void queryNoteCommentDetailsListPage(String note_id, String parent_id, Integer currentPage, Integer pageSize) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().queryNoteCommentDetailsListPage(
                            note_id,
                            parent_id,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<NoteCommentDetailListModel>(null) {
                                @Override
                                protected void onSuccess(NoteCommentDetailListModel noteCommentDetailListModel) {
                                    DynamicDetailCommentContract.View view = getView();
                                    if (view != null) {
                                        view.onNoteCommentList(noteCommentDetailListModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicDetailCommentContract.View view = getView();
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
    public void insertNoteComment(
            String user_id,
            Boolean is_reply,
            String reply_id,
            String parent_id,
            String note_id,
            String content) {
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
                                    DynamicDetailCommentContract.View view = getView();
                                    if (view != null) {
                                        view.onCommentInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicDetailCommentContract.View view = getView();
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
    public void deleteNoteComment(String id) {

        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().deleteNoteComment(
                            id,
                            new NetworkSubscriber<InsertInfoResultModel>(getView()) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    DynamicDetailCommentContract.View view = getView();
                                    if (view != null) {
                                        view.onCommentDeleteSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicDetailCommentContract.View view = getView();
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
    public void insertHistory(
            String user_id,
            String history_id,
            String operation_type,
            String history_type,
            String destination) {

        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().insertHistory(
                            user_id,
                            history_id,
                            operation_type,
                            history_type,
                            destination,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    DynamicDetailCommentContract.View view = getView();
                                    if (view!=null){
                                        view.onLikeSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicDetailCommentContract.View view = getView();
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
    protected DynamicDetailCommentContract.Model createModule() {
        return new DynamicDetailCommentModel();
    }

    @Override
    public void start() {

    }
}
