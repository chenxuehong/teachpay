package com.huihe.base_lib.ui.activity;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Vibrator;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.huihe.base_lib.BuildConfig;
import com.huihe.base_lib.R;
import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.db.MySQLiteDBDao;
import com.huihe.base_lib.db.MySQLiteOpenHelper;
import com.huihe.base_lib.net.HttpEngineCore;
import com.huihe.base_lib.ui.service.LocationService;
import com.huihe.base_lib.ui.widget.MyRefreshHeader;
import com.huihe.base_lib.utils.LogUtils;
import com.huihe.base_lib.utils.MultiLanguageUtil;
import com.huihe.base_lib.utils.PListParserUtils;
import com.huihe.base_lib.utils.SPUtils;
import com.huihe.base_lib.utils.manager.AppManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.List;


public class BaseApplication extends Application {
    private static BaseApplication application;
    private static Handler handler = new Handler();

    private static String url;

    private static final int INSTALL_PACKAGES_REQUESTCODE = 100;
    public static long application_attach_time;


    public static BaseApplication getInstance() {
        return application;
    }

    public Vibrator mVibrator;

    private static final String TAG = BaseApplication.class.getSimpleName();

    public static Handler getHandler() {
        return handler;
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.black);//全局设置主题颜色
                layout.setHeaderMaxDragRate(3);
                return new MyRefreshHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMainTask();
        initSdk();
    }

    private void initMainTask() {
        application = this;
        handler = new Handler();
        initARouter();
        initLocSdk();
    }

    protected void initSdk() {

    }

    private void initLocSdk() {
        /***
         * 初始化定位sdk，建议在Application中创建
         */
//        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
//        String sdkVersion = locationService.getSDKVersion();
//        LogUtils.i(TAG,"百度地图sdkVersion = " + sdkVersion);
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();  // 打印日志
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);// 尽可能早，推荐在Application中初始化
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtil.getInstance().setConfiguration(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); //防止方法数过多,分包技术
        MultiLanguageUtil.getInstance().setConfiguration(getApplicationContext());
        application_attach_time = System.currentTimeMillis();
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        AppManager.getInstance().finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
