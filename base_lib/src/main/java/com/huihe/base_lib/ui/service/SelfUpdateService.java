package com.huihe.base_lib.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.huihe.base_lib.ui.service.binder.UpdateServiceBinder;

public class SelfUpdateService extends Service{

    public static final int INSTALL_PACKAGES_REQUESTCODE = 101;

    UpdateServiceBinder updateBinder;

    @Override
    public void onCreate() {
        updateBinder = new UpdateServiceBinder();
        updateBinder.init(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return updateBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (updateBinder != null) {
            updateBinder.unDo();
            updateBinder = null;
        }
    }
}
