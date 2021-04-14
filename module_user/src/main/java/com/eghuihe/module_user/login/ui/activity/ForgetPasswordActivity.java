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
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.FormatUtil;
import com.huihe.base_lib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseTitleActivity {

    @BindView(R2.id.forget_password_et_number)
    EditText etNumber;
    @BindView(R2.id.forget_password_iv_number_delete)
    ImageView ivNumberDelete;
    @BindView(R2.id.forget_password_et_code)
    EditText etEnterCode;
    @BindView(R2.id.forget_password_iv_code_delete)
    ImageView ivcodeDelete;
    @BindView(R2.id.forget_password_tv_code_get)
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

    @OnClick({R2.id.forget_password_tv_code_get, R2.id.forget_password__tv_nextStep})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.forget_password_tv_code_get) {
                if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
                    ToastUtils.showLongToast(this, getResources().getString(R.string.tip_enter_phone_or_email));
                    return;
                }

                countDownTimer.start();
                if (FormatUtil.isEmail(etNumber.getText().toString().trim())) {

                    UserServiceImpl.sendMail(etNumber.getText().toString().trim(), null, new NetworkSubscriber<GetMailCodeModel>(this) {
                        @Override
                        protected void onSuccess(GetMailCodeModel insertInfoResultModel) {
                            ToastUtils.showShortToast(ForgetPasswordActivity.this, getResources().getString(R.string.Verification_code_sent_successfully));
                        }
                    });
                } else {
                    UserServiceImpl.sendSms(etNumber.getText().toString().trim(), null, null, new NetworkSubscriber<InsertInfoResultModel>(this) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            ToastUtils.showShortToast(ForgetPasswordActivity.this, getResources().getString(R.string.Verification_code_sent_successfully));
                        }
                    });
                }
            } else if (view.getId() == R.id.forget_password__tv_nextStep) {
                if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
                    ToastUtils.showLongToast(this, getResources().getString(R.string.tip_enter_phone_or_email));
                    return;
                }
                if (TextUtils.isEmpty(etEnterCode.getText().toString().trim())) {
                    ToastUtils.showLongToast(this, getResources().getString(R.string.tip_enter_code));
                    return;
                }
                if (FormatUtil.isEmail(etNumber.getText().toString().trim())) {
                    UserServiceImpl.validateEmail(null, etEnterCode.getText().toString().trim(), etNumber.getText().toString().trim(), new NetworkSubscriber<InsertInfoResultModel>(this) {

                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            startActivity(ResettingLoginPasswordActivity.class, ResettingLoginPasswordActivity.KEY_DATA,
                                    etEnterCode.getText().toString().trim() + "," + etNumber.getText().toString().trim());
                        }
                    });
                } else {
                    UserServiceImpl.validateSms(etEnterCode.getText().toString().trim(), etNumber.getText().toString().trim(), new NetworkSubscriber<InsertInfoResultModel>(this) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                            startActivity(ResettingLoginPasswordActivity.class, ResettingLoginPasswordActivity.KEY_DATA,
                                    etEnterCode.getText().toString().trim() + "," + etNumber.getText().toString().trim());
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Forget_Password));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initData() {

    }
}
