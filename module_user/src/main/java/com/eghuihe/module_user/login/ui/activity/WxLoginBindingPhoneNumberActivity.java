package com.eghuihe.module_user.login.ui.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.google.android.material.textfield.TextInputEditText;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.login.WxLoginModel;
import com.huihe.base_lib.model.login.WxLoginUserEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

import butterknife.BindView;
import butterknife.OnClick;

public class WxLoginBindingPhoneNumberActivity extends BaseTitleActivity {
    @BindView(R2.id.binding_phone_number_textInputEt_code)
    TextInputEditText etCode;

    @BindView(R2.id.binding_phone_number_textInputEt_phone_number)
    TextInputEditText etPhoneNumber;
    @BindView(R2.id.binding_phone_number_tv_getCode)
    TextView tvGetCode;

    CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            if (tvGetCode != null) {
                tvGetCode.setText(String.format(getResources().getString(R.string.twice_get_code_countdowntime), String.valueOf(millisUntilFinished / 1000)));
                tvGetCode.setEnabled(false);
            }
        }

        @Override
        public void onFinish() {
            if (tvGetCode != null) {
                tvGetCode.setText(getResources().getString(R.string.get_code));
                tvGetCode.setEnabled(true);
            }
        }
    };

    @OnClick({R2.id.binding_phone_number_tv_finish, R2.id.binding_phone_number_tv_getCode})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.binding_phone_number_tv_finish) {
                if (checkInput()) {
                    WxLoginUserEntity wxLoginUserEntity = LoginHelper.getmWxLoginUserEntity();
                    wxLogin(etPhoneNumber.getText().toString(), etCode.getText().toString(),
                            wxLoginUserEntity.getNickname(), wxLoginUserEntity.getHeadimgurl(), wxLoginUserEntity.getSex(), wxLoginUserEntity.getOpenid());
                }
            } else if (view.getId() == R.id.binding_phone_number_tv_getCode) {
                if (TextUtils.isEmpty(etPhoneNumber.getText().toString())) {
                    ToastUtils.showShortToast(this, getResources().getString(R.string.tip_enter_phone));
                    return;
                }
                countDownTimer.start();
                UserServiceImpl.sendSms(etPhoneNumber.getText().toString(), null, null, new NetworkSubscriber<InsertInfoResultModel>(this) {
                    @Override
                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                        ToastUtils.showShortToast(WxLoginBindingPhoneNumberActivity.this, getResources().getString(R.string.Verification_code_sent_successfully));
                    }
                });
            }
        }
    }

    private boolean checkInput() {

        if (TextUtils.isEmpty(etPhoneNumber.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.tip_enter_phone));
            return false;
        }
        if (TextUtils.isEmpty(etCode.getText().toString())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.tip_enter_code));
            return false;
        }
        return true;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Mobile_phone_binding));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_binding_phone_number;
    }

    @Override
    protected void initData() {

    }

    private void wxLogin(String login_name, String verification_code, String nick_name, String avatar, Integer sex, final String openid) {
        UserServiceImpl.wxLogin(
                login_name,
                verification_code,
                nick_name,
                avatar,
                sex,
                openid,
                true,
                new NetworkSubscriber<WxLoginModel>(null) {
                    @Override
                    protected void onSuccess(WxLoginModel wxLoginModel) {
                        WxLoginModel.WxLoginEntity data = wxLoginModel.getData();
                        LoginHelper.saveUserData(data);
                        loginIm();
                    }
                });
    }

    private void loginIm() {
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        if (loginInfo != null) {
            UserInfoEntity userInfoEntity = loginInfo.getUserInfoEntity();
            if (userInfoEntity != null) {
                TUIKit.login(
                        userInfoEntity.getUser_id(),
                        loginInfo.getUserSign(),
                        new IUIKitCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                startMain();
                            }

                            @Override
                            public void onError(String module, int errCode, String errMsg) {
                                ToastUtils.showShortToast(BaseApplication.getInstance(),
                                        "登录失败, errCode = ".concat(String.valueOf(errCode))
                                                .concat(", errInfo = ")
                                                .concat(errMsg));
                            }
                        }
                );
            }
        }
    }

    private void startMain() {
        EventBusUtils.sendEvent(new Event(EventAction.LOGIN_SUCCESS));
    }

}
