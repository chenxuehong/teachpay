package com.huihe.base_lib.ui.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.ui.service.binder.IUpdateCallback;
import com.huihe.base_lib.ui.service.binder.IUpdateServiceAidl;
import com.huihe.base_lib.utils.LogUtils;

@Route(path = ARouterConfig.SERVICE.SELF_UPDATE_SERVICE, name = "selfupdate service")
public class SelfUpdateProvideImpl implements SelfUpdateProvider, ServiceConnection {

    private static final String TAG = SelfUpdateProvideImpl.class.getSimpleName();
    private IUpdateServiceAidl iUpdateServiceAidl;
    private Intent intent;
    private SelfUpdateProvideImpl.OnServiceInitializeListener listener;
    private Context context;

    @Override
    public void init(Context context) {
        init(context,null);
    }

    @Override
    public void startSelfUpdateService(Context context) {
        this.context = context.getApplicationContext();
        startSelfUpdateService(context, false, false);
    }

    @Override
    public void startSelfUpdateService(final Context context,final boolean appJustStarted,final boolean isFromAbout) {
        this.context = context.getApplicationContext();
        if (isServiceConnected()) {
            startSelfUpdate(appJustStarted,isFromAbout);
        } else {
            init(context,new SelfUpdateProvideImpl.OnServiceInitializeListener(){
                @Override
                public void onInitialized() {
                    startSelfUpdate(appJustStarted, isFromAbout);
                }
            });
        }
    }

    public void startSelfUpdate(boolean appJustStarted, boolean isFromAbout) {
        if (iUpdateServiceAidl!=null) {
            try {
                iUpdateServiceAidl.startSelfUpdate(appJustStarted,isFromAbout);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(Context context,SelfUpdateProvideImpl.OnServiceInitializeListener listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
        if (!isServiceConnected()) {
            intent = new Intent(context, SelfUpdateService.class);
            context.bindService(intent, this, Context.BIND_AUTO_CREATE);
        } else {
            if (listener!=null) {
                this.listener.onInitialized();
            }
        }
    }

    @Override
    public void getAppInfo(IUpdateCallback callback){
        if (isServiceConnected()) {
            try {
                iUpdateServiceAidl.getAppVersionInfo(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            init(context, () -> {
                try {
                    iUpdateServiceAidl.getAppVersionInfo(callback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void startApkInstallSetting(final int requestCode,final int[] grantResults) {
        if (isServiceConnected()) {
            try {
                iUpdateServiceAidl.startApkInstallSetting(requestCode,grantResults);
            } catch (RemoteException e) {
                e.printStackTrace();
                LogUtils.d(TAG,"iUpdateServiceAidl invoke startApkInstallSetting is fail: " + e.toString());
            }
        } else {
            init(context, () -> {
                try {
                    iUpdateServiceAidl.startApkInstallSetting(requestCode,grantResults);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    LogUtils.d(TAG,"iUpdateServiceAidl invoke startApkInstallSetting is fail: " + e.toString());
                }
            });
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogUtils.d(TAG,"SelfUpdateService onServiceConnected");
        iUpdateServiceAidl = IUpdateServiceAidl.Stub.asInterface(service);
        if (listener!=null) {
            listener.onInitialized();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG, "SelfUpdateService onServiceDisconnected");
    }

    public boolean isServiceConnected() {
        if (iUpdateServiceAidl == null) {
            Log.e(TAG, "selfUpdateService = null");
            return false;
        }
        IBinder binder = iUpdateServiceAidl.asBinder();
        if (binder == null){
            Log.e(TAG, "selfUpdateService.getBinder() = null");
            return false;
        }
        if (!binder.isBinderAlive()) {
            Log.e(TAG, "selfUpdateService.getBinder().isBinderAlive() = false");
            return false;
        }
        if (!binder.pingBinder()) {
            Log.e(TAG, "selfUpdateService.getBinder().pingBinder() = false");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        try {
            if (context != null) {
                context.unbindService(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        iUpdateServiceAidl = null;
        listener = null;
    }

    public interface OnServiceInitializeListener {
        void onInitialized();
    }
}
