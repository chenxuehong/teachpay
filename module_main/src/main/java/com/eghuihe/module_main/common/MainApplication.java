package com.eghuihe.module_main.common;

import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;

public class MainApplication extends BaseApplication {
    @Override
    protected void initSdk() {
        EventBusUtils.register(this);
        LogUtils.e("initSdk", "initSdk");
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBusUtils.unRegister(this);
    }

    @Subscribe
    public void getEvent(Event event) {

        if (EventAction.LOGIN_SUCCESS.equals(event.getAction())){
//            ActivityToActivity.toActivity();
//            AppManager.getInstance().finishActivity("MainActivity");
            ActivityToActivity.toActivity(ARouterConfig.MAIN_MAINACTIVITY);
        }
    }
}
