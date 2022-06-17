package com.huihe.base_lib.ui.service.binder;

import com.huihe.base_lib.model.ApkVersion.AppInfo;

public interface UpdateCallback {
    void onGetAppInfo(AppInfo appInfo);
}
