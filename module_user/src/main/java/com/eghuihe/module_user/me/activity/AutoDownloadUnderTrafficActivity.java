package com.eghuihe.module_user.me.activity;

import android.widget.CompoundButton;
import android.widget.Switch;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;

public class AutoDownloadUnderTrafficActivity extends BaseTitleActivity {

    @BindView(R2.id.newsNofity_switch_auto_download_under_wifi)
    Switch switchAutoDownloadUnderWifi;
    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.photo_video_and_files));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_auto_download_under_traffic;
    }

    @Override
    protected void initView() {
        super.initView();
        switchAutoDownloadUnderWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginHelper.setAutoDownloadUnderWifi(isChecked);
            }
        });
    }

    @Override
    protected void initData() {
       switchAutoDownloadUnderWifi.setChecked(LoginHelper.getAutoDownloadUnderWifi());
    }
}
