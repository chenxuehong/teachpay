package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bm.library.PhotoView;
import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.utils.PhotoTools;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.AppManager;

import java.util.List;


public class GalleryPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> urls;
    private AppCompatActivity activity;
    public GalleryPagerAdapter(Context context, List<String> urls, AppCompatActivity activity) {
        this.context = context;
        this.urls = urls;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return urls != null ? urls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        PhotoView p = new PhotoView(context);
        p.setScaleType(ImageView.ScaleType.FIT_CENTER);
        p.enable();
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        p.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSaveImgDialog(context, urls.get(position));
                return false;
            }
        });
        GlideTools.loadImage(context, urls.get(position), (ImageView) p);
        container.addView(p);
        return p;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private void showSaveImgDialog(final Context context, final String url) {
        View rootView = activity.getWindow().getDecorView().getRootView();
        final CustomPopupWindow customPopupWindow = PopWindowUtils.popBottomDialog(rootView, AppManager.getInstance().currentActivity(), R.layout.layout_save_gallery, false);
        customPopupWindow.setOnClickListener(R.id.layout_save_gallery_tv_save, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoTools.saveBitmap(context, url);
                customPopupWindow.dismiss();
            }
        });
        customPopupWindow.setOnClickListener(R.id.layout_save_gallery_tv_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopupWindow.dismiss();
            }
        });
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
