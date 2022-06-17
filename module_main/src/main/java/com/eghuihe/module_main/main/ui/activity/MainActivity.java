package com.eghuihe.module_main.main.ui.activity;

import android.os.Handler;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eghuihe.module_main.R;
import com.eghuihe.module_main.R2;
import com.eghuihe.module_main.main.ui.mvp.MainContract;
import com.eghuihe.module_main.main.ui.mvp.MainPresenter;
import com.huihe.base_lib.constants.ARouterConfig;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.ui.activity.BaseMvpActivity;
import com.huihe.base_lib.ui.service.SelfUpdateProvider;
import com.huihe.base_lib.ui.widget.bottomTabLayout.BottomBarLayoutWithVP;
import com.huihe.base_lib.ui.widget.fitViewPager.NoScrollViewPager;
import com.huihe.base_lib.utils.NotificationPermissionUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.huihe.base_lib.utils.Utils;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.entities_lib.transform.MainTabBean;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

@Route(path = ARouterConfig.MAIN_MAINACTIVITY)
public class MainActivity extends BaseMvpActivity<MainPresenter>
        implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R2.id.act_teaching_pay_main_noScrollViewPager)
    NoScrollViewPager viewPager;
    @BindView(R2.id.act_teaching_pay_main_bottomBarLayout)
    BottomBarLayoutWithVP bottomBar;
    private long mExitTime;

    private Handler handler = new Handler(getMainLooper());
    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teaching_pay_main;
    }

    @Override
    protected void initView() {
        super.initView();
        getPresenter().start();
        SelfUpdateProvider.intance.startSelfUpdateService(this,true,false);
    }

    @Override
    protected void initData() {
        if (bottomBar != null) {
            bottomBar.reset();
        }
        bottomBar.with(this)
                .setViewPager(viewPager)
                .setTextColor(
                        getResources().getColor(R.color.color_FF7300),
                        getResources().getColor(R.color.color_393939))
                .setTextSize(12)
                .setSmoothScroll(false)
                .addStateListAnimatorForScale(1.0f, 0.88f, 50);

        List<MainTabBean> tabList = getPresenter().getTabList(Utils.getIdentityType());
        for (MainTabBean mainTabBean : tabList) {
            bottomBar.addItemView(mainTabBean.getTitle(),
                    mainTabBean.getSelectedImgId(),
                    mainTabBean.getUnselectedImgId(),
                    mainTabBean.getFragmentClass());
        }
        viewPager.setOffscreenPageLimit(bottomBar.getFragmentList().size());
        bottomBar.build();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NotificationPermissionUtils.checkAndOpenNotificationPermission(MainActivity.this);
            }
        }, 2000);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShortToast(mContext, getString(R.string.main_exit_app));
                mExitTime = System.currentTimeMillis();
            } else {
                BaseApplication.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bottomBar != null) {
            bottomBar.clearCache();
        }
        MainPresenter presenter = getPresenter();
        if (presenter!=null) {
            presenter.unDo();
        }
        try {
            SelfUpdateProvider.intance.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscribe
    public void getEvent(Event event) {
        if (EventAction.RESET_USERINFO.equals(event.getAction())) {
            MainPresenter presenter = getPresenter();
            if (presenter!=null && LoginHelper.getLoginInfo()!=null && LoginHelper.getLoginInfo().getUserInfoEntity()!=null){
                presenter.queryUserInfo(LoginHelper.getLoginInfo().getUserInfoEntity().getUser_id());
            }
        } else if (EventAction.IM_UNREAD_COUNT.equals(event.getAction())) {
            try {
                Object data = event.getData();
                if (data instanceof String) {
                    String unreadCount = (String) data;
                    if (Utils.getIdentityType() == Utils.IdentityType.IS_STUDENT) {
                        // 首页、课程表、订单、我的
                        if (bottomBar != null) {
                            bottomBar.setUnreadMsgCountAtIndex(2, Integer.valueOf(unreadCount));
                            bottomBar.setItemMsgView(2, Integer.valueOf(unreadCount));
                        }
                    } else if (Utils.getIdentityType() == Utils.IdentityType.IS_MECHANISM) {
                        if (bottomBar != null) {
                            bottomBar.setUnreadMsgCountAtIndex(2, Integer.valueOf(unreadCount));
                            bottomBar.setItemMsgView(2, Integer.valueOf(unreadCount));
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
