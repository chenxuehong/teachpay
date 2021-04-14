package com.huihe.base_lib.ui.activity.mvp;

import android.content.Context;

import com.huihe.base_lib.ui.IStateView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;

import io.reactivex.observers.DisposableObserver;

/**
 * Describe：Presenter基类
 * Created by 吴天强 on 2018/10/17.
 */

@SuppressWarnings("unchecked")
public abstract class BasePresenter<M extends IBaseModel, V extends IStateView> {

    private V mProxyView;
    private M module;
    private WeakReference<V> weakReference;
    private LinkedList<DisposableObserver> disposableObservers;

    public LinkedList<DisposableObserver> getDisposableObservers() {
        return disposableObservers;
    }

    /**
     * 绑定View
     */
    public void attachView(V view) {
        disposableObservers = new LinkedList<>();
        weakReference = new WeakReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(
                view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                new MvpViewHandler(weakReference.get()));
        if (this.module == null) {
            this.module = createModule();
        }
    }

    /**
     * 解绑View
     */
    public void detachView() {
        this.module = null;
        if (isViewAttached()) {
            weakReference.clear();
            weakReference = null;
            dispose();
        }
    }

    public void dispose() {
        if (disposableObservers!=null){
            for (int i = 0; i < disposableObservers.size(); i++) {
                DisposableObserver disposableObserver = disposableObservers.get(i);
                if (!disposableObserver.isDisposed()){
                    disposableObserver.dispose();
                }
            }
            disposableObservers.clear();
        }
    }

    /**
     * 是否与View建立连接
     */
    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    protected V getView() {
        return mProxyView;
    }

    protected M getModule() {
        return module;
    }

    protected Context getContext() {
        return getView().getContext();
    }

    protected void showLoading() {
        getView().showLoading();
    }

    protected void dismissLoading() {
        getView().closeLoading();
    }


    /**
     * 通过该方法创建Module
     */
    protected abstract M createModule();

    /**
     * 初始化方法
     */
    public abstract void start();


    /**
     * View代理类  防止 页面关闭P异步操作调用V 方法 空指针问题
     */
    private class MvpViewHandler implements InvocationHandler {

        private IStateView mvpView;

        MvpViewHandler(IStateView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isViewAttached()) {
                return method.invoke(mvpView, args);
            } //P层不需要关注V层的返回值
            return null;
        }
    }
}