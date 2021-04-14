package com.eghuihe.module_user.me.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.login.ui.activity.PasswordBackActivity;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置支付密码
 */
public class SetPasswordActivity extends BaseTitleActivity {

    @BindView(R2.id.set_password_et_password)
    EditText etPassword;
    @BindView(R2.id.set_password_et_twice_password)
    EditText etTwicePassword;
    @BindView(R2.id.set_password_iv_delete_password)
    ImageView ivDeletePassword;
    @BindView(R2.id.set_password_iv_delete_twice_password)
    ImageView ivDeleteTwicePassword;
    private LoginResultEntity loginResultEntity;

    @OnClick({R2.id.set_password_tv_change_password,
            R2.id.set_password_tv_forget_password,
            R2.id.set_password_iv_delete_password,
            R2.id.set_password_iv_delete_twice_password})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            if (view.getId() == R.id.set_password_tv_change_password) {
                // 修改密码
                UserServiceImpl.updateUserPayPass(
                        loginResultEntity.getUserInfoEntity().getUser_id(),
                        etPassword.getText().toString().trim(), null, new NetworkSubscriber<InsertInfoResultModel>(this) {
                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                finish();
                            }
                        });
            } else if (view.getId() == R.id.set_password_tv_forget_password) {
                // 忘记密码
                startActivity(PasswordBackActivity.class);
            } else if (view.getId() == R.id.set_password_iv_delete_password) {
                // 清空密码
                etPassword.setText("");
                etPassword.setVisibility(View.GONE);
            } else if (view.getId() == R.id.set_password_iv_delete_twice_password) {
                // 清空重复密码
                etTwicePassword.setText("");
                etTwicePassword.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.Set_payment_password));
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

        loginResultEntity = LoginHelper.getLoginInfo();
    }
}
