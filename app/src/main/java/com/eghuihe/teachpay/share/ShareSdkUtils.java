package com.eghuihe.teachpay.share;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.teachpay.R;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.ImageUtil;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareSdkUtils {

    private static CustomPopupWindow customPopupWindow;

    public static void showShardPopWindow(
            Context context,
            final String title,
            final String content,
            final String imagePath,
            final String imageUrl,
            final String url,
            final String wxPath,
            View parentView,
            final PlatformActionListener actionListener) {

        customPopupWindow = PopWindowUtils.popBottomDialog(
                parentView,
                context,
                R.layout.pop_dialog_share, true);
        customPopupWindow.setOnClickListener(
                R.id.pop_dialog_share_fl_Wechat, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showShare(
                                Wechat.NAME,
                                title,
                                content,
                                imageUrl,
                                imagePath,
                                url,
                                actionListener
                        );
                    }
                });
        customPopupWindow.setOnClickListener(R.id.pop_dialog_share_fl_wechatmoments, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(
                        WechatMoments.NAME,
                        title,
                        content,
                        imageUrl,
                        imagePath,
                        url,
                        actionListener
                );
            }
        });
        customPopupWindow.setOnClickListener(R.id.pop_dialog_share_fl_WXMiniProgram, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWxminiProgram(
                        title,
                        content,
                        url,
                        imagePath,
                        imageUrl,
                        wxPath,
                        actionListener
                );
            }
        });
        customPopupWindow.setOnClickListener(R.id.pop_dialog_share_tv_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopupWindow.dismiss();
            }
        });
    }

    public static void showShare(String platform, String title, String content, String imageUrl, String imagePath, String url, PlatformActionListener operationCallback) {
        final OnekeyShare oks = new OnekeyShare();
        oks.setCallback(operationCallback);
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
//        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(url);
//        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        if (!TextUtils.isEmpty(imageUrl)){
            oks.setImageUrl(imageUrl);
        }
        if (!TextUtils.isEmpty(imagePath)){
            oks.setImagePath(imagePath);
        }
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        //启动分享
        oks.show(MobSDK.getContext());
    }

    public static void shareWechat(
            String title,
            String content,
            String url,
            String imagePath,
            String imageUrl,
            PlatformActionListener actionListener) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if (!TextUtils.isEmpty(imagePath)) {
            sp.setImagePath(imagePath);
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            sp.setImageUrl(imageUrl);
        }
        if (!TextUtils.isEmpty(url)) {
            sp.setUrl(url);
        }
        Platform tw = ShareSDK.getPlatform(Wechat.NAME);
        tw.setPlatformActionListener(actionListener); // 设置分享事件回调
        // 执行图文分享
        tw.share(sp);
    }

    public static void shareWechatMoments(
            String title,
            String content,
            String url,
            String imagePath,
            String imageUrl,
            PlatformActionListener actionListener) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(title);
        sp.setText(content);
        if (!TextUtils.isEmpty(imagePath)) {
            sp.setImagePath(imagePath);
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            sp.setImageUrl(imageUrl);
        }
        if (!TextUtils.isEmpty(url)) {
            sp.setUrl(url);
        }
        Platform tw = ShareSDK.getPlatform(WechatMoments.NAME);
        tw.setPlatformActionListener(actionListener); // 设置分享事件回调
        // 执行图文分享
        tw.share(sp);
    }

    public static void shareWxminiProgram(
            String title,
            String content,
            String url,
            String imagePath,
            String imageUrl,
            String wxPath,
            PlatformActionListener actionListener) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_WXMINIPROGRAM);
        sp.setTitle(title);
        sp.setText(content);
        sp.setWxPath(wxPath);
        if (!TextUtils.isEmpty(imagePath)) {
            sp.setImagePath(imagePath);
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            sp.setImageUrl(imageUrl);
        }
        sp.setUrl(wxPath);
        Platform tw = ShareSDK.getPlatform(Wechat.NAME);
        tw.setPlatformActionListener(actionListener); // 设置分享事件回调
        // 执行图文分享
        tw.share(sp);
    }

    public static void onDestory() {
        try {
            if (customPopupWindow != null) {
                customPopupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
