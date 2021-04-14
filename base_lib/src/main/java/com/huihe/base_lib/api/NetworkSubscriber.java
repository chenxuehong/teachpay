package com.huihe.base_lib.api;

import android.util.Log;

import com.google.gson.Gson;
import com.huihe.base_lib.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.BaseRespModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.event.MessageCodeEvent;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.NetWorkUtils;
import com.huihe.base_lib.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

public class NetworkSubscriber<T> extends DisposableObserver<T> {
    public static final String TAG = NetworkSubscriber.class.getSimpleName();
    IStateView iStateView;

    public NetworkSubscriber(IStateView iStateView) {
        this.iStateView = iStateView;
        if (!NetWorkUtils.isNetConnected(BaseApplication.getInstance())) {
            ToastUtils.showShortToast(BaseApplication.getInstance().getApplicationContext(),
                   "请检查网络");
            onComplete();
        }
    }

    @Override
    protected void onStart() {
        if (iStateView != null)
            iStateView.showLoading();
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showShortToast(BaseApplication.getInstance().getApplicationContext(),
                BaseApplication.getInstance().getResources().getString(R.string.Network_Error));
        onComplete();
    }

    @Override
    public void onComplete() {
        if (iStateView != null)
            iStateView.closeLoading();
    }

    @Override
    public void onNext(T t) {

        try {
            String json = new Gson().toJson(t);
            LogUtils.i(TAG, "onNext:" + json);
            BaseRespModel result = (BaseRespModel) t;

            if (result.isSuccess()) {
                onSuccess(t);
            } else {
                if (result.getCode() == 401) {

                    Observable.timer(500, TimeUnit.MICROSECONDS)
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) {
                                    EventBusUtils.sendEvent(new Event(EventAction.LOGOUT_EVENT));
                                }
                            });
                }
                handlerRequestFail(result);
                onFail(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlerRequestFail(BaseRespModel result) {
        try {
            ToastUtils.showShortToast(BaseApplication.getInstance(), result.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        EventBus.getDefault().post(new MessageCodeEvent(result.getCode()));
    }

    protected void onSuccess(T t) {

    }

    protected void onFail(Integer code, String message) {

    }
}