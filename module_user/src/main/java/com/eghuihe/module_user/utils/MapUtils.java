package com.eghuihe.module_user.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.AMapUtil;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.SystemUtils;
import com.huihe.base_lib.utils.ToastUtils;

public class MapUtils {
    /**
     * 开始导航
     *
     * @param context
     * @param latitude
     * @param longitude
     */
    public static void startNavigation(final Context context,
                                       final Double latitude,
                                       final Double longitude) {
        if (latitude == null || longitude == null) {
            ToastUtils.showShortToast(context, "该机构未上传位置");
            return;
        }
        View rootView = ((Activity) context).getWindow().getDecorView().getRootView();
        final CustomPopupWindow customPopupWindow = PopWindowUtils.popBottomDialog(rootView, context, R.layout.dialog_navigation, true);
        TextView tvBaiduMap = (TextView) customPopupWindow.getItemView(R.id.dialog_nav_tv_baiduMap);
        TextView tvAMAP = (TextView) customPopupWindow.getItemView(R.id.dialog_nav_tv_AMAP);
        TextView tvCancel = (TextView) customPopupWindow.getItemView(R.id.dialog_nav_tv_cancel);
        tvCancel.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                customPopupWindow.dismiss();
            }
        });
        tvBaiduMap.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (SystemUtils.isAppInstall(context, "com.baidu.BaiduMap")) {
                    AMapUtil.openBaiduMap(context,
                            longitude,
                            latitude);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.You_have_not_installed_Baidu_map), Toast.LENGTH_LONG).show();
                }

            }
        });
        tvAMAP.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (SystemUtils.isAppInstall(context, "com.autonavi.minimap")) {
                    AMapUtil.openAMap(context,
                            longitude,
                            latitude);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.You_have_not_installed_goldmap), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
