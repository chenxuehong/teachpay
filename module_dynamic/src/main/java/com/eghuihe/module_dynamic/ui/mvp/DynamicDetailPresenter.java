package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.model.dynamic.NoteUserModel;
import com.huihe.base_lib.model.im.GiftModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.LikeInfoModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.List;

public class DynamicDetailPresenter extends BasePresenter<DynamicDetailContract.Model, DynamicDetailContract.View> implements DynamicDetailContract.Presenter {


    @Override
    public void insertNoteComment(String user_id, Boolean is_reply, String reply_id, String parent_id, String note_id, String content) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().insertNoteComment(user_id, is_reply, reply_id, parent_id, note_id, content, new NetworkSubscriber<InsertInfoResultModel>(null) {
                @Override
                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                    getView().onInsertNoteCommentSuccess();
                }
            }));
        }
    }

    @Override
    public void insertHistory(String user_id, String history_id, String operation_type, String history_type, String destination) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().insertHistory(user_id, history_id, operation_type, history_type, destination, new NetworkSubscriber<InsertInfoResultModel>(null) {
                @Override
                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                    getView().onInsertHistorySuccess(operation_type);
                }
            }));
        }
    }

    @Override
    public void deleteNoteUser(String id) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().deleteNoteUser(id, new NetworkSubscriber<InsertInfoResultModel>(null) {
                @Override
                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                    getView().onDeleteNoteUserSuccess();
                }
            }));
        }
    }

    @Override
    public void getNewsInformationInfoListByNoteId(String id) {
        if (isViewAttached()) {
            getDisposableObservers().add(getModule().getNewsInformationInfoListByNoteId(id, new NetworkSubscriber<NoteUserModel>(getView()) {
                @Override
                protected void onSuccess(NoteUserModel noteUserModel) {
                    List<NoteUserEntity> noteUserEntities = noteUserModel.getData();
                    if (noteUserEntities != null && noteUserEntities.size() > 0) {
                        NoteUserEntity noteUserEntity = noteUserEntities.get(0);
                        getView().onNoteUser(noteUserEntity);
                    }
                }

                @Override
                public void onComplete() {
                    DynamicDetailContract.View view = getView();
                    if (view!=null){
                        view.closeLoading();
                    }
                }
            }));
        }
    }

    @Override
    protected DynamicDetailContract.Model createModule() {
        return new DynamicDetailModel();
    }

    @Override
    public void start() {

    }
}
