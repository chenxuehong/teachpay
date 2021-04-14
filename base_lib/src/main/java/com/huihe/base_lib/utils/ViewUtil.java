package com.huihe.base_lib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.lang.reflect.Field;

public class ViewUtil {

    public static void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void quitFullScreen(Activity activity) {
        final WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void setDrawableTop(TextView tv,Drawable drawableTop){
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(),drawableTop.getMinimumHeight());
        tv.setCompoundDrawablesWithIntrinsicBounds(null,drawableTop,null,null);
    }

    public static void setDrawableBottom(TextView tv,Drawable drawableBottom){
        drawableBottom.setBounds(0, 0, drawableBottom.getMinimumWidth(),drawableBottom.getMinimumHeight());
        tv.setCompoundDrawablesWithIntrinsicBounds(null,null,null,drawableBottom);
    }

    public static void setDrawableLeft(TextView tv,Drawable drawableleft){
        drawableleft.setBounds(0, 0, drawableleft.getMinimumWidth(),drawableleft.getMinimumHeight());
        tv.setCompoundDrawablesWithIntrinsicBounds(drawableleft,null,null,null);
    }

    public static void setDrawableRight(TextView tv,Drawable drawableRight){
        drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(),drawableRight.getMinimumHeight());
        tv.setCompoundDrawablesWithIntrinsicBounds(null,null,drawableRight,null);
    }

    /**
     * 解决InputMethodManager内存泄露现象
     *
     * @param destContext 上下文
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (String param : arr) {
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get
                            .getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        /*if (QLog.isColorLevel()) {
                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        }*/
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
