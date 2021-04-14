package com.huihe.base_lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.huihe.base_lib.constants.AppConfigs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
    /**
     * res目录下面的一张图片保存到本地
     *
     * @param resIconId 图片的id
     */
    public static String saveImage(Context context, int resIconId, String fileName) {
        // getFilesDir().getAbsolutePath()+"/image"\
        //在本地创建一个文件夹
        File fileDir = new File(context.getFilesDir().getAbsolutePath() + AppConfigs.project_name + "/image");
        File file = new File(context.getFilesDir().getAbsolutePath() + AppConfigs.project_name + "/image", fileName);
        // File absoluteFile = getFilesDir().getAbsoluteFile();
        //判断本地是否存在，防止每次启动App都要创建
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
//        Log.i(TAG, "----------------------------------------------------------------");
        //使用BitmapFactory把res下的图片转换成Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resIconId);

        FileOutputStream fos = null;
        ByteArrayOutputStream stream = null;
        try {
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            fos = new FileOutputStream(file);
            fos.write(stream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file.getAbsolutePath();
//        Log.i(TAG, "绝对路径" + getFilesDir().getAbsolutePath() + "/image");
    }
}
