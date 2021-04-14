package com.huihe.base_lib.ui.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.apk.UpdateDownloadListener;
import com.huihe.base_lib.utils.apk.UpdateManager;
import com.huihe.base_lib.ui.widget.dialog.BaseDialog;
import com.huihe.base_lib.utils.DeviceUtils;
import com.huihe.base_lib.utils.FileSizeUtil;
import com.huihe.base_lib.utils.manager.AppManager;

import java.io.File;
import java.io.IOException;

public class UpdateService extends Service {

    private String apkUrl;
    private String filePath;

    private BaseDialog baseDialog;

    public static final String KEY_APK_URL ="apkUrl";
    @Override
    public void onCreate() {

        String versionName = DeviceUtils.getVersionName(getApplicationContext());
        versionName = versionName.replace(".", "_");
        String dirPath = null;
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储

            dirPath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";
        } else {
            dirPath = getExternalCacheDir().getPath() + "/DCIM/Camera";
        }
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        filePath = dirPath.concat( "/teachpay_").concat(versionName).concat( ".apk");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent == null) {

            stopSelf();
        }
        apkUrl = intent.getStringExtra(KEY_APK_URL);

        startDownload();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        if (baseDialog != null) {
            baseDialog.dismiss();
        }
        baseDialog = new BaseDialog(AppManager.getInstance().currentActivity()) {
            @Override
            protected int getLayoutResId() {
                return R.layout.dialog_apk_updating;
            }

            @Override
            protected void initParams() {

                final ProgressBar progressBar = (ProgressBar) getView(R.id.dialog_apk_updating_pb);
                final TextView tvApkSize = (TextView) getView(R.id.dialog_apk_updating_tv_size);
                final TextView tvProgress = (TextView) getView(R.id.dialog_apk_updating_tv_progress);
                final TextView tvStatus = (TextView) getView(R.id.dialog_apk_updating_tv_status);
                getView(R.id.dialog_apk_updating_iv_close).setOnClickListener(new OnDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        stopSelf();
                        UpdateManager.getInstance().stopDownload();
                        dismiss();
                    }
                });
                progressBar.setMax(100);
                UpdateManager.getInstance().startDownloads(apkUrl, filePath, new UpdateDownloadListener() {

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
                        stopSelf();

                    }

                    @Override
                    public void onFailure() {

                        if (tvStatus != null) {
                            tvStatus.setText(getContext().getResources().getString(R.string.update_download_failed));
                        }
                        stopSelf();
                    }
                });
            }
        };
        baseDialog.setPerWidth(246f / 375);
        baseDialog.setCancelOutside(false);
        baseDialog.show();

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
        Uri uri = getUriForFile(this, apkFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        startActivity(intent);
        return pendingIntent;

    }

    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;

        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果SDK版本>=24，即：Build.VERSION.SDK_INT >= 24
            String packageName = context.getPackageName();
            uri = FileProvider.getUriForFile(context, packageName + ".daguerre.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
