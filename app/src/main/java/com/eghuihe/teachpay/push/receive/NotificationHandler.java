package com.eghuihe.teachpay.push.receive;

import android.content.Context;

import com.huihe.base_lib.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationHandler {
    private static final String TAG = NotificationHandler.class.getSimpleName();

    public static void handleGuiNotificationClickListener(Context context, String data) {
        try {
            JSONObject jsonObject = JsonUtil.string2JSONObject(data);
            String type = jsonObject.getString("opera_type");
            if ("curriculum_appointment".equals(type)){

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
