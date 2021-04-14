package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.MasterCommentModel;

import io.reactivex.observers.DisposableObserver;

public class CommentModel implements CommentContract.Model {
    @Override
    public DisposableObserver getMasterCommentList(
            Integer currentPage,
            Integer pageSize,
            String appointment_id,
            String type,
            DisposableObserver<MasterCommentModel> subscriber) {
        return UserServiceImpl.getMasterCommentList(
                currentPage,
                pageSize,
                appointment_id,
                type,
                subscriber
        );
    }
}
