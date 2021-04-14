package com.eghuihe.teachpay.push;

import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.mob.pushsdk.MobPush;
import com.mob.pushsdk.MobPushCallback;
import com.tencent.qcloud.tim.uikit.utils.ThirdPushTokenMgr;

public class MyMobPushCallback implements MobPushCallback<String> {
    private static final String TAG = MyMobPushCallback.class.getSimpleName();

    @Override
    public void onCallback(String rid) {
        LogUtils.i(TAG,"RegistrationId: " + rid);
        LoginHelper.setClientid(rid);
        MobPush.getDeviceToken(new MobPushCallback<String>() {
            @Override
            public void onCallback(String token) {
                ThirdPushTokenMgr.getInstance().setThirdPushToken(token);
            }
        });
    }
}
