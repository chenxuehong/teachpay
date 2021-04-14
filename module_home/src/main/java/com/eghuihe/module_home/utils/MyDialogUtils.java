package com.eghuihe.module_home.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.widget.AutoFitImageView;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.utils.glide.GlideTools;

public class MyDialogUtils {

    public static BaseDialog showLocationTipDialog(Context context, final OnListener onListener) {
        BaseDialog baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_location_tip;
            }

            @Override
            protected void initEvents() {

                getView(R.id.dialog_location_tip_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onListener != null) {
                            onListener.onCancel();
                        }
                    }
                });
                getView(R.id.dialog_location_tip_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onListener != null) {
                            onListener.onSure();
                        }
                    }
                });
            }

            @Override
            protected void initParams() {

                TextView tvContent = (TextView) getView(R.id.dialog_location_tip_tv_content);
                tvContent.setText("教付保正在访问您的位置信息，该权限主要用于查询附近的课程、附近的机构，请允许否则无法使用上述功能");
            }
        };
        baseDialog.setPerWidth(309f / 414);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }

    /**
     * @desc 领取优惠券对话框
     * @param context
     * @param onListener
     * @return
     */
    public static BaseDialog showCouponDialog(final Context context, final OnListener onListener) {
        BaseDialog baseDialog = new BaseDialog(context) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_coupon;
            }

            @Override
            protected void initEvents() {

                getView(R.id.dialog_coupon_iv_icon).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onListener != null) {
                            onListener.onSure();
                        }
                    }
                });
                getView(R.id.dialog_coupon_iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onListener != null) {
                            onListener.onCancel();
                        }
                    }
                });


            }

            @Override
            protected void initParams() {

                AutoFitImageView ivIcon = (AutoFitImageView) getView(R.id.dialog_coupon_iv_icon);
                GlideTools.loadGifImage(BaseApplication.getInstance(),R.drawable.get_coupon_dialog,ivIcon);
            }
        };
        baseDialog.setPerWidth(309f / 414);
        baseDialog.setCancelOutside(false);
        baseDialog.show();
        return baseDialog;
    }
    public interface OnListener {
        void onCancel();

        void onSure();
    }
}
