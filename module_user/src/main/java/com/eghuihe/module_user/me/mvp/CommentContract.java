package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.model.personal.CommentEntity;
import com.huihe.base_lib.model.personal.MasterCommentModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class CommentContract {
    public interface Model extends IBaseModel {

        DisposableObserver getMasterCommentList(
                Integer currentPage,
                Integer pageSize,
                String appointment_id,
                String type,
                DisposableObserver<MasterCommentModel> subscriber);
    }

    public interface View extends IStateView {
        void showMasterCommentList(List<CommentEntity> commentEntities);
    }

    public interface Presenter {
        void getMasterCommentList(
                Integer currentPage,
                Integer pageSize,
                String appointment_id,
                String type);
    }
}
