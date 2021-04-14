package com.huihe.base_lib.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.download.ThreadPoolProxyFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class PhotoTools {

    private static final String TAG = PhotoTools.class.getSimpleName();
    public static final int GET_IMAGE_BY_CAMERA = 5001; // 拍照
    public static final int GET_IMAGE_FROM_PHONE = 5002;  // 选择单张图片
    public static final int GET_MUTI_IMAGE_FROM_PHONE = 5003;  // 选择多张张图片
    public static final int GET_VIDEO_BY_CAMERA = 5004;
    public static final int GET_VIDEO_FROM_PHOTO = 5005;
    public static Uri imageUriFromCamera;
    public static Uri videoUriFromCamera;
    private static String img;

    public static void openCameraImage(final AppCompatActivity activity) {

        imageUriFromCamera = createImagePathUri(activity);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // MediaStore.EXTRA_OUTPUT参数不设置时,系统会自动生成一个uri,但是只会返回一个缩略图
        // 返回图片在onActivityResult中通过以下代码获取
        // Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        activity.startActivityForResult(intent, GET_IMAGE_BY_CAMERA);
    }

    public static void openCameraImage(final Fragment fragment) {
        imageUriFromCamera = createImagePathUri(fragment.getContext());

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // MediaStore.EXTRA_OUTPUT参数不设置时,系统会自动生成一个uri,但是只会返回一个缩略图
        // 返回图片在onActivityResult中通过以下代码获取
        // Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        fragment.startActivityForResult(intent, GET_IMAGE_BY_CAMERA);
    }

    public static void openLocalImage(final AppCompatActivity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }

    public static void openLocalImage(final Fragment fragment) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }

    /**
     * 创建一条图片地址uri,用于保存拍照后的照片
     *
     * @param context
     * @return 图片的uri
     */
    public static Uri createImagePathUri(final Context context) {
        final Uri[] imageFilePath = {null};

        //拍照前保存一条uri的地址
        String status = Environment.getExternalStorageState();
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String imageName = timeFormatter.format(new Date().getTime());
        File file;
        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
            file = new File(Environment.getExternalStorageDirectory(), imageName + ".jpg");
        } else {
            file = new File(context.getExternalCacheDir(), imageName + ".jpg");
        }
        imageFilePath[0] = getUriForFile(context, file);
        LogUtils.i(TAG, "生成的照片输出路径：" + imageFilePath[0].toString());

        return imageFilePath[0];
    }

    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;

        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果SDK版本>=24，即：Build.VERSION.SDK_INT >= 24
            String packageName = context.getPackageName();
            uri = FileProvider.getUriForFile(context, packageName, file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    public static String getRealImageAbsolutePath(Context context, Uri imageUri) {
        String picPath;
        if (Build.VERSION.SDK_INT >= 24) {
            picPath = getFilePathFromURI(context, imageUri);
        } else {
            picPath = getImageAbsolutePath(context, imageUri);
        }
        return picPath;
    }

    public static String getRealVideoAbsolutePath(Context context, Uri uri) {
        String videoPath;
        if (Build.VERSION.SDK_INT >= 24) {
            videoPath = getVideoFilePathFromURI(context, uri);
        } else {
            videoPath = getImageAbsolutePath(context, uri);
        }
        return videoPath;
    }

    private static String getFilePathFromURI(Context context, Uri imageUri) {
        File rootDataDir = context.getFilesDir();
        String fileName = getFileName(imageUri);
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(rootDataDir + File.separator + fileName + ".jpg");
            copyFile(context, imageUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    private static String getVideoFilePathFromURI(Context context, Uri imageUri) {
        File rootDataDir = context.getFilesDir();
        String fileName = getFileName(imageUri);
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(rootDataDir + File.separator + fileName);
            copyFile(context, imageUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    private static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return System.currentTimeMillis() + fileName;
    }

    private static void copyFile(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copyStream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int copyStream(InputStream input, OutputStream output) throws Exception, IOException {
        final int BUFFER_SIZE = 1024 * 2;
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
            }
            try {
                in.close();
            } catch (IOException e) {
            }
        }
        return count;
    }

    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     *
     * @param context
     * @param imageUri
     * @author yaoxing
     * @date 2014-10-12
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null) {
            return null;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri)) {
                return imageUri.getLastPathSegment();
            }
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static String imageTranslateUri(Context context, int resId) {
        if (context == null) {
            return "";
        }
        Resources r = context.getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri.toString();
    }

    /**
     * 创建一条视频地址uri,用于保存拍摄后的视频
     *
     * @param context
     * @return 图片的uri
     */
    public static Uri createVideoPathUri(final Context context) {
        final Uri[] imageFilePath = {null};

        //拍照前保存一条uri的地址
        String status = Environment.getExternalStorageState();
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String videoName = timeFormatter.format(new Date().getTime());
        File file;
        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
            file = new File(context.getExternalCacheDir(), videoName + ".mp4");
        } else {
            file = new File(Environment.getExternalStorageDirectory(), videoName + ".mp4");
        }
        imageFilePath[0] = getUriForFile(context, file);
        LogUtils.i(TAG, "生成的视频输出路径：" + imageFilePath[0].toString());

        return imageFilePath[0];
    }

    public static void shootVideo(AppCompatActivity context) {

        videoUriFromCamera = createVideoPathUri(context);

        //调用系统相机
        Intent intentVideo = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intentVideo.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intentVideo.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intentVideo.addCategory(Intent.CATEGORY_DEFAULT);
        //将拍照结果保存至photo_file的Uri中
        intentVideo.putExtra(MediaStore.EXTRA_OUTPUT, videoUriFromCamera);
        context.startActivityForResult(intentVideo, GET_VIDEO_BY_CAMERA);
    }

    public static void choiceVideo(AppCompatActivity context) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivityForResult(intent, GET_VIDEO_FROM_PHOTO);
    }

    public static void saveBitmap(final Context context, final String path) {

        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    //打开输入流
                    InputStream inputStream = url.openStream();
                    //对网上资源进行下载转换位图图片
                    Bitmap mBitmap = BitmapFactory.decodeStream(inputStream);
                    saveBitmap(context, mBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void SaveBitmapFromView(Context context, View view) {
        int w = view.getWidth();
        int h = view.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        view.layout(0, 0, w, h);
        view.draw(c);
        // 缩小图片
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f); //长和宽放大缩小的比例
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        saveBitmap(context, bmp);
    }

    /*
     * 保存文件，文件名为当前日期
     */
    public static void saveBitmap(final Context context, Bitmap bitmap) {
        try {
            String fileDir = null;
            if (Build.BRAND.equals("Xiaomi")) { // 小米手机
                fileDir = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";
            } else {  // Meizu 、Oppo
                fileDir = Environment.getExternalStorageDirectory().getPath() + "/DCIM";
            }
            File dirFile = new File(fileDir);
            if (!dirFile.exists()) {              //如果不存在，那就建立这个文件夹
                dirFile.mkdirs();
            }

            Date date = new Date();
            long time = date.getTime();
            String bitName = time + ".jpg";
            File file = new File(fileDir, bitName);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShortToast(context, context.getResources().getString(R.string.save_pic_to_camera));
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
