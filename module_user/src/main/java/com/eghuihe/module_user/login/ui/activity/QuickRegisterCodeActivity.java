package com.eghuihe.module_user.login.ui.activity;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.api.impl.UserServiceImpl;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.model.personal.GetMailCodeModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.activity.BaseTitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.FormatUtil;
import com.huihe.base_lib.utils.manager.LoginHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QuickRegisterCodeActivity extends BaseTitleActivity {

    public static final String KEY_NUMBER = "number";
    @BindView(R2.id.register_tv_get_code)
    TextView tvGetCode;
    @BindView(R2.id.register_et_enter_code)
    EditText etEnterCode;
    @BindView(R2.id.register_tv_code1)
    TextView tvCode1;
    @BindView(R2.id.register_tv_code2)
    TextView tvCode2;
    @BindView(R2.id.register_tv_code3)
    TextView tvCode3;
    @BindView(R2.id.register_tv_code4)
    TextView tvCode4;

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
    private List<TextView> textViews;
    private String number;

    @OnClick(R2.id.register_tv_get_code)
    public void OnViewClicked(View view) {

        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.register_tv_get_code) {
                countDownTimer.start();
                if (FormatUtil.isEmail(number)) {

                    UserServiceImpl.sendMail(number, "register", new NetworkSubscriber<GetMailCodeModel>(null) {
                        @Override
                        protected void onSuccess(GetMailCodeModel insertInfoResultModel) {

                        }
                    });
                } else {
                    UserServiceImpl.sendSms(number, "register", null, new NetworkSubscriber<InsertInfoResultModel>(null) {
                        @Override
                        protected void onSuccess(InsertInfoResultModel insertInfoResultModel) {

                        }
                    });
                }
            }
        }
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.register));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_register_code;
    }

    @Override
    protected void initData() {

        textViews = new ArrayList<>();
        textViews.add(tvCode1);
        textViews.add(tvCode2);
        textViews.add(tvCode3);
        textViews.add(tvCode4);

        countDownTimer.start();
        number = getIntent().getStringExtra(KEY_NUMBER);
        etEnterCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = s.toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    char[] chars = content.toCharArray();
                    for (int i = 0; i < 4; i++) {
                        if (i < chars.length) {
                            textViews.get(i).setText("●");
                        } else {
                            textViews.get(i).setText("");
                        }

                    }

                    if (chars.length == 4) {
                        UserServiceImpl.register(content, number, new NetworkSubscriber<RegisterUserInfoModel>(null) {
                            @Override
                            protected void onSuccess(RegisterUserInfoModel registerUserInfoModel) {
                                LoginHelper.saveRegisterInfo(registerUserInfoModel);
                                startActivity(QuickRegisterPwSettingctivity.class, QuickRegisterPwSettingctivity.KEY_LOGIN_NAME, number);
                            }
                        });
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        textViews.get(i).setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}