package com.huihe.base_lib.utils.pay.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.huihe.base_lib.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;

import java.util.Map;

public class AlipayUtils {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private static final String TAG = AlipayUtils.class.getSimpleName();
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if ("9000".equals(resultStatus)) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        if (onAliPayListener != null)
                            onAliPayListener.onPaySuccess(payResult);
                        EventBusUtils.sendEvent(new Event(EventAction.PAY_SUCCESS));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        showAlert(context, context.getResources().getString(R.string.pay_failed) + payResult);
                        if (onAliPayListener != null)
                            onAliPayListener.onPayFail(payResult);
                        EventBusUtils.sendEvent(new Event(EventAction.PAY_FAIL));
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if ("9000".equals(resultStatus) && "200".equals(authResult.getResultCode())) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        if (onAuthListener != null) {
                            onAuthListener.onAuthSuccess(authResult);
                        }
//                        showAlert(context, context.getResources().getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(context, context.getResources().getString(R.string.auth_failed) + authResult);
                        if (onAuthListener != null) {
                            onAuthListener.onAuthFail(authResult);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    private static AlipayUtils alipayUtils;

    private OnAliPayListener onAliPayListener;
    private OnAuthListener onAuthListener;
    private Context context;

    public AlipayUtils(Context context) {
        this.context = context;
    }

    public static AlipayUtils getInstance(Context context) {
        if (alipayUtils == null) {
            synchronized (AlipayUtils.class) {
                if (alipayUtils == null) {
                    alipayUtils = new AlipayUtils(context);
                }
            }
        }
        return alipayUtils;
    }

    public void pay(final String orderInfo, OnAliPayListener onAliPayListener) {
        this.onAliPayListener = onAliPayListener;
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                LogUtils.i(TAG, result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public void auth(final String authInfo, OnAuthListener onAuthListener) {
        this.onAuthListener = onAuthListener;
        final Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                AuthTask authTask = new AuthTask((Activity) context);
                Map<String, String> result = authTask.authV2(authInfo, true);
                LogUtils.i(TAG, result.toString());
                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    @SuppressLint("NewApi")
    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }

    public interface OnAliPayListener {
        void onPaySuccess(PayResult payResult);

        void onPayFail(PayResult payResult);
    }

    public interface OnAuthListener {
        void onAuthSuccess(AuthResult authResult);

        void onAuthFail(AuthResult authResult);
    }
}

