package com.eghuihe.module_order.ui.mvp;

import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.MechanismOrderModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class StudentOrderListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryStudentOrderList(
                String study_type,
                String user_id,
                Integer pageSize,
                Integer currentPage,
                String status,
                DisposableObserver<MechanismOrderModel> subscriber);

    }

    public interface View extends IStateView {
        void MechanismOrderList(List<MechanismOrderEntity> mechanismOrderEntities);
    }

    public interface Presenter {
        void queryStudentOrderList(
                String study_type,
                String user_id,
                Integer pageSize,
                Integer currentPage,
                String status);

    }
}
