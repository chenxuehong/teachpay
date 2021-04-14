package com.eghuihe.module_user.login.ui.activity;

import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.login.ui.fragment.CodeLoginFragment;
import com.eghuihe.module_user.login.ui.fragment.LoginFragment;
import com.eghuihe.module_user.login.ui.fragment.RegisterFragment;
import com.google.android.material.tabs.TabLayout;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.adapter.BaseFragmentStatePagerAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

@Route(path = ARouterConfig.ME_LOGINACTIVITY)
public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R2.id.activity_login_tablayout)
    TabLayout tabLayout;
    @BindView(R2.id.activity_login_viewPager)
    ViewPager viewPager;

    private boolean isPwLogin = true;

    @Override
    protected int getLayoutId() {
        return R.layout.acivity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        isPwLogin = true;
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.login));
        titles.add(getResources().getString(R.string.register));
        if (isPwLogin) {
            LoginFragment loginFragment = new LoginFragment();
            fragments.add(loginFragment);
        } else {
            CodeLoginFragment codeLoginFragment = new CodeLoginFragment();
            fragments.add(codeLoginFragment);
        }
        fragments.add(new RegisterFragment());
        viewPager.setAdapter(new BaseFragmentStatePagerAdapter(getSupportFragmentManager(), fragments, titles));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            BaseApplication.getInstance().exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.CODE_LOGIN.equals(event.getAction())) {
            isPwLogin = false;
            initData();
        } else if (EventAction.PASSWORD_LOGIN.equals(event.getAction())) {
            isPwLogin = true;
            initData();
        }
    }
}
