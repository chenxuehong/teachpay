package com.eghuihe.module_user.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;


public class PrivacyActivity extends BaseTitleActivity {

    public static final String KEY_USER_INFO = "KEY_USER_INFO";
    @BindView(R2.id.newsNofity_switch_contact_me_through_phone)
    Switch switchContactMeThroughPhone;
    @BindView(R2.id.newsNofity_switch_contact_me_through_id)
    Switch switchContactMeThroughID;
    @BindView(R2.id.newsNofity_switch_contact_me_through_nick)
    Switch switchContactMeThroughNick;
    @BindView(R2.id.newsNofity_switch_contact_me_through_email)
    Switch switchContactMeThroughEmail;
    private LoginResultEntity loginResultEntity;
    private UserInfoEntity userInfoEntityBean;

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.privacy));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_privacy;
    }

    @Override
    protected void initView() {
        super.initView();
        switchContactMeThroughPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                UserServiceImpl.updateUserPrivacyInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                        loginResultEntity.getUserToken(), isChecked, null, null,
                        null, new NetworkSubscriber<InsertInfoResultModel>(PrivacyActivity.this) {

                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                if (userInfoEntityBean != null)
                                    userInfoEntityBean.setIs_mobile(isChecked);
                            }
                        });
            }
        });
        switchContactMeThroughID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                UserServiceImpl.updateUserPrivacyInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                        loginResultEntity.getUserToken(), null, isChecked, null, null,
                        new NetworkSubscriber<InsertInfoResultModel>(PrivacyActivity.this) {

                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                if (userInfoEntityBean != null)
                                    userInfoEntityBean.setIs_id(isChecked);
                            }
                        });
            }
        });
        switchContactMeThroughNick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                UserServiceImpl.updateUserPrivacyInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                        loginResultEntity.getUserToken(), null, null, isChecked, null,
                        new NetworkSubscriber<InsertInfoResultModel>(PrivacyActivity.this) {

                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                if (userInfoEntityBean != null)
                                    userInfoEntityBean.setIs_name(isChecked);
                            }
                        });
            }
        });
        switchContactMeThroughEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserServiceImpl.updateUserPrivacyInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                        loginResultEntity.getUserToken(), null, null, null, isChecked,
                        new NetworkSubscriber<InsertInfoResultModel>(PrivacyActivity.this) {

                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                if (userInfoEntityBean != null)
                                    userInfoEntityBean.setIs_mail(isChecked);
                            }
                        });
            }
        });
    }

    @Override
    protected void initData() {

        loginResultEntity = LoginHelper.getLoginInfo();
        userInfoEntityBean = getIntentData(KEY_USER_INFO, UserInfoEntity.class);
        if (userInfoEntityBean != null) {
            switchContactMeThroughPhone.setChecked(userInfoEntityBean.isIs_mobile());
            switchContactMeThroughID.setChecked(userInfoEntityBean.isIs_id());
            switchContactMeThroughNick.setChecked(userInfoEntityBean.isIs_name());
            switchContactMeThroughEmail.setChecked(userInfoEntityBean.isIs_mail());
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("userInfoEntityBean", JsonUtil.toJson(userInfoEntityBean)); //放置要传出的数据
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }
}
