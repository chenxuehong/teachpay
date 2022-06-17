package com.eghuihe.module_user.login.ui.fragment;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.login.mvp.LoginContract;
import com.eghuihe.module_user.login.mvp.LoginPresenter;
import com.eghuihe.module_user.login.ui.activity.ForgetPasswordActivity;
import com.eghuihe.module_user.login.ui.activity.RegisterActivity;
import com.eghuihe.module_user.login.ui.activity.WxLoginBindingPhoneNumberActivity;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.login.WeiXinAccessTokenModel;
import com.huihe.base_lib.model.login.WxLoginUserEntity;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpFragment;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.emoji.ContainsEmojiEditText;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
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

public class LoginFragment extends BaseMvpFragment<LoginPresenter> implements LoginContract.View {


    private static final String TAG = LoginFragment.class.getSimpleName();
    @BindView(R2.id.login_et_number)
    ContainsEmojiEditText etNumber;
    @BindView(R2.id.login_iv_number_delete)
    ImageView ivNumberDelete;
    @BindView(R2.id.login_et_pw)
    EditText etEnterPw;
    @BindView(R2.id.login_iv_pw_delete)
    ImageView ivPWDelete;
    @BindView(R2.id.login_iv_pw_chakan)
    ImageView ivChakanPw;
    private Call call;
    private Call call2;

    @OnClick({R2.id.login_tv_login, R2.id.login_iv_weixin_login, R2.id.login_tv_code_login, R2.id.login_tv_fw, R2.id.login_tv_register})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.login_tv_login) {
                login();
            } else if (view.getId() == R.id.login_iv_weixin_login) {
                weixinlogin();
            } else if (view.getId() == R.id.login_tv_code_login) {
                EventBusUtils.sendEvent(new Event(EventAction.CODE_LOGIN));
            } else if (view.getId() == R.id.login_tv_fw) {
                startActivity(ForgetPasswordActivity.class);
            } else if (view.getId() == R.id.login_tv_register) {
                startActivity(RegisterActivity.class);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    private void login() {
        if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
            ToastUtils.showLongToast(getContext(), getResources().getString(R.string.tip_enter_phone_or_email));
            return;
        }
        if (TextUtils.isEmpty(etEnterPw.getText().toString().trim())) {
            ToastUtils.showLongToast(getContext(), getResources().getString(R.string.Please_input_password));
            return;
        }
        showProgress(getResources().getString(R.string.Logining));
        getPresenter().login(etNumber.getText().toString().trim(),
                etEnterPw.getText().toString().trim(),
                "1",
                true);
    }

    private void weixinlogin() {
        EventBusUtils.sendEvent(new Event(EventAction.WX_LOGIN));
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
                                startMain();
                            }

                            @Override
                            public void onError(String module, int errCode, String errMsg) {
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

    @Override
    protected void initView() {
        super.initView();
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
        etEnterPw.addTextChangedListener(new TextWatcher() {
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
        ivPWDelete.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                etEnterPw.setText("");
                ivPWDelete.setVisibility(View.GONE);
            }
        });
        ivChakanPw.setOnClickListener(new OnDoubleClickListener() {
            int iconid = R.mipmap.yincang;

            @Override
            public void onNoDoubleClick(View v) {
                iconid = iconid == R.mipmap.chakan ? R.mipmap.yincang : R.mipmap.chakan;
                ivChakanPw.setImageResource(iconid);
                if (iconid == R.mipmap.chakan) {
                    etEnterPw.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else {
                    etEnterPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etEnterPw.setSelection(etEnterPw.getText().length());
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
                getPresenter().wxOpenidAppVer(
                        openid,
                        true);
            }
        });
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
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onLoginSuccess() {
        LogUtils.i(TAG, getResources().getString(R.string.login_success));
        loginSuccess();
    }

    @Override
    public void onWxOpenidAppVerSuccess(LoginResultEntity wxOpenidAppVerEntity, String wx_openid) {
        if (wxOpenidAppVerEntity.isResult()) {
            startActivity(new Intent(getContext(), WxLoginBindingPhoneNumberActivity.class));
        } else {
            LoginHelper.saveUserData(wxOpenidAppVerEntity);
            loginSuccess();
        }
    }

    @Override
    public void onWxLoginSuccess() {
        loginSuccess();
    }
}
