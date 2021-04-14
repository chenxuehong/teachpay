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
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class QuickRegisterPwSettingctivity extends BaseTitleActivity {
    public static final String KEY_LOGIN_NAME = "login_name";

    @BindView(R2.id.register_et_pw)
    EditText etPw;
    @BindView(R2.id.register_iv_pw_delete)
    ImageView ivPWDelete;
    @BindView(R2.id.register_et_twice_pw)
    EditText etTwicePw;
    @BindView(R2.id.register_iv_pw_twice_delete)
    ImageView ivTwicePWDelete;
    private String loginName;
    private RegisterUserInfoModel.RegisterUserInfoEntity registerUserInfoEntity;

    @OnClick({R2.id.register_tv_wansinfo})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.register_tv_wansinfo) {
                if (inputCheck()) {
                    UserServiceImpl.updateUser(
                            loginName,
                            registerUserInfoEntity.getUser_id(),
                            etPw.getText().toString().trim(),
                            true,
                            new NetworkSubscriber<InsertInfoResultModel>(null) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                            startActivity(EditUserInfo1Activity.class,
                                    EditUserInfo1Activity.KEY_LOGIN_NAME_AND_PW, loginName + "," + etPw.getText().toString());
                        }
                    });
                }
            }
        }
    }

    private boolean inputCheck() {
        if (TextUtils.isEmpty(etPw.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.please_enter_pw));
            return false;
        }
        if (TextUtils.isEmpty(etTwicePw.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.please_swice_enter_pw));
            return false;
        }
        if (!etPw.getText().toString().equals(etTwicePw.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.two_pw_are_inconsistent));
            return false;
        }
        return true;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.register));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_register_pw;
    }

    @Override
    protected void initData() {
        etPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ivPWDelete.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etTwicePw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ivTwicePWDelete.setVisibility(s.toString().trim().length() > 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginName = getIntent().getStringExtra(KEY_LOGIN_NAME);
        registerUserInfoEntity = LoginHelper.getRegisterUserInfoModel().getData();
    }
}