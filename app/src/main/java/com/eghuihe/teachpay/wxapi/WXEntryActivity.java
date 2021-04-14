package com.eghuihe.teachpay.wxapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.eghuihe.teachpay.AppApplication;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import cn.sharesdk.wechat.utils.WechatHandlerActivity;


public class WXEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler {

    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    private static final String TAG = WXEntryActivity.class.getSimpleName();
    private IWXAPI wXapi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wXapi = AppApplication.getWXapi();
        boolean handleIntent = wXapi.handleIntent(getIntent(), this);

        //下面代码是判断微信分享后返回WXEnteryActivity的，如果handleIntent==false,说明没有调用IWXAPIEventHandler，则需要在这里销毁这个透明的Activity;
        if (!handleIntent) {
            finish();
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtils.e(TAG, "onReq: " + baseReq.toString());
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        wXapi.handleIntent(intent, this);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtils.showShortToast(WXEntryActivity.this, "用户拒绝授权登录");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == baseResp.getType())
                    ToastUtils.showShortToast(WXEntryActivity.this, "分享失败");
                else ToastUtils.showShortToast(WXEntryActivity.this, "用户取消授权登录");
                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        String code = ((SendAuth.Resp) baseResp).code;
                        LogUtils.e(TAG, "code = " + code);
                        EventBusUtils.sendEvent(new Event(EventAction.WX_LOGIN_ACCESSTOKEN, code));
                        finish();
                        break;
                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtils.showShortToast(WXEntryActivity.this, "微信分享成功");
                        finish();
                        break;
                }
                break;
            default:
                finish();
        }
    }

}