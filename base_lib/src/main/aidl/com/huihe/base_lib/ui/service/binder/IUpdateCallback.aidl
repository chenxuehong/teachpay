// IUpdateCallback.aidl
package com.huihe.base_lib.ui.service.binder;
import com.huihe.base_lib.model.ApkVersion.AppInfo;
// Declare any non-default types here with import statements

interface IUpdateCallback {

   void onGetAppInfo(in AppInfo appInfo);
}