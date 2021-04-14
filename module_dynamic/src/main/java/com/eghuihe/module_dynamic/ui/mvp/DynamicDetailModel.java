package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;

import io.reactivex.observers.DisposableObserver;

public class DynamicDetailModel implements DynamicDetailContract.Model {

    @Override
    public DisposableObserver insertNoteComment(
            String user_id,
            Boolean is_reply,
            String reply_id,
            String parent_id,
            String note_id,
            String content,
            NetworkSubscriber networkSubscriber) {
        return UserServiceImpl.insertNoteComment(
                user_id,
                is_reply,
                reply_id,
                parent_id,
                note_id,
                content,
                networkSubscriber);
    }

    @Override
    public DisposableObserver insertHistory(
            String user_id,
            String history_id,
            String operation_type,
            String history_type,
            String destination,
            NetworkSubscriber networkSubscriber) {
        return UserServiceImpl.insertHistory(
                user_id,
                history_id,
                operation_type,
                history_type,
                destination,
                networkSubscriber);
    }

    @Override
    public DisposableObserver deleteNoteUser(String id, NetworkSubscriber networkSubscriber) {
        return UserServiceImpl.deleteNoteUser(id,
                networkSubscriber);
    }

    @Override
    public DisposableObserver getNewsInformationInfoListByNoteId(String id, NetworkSubscriber networkSubscriber) {
        return UserServiceImpl.queryNoteUserfindById(id, networkSubscriber);
    }

}
