package com.tencent.qcloud.tim.tuikit.live.modules.liveroom.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.NumberUtils;
import com.tencent.qcloud.tim.tuikit.live.R;
import com.tencent.qcloud.tim.tuikit.live.utils.GlideEngine;

import java.util.List;

public class AudienceShoppingRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {
    private OnListener onListener;

    public AudienceShoppingRvAdapter(int layoutId, Context context, OnListener onListener) {
        super(layoutId, context);
        this.onListener = onListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, int position) {
        String faceUrl = masterSetPriceEntity.getFace_url();
        ImageView ivFaceUrl = viewHolder.getView(R.id.item_audience_shopping_iv_faceUrl);
        GlideEngine.loadImage(ivFaceUrl, faceUrl);
        String title = masterSetPriceEntity.getTitle();
        String course_num = masterSetPriceEntity.getCourse_num();
        course_num = TextUtils.isEmpty(course_num) ? "0" : course_num;
        viewHolder.setText(R.id.item_audience_shopping_tv_title,
                "【".concat(course_num).concat("节】").concat(title));
        Long sorted = masterSetPriceEntity.getSorted();
        sorted = sorted == null ? 0 : sorted;
        viewHolder.setText(R.id.item_audience_shopping_tv_no,
                String.valueOf(sorted));
        final boolean is_live_streaming = masterSetPriceEntity.getIs_live_streaming();
        viewHolder.setVisible(R.id.item_audience_shopping_tv_is_Teaching, is_live_streaming);
        // 直播总价格
        Double live_stream_price = masterSetPriceEntity.getLive_stream_price();
        live_stream_price = live_stream_price == null ? 0.0 : NumberUtils.tranToTwoDecimal(live_stream_price);
        // 直播单价
        Double living_single_session_price = masterSetPriceEntity.getLiving_single_session_price();
        living_single_session_price = living_single_session_price == null ? 0.0 : NumberUtils.tranToTwoDecimal(living_single_session_price);

        if (living_single_session_price>0){
            viewHolder.setText(R.id.item_audience_shopping_tv_title,
                    "【".concat(course_num)
                            .concat("节 ")
                            .concat(NumberUtils.transMoney(String.valueOf(live_stream_price)))
                            .concat("】")
                            .concat(title));
            viewHolder.setText(R.id.item_audience_shopping_tv_amount,
                    NumberUtils.transMoney(String.valueOf(living_single_session_price)).concat("/节"));
        }else {
            viewHolder.setText(R.id.item_audience_shopping_tv_title,
                    "【".concat(course_num)
                            .concat("节")
                            .concat("】")
                            .concat(title));
            viewHolder.setText(R.id.item_audience_shopping_tv_amount,
                    NumberUtils.transMoney(String.valueOf(live_stream_price)));
        }
        String pay_num = masterSetPriceEntity.getPay_num();
        pay_num = TextUtils.isEmpty(pay_num) ? "0" : pay_num;
        viewHolder.setText(R.id.item_audience_shopping_tv_payNum,
                String.format(context.getResources().getString(R.string.purchased_num), pay_num));
        if (container(masterSetPriceEntity, "experience_specials")) {
            // 春节活动
            viewHolder.setVisible(R.id.item_audience_shopping_iv_all_discount, true);
        }

        if (container(masterSetPriceEntity, "single_payment")) {
            // 单价支付
            viewHolder.setVisible(R.id.item_audience_shopping_iv_single_pay, true);
        }

        if (container(masterSetPriceEntity, "grouping")) {
            // 拼团返现
            viewHolder.setVisible(R.id.item_audience_shopping_iv_grouping_back, true);
        }
        viewHolder.setOnClickListener(R.id.item_audience_shopping_tv_buy, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListener != null) {
                    onListener.goGuy(masterSetPriceEntity);
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
        void goGuy(MasterSetPriceEntity masterSetPriceEntity);
    }
}
