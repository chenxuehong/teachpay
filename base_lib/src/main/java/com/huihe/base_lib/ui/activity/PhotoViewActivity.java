package com.huihe.base_lib.ui.activity;

import android.content.Intent;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.adapter.GalleryPagerAdapter;
import com.huihe.base_lib.ui.widget.fitViewPager.MyViewPager;
import com.huihe.base_lib.ui.widget.fitViewPager.transformers.GalleryTransformer;
import com.huihe.base_lib.utils.JsonUtil;

import java.util.List;

public class PhotoViewActivity extends BaseAnimActivity {

    private MyViewPager myViewPager;
    private TextView tvCount;
    private int currentPosition;
    private List<String> imgs;

    public static final String CURRENT_POSITION = "currentPosition";
    public static final String URLS = "urls";
    @Override
    protected int getLayoutId() {
        return R.layout.acivity_photoview;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.black;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void initView() {
        myViewPager = findViewById(R.id.activity_photoview_viewpager);
        tvCount = findViewById(R.id.activity_photoview_tv_img_count);
        myViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tvCount.setText(currentPosition + 1 + "/" + imgs.size());
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra(CURRENT_POSITION, 0);
        String urls = intent.getStringExtra(URLS);
        imgs = JsonUtil.fromJson(urls, new TypeToken<List<String>>() {
        }.getType());

        myViewPager.setPageTransformer(true, new GalleryTransformer());
        GalleryPagerAdapter galleryPagerAdapter = new GalleryPagerAdapter(this, imgs, this);
        myViewPager.setAdapter(galleryPagerAdapter);
        myViewPager.setOffscreenPageLimit(3);
        myViewPager.setCurrentItem(currentPosition, false);
        tvCount.setText((currentPosition + 1) + "/" + imgs.size());

    }
}
