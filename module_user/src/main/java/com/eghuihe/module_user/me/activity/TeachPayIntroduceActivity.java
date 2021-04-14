package com.eghuihe.module_user.me.activity;

import android.view.View;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.widget.title.CommonTitle;
import com.huihe.base_lib.ui.widget.title.OnCommonTitleListener;

import butterknife.BindView;
import butterknife.OnClick;

public class TeachPayIntroduceActivity extends BaseActivity {

    @BindView(R2.id.activity_teachpay_introduce_title)
    CommonTitle commonTitle;

    @OnClick({
            R2.id.activity_teachpay_introduce_iv_activity
    })
    public void onViewClicked(View view) {
        if (view.getId() == R.id.activity_teachpay_introduce_iv_activity) {
            // 去活动现场
        }
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.color_FF7300;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teach_pay_introduce;
    }

    @Override
    protected void initData() {
        commonTitle.setOnCommonTitleListener(new OnCommonTitleListener() {
            @Override
            public void onLeftTitleClicked(View view) {
                finish();
            }
        });
    }
}
