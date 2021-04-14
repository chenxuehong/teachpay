package com.eghuihe.module_user.login.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.login.mvp.EditUserInfo1Contract;
import com.eghuihe.module_user.login.mvp.EditUserInfo1Presenter;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.login.RegisterUserInfoModel;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.emoji.ContainsEmojiEditText;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class EditUserInfo1Activity extends BaseMvpTitleActivity<EditUserInfo1Presenter>
        implements EditUserInfo1Contract.View {

    public static final String KEY_LOGIN_NAME_AND_PW = "loginNameAndPw";
    @BindView(R2.id.edit_userinfo_1_iv_head)
    CircleImageView ivHead;
    @BindView(R2.id.edit_userinfo_1_et_nick)
    ContainsEmojiEditText etNick;
    @BindView(R2.id.edit_userinfo_1_tv_sex)
    TextView tvSex;
    @BindView(R2.id.edit_userinfo_1_tv_birthday)
    TextView tvBirthday;
    @BindView(R2.id.edit_userinfo_1_et_invite_code)
    EditText etInviteCode;

    // 需要上传的用户信息
    private String headPath;
    private int sex = 1;
    private String birth;
    private RegisterUserInfoModel.RegisterUserInfoEntity registerUserInfoEntity;
    private String loginName;
    private String pw;

    @OnClick({
            R2.id.edit_userinfo_1_iv_head,
            R2.id.edit_userinfo_1_ll_sex,
            R2.id.edit_userinfo_1_ll_birthday,
            R2.id.edit_userinfo_1_tv_enter,
            R2.id.edit_userinfo_1_tv_next_step})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.edit_userinfo_1_iv_head) {
                PhotoSelectUtils.selectPic(EditUserInfo1Activity.this, new PhotoSelectUtils.OnCallBack() {
                    @Override
                    protected void updateLoadImage(String path) {

                        showUploading();
                        new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".png", new QiNiuUtils.OnUploadListener() {
                            @Override
                            public void onUploadFinish(String picUrl) {
                                headPath = picUrl;
                                GlideTools.loadImage(EditUserInfo1Activity.this, path, ivHead);
                                closeLoading();
                            }

                            @Override
                            public void onUploadFail(int code, String error) {
                                closeLoading();
                            }
                        });

                    }
                });
            } else if (view.getId() == R.id.edit_userinfo_1_ll_sex) {
                View rootview = getWindow().getDecorView().getRootView();
                CustomPopupWindow customPopupWindow = PopWindowUtils.popBottomDialog(rootview, EditUserInfo1Activity.this, R.layout.select_sex, true);
                customPopupWindow.setOnClickListener(R.id.select_sex_nan, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sex = 1;
                        tvSex.setText(getResources().getString(R.string.sex_nan));
                        customPopupWindow.dismiss();
                    }
                });
                customPopupWindow.setOnClickListener(R.id.select_sex_nv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sex = 2;
                        tvSex.setText(getResources().getString(R.string.sex_nv));
                        customPopupWindow.dismiss();
                    }
                });
                customPopupWindow.setOnClickListener(R.id.select_sex_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customPopupWindow.dismiss();
                    }
                });
            } else if (view.getId() == R.id.edit_userinfo_1_ll_birthday) {
                DatePicker datePicker = new DatePicker(EditUserInfo1Activity.this, DatePicker.YEAR_MONTH_DAY);
                datePicker.setLabel(getResources().getString(R.string.year), getResources().getString(R.string.month), getResources().getString(R.string.day));
                datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {

                        birth = new StringBuilder().append(year).append("-").append(month).append("-").append(day).toString();
                        tvBirthday.setText(birth);
                    }
                });
                datePicker.setRangeStart(DateUtils.getCurYear() - 100, 1, 1);
                datePicker.setRangeEnd(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
                datePicker.setSelectedItem(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
                datePicker.setResetWhileWheel(false);
                datePicker.show();
            } else if (view.getId() == R.id.edit_userinfo_1_tv_enter) {
                getPresenter().login(
                        loginName,
                        pw,
                        "1",
                        true
                );
            } else if (view.getId() == R.id.edit_userinfo_1_tv_next_step) {
                if (checkInput()) {
                    String nick = etNick.getText().toString().trim();
                    getPresenter().updateUserInfo(
                            registerUserInfoEntity.getUser_id(),
                            nick,
                            headPath,
                            sex,
                            birth,
                            null,
                            null,
                            null,
                            null,
                            null,
                            etInviteCode.getText().toString().trim(),
                            true
                    );
                }
            }
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etNick.getText().toString().trim())) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_set_your_nickname));
            return false;
        }
        if (TextUtils.isEmpty(headPath)) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_set_your_avatar));
            return false;
        }
        if (TextUtils.isEmpty(birth)) {
            ToastUtils.showShortToast(this, getResources().getString(R.string.Please_set_your_birthday));
            return false;
        }
        return true;
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.wans_person_info_1));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_edit_userinof_1;
    }

    @Override
    protected void initData() {
        headPath = "";
        birth = "";
        sex = 1;
        registerUserInfoEntity = LoginHelper.getRegisterUserInfoModel().getData();
        String loginNameAndPw = getIntent().getStringExtra(KEY_LOGIN_NAME_AND_PW);
        String[] strings = loginNameAndPw.split(",");
        loginName = strings[0];
        pw = strings[1];
        etInviteCode.setTransformationMethod(new ReplacementTransformationMethod() {
            @Override
            protected char[] getOriginal() {
                char[] aa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                return aa;
            }

            @Override
            protected char[] getReplacement() {
                char[] cc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                return cc;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onUpdateUserInfo() {
        getPresenter().login(
                loginName,
                pw,
                "1",
                true
        );
    }

    @Override
    public void onLoginSuccess(LoginResultEntity loginResultEntity) {
        LoginHelper.saveUserData(loginResultEntity);
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

    @Override
    protected EditUserInfo1Presenter createPresenter() {
        return new EditUserInfo1Presenter();
    }
}
