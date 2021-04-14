package com.huihe.base_lib.utils.download;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtil {

    private String fileURl;// 文件URL地址
    private Context mContext;
    private ImageView imageView;
    // bundle key
    public final String KEY_FILE_PROGRESS = "keyFileProgress";
    public final String KEY_FILE_LENGTH = "keyFileLength";
    private static final String KEY_FILE_ERROR = "keyFileError";

    private static final int SAVE_SUCCESS = 0;//保存文件成功
    private static final int SAVE_FAILURE = 1;//保存文件失败
    private static final int SAVE_BEGIN = 2;//开始保存文件
    private static final int SAVE_PROGRESS = 3;// 保存文件进度

    public static final File appDir = new File(Environment.getExternalStorageDirectory(), "bigStward");
    private File currentDir = new File(Environment.getExternalStorageDirectory(), "bigStward");

    private static DownloadUtil intance;

    private DownloadUtil() {

    }

    public static DownloadUtil getIntance() {
        if (intance == null) {
            synchronized (DownloadUtil.class) {
                if (intance == null) {
                    intance = new DownloadUtil();
                }
            }
        }
        return intance;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SAVE_BEGIN:
                    if (onDownloadListener != null) {
                        long fileLength = msg.getData().getLong(KEY_FILE_LENGTH, -1);
                        onDownloadListener.onStart(fileLength);
                    }
                    break;
                case SAVE_SUCCESS:

                    if (onDownloadListener != null) {
                        onDownloadListener.onSuccess(currentDir.getPath().toString());
                    }
                    break;
                case SAVE_FAILURE:
                    if (onDownloadListener != null) {
                        String error = msg.getData().getString(KEY_FILE_ERROR, "");
                        onDownloadListener.onFail(error);
                    }
                    break;
                case SAVE_PROGRESS:
                    if (onDownloadListener != null) {
                        int perValue = msg.getData().getInt(KEY_FILE_PROGRESS, -1);
                        if (perValue > 0 && perValue <= 100) {
                            onDownloadListener.onProgress(perValue);
                        }
                    }
                    break;
            }
        }
    };

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private Runnable mPicDownloadTask = new Runnable() {

        @Override
        public void run() {

            Bitmap bitmap = returnBitMap(fileURl);

            // 首先保存图片
            if (!currentDir.exists()) {
                currentDir.mkdir();
            }
            Message message;
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(currentDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                fos.flush();
                fos.close();
                message = Message.obtain();
                message.what = SAVE_BEGIN;
                Bundle data = new Bundle();
                data.putLong(KEY_FILE_LENGTH, file.length());
                message.setData(data);
                mHandler.sendMessage(message);
            } catch (FileNotFoundException e) {
                message = Message.obtain();
                message.what = SAVE_FAILURE;
                Bundle data = new Bundle();
                data.putString(KEY_FILE_ERROR, e.toString());
                message.setData(data);
                mHandler.sendMessage(message);
                e.printStackTrace();
            } catch (IOException e) {
                message = Message.obtain();
                message.what = SAVE_FAILURE;
                Bundle data = new Bundle();
                data.putString(KEY_FILE_ERROR, e.toString());
                message.setData(data);
                mHandler.sendMessage(message);
                e.printStackTrace();
            }

            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                        file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                message = Message.obtain();
                message.what = SAVE_FAILURE;
                Bundle data = new Bundle();
                data.putString(KEY_FILE_ERROR, e.toString());
                message.setData(data);
                mHandler.sendMessage(message);
                return;
            }
            // 最后通知图库更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            mContext.sendBroadcast(intent);
            mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();
        }
    };

    private Runnable mDownloadTask = new Runnable() {
        @Override
        public void run() {
            try {
                // 保存目录
                if (!currentDir.exists()) {
                    currentDir.mkdir();
                }
                //对资源链接
                URL url = new URL(fileURl);
                URLConnection conn = url.openConnection();
                // 建立链接
                conn.connect();
                //打开输入流
                InputStream inputStream = url.openStream();
                // 获取文件流大小，用于更新进度
                long fileTotallength = conn.getContentLength();
                File file = new File(currentDir, getFileName(fileURl));
                Message message = Message.obtain();
                message.what = SAVE_BEGIN;
                Bundle data = new Bundle();
                data.putLong(KEY_FILE_PROGRESS, inputStream.available());
                mHandler.sendMessage(message);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                int length = 0;
                long totalLength = 0;
                byte[] buf = new byte[1024];
                while ((length = inputStream.read(buf)) != -1) {

                    totalLength += length;
                    message = Message.obtain();
                    data = new Bundle();
                    int perValue = (int) (totalLength * 100f / fileTotallength + 0.5);
                    data.putInt(KEY_FILE_PROGRESS, perValue);
                    message.setData(data);
                    message.what = SAVE_PROGRESS;
                    mHandler.sendMessage(message);
                    fileOutputStream.write(buf, 0, length);
                }
                fileOutputStream.close();
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Message message = Message.obtain();
                message.what = SAVE_FAILURE;
                Bundle data = new Bundle();
                data.putString(KEY_FILE_ERROR, e.toString());
                message.setData(data);
                mHandler.sendMessage(message);
            } catch (IOException e) {

                e.printStackTrace();
                Message message = Message.obtain();
                Bundle data = new Bundle();
                data.putString(KEY_FILE_ERROR, e.toString());
                message.setData(data);
                message.what = SAVE_FAILURE;
                mHandler.sendMessage(message);
            }
            Message message = Message.obtain();
            message.what = SAVE_SUCCESS;
            mHandler.sendMessage(message);
        }
    };

    private String getFileName(String spUrl) {
        int index = spUrl.lastIndexOf("/");
        String substring = spUrl.substring(index);
        return substring;
    }

    /**
     * 保存到本地相册
     */
    public void startDownloadPic(Context context, final String fileUrl, File fileDir, OnDownloadListener onDownloadListener) {

        if (TextUtils.isEmpty(fileUrl)) {
            //Toast.makeText(context,"url不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        mContext = context;
        fileURl = fileUrl;
        currentDir = fileDir;
        setOnDownloadListener(onDownloadListener);
        ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(mPicDownloadTask);
    }

    public void startDownloadFile(Context context, final String fileUrl, File fileDir, OnDownloadListener onDownloadListener) {
        if (TextUtils.isEmpty(fileUrl)) {
            //Toast.makeText(context,"文件url不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        this.mContext = context;
        this.fileURl = fileUrl;
        this.currentDir = fileDir;
        setOnDownloadListener(onDownloadListener);
        //为了下载视频资源，开辟一个新的子线程
        ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(mDownloadTask);
    }

    private OnDownloadListener onDownloadListener;

    private void setOnDownloadListener(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
    }

    public interface OnDownloadListener {

        void onStart(long fileLength);

        void onProgress(int perValue);

        void onSuccess(String path);

        void onFail(String error);
    }
}