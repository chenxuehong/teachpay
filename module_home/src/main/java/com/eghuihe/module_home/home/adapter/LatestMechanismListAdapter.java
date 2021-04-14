package com.eghuihe.module_home.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.eghuihe.module_home.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

public class LatestMechanismListAdapter extends EmptyRVAdapter<MasterMechanismModel.MasterMechanismEntity> {
    public LatestMechanismListAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MasterMechanismModel.MasterMechanismEntity masterMechanismEntity, int position) {
        RoundedImageView ivCover = viewHolder.getView(R.id.item_latest_mechanism_iv_cover);
        ViewGroup.LayoutParams layoutParams = ivCover.getLayoutParams();
        layoutParams.width = DensityUtils.getScreenWidth(context) / 3 - DensityUtils.dp2px(context, 15);
        layoutParams.height = layoutParams.width * 101 / 108;
        ivCover.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams itemLayoutParams = viewHolder.itemView.getLayoutParams();
        itemLayoutParams.width = DensityUtils.getScreenWidth(context) / 3;
        viewHolder.itemView.setLayoutParams(itemLayoutParams);

        String mechanism_logo = masterMechanismEntity.getMechanism_logo();
        String mechanism_name = masterMechanismEntity.getMechanism_name();

        String categoriesChild = masterMechanismEntity.getCategories_child();
        GlideTools.loadRoundedImage(context, mechanism_logo, DensityUtils.dp2px(context, 5), ivCover);
        viewHolder.setText(R.id.item_latest_mechanism_tv_type, categoriesChild);
        viewHolder.setText(R.id.item_latest_mechanism_tv_name, mechanism_name);
        double latitude = masterMechanismEntity.getLatitude();
        double longitude = masterMechanismEntity.getLongitude();
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        viewHolder.setText(R.id.item_latest_mechanism_tv_distance,
                distanceStr);
        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String mechanism_id = masterMechanismEntity.getId();
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_DETAIL,mechanism_id));
            }
        });
    }

    @Override
    protected void convertEmptyView(ViewHolder viewHolder, int i) {
        viewHolder.setText(R.id.tv_no_data,
                context.getResources().getString(R.string.No_latest_resident_organization));
    }
}
