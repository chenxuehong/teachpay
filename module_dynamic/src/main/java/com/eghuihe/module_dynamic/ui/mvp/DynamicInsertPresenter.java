package com.eghuihe.module_dynamic.ui.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class DynamicInsertPresenter extends BasePresenter<DynamicInsertContract.Model,DynamicInsertContract.View>
        implements DynamicInsertContract.Presenter {

    @Override
    public void noteUserInsert(
            String content,
            String user_id,
            Integer status,
            String picts,
            Integer style,
            String classfiy,
            String master_set_price_id) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().noteUserInsert(
                            content,
                            user_id,
                            status,
                            picts,
                            style,
                            classfiy,
                            master_set_price_id,
                            new NetworkSubscriber<InsertInfoResultModel>(null){
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    DynamicInsertContract.View view = getView();
                                    if (view!=null){
                                        view.onInsertSuccess();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    DynamicInsertContract.View view = getView();
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
    protected DynamicInsertContract.Model createModule() {
        return new DynamicInsertModel();
    }

    @Override
    public void start() {

    }
}
