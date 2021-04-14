package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;

import io.reactivex.observers.DisposableObserver;

public class DynamicDetailCommentModel implements DynamicDetailCommentContract.Model {
    @Override
    public DisposableObserver queryNoteCommentDetailsListPage(
            String note_id,
            String parent_id,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<NoteCommentDetailListModel> subscriber) {
        return UserServiceImpl.queryNoteCommentDetailsListPage(
                note_id,
                parent_id,
                currentPage,
                pageSize,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertNoteComment(
            String user_id,
            Boolean is_reply,
            String reply_id,
            String parent_id,
            String note_id,
            String content,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertNoteComment(
                user_id,
                is_reply,
                reply_id,
                parent_id,
                note_id,
                content,
                subscriber
        );
    }

    @Override
    public DisposableObserver deleteNoteComment(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.deleteNoteComment(
                id,
                subscriber
        );
    }

    @Override
    public DisposableObserver insertHistory(
            String user_id,
            String history_id,
            String operation_type,
            String history_type,
            String destination,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.insertHistory(
                user_id,
                history_id,
                operation_type,
                history_type,
                destination,
                subscriber
        );
    }
}
