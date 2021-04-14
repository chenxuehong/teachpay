package com.eghuihe.module_user.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.eghuihe.module_user.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.ActivityEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.adapter.EmptyRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class RewardCourseRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    public RewardCourseRvAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, int position) {
        RoundedImageView ivCover = viewHolder.getView(R.id.item_reward_course_iv_cover);
        String face_url = masterSetPriceEntity.getFace_url();
        GlideTools.loadImage(context, face_url, ivCover);
        String label = masterSetPriceEntity.getLabel();
        String categories = masterSetPriceEntity.getCategories();
        if (TextUtils.isEmpty(categories)) {
            viewHolder.setText(R.id.item_reward_course_tv_label, label);
        } else {
            viewHolder.setText(R.id.item_reward_course_tv_label,
                    TextUtils.isEmpty(label) ? categories : categories.concat("/").concat(label));
        }
        String title = masterSetPriceEntity.getTitle();
        String course_num = masterSetPriceEntity.getCourse_num();
        viewHolder.setText(R.id.item_reward_course_tv_name, "【".concat(course_num).concat("节】").concat(title));
        viewHolder.setVisible(R.id.item_reward_course_tv_discount_amount, false);
        MasterSetPriceEntity.Map map = masterSetPriceEntity.getMap();
        if (map != null) {
            String counponPrice = "0.0";
            List<MasterSetPriceEntity.Map.PriceEntity> priceList = map.getPriceList();
            if (priceList != null && priceList.size() > 0) {
                MasterSetPriceEntity.Map.PriceEntity priceEntity = priceList.get(0);
                counponPrice = priceEntity.getPrice();
                counponPrice = TextUtils.isEmpty(counponPrice) ? "0.0" : counponPrice;
                if (!TextUtils.isEmpty(counponPrice)) {
                    viewHolder.setText(R.id.item_reward_course_tv_amount, NumberUtils.transMoney(counponPrice).concat("/节"));
                }
            }
            ActivityEntity activityEntity = map.getActivityEntity();
            if (activityEntity != null) {
                String price = activityEntity.getPrice();
                price = NumberUtils.transMoney(price);
                viewHolder.setText(R.id.item_reward_course_tv_amount, price.concat("/节"));
                viewHolder.setStrikeThrough(R.id.item_reward_course_tv_discount_amount, counponPrice.concat("/节"));
                viewHolder.setVisible(R.id.item_reward_course_tv_discount_amount, true);
            }
        }
        double latitude = masterSetPriceEntity.getLatitude();
        double longitude = masterSetPriceEntity.getLongitude();
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        viewHolder.setText(R.id.item_reward_course_tv_distance,
                distanceStr);

        String pay_num = masterSetPriceEntity.getPay_num();
        Boolean is_attend_activities = masterSetPriceEntity.getIs_attend_activities();
        viewHolder.setVisible(R.id.item_reward_course_iv_all_discount, is_attend_activities == null ? false : is_attend_activities);
        viewHolder.setText(R.id.item_reward_course_tv_payNum, "已售:".concat(pay_num));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
            }
        });

    }

    @Override
    protected void convertEmptyView(ViewHolder viewHolder, int i) {
        viewHolder.setText(R.id.tv_no_data, "暂无课程数据");
    }
}
