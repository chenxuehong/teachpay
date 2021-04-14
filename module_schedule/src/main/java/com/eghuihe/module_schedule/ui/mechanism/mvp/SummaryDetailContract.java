package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.model.SummaryInfoEntity;
import com.huihe.base_lib.model.SummaryInfoModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class SummaryDetailContract {

    public interface Model extends IBaseModel {
        DisposableObserver querySummaryList(
                String appointment_id,
                DisposableObserver<SummaryInfoModel> subscriber);
    }

    public interface View extends IStateView {

        void onSummaryList(List<SummaryInfoEntity> summaryInfoEntities);
    }

    public interface Presenter {
        void querySummaryList(
                String appointment_id);
    }
}
