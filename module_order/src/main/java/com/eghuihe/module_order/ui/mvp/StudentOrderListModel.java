package com.eghuihe.module_order.ui.mvp;

import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismOrderModel;

import io.reactivex.observers.DisposableObserver;

public class StudentOrderListModel implements StudentOrderListContract.Model {
    @Override
    public DisposableObserver queryStudentOrderList(
            String study_type,
            String user_id,
            Integer pageSize,
            Integer currentPage,
            String status,
            DisposableObserver<MechanismOrderModel> subscriber) {
        return UserServiceImpl.queryStudentOrderList(
                study_type,
                user_id,
                pageSize,
                currentPage,
                status,
                subscriber
        );
    }
}
