package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.AutoFitImageView;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;

public class CourseDescriptionRvAdapter extends EmptyRVAdapter<String> {

    public CourseDescriptionRvAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    public CourseDescriptionRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final String s, final int position) {
        ImageView ivCover = viewHolder.getView(R.id.item_desc_img_iv_icon);
        GlideTools.loadImage(context, s, ivCover);
        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                Intent intent = new Intent(context, PhotoViewActivity.class);
                intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(getData()));
                int[] location = new int[2];
                v.getLocationOnScreen(location);

                int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                context.startActivity(intent);
            }
        });
    }
}
