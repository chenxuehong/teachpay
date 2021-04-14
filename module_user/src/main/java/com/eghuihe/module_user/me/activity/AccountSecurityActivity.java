package com.eghuihe.module_user.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.UpdateBindingMobileEvent;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 账户和安全
 */
public class AccountSecurityActivity extends BaseTitleActivity {

    private LoginResultEntity loginResultEntity;

    @BindView(R2.id.account_security_tv_mobile_phone)
    TextView tvMobilePhone;
    @BindView(R2.id.account_security_tv_email)
    TextView tvEmail;

    @OnClick({R2.id.account_security_ll_mobile_phone,
            R2.id.account_security_ll_set_password,
            R2.id.account_security_ll_email})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.account_security_ll_mobile_phone) {
                // 绑定手机
                startActivity(PhoneNumberActivity.class);
            } else if (view.getId() == R.id.account_security_ll_set_password) {
                startActivity(SetPasswordActivity.class);
            } else if (view.getId() == R.id.account_security_ll_email) {
//                startActivity(EmailActivity.class);
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.account_and_anquan));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_account_security;
    }

    @Override
    protected void initData() {

        loginResultEntity = LoginHelper.getLoginInfo();
        UserServiceImpl.queryUserInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                new NetworkSubscriber<UserInfoModel>(this) {
                    @Override
                    protected void onSuccess(UserInfoModel userInfoModel) {
                        UserInfoEntity userInfoEntityBean = userInfoModel.getData();
                        if (userInfoEntityBean != null) {
                            BindData(userInfoEntityBean);
                        }
                    }
                });
    }

    private void BindData(UserInfoEntity userInfoEntityBean) {
        if (!TextUtils.isEmpty(userInfoEntityBean.getMobile())) {
            tvMobilePhone.setText(userInfoEntityBean.getMobile());
        }
        if (!TextUtils.isEmpty(userInfoEntityBean.getMail())) {
            tvEmail.setText(userInfoEntityBean.getMail());
        }
    }

    @Subscribe
    public void updateBindingMobile(UpdateBindingMobileEvent accountSecurityEvent) {
        String phoneNumber = accountSecurityEvent.phoneNumber;
        if (tvMobilePhone != null) {
            tvMobilePhone.setText(phoneNumber);
        }
    }
}
