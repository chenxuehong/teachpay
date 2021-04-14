package com.eghuihe.module_user.login.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.login.mvp.MainContract;
import com.eghuihe.module_user.login.mvp.MainPresenter;
import com.eghuihe.module_user.utils.MyDialogUtils;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.banner.BannerModel;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.widget.banner.OnItemClickListener;
import com.huihe.base_lib.ui.widget.banner.XBanner;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.fitViewPager.transformers.ScalePageTransformer;
import com.huihe.base_lib.utils.ActivityToActivity;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.SPUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.ME_SPLASHACTIVITY)
public class SplashActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    private static final String TAG = SplashActivity.class.getSimpleName();
    @BindView(R2.id.splash_xbanner)
    XBanner xBanner;
    @BindView(R2.id.splash_tv_time)
    TextView tvTime;

    private Object bannerModel;
    private BaseDialog agreementDialog;

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    int time = 3;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (time > 0) {
                    if (tvTime != null) {
                        tvTime.setVisibility(View.VISIBLE);
                        tvTime.setText(time-- + getResources().getString(R.string.jump));
                    }
                    handler.sendEmptyMessageDelayed(100, 1000);
                } else {
                    handleData();
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.acivity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        if (tvTime != null) {
            tvTime.setEnabled(true);
            tvTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.removeCallbacksAndMessages(null);
                    handleData();
                }
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        long application_attach_time = BaseApplication.application_attach_time;
        long diffTime = System.currentTimeMillis() - application_attach_time;
        LogUtils.i(TAG,"diffTime = ".concat(String.valueOf(diffTime)) );
    }

    @Override
    protected void initData() {
        bannerModel = null;
        if (LoginHelper.getBoolean(LoginHelper.KEY_IS_POLICYGRANT)) {
            startDownTimer();
        } else {
            showAgreementTipDialog();
        }
    }

    private void showAgreementTipDialog() {
        agreementDialog = MyDialogUtils.showAgreementTipDialog(this, new MyDialogUtils.OnListener() {
            @Override
            public void onCancel() {
                showExitTipDialog();
            }

            @Override
            public void onSure() {
                submitPolicyGrant(true);
                startDownTimer();
            }
        });
    }

    private void submitPolicyGrant(boolean granted) {
        EventBusUtils.sendEvent(new Event(EventAction.SUBMIT_POLICYGRANT, granted));
    }

    private void showExitTipDialog() {
        DialogUtils.showTipDialog(
                this,
                null,
                getResources().getString(R.string.no_agreement_tip),
                getResources().getString(R.string.Disagree_and_exit),
                getResources().getString(R.string.Think_again), new DialogUtils.OnListener() {
                    @Override
                    public void okClicked() {
                        showAgreementTipDialog();
                    }

                    @Override
                    public void cancelClicked() {
                        submitPolicyGrant(false);
                        finish();
                    }
                });
    }

    private void initBanner() {
        String type = "teachPay_launchScreen";
        getPresenter().getBannerData(type, "1");
    }

    private void startDownTimer() {
        initBanner();
        handler.sendEmptyMessage(100);
    }

    private void handleData() {
        handler.removeMessages(100);
        tvTime.setEnabled(false);
        xBanner.setEnabled(false);
        if (LoginHelper.isLogin()) {
            login();
        } else {
            startLogin();
        }
    }

    private void login() {
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        if (loginInfo != null) {
            UserInfoEntity userInfoEntity = loginInfo.getUserInfoEntity();
            if (userInfoEntity != null) {
                TUIKit.login(
                        userInfoEntity.getUser_id(),
                        loginInfo.getUserSign(),
                        new IUIKitCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                startMain();
                            }

                            @Override
                            public void onError(String module, int errCode, String errMsg) {
                                ToastUtils.showShortToast(BaseApplication.getInstance(),
                                        "登录失败, errCode = ".concat(String.valueOf(errCode))
                                                .concat(", errInfo = ")
                                                .concat(errMsg));
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        startLogin();
                                    }
                                });
                            }
                        }
                );
            }else {
                startLogin();
            }
        }
    }

    private void startLogin() {
        int loginNum = LoginHelper.getLoginNum();
        if (loginNum <= 1) {
            ActivityToActivity.toActivity(ARouterConfig.MAIN_GUIDEACTIVITY);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        delayFinish();
        if (bannerModel != null)
            EventBusUtils.sendEvent(new Event(EventAction.BANNER, (BannerModel.BannerEntity) bannerModel));
    }

    private void delayFinish() {
        finish();
    }

    private void startMain() {
        ActivityToActivity.toActivity(ARouterConfig.MAIN_MAINACTIVITY);
        delayFinish();
        EventBusUtils.sendEvent(new Event(EventAction.BANNER, (BannerModel.BannerEntity) bannerModel));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        if (xBanner != null)
            xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止轮播
        if (xBanner != null)
            xBanner.stopAutoPlay();
    }

    @Override
    protected void onDestroy() {
        try {
            if (agreementDialog != null) {
                agreementDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (handler != null)
            handler.removeMessages(100);
        super.onDestroy();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showBanner(List<BannerModel.BannerEntity> bannerEntities) {
        if (bannerEntities == null && bannerEntities.size() == 0) {
            return;
        }
        //刷新数据之后，需要重新设置是否支持自动轮播
        xBanner.setAutoPlayAble(bannerEntities.size() > 1);
        xBanner.setBannerData(bannerEntities);
        xBanner.setCustomPageTransformer(new ScalePageTransformer());
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                BannerModel.BannerEntity entity = (BannerModel.BannerEntity) model;
                GlideTools.loadImage(
                        SplashActivity.this,
                        entity.getPic(),
                        (ImageView) view,
                        R.drawable.splash,
                        R.drawable.splash);
            }
        });
        xBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                bannerModel = model;
                handleData();
            }
        });
    }
}
