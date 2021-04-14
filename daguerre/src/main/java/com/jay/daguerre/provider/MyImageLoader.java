package com.jay.daguerre.provider;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.jay.daguerre.R;
import com.jay.daguerre.utils.UriUtils;

import java.io.File;

public class MyImageLoader implements ImageLoader {

    private RequestOptions mOptions = new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .format(DecodeFormat.PREFER_RGB_565)
            .placeholder(R.drawable.icon_image_default)
            .error(R.drawable.icon_image_error);

    private RequestOptions mPreOptions = new RequestOptions()
            .skipMemoryCache(true)
            .error(R.drawable.icon_image_error);

    @Override
    public void loadImage(Context context, ImageView imageView, String path, boolean isGif, boolean isVideo) {
        Glide.with(context).load(path).apply(mOptions).into(imageView);
    }

    @Override
    public void loadPreviewImage(Context context, ImageView imageView, String path, boolean isGif, boolean isVideo) {
        Glide.with(context).load(path).apply(mPreOptions).into(imageView);
    }

    @Override
    public void loadAlbumImage(Context context, ImageView imageView, String path) {
        Glide.with(context).load(path).apply(mOptions).into(imageView);
    }
}
