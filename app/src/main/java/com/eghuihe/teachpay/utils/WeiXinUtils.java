package com.eghuihe.teachpay.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.eghuihe.teachpay.AppApplication;
import com.eghuihe.teachpay.R;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WXMiniProgramObject;

public class WeiXinUtils {
    /**
     * 调支付的方法
     * <p>
     * 注意： 每次调用微信支付的时候都会校验 appid 、包名 和 应用签名的。 这三个必须保持一致才能够成功调起微信
     *
     * @param wxPayBean
     */
    public static void startWechatPay(WxPayModel.WxPayEntity wxPayBean) {

        IWXAPI wXapi = AppApplication.getWXapi();
        if (!wXapi.isWXAppInstalled()) {
            ToastUtils.showShortToast(AppManager.getInstance().currentActivity(), "您还未安装微信客户端");
            return;
        }
        //这里的bean，是服务器返回的json生成的bean
        PayReq payRequest = new PayReq();
        payRequest.appId = wxPayBean.getAppid();
        payRequest.partnerId = wxPayBean.getPartnerid();
        payRequest.prepayId = wxPayBean.getPrepayid();
        payRequest.packageValue = wxPayBean.getPackageX();//固定值
        payRequest.nonceStr = wxPayBean.getNoncestr();
        payRequest.timeStamp = wxPayBean.getTimestamp();
        payRequest.sign = wxPayBean.getPaySign();

        //发起请求，调起微信前去支付
        boolean sendReq = wXapi.sendReq(payRequest);
    }

    public static void wxThridLogin() {
        IWXAPI wXapi = AppApplication.getWXapi();
        if (!wXapi.isWXAppInstalled()) {
            ToastUtils.showShortToast(AppManager.getInstance().currentActivity(), "您还未安装微信客户端");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        wXapi.sendReq(req);
    }
}
