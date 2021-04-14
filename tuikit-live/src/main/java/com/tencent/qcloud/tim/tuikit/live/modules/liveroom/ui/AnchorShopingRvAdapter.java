package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.NumberUtils;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.utils.GlideEngine;

import java.util.List;

public class AnchorShopingRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    private OnListener onListener;

    public AnchorShopingRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceDisplayEntity, int position) {

        String faceUrl = masterSetPriceDisplayEntity.getFace_url();
        ImageView ivFaceUrl = viewHolder.getView(R.id.item_anchor_shopping_iv_faceUrl);
        GlideEngine.loadImage(ivFaceUrl, faceUrl);
        String title = masterSetPriceDisplayEntity.getTitle();
        String course_num = masterSetPriceDisplayEntity.getCourse_num();
        course_num = TextUtils.isEmpty(course_num) ? "0" : course_num;
        viewHolder.setText(R.id.item_anchor_shopping_tv_title,
                "【".concat(course_num).concat("节】").concat(title));
        Long sorted = masterSetPriceDisplayEntity.getSorted();
        sorted = sorted == null ? 0 : sorted;
        viewHolder.setText(R.id.item_anchor_shopping_tv_no,
                String.valueOf(sorted));
        final boolean is_live_streaming = masterSetPriceDisplayEntity.getIs_live_streaming();
        TextView tvStartTeaching = viewHolder.getView(R.id.item_anchor_shopping_tv_startTeaching);
        if (is_live_streaming) {
            tvStartTeaching.setTextColor(context.getResources().getColor(R.color.color_333333));
            tvStartTeaching.setBackgroundResource(R.drawable.shape_radius_15_stroke_dp_1_color_666666);
            viewHolder.setText(R.id.item_anchor_shopping_tv_startTeaching, "结束讲解");
        } else {
            tvStartTeaching.setTextColor(context.getResources().getColor(R.color.white));
            tvStartTeaching.setBackgroundResource(R.drawable.shape_bg_radius_15_color_maincolor);
            viewHolder.setText(R.id.item_anchor_shopping_tv_startTeaching, "开始讲解");
        }
        // 直播总价格
        Double live_stream_price = masterSetPriceDisplayEntity.getLive_stream_price();
        live_stream_price = live_stream_price == null ? 0.0 : NumberUtils.tranToTwoDecimal(live_stream_price);
        // 直播单价
        Double living_single_session_price = masterSetPriceDisplayEntity.getLiving_single_session_price();
        living_single_session_price = living_single_session_price == null ? 0.0 : NumberUtils.tranToTwoDecimal(living_single_session_price);
        if (living_single_session_price>0){
            viewHolder.setText(R.id.item_anchor_shopping_tv_title,
                    "【".concat(course_num)
                            .concat("节 ")
                            .concat(NumberUtils.transMoney(String.valueOf(live_stream_price)))
                            .concat("】")
                            .concat(title));
            viewHolder.setText(R.id.item_anchor_shopping_tv_amount,
                    NumberUtils.transMoney(String.valueOf(living_single_session_price)).concat("/节"));
        }else {
            viewHolder.setText(R.id.item_anchor_shopping_tv_title,
                    "【".concat(course_num)
                            .concat("节")
                            .concat("】")
                            .concat(title));
            viewHolder.setText(R.id.item_anchor_shopping_tv_amount,
                    NumberUtils.transMoney(String.valueOf(live_stream_price)));
        }
        String pay_num = masterSetPriceDisplayEntity.getPay_num();
        pay_num = TextUtils.isEmpty(pay_num) ? "0" : pay_num;
        viewHolder.setText(R.id.item_anchor_shopping_tv_payNum,
                String.format(context.getResources().getString(R.string.purchased_num), pay_num));
        if (container(masterSetPriceDisplayEntity, "experience_specials")) {
            // 春节活动
            viewHolder.setVisible(R.id.item_anchor_shopping_iv_all_discount, true);
        }

        if (container(masterSetPriceDisplayEntity, "single_payment")) {
            // 单价支付
            viewHolder.setVisible(R.id.item_anchor_shopping_iv_single_pay, true);
        }

        if (container(masterSetPriceDisplayEntity, "grouping")) {
            // 拼团返现
            viewHolder.setVisible(R.id.item_anchor_shopping_iv_grouping_back, true);
        }
        viewHolder.setOnClickListener(R.id.item_anchor_shopping_tv_startTeaching, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    if (!is_live_streaming) {
                        onListener.startTeach(masterSetPriceDisplayEntity);
                    } else {
                        onListener.stopTeach(masterSetPriceDisplayEntity);
                    }
                }

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

    public interface OnListener {
        void startTeach(MasterSetPriceEntity masterSetPriceDisplayEntity);

        void stopTeach(MasterSetPriceEntity masterSetPriceDisplayEntity);
    }
}