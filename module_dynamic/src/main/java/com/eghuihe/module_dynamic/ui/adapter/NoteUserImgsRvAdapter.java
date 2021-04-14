package com.eghuihe.module_dynamic.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.ui.activity.PlayerActivity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;

import java.util.List;

public class NoteUserImgsRvAdapter extends CommonRVAdapter<String> {
    private int style;
    private List<String> picList;
    public NoteUserImgsRvAdapter(int layoutId, Context context, List<String> data, int style, List<String> picList) {
        super(layoutId, context, data);
        this.style = style;
        this.picList = picList;
    }

    @Override
    protected void covert(ViewHolder viewHolder,final String path, int position) {

        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (picList.size() == 1) {
            layoutParams.height = DensityUtils.dp2px(context, 160);
        } else {
            layoutParams.height = DensityUtils.getScreenWidth(context) / 2 - DensityUtils.dp2px(context, 12) * 2;
        }
        viewHolder.itemView.setLayoutParams(layoutParams);

        if (style==0){
            viewHolder.setVisible(R.id.item_dynamic_img_play,true);
        }else {
            viewHolder.setVisible(R.id.item_dynamic_img_play,false);
        }
        ImageView ivCover = viewHolder.getView(R.id.item_dynamic_img_iv_cover);
        GlideTools.loadImage(context,path,ivCover);
        viewHolder.setText(R.id.item_dynamic_img_tv_count,String.valueOf(picList.size()).concat("图"));
        viewHolder.setVisible(R.id.item_dynamic_img_tv_count,false);
        if (picList.size()>4){
            if (position == getItemCount()-1){
                viewHolder.setVisible(R.id.item_dynamic_img_tv_count,true);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (style==0){
                    Intent intent = new Intent(context, PlayerActivity.class);
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                    int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                    intent.putExtra(PlayerActivity.PLAY_PATH, path);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, PhotoViewActivity.class);
                    intent.putExtra(PhotoViewActivity.CURRENT_POSITION, position);
                    intent.putExtra(PhotoViewActivity.URLS, JsonUtil.toJson(picList));
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                    int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                    context.startActivity(intent);
                }
            }
        });
    }
}
