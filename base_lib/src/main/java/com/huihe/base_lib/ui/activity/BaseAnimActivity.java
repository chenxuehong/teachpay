package com.huihe.base_lib.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.StatusBarUtil;
import com.huihe.base_lib.utils.amin.ActivityAnimationHelper;
import com.huihe.base_lib.utils.amin.iml.AbsAnimationListener;
import com.huihe.base_lib.utils.manager.AppManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseAnimActivity extends AppCompatActivity {

    private boolean isStopDispatchKeyEvent = false;
    private boolean isInterceptKeyEventHandleSelf = false;
    private boolean isInterceptKeyEvent = false;
    private Unbinder unbinder;

    public void stopDispatchKeyEvent(boolean stop) {
        isStopDispatchKeyEvent = stop;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initWindow();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (useButterKnife()) {
            unbinder = ButterKnife.bind(this);
        }
        if (useEventBus()) {
            EventBusUtils.register(this);
        }
        AppManager.getInstance().addActivity(this);
        initView();
        initData();
    }

    protected boolean useEventBus() {
        return false;
    }

    protected boolean useButterKnife() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
        if (useButterKnife()) {
           if (unbinder!=null){
               unbinder.unbind();
           }
        }
        if (useEventBus()) {
            EventBusUtils.unRegister(this);
        }
    }

    protected void initView() {
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    protected void initWindow() {

        try {
            if (isFullScreen()) {

                StatusBarUtil.setStatusBarTranslucent(this, getLightStatusBar());
            } else {
                setStatusBarColor();
            }
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean getLightStatusBar() {
        return true;
    }

    protected boolean isFullScreen() {
        return true;
    }

    protected void setStatusBarColor() {
        int statusBarColor = getStatusBarColor();
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(statusBarColor));
    }

    protected int getStatusBarColor() {
        return R.color.default_status_bar_color;
    }

    @Override
    public void finish() {
        ActivityAnimationHelper.animScaleDown(this, new AbsAnimationListener() {
            @Override
            public void onAnimationEnd() {
                BaseAnimActivity.super.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityAnimationHelper.animScaleUp(this, getIntent());
    }

    /**
     * 增加KeyEvent的拦截：一次性拦截所有后续KeyEvent or 只拦截一次KeyEvent
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (isStopDispatchKeyEvent) {
            return true;
        }
        onDispatchKeyEvent(event);
        int keyAction = event.getAction();
        if (keyAction == KeyEvent.ACTION_DOWN) {
            if (isInterceptKeyEventHandleSelf) {
                return onKeyDown(event.getKeyCode(), event);
            } else if (isInterceptKeyEvent) {
                return true;
            }
        } else if (keyAction == KeyEvent.ACTION_UP) {
            boolean isIntercept = false;
            if (isInterceptKeyEventHandleSelf) {
                isIntercept = onKeyUp(event.getKeyCode(), event);
            } else if (isInterceptKeyEvent) {
                isIntercept = true;
            }
            isInterceptKeyEvent = false;
            isInterceptKeyEventHandleSelf = false;
            if (isIntercept) {
                return isIntercept;
            }
        }
        return super.dispatchKeyEvent(event);
    }
    protected void onDispatchKeyEvent(KeyEvent event) {}

    protected final void interceptKeyEvent() {
        interceptKeyEvent(true);
    }

    protected final void interceptKeyEvent(boolean handleSelf) {
        isInterceptKeyEventHandleSelf = handleSelf;
        isInterceptKeyEvent = true;
    }
}
