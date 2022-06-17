package com.huihe.base_lib.ui.service.binder;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huihe.base_lib.BuildConfig;
import com.huihe.base_lib.R;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.ApkVersion.AppInfo;
import com.huihe.base_lib.model.ApkVersion.NewInfo;
import com.huihe.base_lib.model.ApkVersion.VersionIterationInfo;
import com.huihe.base_lib.model.ApkVersion.VersionIterationModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.service.SelfUpdateService;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.utils.DeviceUtils;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.FileSizeUtil;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.MarketUtil;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.apk.UpdateDownloadListener;
import com.huihe.base_lib.utils.apk.UpdateManager;
import com.huihe.base_lib.utils.manager.AppManager;
import com.jay.daguerre.utils.UriUtils;

import java.io.File;
import java.io.IOException;

import androidx.core.app.ActivityCompat;
import io.reactivex.disposables.CompositeDisposable;

public class UpdateServiceBinder extends IUpdateServiceAidl.Stub implements IUpdateCallback {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AppInfo appInfo;
    private boolean appJustStarted;
    private boolean isFromAbout;
    private BaseDialog checkUpdateDialog;
    private BaseDialog tipInstallPermissiondialog;
    private View root;
    public static final int INSTALL_PACKAGES_REQUESTCODE = 101;
    private String filePath;
    private Context context;

    public void init(Context context){
        if (context==null) {
            throw new IllegalArgumentException("updateServiceBinder content is null");
        }
        this.context = context;
        String versionName = DeviceUtils.getVersionName(context);
        versionName = versionName.replace(".", "_");
        String dirPath = null;
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储

            dirPath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";
        } else {
            dirPath = context.getExternalCacheDir().getPath() + "/DCIM/Camera";
        }
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        filePath = dirPath.concat("/teachpay_").concat(versionName).concat(".apk");

    }

    @Override
    public void startSelfUpdate(boolean appJustStarted,boolean isFromAbout) throws RemoteException {
        this.appJustStarted = appJustStarted;
        this.isFromAbout = isFromAbout;
        getAppVersionInfo(this);
    }

    @Override
    public void onGetAppInfo(AppInfo appInfo) throws RemoteException {
        this.appInfo = appInfo;
        if (appInfo != null && appInfo.isIs_iteration()) {
            showUpdateDialog();
        }
    }

    /**
     * @desc 显示更新对话框
     */
    private void showUpdateDialog() {
        dismissCheckUpdateDialog();
        if (checkUpdateDialog == null) {
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
                            } else {
                                boolean goToAppMarket = MarketUtil.goToAppMarket(AppManager.getInstance().currentActivity());
                                if (!goToAppMarket) {
                                    checkIsAndroidO(getRoot());
                                }
                            }
                        }
                    });
                }

                @Override
                protected void initParams() {
                    TextView tvVersionInfo = (TextView) getView(R.id.dialog_apk_update_tv_versionInfo);
                    TextView tvTitle = (TextView) getView(R.id.dialog_apk_update_tv_title);
                    LinearLayout include_appupdate_1 = (LinearLayout) getView(R.id.include_appupdate_1);
                    LinearLayout include_appupdate_2 = (LinearLayout) getView(R.id.include_appupdate_2);
                    include_appupdate_1.setVisibility(View.VISIBLE);
                    include_appupdate_2.setVisibility(View.GONE);
                    root = getRoot();
                    tvTitle.setText(AppManager.getInstance().currentActivity().getResources().getString(R.string.New_version_found));
                    tvVersionInfo.setText(
                            String.format(AppManager.getInstance().currentActivity().getResources().getString(R.string.app_name_params),
                                    AppManager.getInstance().currentActivity().getResources().getString(R.string.application_name))
                                    + "\n"
                                    + String.format(AppManager.getInstance().currentActivity().getResources().getString(R.string.newest_version_params),
                                    TextUtils.isEmpty(appInfo.getVersion())?"":appInfo.getVersion())
                                    + "\n" +  (TextUtils.isEmpty(appInfo.getIteration_content())?"":appInfo.getIteration_content()));
                }
            };
            checkUpdateDialog.setPerWidth(246f / 375);
            checkUpdateDialog.setCancelOutside(false);
        }
        checkUpdateDialog.show();
    }

    /**
     * 上线调用
     */
    private void checkIsAndroidO(View root) {
        if (context!=null) {
            if (Build.VERSION.SDK_INT >= 26) {
                boolean b = context.getPackageManager().canRequestPackageInstalls();
                if (b) {
                    installApk(root);//安装应用的逻辑(写自己的就可以)
                } else {
                    showInstallPermissionDialog();
                }
            } else {
                installApk(root);
            }
        } else {
            throw new IllegalArgumentException("context cannot is null");
        }
    }

    private void showInstallPermissionDialog() {
        dismissTipInstallPermissiondialog();
        //请求安装未知应用来源的权限 安装应用需要打开未知来源权限，请去设置中开启权限
        if (tipInstallPermissiondialog == null) {
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
                            dismissTipInstallPermissiondialog();
                        }
                    }
            );
            try {
                if (tipInstallPermissiondialog != null && !tipInstallPermissiondialog.isShowing()) {
                    tipInstallPermissiondialog.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dismissCheckUpdateDialog() {
        try {
            if (checkUpdateDialog != null && checkUpdateDialog.isShowing()) {
                checkUpdateDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissTipInstallPermissiondialog() {
        try {
            if (tipInstallPermissiondialog != null && tipInstallPermissiondialog.isShowing()) {
                tipInstallPermissiondialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void installApk(final View root) {
        MPermission.with(AppManager.getInstance().currentActivity()).setPermission(
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}
        ).requestPermission(
                new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        if (appInfo != null)
                            startDownload(root);
                    }

                    @Override
                    public void valdateFail() {
                    }
                }
        );
    }

    private void startDownload(final View root) {
        final ProgressBar progressBar = (ProgressBar) root.findViewById(R.id.dialog_apk_updating_pb);
        final TextView tvApkSize = (TextView) root.findViewById(R.id.dialog_apk_updating_tv_size);
        final TextView tvProgress = (TextView) root.findViewById(R.id.dialog_apk_updating_tv_progress);
        final TextView tvStatus = (TextView) root.findViewById(R.id.dialog_apk_updating_tv_status);
        LinearLayout include_appupdate_1 = (LinearLayout) root.findViewById(R.id.include_appupdate_1);
        LinearLayout include_appupdate_2 = (LinearLayout) root.findViewById(R.id.include_appupdate_2);
        include_appupdate_1.setVisibility(View.GONE);
        include_appupdate_2.setVisibility(View.VISIBLE);
        root.findViewById(R.id.dialog_apk_updating_iv_close).setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                dismissCheckUpdateDialog();
            }
        });
        progressBar.setMax(100);
        UpdateManager.getInstance().startDownloads(appInfo.getUrl(), filePath, new UpdateDownloadListener() {

            @Override
            public void onProgressChanged(long length, int progress, String downloadUrl) {

                if (progressBar != null)
                    progressBar.setProgress(progress);
                if (tvApkSize != null) {
                    String apkTotalSize = FileSizeUtil.FormetFileSize(length);
                    String apkdownSize = FileSizeUtil.FormetFileSize(length * progress / 100);
                    tvApkSize.setText(apkdownSize + "/" + apkTotalSize);
                }
                if (tvProgress != null) {
                    tvProgress.setText(progress + "%");
                }
            }

            @Override
            public void onFinished(long completeSize, String downloadUrl) {
                if (progressBar != null)
                    progressBar.setProgress(100);
                if (tvApkSize != null) {
                    String apkTotalSize = FileSizeUtil.FormetFileSize(completeSize);
                    tvApkSize.setText(apkTotalSize + "/" + apkTotalSize);
                }
                if (tvProgress != null) {
                    tvProgress.setText(100 + "%");
                }
                // 下载完成进入安装界面
                getContentIntent();
            }

            @Override
            public void onFailure() {
                if (tvStatus != null) {
                    tvStatus.setText(root.getContext().getResources().getString(R.string.update_download_failed));
                }
            }
        });
    }

    /**
     * 进入apk安装程序
     *
     * @return
     */
    private PendingIntent getContentIntent() {

        File apkFile = new File(filePath);
        if (!apkFile.exists()) {
            try {
                apkFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = UriUtils.getUriForFile(context, apkFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        context.startActivity(intent);
        return pendingIntent;
    }

    public void startInstallApk()  {
        MPermission.with(AppManager.getInstance().currentActivity()).setPermission(
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}
        ).requestPermission(new MPermission.OnCallBack(){

            @Override
            public void valdateSuccess() {
                if (root != null && appInfo != null) {
                    startDownload(root);
                }
            }

            @Override
            public void valdateFail() {

            }
        });
    }

    @Override
    public void startApkInstallSetting(int requestCode, int[] grantResults) throws RemoteException {
        this.context = context;
        if (SelfUpdateService.INSTALL_PACKAGES_REQUESTCODE == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startInstallApk();
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

    @Override
    public void getAppVersionInfo(IUpdateCallback callback) throws RemoteException {
        if (context==null) {
            throw new IllegalArgumentException("context cannot is null");
        }
        String versionName = DeviceUtils.getVersionName(context.getApplicationContext());
        String cuPlatform = MarketUtil.getCuPlatform();
        compositeDisposable.add(
                UserServiceImpl.versionIteration(
                        versionName,
                        cuPlatform,
                        new NetworkSubscriber<VersionIterationModel>(null){
                            @Override
                            protected void onSuccess(VersionIterationModel versionIterationModel) {
                                VersionIterationInfo iterationEntity = versionIterationModel.getData();
                                if (iterationEntity != null
                                        && iterationEntity.getNew_info()!=null
                                        && !TextUtils.isEmpty(iterationEntity.getNew_info().getUrl())) {
                                    NewInfo appNewVerInfo = iterationEntity.getNew_info();
                                    if (callback!=null) {
                                        appInfo = new AppInfo();
                                        appInfo.setUrl(appNewVerInfo.getUrl());
                                        appInfo.setIs_iteration(iterationEntity.isIs_iteration());
                                        appInfo.setName(appNewVerInfo.getName());
                                        appInfo.setIteration_content(appNewVerInfo.getIteration_content());
                                        appInfo.setIs_new(appNewVerInfo.isIs_new());
                                        appInfo.setPlatform(appNewVerInfo.getPlatform());
                                    }

                                } else if (isFromAbout){
                                    ToastUtils.showShortToast(BaseApplication.getInstance(),
                                            AppManager.getInstance().currentActivity().getResources().getString(R.string.appupdate_datanull));
                                }
                            }

                            @Override
                            public void onComplete() {
                                super.onComplete();
                                try {
                                    if (callback!=null) {
                                        callback.onGetAppInfo(appInfo);
                                    }
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isFromAbout){
                                    ToastUtils.showShortToast(BaseApplication.getInstance(),
                                            AppManager.getInstance().currentActivity().getResources().getString(R.string.appupdate_neterror));
                                }
                            }

                            @Override
                            protected void onFail(Integer code, String message) {
                                super.onFail(code, message);
                                if (isFromAbout){
                                    ToastUtils.showShortToast(BaseApplication.getInstance(),
                                            AppManager.getInstance().currentActivity().getResources().getString(R.string.appupdate_loadfail));
                                }
                            }
                        }
                ));
    }

    public void unDo(){
        if (compositeDisposable!=null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
        UpdateManager.getInstance().stopDownload();
        appInfo = null;
        appJustStarted = false;
        isFromAbout = false;
    }

}
