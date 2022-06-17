// IUpdateServiceAidl.aidl
package com.huihe.base_lib.ui.service.binder;
import com.huihe.base_lib.ui.service.binder.IUpdateCallback;
import com.huihe.base_lib.model.ApkVersion.AppInfo;
import android.content.Context;
// Declare any non-default types here with import statements

interface IUpdateServiceAidl {
   void startSelfUpdate(boolean appJustStarted,boolean isFromAbout);
   void getAppVersionInfo(in IUpdateCallback callback);
   void startApkInstallSetting(int requestCode,in int[] grantResults);
}