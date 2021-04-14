package com.huihe.base_lib.utils.select;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.PhotoTools;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.imageview.BitmapUtils;
import com.jay.daguerre.Daguerre;
import com.jay.daguerre.MimeType;
import com.jay.daguerre.provider.MyImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PhotoSelectUtils {

    private static Context mContext;
    private static OnCallBack mOnCallBack;
    private static CustomPopupWindow customPopupWindow;

    public static CustomPopupWindow getCustomPopupWindow() {
        return customPopupWindow;
    }

    public static void selectPic(final AppCompatActivity activity, OnCallBack onCallBack) {
        mContext = activity;
        mOnCallBack = onCallBack;
        MPermission.with(activity)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        View rootview = ((BaseActivity) mContext).getWindow().getDecorView().getRootView();
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootview, mContext, R.layout.select_pic, true);
                        customPopupWindow.setOnClickListener(R.id.select_take, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //仅拍照
                                if (activity != null) {
                                    Daguerre.launchTakePhoto(activity, PhotoTools.GET_IMAGE_BY_CAMERA);
                                }
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //单选并剪裁
                                if (activity != null) {
                                    Daguerre.with(activity)
                                            .spanCount(4)
                                            .maxSelectable(1)
                                            .mimeType(MimeType.IMAGE, MimeType.JPEG, MimeType.PNG, MimeType.WEBM, MimeType.GIF, MimeType.JPG, MimeType.BMP)
                                            .setImageLoader(new MyImageLoader())
                                            .launch(PhotoTools.GET_IMAGE_FROM_PHONE);
                                }

                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static void selectPic(final Fragment fragment, OnCallBack onCallBack) {
        mContext = fragment.getContext();
        mOnCallBack = onCallBack;

        MPermission.with((BaseActivity) mContext)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        View rootview = ((BaseActivity) mContext).getWindow().getDecorView().getRootView();
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootview, mContext, R.layout.select_pic, true);
                        customPopupWindow.setOnClickListener(R.id.select_take, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //仅拍照
                                if (fragment != null) {
                                    Daguerre.launchTakePhoto(fragment, PhotoTools.GET_IMAGE_BY_CAMERA);
                                }

                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //单选并剪裁
                                if (fragment != null) {

                                    Daguerre.with(fragment)
                                            .spanCount(4)
                                            .maxSelectable(1)
                                            .mimeType(MimeType.IMAGE, MimeType.JPEG, MimeType.PNG, MimeType.WEBM, MimeType.GIF, MimeType.JPG, MimeType.BMP)
                                            .setImageLoader(new MyImageLoader())
                                            .launch(PhotoTools.GET_IMAGE_FROM_PHONE);
                                }

                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static void selectMutiPic(final AppCompatActivity activity, OnCallBack onCallBack) {
        mContext = activity;
        mOnCallBack = onCallBack;
        MPermission.with(activity)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        View rootview = ((BaseActivity) mContext).getWindow().getDecorView().getRootView();
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootview, mContext, R.layout.select_pic, true);
                        customPopupWindow.setOnClickListener(R.id.select_take, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //仅拍照
                                if (activity != null) {
                                    Daguerre.launchTakePhoto(activity, PhotoTools.GET_IMAGE_BY_CAMERA);
                                }
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //限数量的多选
                                Daguerre.with(activity)
                                        .spanCount(4)
                                        .maxSelectable(6)
                                        .mimeType(MimeType.IMAGE, MimeType.JPEG, MimeType.PNG, MimeType.WEBM, MimeType.GIF, MimeType.JPG, MimeType.BMP)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_MUTI_IMAGE_FROM_PHONE);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static void selectMutiPicOrVideo(final AppCompatActivity activity, OnCallBack onCallBack) {
        mContext = activity;
        mOnCallBack = onCallBack;
        MPermission.with(activity)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        View rootview = ((BaseActivity) mContext).getWindow().getDecorView().getRootView();
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootview, mContext, R.layout.select_video_or_photo, true);
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //限数量的多选
                                Daguerre.with(activity)
                                        .spanCount(4)
                                        .maxSelectable(6)
                                        .mimeType(MimeType.IMAGE, MimeType.JPEG, MimeType.PNG, MimeType.WEBM, MimeType.GIF, MimeType.JPG, MimeType.BMP)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_MUTI_IMAGE_FROM_PHONE);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_video, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 选择视频
                                Daguerre.with(activity)
                                        .spanCount(2)
                                        .maxSelectable(1)
                                        .mimeType(MimeType.VIDEO,
                                                MimeType.MP4, MimeType.M4V,
                                                MimeType.MKV, MimeType.MPEG,
                                                MimeType.WMV, MimeType.WEBM,
                                                MimeType.MOV, MimeType.THREEGP,
                                                MimeType.MPG)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_VIDEO_FROM_PHOTO);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static void selectMutiPicOrVideo(final Fragment fragment, OnCallBack onCallBack) {
        mContext = fragment.getContext();
        mOnCallBack = onCallBack;
        MPermission.with(fragment)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        View rootview = ((Activity) mContext).getWindow().getDecorView().getRootView();
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootview, mContext, R.layout.select_video_or_photo, true);
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //限数量的多选
                                Daguerre.with(fragment)
                                        .spanCount(4)
                                        .maxSelectable(6)
                                        .mimeType(MimeType.IMAGE, MimeType.JPEG, MimeType.PNG, MimeType.WEBM, MimeType.GIF, MimeType.JPG, MimeType.BMP)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_MUTI_IMAGE_FROM_PHONE);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_video, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 选择视频
                                Daguerre.with(fragment)
                                        .spanCount(2)
                                        .maxSelectable(1)
                                        .mimeType(MimeType.VIDEO,
                                                MimeType.MP4, MimeType.M4V,
                                                MimeType.MKV, MimeType.MPEG,
                                                MimeType.WMV, MimeType.WEBM,
                                                MimeType.MOV, MimeType.THREEGP,
                                                MimeType.MPG)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_VIDEO_FROM_PHOTO);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static void selectMutiPicOrVideo(final Fragment fragment,final View rootView, OnCallBack onCallBack) {
        mContext = fragment.getContext();
        mOnCallBack = onCallBack;
        MPermission.with(fragment)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootView, mContext, R.layout.select_video_or_photo, true);
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //限数量的多选
                                Daguerre.with(fragment)
                                        .spanCount(4)
                                        .maxSelectable(6)
                                        .mimeType(MimeType.IMAGE, MimeType.JPEG, MimeType.PNG, MimeType.WEBM, MimeType.GIF, MimeType.JPG, MimeType.BMP)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_MUTI_IMAGE_FROM_PHONE);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_video, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 选择视频
                                Daguerre.with(fragment)
                                        .spanCount(2)
                                        .maxSelectable(1)
                                        .mimeType(MimeType.VIDEO,
                                                MimeType.MP4, MimeType.M4V,
                                                MimeType.MKV, MimeType.MPEG,
                                                MimeType.WMV, MimeType.WEBM,
                                                MimeType.MOV, MimeType.THREEGP,
                                                MimeType.MPG)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_VIDEO_FROM_PHOTO);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static void selectVideo(final Context context, OnCallBack onCallBack) {
        mContext = context;
        mOnCallBack = onCallBack;
        MPermission.with(context)
                .setPermission(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE})
                .requestPermission(new MPermission.OnCallBack() {
                    @Override
                    public void valdateSuccess() {
                        View rootview = ((BaseActivity) context).getWindow().getDecorView().getRootView();
                        customPopupWindow = PopWindowUtils.popBottomDialog(rootview, context, R.layout.select_video, true);
                        customPopupWindow.setOnClickListener(R.id.select_take, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 拍摄
                                Daguerre.launchCamera((BaseActivity) context, PhotoTools.GET_VIDEO_BY_CAMERA);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_pick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 选择视频
                                Daguerre.with((BaseActivity) context)
                                        .spanCount(2)
                                        .maxSelectable(1)
                                        .mimeType(MimeType.VIDEO,
                                                MimeType.MP4, MimeType.M4V,
                                                MimeType.MKV, MimeType.MPEG,
                                                MimeType.WMV, MimeType.WEBM,
                                                MimeType.MOV, MimeType.THREEGP,
                                                MimeType.MPG)
                                        .setImageLoader(new MyImageLoader())
                                        .launch(PhotoTools.GET_VIDEO_FROM_PHOTO);
                            }
                        });
                        customPopupWindow.setOnClickListener(R.id.select_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopupWindow.dismiss();
                            }
                        });
                    }

                    @Override
                    public void valdateFail() {

                    }
                });
    }

    public static class OnCallBack {
        protected void updateLoadImage(String path) {
        }

        protected void updateLoadImage(List<String> picList) {
        }

        protected void updateLoadVideo(String path) {
        }

        protected void updateLoadVideo(List<String> videoList) {
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoTools.GET_IMAGE_BY_CAMERA:
                    if (mOnCallBack != null) {
                        File cameraOutPutFile = Daguerre.getCameraOutPutFile();
                        if (cameraOutPutFile != null) {
                            ArrayList<String> compressImages = new ArrayList<>();
                            String compressImage = BitmapUtils.compressImage(cameraOutPutFile.getAbsolutePath());
                            compressImages.add(compressImage);
                            if (compressImages != null && compressImages.size() > 0)
                                mOnCallBack.updateLoadImage(compressImages.get(0));
                        }
                    }
                    break;
                case PhotoTools.GET_IMAGE_FROM_PHONE:
                    if (data != null) {
                        //获取选择器返回的数据
                        ArrayList<String> images = Daguerre.obtainResultSet(data);
                        if (mOnCallBack != null && images != null && images.size() > 0) {

                            ArrayList<String> compressImages = new ArrayList<>();
                            for (int i = 0; i < images.size(); i++) {
                                String compressImage = BitmapUtils.compressImage(images.get(i));
                                compressImages.add(compressImage);
                            }
                            mOnCallBack.updateLoadImage(compressImages.get(0));
                        }
                    }
                    break;
                case PhotoTools.GET_MUTI_IMAGE_FROM_PHONE:
                    if (data != null) {

                        if (mOnCallBack != null) {
                            //获取选择器返回的数据
                            ArrayList<String> images = Daguerre.obtainResultSet(data);
                            ArrayList<String> compressImages = new ArrayList<>();
                            for (int i = 0; i < images.size(); i++) {
                                String compressImage = BitmapUtils.compressImage(images.get(i));
                                compressImages.add(compressImage);
                            }
                            mOnCallBack.updateLoadImage(compressImages);
                        }
                    }
                    break;
                case PhotoTools.GET_VIDEO_BY_CAMERA:
                    if (mOnCallBack != null) {
                        File cameraOutPutFile = Daguerre.getCameraOutPutFile();
                        if (cameraOutPutFile != null) {
                            String realVideoAbsolutePath = cameraOutPutFile.getAbsolutePath();
                            mOnCallBack.updateLoadVideo(realVideoAbsolutePath);
                        }
                    }
                    break;
                case PhotoTools.GET_VIDEO_FROM_PHOTO:
                    ArrayList<String> images = Daguerre.obtainResultSet(data);
                    if (mOnCallBack != null && images != null && images.size() > 0) {
                        if (images.size() == 1) {
                            mOnCallBack.updateLoadVideo(images.get(0));
                        } else {
                            mOnCallBack.updateLoadVideo(images);
                        }
                    }
                    break;
            }

            if (customPopupWindow != null) {
                customPopupWindow.dismiss();
            }
        }
    }
}
