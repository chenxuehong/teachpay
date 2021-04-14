package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.mvp.SettingContract;
import com.eghuihe.module_user.me.mvp.SettingPresenter;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.event.PersonalUserInfoEvent;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.activity.H5TitleActivity;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DataLoader;
import com.huihe.base_lib.utils.DialogUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.SystemUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.manager.DataCleanManager;
import com.huihe.base_lib.utils.manager.LoginHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseMvpTitleActivity<SettingPresenter> implements SettingContract.View {
    private static final int REQUEST_CODE_PRAVACY = 100;
    @BindView(R2.id.setting_tv_cache)
    TextView tvCache;
    @BindView(R2.id.setting_tv_customer_service)
    TextView tvCustomerService;
    private UserInfoEntity userInfoEntityBean;

    @OnClick({
            R2.id.setting_exit_login,
            R2.id.setting_ll_accountAndSecurity,
            R2.id.setting_ll_news_notify,
            R2.id.setting_ll_privacy,
            R2.id.setting_ll_common,
            R2.id.setting_ll_about_we,
            R2.id.setting_ll_cache,
            R2.id.setting_ll_customer_service,
            R2.id.setting_ll_user_agreement,
            R2.id.setting_ll_Privacy_policy,
            R2.id.setting_ll_close_account,
            R2.id.setting_ll_contact_wo})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.setting_ll_accountAndSecurity) {
                startActivity(AccountSecurityActivity.class);
            } else if (view.getId() == R.id.setting_ll_news_notify) {
                startActivity(NewsNofityActivity.class);
            } else if (view.getId() == R.id.setting_ll_privacy) {
                startActivityForResult(PrivacyActivity.class,
                        new ExtraEntity(PrivacyActivity.KEY_USER_INFO, userInfoEntityBean), REQUEST_CODE_PRAVACY);
            } else if (view.getId() == R.id.setting_ll_common) {
                startActivity(CommonActivity.class);
            } else if (view.getId() == R.id.setting_ll_about_we) {
                startActivity(AbountWeActivity.class);
            } else if (view.getId() == R.id.setting_ll_cache) {
                // 弹出清理缓存的提示对话框
                DialogUtils.showTipDialog(this,
                        "",
                        getResources().getString(R.string.Sure_clear_allcache),
                        getResources().getString(R.string.cancel),
                        getResources().getString(R.string.sure),
                        new DialogUtils.OnListener() {
                            @Override
                            public void okClicked() {
                                DataCleanManager.clearAllCache(SettingActivity.this);
                                initData();
                            }

                            @Override
                            public void cancelClicked() {

                            }
                        });
            } else if (view.getId() == R.id.setting_ll_contact_wo) {
                startActivity(FeedBackActivity.class);
            } else if (view.getId() == R.id.setting_ll_customer_service) {
                DialogUtils.showCallPhoneDialog(this,
                        AppConfigs.SERVICE_CALL,
                        new DialogUtils.OnOkAndCancelListener() {
                            @Override
                            public void okClicked() {
                                SystemUtils.callMobile(SettingActivity.this, AppConfigs.SERVICE_CALL);
                            }

                            @Override
                            public void cancelClicked() {

                            }
                        });
            } else if (view.getId() == R.id.setting_ll_user_agreement) {
                Intent intent1 = new Intent(this, H5TitleActivity.class);
                intent1.putExtra(H5TitleActivity.KET_URL,
                        DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.USER_AGREEMENT, AppConfigs.Cooperation.EN.USER_AGREEMENT));
                startActivity(intent1);
            } else if (view.getId() == R.id.setting_ll_Privacy_policy) {
                Intent intent2 = new Intent(this, H5TitleActivity.class);
                intent2.putExtra(H5TitleActivity.KET_URL,
                        DataLoader.getInstance().getCooperation(AppConfigs.Cooperation.ZH.PRIVACY, AppConfigs.Cooperation.EN.PRIVACY));
                startActivity(intent2);
            } else if (view.getId() == R.id.setting_ll_close_account) {
                DialogUtils.showTipDialog(this, "",
                        getResources().getString(R.string.Sure_close_account),
                        getResources().getString(R.string.cancel),
                        getResources().getString(R.string.sure),
                        new DialogUtils.OnListener() {
                            @Override
                            public void okClicked() {
                                closeAccount();
                            }

                            @Override
                            public void cancelClicked() {

                            }
                        });
            } else if (view.getId() == R.id.setting_exit_login) {
                // 退出登录
                DialogUtils.showTipDialog(this, "",
                        getResources().getString(R.string.Sure_logout),
                        getResources().getString(R.string.cancel),
                        getResources().getString(R.string.sure),
                        new DialogUtils.OnListener() {
                            @Override
                            public void okClicked() {
                                EventBusUtils.sendEvent(new Event(EventAction.LOGOUT_EVENT));
                            }

                            @Override
                            public void cancelClicked() {

                            }
                        });
            }
        }
    }

    private void closeAccount() {
        showProgress(getResources().getString(R.string.closing_account));
        getPresenter().closeAccount(String.valueOf(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id()));
    }

    @Override
    public void showCloseActivityFinish() {
        ToastUtils.showSuccess(getResources().getString(R.string.closing_account_suc));
        BaseApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBusUtils.sendEvent(new Event(EventAction.LOGOUT_EVENT));
            }
        }, 1500);
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.setting));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        userInfoEntityBean = LoginHelper.getLoginInfo().getUserInfoEntity();
        tvCache.setText(DataCleanManager.getTotalCacheSize(this));
        tvCustomerService.setText(AppConfigs.SERVICE_CALL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null) {
            if (requestCode == REQUEST_CODE_PRAVACY) {
                String userInfoJson = data.getStringExtra("userInfoEntityBean");
                userInfoEntityBean = JsonUtil.fromJson(userInfoJson, UserInfoEntity.class);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().post(new PersonalUserInfoEvent());
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

}
