package com.huihe.base_lib.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.huihe.base_lib.R;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.InputManager;
import com.huihe.base_lib.utils.manager.AppManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseStatusBarActivity extends AppCompatActivity implements View.OnClickListener {

    public Activity mContext;
    private Unbinder unbinder;


    public abstract int getLayoutId();


    protected void onNoFastClick(View view) {
    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(getLayoutId());
//        StatusBarCompat.translucentStatusBar(this);
        unbinder = ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        mContext = this;

        bindView();
    }

    private void initWindow() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏);
        setStatusBarColor();
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(getStatusBarColor());
//            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar_color));
            int vis = getWindow().getDecorView().getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            getWindow().getDecorView().setSystemUiVisibility(vis);
        }
    }

    protected int getStatusBarColor() {
        return getResources().getColor(R.color.default_status_bar_color);
    }

    protected abstract void bindView();


    @Override
    public void onClick(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            onNoFastClick(view);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InputManager.fixInputMethodManagerLeak(this);
    }

    @Override
    public void finish() {
        InputManager.fixInputMethodManagerLeak(this);
        super.finish();
    }


    /**
     * 设置默认字体大小
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
//        KeyBoardUtils.forceCloseKayBoard(BaseStatusBarActivity.this);

        if (unbinder!=null){
            unbinder.unbind();
        }
        AppManager.getInstance().removeActivity(this);
        InputManager.fixInputMethodManagerLeak(this);
        // System.gc();
        super.onDestroy();
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void startActivity(Class clazz, ExtraEntity extraEntity) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(extraEntity.key, new Gson().toJson(extraEntity.entity));
        startActivity(intent);
    }

    public <T> T getIntentData(String key,Class<T> aClass){

        return new Gson().fromJson(getIntent().getStringExtra(key),aClass);
    }

    public void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

}