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
 * 手机号
 */
public class PhoneNumberActivity extends BaseTitleActivity {

    private LoginResultEntity loginResultEntity;

    @BindView(R2.id.phone_number_tv_number)
    TextView tvMobilePhone;

    @OnClick(R2.id.phone_number_tv_change_mobile)
   public void onViewClicked(View view){
        if (!EventUtils.isFastDoubleClick(view)){
            if (view.getId() == R.id.phone_number_tv_change_mobile){
                startActivity(BindingPhoneNumberActivity.class);
            }
        }
    }
    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Phone_Number));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_phone_number;
    }

    @Override
    protected void initData() {
        loginResultEntity = LoginHelper.getLoginInfo();
        UserServiceImpl.queryUserInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                new NetworkSubscriber<UserInfoModel>(this) {
                    @Override
                    protected void onSuccess(UserInfoModel userInfoModel) {
                        final UserInfoEntity userInfoEntityBean = userInfoModel.getData();
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
    }

    @Subscribe
    public void updateBindingMobile(UpdateBindingMobileEvent accountSecurityEvent){
        String phoneNumber = accountSecurityEvent.phoneNumber;
        if (tvMobilePhone!=null){
            tvMobilePhone.setText(phoneNumber);
        }
    }
}
