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
import com.eghuihe.module_user.login.mvp.CodeLoginContract;
import com.eghuihe.module_user.login.mvp.CodeLoginPresenter;
import com.eghuihe.module_user.login.ui.activity.ForgetPasswordActivity;
import com.eghuihe.module_user.login.ui.activity.RegisterActivity;
import com.eghuihe.module_user.login.ui.activity.WxLoginBindingPhoneNumberActivity;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.login.WeiXinAccessTokenModel;
import com.huihe.base_lib.model.login.WxLoginModel;
import com.huihe.base_lib.model.login.WxLoginUserEntity;
import com.huihe.base_lib.model.login.WxOpenidAppVerModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.FormatUtil;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CodeLoginFragment extends BaseMvpFragment<CodeLoginPresenter> implements CodeLoginContract.View {

    private static final String TAG = CodeLoginFragment.class.getSimpleName();
    @BindView(R2.id.code_login_et_number)
    EditText etNumber;
    @BindView(R2.id.code_login_iv_number_delete)
    ImageView ivNumberDelete;
    @BindView(R2.id.code_login_et_code)
    EditText etEnterCode;
    @BindView(R2.id.code_login_iv_code_delete)
    ImageView ivcodeDelete;
    @BindView(R2.id.code_login_tv_code_get)
    TextView tvGetCode;

    private Call call;

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
    private Call call2;

    @OnClick({R2.id.code_login_tv_code_get,
            R2.id.code_login_tv_login,
            R2.id.code_login_tv_register,
            R2.id.code_login_iv_weixin_login,
            R2.id.code_login_tv_code_login,
            R2.id.code_login_tv_fw})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.code_login_tv_code_get) {
                if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
                    ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_phone_or_email));
                    return;
                }
                countDownTimer.start();
                if (FormatUtil.isEmail(etNumber.getText().toString().trim())) {
                    getPresenter().sendMail(etNumber.getText().toString().trim(), "login");
                } else {
                    getPresenter().sendSms(etNumber.getText().toString().trim(), "login");
                }
            } else if (view.getId() == R.id.code_login_tv_login) {

                if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
                    ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_phone_or_email));
                    return;
                }
                if (TextUtils.isEmpty(etEnterCode.getText().toString().trim())) {
                    ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_code));
                    return;
                }
                // 登录
                showProgress(getResources().getString(R.string.Logining));
                getPresenter().codeLogin(etNumber.getText().toString().trim(),
                        etEnterCode.getText().toString().trim(),true);
            } else if (view.getId() == R.id.code_login_tv_register) {
                startActivity(RegisterActivity.class);
            } else if (view.getId() == R.id.code_login_iv_weixin_login) {
                // 微信登录
                weixinlogin();
            } else if (view.getId() == R.id.code_login_tv_fw) {
                // 忘记密码
                startActivity(ForgetPasswordActivity.class);
            } else if (view.getId() == R.id.code_login_tv_code_login) {
                // 密码登录
                EventBusUtils.sendEvent(new Event(EventAction.PASSWORD_LOGIN));
            }
        }
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    private void weixinlogin() {

        EventBusUtils.sendEvent(new Event(EventAction.WX_LOGIN));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_code_login;
    }

    @Override
    protected void initData() {
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ivNumberDelete.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivNumberDelete.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                etNumber.setText("");
                ivNumberDelete.setVisibility(View.GONE);
            }
        });
        etEnterCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ivcodeDelete.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivcodeDelete.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                etEnterCode.setText("");
                ivcodeDelete.setVisibility(View.GONE);
            }
        });
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.WX_LOGIN_ACCESSTOKEN.equals(event.getAction())) {
            Object data = event.getData();
            if (data instanceof String) {
                String code = (String) data;
                getAccessToken(code);
            }
        }
    }

    //获取access_token
    private void getAccessToken(final String code) {
        //这个接口需用get请求
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AppConfigs.WeiXinPay.APPID + "&secret="
                + AppConfigs.WeiXinPay.AppSecret + "&code=" + code + "&grant_type=authorization_code";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(path)
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, "onFailure: 失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                WeiXinAccessTokenModel.WeiXinAccessTokenEntity tokenEntity = JsonUtil.fromJson(result, WeiXinAccessTokenModel.WeiXinAccessTokenEntity.class);
                LogUtils.d(TAG, "请求微信服务器成功: " + result);
                getUserInfo(tokenEntity.getAccess_token(), tokenEntity.getOpenid());
            }
        });
    }

    private void getUserInfo(String accessToken, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();
        call2 = client.newCall(request);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, "onFailure: userinfo" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                WxLoginUserEntity wxLoginUserEntity = JsonUtil.fromJson(json, WxLoginUserEntity.class);
                LoginHelper.setWxUserInfo(wxLoginUserEntity);
                wxOpenidAppVerLogin(openid);
            }
        });

    }

    private void wxOpenidAppVerLogin(final String openid) {
        if (TextUtils.isEmpty(openid)) {
            return;
        }
        getPresenter().wxOpenidAppVer(openid,true);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (call != null) {
            call.cancel();
        }
        if (call2 != null) {
            call2.cancel();
        }
    }

    @Override
    protected CodeLoginPresenter createPresenter() {
        return new CodeLoginPresenter();
    }

    @Override
    public void onSendSuccess() {
        ToastUtils.showShortToast(getContext(), getResources().getString(R.string.Verification_code_sent_successfully));
    }

    @Override
    public void onLoginSuccess(LoginResultEntity loginResultEntity) {
        LoginHelper.saveUserData(loginResultEntity);
        loginSuccess();
    }

    @Override
    public void onWxOpenidAppVer(LoginResultEntity wxOpenidAppVerEntity, String wx_openid) {
        if (wxOpenidAppVerEntity.isResult()) {
            startActivity(new Intent(getContext(), WxLoginBindingPhoneNumberActivity.class));
        } else {
            LoginHelper.saveUserData(wxOpenidAppVerEntity);
            loginSuccess();
        }
    }

    @Override
    public void onWxLogin(WxLoginModel.WxLoginEntity wxLoginEntity) {
        LoginHelper.saveUserData(wxLoginEntity);
        loginSuccess();
    }

    private void loginSuccess() {
        loginIm();
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
                                closeLoading();
                                startMain();
                            }

                            @Override
                            public void onError(String module, int errCode, String errMsg) {
                                closeLoading();
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
        ToastUtils.showSuccess(getResources().getString(R.string.login_suc));
        EventBusUtils.sendEvent(new Event(EventAction.LOGIN_SUCCESS));
    }
}
