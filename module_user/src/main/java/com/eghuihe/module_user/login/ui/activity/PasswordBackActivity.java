package com.eghuihe.module_user.login.ui.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UserInfoModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class PasswordBackActivity extends BaseTitleActivity {

    private LoginResultEntity loginResultEntity;

    @BindView(R2.id.retrieve_password_iv_phone_number_checkState)
    ImageView ivPhoneNumberCheckState;
    @BindView(R2.id.retrieve_password_iv_email_checkState)
    ImageView ivEmailCheckState;
    @BindView(R2.id.retrieve_password_tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R2.id.retrieve_password_tv_email)
    TextView tvEmail;
    @BindView(R2.id.retrieve_password_tc_getCode)
    TextView tvGetCode;
    @BindView(R2.id.retrieve_password_et_code)
    EditText etCode;

    private boolean isPhoneChecked;
    private boolean isEmailChecked;
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

    @OnClick({R2.id.retrieve_password_iv_phone_number_checkState, R2.id.retrieve_password_tv_phone_number_checkState,
            R2.id.retrieve_password_iv_email_checkState, R2.id.retrieve_password_tv_email_checkState,
            R2.id.retrieve_password_ll_phone_number, R2.id.retrieve_password_ll_email,
            R2.id.retrieve_password_tc_getCode, R2.id.retrieve_password_tc_nextStep})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.retrieve_password_iv_phone_number_checkState
                || view.getId() == R.id.retrieve_password_tv_phone_number_checkState) {

            if (isEmailChecked) {
                ivEmailCheckState.setImageResource(R.mipmap.uncheck_state);
                isEmailChecked = false;
            }
            ivPhoneNumberCheckState.setImageResource(isPhoneChecked ? R.mipmap.uncheck_state : R.mipmap.check_state);
            isPhoneChecked = !isPhoneChecked;
        } else if (view.getId() == R.id.retrieve_password_iv_email_checkState
                || view.getId() == R.id.retrieve_password_tv_email_checkState) {
            if (isPhoneChecked) {
                ivPhoneNumberCheckState.setImageResource(R.mipmap.uncheck_state);
                isPhoneChecked = false;
            }
            ivEmailCheckState.setImageResource(isEmailChecked ? R.mipmap.uncheck_state : R.mipmap.check_state);
            isEmailChecked = !isEmailChecked;
        } else if (view.getId() == R.id.retrieve_password_ll_phone_number
                || view.getId() == R.id.retrieve_password_ll_email) {
            // 邮箱地址
        } else if (view.getId() == R.id.retrieve_password_tc_getCode) {
            if (!EventUtils.isFastDoubleClick(view.getId())) {
                countDownTimer.start();
                // 获取验证码
                if (isPhoneChecked) {
                    UserServiceImpl.sendSms(tvPhoneNumber.getText().toString(), null, null, new NetworkSubscriber<InsertInfoResultModel>(this) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                            ToastUtils.showShortToast(PasswordBackActivity.this, getResources().getString(R.string.Verification_code_sent_successfully));
                        }
                    });

                }
                if (isEmailChecked) {
                    UserServiceImpl.sendMail(tvEmail.getText().toString(), null, new NetworkSubscriber<GetMailCodeModel>(this) {
                        @Override
                        protected void onSuccess(GetMailCodeModel insertInfoResultModel) {

                            ToastUtils.showShortToast(PasswordBackActivity.this, getResources().getString(R.string.Verification_code_sent_successfully));
                        }
                    });
                }
            }
        } else if (view.getId() == R.id.retrieve_password_tc_nextStep) {
            if (!EventUtils.isFastDoubleClick(view.getId())) {
                if (!isPhoneChecked && !isEmailChecked) {
                    ToastUtils.showShortToast(this, getResources().getString(R.string.tip_selete_phone_or_email));
                    return;
                }

                if (TextUtils.isEmpty(etCode.getText().toString())) {

                    ToastUtils.showShortToast(this, getResources().getString(R.string.tip_enter_code));
                    return;
                }
                // 校验验证码
                if (isPhoneChecked) {

                    UserServiceImpl.validateSms(etCode.getText().toString(), tvPhoneNumber.getText().toString(), new NetworkSubscriber<InsertInfoResultModel>(this) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            startActivity(ResettingPayPasswordActivity.class, ResettingPayPasswordActivity.KEY_DATA,
                                    etCode.getText().toString());
                        }
                    });
                }
                if (isEmailChecked) {
                    UserServiceImpl.validateEmail(loginResultEntity.getUserToken(), etCode.getText().toString(), tvEmail.getText().toString(), new NetworkSubscriber<InsertInfoResultModel>(this) {

                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            startActivity(ResettingPayPasswordActivity.class, ResettingPayPasswordActivity.KEY_DATA,
                                    etCode.getText().toString());
                        }
                    });
                }
            }

        }

    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Retrieve_password));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    protected void initData() {

        loginResultEntity = LoginHelper.getLoginInfo();
        isPhoneChecked = false;
        isEmailChecked = false;
        UserServiceImpl.queryUserInfo(loginResultEntity.getUserInfoEntity().getUser_id(),
                new NetworkSubscriber<UserInfoModel>(null) {
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
        if (tvPhoneNumber != null) {
            tvPhoneNumber.setText(userInfoEntityBean.getMobile());
        }
        if (tvEmail != null) {
            tvEmail.setText(userInfoEntityBean.getMail());
        }
    }
}
