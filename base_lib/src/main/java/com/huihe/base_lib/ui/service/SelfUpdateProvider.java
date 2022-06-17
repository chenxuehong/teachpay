package com.huihe.base_lib.ui.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.huihe.base_lib.ui.service.binder.IUpdateCallback;


public interface SelfUpdateProvider extends IProvider {

    SelfUpdateProvider intance = new SelfUpdateProvideImpl();
    void init(Context context, SelfUpdateProvideImpl.OnServiceInitializeListener listener);
    /**
     * 启动自升级服务
     *
     * @param context
     */
    void startSelfUpdateService(Context context);

    /**
     * 启动自升级服务
     * @param context
     * @param appJustStarted 是否从首页启动该服务，用于校验版本更新,
     * @param isFromAbout 是否从关于页面启动该服务，用于校验版本更新
     */
    void startSelfUpdateService(Context context,boolean appJustStarted,boolean isFromAbout);

    void getAppInfo(IUpdateCallback callback);
    void startApkInstallSetting(int requestCode, int[] grantResults);

    void onDestroy();
}
