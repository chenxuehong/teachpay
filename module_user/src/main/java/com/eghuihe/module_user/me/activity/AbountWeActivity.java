package com.eghuihe.module_user.me.activity;

import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.model.ApkVersion.AppInfo;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.service.SelfUpdateProvider;
import com.huihe.base_lib.ui.service.binder.IUpdateCallback;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DeviceUtils;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

public class AbountWeActivity extends BaseTitleActivity implements IUpdateCallback {

    private static final String TAG = AbountWeActivity.class.getSimpleName();
    @BindView(R2.id.about_us_tv_version)
    TextView tvVersion;
    @BindView(R2.id.about_us_tv_isNewest)
    TextView tvNewestVersion;

    @OnClick(R2.id.about_us_tv_Check_for_updates)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.about_us_tv_Check_for_updates) {
            SelfUpdateProvider.intance.startSelfUpdateService(this,false,true);
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
        SelfUpdateProvider.intance.getAppInfo(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SelfUpdateProvider.intance.startApkInstallSetting(requestCode,grantResults);
    }

    @Override
    public IBinder asBinder() {
        return null;
    }

    @Override
    public void onGetAppInfo(AppInfo appInfo) {
        try {
            if (appInfo!=null && appInfo.isIs_iteration()) {
                // 需要更新
                if (tvNewestVersion != null) {
                    String version = appInfo.getVersion();
                    if (!TextUtils.isEmpty(version)){
                        tvNewestVersion.setText(version);
                    }
                }
            } else {
                // 已经是最新版本
                if (tvNewestVersion != null) {
                    tvNewestVersion.setText(getResources().getString(R.string.is_latest_version));
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
