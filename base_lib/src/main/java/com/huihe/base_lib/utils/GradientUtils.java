package com.huihe.base_lib.utils;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.widget.TextView;

public class GradientUtils {
    public static void setTextGradient(TextView textView,String startColor,String endColor,Shader.TileMode tileMode) {
        LinearGradient mLinearGradient = new LinearGradient(0, 0,
                textView.getPaint().getTextSize()* textView.getText().length(),
                0,
                Color.parseColor(startColor),
                Color.parseColor(endColor),
                tileMode);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();
    }
}
