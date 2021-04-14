package com.eghuihe.module_user.activities.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.glide.GlideTools;

public class ActivitiesRvAdapter extends EmptyRVAdapter<BusinessActivityTypeEntity> {

    private OnListener onListener;

    public ActivitiesRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final BusinessActivityTypeEntity businessActivityTypeEntity, int position) {
        ImageView ivCover = viewHolder.getView(R.id.item_activity_iv_cover);
        viewHolder.setText(R.id.item_activity_tv_title,
                "【".concat(businessActivityTypeEntity.getTags())
                        .concat("】 ")
                        .concat(businessActivityTypeEntity.getTitle()));
        String start_time = businessActivityTypeEntity.getStart_time();
        String end_time = businessActivityTypeEntity.getEnd_time();
        start_time = DateUtils.getOtherDateStr(start_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        end_time = DateUtils.getOtherDateStr(end_time, DateUtils.YMDHMSFormatStr, DateUtils.YMDFormatStr);
        viewHolder.setText(R.id.item_activity_tv_due,
                start_time.concat("至").concat(end_time));
        GlideTools.loadImage(context, businessActivityTypeEntity.getBackground_image(), ivCover);
        viewHolder.setOnClickListener(R.id.item_activity_tv_join,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener != null) {
                            onListener.onViewDetail(businessActivityTypeEntity);
                        }
                    }
                });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    onListener.onViewDetail(businessActivityTypeEntity);
                }
            }
        });
    }

    public interface OnListener {
        void onViewDetail(BusinessActivityTypeEntity businessActivityTypeEntity);
    }
}
