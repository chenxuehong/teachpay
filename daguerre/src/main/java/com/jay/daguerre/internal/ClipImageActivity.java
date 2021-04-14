package com.jay.daguerre.internal;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jay.daguerre.R;
import com.jay.daguerre.utils.DensityUtils;
import com.jay.daguerre.utils.ImageUtil;
import com.jay.daguerre.utils.StringUtils;
import com.jay.daguerre.utils.VersionUtils;
import com.jay.daguerre.widget.ClipImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ClipImageActivity extends Activity {

    public static final String KET_SELECT_IMAGES = "select_images";
    private FrameLayout btnConfirm;
    private FrameLayout btnBack;
    private ClipImageView imageView;
    private boolean isCameraImage;
    private static String mSelectResource;
    private FrameLayout flStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_clip_image);
        setStatusBarColor();
        initView();
    }

    /**
     * 修改状态栏颜色
     */
    private void setStatusBarColor() {
        if (VersionUtils.isAndroidL()) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#46cecf"));
        }
    }

    private void initView() {
        imageView = findViewById(R.id.process_img);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnBack = findViewById(R.id.btn_back);
        flStatus = findViewById(R.id.clip_image_fl_status);

        ViewGroup.LayoutParams layoutParams = flStatus.getLayoutParams();
        layoutParams.height = DensityUtils.getStatusHeight(this);
        flStatus.setLayoutParams(layoutParams);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = imageView.getCroppedImage();
                btnConfirm.setEnabled(false);
                confirm(bitmap);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bitmap bitmap = ImageUtil.decodeSampledBitmapFromFile(this, mSelectResource,
                DensityUtils.getScreenWidth(this), 1080);
        imageView.setImageBitmap(bitmap);
    }

    private void confirm(Bitmap bitmap) {
        String imagePath = null;
        if (bitmap != null) {
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.getDefault())).toString();
            String path = ImageUtil.getImageCacheDir(this);
            imagePath = ImageUtil.saveImage(bitmap, path, name);
            bitmap.recycle();
            bitmap = null;
        }

        if (StringUtils.isNotEmptyString(imagePath)) {
            ArrayList<String> selectImages = new ArrayList<>();
            selectImages.add(imagePath);
            Intent intent = new Intent();
            intent.putStringArrayListExtra(KET_SELECT_IMAGES, selectImages);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    /**
     * 启动图片选择器
     *
     * @param activity
     * @param requestCode
     */
    public static void openActivity(Activity activity, int requestCode,String selectResource) {
        mSelectResource = selectResource;
        Intent intent = new Intent(activity, ClipImageActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动图片选择器
     *
     * @param fragment
     * @param requestCode
     */
    public static void openActivity(Fragment fragment, int requestCode,String selectResource) {
        mSelectResource = selectResource;
        Intent intent = new Intent(fragment.getActivity(), ClipImageActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动图片选择器
     *
     * @param fragment
     * @param requestCode
     */
    public static void openActivity(android.app.Fragment fragment, int requestCode,String selectResource) {
        mSelectResource = selectResource;
        Intent intent = new Intent(fragment.getActivity(), ClipImageActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }
}
