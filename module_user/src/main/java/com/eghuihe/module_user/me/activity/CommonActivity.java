package com.eghuihe.module_user.me.activity;

import android.view.View;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;

import butterknife.OnClick;

public class CommonActivity extends BaseTitleActivity {

    @OnClick(R2.id.common_tv_photo_video_and_files)
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.common_tv_photo_video_and_files) {
                startActivity(AutoDownloadUnderTrafficActivity.class);
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.common));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initData() {

    }
}
