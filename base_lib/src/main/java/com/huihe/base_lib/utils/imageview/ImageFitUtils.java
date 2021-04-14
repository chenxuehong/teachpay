package com.huihe.base_lib.utils.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageFitUtils {

    /**
     * 重新调整适配 ImageView的宽
     *
     * @param context
     * @param imgResId
     * @param view
     */
    public static void fitWidth(@NonNull Context context, int imgResId, @NonNull ImageView view) {

        view.measure(0, 0);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeResource(context.getResources(), imgResId, opts);
        int outWidth = opts.outWidth;
        int outHeight = opts.outHeight;

        int measuredHeight = view.getMeasuredHeight();
        float scale = outWidth * 1f / outHeight;
        int ivWidth = (int) (scale * measuredHeight + 0.5f);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = ivWidth;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 重新调整适配 ImageView的高
     *
     * @param context
     * @param imgResId
     * @param view
     */
    public static void fitHeight(@NonNull Context context, int imgResId, @NonNull ImageView view) {

        view.measure(0, 0);

        float scale = getImageScale(context, imgResId);
        int measuredWidth = view.getMeasuredWidth();
        int ivHeight = (int) (scale * measuredWidth + 0.5f);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = ivHeight;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 重新调整适配 ImageView的高
     *
     * @param context
     * @param imgResId
     * @param imageViewWidth
     * @param ivIcon
     */
    public static void fitHeight(@NonNull Context context, int imgResId, int imageViewWidth, ImageView ivIcon) {

        float imageScale = ImageFitUtils.getImageScale(context, imgResId);
        int imageViewHeight = (int) (imageViewWidth * imageScale + 0.5f);
        ViewGroup.LayoutParams layoutParams = ivIcon.getLayoutParams();
        layoutParams.height = imageViewHeight;
        layoutParams.width = imageViewWidth;
        ivIcon.setLayoutParams(layoutParams);
        ivIcon.setImageResource(imgResId);
    }

    /**
     * @param context
     * @param imgResId
     * @return
     * @desc 图片高宽比
     */
    public static float getImageScale(@NonNull Context context, int imgResId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeResource(context.getResources(), imgResId, opts);
        int outWidth = opts.outWidth;
        int outHeight = opts.outHeight;
        return outHeight * 1f / outWidth;
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        Log.i("", w + "---------------" + h);
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 512f;
        float ww = 512f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        //return bitmap;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
