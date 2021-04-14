package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.dynamic.NoteUserModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;
import com.huihe.base_lib.ui.holder.ViewHolder;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class DynamicContract {


    public interface Model extends IBaseModel {

        DisposableObserver queryNoteUserfindById(
                String id,
                NetworkSubscriber<NoteUserModel> networkSubscriber);

        DisposableObserver getNewsInformationInfoList(
                String user_id,
                String classfiy,
                Integer curPage,
                Integer pageSize,
                NetworkSubscriber networkSubscriber);

        DisposableObserver deleteNoteUser(
                String id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver insertHistory(
                String user_id,
                String history_id,
                String operation_type,
                String history_type,
                String destination,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver insertNoteComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

    }

    public interface View extends IStateView {

        /**
         * @param noteUserEntities
         * @desc 显示动态列表
         */
        void showNewsInformationInfoList(List<NoteUserEntity> noteUserEntities);

        void showNoteUserEntity(NoteUserEntity noteUserEntity, ViewHolder viewHolder);

        void onDeleteNoteSuccess(int position);


        void onInsertHistorySuccess(String operation_type, int position, ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity);

        void onInsertNoteCommentSuccess(ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity);

        int getCurrentPage();

        int getPageSize();
    }

    public interface Presenter {

        void getNewsInformationInfoList(String user_id, String classfiy);

        void deleteNoteUser(
                String id, int position);

        void insertHistory(
                String user_id,
                String history_id,
                String operation_type,
                String history_type,
                String destination, int position, ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity);

        void insertNoteComment(
                String user_id,
                Boolean is_reply,
                String reply_id,
                String parent_id,
                String note_id,
                String content, ViewHolder viewHolder, NoteUserEntity newsInformationInfoEntity);

        void queryNoteUserfindById(
                String id, ViewHolder viewHolder);
    }
}
