package com.huihe.base_lib.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.huihe.base_lib.R;

import java.util.ArrayList;
import java.util.List;


public class ColorAndSizeTextUtil {

    private static final String TAG = "ColorAndSizeTextUtil";

    private ColorAndSizeTextUtil() {
    }

    /**
     * 设置 TextView 中部分文字颜色.
     *
     * @param allTextStr   全部文字
     * @param colorTextStr 需要改变颜色的文字
     * @param colorId      改变的颜色
     */
    public static void setColorText(TextView tv,
                                    String allTextStr,
                                    String[] colorTextStr,
                                    @ColorRes final int colorId, final float colorTxtSpSize, final OnTextClickedListener listener) {
        if (colorTextStr == null || colorTextStr.length == 0 || TextUtils.isEmpty(allTextStr)) {
            return;
        }
        List<int[]> positions = new ArrayList<>();
        for (String str : colorTextStr) {
            findSubStringPositons(allTextStr.toLowerCase(), str.toLowerCase(), 0, positions);
        }

        final Context context = tv.getContext();

        SpannableString spanText = new SpannableString(allTextStr);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                if (listener != null)
                    listener.onClicked(textView);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        for (int[] position : positions) {
            spanText.setSpan(new CharacterStyle() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(context.getResources().getColor(colorId)); //设置文字颜色
                    ds.setFakeBoldText(false);
                    ds.setTextSize(DensityUtils.sp2px(context, colorTxtSpSize));
                    ds.setUnderlineText(false);
                }
            }, position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(clickableSpan, position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
//        tv.setHighlightColor(context.getResources().getColor(R.color.transparent));// 设置点击后的颜色为透明，否则会一直出现高亮
        tv.setText(spanText);
        tv.setMovementMethod(LinkMovementMethod.getInstance());//这个也得设置,不设置点击没效果
    }

    /**
     * 设置 TextView 中部分文字颜色.
     *
     * @param allTextStr   全部文字
     * @param colorTextStr 需要改变颜色的文字
     * @param colorIds     改变的颜色
     */
    public static void setColorText(TextView tv,
                                    String allTextStr,
                                    String[] colorTextStr,
                                    @ColorRes final int[] colorIds, final float colorTxtSpSize, final OnTextClickedListener listener) {
        if (colorTextStr == null || colorTextStr.length == 0 || TextUtils.isEmpty(allTextStr)) {
            return;
        }
        List<int[]> positions = new ArrayList<>();
        for (String str : colorTextStr) {
            findSubStringPositons(allTextStr.toLowerCase(), str.toLowerCase(), 0, positions);
        }

        final Context context = tv.getContext();

        SpannableString spanText = new SpannableString(allTextStr);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                if (listener != null)
                    listener.onClicked(textView);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        for (int i = 0; i < positions.size(); i++) {
            int[] position = positions.get(i);
            final int finalI = i;
            spanText.setSpan(new CharacterStyle() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    int colorId = colorIds[finalI];
                    ds.setColor(context.getResources().getColor(colorId)); //设置文字颜色
                    ds.setFakeBoldText(false);
                    ds.setTextSize(DensityUtils.sp2px(context, colorTxtSpSize));
                    ds.setUnderlineText(false);
                }
            }, position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(clickableSpan, position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
//        tv.setHighlightColor(context.getResources().getColor(R.color.transparent));// 设置点击后的颜色为透明，否则会一直出现高亮
        tv.setText(spanText);
        tv.setMovementMethod(LinkMovementMethod.getInstance());//这个也得设置,不设置点击没效果
    }

    public static void findSubStringPositons(String totalString, String subString, int startIndex, List<int[]> positions) {
        if (positions != null && !TextUtils.isEmpty(totalString) && !TextUtils.isEmpty(subString)) {
            int start = totalString.indexOf(subString, startIndex);
            if (start == -1) {
                return;
            }
            int end = start + subString.length();
            int[] position = new int[2];
            position[0] = start;
            position[1] = end;
            positions.add(position);
            findSubStringPositons(totalString, subString, end, positions);
        }
    }

    public static void setSubTextSize(TextView tv,
                                      String allTextStr,
                                      String[] sizeTextStr,
                                      final float size,
                                      final boolean blod,
                                      final int color) {
        if (sizeTextStr == null || sizeTextStr.length == 0 || TextUtils.isEmpty(allTextStr)) {
            return;
        }
        List<int[]> positions = new ArrayList<>();
        for (String str : sizeTextStr) {
            findSubStringPositons(allTextStr.toLowerCase(), str.toLowerCase(), 0, positions);
        }

        SpannableString spanText = new SpannableString(allTextStr);
        for (int[] position : positions) {
            spanText.setSpan(new CharacterStyle() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setTextSize(size);
                    ds.setFakeBoldText(blod);
                    ds.setUnderlineText(false);
                    if (color != -1) {
                        ds.setColor(color);
                    }
                }
            }, position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        tv.setText(spanText);
    }

    /**
     * @desc 设置部分文本超链接
     * @desc colorTextStr
     * @desc colorIds
     * @desc urls
     */
    public static void setLinkedText(TextView textView, String allTextStr, String[] colorTextStr, final int[] colorIds, String[] urls, final float colorTxtSpSize) {
        if (colorTextStr == null || colorTextStr.length == 0 || TextUtils.isEmpty(allTextStr)) {
            return;
        }
        List<int[]> positions = new ArrayList<>();
        for (String str : colorTextStr) {
            findSubStringPositons(allTextStr.toLowerCase(), str.toLowerCase(), 0, positions);
        }
        final Context context = textView.getContext();
        SpannableString spanText = new SpannableString(allTextStr);
        for (int i = 0; i < positions.size(); i++) {
            int[] position = positions.get(i);
            final int finalI = i;
            spanText.setSpan(new URLSpan(urls[finalI]), position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(new CharacterStyle() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    int colorId = colorIds[finalI];
                    ds.setColor(context.getResources().getColor(colorId)); //设置文字颜色
                    ds.setFakeBoldText(false);
                    ds.setTextSize(DensityUtils.sp2px(context, colorTxtSpSize));
                    ds.setUnderlineText(false);
                }
            }, position[0], position[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setHighlightColor(textView.getResources().getColor(R.color.color_red));
        textView.setText(spanText);
        textView.setMovementMethod(LinkMovementMethod.getInstance());//这个也得设置,不设置点击没效果
    }

    public interface OnTextClickedListener {
        void onClicked(View view);
    }
}
