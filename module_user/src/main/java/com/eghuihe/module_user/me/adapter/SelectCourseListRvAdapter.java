package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectCourseListRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    private Map<Integer, MasterSetPriceEntity> selectedMap;

    public Map<Integer, MasterSetPriceEntity> getSelectedMap() {
        return selectedMap;
    }

    private OnListener onListener;

    public SelectCourseListRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        selectedMap = new HashMap<>();
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, final int position) {
        String title = masterSetPriceEntity.getTitle();
        String course_num = masterSetPriceEntity.getCourse_num();
        String default_discount_price = masterSetPriceEntity.getDefault_discount_price();
        String original_price = masterSetPriceEntity.getOriginal_price();
        String face_url = masterSetPriceEntity.getFace_url();
        String pay_num = masterSetPriceEntity.getPay_num();
        RoundedImageView ivCover = viewHolder.getView(R.id.item_select_mastersetprice_iv_cover);
        GlideTools.loadImage(context, face_url, ivCover);
        viewHolder.setText(R.id.item_select_mastersetprice_tv_purchase_num,
                String.format(context.getResources().getString(R.string.purchased_num), pay_num));
        if (container(masterSetPriceEntity, "single_payment")) {
            viewHolder.setText(R.id.item_select_mastersetprice_tv_title,
                    "【".concat(course_num)
                            .concat("节】")
                            .concat(NumberUtils.transMoney(default_discount_price))
                            .concat("】")
                            .concat(title));
            viewHolder.setText(R.id.item_select_mastersetprice_tv_discount_price,
                    NumberUtils.transMoney(original_price).concat("/节"));
        } else {
            viewHolder.setText(R.id.item_select_mastersetprice_tv_title,
                    "【".concat(course_num)
                            .concat("节】")
                            .concat(title));
            viewHolder.setText(R.id.item_select_mastersetprice_tv_discount_price,
                    NumberUtils.transMoney(default_discount_price));
        }

        viewHolder.setVisible(R.id.item_select_mastersetprice_tv_activity, false);
        viewHolder.setVisible(R.id.item_select_mastersetprice_iv_grouping_back, false);
        viewHolder.setVisible(R.id.item_select_mastersetprice_iv_single_pay, false);
        if (container(masterSetPriceEntity, "experience_specials")) {
            // 春节活动
            viewHolder.setVisible(R.id.item_select_mastersetprice_tv_activity, true);
        }

        if (container(masterSetPriceEntity, "single_payment")) {
            // 单价支付
            viewHolder.setVisible(R.id.item_select_mastersetprice_iv_grouping_back, true);
        }

        if (container(masterSetPriceEntity, "grouping")) {
            // 拼团返现
            viewHolder.setVisible(R.id.item_select_mastersetprice_iv_single_pay, true);
        }
        final CheckBox checkBox = viewHolder.getView(R.id.item_select_mastersetprice_checkbox);
        boolean isSelected = getIsSelected(position, selectedMap);
        checkBox.setChecked(isSelected);
        viewHolder.setVisible(R.id.item_select_mastersetprice_tv_update, isSelected);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSelected) {
                if (isSelected) {
                    selectedMap.put(position, masterSetPriceEntity);
                } else {
                    selectedMap.remove(position);
                    if (onListener != null) {
                        onListener.updateLivePrice(masterSetPriceEntity, false);
                    }
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.setOnClickListener(
                R.id.item_select_mastersetprice_tv_update,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onListener != null) {
                            onListener.updateLivePrice(masterSetPriceEntity, isSelected);
                        }
                    }
                });

    }

    private boolean getIsSelected(int position, Map<Integer, MasterSetPriceEntity> selectedMap) {
        MasterSetPriceEntity masterSetPriceEntity = selectedMap.get(position);
        return masterSetPriceEntity != null;
    }

    private boolean container(MasterSetPriceEntity masterSetPriceEntity, String type) {
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        if (map != null) {
            List<BusinessActivityTypeEntity> activityEntityList = map.getActivityEntityList();
            if (activityEntityList != null) {
                for (int i = 0; i < activityEntityList.size(); i++) {
                    BusinessActivityTypeEntity businessActivityTypeEntity = activityEntityList.get(i);
                    if (type.equals(businessActivityTypeEntity.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public interface OnListener {
        void updateLivePrice(MasterSetPriceEntity masterSetPriceEntity, boolean isSelected);
    }
}
