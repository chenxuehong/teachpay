package com.huihe.base_lib.utils.imageview;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.FileSizeUtil;
import com.huihe.base_lib.utils.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 关于图片的工具类
 */
public class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();

    /**
     * 获取图片的旋转角度
     *
     * @param filePath
     * @return
     */
    public static int getRotateAngle(String filePath) {
        int rotate_angle = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate_angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate_angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate_angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate_angle;
    }

    /**
     * 旋转图片角度
     *
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap setRotateAngle(int angle, Bitmap bitmap) {

        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;

    }

    //转换为圆形状的bitmap
    public static Bitmap createCircleImage(Bitmap source) {
        int length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }


    /**
     * 图片压缩-质量压缩
     *
     * @param filePath 源图片路径
     * @return 压缩后的路径
     */

    public static String compressImage(String filePath) {
        if (FileSizeUtil.getFileOrFilesSize(filePath, FileSizeUtil.SIZETYPE_KB) < 200) {
            LogUtils.i(TAG, "compressImage 文件小于1m不需要压缩(kb): " + FileSizeUtil.getFileOrFilesSize(filePath, FileSizeUtil.SIZETYPE_KB));
            return filePath;
        }
        //原文件
        File oldFile = new File(filePath);
        LogUtils.i(TAG, "compressImage 压缩前(kb): " + FileSizeUtil.getFileOrFilesSize(filePath, FileSizeUtil.SIZETYPE_KB));
        //压缩文件路径 照片路径/
        String targetPath = oldFile.getPath();
        int quality = 50;//压缩比例0-100
        Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片
        int degree = getRotateAngle(filePath);//获取相片拍摄角度

        if (degree != 0) {//旋转照片角度，防止头像横着显示
            bm = setRotateAngle(degree, bm);
        }
//        File outputFile = new File(targetPath);
        String extStorageDirectory = BaseApplication.getInstance().getExternalFilesDir("").getAbsolutePath();
        File outputFile = new File(extStorageDirectory, Long.valueOf((new Date()).getTime()) + ".jpg");//创建文件，第一个参数为路径，第二个参数为文件名
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return filePath;
        }
        LogUtils.i(TAG, "compressImage 压缩后(kb): " + FileSizeUtil.getFileOrFilesSize(outputFile.getPath(), FileSizeUtil.SIZETYPE_KB));
        return outputFile.getPath();
    }

    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, options.outWidth, options.outHeight);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap viewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bm = view.getDrawingCache();
        return bm;
    }

    public static Bitmap getBitmapFromView(View view) {
        // Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        // Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            // has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            // does not have background drawable, then draw white background on
            // the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        // return the bitmap
        return returnedBitmap;
    }

    public static boolean saveBitmap(Bitmap bitmap, String filename, Context context) {

        String extStorageDirectory = context.getExternalFilesDir("").getAbsolutePath();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory, filename + ".JPEG");//创建文件，第一个参数为路径，第二个参数为文件名
        Uri uri;
        try {
            outStream = new FileOutputStream(file);//创建输入流
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.close();
            // 实现相册更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(context, "com.lx0.recognize.fileprovider", file);
            } else {
                //适配Android N版本之前
                uri = Uri.fromFile(file);
            }
            Toast.makeText(context, "保存成功",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "保存失败 exception:" + e,
                    Toast.LENGTH_SHORT).show();
            Log.d("RecognizeUtil", e.toString());
            return false;
        }

        Log.d("Util", " " + file.getAbsolutePath());
        return true;
    }

    // 将图片保存到相册
    public static boolean saveToDcim(Bitmap bitmap, String filename, Context context) {
        Uri uri;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, filename);
        //兼容Android Q和以下版本
        if (Build.VERSION.SDK_INT >= 29) {
            //android Q中不再使用DATA字段，而用RELATIVE_PATH代替
            //RELATIVE_PATH是相对路径不是绝对路径
            //DCIM是系统文件夹，关于系统文件夹可以到系统自带的文件管理器中查看，不可以写没存在的名字
            contentValues.put("relative_path", "DCIM/" + context.getPackageName());

        } else {
            contentValues.put(MediaStore.Images.Media.DATA, context.getExternalFilesDir("").getAbsolutePath() + filename + ".JPEG");
        }
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(context, "保存成功",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "保存失败",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        context.sendBroadcast(intent);
        return true;
    }

    public static String bitmapToFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
