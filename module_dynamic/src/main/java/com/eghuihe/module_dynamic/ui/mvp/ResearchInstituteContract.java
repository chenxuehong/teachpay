package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.MechanismCategoryEntity;
import com.huihe.base_lib.model.MechanismCategoryModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ResearchInstituteContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismCategoryChildList(
                NetworkSubscriber<MechanismCategoryModel> subscriber);
    }

    public interface View extends IStateView {
        void onMechanismCategoryList(List<MechanismCategoryEntity> mechanismCategoryEntities);
    }

    public interface Presenter {
        void queryMechanismCategoryChildList();
    }
}
