package com.huihe.base_lib.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.huihe.base_lib.R;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.widget.dialog.LoadingDialog;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.InputManager;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.StatusBarUtil;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.AppManager;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements IStateView, View.OnClickListener {

    private static final Object TAG = BaseActivity.class.getSimpleName();
    private boolean isConfigChange = false;
    private LoadingDialog loadingDialog;
    public Context mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initWindow();
        super.onCreate(savedInstanceState);
        mContext = this;
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setCancelable(false);
        setContentView(getLayoutId());
        if (useButterKnife()) {
            //初始化ButterKnife
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

    protected void initWindow() {
        try {
            if (isFullScreen()) {

                StatusBarUtil.setStatusBarTranslucent(this, getLightStatusBar());
            } else {
                setStatusBarColor();
            }
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏);
            if (isKeepScreenOn())
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean isKeepScreenOn() {
        return false;
    }

    protected boolean getLightStatusBar() {
        return true;
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected void initView() {
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isConfigChange) {
            // 友盟统计 调用onResume方法
            MobclickAgent.onResume(this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        BaseApplication.getHandler().removeCallbacksAndMessages(null);
        InputManager.fixInputMethodManagerLeak(this);
        if (useButterKnife()) {
            if (unbinder != null) {
                unbinder.unbind();
            }
        }
        if (useEventBus()) {
            EventBusUtils.unRegister(this);
        }
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    protected void setStatusBarColor() {
        int statusBarColor = getStatusBarColor();
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(statusBarColor));
    }

    protected int getStatusBarColor() {
        return R.color.default_status_bar_color;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View currentFocus = getCurrentFocus();
            //如果不是落在EditText区域，则需要关闭输入法
            if (hideKeyboard(currentFocus, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                doAfterHideKeyboard();
            } else {
                doAfterShowKeyboard();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void doAfterShowKeyboard() {

    }

    protected void doAfterHideKeyboard() {
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
     */
    private boolean hideKeyboard(View view, MotionEvent event) {
        if (view != null && view instanceof EditText) {

            int[] location = new int[]{0, 0};
            view.getLocationInWindow(location);
            //获取现在拥有焦点的控件view的位置，即EditText
            int left = location[0];
            int top = location[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
            boolean isInEt = (event.getX() > left && event.getX() < right && event.getY() > top
                    && event.getY() < bottom);
            return !isInEt;
        }
        return false;
    }

    public void startActivity(Class clazz) {

        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }
    }

    protected boolean hasEnterTransitionAnim() {
        return true;
    }

    public void startActivity(Class clazz, ExtraEntity extraEntity) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        startActivity(intent);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    public void startActivity(Class clazz, ExtraEntity[] extraEntitys) {
        Intent intent = new Intent(this, clazz);
        for (int i = 0; i < extraEntitys.length; i++) {
            ExtraEntity extraEntity = extraEntitys[i];
            intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        }
        startActivity(intent);

    }

    public void startActivityForResult(Class clazz, ExtraEntity extraEntity, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        startActivityForResult(intent, requestCode);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    @Override
    public void onClick(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            onNoFastClick(view);
        }
    }

    protected void onNoFastClick(View view) {

    }

    public <T> T getIntentData(String key, Class<T> aClass) {

        return new Gson().fromJson(getIntent().getStringExtra(key), aClass);
    }

    public <T> List<T> getIntentData(String key, Type type) {

        return JsonUtil.fromJson(getIntent().getStringExtra(key), type);
    }

    public void startActivity(Class clazz, String key, int value) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, value);
        startActivity(intent);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    public void startActivity(Class clazz, String key, String value) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, value);
        startActivity(intent);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    public void startActivityForResult(Class clazz, String key, int value, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestCode);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    public void startActivityForResult(Class clazz, String key, String value, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestCode);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    public void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
        if (hasEnterTransitionAnim()) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        }

    }

    @Override
    public void finish() {
        super.finish();
        if (hasFinishTransitionAnim()) {
            overridePendingTransitionExit();
        }
    }

    protected boolean hasFinishTransitionAnim() {
        return true;
    }

    private void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    public View ViewInflater(int layoutId, ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(this).inflate(layoutId, root, attachToRoot);
    }

    @Override
    public void showLoading() {
        if (isShowLoading()) {
            if (loadingDialog != null) {
                loadingDialog.show();
            }
        }
    }

    @Override
    public void showProgress(String msg) {
        if (loadingDialog != null) {
            loadingDialog.setTitleText(msg);
            loadingDialog.show();
        }
    }

    @Override
    public boolean isShowLoading() {
        return true;
    }

    @Override
    public void showUploading() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }

    @Override
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void retry() {

    }
}

