package com.huihe.base_lib.utils.pay;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.ToastUtils;

public class PayUtils {

    private static final int PAY_METHOD_PAYBAO = 100;
    private static final int PAY_METHOD_WEIXIN = 101;
    private static int payMethod = PAY_METHOD_PAYBAO; // 默认支付宝支付


    private static int id = R.drawable.uncheck_state;

    public static CustomPopupWindow popPayWindow(final Context context,
                                                 final String payValue,
                                                 final OnListener onListener) {
        id = R.drawable.uncheck_state;
        payMethod = PAY_METHOD_PAYBAO;
        View rootView = ((Activity) context).getWindow().getDecorView().getRootView();
        CustomPopupWindow customPopupWindow = PopWindowUtils.popBottomDialog(rootView, context, R.layout.dialog_pay, true);
        TextView tvPayCount = (TextView) customPopupWindow.getItemView(R.id.dialog_pay_count);
        final ImageView ivPaybao = (ImageView) customPopupWindow.getItemView(R.id.dialog_pay_iv_paybao);
        final ImageView ivWeiXin = (ImageView) customPopupWindow.getItemView(R.id.dialog_pay_iv_weixinpay);
        final ImageView ivCor = (ImageView) customPopupWindow.getItemView(R.id.dialog_pay_iv_cor);
        // 选中支付宝支付监听
        customPopupWindow.setOnClickListener(R.id.dialog_pay_ll_paybao, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ivPaybao.setImageResource(R.drawable.check_state);
                ivWeiXin.setImageResource(R.drawable.uncheck_state);
                payMethod = PAY_METHOD_PAYBAO;
            }
        });
        // 选中微信支付监听
        customPopupWindow.setOnClickListener(R.id.dialog_pay_ll_weixinpay, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivWeiXin.setImageResource(R.drawable.check_state);
                ivPaybao.setImageResource(R.drawable.uncheck_state);
                payMethod = PAY_METHOD_WEIXIN;
            }
        });
        // 协议选中监听
        customPopupWindow.setOnClickListener(R.id.dialog_pay_ll_cor, new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                id = (id == R.drawable.uncheck_state) ? R.drawable.check_state : R.drawable.uncheck_state;
                ivCor.setImageResource(id);
            }
        });
        // 确认支付监听
        customPopupWindow.setOnClickListener(R.id.dialog_pay_tv_comfirm_pay, new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (id == R.drawable.uncheck_state) {
                    ToastUtils.showLongToast(context,
                            context.getResources().getString(R.string.Please_agree_to_the_recharge_agreement));
                    return;
                }
                // 开始调用三方支付的SDK
                switch (payMethod) {
                    case PAY_METHOD_PAYBAO:
                        onListener.alipay();
                        break;
                    case PAY_METHOD_WEIXIN:
                        onListener.weixinPay();
//                        ToastUtils.showLongToast(context, "调用微信支付接口");
                        break;
                }
            }
        });

        tvPayCount.setText(payValue);
        return customPopupWindow;
    }

    public interface OnListener {
        void alipay();

        void weixinPay();
    }

}
