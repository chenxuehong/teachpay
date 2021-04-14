package com.huihe.base_lib.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.View;

import com.huihe.base_lib.R;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.BusinessActivityTypeEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.MyTextView;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.utils.DensityUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.GeometryUtil;
import com.huihe.base_lib.utils.NumberUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class TeachPayCourseListRvAdapter extends EmptyRVAdapter<MasterSetPriceEntity> {

    public TeachPayCourseListRvAdapter(int layoutId, Context context) {
        super(layoutId, context);
    }

    public TeachPayCourseListRvAdapter(int layoutId, Context context, int emptyDataLayoutId) {
        super(layoutId, context, emptyDataLayoutId);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MasterSetPriceEntity masterSetPriceEntity, int position) {
        String label = masterSetPriceEntity.getLabel();
        String face_url = masterSetPriceEntity.getFace_url();
        String title = masterSetPriceEntity.getTitle();
        String pay_num = masterSetPriceEntity.getPay_num();

        RoundedImageView ivCover = viewHolder.getView(R.id.item_query_mechanism_course_iv_cover);
        GlideTools.loadImage(context, face_url, ivCover);
        String course_num = masterSetPriceEntity.getCourse_num();
        course_num = TextUtils.isEmpty(course_num) ? "1" : course_num;

        MyTextView tvName = viewHolder.getView(R.id.item_query_mechanism_course_tv_name);
        tvName.setText("");
        viewHolder.setText(R.id.item_query_mechanism_course_tv_payNum,
                String.format(context.getResources().getString(R.string.purchased_num), pay_num));
        String price_list = masterSetPriceEntity.getPrice_list();
        String categories = masterSetPriceEntity.getCategories();
        if (!TextUtils.isEmpty(categories)) {
            viewHolder.setText(R.id.item_query_mechanism_course_tv_label,
                    TextUtils.isEmpty(label) ?
                            categories : categories.concat("/").concat(label));
        } else {
            viewHolder.setText(R.id.item_query_mechanism_course_tv_label, label);
        }
        Double longitude = masterSetPriceEntity.getLongitude();
        longitude = longitude == null ? 0d : longitude;
        Double latitude = masterSetPriceEntity.getLatitude();
        latitude = latitude == null ? 0d : latitude;
        double distance = GeometryUtil.GetLongDistance(
                Double.valueOf(LoginHelper.getLongitude()),
                Double.valueOf(LoginHelper.getLatitude()),
                longitude,
                latitude);
        String distanceStr = NumberUtils.getDistance(distance);
        viewHolder.setText(R.id.item_query_mechanism_course_tv_distance, distanceStr);
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
        String live_streaming_id = masterSetPriceEntity.getLive_streaming_id();
        boolean isLiving = !"0".equals(live_streaming_id) && !TextUtils.isEmpty(live_streaming_id);
        if (container(masterSetPriceEntity, "single_payment")) {

            // 参与单价课活动
            // 显示单价课价格 original_price
            viewHolder.setText(R.id.item_query_mechanism_course_tv_discount_amount,
                    NumberUtils.transMoney(original_price).concat("/节"));
            viewHolder.setVisible(R.id.item_query_mechanism_course_tv_amount,
                    false);
            // 显示默认价格 default_discount_price
            if (isLiving)
                tvName.insertScaleDrawable(R.mipmap.living, DensityUtils.dp2px(context, 18));
            tvName.append("【".concat(course_num).concat("节 ")
                    .concat(NumberUtils.transMoney(default_discount_price))
                    .concat("】")
                    .concat(title));
        } else {
            // 不参加单价课价格
            // 显示默认折扣价格和原价
            viewHolder.setText(R.id.item_query_mechanism_course_tv_discount_amount,
                    NumberUtils.transMoney(default_discount_price));
            viewHolder.setStrikeThrough(R.id.item_query_mechanism_course_tv_amount,
                    NumberUtils.transMoney(amout));
            viewHolder.setVisible(R.id.item_query_mechanism_course_tv_amount,
                    true);
            if (isLiving)
                tvName.insertScaleDrawable(R.mipmap.living, DensityUtils.dp2px(context, 18));
            tvName.append("【".concat(course_num).concat("节】").concat(title));
        }
        viewHolder.setVisible(R.id.item_query_mechanism_course_iv_all_discount, false);
        viewHolder.setVisible(R.id.item_query_mechanism_course_iv_grouping_back, false);
        viewHolder.setVisible(R.id.item_query_mechanism_course_iv_single_pay, false);
        if (container(masterSetPriceEntity, "experience_specials")) {
            // 春节活动
            viewHolder.setVisible(R.id.item_query_mechanism_course_iv_all_discount, true);
        }

        if (container(masterSetPriceEntity, "single_payment")) {
            // 单价支付
            viewHolder.setVisible(R.id.item_query_mechanism_course_iv_single_pay, true);
        }

        if (container(masterSetPriceEntity, "grouping")) {
            // 拼团返现
            viewHolder.setVisible(R.id.item_query_mechanism_course_iv_grouping_back, true);
        }
        viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                EventBusUtils.sendEvent(new Event(EventAction.MECHANISM_COURSE_DETAIL, masterSetPriceEntity));
            }
        });

        if (isShowLine) {
            viewHolder.setVisible(R.id.item_query_mechanism_course_view_line, position != getItemCount() - 1);
        } else {
            viewHolder.setVisible(R.id.item_query_mechanism_course_view_line, false);
        }
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

    private boolean isShowLine;

    public void showLine(boolean isShowLine) {
        this.isShowLine = isShowLine;
    }
}
