package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class DynamicDetailCommentContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryNoteCommentDetailsListPage(
                String note_id,
                String parent_id,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<NoteCommentDetailListModel> subscriber);

        DisposableObserver insertNoteComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver deleteNoteComment(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
        DisposableObserver insertHistory(
                String user_id,
                String history_id,
                String operation_type,
                String history_type,
                String destination,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void onNoteCommentList(List<NoteCommentDetailListModel.NoteCommentEntity> noteCommentEntities);

        void onCommentInsertSuccess();
        void onCommentDeleteSuccess();
        void onLikeSuccess();
    }

    public interface Presenter {
        void queryNoteCommentDetailsListPage(
                String note_id,
                String parent_id,
                Integer currentPage,
                Integer pageSize);

        void insertNoteComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content);

        void deleteNoteComment(
                String id);
        void insertHistory(
                String user_id,
                String history_id,
                String operation_type,
                String history_type,
                String destination);
    }
}
