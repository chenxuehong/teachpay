package com.eghuihe.module_main.main.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_main.R;
import com.eghuihe.module_main.R2;
import com.eghuihe.module_main.main.ui.mvp.MainContract;
import com.eghuihe.module_main.main.ui.mvp.MainPresenter;
import com.huihe.base_lib.BuildConfig;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.fragment.BaseFragment;
import com.huihe.base_lib.ui.service.UpdateService;
import com.huihe.base_lib.ui.widget.bottomTabLayout.BottomBarLayoutWithVP;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.fitViewPager.NoScrollViewPager;
import com.huihe.base_lib.utils.ARouterUtils;
import com.huihe.base_lib.utils.DeviceUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.MarketUtil;
import com.huihe.base_lib.utils.NotificationPermissionUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.Utils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.component.fragment.ConversationFragment;
import com.tencent.qcloud.tim.uikit.component.fragment.StudentConversationFragment;
import com.tencent.qcloud.tim.uikit.utils.IMFunc;
import com.tencent.qcloud.tim.uikit.utils.ThirdPushTokenMgr;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

@Route(path = ARouterConfig.MAIN_MAINACTIVITY)
public class MainActivity extends BaseMvpActivity<MainPresenter>
        implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int INSTALL_PACKAGES_REQUESTCODE = 101;
    @BindView(R2.id.act_teaching_pay_main_noScrollViewPager)
    NoScrollViewPager viewPager;
    @BindView(R2.id.act_teaching_pay_main_bottomBarLayout)
    BottomBarLayoutWithVP bottomBar;
    private long mExitTime;

    private BaseFragment mechanismScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_MECHANISMSCHEDULE_FRAGMENT);
    private BaseFragment mechanismArrangeScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_MECHANIARRANGESMSCHEDULEFIX_FRAGMENT);
    private BaseFragment studentScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_STUDENTSCHEDULE_FRAGMENT);
    private BaseFragment dynamicfragment = ARouterUtils.getFragment(ARouterConfig.DYNAMIC_DYNAMICFRAGMENT);
    private BaseFragment meFragment = ARouterUtils.getFragment(ARouterConfig.ME_MEFRAGMENT);
    private BaseFragment homeFragment = ARouterUtils.getFragment(ARouterConfig.HOME_FRAGMENT);
    private BaseFragment incomeCenterFragment = ARouterUtils.getFragment(ARouterConfig.ME_MECHANISM_INCOMECENTERFRAGMENT);
    private BaseFragment teacherScheduleFragment = ARouterUtils.getFragment(ARouterConfig.SCHEDULE_TEACHERSCHEDULE_FRAGMENT);
    private BaseDialog checkUpdateDialog;
    private BaseDialog tipInstallPermissiondialog;
    private VersionIterationModel.VersionIterationEntity.NewInfoBean new_info;

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teaching_pay_main;
    }

    @Override
    protected void initData() {
        pushClientId();
        if (bottomBar != null) {
            bottomBar.reset();
        }
        bottomBar.with(this)
                .setViewPager(viewPager)
                .setTextColor(
                        getResources().getColor(R.color.color_FF7300),
                        getResources().getColor(R.color.color_393939))
                .setTextSize(12)
                .setSmoothScroll(false)
                .addStateListAnimatorForScale(1.0f, 0.88f, 50);

        if (Utils.TYPE_STUDENT.equals(Utils.getIdentityType())) {
            // 首页、课程表、订单、我的
            initStudentItemViews();
        } else if (Utils.TYPE_MECHANISM.equals(Utils.getIdentityType())) {
            if (Utils.isSwitchMechanismIdentity()) {
                // 课程表、排课表、收益、我的
                initMechanismViews();
            } else {
                initStudentItemViews();
            }
        } else {
            initTeacherViews();
        }
        viewPager.setOffscreenPageLimit(bottomBar.getFragmentList().size());
        bottomBar.build();

        String versionName = DeviceUtils.getVersionName(getApplicationContext());
        String cuPlatform = MarketUtil.getCuPlatform();
        getPresenter().versionIteration(versionName, cuPlatform);

    }

    private void pushClientId() {
        NotificationPermissionUtils.checkAndOpenNotificationPermission(this);
        LoginResultEntity data = LoginHelper.getLoginInfo();
        String model = DeviceUtils.getModel();
        String uuid = DeviceUtils.getUUID();
        getPresenter().userDeviceInsert(
                model,
                LoginHelper.getClientid(),
                String.valueOf(data.getUserInfoEntity().getUser_id()),
                uuid,
                ThirdPushTokenMgr.getInstance().getThirdPushToken()
        );
        ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
    }

    private void initTeacherViews() {
        bottomBar.addItemView(getResources().getString(R.string.Course_table),
                R.mipmap.teaching_pay_schedule_selected,
                R.mipmap.teaching_pay_schedule_unselected,
                teacherScheduleFragment.getClass())
                .addItemView(getResources().getString(R.string.tab_me),
                        R.mipmap.teaching_pay_me_selected,
                        R.mipmap.teaching_pay_me_unselected,
                        meFragment.getClass());
    }

    private void initMechanismViews() {
        bottomBar.addItemView(getResources().getString(R.string.Course_table),
                R.mipmap.teaching_pay_schedule_selected,
                R.mipmap.teaching_pay_schedule_unselected,
                mechanismScheduleFragment.getClass())
                .addItemView(getResources().getString(R.string.Arranged_Schedule),
                        R.mipmap.teaching_pay_arrange_schedule_selected,
                        R.mipmap.teaching_pay_arrange_schedule_unselected,
                        mechanismArrangeScheduleFragment.getClass())
                .addItemView("消息",
                        R.mipmap.im_selected,
                        R.mipmap.im_unselected,
                        ConversationFragment.class)
                .addItemView(getResources().getString(R.string.income),
                        R.mipmap.teaching_pay_income_selected,
                        R.mipmap.teaching_pay_income_unselected,
                        incomeCenterFragment.getClass())
                .addItemView(getResources().getString(R.string.tab_me),
                        R.mipmap.teaching_pay_me_selected,
                        R.mipmap.teaching_pay_me_unselected,
                        meFragment.getClass());
    }

    private void initStudentItemViews() {
        bottomBar.addItemView(getResources().getString(R.string.tab_home),
                R.mipmap.teaching_pay_home_selected,
                R.mipmap.teaching_pay_home_unselected,
                homeFragment.getClass())
                .addItemView(getResources().getString(R.string.Course_table),
                        R.mipmap.teaching_pay_schedule_selected,
                        R.mipmap.teaching_pay_schedule_unselected,
                        studentScheduleFragment.getClass())
                .addItemView("消息",
                        R.mipmap.im_selected,
                        R.mipmap.im_unselected,
                        StudentConversationFragment.class)
                .addItemView("宝宝秀",
                        R.mipmap.dynamic_selected,
                        R.mipmap.dynamic_unselected,
                        dynamicfragment.getClass())
                .addItemView(getResources().getString(R.string.tab_me),
                        R.mipmap.teaching_pay_me_selected,
                        R.mipmap.teaching_pay_me_unselected,
                        meFragment.getClass());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShortToast(mContext, getString(R.string.main_exit_app));
                mExitTime = System.currentTimeMillis();
            } else {
                BaseApplication.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bottomBar != null) {
            bottomBar.clearCache();
        }
        try {
            if (checkUpdateDialog != null) {
                checkUpdateDialog.dismiss();
            }
            if (tipInstallPermissiondialog != null) {
                tipInstallPermissiondialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.RESET_USERINFO.equals(event.getAction())) {
            getPresenter().queryUserInfo(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
        } else if (EventAction.IM_UNREAD_COUNT.equals(event.getAction())) {
            try {
                Object data = event.getData();
                if (data instanceof String) {
                    String unreadCount = (String) data;
                    if (Utils.TYPE_STUDENT.equals(Utils.getIdentityType())) {
                        // 首页、课程表、订单、我的
                        if (bottomBar != null) {
                            bottomBar.setUnreadMsgCountAtIndex(2, Integer.valueOf(unreadCount));
                            bottomBar.setItemMsgView(2, Integer.valueOf(unreadCount));
                        }
                    } else if (Utils.TYPE_MECHANISM.equals(Utils.getIdentityType())) {
                        if (bottomBar != null) {
                            bottomBar.setUnreadMsgCountAtIndex(2, Integer.valueOf(unreadCount));
                            bottomBar.setItemMsgView(2, Integer.valueOf(unreadCount));
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUserInfo(UserInfoEntity userInfoEntity) {
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        loginInfo.setUserInfoEntity(userInfoEntity);
        LoginHelper.saveUserData(loginInfo);
    }

    @Override
    public void onVersionIteration(VersionIterationModel.VersionIterationEntity iterationEntity) {
        if (iterationEntity != null && iterationEntity.isIs_iteration()) {
            new_info = iterationEntity.getNew_info();
            if (new_info != null) {
                showCheckUpdateDialog(new_info);
            }
        }
    }

    private void showCheckUpdateDialog(final VersionIterationModel.VersionIterationEntity.NewInfoBean newInfoBean) {
        // 需要更新
        checkUpdateDialog = new BaseDialog(AppManager.getInstance().currentActivity()) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_apk_update;
            }

            @Override
            protected void initEvents() {

                getView(R.id.dialog_apk_update_iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                getView(R.id.dialog_apk_update_tv_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (BuildConfig.DEBUG) {
                            Uri uri = Uri.parse("http://d.7short.com/aljy");    //设置跳转的网站
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            AppManager.getInstance().currentActivity().startActivity(intent);
                            dismiss();
                        } else {
                            boolean goToAppMarket = MarketUtil.goToAppMarket(AppManager.getInstance().currentActivity());
                            if (!goToAppMarket) {
                                checkIsAndroidO(newInfoBean.getUrl());
                            }
                            dismiss();
                        }
                    }
                });
            }

            @Override
            protected void initParams() {
                TextView tvVersionInfo = (TextView) getView(R.id.dialog_apk_update_tv_versionInfo);
                TextView tvTitle = (TextView) getView(R.id.dialog_apk_update_tv_title);
                tvTitle.setText(getResources().getString(R.string.New_version_found));
                tvVersionInfo.setText(
                        String.format(getResources().getString(R.string.app_name_params), getResources().getString(R.string.application_name))
                                + "\n"
                                + String.format(getResources().getString(R.string.newest_version_params), String.valueOf(newInfoBean.getVersion()))
                                + "\n" + newInfoBean.getIteration_content());
            }
        };
        checkUpdateDialog.setPerWidth(246f / 375);
        checkUpdateDialog.setCancelOutside(false);
        checkUpdateDialog.show();
    }

    /**
     * 上线调用
     *
     * @param url
     */
    private void checkIsAndroidO(String url) {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                installApk(url);//安装应用的逻辑(写自己的就可以)
            } else {
                //请求安装未知应用来源的权限 安装应用需要打开未知来源权限，请去设置中开启权限
                tipInstallPermissiondialog = DialogUtils.showTipDialog(AppManager.getInstance().currentActivity(),
                        null,
                        BaseApplication.getInstance().getResources().getString(R.string.install_permission_tip),
                        BaseApplication.getInstance().getResources().getString(R.string.cancel),
                        BaseApplication.getInstance().getResources().getString(R.string.setting),
                        new DialogUtils.OnListener() {
                            @Override
                            public void okClicked() {
                                ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(),
                                        new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},
                                        INSTALL_PACKAGES_REQUESTCODE);
                            }

                            @Override
                            public void cancelClicked() {

                            }
                        }
                );
            }
        } else {
            installApk(url);
        }
    }

    private void installApk(String url) {
        MPermission.with(this).setPermission(
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}
        ).requestPermission(
                new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        Intent intent = new Intent(AppManager.getInstance().currentActivity(), UpdateService.class);
                        intent.putExtra(UpdateService.KEY_APK_URL, url);
                        AppManager.getInstance().currentActivity().startService(intent);
                    }

                    @Override
                    public void valdateFail() {

                    }
                }
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (INSTALL_PACKAGES_REQUESTCODE == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (new_info != null)
                    installApk(new_info.getUrl());
            } else {
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {//2.3
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", AppManager.getInstance().currentActivity().getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {//2.2
                    localIntent.setAction(Intent.ACTION_VIEW);
                    localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    localIntent.putExtra("com.android.settings.ApplicationPkgName", AppManager.getInstance().currentActivity().getPackageName());
                }
                AppManager.getInstance().currentActivity().startActivity(localIntent);
            }
        }
    }
}
