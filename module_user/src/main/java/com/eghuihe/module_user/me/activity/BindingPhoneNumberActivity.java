package com.eghuihe.module_user.me.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.google.android.material.textfield.TextInputEditText;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.UpdateBindingMobileEvent;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class BindingPhoneNumberActivity extends BaseTitleActivity {

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
                tvGetCode.setText(String.format(getResources().getString(R.string.twice_get_code_countdowntime),
                        String.valueOf(millisUntilFinished / 1000)));
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
    private LoginResultEntity loginResultEntity;

    @OnClick({
            R2.id.binding_phone_number_tv_finish,
            R2.id.binding_phone_number_tv_getCode
    })
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.binding_phone_number_tv_finish) {

                if (checkInput()) {
                    UserServiceImpl.updateUserMobile(loginResultEntity.getUserToken(), loginResultEntity.getUserInfoEntity().getUser_id(),
                            etPhoneNumber.getText().toString().trim(), etCode.getText().toString().trim(), new NetworkSubscriber<InsertInfoResultModel>(this) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    EventBus.getDefault().post(new UpdateBindingMobileEvent(etPhoneNumber.getText().toString()));
                                    finish();
                                }
                            });
                }
            } else if (view.getId() == R.id.binding_phone_number_tv_getCode) {
                if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
                    ToastUtils.showShortToast(this, getResources().getString(R.string.tip_enter_phone));
                    return;
                }
                countDownTimer.start();
                UserServiceImpl.sendSms(etPhoneNumber.getText().toString().trim(), null, null, new NetworkSubscriber<InsertInfoResultModel>(this) {
                    @Override
                    protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                        ToastUtils.showShortToast(BindingPhoneNumberActivity.this,
                                getResources().getString(R.string.Verification_code_sent_successfully));
                    }
                });
            }
        }
    }

    private boolean checkInput() {

        if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.tip_enter_phone));
            return false;
        }
        if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
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

        loginResultEntity = LoginHelper.getLoginInfo();
    }
}
