package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.MasterCommentModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class CommentPresenter extends BasePresenter<CommentContract.Model, CommentContract.View>
        implements CommentContract.Presenter {
    @Override
    public void getMasterCommentList(
            Integer currentPage,
            Integer pageSize,
            String appointment_id,
            String type) {
        if (isViewAttached()) {
            getDisposableObservers().add(
                    getModule().getMasterCommentList(
                            currentPage,
                            pageSize,
                            appointment_id,
                            type,
                            new NetworkSubscriber<MasterCommentModel>(null) {
                                @Override
                                protected void onSuccess(MasterCommentModel masterCommentModel) {
                                    getView().showMasterCommentList(masterCommentModel.getData());
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    getView().closeLoading();
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected CommentContract.Model createModule() {
        return new CommentModel();
    }

    @Override
    public void start() {

    }
}
