package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.MechanismCategoryModel;

import io.reactivex.observers.DisposableObserver;

public class ResearchInstituteModel implements ResearchInstituteContract.Model {

    @Override
    public DisposableObserver queryMechanismCategoryChildList(
            NetworkSubscriber<MechanismCategoryModel> subscriber) {
        return UserServiceImpl.queryMechanismCategoryChildList(
                subscriber
        );
    }
}
