package com.huihe.base_lib.utils.VideoThumbLoader;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.LruCache;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.glide.GlideTools;

public class MyVideoThumbLoader {
    // 创建cache
    private LruCache<String, Bitmap> lruCache;
    private Bitmap placebitmap;

    public MyVideoThumbLoader() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();// 获取最大的运行内存
        int maxSize = maxMemory / 4;
        // 拿到缓存的内存大小
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 这个方法会在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    private void addVideoThumbToCache(String path, Bitmap bitmap) {
        if (getVideoThumbToCache(path) == null) {
            // 当前地址没有缓存时，就添加

            lruCache.put(path, bitmap);
        }
    }

    private Bitmap getVideoThumbToCache(String path) {

        return lruCache.get(path);

    }

    public void showThumbByAsynctack(String path, MyImageView imgview) {
        placebitmap = android.graphics.BitmapFactory.decodeResource(
                BaseApplication.getInstance().getResources(),
                R.color.color_cccccc);
        GlideTools.loadImage(BaseApplication.getInstance(), "", imgview);
        if (getVideoThumbToCache(path) == null) {
            // 异步加载
            new MyBobAsynctack(imgview, path).execute(path);
        } else {
            imgview.setImageBitmap(getVideoThumbToCache(path));
        }

    }

    class MyBobAsynctack extends AsyncTask<String, Void, Bitmap> {
        private MyImageView imgView;
        private String path;

        public MyBobAsynctack(MyImageView imageView, String path) {
            this.imgView = imageView;
            this.path = path;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = placebitmap;
            try {
                bitmap = VideoUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
                System.out.println("111111path: " + path + "  bitmap: "
                        + bitmap);

//                // //直接对Bitmap进行缩略操作，最后一个参数定义为OPTIONS_RECYCLE_INPUT ，来回收资源
//
//                Bitmap bitmap2 = tu.extractThumbnail(bitmap, 50, 50,
//                        ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
//                System.out.println("path: " + path + "bitmap2: " + bitmap2);

                // 加入缓存中
                if (getVideoThumbToCache(params[0]) == null) {
                    addVideoThumbToCache(path, bitmap);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {// 通过 Tag可以绑定 图片地址和
                // imageView，这是解决Listview加载图片错位的解决办法之一
                imgView.setImageBitmap(bitmap);
            }
//            imgView.setImageBitmap(bitmap);
        }
    }
}
