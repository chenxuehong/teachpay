package com.eghuihe.module_user.login.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class ResettingLoginPasswordActivity extends BaseTitleActivity {

    public static final String KEY_DATA = "KEY_DATA";

    @BindView(R2.id.set_password_et_password)
    EditText etPassword;
    @BindView(R2.id.set_password_et_twice_password)
    EditText etTwicePassword;
    @BindView(R2.id.set_password_iv_delete_password)
    ImageView ivDeletePassword;
    @BindView(R2.id.set_password_iv_delete_twice_password)
    ImageView ivDeleteTwicePassword;
    private String code;
    private String number;

    @OnClick({R2.id.set_password_tv_change_password, R2.id.set_password_tv_forget_password,
            R2.id.set_password_iv_delete_password, R2.id.set_password_iv_delete_twice_password})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            if (view.getId() == R.id.set_password_tv_change_password) {
                if (inputCheck()) {
                    UserServiceImpl.updateUserLoginPass(
                            number,
                            code,
                            etPassword.getText().toString().trim(),
                            true,
                            new NetworkSubscriber<InsertInfoResultModel>(this) {
                                @Override
                                protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                    startActivity(LoginActivity.class);
                                    AppManager.getInstance().finishActivity(ForgetPasswordActivity.class);
                                    AppManager.getInstance().finishActivity(ResettingLoginPasswordActivity.class);
                                }
                            });
                }
            } else if (view.getId() == R.id.set_password_tv_forget_password) {
                startActivity(PasswordBackActivity.class);
            } else if (view.getId() == R.id.set_password_iv_delete_password) {
                etPassword.setText("");
                etPassword.setVisibility(View.GONE);
            } else if (view.getId() == R.id.set_password_iv_delete_twice_password) {
                etTwicePassword.setText("");
                etTwicePassword.setVisibility(View.GONE);
            }
        }
    }

    private boolean inputCheck() {
        if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.please_enter_pw));
            return false;
        }
        if (TextUtils.isEmpty(etTwicePassword.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.please_swice_enter_pw));
            return false;
        }
        if (!etPassword.getText().toString().equals(etTwicePassword.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.two_pw_are_inconsistent));
            return false;
        }
        return true;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Set_Login_password));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initView() {
        super.initView();
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ivDeletePassword.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTwicePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ivDeleteTwicePassword.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        String data = getIntent().getStringExtra(KEY_DATA);
        String[] split = data.split(",");
        code = split[0];
        number = split[1];
    }
}
