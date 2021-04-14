package com.eghuihe.module_dynamic.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eghuihe.module_dynamic.R;
import com.eghuihe.module_dynamic.ui.activity.PlayerActivity;
import com.huihe.base_lib.model.dynamic.NoteUserEntity;
import com.huihe.base_lib.ui.activity.PhotoViewActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.amin.AnimationConstants;
import com.huihe.base_lib.utils.glide.GlideTools;

import java.util.List;

public class ItemNoteUserImsRvAdapter extends CommonRVAdapter<String> {

    private NoteUserEntity newsInformationInfoEntity;

    public ItemNoteUserImsRvAdapter(int layoutId, Context context, List<String> data, NoteUserEntity newsInformationInfoEntity) {
        super(layoutId, context, data);
        this.newsInformationInfoEntity = newsInformationInfoEntity;
    }

    @Override
    protected void covert(ViewHolder viewHolder, final String s, final int position) {

        ImageView view = viewHolder.getView(R.id.item_imageview);
        ImageView ivPlay = viewHolder.getView(R.id.item_imageview_paly);

        if (getItemCount() == 1) {

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (newsInformationInfoEntity.getStyle() == 0) {
                layoutParams.width = DensityUtils.dp2px(context, 80);
                layoutParams.height = DensityUtils.dp2px(context, 120);
                view.setLayoutParams(layoutParams);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideTools.loadRoundedImage(context, s, DensityUtils.dp2px(context, 8), view);

            } else {
                layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                layoutParams.height = DensityUtils.dp2px(context, 120);
                view.setLayoutParams(layoutParams);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideTools.loadFitWidthImage(context, s, DensityUtils.dp2px(context, 8), view);
            }
        } else {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = DensityUtils.dp2px(context, 80);
            view.setLayoutParams(layoutParams);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            int rowNum = (int) Math.ceil(getItemCount() * 1f / 3);
            int row = (int) Math.ceil((position + 1) * 1f / 3);
            if (rowNum > 1) {
                if (row == 1) {
                    // 第一行
                    if ((position + 1) % 3 == 1) {
                        GlideTools.loadLeftTopRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                    } else if ((position + 1) % 3 == 0) {
                        GlideTools.loadRightTopRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                    } else {
                        GlideTools.loadImage(context, s, view);
                    }
                } else if (row == rowNum) {
                    // 最后一行
                    if ((position + 1) % 3 == 1) {
                        GlideTools.loadLeftBottomRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                    } else if ((position + 1) % 3 == 0) {
                        GlideTools.loadRightBottomRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                    } else {
                        if (position + 1 == getItemCount()) {
                            GlideTools.loadRightBottomRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                        } else {
                            GlideTools.loadImage(context, s, view);
                        }
                    }
                } else {
                    // 中间行
                    GlideTools.loadImage(context, s, view);
                }
            } else {
                if ((position + 1) % 3 == 1) {
                    GlideTools.loadLeftRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                } else if ((position + 1) % 3 == 0) {
                    GlideTools.loadRightRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                } else {
                    if (position + 1 == getItemCount()) {
                        GlideTools.loadRightRoundedImage(context, s, DensityUtils.dp2px(context, 8), view, context.getResources().getColor(R.color.color_88e6e6e6));
                    } else {
                        GlideTools.loadImage(context, s, view);
                    }
                }
            }

        }

        if (newsInformationInfoEntity.getStyle() == 0) {
            ivPlay.setVisibility(View.VISIBLE);
        } else {
            ivPlay.setVisibility(View.GONE);
        }

        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (newsInformationInfoEntity.getStyle() == 0) {
                    Intent intent = new Intent(context, PlayerActivity.class);
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);

                    int centerX = (int) (location[0] + v.getMeasuredWidth() / 2);
                    int centery = (int) (location[1] + v.getMeasuredHeight() / 2);
                    intent.putExtra(PlayerActivity.PLAY_PATH, s);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTX, centerX);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_PIVOTY, centery);
                    intent.putExtra(AnimationConstants.ACTIVITY_ANIMATION_ENABLE, true);
                    context.startActivity(intent);
                } else {
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
            }
        });

    }

}
