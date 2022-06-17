package com.eghuihe.teachpay;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eghuihe.teachpay.push.CustomNotification;
import com.eghuihe.teachpay.push.MobPushUtils;
import com.eghuihe.teachpay.push.MyMobPushCallback;
import com.eghuihe.teachpay.push.MyMobPushReceiver;
import com.eghuihe.teachpay.share.ShareSdkUtils;
import com.eghuihe.teachpay.share.WebActivity;
import com.eghuihe.teachpay.utils.BannerUtils;
import com.eghuihe.teachpay.utils.WeiXinUtils;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.mob.MobSDK;
import com.mob.pushsdk.MobPush;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.help.ConfigHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;


public class AppApplication extends BaseApplication {

    private static final String TAG = AppApplication.class.getSimpleName();
    private static IWXAPI api;
    private MyMobPushReceiver myMobPushReceiver;

    public static IWXAPI getWXapi() {
        return api;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Stack<AppCompatActivity> activityStack = AppManager.getInstance().getActivityStack();
            if (activityStack != null) {
                Iterator<AppCompatActivity> iterator = activityStack.iterator();
                LogUtils.i(TAG, "======================start=====================");
                while (iterator.hasNext()) {
                    AppCompatActivity next = iterator.next();
                    String name = next.getClass().getName();
                    LogUtils.i(TAG, "activity name-->" + name);
                }
                LogUtils.i(TAG, "======================end=====================");
            }
            handler.sendEmptyMessageDelayed(100, 3000);
        }
    };

    @Override
    protected void initSdk() {
        EventBusUtils.register(this);
        initIM();
        initModePush();
        initThreadTask();
        LogUtils.e("initSdk", "initSdk");
//        handler.sendEmptyMessageDelayed(100, 3000);
    }

    private void initThreadTask() {
      new Thread(new Runnable() {
          @Override
          public void run() {
              Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
              initWxSdk();
          }
      }).start();
    }

    private void initIM() {

        if (TUIKit.isMainProcess(getApplicationContext())) {
            TUIKit.init(this, AppConfigs.IM.sdkAppID, new ConfigHelper().getConfigs());
        }
    }

    private void initWxSdk() {
        api = WXAPIFactory.createWXAPI(this, AppConfigs.WeiXinPay.APPID, true);
        api.registerApp(AppConfigs.WeiXinPay.APPID);
    }

    private void initModePush() {
        MobSDK.init(this, "31bf832473abc", "fe9719c1e197caa5d6b7716006410414");
        MobPush.setNotifyIcon(R.mipmap.teach_pay_app_icon);
        MobPush.setShowBadge(true);
        myMobPushReceiver = new MyMobPushReceiver();
        MobPush.setTailorNotification(CustomNotification.class);
        MobPush.addPushReceiver(myMobPushReceiver);
        MobPush.getRegistrationId(new MyMobPushCallback());
        MobSDK.submitPolicyGrantResult(true, null);
    }

    @Override
    public void onTerminate() {
        EventBusUtils.unRegister(this);
        if (myMobPushReceiver != null) {
            MobPush.removePushReceiver(myMobPushReceiver);
        }
        TUIKit.logout(null);
        handler.removeCallbacksAndMessages(null);
        ShareSdkUtils.onDestory();
        super.onTerminate();
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.LOGIN_SUCCESS.equals(event.getAction())) {
            ActivityToActivity.toActivity(ARouterConfig.MAIN_MAINACTIVITY);
            AppManager.getInstance().finishAllActivity("com.eghuihe.module_main.main.ui.activity.MainActivity");
        } else if (EventAction.BANNER.equals(event.getAction())) {
            BannerModel.BannerEntity bannerEntity = (BannerModel.BannerEntity) event.getData();
            BannerUtils.doItemEvent(AppManager.getInstance().currentActivity(), bannerEntity);
        } else if (EventAction.MECHANISM_COURSE_DETAIL.equals(event.getAction())) {
            // 进入机构课程详情
            MasterSetPriceEntity masterSetPriceEntity = (MasterSetPriceEntity) event.getData();
            Map<String, String> params = new HashMap<>();
            params.put(ArgumentsConfig.MECHANISM_COURSE_DETAIL, JsonUtil.toJson(masterSetPriceEntity));
            ActivityToActivity.toActivity(ARouterConfig.ME_MECHANISM_COURSE_DETAIL, params);
        }  else if (EventAction.MECHANISM_DETAIL.equals(event.getAction())) {
            // 进入机构详情
            String mechanism_id = (String) event.getData();
            Map<String, String> params = new HashMap<>();
            params.put(ArgumentsConfig.KEY_MECHANISM_ID, mechanism_id);
            ActivityToActivity.toActivity(ARouterConfig.ME_MECHANISM_DETAIL, params);
        } else if (EventAction.TEACH_PAY_SWITCH_IDENTITIES.equals(event.getAction())) {
            // 切换身份
            AppManager.getInstance().finishActivity("com.eghuihe.module_main.main.ui.activity.MainActivity");
            ActivityToActivity.toActivity(ARouterConfig.MAIN_MAINACTIVITY);
        } else if (EventAction.LOGOUT_EVENT.equals(event.getAction())) {
            // 退出登录
            ActivityToActivity.toActivity(ARouterConfig.ME_LOGINACTIVITY);
            AppManager.getInstance().finishAllActivity("com.eghuihe.module_user.login.ui.activity.LoginActivity");
            LoginHelper.clearData();
        } else if (EventAction.WX_PAY.equals(event.getAction())) {
            // 调用微信支付
            Object data = event.getData();
            if (data != null && data instanceof WxPayModel.WxPayEntity) {
                WxPayModel.WxPayEntity wxPayEntity = (WxPayModel.WxPayEntity) data;
                WeiXinUtils.startWechatPay(wxPayEntity);
            }
        } else if (EventAction.WX_LOGIN.equals(event.getAction())) {
            // 调用微信登录
            WeiXinUtils.wxThridLogin();
        } else if (EventAction.SUBMIT_POLICYGRANT.equals(event.getAction())) {
            // mob推送隐私授权
            Object data = event.getData();
            if (data instanceof Boolean) {
                MobPushUtils.submitPolicyGrant((Boolean) data);
            }
        } else if (EventAction.SETTING_MOTHER_TONGUE.equals(event.getAction())) {
            // 设置母语
            ActivityToActivity.toActivity(ARouterConfig.ME_MOTHERTONGUESETTINGACTIVITY);
        }  else if (EventAction.PING_GROUP.equals(event.getAction())) {
            // 拼团
            Object data = event.getData();
            if (data instanceof Map) {
                Map<String, String> map = (Map<String, String>) data;
                String url = map.get(ArgumentsConfig.KEY_URL);
                String title = map.get(ArgumentsConfig.KEY_TITLE);
                String wxPath = map.get(ArgumentsConfig.KEY_WXPATH);
                String content = map.get(ArgumentsConfig.KEY_CONTENT);
                String imgUrl = map.get(ArgumentsConfig.KEY_IMGURL);
                AppCompatActivity appCompatActivity = AppManager.getInstance().currentActivity();
                if (appCompatActivity != null) {
                    Intent intent = new Intent(appCompatActivity, WebActivity.class);
                    intent.putExtra(ArgumentsConfig.KEY_URL, url);
                    intent.putExtra(ArgumentsConfig.KEY_TITLE, title);
                    intent.putExtra(ArgumentsConfig.KEY_WXPATH, wxPath);
                    intent.putExtra(ArgumentsConfig.KEY_CONTENT, content);
                    intent.putExtra(ArgumentsConfig.KEY_IMGURL, imgUrl);
                    appCompatActivity.startActivity(intent);
                }
            }
        }else if (EventAction.COURSE_DETAIL_SHARE.equals(event.getAction())) {
            // 课程详情分享
            share(event);
        }else if (EventAction.MECHANISM_DETAIL_SHARE.equals(event.getAction())) {
            // 机构详情分享
            share(event);
        }else if (EventAction.BAOBAOXIU_SHARE.equals(event.getAction())) {
            // 宝宝秀分享
            share(event);
        }
    }

    private void share(Event event) {
        Object data = event.getData();
        if (data instanceof Map) {
            Map<String, String> map = (Map<String, String>) data;
            String url = map.get(ArgumentsConfig.KEY_URL);
            String title = map.get(ArgumentsConfig.KEY_TITLE);
            String wxPath = map.get(ArgumentsConfig.KEY_WXPATH);
            String content = map.get(ArgumentsConfig.KEY_CONTENT);
            String imgUrl = map.get(ArgumentsConfig.KEY_IMGURL);
            final AppCompatActivity appCompatActivity = AppManager.getInstance().currentActivity();
            if (appCompatActivity!=null){
                View rootView = appCompatActivity.getWindow().getDecorView().getRootView();
                ShareSdkUtils.showShardPopWindow(
                        getApplicationContext(),
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
                                ShareSdkUtils.onDestory();
                                EventBusUtils.sendEvent(new Event(EventAction.DYNAMIC_SHARE_SUCCESS,platform.getName()));
                            }

                            @Override
                            public void onError(Platform platform, int i, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(Platform platform, int i) {
                                ToastUtils.showShortToast(appCompatActivity, "取消分享");
                            }
                        });
            }
        }
    }

}
