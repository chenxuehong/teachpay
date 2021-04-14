package com.huihe.base_lib.utils.glide;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

public class UniformScaleTransformation extends ImageViewTarget<Bitmap> {
    private ImageView target;
    private boolean autoFitHeight;

    public UniformScaleTransformation(ImageView target,boolean autoFitHeight) {
        super(target);
        this.target = target;
        this.autoFitHeight = autoFitHeight;
    }

    @Override
    protected void setResource(Bitmap resource) {

        if(resource == null){
            return;
        }

        view.setImageBitmap(resource);
        //获取原图的宽高
        int width = resource.getWidth();
        int height = resource.getHeight();
        if (autoFitHeight){
            //获取imageView的宽
            int imageViewWidth = target.getWidth();

            //计算缩放比例
            float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);

            //计算图片等比例放大后的高
            int imageViewHeight = (int) (height * sy);
            ViewGroup.LayoutParams params = target.getLayoutParams();
            params.height = imageViewHeight;
            target.setLayoutParams(params);
        }else {
            //获取imageView的宽
            int imageViewHeight = target.getHeight();

            //计算缩放比例
            float sy = (float) (imageViewHeight * 0.1) / (float) (width * 0.1);

            //计算图片等比例放大后的高
            int imageViewWidth = (int) (height * sy);
            ViewGroup.LayoutParams params = target.getLayoutParams();
            params.width = imageViewWidth;
            target.setLayoutParams(params);
        }

    }
}
