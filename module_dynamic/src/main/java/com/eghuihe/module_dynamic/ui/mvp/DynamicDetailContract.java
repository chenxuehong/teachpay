package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.GiftBean;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class DynamicDetailContract {

    public interface Model extends IBaseModel {

        DisposableObserver insertNoteComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content,
                NetworkSubscriber networkSubscriber);

        DisposableObserver insertHistory(
                String user_id,
                String history_id,
                String operation_type,
                String history_type,
                String destination,
                NetworkSubscriber networkSubscriber);


        DisposableObserver deleteNoteUser(
                String id,
                NetworkSubscriber networkSubscriber);


        DisposableObserver getNewsInformationInfoListByNoteId(
                String id,
                NetworkSubscriber networkSubscriber);
    }

    public interface View extends IStateView {


        void onInsertNoteCommentSuccess();

        void onInsertHistorySuccess(String operation_type);

        void onQueryHistorySuccess(List<LikeInfoModel.LikeInfoEntity> likeInfoEntities);

        void onDeleteNoteUserSuccess();

        void onNoteUser(NoteUserEntity noteUserEntity);
    }

    public interface Presenter {

        void insertNoteComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content);

        void insertHistory(
                String user_id,
                String history_id,
                String operation_type,
                String history_type,
                String destination);


        void deleteNoteUser(
                String id);

        void getNewsInformationInfoListByNoteId(
                String id);
    }
}
