package com.jay.daguerre;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.jay.daguerre.internal.ConfigParams;
import com.jay.daguerre.internal.DaguerreActivity;
import com.jay.daguerre.provider.ImageLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jay on 2017/11/23 下午3:03
 * daguerre main class
 */
public final class Daguerre {

    public final static String INTENT_EXTRA_KEY_MAX = "maxSelectable";
    public final static String INTENT_EXTRA_KEY_SPAN_COUNT = "spanCount";
    public final static String INTENT_EXTRA_KEY_THEME = "theme";
    public final static String INTENT_EXTRA_RESULT = "result";

    private Activity mActivity;
    private Fragment mFragment;
    private Intent mIntent;
    private static File mCameraOutPutFile;

    public static File getCameraOutPutFile() {
        return mCameraOutPutFile;
    }

    private Daguerre(Activity activity) {
        mActivity = activity;
        mIntent = new Intent(activity, DaguerreActivity.class);
    }

    private Daguerre(Fragment fragment) {
        mFragment = fragment;
        mIntent = new Intent(fragment.getContext(), DaguerreActivity.class);
    }

    public static Daguerre with(Activity activity) {
        return new Daguerre(activity);
    }

    public static Daguerre with(Fragment fragment) {
        return new Daguerre(fragment);
    }

    /**
     * 设置图片加载器
     *
     * @param imageLoader {@link ImageLoader}
     */
    public Daguerre setImageLoader(ImageLoader imageLoader) {
        ConfigParams.getInstance().setImageLoader(imageLoader);
        return this;
    }

    /**
     * 设置最大选择数据，默认为1
     *
     * @param max value
     */
    public Daguerre maxSelectable(int max) {
        mIntent.putExtra(INTENT_EXTRA_KEY_MAX, max);
        return this;
    }

    /**
     * 设置列表列数
     *
     * @param spanCount 列数
     */
    public Daguerre spanCount(int spanCount) {
        mIntent.putExtra(INTENT_EXTRA_KEY_SPAN_COUNT, spanCount);
        return this;
    }

    /**
     * 设置主题样式
     *
     * @param theme 主题样式资源
     */
    public Daguerre theme(@StyleRes int theme) {
        mIntent.putExtra(INTENT_EXTRA_KEY_THEME, theme);
        return this;
    }

    /**
     * 设置 mine 类型
     *
     * @param mimeTypes mine 类型，可传多个
     */
    public Daguerre mimeType(int mimeType, String... mimeTypes) {
        ConfigParams.getInstance().setMimeType(mimeType, mimeTypes);
        return this;
    }

    /**
     * @return 获得选择结果的第一个，一般单选操作时调用此方法
     */
    public static String obtainResult(Intent data) {
        ArrayList<String> result = data.getStringArrayListExtra(INTENT_EXTRA_RESULT);
        return result.get(0);
    }

    /**
     * @return 获得选择结果集
     */
    public static ArrayList<String> obtainResultSet(Intent data) {
        return data.getStringArrayListExtra(INTENT_EXTRA_RESULT);
    }

    public static void launchTakePhoto(Fragment fragment, int requestCode) {
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(fragment.getContext().getPackageManager()) != null) {
            mCameraOutPutFile = DaguerreActivity.createPhotoFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // 适配 7.0+ 系统
                String packageName = fragment.getContext().getPackageName();
                Context context = fragment.getContext();
                Uri uri = FileProvider.getUriForFile(context,
                        packageName + ".daguerre.fileprovider", mCameraOutPutFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraOutPutFile));
            }
            fragment.startActivityForResult(cameraIntent, requestCode);
        } else {
            Toast.makeText(fragment.getContext(), R.string.daguerre_not_found_camera_app, Toast.LENGTH_SHORT).show();
        }
    }

    public static void launchTakePhoto(AppCompatActivity activity, int requestCode) {
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(activity.getPackageManager()) != null) {
            mCameraOutPutFile = DaguerreActivity.createPhotoFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // 适配 7.0+ 系统
                String packageName = activity.getPackageName();
                Uri uri = FileProvider.getUriForFile(activity,
                        packageName + ".daguerre.fileprovider", mCameraOutPutFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraOutPutFile));
            }
            activity.startActivityForResult(cameraIntent, requestCode);
        } else {
            Toast.makeText(activity, R.string.daguerre_not_found_camera_app, Toast.LENGTH_SHORT).show();
        }
    }

    public static void launchCamera(Fragment fragment, int requestCode) {
        Intent cameraIntent = new Intent();
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        cameraIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        if (cameraIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            mCameraOutPutFile = DaguerreActivity.createVideoFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // 适配 7.0+ 系统
                String packageName = fragment.getActivity().getPackageName();

                Uri uri = FileProvider.getUriForFile(fragment.getActivity(), packageName + ".daguerre.fileprovider", mCameraOutPutFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraOutPutFile));
            }
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            fragment.startActivityForResult(cameraIntent, requestCode);
        } else {
            Toast.makeText(fragment.getActivity(), R.string.daguerre_not_found_camera_app, Toast.LENGTH_SHORT).show();
        }
    }

    public static void launchCamera(Activity activity, int requestCode) {
        Intent cameraIntent = new Intent();
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        cameraIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        if (cameraIntent.resolveActivity(activity.getPackageManager()) != null) {
            mCameraOutPutFile = DaguerreActivity.createVideoFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // 适配 7.0+ 系统
                String packageName = activity.getPackageName();
                Uri uri = FileProvider.getUriForFile(activity, packageName + ".daguerre.fileprovider", mCameraOutPutFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraOutPutFile));
            }
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            activity.startActivityForResult(cameraIntent, requestCode);
        } else {
            Toast.makeText(activity, R.string.daguerre_not_found_camera_app, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 启动库
     *
     * @param requestCode 请求码，用于返回给 {@link Activity#(int, int, Intent)} 判断操作类型
     */
    public void launch(int requestCode) {
        if (mActivity != null) {
            mActivity.startActivityForResult(mIntent, requestCode);
        } else if (mFragment != null) {
            mFragment.startActivityForResult(mIntent, requestCode);
        }
    }
}
