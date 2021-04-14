package com.huihe.base_lib.utils.PopWindow;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.KeyBoardUtils;
import com.huihe.base_lib.utils.ToastUtils;

public class PopWindowUtils {

    public static CustomPopupWindow popBottomDialog(View rootview, Context context, int containerView, boolean enableAlfa) {

        CustomPopupWindow popupWindow = new CustomPopupWindow.Builder(context)
                .setAnimationStyle(R.style.showPopupAnimation)
                .setContentView(containerView)
                .setwidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setheight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setEnableAlfa(enableAlfa)
                .build();
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_FROM_FOCUSABLE);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        return popupWindow;
    }

    public static CustomPopupWindow popInputTextWindow(final Context context, String tip, View rootView, final OnInputListener listener) {

        try {
            final CustomPopupWindow customPopupWindow = PopWindowUtils.popBottomDialog(rootView, context, R.layout.item_comment_reply_enter, false);
            final EditText etContent = (EditText) customPopupWindow.getItemView(R.id.item_comment_reply_et_content);
            etContent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            etContent.setHint(tip);
            etContent.setFocusable(true);
            etContent.requestFocus();
            etContent.setSelection(0);
            customPopupWindow.setOnClickListener(R.id.item_comment_reply_tv_ok, new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final String content = etContent.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtils.showShortToast(context, "请输入内容");
                        return;
                    }
                    customPopupWindow.dismiss();
                    if (listener != null) {
                        listener.okClicked(content);
                        KeyBoardUtils.closeKeybord(etContent, context);
                    }
                }
            });

            KeyBoardUtils.showSoftInput(context);
            return customPopupWindow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CustomPopupWindow popBottomIMUserSettingDialog(View rootview, Context context, int containerView) {
        CustomPopupWindow popupWindow = new CustomPopupWindow.Builder(context)
                .setAnimationStyle(R.style.showPopupAnimation)
                .setContentView(containerView)
                .setwidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setheight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setEnableAlfa(true)
                .build();
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        return popupWindow;
    }

    public static PopupWindow popupDownWindow(View windowView, View parent) {
        PopupWindow popup = new PopupWindow(windowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        int[] location = calculatePopWindowPos(parent, windowView);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setWidth(DensityUtils.dp2px(windowView.getContext(), 125));
        final Activity activity = (Activity) windowView.getContext();
        backgroundAlpha(activity, 0.5f);
//        popup.setBackgroundDrawable(new ColorDrawable(0xAEEEEE00));
        windowView.measure(0, 0);
        int measuredWidth = windowView.getMeasuredWidth();
        popup.showAtLocation(parent, Gravity.START | Gravity.TOP, location[0] - measuredWidth, location[1]);
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity, 1.0f);
            }
        });
        return popup;
    }

    public static PopupWindow popupDownWindowNoAlpha(View windowView, View parent) {
        PopupWindow popup = new PopupWindow(windowView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final int windowPos[] = new int[2];
        parent.measure(0, 0);
        parent.getLocationOnScreen(windowPos);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.showAtLocation(parent, Gravity.START | Gravity.TOP, 0, windowPos[1]);
        return popup;
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private static void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
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

    public interface OnInputListener {
        void okClicked(String content);
    }
}
