package com.eghuihe.module_user.login.ui.fragment;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.login.mvp.RegisterContract;
import com.eghuihe.module_user.login.mvp.RegisterPresenter;
import com.eghuihe.module_user.login.ui.activity.QuickRegisterPwSettingctivity;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.FormatUtil;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseMvpFragment<RegisterPresenter> implements RegisterContract.View {

    @BindView(R2.id.register_et_number)
    EditText etNumber;
    @BindView(R2.id.register_iv_number_delete)
    ImageView ivNumberDelete;
    @BindView(R2.id.register_et_code)
    EditText etCode;
    @BindView(R2.id.register_iv_code_delete)
    ImageView ivDeleteCode;
    @BindView(R2.id.register_code)
    TextView tvGetCode;

    @OnClick({
            R2.id.register_code,
            R2.id.register_tv_nextStep,
            R2.id.register_code_privacy_policy
    })
    public void OnViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.register_code_privacy_policy) {
                Intent intent = new Intent(getContext(), H5TitleActivity.class);
                intent.putExtra(H5TitleActivity.KET_URL,
                        DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.PRIVACY, AppConfigs.Cooperation.EN.PRIVACY));
                intent.putExtra(H5TitleActivity.KET_TITLE, "教付保隐私权政策");
                startActivity(intent);
            } else if (view.getId() == R.id.register_code) {
                // 获取验证码
                if (checkInputForGetCode()) {
                    countDownTimer.start();
                    String loginName = etNumber.getText().toString().trim();
                    if (FormatUtil.isEmail(loginName)) {
                        getPresenter().sendMail(loginName, "register");
                    } else {
                        getPresenter().sendSms(loginName, "register");
                    }
                }
            } else if (view.getId() == R.id.register_tv_nextStep) {
                // 下一步
                if (checkInput()) {
                    getPresenter().register(
                            etCode.getText().toString().trim(),
                            etNumber.getText().toString().trim());
                }
            }
        }
    }

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

    private boolean checkInput() {
        if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
            ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_phone_or_email));
            return false;
        }
        if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
            ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_code));
            return false;
        }
        return true;
    }

    private boolean checkInputForGetCode() {
        if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
            ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_phone_or_email));
            return false;
        }
        return true;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        super.initData();
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ivDeleteCode.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivDeleteCode.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                etCode.setText("");
                ivDeleteCode.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showSendCodeSuccess() {
        ToastUtils.showShortToast(getContext(),
                getResources().getString(R.string.Verification_code_sent_successfully));
    }

    @Override
    public void onRegisterSuccess(RegisterUserInfoModel registerUserInfoEntity) {
        LoginHelper.saveRegisterInfo(registerUserInfoEntity);
        startActivity(QuickRegisterPwSettingctivity.class, QuickRegisterPwSettingctivity.KEY_LOGIN_NAME, etNumber.getText().toString().trim());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
