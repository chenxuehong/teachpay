package com.huihe.base_lib.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AssetsUtils {

    public static InputStream readIsFromAssets(Context context,String fileName){

        try {
           return context.getAssets().open(fileName, Context.MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
