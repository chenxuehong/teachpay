package com.eghuihe.module_schedule.ui.mechanism.adapter;

import android.content.Context;
import android.view.View;

import com.eghuihe.module_schedule.R;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SummaryPhotoRvAdapter extends CommonRVAdapter<String> {
    public static final String UPLOAD = "upload";

    public SummaryPhotoRvAdapter(int layoutId, Context context, List<String> data) {
        super(layoutId, context, data);
    }

    @Override
    protected void covert(ViewHolder viewHolder, String photo, int position) {
        RoundedImageView ivIcon = viewHolder.getView(R.id.item_upload_img_icon);

        if (UPLOAD.equals(photo)) {
            viewHolder.setImageResource(R.id.item_upload_img_icon, R.mipmap.add_uploading);
            viewHolder.setVisible(R.id.item_upload_img_icon_delete, false);
        } else {
            viewHolder.setVisible(R.id.item_upload_img_icon_delete, true);
            GlideTools.loadRoundedImage(context, photo, DensityUtils.dp2px(context, 12), ivIcon);
        }
        viewHolder.setOnClickListener(R.id.item_upload_img_icon_delete, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });

    }

}
