package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.utils.GlideEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectLiveGoodRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    private Map<Integer, MasterSetPriceEntity> selectEntityMap;

    public Map<Integer, MasterSetPriceEntity> getSelectEntityMap() {
        return selectEntityMap;
    }

    public SelectLiveGoodRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
        selectEntityMap = new HashMap<>();
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, final int position) {
        RoundedImageView ivCover = viewHolder.getView(R.id.item_select_live_good_iv_faceUrl);
        String face_url = masterSetPriceEntity.getFace_url();
        GlideEngine.loadImage(ivCover, face_url);
        String title = masterSetPriceEntity.getTitle();
        String course_num = masterSetPriceEntity.getCourse_num();
        String pay_num = masterSetPriceEntity.getPay_num();
        viewHolder.setText(R.id.item_select_live_good_tv_title,
                "【".concat(course_num).concat("节】").concat(title));
        viewHolder.setText(R.id.item_select_live_good_tv_payNum,
                String.format(context.getResources().getString(R.string.purchased_num), pay_num));
        // 单价
        String original_price = masterSetPriceEntity.getOriginal_price();
        original_price = String.valueOf(NumberUtils.tranToTwoDecimal(original_price));
        // 原价
        String amout = masterSetPriceEntity.getAmout();
        // 折扣价
        String discount_amout = masterSetPriceEntity.getDiscount_amout();
        discount_amout = String.valueOf(NumberUtils.tranToTwoDecimal(discount_amout));
        // 默认价格
        String default_discount_price = masterSetPriceEntity.getDefault_discount_price();
        if (container(masterSetPriceEntity, "single_payment")) {
            // 参与单价课活动
            // 显示单价课价格 original_price
            viewHolder.setText(R.id.item_select_live_good_tv_amount,
                    NumberUtils.transMoney(original_price).concat("/节"));
            // 显示默认价格 default_discount_price
            viewHolder.setText(R.id.item_select_live_good_tv_title,
                    "【".concat(course_num)
                            .concat("节 ")
                            .concat(NumberUtils.transMoney(default_discount_price))
                            .concat("】")
                            .concat(title));
        } else {
            // 不参加单价课价格
            // 显示默认折扣价格和原价
            viewHolder.setText(R.id.item_select_live_good_tv_amount,
                    NumberUtils.transMoney(default_discount_price));
        }
        if (container(masterSetPriceEntity, "experience_specials")) {
            // 春节活动
            viewHolder.setVisible(R.id.item_select_live_good_iv_all_discount, true);
        }

        if (container(masterSetPriceEntity, "single_payment")) {
            // 单价支付
            viewHolder.setVisible(R.id.item_select_live_good_iv_single_pay, true);
        }

        if (container(masterSetPriceEntity, "grouping")) {
            // 拼团返现
            viewHolder.setVisible(R.id.item_select_live_good_iv_grouping_back, true);
        }
        final RadioButton rbtn = viewHolder.getView(R.id.item_select_live_good_rbtn_check);
        MasterSetPriceEntity masterSetPriceEntity1 = selectEntityMap.get(position);
        final String live_streaming_id = masterSetPriceEntity.getLive_streaming_id();
        if ("0".equals(live_streaming_id) || TextUtils.isEmpty(live_streaming_id)) {
            rbtn.setVisibility(View.VISIBLE);
            viewHolder.setVisible(R.id.item_select_live_good_tv_living, false);
        } else {
            rbtn.setVisibility(View.GONE);
            viewHolder.setVisible(R.id.item_select_live_good_tv_living, true);
        }

        if (masterSetPriceEntity1 != null) {
            rbtn.setChecked(true);
        } else {
            rbtn.setChecked(false);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbtn.setChecked(!rbtn.isChecked());
            }
        });
        rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!"0".equals(live_streaming_id) && !TextUtils.isEmpty(live_streaming_id)) {
                    ToastUtils.showShortToast(context, "该商品正在直播中");
                    rbtn.setChecked(false);
                    return;
                }
                if (isChecked) {
                    selectEntityMap.clear();
                    selectEntityMap.put(position, masterSetPriceEntity);
                    notifyDataSetChanged();
                } else {
                    selectEntityMap.remove(position);
                }
                EventBusUtils.sendEvent(new Event(EventAction.SELECT_NUM, selectEntityMap.size()));
            }
        });
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
}
