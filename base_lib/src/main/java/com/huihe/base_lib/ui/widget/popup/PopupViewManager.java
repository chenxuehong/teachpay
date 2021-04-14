package com.huihe.base_lib.ui.widget.popup;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.DensityUtils;

import java.util.List;

public class PopupViewManager {

    private static PopupWindow popupWindow;

    private static class Holder {
        private static PopupViewManager instance = new PopupViewManager();
    }

    private PopupViewManager() {
    }

    public static PopupViewManager getInstance() {
        return Holder.instance;
    }

    public void showTopRightView(final Activity activity, View view, List<PopupMenuItem> items, final OnItemClickListener listener) {
        popupWindow = new PopupWindow(view.getContext());
        CustomPopMenuAdapter adapter = new CustomPopMenuAdapter(activity, items);
        View menuView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_pop_menu, null);
        // 设置布局文件
        popupWindow.setContentView(menuView);

        ListView menuList = menuView.findViewById(R.id.menu_list);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.setWidth(DensityUtils.dp2px(BaseApplication.getInstance(),125));
//        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        int[] location = calculatePopWindowPos(view, menuView);
        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.mipmap.bg_popup));
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        popupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        popupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        popupWindow.setOutsideTouchable(true);
        backgroundAlpha(activity, 0.5f);
        // 相对于 + 号正下面，同时可以设置偏移量
//        int[] location = getPopupWindowLoction(view);
        popupWindow.showAtLocation(view, Gravity.START | Gravity.TOP, location[0] - DensityUtils.dp2px(activity, 50), location[1]);
//        mMenuWindow.showAtLocation(view, Gravity.RIGHT | Gravity.TOP, ScreenUtils.getPxByDp(15), ScreenUtils.getPxByDp(70));
        // 设置pop关闭监听，用于改变背景透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity, 1.0f);
            }
        });
    }

    public void showCenterView(final Activity activity, View view, List<PopupMenuItem> items, final OnItemClickListener listener) {
        final PopupWindow popupWindow = new PopupWindow(activity);
        CustomPopMenuAdapter adapter = new CustomPopMenuAdapter(activity, items);
        View menuView = LayoutInflater.from(activity).inflate(R.layout.custom_pop_menu, null);
        // 设置布局文件
        popupWindow.setContentView(menuView);

        ListView menuList = menuView.findViewById(R.id.menu_list);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.setWidth(DensityUtils.dp2px(BaseApplication.getInstance(),125));
//        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.shape_bg_popup));
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        popupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        popupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        popupWindow.setOutsideTouchable(true);
        backgroundAlpha(activity, 0.5f);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        // 设置pop关闭监听，用于改变背景透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity, 1.0f);
            }
        });
    }

    public PopupWindow showCenterView(final Activity activity, View view,View contentView) {
        final PopupWindow popupWindow = new PopupWindow(activity);
        // 设置布局文件
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.shape_bg_popup));
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        popupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        popupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        popupWindow.setOutsideTouchable(true);
        int[] pos = calculatePopWindowPos(view, contentView);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        // 设置pop关闭监听，用于改变背景透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity, 1.0f);
            }
        });
        return popupWindow;
    }

    public PopupWindow showTopView(final Activity activity, View view,View contentView) {
        final PopupWindow popupWindow = new PopupWindow(activity);
        // 设置布局文件
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.shape_bg_popup));
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        popupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        popupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        popupWindow.setOutsideTouchable(true);
        int[] pos = calculatePopWindowPos(view, contentView);
        popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
        // 设置pop关闭监听，用于改变背景透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity, 1.0f);
            }
        });
        return popupWindow;
    }

    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        final int anchorWidth = anchorView.getWidth();
        // 获取屏幕的高宽
        final int screenHeight = DensityUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = DensityUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
//            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
//            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        if (anchorLoc[0] + anchorWidth / 2 + windowWidth / 2 > screenWidth) {//右侧超出屏幕
            windowPos[0] = screenWidth - windowWidth;
        } else if (anchorLoc[0] + anchorWidth / 2 - windowWidth / 2 < 0) {//左侧超出屏幕
            windowPos[0] = 0;
        } else {
            windowPos[0] = anchorLoc[0] + anchorWidth / 2 - windowWidth / 2;
        }

        return windowPos;
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
