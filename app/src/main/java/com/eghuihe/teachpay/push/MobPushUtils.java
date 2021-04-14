package com.eghuihe.teachpay.push;

import android.util.Log;

import com.huihe.base_lib.utils.manager.LoginHelper;
import com.mob.MobSDK;
import com.mob.OperationCallback;

import static org.greenrobot.eventbus.EventBus.TAG;

public class MobPushUtils {
    public static void submitPolicyGrant(boolean granted) {
        LoginHelper.saveBoolean(LoginHelper.KEY_IS_POLICYGRANT, granted);
        MobSDK.submitPolicyGrantResult(granted, new OperationCallback<Void>() {
            @Override
            public void onComplete(Void data) {
                Log.d(TAG, "隐私协议授权结果提交：成功");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "隐私协议授权结果提交：失败");
            }
        });
    }
}
