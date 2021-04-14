package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.dynamic.NoteCommentDetailListModel;
import com.huihe.base_lib.model.personal.HistoryListModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class DynamicDetailLikeListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryHistoryListPage(
                Integer status,
                String history_id,
                String operation_type,
                String history_type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<HistoryListModel> subscriber);
    }

    public interface View extends IStateView {
        void onHistoryList(List<HistoryListModel.HistoryListEntity> historyListEntities);
    }

    public interface Presenter {
        void queryHistoryListPage(
                Integer status,
                String history_id,
                String operation_type,
                String history_type,
                Integer currentPage,
                Integer pageSize);
    }
}
