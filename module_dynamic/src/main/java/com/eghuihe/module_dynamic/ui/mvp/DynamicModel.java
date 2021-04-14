package com.eghuihe.module_dynamic.ui.mvp;

import android.text.TextUtils;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.dynamic.NoteUserModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.LikeInfoModel;

import io.reactivex.observers.DisposableObserver;

public class DynamicModel implements DynamicContract.Model {
    @Override
    public DisposableObserver queryNoteUserfindById(
            String id,
            NetworkSubscriber<NoteUserModel> subscriber) {
        return UserServiceImpl.queryNoteUserfindById(
                id,
                subscriber
        );
    }


    @Override
    public DisposableObserver getNewsInformationInfoList(
            String user_id,
            String classfiy,
            Integer curPage,
            Integer pageSize,
            NetworkSubscriber networkSubscriber) {
        return UserServiceImpl.getNewsInformationInfoList(
                curPage,
                pageSize,
                TextUtils.isEmpty(user_id) ? null : user_id,
                classfiy,
                "1",
                networkSubscriber);
    }

    @Override
    public DisposableObserver deleteNoteUser(
            String id,
            NetworkSubscriber<InsertInfoResultModel> subscriber) {
        return UserServiceImpl.deleteNoteUser(
                id,
                subscriber);
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
                subscriber);
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

}
