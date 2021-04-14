package com.huihe.base_lib.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.huihe.base_lib.R;
import com.huihe.base_lib.utils.DensityUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by bruce on 2017/2/10.
 */
public class GlideTools {


    private static CornersTransformation cornersTransformation;

    public static void loadImageFinish(Context context, Object imageUrl, final ImageView imageView, final OnLoadListener onLoadListener) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                        if (onLoadListener != null) {
                            onLoadListener.loadfinish();
                        }
                    }
                });
    }

    public interface OnLoadListener {
        void loadfinish();
    }

    public static void loadImage(Context context, Object imageUrl, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.color.color_cccccc)
                .dontAnimate()
                .error(R.color.color_cccccc)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImage(Context context, Object imageUrl, ImageView imageView, int placeHolder, int error) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeHolder)
                .dontAnimate()
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void clear(Context context, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .clear(imageView);
    }

    public static void loadFitImage(final Context context, Object imageUrl, final ImageView imageView) {
        if (context == null) {
            return;
        }

        Glide.with(context)
                .load(imageUrl)
                .thumbnail(0.5f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }
                })
                .placeholder(R.color.color_cccccc)
                .error(R.color.color_cccccc)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        int width = resource.getIntrinsicWidth();//图片原始宽度
                        int height = resource.getIntrinsicHeight();//图片原始高度
                        int scaledW = DensityUtils.getScreenWidth(context);//固定图片展示宽度
                        int scaledH = (height * scaledW) / width;//计算出按比缩放后的宽度

                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.width = scaledW;
                        layoutParams.height = scaledH;
                        imageView.setLayoutParams(layoutParams);
                        imageView.setImageDrawable(resource);
                    }
                });

    }

    public static void loadFitWidthImage(final Context context, final Object imageUrl, final int radius, final ImageView imageView) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.ALL);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(cornersTransformation);
        Glide.with(context)
                .load(imageUrl)
                .thumbnail(0.5f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.color_e6e6e6)
                .error(R.color.color_e6e6e6)
                .apply(requestOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        int width = resource.getIntrinsicWidth();//图片原始宽度
                        int height = resource.getIntrinsicHeight();//图片原始高度
                        int scaledH = layoutParams.height;
                        int scaledW = (width * scaledH) / height;//计算出按比缩放后的宽度
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        layoutParams.width = scaledW;
                        layoutParams.height = scaledH;
                        imageView.setLayoutParams(layoutParams);
                        imageView.setImageDrawable(resource);
                    }
                });

    }

    public static void loadGifImage(Context context, Object imageUrl, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(imageUrl)
                .into(imageView);
    }

    public static void loadImage(Context context, Object imageUrl, ImageView imageView, int color) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .placeholder(color)
                .error(color)
                .thumbnail(0.5f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImageComplete(Context context, Object imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.color.color_cccccc)
                .error(R.color.color_cccccc)
                .thumbnail(0.5f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadHeadImage(Context context, Object imageUrl, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.color.color_cccccc)
                .error(R.color.color_cccccc)
                .thumbnail(0.5f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView) {
        if (context == null || imageUrl == null || imageView == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.ALL);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(cornersTransformation);
        Glide.with(context)
                .load(imageUrl)
                .thumbnail(0.5f)
                .placeholder(R.color.color_cccccc)
                .error(R.color.color_cccccc)
                .apply(requestOptions)
                .dontAnimate()
                .into(imageView);
    }

    public static void loadTopRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.TOP);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadLeftTopRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.TOP_LEFT);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadRightTopRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.TOP_RIGHT);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadRightBottomRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.BOTTOM_RIGHT);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadLeftBottomRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.BOTTOM_LEFT);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadLeftRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.LEFT);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadRightRoundedImage(Context context, Object imageUrl, int radius, ImageView imageView, int placeColor) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.RIGHT);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeColor)
                .thumbnail(0.5f)
                .error(placeColor)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
//                .dontAnimate()
                .into(imageView);
    }

    public static void loadRoundedHeadImage(Context context, Object imageUrl, int radius, ImageView imageView) {
        if (context == null) {
            return;
        }
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.ALL);
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.color.color_cccccc)
                .error(R.color.color_cccccc)
                .thumbnail(0.5f)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                //.bitmapTransform(new CenterCrop(context), new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL))
                .dontAnimate()
                .into(imageView);
    }

    public static void loadRoundedImageFromFile(Context context, String file, int radius, ImageView imageView) {
        cornersTransformation = null;
        cornersTransformation = new CornersTransformation(context, radius, 0, CornersTransformation.CornerType.ALL);
        Glide.with(context)
                .load(new File(file))
                .placeholder(R.color.color_cccccc)
                .error(R.color.color_cccccc)
                .centerCrop()
                .thumbnail(0.5f)
                .apply(RequestOptions.bitmapTransform(cornersTransformation))
                //.bitmapTransform(new CenterCrop(context), new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL))
                .dontAnimate()
                .into(imageView);
    }

    public static void resumeRequest(Context context) {

        Glide.with(context).resumeRequests();
    }

    public static void pauseRequest(Context context) {

        Glide.with(context).pauseRequests();
    }

    public static void clear(Context context, View view) {
        if (context == null || view == null) {
            return;
        }
        Glide.with(context).clear(view);
    }

    public static void setNetVideoBitmap(final String videoUrl, final ImageView ivPic) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                try {
                    //根据url获取缩略图
                    retriever.setDataSource(videoUrl, new HashMap());
                    //获得第一帧图片
                    bitmap = retriever.getFrameAtTime();
                    Message message = new Message();
                    message.what = 1;
                    message.obj = ivPic;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("msg", bitmap);
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                ImageView imageView = (ImageView) msg.obj;
                final Bitmap bitmap = msg.getData().getParcelable("msg");
                imageView.setImageBitmap(bitmap);
            }
            return false;
        }
    });

}