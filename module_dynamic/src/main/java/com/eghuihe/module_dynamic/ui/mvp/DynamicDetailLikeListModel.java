package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.HistoryListModel;

import io.reactivex.observers.DisposableObserver;

public class DynamicDetailLikeListModel implements DynamicDetailLikeListContract.Model {
    @Override
    public DisposableObserver queryHistoryListPage(
            Integer status,
            String history_id,
            String operation_type,
            String history_type,
            Integer currentPage,
            Integer pageSize,
            NetworkSubscriber<HistoryListModel> subscriber) {
        return UserServiceImpl.queryHistoryListPage(
                status,
                history_id,
                operation_type,
                history_type,
                currentPage,
                pageSize,
                subscriber
        );
    }
}
