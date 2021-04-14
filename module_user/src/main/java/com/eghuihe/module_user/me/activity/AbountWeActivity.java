package com.eghuihe.module_user.me.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.mvp.AbountWeContract;
import com.eghuihe.module_user.me.mvp.AbountWePresenter;
import com.huihe.base_lib.BuildConfig;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.service.UpdateService;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DeviceUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.MarketUtil;
import com.huihe.base_lib.utils.manager.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class AbountWeActivity extends BaseMvpTitleActivity<AbountWePresenter>
        implements AbountWeContract.View {

    private static final int INSTALL_PACKAGES_REQUESTCODE = 1001;
    private static final String TAG = AbountWeActivity.class.getSimpleName();
    @BindView(R2.id.about_us_tv_version)
    TextView tvVersion;
    @BindView(R2.id.about_us_tv_isNewest)
    TextView tvNewestVersion;
    private VersionIterationModel.VersionIterationEntity iterationEntity;
    private VersionIterationModel.VersionIterationEntity.NewInfoBean new_info;
    private BaseDialog checkUpdateDialog;
    private BaseDialog tipInstallPermissiondialog;

    @OnClick(R2.id.about_us_tv_Check_for_updates)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.about_us_tv_Check_for_updates) {
            if (iterationEntity != null && iterationEntity.isIs_iteration()) {
                new_info = iterationEntity.getNew_info();
                if (new_info != null) {
                    showCheckUpdateDialog(new_info);
                }
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.about_we));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_abount_wo;
    }

    @Override
    protected void initData() {

        String versionName = DeviceUtils.getVersionName(getApplicationContext());
        tvVersion.setText(String.format(getResources().getString(R.string.version_is_no), versionName));
        String cuPlatform = MarketUtil.getCuPlatform();
        getPresenter().versionIteration(versionName, cuPlatform);
    }

    @Override
    public void onVersionIteration(VersionIterationModel.VersionIterationEntity versionIterationEntity) {
        iterationEntity = versionIterationEntity;
        if (iterationEntity.isIs_iteration()) {
            // 需要更新
            if (tvNewestVersion != null) {
                tvNewestVersion.setText(String.valueOf(iterationEntity.getNew_info().getVersion()));
            }
        } else {
            // 已经是最新版本
            if (tvNewestVersion != null) {
                tvNewestVersion.setText(getResources().getString(R.string.is_latest_version));
            }
        }
    }

    @Override
    protected AbountWePresenter createPresenter() {
        return new AbountWePresenter();
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
                            Uri uri = Uri.parse("http://d.firim.pro/8mp4");    //设置跳转的网站
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
                startActivity(localIntent);
            }
        }
    }

    @Override
    protected void onDestroy() {
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
        super.onDestroy();
    }
}
