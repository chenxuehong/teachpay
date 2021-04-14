package com.huihe.base_lib.ui.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class MyTextView extends AppCompatTextView {

    private Context context;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void insertDrawable(int id) {
        final SpannableString ss = new SpannableString("icon");
        //得到drawable对象，即所要插入的图片
        try {
            GifDrawable gifDrawable = new GifDrawable(context.getResources(), id);
            gifDrawable.setBounds(0, 0, gifDrawable.getIntrinsicWidth(), gifDrawable.getIntrinsicHeight());
            //用这个drawable对象代替字符串easy
            ImageSpan span = new ImageSpan(gifDrawable, ImageSpan.ALIGN_CENTER);
            //包括0但是不包括"easy".length()即：4。[0,4)。值得注意的是当我们复制这个图片的时候，实际是复制了"easy"这个字符串。
            ss.setSpan(span, 0, "icon".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            append(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void insertScaleDrawable(int id, int height) {
        final SpannableString ss = new SpannableString("icon");
        //得到drawable对象，即所要插入的图片
        try {
            GifDrawable gifDrawable = new GifDrawable(context.getResources(), id);
            int intrinsicWidth = gifDrawable.getIntrinsicWidth();
            int intrinsicHeight = gifDrawable.getIntrinsicHeight();
            int width = intrinsicWidth * height / intrinsicHeight;
            gifDrawable.setBounds(0, 0, width, height);
            //用这个drawable对象代替字符串easy
            ImageSpan span = new ImageSpan(gifDrawable, ImageSpan.ALIGN_CENTER);
            //包括0但是不包括"easy".length()即：4。[0,4)。值得注意的是当我们复制这个图片的时候，实际是复制了"easy"这个字符串。
            ss.setSpan(span, 0, "icon".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            append(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
