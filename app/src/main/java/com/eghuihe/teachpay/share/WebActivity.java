package com.eghuihe.teachpay.share;

import android.view.View;

import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.utils.ToastUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

public class WebActivity extends H5TitleActivity {
    @Override
    protected boolean useShare() {
        return true;
    }

    @Override
    protected void showSharePopWindow(
            String url,
            String title,
            String wxPath,
            String content,
            String imgUrl) {
        View rootView = getWindow().getDecorView().getRootView();
        ShareSdkUtils.showShardPopWindow(
                WebActivity.this,
                title,
                content,
                "",
                imgUrl,
                url,
                wxPath,
                rootView,
                new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                        ToastUtils.showShortToast(WebActivity.this, "分享成功");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ToastUtils.showShortToast(WebActivity.this, "取消分享");
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        ShareSdkUtils.onDestory();
        super.onDestroy();
    }
}
