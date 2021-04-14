package com.eghuihe.module_user.login.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.FormatUtil;
import com.huihe.base_lib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseTitleActivity {

    @BindView(R2.id.register_et_number)
    EditText etNumber;
    @BindView(R2.id.register_iv_number_delete)
    ImageView ivNumberDelete;
    @BindView(R2.id.register_code)
    TextView tvGetCode;

    @OnClick({
            R2.id.register_code,
            R2.id.register_code_privacy_policy
    })
    public void OnViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.register_code_privacy_policy) {
                Intent intent = new Intent(this, H5TitleActivity.class);
                intent.putExtra(H5TitleActivity.KET_URL,
                        DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.PRIVACY, AppConfigs.Cooperation.EN.PRIVACY));
                intent.putExtra(H5TitleActivity.KET_TITLE, getResources().getString(R.string.Tmore_Privacy_Policy));
                startActivity(intent);
            } else if (view.getId() == R.id.register_code) {
                // 获取验证码
                if (checkInput()) {
                    String loginName = etNumber.getText().toString().trim();
                    if (FormatUtil.isEmail(etNumber.getText().toString().trim())) {

                        UserServiceImpl.sendMail(loginName, "register", new NetworkSubscriber<GetMailCodeModel>(null) {
                            @Override
                            protected void onSuccess(GetMailCodeModel insertInfoResultModel) {

                                ToastUtils.showShortToast(RegisterActivity.this,
                                        getResources().getString(R.string.Verification_code_sent_successfully));
                            }
                        });
                    } else {
                        UserServiceImpl.sendSms(loginName, "register", null, new NetworkSubscriber<InsertInfoResultModel>(null) {
                            @Override
                            protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {
                                ToastUtils.showShortToast(RegisterActivity.this,
                                        getResources().getString(R.string.Verification_code_sent_successfully));
                            }
                        });
                    }
                    startActivity(QuickRegisterCodeActivity.class, QuickRegisterCodeActivity.KEY_NUMBER, etNumber.getText().toString());
                }
            }
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
            ToastUtils.showLongToast(this, getResources().getString(R.string.tip_enter_phone_or_email));
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
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }
}
