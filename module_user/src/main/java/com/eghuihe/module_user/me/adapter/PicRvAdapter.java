package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.ui.activity.BaseActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PicRvAdapter extends CommonRVAdapter<String> {

    private boolean isEnableClose;

    public final static String UPLOAD = "upload";

    public PicRvAdapter(int layoutId, Context context, List<String> data) {
        super(layoutId, context, data);
        isEnableClose = false;
    }

    @Override
    protected void covert(ViewHolder viewHolder, String path, int position) {
        RoundedImageView ivIcon = viewHolder.getView(R.id.item_note_img_icon);
        ivIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideTools.loadRoundedImage(context, path, DensityUtils.dp2px(context, 12), ivIcon);

        viewHolder.getView(R.id.item_note_img_icon_delete).setVisibility(isEnableClose ? View.GONE : View.VISIBLE);
        viewHolder.setOnClickListener(R.id.item_note_img_icon_delete, new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                remove(position);
            }
        });
        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                Intent intent = new Intent(context, PhotoViewActivity.class);
                intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                List<String> data = getData();
                intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(data));

                int[] location = new int[2];
                v.getLocationOnScreen(location);

                int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                ((BaseActivity) context).startActivity(intent);
            }
        });
    }

    public void enableClose(boolean isEnableClose) {
        this.isEnableClose = isEnableClose;
    }
}
