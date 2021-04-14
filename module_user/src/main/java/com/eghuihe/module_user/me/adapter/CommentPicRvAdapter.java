package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CommentPicRvAdapter extends CommonRVAdapter<String> {

    public CommentPicRvAdapter(int layoutId, Context context, List<String> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, String photo, int position) {
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        layoutParams.height = (DensityUtils.getScreenWidth(context) - DensityUtils.dp2px(context, 72)) / 3;
        viewHolder.itemView.setLayoutParams(layoutParams);
        RoundedImageView ivPic = viewHolder.getView(R.id.item_mechanism_course_comment_pic_iv);
        GlideTools.loadImage(context, photo, ivPic);
        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoViewActivity.class);
                intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                List<String> data = getData();
                intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(data));

                int[] location = new int[2];
                view.getLocationOnScreen(location);
                int centerX = (int) (location[0] + view.getMeasuredWidth() / 2);
                int centery = (int) (location[1] + view.getMeasuredHeight() / 2);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                context.startActivity(intent);
            }
        });
    }
}
