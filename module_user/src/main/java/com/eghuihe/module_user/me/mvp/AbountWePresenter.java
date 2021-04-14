package com.eghuihe.module_user.me.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.ui.activity.mvp.BasePresenter;

public class AbountWePresenter extends BasePresenter<AbountWeContract.Model, AbountWeContract.View>
        implements AbountWeContract.Presenter {

    @Override
    public void versionIteration(
            String version,
            String platform) {
        if (isViewAttached()){
            getDisposableObservers().add(
                    getModule().versionIteration(
                            version,
                            platform,
                            new NetworkSubscriber<VersionIterationModel>(null){
                                @Override
                                protected void onSuccess(VersionIterationModel versionIterationModel) {
                                    AbountWeContract.View view = getView();
                                    if (view!=null){
                                        view.onVersionIteration(versionIterationModel.getData());
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    super.onComplete();
                                    AbountWeContract.View view = getView();
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
    protected AbountWeContract.Model createModule() {
        return new AbountWeModel();
    }

    @Override
    public void start() {

    }
}
