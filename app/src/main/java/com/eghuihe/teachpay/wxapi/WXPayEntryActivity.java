package com.eghuihe.teachpay.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.eghuihe.teachpay.AppApplication;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = WXPayEntryActivity.class.getSimpleName();
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = AppApplication.getWXapi();
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK://支付成功
//                    Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                    EventBusUtils.sendEvent(new Event(EventAction.PAY_SUCCESS));
                    break;
                case BaseResp.ErrCode.ERR_COMM://错误，可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    Toast.makeText(this, "支付错误", Toast.LENGTH_SHORT).show();
                    EventBusUtils.sendEvent(new Event(EventAction.PAY_FAIL));
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消，无需处理。发生场景：用户不支付了，点击取消，返回APP。
                    Toast.makeText(this, "用户取消", Toast.LENGTH_SHORT).show();
                    EventBusUtils.sendEvent(new Event(EventAction.PAY_FAIL));
                    break;
            }
        }
        finish();
    }
}