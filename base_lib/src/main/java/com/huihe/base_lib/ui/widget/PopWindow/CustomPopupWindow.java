package com.huihe.base_lib.ui.widget.PopWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.huihe.base_lib.ui.widget.dialog.MyPopWindow;

public class CustomPopupWindow {
    private boolean isCanCancel = true;
    private MyPopWindow mPopupWindow;
    private View contentView;
    private boolean enableAlfa;
    private static Context mContext;
    private float alfa;
    public CustomPopupWindow(Builder builder) {
        contentView = LayoutInflater.from(mContext).inflate(builder.contentViewId, null);
        mPopupWindow = new MyPopWindow(contentView, builder.width, builder.height);
        enableAlfa = builder.enableAlfa;
        alfa = builder.alfa;
        isCanCancel = builder.isCanCancel;
        //设置点击外部可以取消，必须和下面这个方法配合才有效
        mPopupWindow.setOutsideTouchable(isCanCancel);
        //设置一个空背景,设置了这个背景之后，设置点击外部取消才有效
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Popupwindow可以点击,PopupWindow弹出后，所有的触屏和物理按键都有PopupWindows处理。
        // 其他任何事件的响应都必须发生在PopupWindow消失之后， （home  等系统层面的事件除外）。
        // 比如这样一个PopupWindow出现的时候，按back键首先是让PopupWindow消失，
        // 第二次按才是退出activity，
        mPopupWindow.setFocusable(isCanCancel);
        setOnDismissListener();
        mPopupWindow.setAnimationStyle(builder.animStyle); //设置pop显示的动画效果
    }

    public View getContentView() {
        return contentView;
    }

    /**
     * 更改屏幕窗口透明度
     *
     * @param context
     * @param alfa
     */
    void changeWindowAlfa(Context context, float alfa) {
        if (context instanceof AppCompatActivity) {
            WindowManager.LayoutParams attributes = ((AppCompatActivity) context).getWindow().getAttributes();
            attributes.alpha = alfa;
            ((AppCompatActivity) context).getWindow().setAttributes(attributes);
        } else if (context instanceof Activity) {
            WindowManager.LayoutParams attributes = ((Activity) context).getWindow().getAttributes();
            ((Activity) context).getWindow().setAttributes(attributes);
        }

    }

    /**
     * popup 消失
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }


    /**
     * 相对于窗体的显示位置
     *
     * @param view    可以为Activity中的任意一个View（最终的效果一样），
     *                会通过这个View找到其父Window，也就是Activity的Window。
     * @param gravity 在窗体中的位置，默认为Gravity.NO_GRAVITY
     * @param x       表示距离Window边缘的距离，方向由Gravity决定。
     *                例如：设置了Gravity.TOP，则y表示与Window上边缘的距离；
     *                而如果设置了Gravity.BOTTOM，则y表示与下边缘的距离。
     * @param y
     * @return
     */
    public CustomPopupWindow showAtLocation(View view, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            if (enableAlfa)
                changeWindowAlfa(mContext, alfa);
            mPopupWindow.showAtLocation(view, gravity, x, y);
        }
        return this;
    }

    public CustomPopupWindow showAtLocationNoAlfa(View view, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(view, gravity, x, y);
        }
        return this;
    }

    public CustomPopupWindow setSoftInputMode(int mode) {
        if (mPopupWindow != null) {
            mPopupWindow.setSoftInputMode(mode);
        }
        return this;
    }

    public CustomPopupWindow setInputMethodMode(int mode) {
        if (mPopupWindow != null) {
            mPopupWindow.setInputMethodMode(mode);
        }
        return this;
    }

    public CustomPopupWindow setFocusable(boolean focusable) {
        if (mPopupWindow != null) {
            mPopupWindow.setFocusable(focusable);
        }
        return this;
    }

    /**
     * 显示在anchor控件的正下方，或者相对这个控件的位置
     *
     * @param anchor
     * @param xoff
     * @param yoff
     * @param gravity
     * @return
     */
    public CustomPopupWindow showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (mPopupWindow != null) {
            if (enableAlfa)
                changeWindowAlfa(mContext, alfa);
            mPopupWindow.showAsDropDown(anchor, xoff, yoff, gravity);
        }
        return this;
    }

    /**
     * 根据id获取view
     *
     * @param viewId
     * @return
     */
    public View getItemView(int viewId) {
        if (mPopupWindow != null) {
            return contentView.findViewById(viewId);
        }
        return null;
    }

    /**
     * 根据id设置pop内部的控件的点击事件的监听
     *
     * @param viewId
     * @param listener
     */
    public CustomPopupWindow setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getItemView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    private void setOnDismissListener() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (enableAlfa)
                    changeWindowAlfa(mContext, 1f);
                if (onListener!=null){
                    onListener.onDismiss();
                }
            }
        });
    }

    private OnListener onListener;
    public void setOnListener(OnListener onListener){
        this.onListener = onListener;
    }

    public interface OnListener{
        void onDismiss();
    }

    /**
     * builder 类
     */
    public static class Builder {
        private int contentViewId; //pop的布局文件
        private int width; //pop的宽度
        private int height;  //pop的高度
        private int animStyle; //动画效果
        private boolean enableAlfa;
        private float alfa = 0.5f;
        private boolean isCanCancel = true;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setContentView(int contentViewId) {
            this.contentViewId = contentViewId;
            return this;
        }

        public Builder setwidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setheight(int height) {
            this.height = height;
            return this;
        }

        public Builder setAnimationStyle(int animStyle) {
            this.animStyle = animStyle;
            return this;
        }

        public Builder setEnableAlfa(boolean enableAlfa) {
            this.enableAlfa = enableAlfa;
            return this;
        }

        public Builder setAlfa(float alfa) {
            this.alfa = alfa;
            return this;
        }

        public Builder setCanCancel(boolean isCanCancel) {
            this.isCanCancel = isCanCancel;
            return this;
        }

        public CustomPopupWindow build() {
            return new CustomPopupWindow(this);
        }
    }
}
