package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.HistoryListModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class DynamicDetailLikeListPresenter extends BasePresenter<DynamicDetailLikeListContract.Model,DynamicDetailLikeListContract.View>
        implements DynamicDetailLikeListContract.Presenter {
    @Override
    public void queryHistoryListPage(
            Integer status,
            String history_id,
            String operation_type,
            String history_type,
            Integer currentPage,
            Integer pageSize) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().queryHistoryListPage(
                            status,
                            history_id,
                            operation_type,
                            history_type,
                            currentPage,
                            pageSize,
                            new NetworkSubscriber<HistoryListModel>(null){
                                @Override
                                protected void onSuccess(HistoryListModel historyListModel) {
                                    DynamicDetailLikeListContract.View view = getView();
                                    if (view!=null){
                                        view.onHistoryList(historyListModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicDetailLikeListContract.View view = getView();
                                    if (view!=null){
                                        view.closeLoading();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    protected DynamicDetailLikeListContract.Model createModule() {
        return new DynamicDetailLikeListModel();
    }

    @Override
    public void start() {

    }
}
