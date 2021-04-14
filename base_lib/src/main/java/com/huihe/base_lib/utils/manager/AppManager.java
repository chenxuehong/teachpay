package com.huihe.base_lib.utils.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import com.huihe.base_lib.utils.LogUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by bruce on 2017/2/10.
 */
public class AppManager {
    private static AppManager instance = new AppManager();

    private AppManager() {
    }

    public static AppManager getInstance() {
        return instance;
    }

    /**
     * Activity堆栈
     */
    private static Stack<AppCompatActivity> activityStack = new Stack<>();

    public static Stack<AppCompatActivity> getActivityStack() {
        return activityStack;
    }

    /**
     * 获取最后的Activity对象
     */
    public AppCompatActivity currentActivity() {
        try {
            return activityStack.lastElement();
        } catch (Exception e) {
            return null;
        }
    }

    public AppCompatActivity priActivity() {
        try {
            return activityStack.get(activityStack.size() - 2);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将Activity对象放入堆栈
     */
    public void addActivity(AppCompatActivity activity) {
        if (activity != null && !activityStack.contains(activity)) {
            activityStack.add(activity);
        }
    }

    /**
     * 判定某个Activity是否存在
     */
    public boolean isActivityExist(Class<?> clz) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass() == clz) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得activity
     */
    public AppCompatActivity getActivity(Class<?> clz) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass() == clz) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 移除activity
     */
    public void removeActivity(AppCompatActivity activity) {
        activityStack.remove(activity);
        activity = null;
    }

    /**
     * 关闭指定的Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        try {
            LogUtils.i("AppManager", "finishActivity");
            if (activity != null && !activity.isFinishing()
                    && activityStack.contains(activity)) {
                activityStack.remove(activity);
                activity.finish();
                LogUtils.i("AppManager", "activity = " + activity.getClass().getName());
                activity = null;//主动释放对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭除了指定Activity的其他所有activity
     */
    public void finishAllActivity(Class<?> cls) {
        try {
            LogUtils.i("AppManager", "finishAllActivity");
            Iterator<AppCompatActivity> iterator = activityStack.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                AppCompatActivity appCompatActivity = iterator.next();
                ++i;
                LogUtils.i("AppManager", "activity = " + appCompatActivity.getClass().getName() + " 第" + i + "个");
                if (!appCompatActivity.getClass().equals(cls.getClass())) {
                    if (appCompatActivity != null && !appCompatActivity.isFinishing()
                            && activityStack.contains(appCompatActivity)) {
                        appCompatActivity.finish();
                        iterator.remove();
                        LogUtils.i("AppManager", "activity = " + appCompatActivity.getClass().getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 依据类名关闭指定的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 关闭当前Activity
     */
    public void finishCurActivity() {
        AppCompatActivity appCompatActivity = currentActivity();
        if (!isHome(appCompatActivity)) {
            finishActivity(appCompatActivity);
        }
    }

    /**
     * 关闭所有的Activity
     */
    public void finishAllActivity() {
        Iterator<AppCompatActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            iterator.remove();
            activity.finish();
            activity = null;//主动释放对象
        }
        activityStack.clear();
    }

    public void finishAllActivity(AppCompatActivity ac) {
        Iterator<AppCompatActivity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            AppCompatActivity activity = iterator.next();
            if (ac == activity) {
                continue;
            }
            iterator.remove();
            activity.finish();
            activity = null;//主动释放对象
        }
    }

    /**
     * 关闭所有的Activity
     */
    public void finishActivityOfNum(int num) {
        for (int i = 0; i < num && activityStack.size() > 2; i++) {
            Activity activity = activityStack.get(activityStack.size() - 2);
            if (isHome(activity)) {
                break;
            }
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 关闭任务栈上面指定数量的Activity
     */
    public void finishTopActivityOfNum(int num) {
        for (int i = activityStack.size() - num; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void backToHome() {
        for (; ; ) {
            Activity activity = activityStack.get(activityStack.size() - 1);
            if (isHome(activity)) {
                break;
            }
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    private boolean isHome(Activity activity) {
        return activity.getClass().getName().equals("com.eghuihe.qmore.ui.activity.main.MainActivity");
    }

    public void backTo(Class c) {
        for (; ; ) {
            Activity activity = activityStack.get(activityStack.size() - 1);
            if (activity.getClass().getName().equals(c.getName())) {
                break;
            }
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void backTo(String c) {
        for (; ; ) {
            Activity activity = activityStack.get(activityStack.size() - 1);
            if (activity.getClass().getName().equals(c)) {
                break;
            }
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(String c) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().getName().equals(c)) {
                finishActivity(activity);
                break;
            }
        }
    }

    public void finishAllActivity(String c) {
        for (AppCompatActivity activity : activityStack) {
            if (!activity.getClass().getName().equals(c)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 判断app是否处于前台
     *
     * @return
     **/
    public boolean isAppOnForeground(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        /**
         * 获取Android设备中所有正在运行的App
         */
        List<ActivityManager.RunningAppProcessInfo> appProcesses = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            appProcesses = activityManager
                    .getRunningAppProcesses();
        }
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /* 判断应用是否已经启动
     *
     * @param context     上下文对象
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                return true;
            }
        }

        return false;
    }
}